package com.ctbok.exam;

import org.jetbrains.annotations.NotNull;
import org.springframework.cglib.core.Converter;
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
public class Videos {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String sql;
    private Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容

    @RequestMapping(path = "/VideoList", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String VideoList(@RequestBody @NotNull JSONObject json) {
        String userId = json.get("userId").toString();
        String examId = "";
        try {
            examId = json.get("examId").toString();
        } catch (Exception e) {}

        Iterator it;
        List queryList;

        jsonMap.put("userId", userId);   //先放用户ID进去
        jsonMap.put("msg", "");   //信息，暂时无用


        List<Map<String, Object>> examMapList = new ArrayList<Map<String, Object>>();   //分类的Map
        sql = "select a.*,b.user_id,b.role from exams a left join exam_roles b on a.id = b.exam_id " +
                "where b.user_id = " + userId + " and b.role = 1 and a.status = '1'";   //拿出分类
        queryList = jdbcTemplate.queryForList(sql);
        it = queryList.iterator();
        while (it.hasNext()) {
            Map queryMap = (Map) it.next();
            Map<String, Object> examMap = new LinkedHashMap<>();   //分类的Map
            examMap.put("examId", queryMap.get("id"));
            examMap.put("examName", queryMap.get("name"));
            examMap.put("selectedExamId", examId);
            examMap.put("msg", "");
            examMapList.add(examMap);
        }
        jsonMap.put("examList", examMapList);


        List<Map<String, Object>> videoMapList = new ArrayList<Map<String, Object>>();   //视频的Map
        Map<String, Object> videoListMap = new LinkedHashMap<>();   //视频清单的Map

        if (examId.equals("")) {      //如果为空，取全部的数据，如果不为零的正整数，则直接拿ID
            sql = "select a.* from videos a left join exam_roles b on a.exam_id = b.exam_id where b.role = '1' and a.status = '1' and b.user_id = " + userId;
        }
        else {
            sql = "select a.* from videos a left join exam_roles b on a.exam_id = b.exam_id" +
                    " where b.role = '1' and a.status = '1' and a.exam_id = " + examId + " and b.user_id = " + userId;
        }

        queryList = jdbcTemplate.queryForList(sql);
        it = queryList.iterator();
        while (it.hasNext()) {
            Map queryMap = (Map) it.next();
            Map<String, Object> videoMap = new LinkedHashMap<>();   //分类的Map
            videoMap.put("videoId", queryMap.get("id"));
            videoMap.put("examId", queryMap.get("exam_id"));
            videoMap.put("videoName", queryMap.get("video_name"));
            videoMap.put("videoDesc", queryMap.get("video_desc"));
            videoMap.put("teacherName", queryMap.get("teacher_name"));
            videoMap.put("videoPhoto", queryMap.get("video_photo"));
            videoMap.put("publishDate", queryMap.get("publish_date"));
            videoMap.put("videoLength", queryMap.get("video_length"));
            videoMap.put("fileId", queryMap.get("file_id"));
            videoMap.put("appId", queryMap.get("app_id"));
            videoMap.put("psign", queryMap.get("psign"));
            videoMap.put("wxVideoUrl", queryMap.get("wxVideoUrl"));
            videoMapList.add(videoMap);
        }
        jsonMap.put("videoList", videoMapList);

        return json.toJSONString(jsonMap);
    }
}


