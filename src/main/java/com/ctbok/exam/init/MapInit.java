package com.ctbok.exam.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapInit {

    public static HashMap<String, String[]> examMapStatic = new HashMap<>();           //以exam_id为key，exam内容为value
    public static HashMap<String, String[]> paperMapStatic = new HashMap<>();          //以paper_id为key，paper内容为values
    public static HashMap<String, HashMap> paperQuestionMapStatic = new HashMap<>();   //以paper_id为key,以questionMap为value
    public static HashMap<String, String[]> questionMapByPaperStatic = new HashMap<>();       //以question_id为key，以question内容为value，但这个是分了paper去做的
    public static HashMap<String, String[]> questionMapByAllStatic = new HashMap<>();       //以question_id为key，以question内容为value，这个是全量的
    public static HashMap<String, ArrayList<String[]>> answerMapStatic = new HashMap<>();         //以question_id为key，以answer内容为value

}
