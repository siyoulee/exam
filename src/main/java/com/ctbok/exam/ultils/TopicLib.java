package com.ctbok.exam.ultils;

import com.alibaba.fastjson2.JSONObject;
import com.ctbok.exam.init.MapInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

@RestController
@RequestMapping(path = "/exam/ultils")
public class TopicLib {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String sql = "";
    private SqlRowSet rs;
    private SqlRowSet rs2;

    HashMap<String, String[]> examMap = MapInit.examMapStatic;                    //以exam_id为key，exam内容为value
    HashMap<String, String[]> paperMap = MapInit.paperMapStatic;                  //以paper_id为key，paper内容为values
    //    HashMap<String, HashMap> paperQuestionMap = MapInit.paperQuestionMapStatic;   //以paper_id为key,以questionMap为value
//    HashMap<String, String[]> questionMapByPaper = MapInit.questionMapByPaperStatic;            //以question_id为key，以question内容为value，但这个是分了paper去做的
    HashMap<String, String[]> questionMapByAll = MapInit.questionMapByAllStatic;            //以question_id为key，以question内容为value，但这个是分了paper去做的
    LinkedHashMap<String, ArrayList<String[]>> answerMap = MapInit.answerMapStatic;                //以question_id为key，以answer内容为value

    @RequestMapping(path = "/UpdateCache", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String UpdateCache(@RequestBody JSONObject json, @RequestHeader HttpHeaders headers) {
        //** 缓存课程
        /*
        [0]:name
        [1]:desc
        [2]:language
         */  //参数说明
        examMap.clear();

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
        paperMap.clear();

        sql = "select * from exam_paper where status = '1' order by id asc";
        rs = jdbcTemplate.queryForRowSet(sql);
        while (rs.next()) {
            String[] values = {rs.getString("exam_id"), rs.getString("paper_name"), rs.getString("paper_desc"),
                    rs.getString("paper_points"), rs.getString("paper_time")};
            paperMap.put(rs.getString("id"), values);
        }


        /* 缓存问题清单（以paper_id为分割）
        //参数说明
        [0]:question_type
        [1]:question_name
        [2]:question_name_cn
        [3]:question_subject
        [4]:question_points
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
        */


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
        [11]:wx_video_url
         */   //参数说明
        questionMapByAll.clear();
        answerMap.clear();

        sql = "select * from exam_question where status = '1' order by id asc";
        rs = jdbcTemplate.queryForRowSet(sql);
        while (rs.next()) {    //把问题清单捞出来
            String[] questionRecord = {rs.getString("question_type"), rs.getString("question_name"),
                    rs.getString("question_name_cn"), rs.getString("question_subject"),
                    rs.getString("question_points"), rs.getString("clarify"),
                    rs.getString("file_id"), rs.getString("app_id"), rs.getString("psign"),
                    rs.getString("exam_id"), rs.getString("paper_id"), rs.getString("wx_video_url")};
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

        return "更新完成";
    }

    @RequestMapping(path = "/PublicTopic", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String PublicTopic(@RequestBody JSONObject json, @RequestHeader HttpHeaders headers) {
        String idList = json.get("id").toString();
        sql = "update exam_question set status = '1' where id in (" + idList + ")";
        jdbcTemplate.execute(sql);

        return "发布完成";
    }

    @RequestMapping(path = "/GenerateSequence", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String GenerateSequence(@RequestBody JSONObject json, @RequestHeader HttpHeaders headers) {
        String questionId = "";
        String examId = "";
        String paperId = "";
        String questionType = "";
        String questionSubject = "";
        String userId = "";

        // 第一步，找出有效用户
        sql = "select * from users where status = '1'";
        SqlRowSet usersRS = jdbcTemplate.queryForRowSet(sql);
        while (usersRS.next()) {
            userId = usersRS.getString("id");

            // 第二步，拿出究竟这个人，有多少个课程是可以用的，下面这段，都是在有课程权限的情况下的增删改
            sql = "select * from exam_roles where user_id = " + userId + " and role = '1'";
            SqlRowSet rolesRS = jdbcTemplate.queryForRowSet(sql);
            while (rolesRS.next()) {
                examId = rolesRS.getString("exam_id");
                String insertSql = "insert into exam_sequence(user_id,question_id,exam_id,paper_id,question_type,question_subject,status,do_status) values ";   //插入新题的语句
                String deleteSql = "";

                //第三步，先看看试题列表，比顺序做题列表大的时候的情况，就把试题列表加进顺序做题列表中
                sql = "select a.* from exam_question a right join exam_paper b on a.paper_id = b.id where b.status = '1' and a.status = '1' and a.exam_id = " + examId + " and a.id not in (" +
                        "select question_id from exam_sequence where user_id = " + userId + " and exam_id = " + examId + ") order by a.question_type asc";
                SqlRowSet questionRS = jdbcTemplate.queryForRowSet(sql);
                questionRS.last();    //记录指针移到最后
                int rowCount = questionRS.getRow();     //获取记录行数

                if (rowCount > 0) {   //如果记录数为0，就不用insert了
                    questionRS.beforeFirst();   //回到第一行
                    while (questionRS.next()) {
                        questionId = questionRS.getString("id");
                        paperId = questionRS.getString("paper_id");
                        questionType = questionRS.getString("question_type");
                        questionSubject = questionRS.getString("question_subject");
                        insertSql = insertSql + " ('" + userId + "','" + questionId + "','" + examId + "','" + paperId + "','" + questionType + "','" + questionSubject + "','3','0'),";
                    }
                    insertSql = insertSql.substring(0, insertSql.length() - 1);  //最后的，不能要
                    jdbcTemplate.execute(insertSql);   //最后才执行insert，是因为这样效率更高，另外，
                }

                //第四步，再看看顺序做题列表，比试题列表大的情况，这表明了试题进行了删除
                deleteSql = "delete from exam_sequence where exam_id = " + examId + " and user_id = " + userId + " and question_id not in (" +
                        "select a.id from exam_question a right join exam_paper b on a.paper_id = b.id where b.status='1' and  a.status = '1' and a.exam_id = " + examId + ")";
                jdbcTemplate.execute(deleteSql);
            }
        }

        // 第五步，找出失效的用户，从exam_sequence中删除
        sql = "delete from exam_sequence where user_id in (select id from users where status = '0')";
        jdbcTemplate.execute(sql);

        return "exam_sequence表同步完成";
    }
}
