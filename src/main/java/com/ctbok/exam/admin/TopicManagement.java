package com.ctbok.exam.admin;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ctbok.exam.init.MapInit;
import org.apache.maven.surefire.shade.org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import java.util.*;

@RestController
@RequestMapping(path = "/exam/admin")
public class TopicManagement {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String sql;
    private List questionList;

    private SqlRowSet rs;
    private SqlRowSet rs2;

    HashMap<String, String[]> examMap = MapInit.examMapStatic;                    //以exam_id为key，exam内容为value
    HashMap<String, String[]> paperMap = MapInit.paperMapStatic;                  //以paper_id为key，paper内容为values
    //    HashMap<String, HashMap> paperQuestionMap = MapInit.paperQuestionMapStatic;   //以paper_id为key,以questionMap为value
//    HashMap<String, String[]> questionMapByPaper = MapInit.questionMapByPaperStatic;            //以question_id为key，以question内容为value，但这个是分了paper去做的
    HashMap<String, String[]> questionMapByAll = MapInit.questionMapByAllStatic;            //以question_id为key，以question内容为value，但这个是分了paper去做的
    HashMap<String, ArrayList<String[]>> answerMap = MapInit.answerMapStatic;                //以question_id为key，以answer内容为value


    @RequestMapping(path = "/GetTopicList", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String GetTopicList(@RequestBody JSONObject json) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        String questionName = "";
        String source = "";
        String paperId = "";
        String examId = "";
        String questionType = "";
        String questionSubject = "";
        String questionId = "";

        int totalRecord;  //记录总数
        int totalPage;    //总页数

        String adminId = json.get("adminId").toString();
        String page = json.get("page").toString();
        String status = json.get("status").toString();

        try {
            questionId = json.get("questionId").toString();
        } catch (Exception e) {
        }   //questionId

        try {
            examId = json.get("examId").toString();
        } catch (Exception e) {
        }   //examId

        try {
            questionName = json.get("questionName").toString().replace("'", "''").trim();
        } catch (Exception e) {
        }   //questionName

        try {
            source = json.get("source").toString();
        } catch (Exception e) {
        }   //source

        try {
            paperId = json.get("paperId").toString();
        } catch (Exception e) {
        }   //paperId

        try {
            questionType = json.get("questionType").toString();
        } catch (Exception e) {
        }   //questionType

        try {
            questionSubject = json.get("questionSubject").toString();
        } catch (Exception e) {
        }   //questionSubject


        //** 拿取下拉选项的函数
        jsonMap.put("optionList", GetTopicOption());   //
        //** 拿取完成


        //** 捞出题目数据
        sql = "select * from exam_question where status = '" + status + "'";
        String sqlTotal = "select count(*) as total from exam_question where status = '" + status + "'";

        if (!questionId.equals("")) {    //如果有ID传过来，其它查询条件失效
            sql = sql + " and id = " + questionId;
            sqlTotal = sqlTotal + " and id = " + questionId;
        } else {                         //如果没有ID传过来，其它
            if (!questionName.equals("")) {    //如果有题目名称
                sql = sql + " and (question_name like '%" + questionName + "%' or question_name_cn like '%" + questionName + "%')";
                sqlTotal = sqlTotal + " and (question_name like '%" + questionName + "%' or question_name_cn like '%" + questionName + "%')";
            }
            if (!questionType.equals("")) {   //如果有题型
                sql = sql + " and question_type = '" + questionType + "'";
                sqlTotal = sqlTotal + " and question_type = '" + questionType + "'";
            }
            if (!questionSubject.equals("")) {     //如果有题目类型
                sql = sql + " and question_subject = '" + questionSubject + "'";
                sqlTotal = sqlTotal + " and question_subject = '" + questionSubject + "'";
            }
            if (!examId.equals("")) {     //如果有题目类型
                sql = sql + " and exam_id = " + examId + "";
                sqlTotal = sqlTotal + " and exam_id = " + examId + "";
            }
            if (!paperId.equals("")) {     //如果有题目类型
                sql = sql + " and paper_id = " + paperId + "";
                sqlTotal = sqlTotal + " and paper_id = " + paperId + "";
            }
            if (!source.equals("")) {     //如果有题目类型
                sql = sql + " and source = '" + source + "'";
                sqlTotal = sqlTotal + " and source = '" + source + "'";
            }
        }

        //获取总记录数以及分页数
        SqlRowSet totalRS = jdbcTemplate.queryForRowSet(sqlTotal);
        totalRS.next();
        totalRecord = totalRS.getInt("total");       //获取记录总数
        totalPage = (totalRecord + 20 - 1) / 20;
        jsonMap.put("totalRecord", totalRecord);   //总记录数
        jsonMap.put("totalPage", totalPage);       //总页数
        jsonMap.put("adminId", adminId);
        //分页数完成

        List<Map<String, Object>> TopicList = new ArrayList<Map<String, Object>>();   //题型的列表
        sql = sql + " order by id desc limit " + (Integer.parseInt(page) - 1) * 20 + ",20";    //分页查询
        SqlRowSet questionRS = jdbcTemplate.queryForRowSet(sql);

        while (questionRS.next()) {
            Map<String, Object> questionMap = new LinkedHashMap<>();
            questionMap.put("id", questionRS.getString("id"));
            questionMap.put("questionNameEn", questionRS.getString("question_name"));
            questionMap.put("questionNameCn", questionRS.getString("question_name_cn"));
            questionMap.put("examId", questionRS.getString("exam_id"));
            questionMap.put("paperId", questionRS.getString("paper_id"));
            questionMap.put("questionType", questionRS.getString("question_type"));
            questionMap.put("questionSubject", questionRS.getString("question_subject"));
            questionMap.put("source", questionRS.getString("source"));
            questionMap.put("status", questionRS.getString("status"));
            TopicList.add(questionMap);
        }

        jsonMap.put("TopicList", TopicList);

        return json.toJSONString(jsonMap);
    }

