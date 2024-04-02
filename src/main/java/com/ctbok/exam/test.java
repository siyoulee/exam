package com.ctbok.exam;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ctbok.exam.init.MapInit;
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

import java.io.Console;
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
        /* 处理问题内容 */
        HashMap<String, String[]> questionMapByAll = MapInit.questionMapByAllStatic;
        String id = json.get("id").toString();
        System.out.println("id" + id);

        String[] questionContent = questionMapByAll.get(id);

        return questionContent[2];
    }

}
