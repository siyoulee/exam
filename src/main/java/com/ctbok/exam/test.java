package com.ctbok.exam;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ctbok.exam.ultils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(path = "/exam/")
public class test {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String sql = "";

    @RequestMapping(path = "/test", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String test(@RequestBody JSONObject json) throws UnsupportedEncodingException {

//        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
//        String searchTimeFrom = json.get("searchTimeFrom").toString();
//        String searchTimeEnd = json.get("searchTimeEnd").toString();
//        String updateField = json.get("updateField").toString();
//        sql = "select user_id from user_list";
//        SqlRowSet todayUserId = jdbcTemplate.queryForRowSet(sql);
//        while (todayUserId.next()) {
//            String userId = todayUserId.getString("user_id");
//            sql = "select count(*) as total from exam_sequence where gmt_update between '" + searchTimeFrom +"' and '" + searchTimeEnd + "' and user_id = " + userId;
//            SqlRowSet doCount = jdbcTemplate.queryForRowSet(sql);
//            doCount.next();
//            sql = "update user_list set " + updateField + " = '" + doCount.getString("total")+ "' where user_id = " + userId;
//            jdbcTemplate.execute(sql);
//        }


//        String examId = json.getString("examId").toString();
//        sql = "select * from exam_question where exam_id = " + examId + " and status = '1' order by id asc";
//        SqlRowSet examRowSet = jdbcTemplate.queryForRowSet(sql);
//        while (examRowSet.next()) {
//            String clarify = examRowSet.getString("clarify");
//            String clarify1 = examRowSet.getString("clarify1");
//            String questionId = examRowSet.getString("id");
//            System.out.println(questionId);
//            if (!clarify.equals("")) {
//                String clarify_decode = new String(Base64.getDecoder().decode(clarify),"UTF-8");
//                clarify_decode = clarify_decode.replaceAll("'", "''");
////                clarify = clarify_decode.replace("https://ctbok.cn","");
////                clarify = URLDecoder.decode(clarify, "UTF-8");
//////                clarify = clarify.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
//////                clarify = clarify.replaceAll("\\+", "%2B");
//
////                    String clarify_encode = Base64.getEncoder().encodeToString(clarify1.getBytes(StandardCharsets.UTF_8));
//
////                System.out.println(questionId);
//                sql = "update exam_question set clarify1 = '" + clarify_decode + "' where id = " + questionId;
//                jdbcTemplate.execute(sql);
//            }
//        }

//        jsonMap.put("output", "ok");




        return json.toJSONString("");
    }

}