    @RequestMapping(path = "/GetTopic", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String GetTopic(@RequestBody JSONObject json) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        String questionId = json.get("questionId").toString();

        //** 拿取下拉选项的函数
        jsonMap.put("optionList", GetTopicOption());
        //** 拿取完成

        //* 捞出题目内容
        sql = "select * from exam_question where id = " + questionId;
        SqlRowSet questionRs = jdbcTemplate.queryForRowSet(sql);
        questionRs.next();
        jsonMap.put("questionId", questionRs.getString("id"));
        jsonMap.put("questionNameEn", questionRs.getString("question_name"));
        jsonMap.put("questionNameCn", questionRs.getString("question_name_cn"));
        jsonMap.put("examId", questionRs.getString("exam_id"));
        jsonMap.put("paperId", questionRs.getString("paper_id"));
        jsonMap.put("questionType", questionRs.getString("question_type"));
        jsonMap.put("questionSubject", questionRs.getString("question_subject"));
        jsonMap.put("questionPoints", questionRs.getString("question_points"));
        jsonMap.put("difficulty", questionRs.getString("difficulty"));
        jsonMap.put("confidence", questionRs.getString("confidence"));
        jsonMap.put("fileId", questionRs.getString("file_id"));
        jsonMap.put("appId", questionRs.getString("app_id"));
        jsonMap.put("psign", questionRs.getString("psign"));
        jsonMap.put("wxVideoUrl", questionRs.getString("wx_video_url"));
        jsonMap.put("clarify", questionRs.getString("clarify"));
        jsonMap.put("source", questionRs.getString("source"));
        jsonMap.put("status", questionRs.getString("status"));
        //* 问题捞完

        //* 捞出答案内容
        List<Map<String, Object>> answerList = new ArrayList<Map<String, Object>>();   //题型的列表
        sql = "select * from exam_answer where question_id = " + questionId + " and status = '1'";
        SqlRowSet answerRs = jdbcTemplate.queryForRowSet(sql);
        while (answerRs.next()) {
            Map<String, Object> answerMap = new LinkedHashMap<>();
            answerMap.put("answerId", answerRs.getString("id"));
            answerMap.put("answerContentEn", answerRs.getString("answer_content"));
            answerMap.put("answerContentCn", answerRs.getString("answer_content_cn"));
            answerMap.put("correctAnswerFlag", answerRs.getString("correct_answer"));
            answerList.add(answerMap);
        }
        //* 答案捞完

        jsonMap.put("answerList", answerList);

        return json.toJSONString(jsonMap);
    }

