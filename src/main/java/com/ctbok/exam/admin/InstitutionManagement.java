package com.ctbok.exam.admin;

import com.alibaba.fastjson2.JSONObject;
import com.ctbok.exam.init.MapInit;
import com.ctbok.exam.ultils.CommonLib;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/exam/admin")
public class InstitutionManagement {
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

    @RequestMapping(path = "/AddInstitution", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String AddInstitution(@RequestBody JSONObject json, @RequestHeader HttpHeaders headers) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容

        String token = headers.getFirst("token");  //获取Token
        String adminId = CommonLib.getAdminId(token);  //从token获取AdminId

        String name = json.get("name").toString();    //机构名称
        String status = json.get("status").toString();     //状态，0是失效，1是有效

        sql = "insert into institution(admin_id, name,status,gmt_create,gmt_update) " +
                "values('" + adminId + "','" + name + "','" + status + "',now(),now())";
        jdbcTemplate.execute(sql);

        jsonMap.put("actionStatus", "1");
        jsonMap.put("msg", "机构添加成功");

        return json.toJSONString(jsonMap);
    }

    @RequestMapping(path = "/ModifyInstitution", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String ModifyInstitution(@RequestBody JSONObject json, @RequestHeader HttpHeaders headers) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容

        String token = headers.getFirst("token");  //获取Token
        String adminId = CommonLib.getAdminId(token);  //从token获取AdminId

        String id = json.get("id").toString();    //机构ID
        String name = json.get("name").toString();    //机构名称
        String status = json.get("status").toString();     //状态，0是失效，1是有效

        sql = "update institution set admin_id = '" + adminId + "', name = '" + name + "', status = '" + status + "'," +
                "gmt_update = now() where id = " + id;
        jdbcTemplate.execute(sql);

        jsonMap.put("actionStatus", "1");
        jsonMap.put("msg", "机构信息修改成功");

        return json.toJSONString(jsonMap);
    }

    @RequestMapping(path = "/GetInstitutionList", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String GetInstitutionList(@RequestBody JSONObject json, @RequestHeader HttpHeaders headers) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        String name = "";            //姓名
        String status = "";          //状态
        String page = "";            //当前第几页
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

        try {
            name = json.get("name").toString();
        } catch (Exception e) {
        }   //姓名

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
        sql = "select * from institution where status = '" + status + "'";
        String sqlTotal = "select count(*) as total from institution where status = '" + status + "'";


       if (!name.equals("")) {
            sql = sql + " and name = '" + name + "'";
            sqlTotal = sqlTotal + " and name = '" + name + "'";
        }

        //获取总记录数以及分页数
        SqlRowSet totalRS = jdbcTemplate.queryForRowSet(sqlTotal);
        totalRS.next();
        totalRecord = totalRS.getInt("total");       //获取记录总数
        totalPage = (totalRecord + pageCount - 1) / pageCount;  //计算分页数
        jsonMap.put("totalRecord", totalRecord);   //总记录数
        jsonMap.put("totalPage", totalPage);       //总页数
        //分页数完成

        List<Map<String, Object>> institutionList = new ArrayList<Map<String, Object>>();   //题型的列表
        sql = sql + " order by id desc limit " + (Integer.parseInt(page) - 1) * pageCount + "," + pageCount;    //分页查询
        SqlRowSet institutionRS = jdbcTemplate.queryForRowSet(sql);

        while (institutionRS.next()) {
            Map<String, Object> adminMap = new LinkedHashMap<>();
            adminMap.put("id", institutionRS.getString("id"));    //管理员ID
            adminMap.put("name", institutionRS.getString("name"));      //机构ID
            adminMap.put("status", institutionRS.getString("status"));      //状态，0是停用，1是启用
            institutionList.add(adminMap);
        }

        jsonMap.put("institutionList", institutionList);

        return json.toJSONString(jsonMap);
    }

    public Map<String, Object> GetOption() {
        //** 拼装SearchOptions
        Map<String, Object> optionMap = new LinkedHashMap<>();  //Options的Map

        //* 取status（机构状态）
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
        //* 取status（机构状态）组装完成


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


        //** 拼装SearchOptions完成
        return optionMap;
    }
}
