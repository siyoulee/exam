package com.ctbok.exam.wx;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
//import com.alibaba.fastjson.parser.Feature;
import com.ctbok.exam.ultils.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(path = "/exam/wx")
public class Practice {
    @Autowired
    JdbcTemplate jdbcTemplate;
    private String sql;
    private Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容

    @RequestMapping(path = "/WxSearchTopic", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String WxSearchTopic(@RequestBody JSONObject json) {
//        DataBaseUtils dbUtils = new DataBaseUtils();
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dbUtils.GetDataSource().build());
        String userId = json.get("userId").toString();
        String keyWord = json.get("keyWord").toString();
        String examId = json.get("examId").toString();    //课程类型，如CDMP-A级、CDMP-PM级、CDGA、CDGP等
        jsonMap.put("userId", userId);

        List<Map<String, Object>> queryList = new ArrayList<Map<String, Object>>();  //放问题与答案

        sql = "select * from exam_question where question_name like '%" + keyWord + "%' and status='1' and exam_id = " + examId;
        SqlRowSet queryQuestion = jdbcTemplate.queryForRowSet(sql);
        while (queryQuestion.next()) {
            sql = "select * from exam_answer where question_id = " + queryQuestion.getString("id") + " and status='1' and correct_answer = '1'";
            SqlRowSet queryAnswer = jdbcTemplate.queryForRowSet(sql);
            queryAnswer.next();
            Map<String, Object> queryMap = new LinkedHashMap<>();
            queryMap.put("id", queryQuestion.getString("id"));
            queryMap.put("questionNameEn", queryQuestion.getString("question_name"));
            queryMap.put("questionNameCn", queryQuestion.getString("question_name_cn"));
            queryMap.put("answerContentEn", queryAnswer.getString("answer_Content"));
            queryMap.put("answerContentCn", queryAnswer.getString("answer_Content_cn"));
            queryList.add(queryMap);
        }

        jsonMap.put("searchList", queryList);
        return json.toJSONString(jsonMap);
    }

    @RequestMapping(path = "/WxGetExamDetails", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String WxGetExamDetails(@RequestBody JSONObject json) {
//        DataBaseUtils dbUtils = new DataBaseUtils();
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dbUtils.GetDataSource().build());
        String userId = json.get("userId").toString();
        String examId = json.get("examId").toString();
        jsonMap.put("userId", userId);
        String a = "0";
        Math.ceil(Integer.parseInt(a));

        List<Map<String, Object>> queryList = new ArrayList<Map<String, Object>>();  //课程内容

        String examName, sequenceDo, sequenceTotal, wrongTotal, favTotal;

        Map<String, Object> queryMap = new LinkedHashMap<>();

//        //更新错误题目的结果，到真正的状态中
//        sql = "update exam_sequence set status = temp_status where exam_id = " + examId + " and user_id = " + userId + " and temp_status in ('0','1')";   //将temp_status的状态，赋值回status，因为有些错题，可能已经做正确了，第一次进入时要初始化
//        jdbcTemplate.execute(sql);

        //已做的题目
        sql = "select count(*) as sequenceDo from exam_sequence where user_id = " + userId + " and status != '3' and exam_id = " + examId;
        SqlRowSet queryDo = jdbcTemplate.queryForRowSet(sql);
        queryDo.next();
        sequenceDo = queryDo.getString("sequenceDo");

        //全部的题目
        sql = "select count(*) as sequenceTotal from exam_sequence where user_id = " + userId + " and exam_id = " + examId;
        SqlRowSet queryTotal = jdbcTemplate.queryForRowSet(sql);
        queryTotal.next();
        sequenceTotal = queryTotal.getString("sequenceTotal");

        //错题数目
        sql = "select count(*) as wrongTotal from wrong_question where user_id = " + userId + " and exam_id = " + examId;
        SqlRowSet queryWrong = jdbcTemplate.queryForRowSet(sql);
        queryWrong.next();
        wrongTotal = queryWrong.getString("wrongTotal");

        //收藏数目
        sql = "select count(*) as favTotal from favorites_question where user_id = " + userId + " and exam_id = " + examId;
        SqlRowSet queryFav = jdbcTemplate.queryForRowSet(sql);
        queryFav.next();
        favTotal = queryFav.getString("favTotal");

        queryMap.put("examId", examId);
        queryMap.put("sequenceDo", sequenceDo);
        queryMap.put("sequenceTotal", sequenceTotal);
        queryMap.put("wrongTotal", wrongTotal);
        queryMap.put("favTotal", favTotal);
        queryMap.put("getPonts", sequenceDo);
        if (sequenceDo.equals("0")) {    //如果完全未做，那分数预估就是/
            queryMap.put("estimatedScore", "/");

        } else if (wrongTotal.equals("0")) {
            queryMap.put("estimatedScore", "100");

        } else {    //有做的，就不是0，就可以除
            int estimatedScore = (int) Math.ceil(Integer.parseInt(wrongTotal) / Integer.parseInt(sequenceDo));
            queryMap.put("estimatedScore", estimatedScore);
        }

        queryList.add(queryMap);

        jsonMap.put("examList", queryList);

        GenerateSequenceExamListWx(userId);

//        //记录使用日志
//        sql = "insert user_logs(user_id,view_time,type) values(" + userId + ", now(), '2')";
//        jdbcTemplate.execute(sql);
//        //日志记录完毕

        return json.toJSONString(jsonMap);
    }