    @RequestMapping(path = "/UpdateTopic", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String UpdateTopic(@RequestBody JSONObject json) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        Map queryMap;
        List queryList;
        Iterator it;

        String clarify = "";
        String questionNameCn = "";
        String appId = "";
        String fileId = "";
        String psign = "";
        String wxVideoUrl = "";

        String questionId = json.get("questionId").toString();
        String adminId = json.get("adminId").toString();
        String paperId = json.get("paperId").toString();
        String examId = json.get("examId").toString();
        String questionNameEn = json.get("questionNameEn").toString().replace("'", "''");
        try {
            questionNameCn = json.get("questionNameCn").toString().replace("'", "''");
        } catch (Exception e) {
        }

        try {
            appId = json.get("appId").toString();
            fileId = json.get("fileId").toString();
            psign = json.get("psign").toString();
            wxVideoUrl = json.get("wxVideoUrl").toString();
        } catch (Exception e) {
        }

        try {
            clarify = json.get("clarify").toString().replace("'", "''");
        } catch (Exception e) {
        }


        String questionPoints = json.get("questionPoints").toString();
        String questionSubject = json.get("questionSubject").toString();
        String questionType = json.get("questionType").toString();
        String difficulty = json.get("difficulty").toString();
        String confidence = json.get("confidence").toString();
        String source = json.get("source").toString();
        String status = json.get("status").toString();

//        SimpleDateFormat dateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sql = "update exam_question set " +
                "exam_id = " + examId +
                ",paper_id = " + paperId +
                ",question_type = '" + questionType + "'" +
                ",question_subject = '" + questionSubject + "'" +
                ",question_name = '" + questionNameEn + "'" +
                ",question_name_cn = '" + questionNameCn + "'" +
                ",difficulty = '" + difficulty + "'" +
                ",confidence = '" + confidence + "'" +
                ",question_points = '" + questionPoints + "'" +
                ",file_id = '" + fileId + "'" +
                ",app_id = '" + appId + "'" +
                ",psign = '" + psign + "'" +
                ",wx_video_url = '" + wxVideoUrl + "'" +
                ",clarify = '" + clarify + "'" +
                ",source = '" + source + "'" +
                ",status = '" + status + "'" +
                " where id = " + questionId;
        jdbcTemplate.execute(sql);

        //* 处理答案内容
        if (!questionType.equals("3")) {     //不是问答题，才有答案，问答题是只有问题的
            JSONArray answerList = json.getJSONArray("answerList");
            String answerContentEn = "";    //答案英文名
            String answerContentCn = "";    //答案中文名
            String correctAnswer = "";    //正确答案标识
            String answerId = "";    //答案ID

            //处理答案的拆解
            for (int j = 0; j < answerList.size(); j++) {
                Map mapAnswer = (Map) answerList.get(j);
                answerId = mapAnswer.get("answerId").toString();
                answerContentEn = mapAnswer.get("answerContentEn").toString().replace("'", "''");
                answerContentCn = mapAnswer.get("answerContentCn").toString().replace("'", "''");
                correctAnswer = mapAnswer.get("correctAnswerFlag").toString();
                sql = "update exam_answer set answer_content = '" + answerContentEn + "', answer_content_cn = '" + answerContentCn + "', " +
                        "correct_answer = '" + correctAnswer + "' where id = " + answerId;
                jdbcTemplate.execute(sql);
            }
        }

        jsonMap.put("adminId", adminId);
        jsonMap.put("status", "1");
        jsonMap.put("msg", "题目修改成功");

        return json.toJSONString(jsonMap);
    }

