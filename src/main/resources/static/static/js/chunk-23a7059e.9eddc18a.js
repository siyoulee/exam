(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-23a7059e"],{"14b5":function(e,r,s){"use strict";s("c3af")},7803:function(e,r,s){"use strict";s.r(r);var t=function(){var e=this,r=e._self._c;return r("div",{staticClass:"login-bg"},[r("div",{staticClass:"login-container clearfix"},[e._m(0),r("div",{staticClass:"fr login-right"},[r("div",{staticClass:"login-wrap"},[r("div",{staticClass:"login-content"},[r("h2",[e._v("注册账号")]),r("el-form",{ref:"form",attrs:{model:e.form,rules:e.rules}},[r("el-form-item",{attrs:{prop:"examType"}},[r("div",{staticClass:"sex-wrap"},[r("label",[e._v("课程类型")]),r("div",[r("el-radio",{attrs:{label:"1"},model:{value:e.form.examType,callback:function(r){e.$set(e.form,"examType",r)},expression:"form.examType"}},[e._v("CDMP")]),r("el-radio",{attrs:{label:"9"},model:{value:e.form.examType,callback:function(r){e.$set(e.form,"examType",r)},expression:"form.examType"}},[e._v("CDGA")])],1)])]),r("el-form-item",{attrs:{prop:"mobile"}},[r("el-input",{attrs:{placeholder:"请输入手机号码",maxlength:"11","prefix-icon":"el-icon-mobile-phone"},model:{value:e.form.mobile,callback:function(r){e.$set(e.form,"mobile",r)},expression:"form.mobile"}})],1),r("el-form-item",{attrs:{prop:"username"}},[r("el-input",{attrs:{placeholder:"请输入用户名",maxlength:"20","prefix-icon":"el-icon-user"},model:{value:e.form.username,callback:function(r){e.$set(e.form,"username",r)},expression:"form.username"}})],1),r("el-form-item",{attrs:{prop:"sex"}},[r("div",{staticClass:"sex-wrap"},[r("label",[e._v("性别")]),r("div",[r("el-radio",{attrs:{label:"1"},model:{value:e.form.sex,callback:function(r){e.$set(e.form,"sex",r)},expression:"form.sex"}},[e._v("男")]),r("el-radio",{attrs:{label:"0"},model:{value:e.form.sex,callback:function(r){e.$set(e.form,"sex",r)},expression:"form.sex"}},[e._v("女")])],1)])]),r("el-form-item",{attrs:{prop:"mail"}},[r("el-input",{attrs:{placeholder:"请输入邮箱",maxlength:"50","prefix-icon":"el-icon-message"},model:{value:e.form.mail,callback:function(r){e.$set(e.form,"mail",r)},expression:"form.mail"}})],1),r("el-form-item",{attrs:{prop:"password"}},[r("el-input",{attrs:{type:"password",placeholder:"请输入密码",maxlength:"20","prefix-icon":"el-icon-lock"},model:{value:e.form.password,callback:function(r){e.$set(e.form,"password",r)},expression:"form.password"}})],1),r("el-form-item",{attrs:{prop:"password1"}},[r("el-input",{attrs:{type:"password",placeholder:"请输入重复密码",maxlength:"20","prefix-icon":"el-icon-lock"},model:{value:e.form.password1,callback:function(r){e.$set(e.form,"password1",r)},expression:"form.password1"}})],1),r("el-form-item",[r("div",{staticStyle:{display:"flex","justify-content":"space-between","margin-bottom":"20px"}},[r("el-checkbox",{model:{value:e.isRead,callback:function(r){e.isRead=r},expression:"isRead"}},[e._v("我已阅读并同意《用户服务协议》")]),r("router-link",{staticStyle:{cursor:"pointer",color:"#2771ea"},attrs:{to:"/login"}},[e._v("去登录")])],1),r("el-button",{staticClass:"login-btn",attrs:{type:"primary"},on:{click:e.submitForm}},[e._v("注 册")])],1)],1)],1)])])])])},a=[function(){var e=this,r=e._self._c;return r("div",{staticClass:"login-text fl"},[r("h2",[e._v("CTBOK")]),r("p",[e._v("CTBOK自主研发整套考题，供千万考生免费使用并已帮助百万考生取得优异的成绩。")]),r("p",[e._v("CTBOK自主研发整套考题，供千万考生免费使用并已帮助百万考生取得优异的成绩。CTBOK自主研发整套考题，供千万考生免费使用并已帮助百万考生取得优异的成绩。")]),r("img",{attrs:{src:s("e642"),alt:""}})])}],o=s("7ded"),i={name:"register",data:function(){var e=this,r=function(r,s,t){if(e.form.password===s)return t();t(new Error("两次密码不一致"))};return{form:{sex:"1",examType:"1"},rules:{mobile:[{required:!0,message:"请输入手机号",trigger:"blur"},{pattern:/^1(3[0-9]|4[5,7]|5[0,1,2,3,5,6,7,8,9]|6[2,5,6,7]|7[0,1,7,8,6]|8[0-9]|9[1,8,9])\d{8}$/,message:"请输入正确的手机号"}],username:[{required:!0,message:"请输入用户名",trigger:"blur"}],mail:[{required:!0,message:"请输入邮箱",trigger:"blur"},{pattern:/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/,message:"请输入正确的邮箱"}],password:[{required:!0,message:"请输入密码",trigger:"blur"}],password1:[{required:!0,message:"请输入密码",trigger:"blur"},{validator:r,trigger:"blur"}]},time:60,codeDisabled:!1,isRead:!1}},methods:{getCode:function(){var e=this;if(!this.codeDisabled){this.codeDisabled=!0;var r=setInterval((function(){e.time--,e.time<=0&&(clearInterval(r),e.codeDisabled=!1)}),1e3)}},submitForm:function(){var e=this;this.$refs["form"].validate((function(r){if(r){if(!e.isRead)return e.msgError("请先同意用户服务协议！"),!1;Object(o["f"])(e.form).then((function(r){e.msgSuccess("注册成功！"),e.$refs["form"]&&e.$refs["form"].resetFields()}))}}))}}},l=i,n=(s("14b5"),s("2877")),m=Object(n["a"])(l,t,a,!1,null,"43b004ec",null);r["default"]=m.exports},c3af:function(e,r,s){},e642:function(e,r,s){e.exports=s.p+"static/img/login-bg.7350f8c9.png"}}]);