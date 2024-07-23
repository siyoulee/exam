package com.ctbok.exam;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson2.JSONObject;
import com.ctbok.exam.ultils.*;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.regex.*;

import java.util.*;


@RestController
@RequestMapping(path = "/exam")
public class Register {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String sql;

    @RequestMapping(path = "/Register", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String Register(@RequestBody JSONObject json) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        String mobile = json.get("mobile").toString();
        String username = json.get("username").toString();
        String password = json.get("password").toString();
        String sex = json.get("sex").toString();
        String mail = json.get("mail").toString();
        String examType = json.get("examType").toString();   //1是CDMP，9是CDGA
        String userId = "";

        String searchSql = "select * from users where mobile='" + mobile + "'";
        String insertSql = "insert into users(mobile,username,password,sex,mail,user_group_id,expired_date,status,gmt_create) values('" + mobile + "','" + username + "',MD5('" + password + "'),'" + sex + "','" + mail + "','3','2099/12/31 00:00:00','0',NOW())";
        List queryList = jdbcTemplate.queryForList(searchSql);

        if (queryList.isEmpty()) {
            jdbcTemplate.execute(insertSql);
            sql = "select * from users where mobile = '" + mobile + "'";
            SqlRowSet userRowSet = jdbcTemplate.queryForRowSet(sql);
            userRowSet.next();
            userId = userRowSet.getString("id");

            sql = "insert exam_roles(user_id,exam_id,role,expired_date) values('" + userId + "'," + examType + ",'1','2099-12-31 00:00:00')";
            jdbcTemplate.execute(sql);
            return "{\n\"registerStatus\":\"success\"}";
        } else {
            return "{\n\"registerStatus\":\"failed\",\n\"errorDsec\":\"已存在用户\"\n}";
        }

    }

    @RequestMapping(path = "/ModifyPassword", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String ModifyPassword(@RequestBody JSONObject json) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        String originPassword = json.get("originPassword").toString();
        String password = json.get("password").toString();
        String userId = json.get("userId").toString();
        List queryList;

        String searchSql = "select * from users where id='" + userId + "' and password=MD5('" + originPassword + "')";
        String updateSql = "update users set password=MD5('" + password + "') where id=" + userId;
        queryList = jdbcTemplate.queryForList(searchSql);

        if (queryList.isEmpty()) {
            jsonMap.put("registerStatus", "failed");
            jsonMap.put("errorDsec", "原始密码不符合");

            return json.toJSONString(jsonMap);
        } else {
            jdbcTemplate.execute(updateSql);
            jsonMap.put("modifyStatus", "success");
            return json.toJSONString(jsonMap);
        }

    }

    @RequestMapping(path = "/ModifyProfile", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String ModifyProfile(@RequestBody JSONObject json) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        String username = "";
        String sex = "";
        String userId = "";
        String mobile = "";
        String mail = "";

        try {
            username = json.get("username").toString();
        } catch (Exception e) {
        }

        try {
            sex = json.get("sex").toString();
        } catch (Exception e) {
        }

        try {
            userId = json.get("userId").toString();
        } catch (Exception e) {
        }

        try {
            mobile = json.get("mobile").toString();
        } catch (Exception e) {
        }

        try {
            mail = json.get("mail").toString();
        } catch (Exception e) {
        }

        String updateSql = "update users set mobile = '" + mobile + "', mail = '" + mail + "', username = '" + username + "', sex='" + sex + "', gmt_update = NOW() where id=" + userId;
        jdbcTemplate.execute(updateSql);

        jsonMap.put("modifyStatus", "success");
        jsonMap.put("msg", "更新成功");
        jsonMap.put("userId", userId);
        return json.toJSONString(jsonMap);
    }

    @RequestMapping(path = "/GetProfile", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String GetProfile(@RequestBody JSONObject json) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        String userId = json.get("userId").toString();
        String returnString = "";
        Map queryMap;
        List queryList;

        String searchSql = "select * from users where id='" + userId + "'";
        queryList = jdbcTemplate.queryForList(searchSql);
        Iterator it = queryList.iterator();
        queryMap = (Map) it.next();

        jsonMap.put("userId", queryMap.get("id").toString());
        jsonMap.put("mobile", queryMap.get("mobile").toString());
        jsonMap.put("username", queryMap.get("username").toString());
        jsonMap.put("sex", queryMap.get("sex").toString());
        jsonMap.put("mail", queryMap.get("mail").toString());
        jsonMap.put("status", queryMap.get("status").toString());

        return json.toJSONString(jsonMap);
    }