    @RequestMapping(path = "/AddTopic", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String AddTopic(@RequestBody JSONObject json) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        Map queryMap;
        List queryList;
        Iterator it;

        String clarify = "";
        String questionNameCn = "";
        String appId = "";
        String fileId = "";
        String psign = "";
        String wxVideoUrl = "";
        String difficulty = "";
        String confidence = "";

        String adminId = json.get("adminId").toString();
        String paperId = json.get("paperId").toString();
        String examId = json.get("examId").toString();
        String questionNameEn = json.get("questionNameEn").toString().replace("'", "''");
        try {
            questionNameCn = json.get("questionNameCn").toString().replace("'", "''");
        } catch (Exception e) {
        }

        try {
            appId = json.get("appId").toString();
            fileId = json.get("fileId").toString();
            psign = json.get("psign").toString();
            wxVideoUrl = json.get("wxVideoUrl").toString();
        } catch (Exception e) {
        }

        try {
            clarify = json.get("clarify").toString().replace("'", "''");
        } catch (Exception e) {
        }

        try {
            difficulty = json.get("difficulty").toString();
            confidence = json.get("confidence").toString();
        } catch (Exception e) {
        }

        String questionPoints = json.get("questionPoints").toString();
        String questionSubject = json.get("questionSubject").toString();
        String questionType = json.get("questionType").toString();

        String source = json.get("source").toString();
        String status = json.get("status").toString();

        sql = "insert into exam_question(exam_id,paper_id,question_type,question_subject,question_name,question_name_cn,difficulty,confidence," +
                "question_points,file_id,app_id,psign,wx_video_url,clarify,source,status) " +
                "values('" + examId + "','" + paperId + "','" + questionType + "','" + questionSubject + "'," +
                "'" + questionNameEn + "','" + questionNameCn + "','" + difficulty + "','" + confidence + "'," +
                "'" + questionPoints + "','" + fileId + "','" + appId + "','" + psign + "','" + wxVideoUrl + "'," +
                "'" + clarify + "','" + source + "','" + status + "')";
        jdbcTemplate.execute(sql);


        if (!questionType.equals("3")) {     //不是问答题，才有答案，问答题是只有问题的
            queryList = jdbcTemplate.queryForList("select * from exam_question order by id desc limit 1");   //拿取刚插入的ID
            it = queryList.iterator();
            queryMap = (Map) it.next();
            String insertId = queryMap.get("id").toString();   //刚刚插入的问题ID
            JSONArray answerList = json.getJSONArray("answerList");    //把答案清单，转化为数组
            String answerContentEn = "";    //答案英文名
            String answerContentCn = "";    //答案中文名
            String correctAnswer = "";    //正确答案标识
            //处理答案的拆解
            for (int j = 0; j < answerList.size(); j++) {
                Map mapAnswer = (Map) answerList.get(j);
                answerContentEn = mapAnswer.get("answerContentEn").toString().replace("'", "''");
                answerContentCn = mapAnswer.get("answerContentCn").toString().replace("'", "''");
                correctAnswer = mapAnswer.get("correctAnswerFlag").toString();

                sql = "insert into exam_answer(question_id,answer_content,answer_content_cn,correct_answer,status) " +
                        "values('" + insertId + "','" + answerContentEn + "','" + answerContentCn + "','" + correctAnswer + "','1')";
                jdbcTemplate.execute(sql);
            }
        }

        jsonMap.put("adminId", adminId);
        jsonMap.put("status", "1");
        jsonMap.put("msg", "题目添加成功");



        return json.toJSONString(jsonMap);
    }

