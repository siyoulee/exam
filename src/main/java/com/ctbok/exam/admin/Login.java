package com.ctbok.exam.admin;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ctbok.exam.ultils.*;   //JWT类

import java.util.*;

@RestController
@RequestMapping(path = "/exam")
public class Login {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容

    @RequestMapping(path = "/admin/AdminLogin",produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
    public String AdminLogin(@RequestBody JSONObject json) {
        Map<String, Object> jsonMap = new LinkedHashMap<>();  //放Json内容
        String mobile = json.get("mobile").toString();
        String password = json.get("password").toString();
        String adminId = "";
        String token = "";
        String servletPath = "";

        String sql = "select * from admin where mobile='" + mobile + "' and password=MD5('" + password + "') limit 1";

        List queryList = jdbcTemplate.queryForList(sql);

        if (queryList.isEmpty()) {
            jsonMap.put("status", "0");
            jsonMap.put("msg", "登录失败");
        }
        else {
            Iterator it = queryList.iterator();

            if (it.hasNext()) {
                Map queryMap = (Map) it.next();
                adminId = queryMap.get("id").toString();
            }
            JWT objToken = new JWT();   //Token生成类

            sql = "select a.id,a.servlet_path,b.user_group_id,b.role from interfaces a left join interface_roles b on a.id = b.interface_id where a.status='1' and b.user_group_id = 3";
            SqlRowSet queryRoles = jdbcTemplate.queryForRowSet(sql);
            while (queryRoles.next()) {
                servletPath = servletPath + queryRoles.getString("servlet_path") + ",";
            }

            servletPath = servletPath.substring(0, servletPath.length() - 1);  //去除最后的"，"
            //拿取servletPath完成

            token = objToken.getJWToken(adminId, "0", "", servletPath);

            jsonMap.put("status", "1");
            jsonMap.put("msg", "登录成功");
            jsonMap.put("token", token);
            jsonMap.put("adminId", adminId);
        }

        return json.toJSONString(jsonMap);
    }
}