    @RequestMapping(path = "/WxLogin", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String WxLogin(@RequestBody JSONObject json) {
//        DataBaseUtils dbUtils = new DataBaseUtils();
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dbUtils.GetDataSource().build());
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        String code = json.get("code").toString();   //微信的code
        String types = json.get("types").toString();   //登录或注册类型，1是小程序，2是网页扫码登录
        String openId = "";
        String sessionKey = "";
        String unionId = "";
        String userId = "";
        String token = "";
        String userGroupId = "";
        String appId = "";
        String secretKey = "";
        String errcode = "";
        String examId = "";
        String requestUrl = "";
        String historyStatus = "";  //是否记录做题历史数据

        if (types.equals("1")) {          //根据不同的类型，拿不同的标识
            appId = "wxe7d89e5ec819c176";
            secretKey = "b4ee0f16696b7b94959503346fda9553";
            requestUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + secretKey +
                    "&js_code=" + code + "&grant_type=authorization_code";
        } else {
            appId = "wx79a0478372a51c08";
            secretKey = "a27144458a55e1d8a501d96ac9872116";
            requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret=" + secretKey +
                    "&code=" + code + "&grant_type=authorization_code";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");//设置时间格式
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);  //设置起时间
        cal.add(Calendar.YEAR, 1);//增加一年
        String timeNow = sdf.format(cal.getTime());

        String jsonData = restTemplate.getForObject(requestUrl, String.class);
        JSONObject jsonObject = JSONObject.parseObject(jsonData);
        try {
            errcode = jsonObject.getString("errcode").toString();
        } catch (Exception e) {
        }

        if (errcode.equals("")) {    //如果腾讯返回的信息中没有报错
            openId = jsonObject.getString("openid");
            sessionKey = jsonObject.getString("session_key");
            unionId = jsonObject.getString("unionid");


            sql = "select count(*) as total from users where union_id = '" + unionId + "'";    //判断是新用户还是老用户
            SqlRowSet userRS = jdbcTemplate.queryForRowSet(sql);
            userRS.next();
            if (userRS.getString("total").equals("0")) {     //如果是新用户
                sql = "insert users(open_id,union_id,user_group_id,expired_date,status) values('" + openId + "','" + unionId + "','3','" + timeNow + "','1')";
                jdbcTemplate.execute(sql);

                sql = "select * from users where union_id = '" + unionId + "'";
                SqlRowSet userRS1 = jdbcTemplate.queryForRowSet(sql);
                userRS1.next();
                userId = userRS1.getString("id");
                userGroupId = userRS1.getString("user_group_id");
                historyStatus = userRS1.getString("history_status");

                token = GetTokenWx(userId, historyStatus, userGroupId);

                jsonMap.put("status", "1");
                jsonMap.put("msg", "注册成功");
                jsonMap.put("userId", userId);
                jsonMap.put("token", token);

                sql = "insert exam_roles(user_id,exam_id,role,expired_date) values('" + userId + "','12','1','2099-12-31 00:00:00')";   //注册的时候，给予体验帐号
                jdbcTemplate.execute(sql);
                sql = "insert exam_roles(user_id,exam_id,role,expired_date) values('" + userId + "','13','1','2099-12-31 00:00:00')";   //注册的时候，给予体验帐号
                jdbcTemplate.execute(sql);

            } else {   //老用户走这里
                sql = "select * from users where union_id = '" + unionId + "'";
                SqlRowSet userRS1 = jdbcTemplate.queryForRowSet(sql);
                userRS1.next();
                userId = userRS1.getString("id");
                userGroupId = userRS1.getString("user_group_id");
                historyStatus = userRS1.getString("history_status");

                token = GetTokenWx(userId, historyStatus, userGroupId);

                jsonMap.put("status", "2");
                jsonMap.put("msg", "登录成功");
                jsonMap.put("userId", userId);
                jsonMap.put("token", token);
            }
        } else if (errcode.equals("40163")) {     //如果报错代码为40163，即code已被使用
            jsonMap.put("status", "0");
            jsonMap.put("msg", "code已被使用");
        }

        GenerateSequenceExamListWx(userId);  //生成顺序做题列表
        jsonMap.put("menusLv1", GetMenus(userGroupId));

        return json.toJSONString(jsonMap);
    }

    public String GetTokenWx(String userId, String historyStatus, String userGroupId) {
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

    public void GenerateSequenceExamListWx(String userId) {
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

}