    @RequestMapping(path = "/TopicStatusModify", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String TopicStatusModify(@RequestBody JSONObject json) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        String adminId = json.get("adminId").toString();
        String questionId = json.get("questionId").toString();
        String status = json.get("status").toString();

        sql = "update exam_question set status='" + status + "' where id = " + questionId;
        jdbcTemplate.execute(sql);

        jsonMap.put("status", "1");
        jsonMap.put("msg", "修改成功");

        return json.toJSONString(jsonMap);
    }

    @RequestMapping(path = "/GetExchangeCode", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String GetExchangeCode(@RequestBody JSONObject json) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        String adminId = json.get("adminId").toString();
        String types = json.get("types").toString();
        String exchangeCode = "";
        String examId = json.get("examId").toString();

        sql = "select * from exchange where exam_id = " + examId + " and given = '' and status = '1' order by id asc";
        SqlRowSet codeRs = jdbcTemplate.queryForRowSet(sql);
        codeRs.next();
        exchangeCode = codeRs.getString("code");

        sql = "update exchange set given = '1' where code = '" + exchangeCode + "'";
        jdbcTemplate.execute(sql);

        jsonMap.put("status", "1");
        jsonMap.put("msg", "获取成功");

        if (types.equals("1")) {    //如果只是拿码
            jsonMap.put("code", exchangeCode);
        } else {                    //如果是拿一段话
            if (examId.equals("1")) {    //
                jsonMap.put("code", "链接: https://pan.baidu.com/s/1ODOPIA5-3DHshbt-lS4o1g 提取码: ejwh \n" +
                        "书本，参考书，各种解决方案，行业资料，请在这里下载\n" +
                        "\n" +
                        "链接: https://pan.baidu.com/s/1Go25Jt6gOnr0v9Q6r5hXMA 提取码: p3xh \n" +
                        "培训视频请到这下载\n" +
                        "\n" +
                        "题库注册：\n" +
                        "小程序与PC端联动，只兑换一次即可，课程兑换码：" + exchangeCode + "\n" +
                        "\n" +
                        "PC端：请到https://ctbok.cn网站，扫码注册帐户，注册完后，在首页中的“兑换课程”，输入兑换码兑换课程；\n" +
                        "小程序端：请扫描下面二维码，注册帐户，注册完成后，在“个人中心“，右上角“注册课程”，输入兑换码兑换课程；\n" +
                        "PC端的题库刷前三套就够了哈，后面三套是旧的题目；\n" +
                        "小程序只有前三套的真题，直接刷即可。");

            } else if (examId.equals("9")) {
                jsonMap.put("code", "链接: https://pan.baidu.com/s/1ODOPIA5-3DHshbt-lS4o1g 提取码: ejwh \n" +
                        "书本，参考书，各种解决方案，行业资料，请在这里下载\n" +
                        "\n" +
                        "链接: https://pan.baidu.com/s/1AKXWQJycyk2_Gpbhlwslog 提取码: ft6q \n" +
                        "培训视频请到这下载\n" +
                        "\n" +
                        "题库注册：\n" +
                        "小程序与PC端联动，只兑换一次即可，课程兑换码：" + exchangeCode + "\n" +
                        "\n" +
                        "PC端：请到https://ctbok.cn网站，扫码注册帐户，注册完后，在首页中的“兑换课程”，输入兑换码兑换课程；\n" +
                        "小程序端：请扫描下面二维码，注册帐户，注册完成后，在“个人中心“，右上角“注册课程”，输入兑换码兑换课程；" +
                        "PC端的题库刷前四套就够了哈，后面四套是旧的题目；\n" +
                        "小程序默认只有前四套的题，直接刷即可，如果有需要另外四套模拟题库刷题，请与老师联系");
            }
        }


        return json.toJSONString(jsonMap);
    }