    @RequestMapping(path = "/Login", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String login(@RequestBody JSONObject json) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        //这里处理了一些一般的SQL注入问题
        String mobile = json.get("mobile").toString().replace("'", "").replace(" union ", "").replace(" and ", "").replace(" or ", "");
        String password = json.get("password").toString().replace("'", "").replace(" union ", "").replace(" and ", "").replace(" or ", "");
        String userId = "";
        String historyStatus = "";  //是否记录做题历史数据
        String userGroupId = "";  //用户分组，其实现在没什么用的了
        String examId = "";
        String token = "";   //记录token的字符串
        Iterator it;
        List queryList;

        //取Json一级的内容，不含菜单，是基本的登录信息
        sql = "select * from users where mobile='" + mobile + "' and password=MD5('" + password + "') and status = '1' limit 1";
        queryList = jdbcTemplate.queryForList(sql);

        if (queryList.isEmpty()) {
            jsonMap.put("loginStatus", "0");
            jsonMap.put("msg", "登录失败，请查检用户名密码，或帐号是否已过期");
            jsonMap.put("userId", "");
        } else {
            it = queryList.iterator();
            if (it.hasNext()) {
                Map queryMap = (Map) it.next();
                userId = queryMap.get("id").toString();
                historyStatus = queryMap.get("history_status").toString();
                userGroupId = queryMap.get("user_group_id").toString();
                String servletPath = "";

                //拿取token
                token = GetToken(userId, historyStatus, userGroupId);
            }
            jsonMap.put("loginStatus", "1");
            jsonMap.put("msg", "登录成功");
            jsonMap.put("token", token);
            jsonMap.put("userId", userId);

            //取Json二级的内容，即一级菜单
            jsonMap.put("menusLv1", GetMenus(userGroupId));

            sql = "select * from exam_roles where user_id = " + userId + " and role = '1' and  now() < expired_date";       //拿取已赋权限的examId，可能有多个
            SqlRowSet rolesRS = jdbcTemplate.queryForRowSet(sql);
            while (rolesRS.next()) {
                examId = examId + rolesRS.getString("exam_id") + ",";
            }
            if (!examId.equals("")) {
                examId = examId.substring(0, examId.length() - 1);
            }

            GenerateSequenceExamList(userId);
        }

        return json.toJSONString(jsonMap);
    }

    @RequestMapping(path = "/ExchangeCode", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String ExchangeCode(@RequestBody JSONObject json) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        String userId = json.get("userId").toString();
        String code = json.get("code").toString().replace("'", "").replace(" union ", "").replace(" and ", "").replace(" or ", "").replace(" ","");
        sql = "select count(*) as total from exchange where binary code = '" + code + "' and status = '1'";    //查看兑换码是否正确
        SqlRowSet codeRowSetTotal = jdbcTemplate.queryForRowSet(sql);
        codeRowSetTotal.next();

        if (codeRowSetTotal.getInt("total") == 1) {
            sql = "select * from exchange where binary code = '" + code + "'";
            SqlRowSet codeRowSet = jdbcTemplate.queryForRowSet(sql);      //把代码对应的examId拿出来
            codeRowSet.next();
            String examId = codeRowSet.getString("exam_id");

            String[] examIdList = examId.split(",");
            for (int i = 0; i < examIdList.length; i++) {
                sql = "insert into exam_roles(user_id,exam_id,role,expired_date) values('" + userId + "','" + examIdList[i].toString() + "','1',NOW() + INTERVAL 1 YEAR)";
                jdbcTemplate.execute(sql);
            }


            sql = "update exchange set status = '0', user_id = " + userId + ", gmt_update=now() where code = '" + code + "'";
            jdbcTemplate.execute(sql);

            //把体验课删除
            sql = "update exam_roles set role = '0' where user_id = " + userId + " and exam_id in (12,13)";    //把CDMP体验课的权限去除
            jdbcTemplate.execute(sql);
            sql = "delete from exam_sequence where user_id = " + userId + " and exam_id in (12,13)";   //删除顺序做题相关的内容
            jdbcTemplate.execute(sql);
            sql = "delete from wrong_question where user_id = " + userId + " and exam_id in (12,13)";   //删除错题内容
            jdbcTemplate.execute(sql);
            sql = "delete from favorites_question where user_id = " + userId + " and exam_id in (12,13)";   //删除收藏题目内容
            jdbcTemplate.execute(sql);

            jsonMap.put("status", "1");
            jsonMap.put("msg", "课程兑换成功");
            jsonMap.put("userId", userId);
        } else {
            jsonMap.put("status", "0");
            jsonMap.put("msg", "课程兑换失败，兑换码已使用或不存在");
            jsonMap.put("userId", userId);
        }

        GenerateSequenceExamList(userId);

        return json.toJSONString(jsonMap);
    }

    @RequestMapping(path = "/GenerateExchangeCode", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String GenerateExchangeCode(@RequestBody JSONObject json) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容

        for (int i = 0; i < 100; i++) {
            String code = GenerateExamCode(8);
            sql = "insert into exchange(exam_id,code,status) values('9','" + code + "','1')";
            jdbcTemplate.execute(sql);
        }

        jsonMap.put("msg", "课程代码生成成功");

        return json.toJSONString(jsonMap);
    }

    public String GetToken(String userId, String historyStatus, String userGroupId) {
        String servletPath = "";
        String token = "";
        JWT objToken = new JWT();   //Token生成类

        sql = "select a.id,a.servlet_path,b.user_group_id,b.role from interfaces a left join interface_roles b on a.id = b.interface_id where a.status='1' and b.user_group_id = " + userGroupId;
        SqlRowSet queryRoles = jdbcTemplate.queryForRowSet(sql);
        while (queryRoles.next()) {
            servletPath = servletPath + queryRoles.getString("servlet_path") + ",";
        }

        servletPath = servletPath.substring(0, servletPath.length() - 1);  //去除最后的"，"
        //拿取servletPath完成

        token = objToken.getJWToken(userId, historyStatus, "user", servletPath);
        return token;
    }

