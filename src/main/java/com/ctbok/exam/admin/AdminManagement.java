package com.ctbok.exam.admin;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ctbok.exam.init.MapInit;
import com.ctbok.exam.ultils.CommonLib;
import com.ctbok.exam.ultils.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import com.ctbok.exam.init.*;

import java.util.*;

@RestController
@RequestMapping(path = "/exam/admin")
public class AdminManagement {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String sql;
    private List questionList;

    private SqlRowSet rs;
    private SqlRowSet rs2;

    HashMap<String, String[]> examMap = MapInit.examMapStatic;                    //以exam_id为key，exam内容为value
    HashMap<String, String[]> paperMap = MapInit.paperMapStatic;                  //以paper_id为key，paper内容为values
    HashMap<String, String[]> questionMapByAll = MapInit.questionMapByAllStatic;            //以question_id为key，以question内容为value，但这个是分了paper去做的
    HashMap<String, ArrayList<String[]>> answerMap = MapInit.answerMapStatic;                //以question_id为key，以answer内容为value

    @RequestMapping(path = "/AddAdmin", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String AddAdmin(@RequestBody JSONObject json, @RequestHeader HttpHeaders headers) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容

        String token = headers.getFirst("token");  //获取Token
        String adminId = CommonLib.getAdminId(token);  //从token获取AdminId

        String institutionId = json.get("institutionId").toString();   //机构ID
        String mobile = json.get("mobile").toString();   //手机号码
        String username = json.get("username").toString();    //管理员名称
        String password = json.get("password").toString();    //密码
        String sex = json.get("sex").toString();    //性别
        String mail = json.get("mail").toString();    //邮件
        String type = json.get("type").toString();    //管理员类型，1是高级管理员，2是机构老师
        String status = json.get("status").toString();     //状态，0是失效，1是有效

        sql = "insert into admin(institution_id,mobile,username,password,sex,mail,type,status,gmt_create,gmt_update) " +
                "values('" + institutionId + "','" + mobile + "','" + username + "',md5('" + password + "')," +
                "'" + sex + "','" + mail + "','" + type + "','" + status + "'," +
                "now(),now())";
        jdbcTemplate.execute(sql);

        jsonMap.put("actionStatus", "1");
        jsonMap.put("msg", "管理员添加成功");

        return json.toJSONString(jsonMap);
    }

    @RequestMapping(path = "/ModifyAdmin", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String ModifyAdmin(@RequestBody JSONObject json, @RequestHeader HttpHeaders headers) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容

        String id = json.get("id").toString();   //需要修改的管理员ID
        String institutionId = json.get("institutionId").toString();   //机构ID
        String mobile = json.get("mobile").toString();        //手机号码
        String username = json.get("username").toString();    //管理员名称
        String sex = json.get("sex").toString();              //性别
        String mail = json.get("mail").toString();            //邮件
        String type = json.get("type").toString();            //管理员类型，1是高级管理员，2是机构老师
        String status = json.get("status").toString();        //状态，0是失效，1是有效

        sql = "update admin set institution_id = " + institutionId + ", mobile = '" + mobile + "'," +
                " username = '" + username + "', sex = '" + sex + "', mail = '" + mail + "'," +
                " type = '" + type + "', " + "status = '" + status + "',gmt_update = now() where id = " + id;
        jdbcTemplate.execute(sql);

        jsonMap.put("actionStatus", "1");
        jsonMap.put("msg", "管理员信息修改成功");

        return json.toJSONString(jsonMap);
    }

    @RequestMapping(path = "/ModifyAdminPassword", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String ModifyAdminPassword(@RequestBody JSONObject json, @RequestHeader HttpHeaders headers) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容

        String id = json.get("id").toString();   //需要修改的管理员ID
        String password = json.get("password").toString();   //需要修改的管理员ID

        sql = "update admin set password = md5('" + password + "') where id = " + id;
        jdbcTemplate.execute(sql);

        jsonMap.put("actionStatus", "1");
        jsonMap.put("msg", "管理员密码修改成功");

        return json.toJSONString(jsonMap);
    }

