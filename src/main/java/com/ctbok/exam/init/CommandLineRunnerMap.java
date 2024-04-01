package com.ctbok.exam.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CommandLineRunnerMap implements CommandLineRunner {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String sql = "";
    SqlRowSet rs;
    SqlRowSet rs2;

    HashMap<String, String[]> examMap = MapInit.examMapStatic;                    //以exam_id为key，exam内容为value
    HashMap<String, String[]> paperMap = MapInit.paperMapStatic;                  //以paper_id为key，paper内容为values
    HashMap<String, HashMap> paperQuestionMap = MapInit.paperQuestionMapStatic;   //以paper_id为key,以questionMap为value
    HashMap<String, String[]> questionMapByPaper = MapInit.questionMapByPaperStatic;            //以question_id为key，以question内容为value，但这个是分了paper去做的
    HashMap<String, String[]> questionMapByAll = MapInit.questionMapByAllStatic;            //以question_id为key，以question内容为value，但这个是分了paper去做的
    HashMap<String, ArrayList<String[]>> answerMap = MapInit.answerMapStatic;                //以question_id为key，以answer内容为value

    @Override
    public void run(String... args) throws Exception {
        ArrayList<String[]> questionList = new ArrayList<>();

        //** 缓存课程
        /*
        [0]:name
        [1]:desc
        [2]:language
         */  //参数说明
        sql = "select * from exams where status = '1' order by id asc";
        rs = jdbcTemplate.queryForRowSet(sql);
        while (rs.next()) {
            String[] values = {rs.getString("name"), rs.getString("desc"), rs.getString("language")};
            examMap.put(rs.getString("id"), values);
        }


        //** 缓存试卷
        /*
        [0]:examId
        [1]:paper_name
        [2]:paperDesc
        [3]:paper_points
        [4]:paper_time
         */   //参数说明
        sql = "select * from exam_paper where status = '1' order by id asc";
        rs = jdbcTemplate.queryForRowSet(sql);
        while (rs.next()) {
            String[] values = {rs.getString("exam_id"), rs.getString("paper_name"), rs.getString("paper_desc"),
                    rs.getString("paper_points"), rs.getString("paper_time")};
            paperMap.put(rs.getString("id"), values);
        }


        //** 缓存问题清单（以paper_id为分割）
        /*
        [0]:question_type
        [1]:question_name
        [2]:question_name_cn
        [3]:question_subject
        [4]:question_points
         */   //参数说明
        Iterator<Map.Entry<String, String[]>> iteratorPaperMap = paperMap.entrySet().iterator();
        while (iteratorPaperMap.hasNext()) {      //遍历试卷列表
            Map.Entry<String, String[]> entry = iteratorPaperMap.next();
            String paperId = entry.getKey();      //从Map中获取试卷ID

            sql = "select * from exam_question where paper_id = " + paperId + " and status = '1' order by id asc";
            rs = jdbcTemplate.queryForRowSet(sql);
            while (rs.next()) {    //把问题清单捞出来
                String[] questionRecord = {rs.getString("question_type"), rs.getString("question_name"),
                        rs.getString("question_name_cn"), rs.getString("question_subject"), rs.getString("question_points")};
                questionMapByPaper.put(rs.getString("id"), questionRecord);     //以question_id为key，question内容为value
            }

            paperQuestionMap.put(paperId, questionMapByPaper);    //把questionMap放到paperQuestionMap中，这样可以先索引paper_id，再索引question_id
        }


        //** 缓存问题清单（全部）
        /*
        [0]:question_type
        [1]:question_name
        [2]:question_name_cn
        [3]:question_subject
        [4]:question_points
        [5]:clarify
        [6]:file_id
        [7]:psign
        [8]:app_id
        [9]:exam_id
        [10]:paper_id

         */   //参数说明
        sql = "select * from exam_question where status = '1' order by id asc";
        rs = jdbcTemplate.queryForRowSet(sql);
        while (rs.next()) {    //把问题清单捞出来
            String[] questionRecord = {rs.getString("question_type"), rs.getString("question_name"),
                    rs.getString("question_name_cn"), rs.getString("question_subject"),
                    rs.getString("question_points"), rs.getString("clarify"),
                    rs.getString("file_id"), rs.getString("app_id"),
                    rs.getString("psign"), rs.getString("exam_id"), rs.getString("paper_id")};
            questionMapByAll.put(rs.getString("id"), questionRecord);     //以question_id为key，question内容为value

            //处理答案
            //** 缓存答案清单
            /*
            [0]:answer_id
            [1]:answer_content
            [2]:answer_content_cn
            [3]:correct_answer
             */   //参数说明
            sql = "select * from exam_answer where question_id = " + rs.getString("id") + " order by id asc";
            rs2 = jdbcTemplate.queryForRowSet(sql);
            ArrayList<String[]> answerList = new ArrayList<>();
            while (rs2.next()) {
                String[] answerRecord = {rs2.getString("id"), rs2.getString("answer_content"),
                        rs2.getString("answer_content_cn"), rs2.getString("correct_answer")};
                answerList.add(answerRecord);
            }
            answerMap.put(rs.getString("id"), answerList);
        }

    }

//        Collections.shuffle(questionList);
}