    public String GenerateExamCode(int len) {
        /**
         * 生成随机密码生成方式一
         * 密码字典 -> 随机获取字符
         *
         * @param len 生成密码长度（最短为4位）
         * @return 随机密码
         */
        // 如果len 小于等于3
        if (len <= 3) {
            throw new RuntimeException("密码的位数不能小于3位！");
        }
        //生成的随机数
        int i;
        //生成的密码的长度
        int count = 0;
        // 密码字典
        char[] str = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        // 大写字母密码字典
        List<Character> bigStrList = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
        Set<Character> bigStrSet = new HashSet<>(bigStrList);
        // 小写字母的密码字典
        List<Character> upperStrList = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
        Set<Character> upperStrSet = new HashSet<>(upperStrList);
        // 数字的密码字典
        List<Character> numStrList = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
        Set<Character> numStrSet = new HashSet<>(numStrList);

        StringBuilder builder = new StringBuilder();
        Random r = new Random();
        boolean isContainBigChar = false;
        boolean isContainUpperChar = false;
        boolean isContainNumChar = false;
        while (count < len - 3) {
            //生成 0 ~ 密码字典-1之间的随机数
            i = r.nextInt(str.length);
            builder.append(str[i]);
            count++;
            if (!isContainBigChar && bigStrSet.contains(str[i])) {
                isContainBigChar = true;
            }
            if (!isContainUpperChar && upperStrSet.contains(str[i])) {
                isContainUpperChar = true;
            }
            if (!isContainNumChar && numStrSet.contains(str[i])) {
                isContainNumChar = true;
            }
        }
        // 如果不存在的，则加，确保一定会存在数字，大写字母，小写字母
        if (!isContainBigChar) {
            builder.append(bigStrList.get(r.nextInt(bigStrList.size())));
        }
        if (!isContainUpperChar) {
            builder.append(upperStrList.get(r.nextInt(upperStrList.size())));
        }
        if (!isContainNumChar) {
            builder.append(numStrList.get(r.nextInt(numStrList.size())));
        }
        while (builder.length() < len) {
            builder.append(str[r.nextInt(str.length)]);
        }


        return builder.toString();
    }

    public List<Map<String, Object>> GetMenus(String userGroupId) {
//        DataBaseUtils dbUtils = new DataBaseUtils();
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dbUtils.GetDataSource().build());

        List<Map<String, Object>> menuLv2 = new ArrayList<Map<String, Object>>();   //二级Json
        sql = "select b.*,a.role from menu_roles a right join menus b on a.menu_id = b.id where a.user_group_id = " + userGroupId + " and a.role='1' and level='1'";   //拿出菜单权限
        List queryList2 = jdbcTemplate.queryForList(sql);
        Iterator it2 = queryList2.iterator();
        while (it2.hasNext()) {
            Map queryMap2 = (Map) it2.next();
            Map<String, Object> roleLv2 = new LinkedHashMap<>();  //放二级的Json内容
            roleLv2.put("menuId", queryMap2.get("id"));
            roleLv2.put("menuName", queryMap2.get("name"));
            roleLv2.put("url", queryMap2.get("url"));

            //取Json三级的内容，即二级菜单
            List<Map<String, Object>> menuLv3 = new ArrayList<Map<String, Object>>();   //三级Json
            sql = "select b.*,a.role from menu_roles a right join menus b on a.menu_id = b.id where a.user_group_id = " + userGroupId + " and a.role='1' and level='2' and parent_id = " + queryMap2.get("id");   //拿出菜单权限
            List queryList3 = jdbcTemplate.queryForList(sql);
            Iterator it3 = queryList3.iterator();
            while (it3.hasNext()) {
                Map queryMap3 = (Map) it3.next();
                Map<String, Object> roleLv3 = new LinkedHashMap<>();  //放二级的Json内容
                roleLv3.put("menuId", queryMap3.get("id"));
                roleLv3.put("menuName", queryMap3.get("name"));
                roleLv3.put("url", queryMap3.get("url"));
                menuLv3.add(roleLv3);
            }
            roleLv2.put("menusLv2", menuLv3);
            menuLv2.add(roleLv2);
        }
        return menuLv2;
    }

    public void GenerateSequenceExamList(String userId) {
        String questionId = "";
        String examId = "";
        String paperId = "";
        String questionType = "";
        String questionSubject = "";

        // 第二步，拿出究竟这个人，有多少个课程是可以用的，下面这段，都是在有课程权限的情况下的增删改
        sql = "select * from exam_roles where user_id = " + userId + " and role = '1' and now() < expired_date";
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
                    insertSql = insertSql + " ('" + userId + "','" + questionId + "','" + examId + "','" + paperId + "','" + questionType + "','" + questionSubject + "','','1'),";
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
}