    @RequestMapping(path = "/GetAdminList", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String GetAdminList(@RequestBody JSONObject json, @RequestHeader HttpHeaders headers) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        String page = "";            //分页
        String mobile = "";          //手机号
        String name = "";            //管理员姓名
        String institutionId = "";     //机构ID
        String type = "";            //类型ID
        String status = "";          //状态ID
        int pageCount;       //每页数据条数

        int totalRecord;  //记录总数
        int totalPage;    //总页数


        //查询条件赋值
        try {
            pageCount = Integer.parseInt(json.get("pageCount").toString());       //每页数据条数，如果没有传，默认是20
        } catch (Exception e) {
            pageCount = 20;
        }   //每页数据条数

        try {
            page = json.get("page").toString();       //分页数，如果为空，就赋值为1，其余按页数赋值，不考虑负数的情况
        } catch (Exception e) {
            page = "1";
        }   //页码

        //查询条件赋值
        try {
            mobile = json.get("mobile").toString();
        } catch (Exception e) {
        }   //手机

        try {
            name = json.get("name").toString();
        } catch (Exception e) {
        }   //姓名

        try {
            institutionId = json.get("institutionId").toString();
        } catch (Exception e) {
        }   //机构ID

        try {
            type = json.get("type").toString();
        } catch (Exception e) {
        }   //管理员类型，0是管理员，1是机构老师

        try {
            status = json.get("status").toString();
        } catch (Exception e) {
            status = "1";                         //管理员状态，如果为空，就赋值为1（启用）
        }   //管理员状态

        //** 拿取下拉选项的函数
        jsonMap.put("optionList", GetOption());  //搜索下拉框
        jsonMap.put("pageCount", pageCount);   //回传分页数
        //** 拿取完成


        //** 捞出题目数据
        sql = "select * from admin where status = '" + status + "'";
        String sqlTotal = "select count(*) as total from admin where status = '" + status + "'";

        if (!mobile.equals("")) {    //如果有ID传过来，其它查询条件失效
            sql = sql + " and mobile = '" + mobile + "'";
            sqlTotal = sqlTotal + " and mobile = '" + mobile + "'";
        } else if (!name.equals("")) {
            sql = sql + " and name = '" + name + "'";
            sqlTotal = sqlTotal + " and name = '" + name + "'";
        } else if (!institutionId.equals("")) {
            sql = sql + " and institution_id = " + institutionId;
            sqlTotal = sqlTotal + " and institution_id = " + institutionId;
        } else if (!type.equals("")) {
            sql = sql + " and type = '" + type + "'";
            sqlTotal = sqlTotal + " and type = '" + type + "'";
        }

        //获取总记录数以及分页数
        SqlRowSet totalRS = jdbcTemplate.queryForRowSet(sqlTotal);
        totalRS.next();
        totalRecord = totalRS.getInt("total");       //获取记录总数
        totalPage = (totalRecord + pageCount - 1) / pageCount;  //计算分页数
        jsonMap.put("totalRecord", totalRecord);   //总记录数
        jsonMap.put("totalPage", totalPage);       //总页数
        //分页数完成

        List<Map<String, Object>> adminList = new ArrayList<Map<String, Object>>();   //题型的列表
        sql = sql + " order by id desc limit " + (Integer.parseInt(page) - 1) * pageCount + "," + pageCount;    //分页查询
        SqlRowSet questionRS = jdbcTemplate.queryForRowSet(sql);

        while (questionRS.next()) {
            Map<String, Object> adminMap = new LinkedHashMap<>();
            adminMap.put("id", questionRS.getString("id"));    //管理员ID
            adminMap.put("institution_id", questionRS.getString("institution_id"));      //机构ID
            adminMap.put("mobile", questionRS.getString("mobile"));      //手机
            adminMap.put("username", questionRS.getString("username"));  //姓名
            adminMap.put("password", questionRS.getString("password"));  //密码
            adminMap.put("sex", questionRS.getString("sex"));            //性别
            adminMap.put("mail", questionRS.getString("mail"));          //邮箱
            adminMap.put("type", questionRS.getString("type"));          //类型，0是管理员，1是机构老师
            adminMap.put("status", questionRS.getString("status"));      //状态，0是停用，1是启用
            adminList.add(adminMap);
        }

        jsonMap.put("adminList", adminList);

        return json.toJSONString(jsonMap);
    }

    public Map<String, Object> GetOption() {
        //** 拼装SearchOptions
        Map<String, Object> optionMap = new LinkedHashMap<>();  //Options的Map

        //* 取type（管理员类型）
        List<Map<String, Object>> typeList = new ArrayList<Map<String, Object>>();   //题型的列表
        Map<String, Object> typeMap = new LinkedHashMap<>();
        typeMap.put("typeId", "0");
        typeMap.put("typeName", "管理员");
        typeList.add(typeMap);
        Map<String, Object> typeMap2 = new LinkedHashMap<>();
        typeMap2.put("typeId", "1");
        typeMap2.put("typeName", "机构老师");
        typeList.add(typeMap2);
        optionMap.put("typeOption", typeList);
        //* 取type（机构类型）组装完成


        //* 取status（管理员状态）
        List<Map<String, Object>> statusList = new ArrayList<Map<String, Object>>();   //题型的列表
        Map<String, Object> statusMap = new LinkedHashMap<>();
        statusMap.put("statusId", "0");
        statusMap.put("statusName", "停用");
        statusList.add(statusMap);
        Map<String, Object> statusMap2 = new LinkedHashMap<>();
        statusMap2.put("statusId", "1");
        statusMap2.put("statusName", "启用");
        statusList.add(statusMap2);
        optionMap.put("statusOption", statusList);
        //* 取type（机构类型）组装完成


//        //* 取pageCount（每页条数）
//        List<Map<String, Object>> pageCountList = new ArrayList<Map<String, Object>>();   //题型的列表
//        Map<String, Object> pageCountMap = new LinkedHashMap<>();
//        pageCountMap.put("1","20");
//        pageCountList.add(pageCountMap);
//        Map<String, Object> pageCountMap2 = new LinkedHashMap<>();
//        pageCountMap.put("2","50");
//        pageCountList.add(pageCountMap2);
//        Map<String, Object> pageCountMap3 = new LinkedHashMap<>();
//        pageCountMap.put("3","100");
//        pageCountList.add(pageCountMap3);
//        optionMap.put("pageCountOption", pageCountMap);
//        //* 取pageCount（每页条数）组装完成


        //* institution（机构列表）
        List<Map<String, Object>> institutionList = new ArrayList<Map<String, Object>>();   //题型的列表

        sql = "select * from institution";
        SqlRowSet institutionRS = jdbcTemplate.queryForRowSet(sql);
        while (institutionRS.next()) {
            Map<String, Object> institutionMap = new LinkedHashMap<>();
            institutionMap.put("institutionId", institutionRS.getString("id"));
            institutionMap.put("institutionName", institutionRS.getString("name"));
            institutionList.add(institutionMap);
        }

        optionMap.put("institutionOption", institutionList);
        //* institution（机构列表）组装完成

        //** 拼装SearchOptions完成
        return optionMap;
    }
}
