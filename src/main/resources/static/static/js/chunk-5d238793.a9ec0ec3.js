(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-5d238793"],{1725:function(t,e,n){"use strict";n("28cb")},"28cb":function(t,e,n){},3846:function(t,e,n){n("9e1e")&&"g"!=/./g.flags&&n("86cc").f(RegExp.prototype,"flags",{configurable:!0,get:n("0bfb")})},"6b54":function(t,e,n){"use strict";n("3846");var i=n("cb7c"),a=n("0bfb"),o=n("9e1e"),r="toString",s=/./[r],c=function(t){n("2aba")(RegExp.prototype,r,t,!0)};n("79e5")((function(){return"/a/b"!=s.call({source:"a",flags:"b"})}))?c((function(){var t=i(this);return"/".concat(t.source,"/","flags"in t?t.flags:!o&&t instanceof RegExp?a.call(t):void 0)})):s.name!=r&&c((function(){return s.call(this)}))},"87b3":function(t,e,n){var i=Date.prototype,a="Invalid Date",o="toString",r=i[o],s=i.getTime;new Date(NaN)+""!=a&&n("2aba")(i,o,(function(){var t=s.call(this);return t===t?r.call(this):a}))},dd7b:function(t,e,n){"use strict";n.r(e);n("57e7");var i=function(){var t=this,e=t._self._c;return e("div",{staticClass:"login-bg"},[e("div",{staticClass:"login-container clearfix"},[t._m(0),e("div",{staticClass:"fr login-right"},[e("div",{staticClass:"login-wrap"},[e("div",{staticClass:"login-content"},[e("div",{staticClass:"tab"},[e("h2",{class:0===t.tabIndex&&"active",on:{click:function(e){return t.handleChange(0)}}},[t._v("扫码登录")]),e("h2",{class:1===t.tabIndex&&"active",on:{click:function(e){return t.handleChange(1)}}},[t._v("账号登录")])]),e("div",{directives:[{name:"show",rawName:"v-show",value:0===t.tabIndex,expression:"tabIndex === 0"}]},[e("div",{attrs:{id:"login_container"}})]),e("el-form",{directives:[{name:"show",rawName:"v-show",value:1===t.tabIndex,expression:"tabIndex === 1"}],ref:"form",staticClass:"mt20",attrs:{model:t.form,rules:t.rules}},[e("el-form-item",{attrs:{prop:"userName"}},[e("el-input",{attrs:{placeholder:"手机号","prefix-icon":"el-icon-user"},model:{value:t.form.userName,callback:function(e){t.$set(t.form,"userName",e)},expression:"form.userName"}})],1),e("el-form-item",{attrs:{prop:"password"}},[e("el-input",{attrs:{type:"password",placeholder:"请输入密码","prefix-icon":"el-icon-lock"},nativeOn:{keyup:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:t.submitForm.apply(null,arguments)}},model:{value:t.form.password,callback:function(e){t.$set(t.form,"password",e)},expression:"form.password"}})],1),e("div",{staticStyle:{display:"flex","justify-content":"space-between","margin-bottom":"30px"}},[e("div",{staticStyle:{cursor:"pointer",color:"#666"},on:{click:t.handleForget}},[t._v("找回密码")])]),e("el-form-item",[e("el-button",{staticClass:"mt15 login-btn",attrs:{type:"primary",loading:t.isLoading},on:{click:t.submitForm}},[t._v(t._s(t.isLoading?"登录中...":"登录"))])],1)],1)],1)])])]),t._m(1)])},a=[function(){var t=this,e=t._self._c;return e("div",{staticClass:"login-text fl"},[e("h2",[t._v("CTBOK")]),e("p",[t._v("CTBOK自主研发整套考题，供千万考生免费使用并已帮助百万考生取得优异的成绩。")]),e("p",[t._v("CTBOK自主研发整套考题，供千万考生免费使用并已帮助百万考生取得优异的成绩。CTBOK自主研发整套考题，供千万考生免费使用并已帮助百万考生取得优异的成绩。")]),e("img",{attrs:{src:n("e642"),alt:""}})])},function(){var t=this,e=t._self._c;return e("p",{staticClass:"filing"},[e("a",{attrs:{href:"http://beian.miit.gov.cn",target:"_blank"}},[t._v("粤ICP备2022014929号")])])}],o=(n("6b54"),n("87b3"),n("7ded")),r={name:"login",data:function(){return{tabIndex:0,form:{},rules:{userName:[{required:!0,message:"请输入账号",trigger:"change"}],password:[{required:!0,message:"请输入密码",trigger:"change"}]},isLoading:!1}},mounted:function(){this.WxLogin()},methods:{WxLogin:function(t){function e(){return t.apply(this,arguments)}return e.toString=function(){return t.toString()},e}((function(){new WxLogin({id:"login_container",appid:"wx79a0478372a51c08",scope:"snsapi_login",redirect_uri:encodeURI("https://ctbok.cn/index.html#/wxLogin")})})),handleChange:function(t){this.tabIndex=t},handleForget:function(){this.$alert("功能正在实现，请微信联系：CTBOK_SIYOU","提示",{confirmButtonText:"确定"})},submitForm:function(){var t=this;this.$refs["form"].validate((function(e){e&&(t.isLoading=!0,Object(o["f"])({mobile:t.form.userName,password:t.form.password}).then((function(e){"1"===e.loginStatus?(t.$store.dispatch("Login",e),t.msgSuccess("登录成功！"),t.$router.push("/personal/index")):t.msgError("登录失败，请检查账号密码是否正确！")})).finally((function(){t.isLoading=!1})))}))}}},s=r,c=(n("1725"),n("2877")),l=Object(c["a"])(s,i,a,!1,null,"f55e1b4c",null);e["default"]=l.exports},e642:function(t,e,n){t.exports=n.p+"static/img/login-bg.7350f8c9.png"}}]);