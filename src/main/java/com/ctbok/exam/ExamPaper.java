package com.ctbok.exam;

import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.util.*;


@RestController
@RequestMapping(path = "/exam")
public class ExamPaper {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String sql;

    @RequestMapping(path = "/PaperList", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String PaperList(@RequestBody @NotNull JSONObject json) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        String examId = json.get("examId").toString();
        String practiseType = json.get("practiseType").toString();
        String userId = json.get("userId").toString();
        String questionSubject = json.get("questionSubject").toString();
        String paperId = "";
        List<Map<String, Object>> paperList = new ArrayList<Map<String, Object>>();   //答案的数组

        jsonMap.put("userId", userId);
        jsonMap.put("examId", examId);

        try {
            practiseType = json.get("practiseType").toString();
        } catch (Exception e) {
        }

        if (practiseType.equals("4")) {   //如果是模拟考
            sql = "select * from exams where id = " + examId;
            SqlRowSet paperRs = jdbcTemplate.queryForRowSet(sql);     //这里为了方便，还是延用paper参数
            paperRs.next();
            Map<String, Object> paperMap = new LinkedHashMap<>();  //放question的map
            paperMap.put("paperId", paperRs.getString("id"));
            paperMap.put("paperName", paperRs.getString("name"));

            sql = "select * from exam_paper where id = " + paperRs.getString("id");
            SqlRowSet paperRs1 = jdbcTemplate.queryForRowSet(sql);     //这里为了方便，还是延用paper参数
            paperRs1.next();
            paperMap.put("paperPhoto", paperRs1.getString("paper_photo"));

            paperList.add(paperMap);

        } else {     //如果是正常做题、错题重做、收藏题目重做

            sql = "select * from exam_paper where exam_id = " + examId + " and status = '1' order by id asc";
            SqlRowSet paperRs = jdbcTemplate.queryForRowSet(sql);
            while (paperRs.next()) {
                Map<String, Object> paperMap = new LinkedHashMap<>();  //放question的map
                paperId = paperRs.getString("id");

                if (practiseType.equals("0") || practiseType.equals("3")) {    //正常做题，模拟考
                    sql = "select count(*) as totalCount from exam_question where paper_id = " + paperRs.getString("id") +
                            " and question_subject = '" + questionSubject + "' and status = '1'";

                } else if (practiseType.equals("1")) {    //错题重做
                    sql = "select count(*) as totalCount from exam_question where paper_id = " + paperRs.getString("id") +
                            " and question_subject = '" + questionSubject + "' and  status = '1' and id in (" +
                            "select question_id from wrong_question where user_id = " + userId + " and exam_id = " + examId + ")";

                } else if (practiseType.equals("2")) {    //收藏题目重做
                    sql = "select count(*) as totalCount from exam_question where paper_id = " + paperRs.getString("id") +
                            " and question_subject = '" + questionSubject + "' and status = '1' and id in (" +
                            "select question_id from favorites_question where user_id = " + userId + " and exam_id = " + examId + ")";

                }

                SqlRowSet totalCountRs = jdbcTemplate.queryForRowSet(sql);
                totalCountRs.next();
                String total = totalCountRs.getString("totalCount");

                if (!total.equals("0")) {
                    paperMap.put("paperId", paperRs.getString("id"));
                    paperMap.put("paperName", paperRs.getString("paper_name"));
                    paperMap.put("paperPhoto", paperRs.getString("paper_photo"));
                    paperMap.put("totalCount", total);

                    if (practiseType.equals("0") || practiseType.equals("3")) {    //正常做题，模拟考
                        sql = "select count(*) as doneCount from exam_sequence where paper_id = " + paperRs.getString("id") +
                                " and question_subject = '" + questionSubject + "' and do_status = '2' and user_id = " + userId;

                    } else if (practiseType.equals("1")) {    //错题重做
                        sql = "select count(*) as doneCount from exam_sequence where paper_id = " + paperRs.getString("id") +
                                " and question_subject = '" + questionSubject + "' and wrong_do_status = '2' and id in (" +
                                "select question_id from wrong_question where user_id = " + userId + " and exam_id = " + examId + ")";

                    } else if (practiseType.equals("2")) {    //收藏题目重做
                        sql = "select count(*) as doneCount from exam_sequence where paper_id = " + paperRs.getString("id") +
                                " and question_subject = '" + questionSubject + "' and fav_do_status = '2' and id in (" +
                                "select question_id from favorites_question where user_id = " + userId + " and exam_id = " + examId + ")";

                    }
                    SqlRowSet doneCountRs = jdbcTemplate.queryForRowSet(sql);
                    doneCountRs.next();
                    paperMap.put("doneCount", doneCountRs.getString("doneCount"));

                    paperList.add(paperMap);
                }
            }
        }
        jsonMap.put("paperList", paperList);

//        //记录使用日志
//        sql = "insert user_logs(user_id,view_time,type) values(" + userId + ", now(), '1')";
//        jdbcTemplate.execute(sql);
//        //日志记录完毕

        return json.toJSONString(jsonMap);

    }

}
