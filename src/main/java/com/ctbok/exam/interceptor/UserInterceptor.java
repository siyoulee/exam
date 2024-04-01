package com.ctbok.exam.interceptor;

import com.alibaba.fastjson2.JSONObject;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ctbok.exam.ultils.JWT;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.maven.surefire.shade.org.apache.commons.lang3.BooleanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 拦截器
 * @Author: 新写的旧代码
 * @CreateTime: 2022/3/24
 */
public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> map = new HashMap<>();

        String Token = request.getHeader("token");
        String SING = "==--Ct88**88--==";
        String getServletPath = request.getServletPath();
        boolean accessRight = true;

        // 捕获刚刚JWT中抛出的异常,并封装对应的返回信息
//        try {
//            JWT.verifyToken(Token);
//            return true;
//        }catch (SignatureVerificationException e){
//            map.put("msg", "无效签名");
//        }catch (TokenExpiredException e){
//            map.put("msg", "已过期");
//        }catch (AlgorithmMismatchException e){
//            map.put("msg", "算法不一致");
//        }catch (Exception e){
//            map.put("msg", "无效身份信息");
//        }
        try {
            JWT.verifyToken(Token);

            DecodedJWT verify = com.auth0.jwt.JWT.require(Algorithm.HMAC256(SING)).build().verify(Token);
            String userId = verify.getClaim("userId").asString();
            String historyStatus = verify.getClaim("historyStatus").asString();  //是否记录做题的历史数据
            String tokenServletPath = verify.getClaim("servletPath").asString();
            String arrServletPath[] = tokenServletPath.split(",");    //这里是把questionIdList变成数组

            for (int i = 0; i < arrServletPath.length; i++) {     //判断页面是否有权限
                if (arrServletPath[i].toString().equals(getServletPath)) {
                    accessRight = true;
                    break;
                }
                else {
                    accessRight = false;
                }
            }

            if (BooleanUtils.isTrue(accessRight)) {
                return true;
            }
            else {
                map.put("accessStatus", "0");
                map.put("msg", "无权访问此页面");
                JSONObject json = new JSONObject(map);
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter writer = response.getWriter();
                writer.print(json);
                writer.flush();
                writer.close();
                return false;
            }


        }catch (SignatureVerificationException e){
            map.put("accessStatus", "0");
            map.put("msg", "无效身份信息");
        }catch (TokenExpiredException e){
            map.put("accessStatus", "0");
            map.put("msg", "无效身份信息");
        }catch (AlgorithmMismatchException e){
            map.put("accessStatus", "0");
            map.put("msg", "无效身份信息");
        }catch (Exception e){
            map.put("accessStatus", "0");
            map.put("msg", "无效身份信息");
        }
        // 封装返回值
//        map.put("code", 4010);
        JSONObject json = new JSONObject(map);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(json);
        writer.flush();
        writer.close();
        return false;
    }
}

