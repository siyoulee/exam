(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-5ddb288a"],{5467:function(t,e,r){"use strict";r("636c")},"636c":function(t,e,r){},8118:function(t,e,r){"use strict";r.r(e);var s=function(){var t=this,e=t._self._c;return e("div",{staticClass:"login-bg"},[e("div",{staticClass:"login-container clearfix"},[t._m(0),e("div",{staticClass:"fr login-right"},[e("div",{staticClass:"login-wrap"},[e("div",{staticClass:"login-content"},[e("h2",[t._v("账号登陆")]),e("el-form",{ref:"form",attrs:{model:t.form,rules:t.rules}},[e("el-form-item",{attrs:{prop:"userName"}},[e("el-input",{attrs:{placeholder:"手机号/用户名","prefix-icon":"el-icon-user"},model:{value:t.form.userName,callback:function(e){t.$set(t.form,"userName",e)},expression:"form.userName"}})],1),e("el-form-item",{attrs:{prop:"password"}},[e("el-input",{attrs:{type:"password",placeholder:"请输入密码","prefix-icon":"el-icon-lock"},model:{value:t.form.password,callback:function(e){t.$set(t.form,"password",e)},expression:"form.password"}})],1),e("div",{staticStyle:{display:"flex","justify-content":"space-between","margin-bottom":"30px"}},[e("div",{staticStyle:{cursor:"pointer",color:"#666"},on:{click:t.handleForget}},[t._v("找回密码")]),e("router-link",{staticStyle:{cursor:"pointer",color:"#2771ea"},attrs:{to:"/register"}},[t._v("注册帐号")])],1),e("el-form-item",[e("el-button",{staticClass:"mt15 login-btn",attrs:{type:"primary"},on:{click:t.submitForm}},[t._v("登 录")])],1)],1)],1)])])]),t._m(1)])},o=[function(){var t=this,e=t._self._c;return e("div",{staticClass:"login-text fl"},[e("h2",[t._v("CTBOK")]),e("p",[t._v("CTBOK自主研发整套考题，供千万考生免费使用并已帮助百万考生取得优异的成绩。")]),e("p",[t._v("CTBOK自主研发整套考题，供千万考生免费使用并已帮助百万考生取得优异的成绩。CTBOK自主研发整套考题，供千万考生免费使用并已帮助百万考生取得优异的成绩。")]),e("img",{attrs:{src:r("e642"),alt:""}})])},function(){var t=this,e=t._self._c;return e("p",{staticClass:"filing"},[e("a",{attrs:{href:"http://beian.miit.gov.cn",target:"_blank"}},[t._v("粤ICP备2022014929号")])])}],i=r("7ded"),a=r("5f87"),n={name:"login",data:function(){return{form:{},rules:{userName:[{required:!0,message:"请输入账号",trigger:"change"}],password:[{required:!0,message:"请输入密码",trigger:"change"}]}}},methods:{handleForget:function(){this.$alert("功能正在实现，请微信联系：CTBOK_SIYOU","提示",{confirmButtonText:"确定"})},submitForm:function(){var t=this;this.$refs["form"].validate((function(e){e&&Object(i["a"])({mobile:t.form.userName,password:t.form.password}).then((function(e){"success"===e.loginStatus?(Object(a["f"])(!0),t.$store.dispatch("Login",e),t.msgSuccess("登录成功！"),t.$router.push("/personal/index")):t.msgError("登录失败，请检查账号密码是否正确！")}))}))}}},c=n,l=(r("5467"),r("2877")),u=Object(l["a"])(c,s,o,!1,null,"46a45105",null);e["default"]=u.exports},e642:function(t,e,r){t.exports=r.p+"static/img/login-bg.7350f8c9.png"}}]);