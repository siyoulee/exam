package com.ctbok.exam;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ctbok.exam.init.*;
import com.ctbok.exam.ultils.CommonLib;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.codec.binary.Base64;

@RestController
@RequestMapping(path = "/exam")
public class ExamPractise {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String sql;
    private List questionList;

    @RequestMapping(path = "/GetExamPage", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String GetExamPage(@RequestBody JSONObject json, @RequestHeader HttpHeaders headers) {
        /* 获取相关的Map全局缓存数据 */
        HashMap<String, String[]> examMap = MapInit.examMapStatic;
        HashMap<String, String[]> paperMap = MapInit.paperMapStatic;

        /* 其它参数初始化 */
        String paperId = "";
        String questionSubject = "";
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        String examId = json.get("examId").toString();  //放试卷ID
        String userId = json.get("userId").toString();       //放用户ID
        String practiseType = json.get("practiseType").toString();
        try {
            paperId = json.get("paperId").toString();
        } catch (Exception e) {
        }

        try {
            questionSubject = json.get("questionSubject").toString();
        } catch (Exception e) {
        }

        /* 试卷信息获取 */
        String[] paperValues = paperMap.get(paperId);
        String paperName = paperValues[1];   //试卷名称：paper_name
        String paperDesc = paperValues[2];   //试卷描述：paper_desc
        String paperPoint = paperValues[3];  //试卷分数：paper_points
        String paperTime = paperValues[4];   //考试时长：paper_time


        /* 拿取课程的语言 */
        String examLanguage = examMap.get(examId)[2];
        jsonMap.put("examLanguage", examLanguage);


        /* 把收藏的题目的ID，放进数组，否则后面很多遍历，怕那台烂云服务器顶不住 */
        String sqlFavorites = "select question_id from favorites_question where user_id='" + userId + "'";
        List favoritesList = jdbcTemplate.queryForList(sqlFavorites);
        int[] arrFavorites = new int[favoritesList.size()];  //定义一个数组放，会省一点资源
        Iterator itFavorites = favoritesList.iterator();
        if (itFavorites.hasNext()) {
            int k = 0;
            while (itFavorites.hasNext()) {
                Map favoritesMap = (Map) itFavorites.next();
                arrFavorites[k] = Integer.valueOf(favoritesMap.get("question_id").toString());
                k++;
            }
        }


        /* 这里是组装需要拿出题目清单的SQL语句，这些SQL语句的组装，很难优化了，先用这样吧，反正question也不多，不像sequence_exam */
        if (practiseType.equals("4")) {            //这里拿的是模拟考试题
            paperName = "模拟考试";
            paperDesc = "模拟考试";
            paperPoint = "100";
            paperTime = "110";

            jsonMap.put("paperId", paperId);
            jsonMap.put("userId", userId);
            jsonMap.put("paperName", paperName);
            jsonMap.put("paperDesc", paperDesc);
            jsonMap.put("paperPoint", paperPoint);
            jsonMap.put("paperTime", paperTime);

            sql = "select a.* from exam_question a right join " +
                    "(select id from exam_question where exam_id = " + examId + " and status = '1' " +
                    "and question_subject = '" + questionSubject + "' order by rand() limit 100) " +
                    " as b on a.id = b.id order by a.question_type asc";
        }

        if (practiseType.equals("0")) {         //这里是按练习试卷拿题
            jsonMap.put("paperId", paperId);
            jsonMap.put("userId", userId);
            jsonMap.put("paperName", paperName);
            jsonMap.put("paperDesc", paperDesc);
            jsonMap.put("paperPoint", paperPoint);
            jsonMap.put("paperTime", paperTime);

            sql = "select a.* from exam_question a right join (" +
                    "select id from exam_question where status='1' and paper_id = " + paperId + " and question_subject = '" + questionSubject + "'" +
                    " order by rand() limit 100) as b on a.id = b.id order by a.question_type asc";  //这里拿的是100题的随机题目
        }

        if (!practiseType.equals("0") && !practiseType.equals("4")) {    //捞出错题及收藏的题目
            if (!paperId.equals("0") && practiseType.equals("1")) {    //按试卷捞错题
                sql = "select a.user_id,a.question_id,b.* from wrong_question a " +
                        "left join exam_question b on a.question_id = b.id " +
                        "where a.user_id='" + userId + "' and b.status='1' and b.paper_id='" + paperId +
                        "' and b.question_subject = '" + questionSubject + "'  order by b.question_type";

                //拿取考点名称
                jsonMap.put("paperName", paperName);
                jsonMap.put("paperId", paperId);
                jsonMap.put("userId", userId);
                jsonMap.put("paperName", paperName + " 错题复习");
                jsonMap.put("paperDesc", paperName + " 错题复习");
                jsonMap.put("paperPoint", "0");
                jsonMap.put("paperTime", "0");
            }

            if (!paperId.equals("0") && practiseType.equals("2")) {    //按试卷捞收藏题目
                sql = "select a.user_id,a.question_id,b.* from favorites_question a " +
                        "left join exam_question b on a.question_id = b.id " +
                        "where a.user_id='" + userId + "' and b.paper_id='" + paperId + "' and b.status='1'" +
                        " and b.question_subject = '" + questionSubject + "' order by b.question_type";


                //拿取考点名称
                jsonMap.put("paperName", paperName);
                jsonMap.put("paperId", paperId);
                jsonMap.put("userId", userId);
                jsonMap.put("paperName", paperName + " 收藏复习");
                jsonMap.put("paperDesc", paperName + " 收藏复习");
                jsonMap.put("paperPoint", "0");
                jsonMap.put("paperTime", "0");
            }
        }

        jsonMap.putAll(GetTopicList(arrFavorites, sql, "Get"));   //拿取问题清单，1是

        return json.toJSONString(jsonMap);
    }

    @RequestMapping(path = "/PostExamPage", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String PostExamPage(@RequestBody JSONObject json, @RequestHeader HttpHeaders headers) {
        /* 获取相关的Map全局缓存数据 */
        HashMap<String, String[]> examMap = MapInit.examMapStatic;   //课程信息的全局缓存
        HashMap<String, String[]> questionMapByAll = MapInit.questionMapByAllStatic;
        LinkedHashMap<String, ArrayList<String[]>> answerMap = MapInit.answerMapStatic;


        //其它的参数初始化
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        String paperId = json.get("paperId").toString();
        String userId = json.get("userId").toString();
        String examId = json.get("examId").toString();
        String paperTime = json.get("paperTime").toString();
        String useTime = json.get("useTime").toString();
        String totalQuestions = json.get("totalQuestions").toString();
        String paperName = json.get("paperName").toString();   //试卷名称
        String paperDesc = json.get("paperDesc").toString();   //试卷描述
        String paperPoint = json.get("paperPoint").toString();  //试卷分数
        String practiseType = json.get("practiseType").toString();  //练习类型，包括是否模拟考
//        String allQuestionId = "";   //全部ID的列表，用来更新exam_sequence
        String questionId = "";
        String answerIdList = "";
        String answerSequence = "";
        String questionType = "";
        String correctStatus = "1";
        String questionIdList = "";  //把接收过来的question_list弄成xx,xx,xx的方式，以便更新sequence_exam更快一点
        int intFav = 0;

        //拿取课程的语言
        String examLanguage = examMap.get(examId)[2];
        jsonMap.put("examLanguage", examLanguage);
        //*

        jsonMap.put("paperId", paperId);
        jsonMap.put("userId", userId);
        jsonMap.put("paperName", paperName);
        jsonMap.put("paperDesc", paperDesc);
        jsonMap.put("paperPoint", paperPoint);
        jsonMap.put("paperTime", paperTime);
        jsonMap.put("totalQuestions", totalQuestions);
        jsonMap.put("useTime", useTime);

        JSONArray questionListGet = json.getJSONArray("questionList");
        List<Map<String, Object>> questionList = new ArrayList<Map<String, Object>>();   //问题的列表

        ////把收藏的题目的ID，放进数组，否则后面很多遍历，怕那台烂云服务器顶不住
        String sqlFavorites = "select question_id from favorites_question where user_id='" + userId + "'";
        List favoritesList = jdbcTemplate.queryForList(sqlFavorites);
        int[] arrFavorites = new int[favoritesList.size()];  //定义一个数组放，会省一点资源
        Iterator itFavorites = favoritesList.iterator();
        if (itFavorites.hasNext()) {
            int m = 0;
            while (itFavorites.hasNext()) {
                Map favoritesMap = (Map) itFavorites.next();
                arrFavorites[m] = Integer.valueOf(favoritesMap.get("question_id").toString());
                m++;
            }
        }
        ////数组生成完成

        ////记录历史做题数
        Boolean isHistoryRecord = historyRecord(userId, questionListGet.size(), headers);
        ////历史做题数记录完成


        for (int i = 0; i < questionListGet.size(); i++) {               //从questionList中遍历每一题，看其答案是否正确
            Map questionMapGet = (Map) questionListGet.get(i);           //从原始的json中拿数据
            Map<String, Object> questionMapPut = new LinkedHashMap<>();  //新的Map
            questionIdList = questionIdList + questionId + ",";

            //从原始的中拿数据，不再读数据库
            questionId = questionMapGet.get("questionId").toString();
//            System.out.println(questionId);

            /*
            //这里看看有没有收藏的题目，如果有收藏的，则标记此题是收藏题目
            if (arrFavorites.length != 0) {      //如果有收藏题
                for (int n = 0; n < arrFavorites.length; n++) {   //如果有大于等于1条收藏的数据
                    if (arrFavorites[n] == Integer.valueOf(questionId)) {
                        intFav = 1;
                        break;
                    } else {
                        intFav = 0;
                    }
                }
            } else {   //如果完全没有收藏的数据（0条收藏），走这个if条件
                intFav = 0;
            }
            */

            try {
                answerSequence = questionMapGet.get("answerSequence").toString();
                answerIdList = questionMapGet.get("answerId").toString();   //递交的答案列表，用","隔开
            } catch (Exception e) {
                answerIdList = "";
                answerSequence = "";
            }

            List<Map<String, Object>> answerListReturn = new ArrayList<Map<String, Object>>();   //答案的列表
            String answerSequenceDecode = new String(Base64.decodeBase64(answerSequence));  //需要先Base64解码

            /* 处理问题内容 */
            String[] questionContent = questionMapByAll.get(questionId);

            //放进新的Map
            questionMapPut.put("questionId", questionId);
            questionMapPut.put("questionNameEn", questionContent[1]);
            questionMapPut.put("questionNameCn", questionContent[2]);
            questionMapPut.put("questionSubject", questionContent[3]);
            questionMapPut.put("questionType", questionContent[0]);
            questionMapPut.put("questionPoint", questionContent[4]);
            questionMapPut.put("clarify", questionContent[5]);
            questionMapPut.put("fileId", questionContent[6]);
            questionMapPut.put("appId", questionContent[7]);
            questionMapPut.put("psign", questionContent[8]);
            questionMapPut.put("wxVideoUrl", questionContent[11]);
            questionMapPut.put("selectAnswer", answerIdList);   //选择答案
            questionType = questionContent[0];

            if (questionType.equals("1") || questionType.equals("2")) {   //如果是单选或多选，才需要找出正确答案
                ArrayList<String[]> answerList = answerMap.get(questionId);    //几条答案，放进一个ArrayList，每一条答案，又是一个文本组织，多个字段放了进去
                String[] answerIterate = answerIdList.split(",");

                for (int j = 0; j < answerList.size(); j++) {
                    String[] answerContent = answerList.get(j);   //从answer的arraylist中，并成数据

                    for (int k = 0; k < answerIterate.length; k++) {
                        String answerRsId = answerContent[0];
                        String answerId = answerIterate[k];
                        if (answerId.equals(answerRsId)) {
                            if (!answerContent[3].equals("1")) {
                                correctStatus = "0";
                                break;
                            }
                        }
                    }
                }

                if (!practiseType.equals("4")) {     //如果不是问答题
                    ////先初始化错题
                    sql = "delete from wrong_question where user_id = " + userId + " and paper_id = " + paperId;
                    jdbcTemplate.execute(sql);
                    ////错题初始化完成

                    if (correctStatus.equals("1")) {   //如果有数据，那证明答案是对的
                        //上面已经全部错题删除了的了
//                        sql = "delete from wrong_question where user_id = " + userId + " and question_id = " + questionId;   //删除错误库的数据
//                        jdbcTemplate.execute(sql);
                        correctStatus = "1";

                    } else {    //否则就是错误的
                        //上面已经全部删除了错题的数据
//                        sql = "delete from wrong_question where user_id = " + userId + " and question_id = " + questionId;   //先删除原来的
//                        jdbcTemplate.execute(sql);
                        sql = "insert wrong_question(question_id, exam_id, paper_id, user_id, status) values(" + questionId +
                                ", " + examId + ", " + paperId + ", " + userId + ", '1')";          //再增加新的
                        jdbcTemplate.execute(sql);
                    }

                    //更新exam_sequence的状态
                    sql = "update exam_sequence set do_status = '2', status = '" + correctStatus + "', paper_id = " + paperId + ", answer_id = '" + answerIdList + "', gmt_update = now()  where question_id = " + questionId + " and user_id = " + userId;
                    jdbcTemplate.execute(sql);
                    //exam_sequence状态更新完成
                }

                for (int j = 0; j < answerList.size(); j++) {
                    Map<String, Object> answerMapReturn = new LinkedHashMap<>();  //新的Map
                    String[] answerContent = answerList.get(j);   //从answer的arraylist中，并成数据

                    String optionSelect = "";
                    if (j == 0) {
                        optionSelect = "A";
                    } else if (j == 1) {
                        optionSelect = "B";
                    } else if (j == 2) {
                        optionSelect = "C";
                    } else if (j == 3) {
                        optionSelect = "D";
                    } else if (j == 4) {
                        optionSelect = "E";
                    }

                    answerMapReturn.put("answerId", answerContent[0]);
                    answerMapReturn.put("answerContentEn", optionSelect + ". " + answerContent[1]);
                    answerMapReturn.put("answerContentCn", answerContent[2]);
                    answerMapReturn.put("correctFlag", answerContent[3]);
                    answerListReturn.add(answerMapReturn);
                }
            } else {
                correctStatus = "1";    //问答，正确状态为空
            }
            questionMapPut.put("answerList", answerListReturn);
            questionMapPut.put("correctStatus", correctStatus);
            correctStatus = "1";

            questionMapPut.put("favorite", intFav);   //把收藏标记进行清加到JsonMap中

            questionList.add(questionMapPut);
        }

        jsonMap.put("questionList", questionList);

        return json.toJSONString(jsonMap);
    }

    @RequestMapping(path = "/SearchTopic", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String SearchTopic(@RequestBody JSONObject json, @RequestHeader HttpHeaders headers) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        String userId = json.get("userId").toString();
        String keyWord = json.get("keyWord").toString();
        String examId = json.get("examId").toString();
        int i;
        int n = 0;

        int correctId = 0;   //正确的ID
        int correctAnswer = 0;   //正确的选择，这里是因为变量变了，所以必须要多弄一个出来
        int selectAnswer = 0;    //选择的答案，也是ID，是与上面的保持一致


        String returnJson = "{\n" + "\"userId\":\"" + userId + "\",\n" +    //返回的json变
                "\"questionList\":[\n";

//        System.out.println(keyWord.replace("'", "&apos;").trim());
        keyWord = keyWord.replace("'", "&apos;").trim();

        //**  这里是拿题目
        sql = "select * from exam_question where question_name like '%" + keyWord + "%'" +
                " and status='1' and exam_id = " + examId;
        questionList = jdbcTemplate.queryForList(sql);

        Iterator itQuestion = questionList.iterator();
        while (itQuestion.hasNext()) {

            i = 0;  //计数器用
            Map questionMap = (Map) itQuestion.next();
            String questionId = questionMap.get("id").toString();
            String questionType = questionMap.get("question_type").toString();
            String questionNameEn = questionMap.get("question_name").toString();
            String questionNameCn = questionMap.get("question_name_cn").toString();
            String questionPoint = questionMap.get("question_points").toString();
            String clarify = questionMap.get("clarify").toString();
            String fileId = questionMap.get("file_id").toString();
            String appId = questionMap.get("app_id").toString();
            String psign = questionMap.get("psign").toString();
            String correctAnswerReply = "";    //正确的答案临时字一会去串

            returnJson = returnJson + "{\n\"questionId\":\"" + questionId + "\",\n" +
                    "\"questionType\":\"" + questionType + "\",\n" +
                    "\"questionNameEn\":\"" + questionNameEn + "\"," +
                    "\"questionNameCn\":\"" + questionNameCn + "\"," +
                    "\"questionPoint\":\"" + questionPoint + "\",\n" +
                    "\"clarify\":\"" + clarify + "\",\n" +
                    "\"fileId\":\"" + fileId + "\",\n" +
                    "\"appId\":\"" + appId + "\",\n" +
                    "\"psign\":\"" + psign + "\",\n" +
                    "\"answerList\":[\n";


            //**  这里是拿答案
            String sqlAnswer = "select * from exam_answer where question_id=" + questionId + " and status='1'";  //这里把所有单条题目的所有答案拿出来
            List answerList = jdbcTemplate.queryForList(sqlAnswer);
            Iterator itAnswer = answerList.iterator();

            while (itAnswer.hasNext()) {    //拿取单条题目的答案
                i++;
                String optionSelect = "";
                if (i == 1) {
                    optionSelect = "A";
                } else if (i == 2) {
                    optionSelect = "B";
                } else if (i == 3) {
                    optionSelect = "C";
                } else if (i == 4) {
                    optionSelect = "D";
                } else if (i == 5) {
                    optionSelect = "E";
                }

                Map answerMap = (Map) itAnswer.next();
//                System.out.println("answer_id:" + answerMap.get("id").toString());
                returnJson = returnJson + "{\n" +
                        "\"answerId\":\"" + answerMap.get("id") + "\",\n" +
                        "\"answerContentEn\":\"" + optionSelect + ". " + answerMap.get("answer_content") + "\",\n" +
                        "\"answerContentCn\":\"" + answerMap.get("answer_content_cn") + "\"\n" +
                        "},\n";

                if (answerMap.get("correct_answer").toString().equals("1")) {         //这一段是选出正确的答案的代码段，用ID记录低，循环外再拼接
                    correctId = Integer.valueOf(answerMap.get("id").toString());
                    correctAnswer = correctId;
                }
            }

            if (correctId > 0) {
                correctAnswerReply = "\"correctAnswer\":\"" + correctId + "\"\n";    //到下面再一齐组装
            }

            correctId = 0;   //这里要回复初始化变量
            i = 0;

            returnJson = returnJson.substring(0, returnJson.length() - 2); //这里应该会多了一个,和\n所以替换
            returnJson = returnJson + "],\n";   //这里是answerList的收尾
            returnJson = returnJson + correctAnswerReply;   //在这里组装正确答案
            returnJson = returnJson + "},\n";     //这里是questionList的收尾
        }

        returnJson = returnJson.substring(0, returnJson.length() - 2); //questionList这里应该会多了一个,和\n所以替换
        returnJson = returnJson + "]\n}";   //整个json收尾

        return returnJson;
    }

    @RequestMapping(path = "/GetExams", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String GetExams(@RequestBody JSONObject json, @RequestHeader HttpHeaders headers) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        List<Map<String, Object>> queryList = new ArrayList<Map<String, Object>>();
        String userId = json.get("userId").toString();
        jsonMap.put("userId", userId);

        sql = "select a.*,b.user_id from exams a left join exam_roles b on a.id = b.exam_id " +
                "where a.status = '1' and b.user_id = " + userId + " and b.role = '1' and now() < b.expired_date order by id asc";
        SqlRowSet query = jdbcTemplate.queryForRowSet(sql);
        while (query.next()) {
            Map<String, Object> queryMap = new LinkedHashMap<>();
            queryMap.put("examId", query.getString("id"));
            queryMap.put("examName", query.getString("name"));
            queryMap.put("examDesc", query.getString("desc"));
            queryMap.put("examPhoto", query.getString("photo"));
            queryList.add(queryMap);
        }
        jsonMap.put("examList", queryList);

        return json.toJSONString(jsonMap);
    }

    @RequestMapping(path = "/FavTopic", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String FavTopic(@RequestBody JSONObject json, @RequestHeader HttpHeaders headers) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        String userId = json.get("userId").toString();
        String questionId = json.get("questionId").toString();
        String favStatus = json.get("favStatus").toString();   //取消还是收藏
        String examId = "";
        String paperId = "";

        sql = "select * from exam_question where id = " + questionId;
        SqlRowSet idRS = jdbcTemplate.queryForRowSet(sql);
        idRS.next();
        examId = idRS.getString("exam_id");
        paperId = idRS.getString("paper_id");


        //* 收藏处理
        if (favStatus.equals("0")) {     //如果是取消收藏
            sql = "delete from favorites_question where question_id=" + questionId + " and user_id=" + userId;
            jdbcTemplate.execute(sql);
        } else {   //新增收藏
            sql = "delete from favorites_question where question_id=" + questionId + " and user_id=" + userId;  //怕中间出现网络原因传递问题，先删了再新增，安全一点
            jdbcTemplate.execute(sql);
            sql = "insert into favorites_question(question_id,user_id,exam_id,paper_id) values('" + questionId + "','" + userId + "','" + examId + "','" + paperId + "')";
            jdbcTemplate.execute(sql);
        }

        jsonMap.put("userId", userId);
        jsonMap.put("status", 1);
        jsonMap.put("msg", "已处理");

        return json.toJSONString(jsonMap);
    }

    @RequestMapping(path = "/SequenceExams", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String SequenceExams(@RequestBody JSONObject json, @RequestHeader HttpHeaders headers) {
        //** 获取相关的Map全局缓存数据
        HashMap<String, String[]> examMap = MapInit.examMapStatic;
        HashMap<String, String[]> paperMap = MapInit.paperMapStatic;

        //** 其它参数的初始化
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        String userId = json.get("userId").toString();
        String examId = json.get("examId").toString();
        String questionId = "";
        String currectQuestionId = "";    //真正要拿出来用的questionId，一般是questionId.equals("")的时候用
        String types = json.get("types").toString();    //0是错题，2是收藏，1是正常做题
        String answerSequence = "";
        String answerId = "";
        String nextStatus = "";
        String paperId = "";
        String questionSubject = "";
        String repeat = "";

        try {
            questionId = json.getString("questionId").toString();
        } catch (Exception e) {
        }

        try {
            nextStatus = json.getString("nextStatus").toString();
        } catch (Exception e) {
        }

        try {
            paperId = json.getString("paperId").toString();
        } catch (Exception e) {
        }

        try {
            questionSubject = json.getString("questionSubject").toString();
        } catch (Exception e) {
        }


        //** 拿取课程的信息
        String[] examDetail = examMap.get(examId);
        jsonMap.put("examName", examDetail[0]);
        jsonMap.put("examDesc", examDetail[1]);
        jsonMap.put("examLanguage", examDetail[2]);
        //*


        //** 拿取考点的信息
        String[] paperDetail = paperMap.get(paperId);
        jsonMap.put("paperName", paperDetail[1]);
        //

        //** 其它的map内容
        jsonMap.put("userId", userId);    //三个固定的先放进去
        jsonMap.put("types", types);
        jsonMap.put("paperId", paperId);

        try {   //下面两个参数是一同出现的，不会只出现一个，无论是提交的问题，还是已经做完的问题
            answerId = json.get("answerId").toString();
            answerSequence = json.get("answerSequence").toString();    //如果点击是已做题目，则没有传answerId
//            answerSequence = new String(Base64.getDecoder().decode(answerSequence));  //需要先Base64解码
        } catch (Exception e) {
        }


        if (questionId.equals("")) {       //代表第一次进入，questionId无，answerId无，answerSequence无
            UpdateSequenceList(userId, types, examId, paperId, questionSubject);  //更新次序列表，每一次第一次进都要，因为要把列表拿出来，像收藏、错误等

            if (types.equals("0")) {  //如果错题重做
                sql = "select * from exam_sequence where user_id = " + userId + " and wrong_do_status = '1' order by question_type asc limit 1";
                SqlRowSet getQuestionId = jdbcTemplate.queryForRowSet(sql);
                getQuestionId.next();
                currectQuestionId = getQuestionId.getString("question_id");

            } else if (types.equals("2")) {    //如果是收藏题目
                sql = "select * from exam_sequence where user_id = " + userId + " and fav_do_status = '1' order by question_type asc limit 1";
                SqlRowSet getQuestionId = jdbcTemplate.queryForRowSet(sql);
                getQuestionId.next();
                currectQuestionId = getQuestionId.getString("question_id");

            } else if (types.equals("1")) {    //如果是正常做题
                sql = "select count(*) as total from exam_sequence where user_id = " + userId + " and do_status = '1' and paper_id = " + paperId;   //看看是不是全部做完的
                SqlRowSet statusRowSet = jdbcTemplate.queryForRowSet(sql);
                statusRowSet.next();
                String totalCount = statusRowSet.getString("total");

                if (totalCount.equals("0")) {    //如果全部都做完，则拿第一条题的详细信息
                    sql = "select * from exam_sequence where user_id = " + userId + " and paper_id = " + paperId +
                            " order by question_type asc limit 1";
                    SqlRowSet getQuestionId = jdbcTemplate.queryForRowSet(sql);
                    getQuestionId.next();
                    currectQuestionId = getQuestionId.getString("question_id");
                    repeat = "1";      //是否全部做完的标准

                } else {   //否则就拿第一条未做
                    sql = "select * from exam_sequence where user_id = " + userId + " and do_status = '1' and paper_id = " + paperId +
                            " order by question_type asc limit 1";
                    SqlRowSet getQuestionId = jdbcTemplate.queryForRowSet(sql);
                    getQuestionId.next();
                    currectQuestionId = getQuestionId.getString("question_id");
                }
            }

            if (repeat.equals("1")) {      //如果是全部做完，拿第一条，走第一个分支
                jsonMap.putAll(GetTopicDetail(currectQuestionId, userId));
            } else {                       //否则，如果是正常做题的时候，走下面的分支
                jsonMap.putAll(GetTopic(currectQuestionId, userId));
            }
            jsonMap.putAll(GetSequenceList(userId, paperId, types));

        } else if (!questionId.equals("") && !answerId.equals("")) {  //代表提交答案，questionId有，answerId有，answerSequence有
            jsonMap.putAll(DoTopic(questionId, answerId, answerSequence, userId, examId, paperId, types));
            jsonMap.putAll(GetSequenceList(userId, paperId, types));
            ////记录历史做题数
            Boolean isHistoryRecord = historyRecord(userId, 1, headers);
            ////历史做题数记录完成

        } else if (!questionId.equals("") && nextStatus.equals("3")) {  //拿取下一题，但不是提交答案
            jsonMap.putAll(GetTopic(questionId, userId));
            jsonMap.putAll(GetSequenceList(userId, paperId, types));

        } else if (!questionId.equals("") && answerId.equals("")) { //代表查看之前的题目，本次内，questionId有，answerId无，answerSequence无
            jsonMap.putAll(GetTopicDetail(questionId, userId));
            jsonMap.putAll(GetSequenceList(userId, paperId, types));
        }

        return json.toJSONString(jsonMap);
    }

    @RequestMapping(path = "/RedoSequenceExam", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String RedoSequenceExam(@RequestBody JSONObject json, @RequestHeader HttpHeaders headers) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        String userId = json.get("userId").toString();
        String examId = json.get("examId").toString();

        sql = "update exam_sequence set status = '',do_status = '1',wrong_do_status = '',answer_id = '' where user_id = " + userId + " and exam_id = " + examId;
        jdbcTemplate.execute(sql);

        sql = "delete from wrong_question where user_id = " + userId + " and exam_id = " + examId;
        jdbcTemplate.execute(sql);

        sql = "delete from favorites_question where user_id = " + userId + " and exam_id = " + examId;
        jdbcTemplate.execute(sql);

        jsonMap.put("userId", userId);
        jsonMap.put("status", "1");
        jsonMap.put("msg", "成功重置");

        return json.toJSONString(jsonMap);
    }

    @RequestMapping(path = "/GetFilterList", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String GetFilterList(@RequestBody JSONObject json, @RequestHeader HttpHeaders headers) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        List<Map<String, Object>> examList = new ArrayList<Map<String, Object>>();   //课程的数组
        List<Map<String, Object>> questionSubjectList = new ArrayList<Map<String, Object>>();   //问题类型的数组
        String userId = json.get("userId").toString();

        sql = "select a.*,b.name,b.language from exam_roles a left join exams b on a.exam_id = b.id where a.user_id = " + userId + " and a.role = '1' and b.status='1' and now() < a.expired_date";
        SqlRowSet examRs = jdbcTemplate.queryForRowSet(sql);
        while (examRs.next()) {
            Map<String, Object> examMap = new LinkedHashMap<>();  //放exam的map
            examMap.put("examId", examRs.getString("exam_id"));
            examMap.put("examName", examRs.getString("name"));
            examList.add(examMap);
        }

        //** 组装试题
        Map<String, Object> questionSubject1 = new LinkedHashMap<>();
        questionSubject1.put("id", "1");
        questionSubject1.put("name", "练习题");
        questionSubjectList.add(questionSubject1);
//        Map<String, Object> questionSubject2 = new LinkedHashMap<>();
//        questionSubject2.put("id", "2");
//        questionSubject2.put("name", "模拟题");
//        questionSubjectList.add(questionSubject2);

        jsonMap.put("examList", examList);
        jsonMap.put("questionSubject", questionSubjectList);

        return json.toJSONString(jsonMap);
    }

    public Map<String, Object> DoTopic(String questionId, String answerId, String answerSequence, String userId, String examId, String paperId, String types) {
        /* 获取相关的Map全局缓存数据 */
        HashMap<String, String[]> questionMapByAll = MapInit.questionMapByAllStatic;
        LinkedHashMap<String, ArrayList<String[]>> answerMap = MapInit.answerMapStatic;

        /* 其它参数初始化 */
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        String correctStatus = "0";
        String correctAnswerId = "";
        String clarify = "";
        String questionType = "";

        //**  这里是捞单条题目内容
        String[] questionContent = questionMapByAll.get(questionId);
        jsonMap.put("questionId", questionId);
        jsonMap.put("questionType", questionContent[0]);
        jsonMap.put("questionNameEn", questionContent[1]);
        jsonMap.put("questionNameCn", questionContent[2]);
        jsonMap.put("questionPoint", questionContent[4]);
        jsonMap.put("clarify", questionContent[5]);
        jsonMap.put("fileId", questionContent[6]);
        jsonMap.put("appId", questionContent[7]);
        jsonMap.put("psign", questionContent[8]);
        jsonMap.put("examId", questionContent[9]);
        questionType = questionContent[0];            //拿取题型并赋值，用于后面的不同题型判断


        //** 这里找出是不是收藏的题目
        sql = "select count(*) as total from favorites_question where question_id = " + questionId + " and user_id = " + userId;
        SqlRowSet favRowSet = jdbcTemplate.queryForRowSet(sql);
        favRowSet.next();
        if (favRowSet.getString("total").equals("1")) {    //如果不是收藏
            jsonMap.put("favorites", "1");
        } else {
            jsonMap.put("favorites", "0");
        }
        //*收藏处理完毕

        List<Map<String, Object>> answerListReturn = new ArrayList<Map<String, Object>>();   //答案的数组

        if (!questionType.equals("3")) {       //如果不是问答题，即如果是单选或多选
            //** 处理答案
            String answerSequenceDecode = new String(Base64.decodeBase64(answerSequence));  //需要先Base64解码
            ArrayList<String[]> answerList = answerMap.get(questionId);    //几条答案，放进一个ArrayList，每一条答案，又是一个文本组织，多个字段放了进去

            for (int i = 0; i < answerList.size(); i++) {
                String optionSelect = "";
                String[] answerContent = answerList.get(i);

                if (i == 0) {
                    optionSelect = "A";
                } else if (i == 1) {
                    optionSelect = "B";
                } else if (i == 2) {
                    optionSelect = "C";
                } else if (i == 3) {
                    optionSelect = "D";
                } else if (i == 4) {
                    optionSelect = "E";
                }

                Map<String, Object> answerMapReturn = new LinkedHashMap<>();  //放answer的map
                answerMapReturn.put("id", answerContent[0]);
                answerMapReturn.put("answerContentEn", optionSelect + ". " + answerContent[1]);
                answerMapReturn.put("answerContentCn", answerContent[2]);
                answerMapReturn.put("correctFlag", answerContent[3]);

                if (answerContent[3].equals("1")) {   //如果正确答案是这个，则把ID加起来
                    correctAnswerId = correctAnswerId + answerContent[0] + ",";
                }

                answerListReturn.add(answerMapReturn);

//                if (answerRowSet.getString("correct_answer").equals("1")) {    //如果是正确状态的话，则赋值正确ID，总有一个可以赋值到的
//                    correctAnswerId = answerRowSet.getString("id");
//                }
            }

            correctAnswerId = correctAnswerId.substring(0, correctAnswerId.length() - 1);    //将正确的id最后的"，"去除

            if (correctAnswerId.equals(answerId)) {   //如果是正确的话
                if (types.equals("0")) {    //如果是错题重做，或者收藏题目重做

                    //更新状态
                    sql = "update exam_sequence set status = '1', answer_id = '" + answerId + "', wrong_do_status = '2' where user_id = " + userId + " and question_id = " + questionId;
                    jdbcTemplate.execute(sql);
                    //删除错误数据
                    sql = "delete from wrong_question where user_id = " + userId + " and question_id = " + questionId;
                    jdbcTemplate.execute(sql);
                } else if (types.equals("2")) {    //如果收藏题目做错的话

                    sql = "delete from wrong_question where user_id = " + userId + " and question_id = " + questionId;
                    jdbcTemplate.execute(sql);
                    sql = "update exam_sequence set status = '1', answer_id = '" + answerId + "', fav_do_status = '2' where user_id = " + userId + " and question_id = " + questionId;
                    jdbcTemplate.execute(sql);
                } else {

                    sql = "delete from wrong_question where user_id = " + userId + " and question_id = " + questionId;
                    jdbcTemplate.execute(sql);
                    sql = "update exam_sequence set status = '1', answer_id = '" + answerId + "', do_status = '2' where user_id = " + userId + " and question_id = " + questionId;
                    jdbcTemplate.execute(sql);
                }
                correctStatus = "1";    //答案正确与否状态标识

            } else {      //如果答案是错误的
                if (types.equals("0")) {    //如果是错题重做，或者收藏题目重做

                    //更新状态
                    sql = "update exam_sequence set status = '0', answer_id = '" + answerId + "', wrong_do_status = '2' where user_id = " + userId + " and question_id = " + questionId;
                    jdbcTemplate.execute(sql);
                } else if (types.equals("2")) {    //如果收藏题目做错的话

                    sql = "delete from wrong_question where user_id = " + userId + " and question_id = " + questionId;
                    jdbcTemplate.execute(sql);
                    sql = "insert wrong_question(question_id,user_id,exam_id,paper_id) values('" + questionId + "','" + userId + "','" + examId + "','" + paperId + "')";
                    jdbcTemplate.execute(sql);
                    sql = "update exam_sequence set status = '0', answer_id = '" + answerId + "', fav_do_status = '2' where user_id = " + userId + " and question_id = " + questionId;
                    jdbcTemplate.execute(sql);
                } else {

                    sql = "delete from wrong_question where user_id = " + userId + " and question_id = " + questionId;
                    jdbcTemplate.execute(sql);
                    sql = "insert wrong_question(question_id,user_id,exam_id,paper_id) values('" + questionId + "','" + userId + "','" + examId + "','" + paperId + "')";
                    jdbcTemplate.execute(sql);
                    sql = "update exam_sequence set status = '0', answer_id = '" + answerId + "', do_status = '2' where user_id = " + userId + " and question_id = " + questionId;
                    jdbcTemplate.execute(sql);
                }
                correctStatus = "0";    //答案正确与否状态标识
            }
        } else {   //如果是问答题
            correctStatus = "1";
            sql = "update exam_sequence set status = '1', do_status = '2', answer_id = '" + correctAnswerId + "' where user_id = " + userId + " and question_id = " + questionId;
            jdbcTemplate.execute(sql);
        }

        jsonMap.put("answerList", answerListReturn);   //将答案列表，放进json
        jsonMap.put("correctStatus", correctStatus);   //把答染ID的顺序放进jsonMap
        jsonMap.put("selectAnswer", answerId);   //所选答案ID
        jsonMap.put("correctAnswer", correctAnswerId);   //所选答案ID
        jsonMap.put("answerSequence", answerSequence);   //答案序列

        return jsonMap;
    }

    public Map<String, Object> GetTopic(String questionId, String userId) {   //按ID拿已做的题目

        /* 获取相关的Map全局缓存数据 */
        HashMap<String, String[]> questionMapByAll = MapInit.questionMapByAllStatic;
        LinkedHashMap<String, ArrayList<String[]>> answerMap = MapInit.answerMapStatic;


        /* 其它内容的初始化 */
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        String answerSequence = "";


        /* 这里是捞单条题目内容 */
        String[] questionDetail = questionMapByAll.get(questionId);
        jsonMap.put("questionId", questionId);
        jsonMap.put("questionType", questionDetail[0]);
        jsonMap.put("questionNameEn", questionDetail[1]);
        jsonMap.put("questionNameCn", questionDetail[2]);
        jsonMap.put("questionPoint", questionDetail[4]);
        jsonMap.put("examId", questionDetail[9]);


        /* 这里找出是不是收藏的题目 */
        sql = "select count(*) as total from favorites_question where question_id = " + questionId + " and user_id = " + userId;
        SqlRowSet favRowSet = jdbcTemplate.queryForRowSet(sql);
        favRowSet.next();
        if (favRowSet.getString("total").equals("1")) {    //如果不是收藏
            jsonMap.put("favorites", "1");
        } else {
            jsonMap.put("favorites", "0");
        }


        /* 处理答案 */
        List<Map<String, Object>> answerListReturn = new ArrayList<Map<String, Object>>();   //答案的数组
        ArrayList<String[]> answerList = answerMap.get(questionId);

        if (!questionDetail[0].equals("3")) {   //如果不是问答题，才有答案列表清单
            //Collections.shuffle(answerList);   //如果有答案的情况下，把答案随机排序一下

            for (int i = 0; i < answerList.size(); i++) {
                String[] answerContent = answerList.get(i);
                String optionSelect = "";
                if (i == 0) {
                    optionSelect = "A";
                } else if (i == 1) {
                    optionSelect = "B";
                } else if (i == 2) {
                    optionSelect = "C";
                } else if (i == 3) {
                    optionSelect = "D";
                } else if (i == 4) {
                    optionSelect = "E";
                }

                answerSequence = answerSequence + answerContent[0] + ",";   //将答案ID的顺序记录下来

                Map<String, Object> answerMapReturn = new LinkedHashMap<>();  //放question的map
                answerMapReturn.put("id", answerContent[0]);
                answerMapReturn.put("answerContentEn", optionSelect + ". " + answerContent[1]);
                answerMapReturn.put("answerContentCn", answerContent[2]);
                answerListReturn.add(answerMapReturn);
            }

            if (!answerSequence.equals("")) {   //把答案顺序Base64编码后返回
                answerSequence = Base64.encodeBase64String(answerSequence.substring(0, answerSequence.length() - 1).getBytes(StandardCharsets.UTF_8));
            }

            jsonMap.put("answerList", answerListReturn);   //将答案列表，放进json
            jsonMap.put("answerSequence", answerSequence);   //答案序列
        }

        return jsonMap;
    }

    public Map<String, Object> GetTopicDetail(String questionId, String userId) {   //按ID拿已做的题目

        /* 获取相关的Map全局缓存数据 */
        HashMap<String, String[]> questionMapByAll = MapInit.questionMapByAllStatic;
        LinkedHashMap<String, ArrayList<String[]>> answerMap = MapInit.answerMapStatic;

        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        String correctStatus = "1";
        String correctAnswerId = "";
        String selectAnswerId = "";
        String answerSequence = "";
        String clarify = "";
        String questionType = "";

        //**  拿之前选择的
        sql = "select * from exam_sequence where question_id = " + questionId + " and user_id = " + userId;
        SqlRowSet sequenceRowSet = jdbcTemplate.queryForRowSet(sql);
        sequenceRowSet.next();
        selectAnswerId = sequenceRowSet.getString("answer_id");
        correctStatus = sequenceRowSet.getString("status");

        //**  这里是捞单条题目内容
        String[] questionContent = questionMapByAll.get(questionId);
        jsonMap.put("questionId", questionId);
        jsonMap.put("questionType", questionContent[0]);
        jsonMap.put("questionNameEn", questionContent[1]);
        jsonMap.put("questionNameCn", questionContent[2]);
        jsonMap.put("questionPoint", questionContent[4]);
        jsonMap.put("clarify", questionContent[5]);
        jsonMap.put("fileId", questionContent[6]);
        jsonMap.put("appId", questionContent[7]);
        jsonMap.put("psign", questionContent[8]);
        jsonMap.put("examId", questionContent[9]);
        questionType = questionContent[0];

        //** 这里找出是不是收藏的题目
        sql = "select count(*) as total from favorites_question where question_id = " + questionId + " and user_id = " + userId;
        SqlRowSet favRowSet = jdbcTemplate.queryForRowSet(sql);
        favRowSet.next();
        if (favRowSet.getString("total").equals("1")) {    //如果不是收藏
            jsonMap.put("favorites", "1");
        } else {
            jsonMap.put("favorites", "0");
        }


        //** 处理答案
        answerSequence = new String(Base64.decodeBase64(answerSequence));
        List<Map<String, Object>> answerListReturn = new ArrayList<Map<String, Object>>();   //答案的数组
        ArrayList<String[]> answerList = answerMap.get(questionId);    //几条答案，放进一个ArrayList，每一条答案，又是一个文本组织，多个字段放了进去

        if (!questionType.equals("3")) {    //如果不是问题题，是选择题才会有用例案
            //Collections.shuffle(answerList);   //如果有答案的情况下，把答案随机排序一下

            for (int i = 0; i < answerList.size(); i++) {
                String[] answerContent = answerList.get(i);

                String optionSelect = "";
                if (i == 0) {
                    optionSelect = "A";
                } else if (i == 1) {
                    optionSelect = "B";
                } else if (i == 2) {
                    optionSelect = "C";
                } else if (i == 3) {
                    optionSelect = "D";
                } else if (i == 4) {
                    optionSelect = "E";
                }

                Map<String, Object> answerMapReturn = new LinkedHashMap<>();  //放question的map
                answerMapReturn.put("id", answerContent[0]);
                answerMapReturn.put("answerContentEn", optionSelect + ". " + answerContent[1]);
                answerMapReturn.put("answerContentCn", answerContent[2]);
                answerMapReturn.put("correctFlag", answerContent[3]);

                if (answerContent[3].equals("1")) {   //如果正确答案是这个，则把ID加起来
                    correctAnswerId = correctAnswerId + answerContent[0] + ",";
                }

                answerSequence = answerSequence + answerContent[0] + ",";   //将答案ID的顺序记录下来
                answerListReturn.add(answerMapReturn);
            }

            answerSequence = Base64.encodeBase64String(answerSequence.substring(0, answerSequence.length() - 1).getBytes(StandardCharsets.UTF_8));
            correctAnswerId = correctAnswerId.substring(0, correctAnswerId.length() - 1);   //把最后的,去除
            jsonMap.put("answerList", answerListReturn);   //将答案列表，放进json
            jsonMap.put("correctStatus", correctStatus);   //是否正确
            jsonMap.put("selectAnswer", selectAnswerId);   //所选答案ID
            jsonMap.put("correctAnswer", correctAnswerId);   //所选答案ID
            jsonMap.put("answerSequence", answerSequence);   //答案序列

        } else {   //如果是问答题的话
            correctStatus = "";
            jsonMap.put("answerList", answerListReturn);   //将答案列表，放进json
            jsonMap.put("correctStatus", correctStatus);   //是否正确
            jsonMap.put("selectAnswer", "");   //所选答案ID
            jsonMap.put("correctAnswer", "");   //所选答案ID
            jsonMap.put("answerSequence", "");   //答案序列
        }

        return jsonMap;
    }

    public Map<String, Object> GetSequenceList(String userId, String paperId, String types) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        List<Map<String, Object>> questionList = new ArrayList<Map<String, Object>>();   //答案的数组
        SqlRowSet sequenceRowSet;
        String doStatus = "";   //正常做题的状态
        String wrongDoStatus = "";   //错误做题的状态
        String favDoStatus = "";   //收藏做题的状态
        String status = "";   //题目正确错误等状态

        if (types.equals("0")) {    //如果是错题重做
            sql = "select * from exam_sequence where user_id = " + userId + " and paper_id = " + paperId + " and wrong_do_status in ('1','2') order by question_type,id asc";
            sequenceRowSet = jdbcTemplate.queryForRowSet(sql);
            while (sequenceRowSet.next()) {
                Map<String, Object> questionMap = new LinkedHashMap<>();  //放question的map
                questionMap.put("questionId", sequenceRowSet.getString("question_id"));
                questionMap.put("questionType", sequenceRowSet.getString("question_type"));
                questionMap.put("examId", sequenceRowSet.getString("exam_id"));
                questionMap.put("paperId", sequenceRowSet.getString("paper_id"));

                status = sequenceRowSet.getString("status");
                wrongDoStatus = sequenceRowSet.getString("wrong_do_status");
                if (wrongDoStatus.equals("1")) {
                    questionMap.put("status", "3");
                } else if (wrongDoStatus.equals("2")) {
                    questionMap.put("status", status);
                }
                questionList.add(questionMap);
            }
        } else if (types.equals("2")) {   //如果是收藏题重做
            sql = "select * from exam_sequence where user_id = " + userId + " and paper_id = " + paperId + " and fav_do_status in ('1','2') order by question_type,id asc";
            sequenceRowSet = jdbcTemplate.queryForRowSet(sql);
            while (sequenceRowSet.next()) {
                Map<String, Object> questionMap = new LinkedHashMap<>();  //放question的map
                questionMap.put("questionId", sequenceRowSet.getString("question_id"));
                questionMap.put("questionType", sequenceRowSet.getString("question_type"));
                questionMap.put("examId", sequenceRowSet.getString("exam_id"));
                questionMap.put("paperId", sequenceRowSet.getString("paper_id"));

                status = sequenceRowSet.getString("status");
                favDoStatus = sequenceRowSet.getString("fav_do_status");
                if (favDoStatus.equals("1")) {
                    questionMap.put("status", "3");
                } else if (favDoStatus.equals("2")) {
                    questionMap.put("status", status);
                }
                questionList.add(questionMap);
            }
        } else if (types.equals("1")) {
            //**  拿之前选择的
            sql = "select * from exam_sequence where user_id = " + userId + " and paper_id = " + paperId + " and do_status in ('1','2') order by question_type,id asc";
            sequenceRowSet = jdbcTemplate.queryForRowSet(sql);
            while (sequenceRowSet.next()) {
                Map<String, Object> questionMap = new LinkedHashMap<>();  //放question的map
                questionMap.put("questionId", sequenceRowSet.getString("question_id"));
                questionMap.put("questionType", sequenceRowSet.getString("question_type"));
                questionMap.put("status", sequenceRowSet.getString("status"));
                questionMap.put("examId", sequenceRowSet.getString("exam_id"));
                questionMap.put("paperId", sequenceRowSet.getString("paper_id"));

                status = sequenceRowSet.getString("status");
                doStatus = sequenceRowSet.getString("do_status");
                if (doStatus.equals("1")) {
                    questionMap.put("status", "3");
                } else if (doStatus.equals("2")) {
                    questionMap.put("status", status);
                }
                questionList.add(questionMap);
            }
        }

        jsonMap.put("questionList", questionList);   //答案序列

        return jsonMap;
    }

    public Map<String, Object> GetTopicList(int[] favoritesList, String sql, String types) {  //按试卷做题：获取题目
        /* 获取相关的Map全局缓存数据 */
//        HashMap<String, String[]> questionMapByAll = MapInit.questionMapByAllStatic;
        LinkedHashMap<String, ArrayList<String[]>> answerMap = MapInit.answerMapStatic;

        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        List<Map<String, Object>> questionRsList = new ArrayList<Map<String, Object>>();   //问题的数组
        String questionId = "";
        int intFav = 0;
        List<Map<String, Object>> questionList = new ArrayList<Map<String, Object>>();   //问题的列表

        questionRsList = jdbcTemplate.queryForList(sql);

        jsonMap.put("totalQuestions", String.valueOf(questionRsList.size()));

        Iterator itQuestion = questionRsList.iterator();
        while (itQuestion.hasNext()) {
            Map questionMapRs = (Map) itQuestion.next();     //需要先转换为Map
            Map<String, Object> questionMap = new LinkedHashMap<>();  //放Json内容
            String answerSequence = "";   //答案的排序

            questionId = questionMapRs.get("id").toString();
            questionMap.put("questionId", questionMapRs.get("id").toString());
            questionMap.put("questionType", questionMapRs.get("question_type").toString());
            questionMap.put("questionNameEn", questionMapRs.get("question_name").toString());
            questionMap.put("questionNameCn", questionMapRs.get("question_name_cn").toString());
            questionMap.put("questionSubject", questionMapRs.get("question_subject").toString());
            questionMap.put("questionPoint", questionMapRs.get("question_points").toString());


            /* 处理答案 */
            List<Map<String, Object>> answerListReturn = new ArrayList<Map<String, Object>>();   //答案的列表，用于返回给json用
            ArrayList<String[]> answerList = answerMap.get(questionId);

            if (!questionMapRs.get("question_type").toString().equals("3")) {   //如果不是问答题，才有答案列表清单
                Collections.shuffle(answerList);     //如果有答案的情况下，把答案随机排序一下

                for (int i = 0; i < answerList.size(); i++) {
                    String[] answerContent = answerList.get(i);
                    String optionSelect = "";
                    if (i == 0) {
                        optionSelect = "A";
                    } else if (i == 1) {
                        optionSelect = "B";
                    } else if (i == 2) {
                        optionSelect = "C";
                    } else if (i == 3) {
                        optionSelect = "D";
                    } else if (i == 4) {
                        optionSelect = "E";
                    }

                    Map<String, Object> answerMapReturn = new LinkedHashMap<>();  //放question的map
                    answerMapReturn.put("answerId", answerContent[0]);
                    answerMapReturn.put("answerContentEn", optionSelect + ". " + answerContent[1]);
                    answerMapReturn.put("answerContentCn", answerContent[2]);
                    answerSequence = answerSequence + answerContent[0] + ",";   //将答案ID的顺序记录
                    answerListReturn.add(answerMapReturn);
                }
                answerSequence = Base64.encodeBase64String(answerSequence.substring(0, answerSequence.length() - 1).getBytes(StandardCharsets.UTF_8));
                answerSequence = answerSequence.substring(0, answerSequence.length() - 1);
                questionMap.put("answerSequence", answerSequence);
            }
            questionMap.put("answerList", answerListReturn);

            int i = 0;
            if (favoritesList.length != 0) {      //如果有收藏题
                for (i = 0; i < favoritesList.length; i++) {   //如果有大于等于1条收藏的数据
                    if (favoritesList[i] == Integer.valueOf(questionId)) {
                        intFav = 1;
                        break;
                    } else {
                        intFav = 0;
                    }
                }
            } else {   //如果完全没有收藏的数据（0条收藏），走这个if条件
                intFav = 0;
            }
            questionMap.put("favorite", intFav);
            questionList.add(questionMap);
        }

        jsonMap.put("questionList", questionList);

        return jsonMap;
    }

    public boolean historyRecord(String userId, int historyCount, HttpHeaders headers) {   //记录做题的历史数
        String token = headers.getFirst("token");  //解释token
        String historyStatus = CommonLib.getHistoryStatus(token);  //这里记录着是否需要记录该人员的做题历史记录
        if (historyStatus.equals("1")) {   //只有标记是需要记录历史做题数的，才进行记录，在users表中进行相关人员标识
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String today = dateFormat.format(calendar.getTime());
            String historySql = "insert history_record(user_id,topic_count) values('" + userId + "','" + historyCount + "')";
            jdbcTemplate.execute(historySql);
        }
        return true;
    }

    public boolean UpdateSequenceList(String userId, String types, String examId, String paperId, String questionSubject) {

        //**
        if (types.equals("0")) {        //如果是错题重做
            //先把wrong_do_status清空，按paper_id清空
            sql = "update exam_sequence set wrong_do_status = '' where user_id = " + userId;
            jdbcTemplate.execute(sql);

            //然后再把当前paper_id下的错误数据，设成wrong_do_status='1'
            sql = "update exam_sequence a, wrong_question b set a.wrong_do_status = '1' where b.user_id =" + userId + " and " +
                    "b.paper_id = " + paperId + " and a.question_id = b.question_id";
            jdbcTemplate.execute(sql);
//
//            sql = "update exam_sequence set status = temp_status where exam_id = " + examId + " and user_id = " + userId + " and temp_status in ('0','1')";   //将temp_status的状态，赋值回status，因为有些错题，可能已经做正确了，第一次进入时要初始化
//            jdbcTemplate.execute(sql);
//
//            sql = "update exam_sequence set do_status = '0', temp_status = '' where exam_id = " + examId + " and user_id = " + userId;
//            jdbcTemplate.execute(sql);
//
//            sql = "update exam_sequence set do_status = '1', temp_status = '3' " + "where status = '0' and exam_id = " + examId + " and user_id = " + userId + " and paper_id = " + paperId + " and question_subject = '" + questionSubject + "'";   //标记需要做的题目
//            jdbcTemplate.execute(sql);

        } else if (types.equals("2")) {   //如果是做收藏题目
            //先把fav_do_status清空，按paper_id清空
            sql = "update exam_sequence set fav_do_status = '' where user_id = " + userId;
            jdbcTemplate.execute(sql);

            //然后再把当前paper_id下的错误数据，设成wrong_do_status='1'
            sql = "update exam_sequence a, favorites_question b set a.fav_do_status = '1' where b.user_id =" + userId + " and " +
                    "b.paper_id = " + paperId + " and a.question_id = b.question_id";
            jdbcTemplate.execute(sql);

        } else if (types.equals("1")) {    //正常做题
//            //先把do_status清空，按paper_id清空
//            sql = "update exam_sequence set do_status = '' where user_id = " + userId;
//            jdbcTemplate.execute(sql);
//
//            //然后再把当前paper_id下的错误数据，设成do_status='1'
//            sql = "update exam_sequence a, favorites_question b set a.wrong_do_status = '1' where b.user_id =" + userId + " and " +
//                    "b.paper_id = " + paperId;
//            jdbcTemplate.execute(sql);

        }

        return true;
    }

    @RequestMapping(path = "/GetFavCount", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String GetFavCount(@RequestBody JSONObject json, @RequestHeader HttpHeaders headers) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容

        String userId = json.get("userId").toString();   //用户ID
        String examId = json.get("examId").toString();   //ExamId


        sql = "select count(*) as total from favorites_question where user_id = " + userId + " and exam_id = " + examId;
        SqlRowSet favRS = jdbcTemplate.queryForRowSet(sql);
        favRS.next();

        jsonMap.put("status", "1");
        jsonMap.put("favCount", favRS.getString("total"));
        jsonMap.put("userId", userId);
        jsonMap.put("examId", examId);

        return json.toJSONString(jsonMap);
    }

    @RequestMapping(path = "/GetWrongCount", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String GetWrongCount(@RequestBody JSONObject json, @RequestHeader HttpHeaders headers) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容

        String userId = json.get("userId").toString();   //用户ID
        String examId = json.get("examId").toString();   //ExamId


        sql = "select count(*) as total from wrong_question where user_id = " + userId + " and exam_id = " + examId;
        SqlRowSet favRS = jdbcTemplate.queryForRowSet(sql);
        favRS.next();

        jsonMap.put("status", "1");
        jsonMap.put("WrongCount", favRS.getString("total"));
        jsonMap.put("userId", userId);
        jsonMap.put("examId", examId);

        return json.toJSONString(jsonMap);
    }

}