    public Map<String, Object> GetTopicOption() {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        //** 拼装SearchOptions
        Map<String, Object> optionMap = new LinkedHashMap<>();  //Options的Map

        //* 取课程与考点
        List<Map<String, Object>> examList = new ArrayList<Map<String, Object>>();   //课程的列表
        sql = "select * from exams where status = '1'";
        SqlRowSet examRS = jdbcTemplate.queryForRowSet(sql);

        while (examRS.next()) {
            List<Map<String, Object>> paperList = new ArrayList<Map<String, Object>>();   //课程的列表
            Map<String, Object> contentMap = new LinkedHashMap<>();
            contentMap.put("examId", examRS.getString("id"));
            contentMap.put("examName", examRS.getString("name"));

            sql = "select * from exam_paper where status = '1' and exam_id = " + examRS.getString("id");
            SqlRowSet paperRS = jdbcTemplate.queryForRowSet(sql);
            while (paperRS.next()) {
                Map<String, Object> paperMap = new LinkedHashMap<>();
                paperMap.put("paperId", paperRS.getString("id"));
                paperMap.put("paperName", paperRS.getString("paper_name"));
                paperList.add(paperMap);
            }
            contentMap.put("paperList", paperList);
            examList.add(contentMap);
        }
        optionMap.put("examList", examList);
        //* 取课程与考点组装完成

        //* 取questionType（题型）
        List<Map<String, Object>> questionTypeList = new ArrayList<Map<String, Object>>();   //题型的列表
        Map<String, Object> questionTypeMap1 = new LinkedHashMap<>();
        questionTypeMap1.put("typeId", "1");
        questionTypeMap1.put("typeName", "单选");
        questionTypeList.add(questionTypeMap1);
        Map<String, Object> questionTypeMap2 = new LinkedHashMap<>();
        questionTypeMap2.put("typeId", "2");
        questionTypeMap2.put("typeName", "多选");
        questionTypeList.add(questionTypeMap2);
        Map<String, Object> questionTypeMap3 = new LinkedHashMap<>();
        questionTypeMap3.put("typeId", "3");
        questionTypeMap3.put("typeName", "问答");
        questionTypeList.add(questionTypeMap3);
        optionMap.put("questionType", questionTypeList);
        //* 取questionType（题型）组装完成

        //* 取questionSubject（题目类型）
        List<Map<String, Object>> questionSubjectList = new ArrayList<Map<String, Object>>();   //题型的列表
        Map<String, Object> questionSubjectMap1 = new LinkedHashMap<>();
        questionSubjectMap1.put("subjectId", "1");
        questionSubjectMap1.put("subjectName", "练习题");
        questionSubjectList.add(questionSubjectMap1);
        Map<String, Object> questionSubjectMap2 = new LinkedHashMap<>();
        questionSubjectMap2.put("subjectId", "2");
        questionSubjectMap2.put("subjectName", "模拟题");
        questionSubjectList.add(questionSubjectMap2);
        optionMap.put("questionSubject", questionSubjectList);
        //* 取questionSubject（题目类型）组装完成

        //* 取status（状态）
        List<Map<String, Object>> statusList = new ArrayList<Map<String, Object>>();   //题型的列表
        Map<String, Object> statusMap1 = new LinkedHashMap<>();
        statusMap1.put("statusId", "1");
        statusMap1.put("statusName", "有效");
        statusList.add(statusMap1);
        Map<String, Object> statusMap2 = new LinkedHashMap<>();
        statusMap2.put("statusId", "0");
        statusMap2.put("statusName", "无效");
        statusList.add(statusMap2);
        optionMap.put("status", statusList);
        //* status（状态）组装完成

        //* 取source（题目来源）
        List<Map<String, Object>> sourceList = new ArrayList<Map<String, Object>>();   //题型的列表
        Map<String, Object> sourceMap1 = new LinkedHashMap<>();
        sourceMap1.put("source", "EXAM");
        sourceList.add(sourceMap1);
        Map<String, Object> sourceMap2 = new LinkedHashMap<>();
        sourceMap2.put("source", "OS");
        sourceList.add(sourceMap2);
        optionMap.put("source", sourceList);
        //* source（题目来源）组装完成

        //** 拼装SearchOptions完成
        return optionMap;
    }
}
