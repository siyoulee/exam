(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-87b88380"],{"31b5":function(e,t){e.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAAq5JREFUWEfNl01IFGEYx3+PuxQERZegQwQhdPESCZ2KfZUwpIwiFCK/dieTLkFEQafs2KnASxrOepAOiZUoSkbt7EqnkjoUHfs4VBBdiiQy54lZHd1dVp39rLm9M8/7/H/zf59553mFnMvY2q9Kkwh7c5+VNBbeoEw7MbmcmUcyByauD1BOAA5KMpBgDRooDlpR6hTsZEwsf84KgInrNZQ+lIuOJbcCJg0cZuK6HbiJ0u0qR1KWzHiTVwAito4JNC4ou59Z8iNw5gICja3HgAkVLiSj0p8FYGxNeDecmDQUkLPgUGOrolx3LOkLBBCJa6u41K2ptE4NCDxPRGUqq84KBUgTF385uY5W1YHFMIOzHfK5JAeKf/n8Mwt24L8BMHfUBIVxesRZK7YoBzJ2yGAMwkMnKifzBRcF0DCs9bpISzB1kBATiW6ZKxtAUOEgcUU5ECRx0JiSAcyQdiN05RUUok5U3q8HUzJAxNYBgXN5RTL2+LJ+BUHt9eOaR3TbdLt8/2dFaIbUQmgXeKkfueL0yR8fpuQlCOKGies+Ua4q1AChTWE6Zjrlpzf3kK07ZmPy1c+z2hGVuR8wQzqJcHRZ6FEoxJknXfIt9wUqBtA8pZvnv3BP4Hi68RBSYZfTjy35lAlRMQBPxCvG+QVGRWlaFn3hhmlLdcq7ii+BL+CteQjuAwfT95TXrktbqkfepp3JqM6K9YRmRHfpb8YF9ovSj7AzEZO2qgF4Qo3DWqsulxQO1LicenpWPlQVwHe6ZUC3TPTKfNVqYKN9Y6UGGmy9q3AYYbcTlV8bTSz2uYlrHGXMiclk9hKsHs3OO5bcLlZg3T+h19aFSAj0JmIymAXgDZZPR17v9wplvKwQQgSoB7Yi7PF/21mn4zTEkhMdQG1ZAZaSzalwIxmVUT/3XzrRkTAYpS3TAAAAAElFTkSuQmCC"},"366c":function(e,t,s){"use strict";s("fc56")},3846:function(e,t,s){s("9e1e")&&"g"!=/./g.flags&&s("86cc").f(RegExp.prototype,"flags",{configurable:!0,get:s("0bfb")})},"6b54":function(e,t,s){"use strict";s("3846");var i=s("cb7c"),n=s("0bfb"),r=s("9e1e"),o="toString",a=/./[o],c=function(e){s("2aba")(RegExp.prototype,o,e,!0)};s("79e5")((function(){return"/a/b"!=a.call({source:"a",flags:"b"})}))?c((function(){var e=i(this);return"/".concat(e.source,"/","flags"in e?e.flags:!r&&e instanceof RegExp?n.call(e):void 0)})):a.name!=o&&c((function(){return a.call(this)}))},"759f":function(e,t,s){"use strict";var i=s("5ca1"),n=s("0a49")(3);i(i.P+i.F*!s("2f21")([].some,!0),"Array",{some:function(e){return n(this,e,arguments[1])}})},"87b3":function(e,t,s){var i=Date.prototype,n="Invalid Date",r="toString",o=i[r],a=i.getTime;new Date(NaN)+""!=n&&s("2aba")(i,r,(function(){var e=a.call(this);return e===e?o.call(this):n}))},fc56:function(e,t,s){},fdce:function(e,t,s){"use strict";s.r(t);var i=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"examination-wrap bg-f5"},[i("div",{staticClass:"examination pt20 pb20 clearfix"},[i("div",{staticClass:"examination-left fl"},[i("div",{staticClass:"bg-white pad15 mb20"},[i("div",{staticClass:"clearfix bor-bottom-eee pb15 pt10"},[i("h1",{staticClass:"fl fz26 fb"},[e._v(e._s(e.form.examName))]),i("span",{staticClass:"tag fr"},[e._v("答题中")])]),i("div",{staticClass:"pt10"},[i("p",{staticClass:"color-666"},[e._v(e._s(e.form.examDesc))])])]),i("div",{directives:[{name:"show",rawName:"v-show",value:e.checked&&e.errorQuestions.length>0||!e.checked&&e.questionList.length>0,expression:"(checked && errorQuestions.length > 0) || (!checked && questionList.length > 0)"}],staticClass:"bg-white pad15 mb20"},[i("div",{staticClass:"bor-bottom-eee pb20 pt10 pl10"},[i("span",{staticClass:"title-2"},[e._v(e._s("1"==e.form.questionType?"单选题":"2"==e.form.questionType?"多选题":"问答题"))]),i("el-checkbox",{staticStyle:{float:"right"},attrs:{label:"显示中文"},model:{value:e.isShowCn,callback:function(t){e.isShowCn=t},expression:"isShowCn"}})],1),i("div",{staticClass:"topic"},[i("div",{staticClass:"topic-item clearfix"},[i("div",{staticClass:"topic-item-label fl"},[i("div",{staticClass:"inlineb tc"},[i("span",{staticStyle:{height:"18px","background-color":"#2771ea","border-radius":"3px","line-height":"18px",color:"#fff",padding:"0 6px"}},[e._v(e._s(e.currentQuestionIndex+1))])])]),i("div",{staticClass:"topic-item-block fl"},[i("div",{staticClass:"clearfix"},[i("pre",{staticClass:"fl",staticStyle:{width:"calc(100% - 60px)"},domProps:{innerHTML:e._s(e.form.questionNameEn)}}),i("div",{staticClass:"fr"},[i("span",{staticClass:"topic-item-score mr10"},[e._v(e._s(e.form.questionPoint)+"分")]),"1"===e.form.favorites?i("i",{staticClass:"iconfont icon-shoucang_shixin",staticStyle:{color:"#ff5930",cursor:"pointer"},on:{click:function(t){return e.handleSign(e.form)}}}):i("i",{staticClass:"iconfont icon-shoucang",staticStyle:{color:"#999",cursor:"pointer"},on:{click:function(t){return e.handleSign(e.form)}}})])]),e.isShowCn?i("p",{staticClass:"color-999 mt5",domProps:{innerHTML:e._s(e.form.questionNameCn)}}):e._e(),"1"==e.form.questionType?i("el-radio-group",{staticClass:"mt10",model:{value:e.form.selectAnswer,callback:function(t){e.$set(e.form,"selectAnswer",t)},expression:"form.selectAnswer"}},e._l(e.form.answerList,(function(t,s){return i("el-radio",{key:s,staticClass:"mb5",attrs:{label:t.id,disabled:e.getSubmitFlag()}},[i("div",{staticStyle:{display:"inline-block","vertical-align":"top"}},[i("p",{staticClass:"color-666",staticStyle:{"white-space":"pre-line"},domProps:{innerHTML:e._s(t.answerContentEn)}}),e.isShowCn?i("p",{staticClass:"color-999",staticStyle:{"white-space":"pre-line"},domProps:{innerHTML:e._s(t.answerContentCn)}}):e._e()])])})),1):e._e(),"2"==e.form.questionType?i("el-checkbox-group",{staticClass:"mt10",model:{value:e.form.selectAnswer,callback:function(t){e.$set(e.form,"selectAnswer",t)},expression:"form.selectAnswer"}},e._l(e.form.answerList,(function(t,s){return i("el-checkbox",{key:s,staticClass:"mb5",staticStyle:{display:"block"},attrs:{label:t.id,disabled:e.getSubmitFlag()}},[i("div",{staticStyle:{display:"inline-block","vertical-align":"top"}},[i("p",{staticClass:"color-666",staticStyle:{"white-space":"pre-line"},domProps:{innerHTML:e._s(t.answerContentEn)}}),e.isShowCn?i("p",{staticClass:"color-999",staticStyle:{"white-space":"pre-line"},domProps:{innerHTML:e._s(t.answerContentCn)}}):e._e()])])})),1):e._e(),"3"!=e.form.questionType||e.getSubmitFlag()?e._e():i("div",{staticStyle:{"padding-top":"20px"}},[i("el-input",{attrs:{type:"textarea",placeholder:"请输入",disabled:!e.inAnswer},model:{value:e.form.selectAnswer,callback:function(t){e.$set(e.form,"selectAnswer",t)},expression:"form.selectAnswer"}})],1),e.getSubmitFlag()?i("div",{staticClass:"bor-top-eee mt10"},[i("div",{staticStyle:{display:"flex","justify-content":"space-between","align-items":"center"}},["3"==e.form.questionType?i("p",{staticClass:"color-999 pt15 pb15"},[e.form["selectAnswer"]?i("span",[e._v("你的答案："+e._s(e.form["selectAnswer"]))]):e._e()]):i("p",{staticClass:"color-999 pt15 pb15"},[e._v("正确答案："),i("span",{staticStyle:{color:"#46c37b"}},[e._v(e._s(e.getAnswerKey(e.form,e.form["correctAnswer"])))]),e._v("， 你的答案： "),i("span",{staticStyle:{color:"#ff5c2f"}},[e._v(e._s(e.getAnswerKey(e.form,e.form["selectAnswer"])?e.getAnswerKey(e.form,e.form["selectAnswer"]):"未作答"))])]),e.form.appId&&e.form.fileId&&e.form.psign?i("div",{staticStyle:{color:"#2771ea"}},[i("i",{staticClass:"iconfont icon-shipinbofang mr5"}),i("span",{staticStyle:{"margin-top":"5px",display:"inline-block",color:"#2771ea",cursor:"pointer"},on:{click:function(t){return e.handleViewVideo(e.form)}}},[e._v("查看讲解视频")])]):e._e()]),i("div",{staticClass:"pt10 pb10 pl15 pr20",staticStyle:{"background-color":"#f5f7fb",display:"flex"}},[i("div",{staticStyle:{width:"55px"}},[e._v("解析：")]),e.form.clarify?i("div",{staticClass:"color-999",class:!e.form.isSpread&&"clarify-spread",staticStyle:{flex:"1","word-break":"break-all"},domProps:{innerHTML:e._s(e.decode(e.form.clarify))}}):i("div",{staticClass:"color-999"},[e._v("暂无解析")])]),e.form.clarify?i("div",{staticClass:"tr pr20 pb10",staticStyle:{"background-color":"#f5f7fb"}},[i("span",{staticStyle:{color:"#2771ea",cursor:"pointer"},on:{click:function(t){return e.handleSpread(e.form)}}},[e._v(e._s(e.form.isSpread?"收起":"查看更多 >"))])]):e._e()]):e._e()],1)]),i("div",{staticClass:"tc pt20 pb10"},[i("button",{staticClass:"theme-btn mr20",attrs:{disabled:0===e.currentQuestionIndex},on:{click:e.handlePrev}},[e._v("上一题")]),i("button",{staticClass:"theme-btn mr20",attrs:{disabled:e.getSubmitFlag()},on:{click:e.handleSubmit}},[e._v("提交")]),i("button",{staticClass:"theme-btn mr20",attrs:{disabled:e.currentQuestionIndex===e.questionList.length-1||e.questionList.length&&"3"===e.questionList[e.currentQuestionIndex].status},on:{click:e.handleNext}},[e._v("下一题")]),i("button",{staticClass:"theme-btn",attrs:{disabled:e.currentQuestionIndex===e.questionList.length-1},on:{click:e.handleUnanswer}},[e._v("未做题目")])])])])]),i("div",{staticClass:"examination-right fr"},[i("div",{staticClass:"examination-right-fixed"},[i("div",{staticClass:"pad15 br4 bg-white",attrs:{id:"answerSheet"}},[i("div",{staticClass:"bor-bottom-eee pb10"},[i("span",{staticClass:"title-2 fb"},[e._v("答题卡")]),e.inAnswer?i("span",{staticClass:"fr theme-color",staticStyle:{cursor:"pointer"},on:{click:function(t){e.isShowAnswerSheet=!e.isShowAnswerSheet}}},[e._v("收起/展开答题卡>>")]):e._e()]),e._m(0),i("div",{directives:[{name:"show",rawName:"v-show",value:!e.isShowAnswerSheet,expression:"!isShowAnswerSheet"}],staticClass:"answer-sheet-wrap pt10"},e._l(e.answerSheetList,(function(t,s){return i("div",{key:s,staticClass:"answer-sheet"},[t.list.length?i("div",{staticStyle:{"margin-bottom":"5px"}},[e._v(e._s(t.name))]):e._e(),e._l(t.list,(function(t,s){return i("a",{key:s,class:[e.getStatusClass(t)],on:{click:function(s){return e.goAnchor(t)}}},[e._v("\n                "+e._s(t.index+1)+"\n              ")])}))],2)})),0),i("div",{staticClass:"pad15 tc"},[i("button",{staticClass:"theme-btn",on:{click:e.handleRedo}},[e._v("重 做")]),i("button",{staticClass:"theme-btn",staticStyle:{"margin-left":"15px"},on:{click:function(t){return e.$router.go(-1)}}},[e._v("返 回")])])])])])]),i("div",{staticClass:"go-answer-sheet",on:{click:function(t){return e.goAnchor("answerSheet")}}},[i("img",{attrs:{src:s("31b5"),alt:""}})]),e.dialogVisible?i("el-dialog",{attrs:{visible:e.dialogVisible,title:"视频播放",width:"800px","append-to-body":"","before-close":e.handleClose},on:{"update:visible":function(t){e.dialogVisible=t}}},[i("div",{staticStyle:{width:"100%",height:"400px",overflow:"hidden"}},[e.dialogVisible?i("video",{staticStyle:{width:"100%",height:"100%"},attrs:{id:"player-container",preload:"auto",width:"100%",height:"400",playsinline:"","webkit-playsinline":"","x5-playsinline":""}}):e._e()])]):e._e()],1)},n=[function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"ibs-card-body__explain pt10"},[s("span",{staticClass:"ibs-success-bg"}),s("small",[e._v("正确")]),s("span",{staticClass:"ibs-danger-bg"}),s("small",[e._v("错误")]),s("span",{staticClass:"ibs-tip-bg"}),s("small",[e._v("未答")])])}],r=(s("6b54"),s("87b3"),s("759f"),s("6d67"),s("28a5"),s("4f37"),s("cabd")),o=s("e762"),a={name:"Examination",components:{},data:function(){return{inAnswer:!0,isShowAnswerSheet:!1,radio:"",checked:"",questions:[],questionList:[],errorQuestions:[],fixed:!1,form:{},remainingTime:"",remainingTimeS:"",timer:null,overForm:{},inputValue:"",dialogVisible:!1,isShowCn:!1,player:null,currentQuestionId:"",currentAnswerId:void 0,currentTypes:"1",currentQuestionIndex:0,answerSheetList:[]}},created:function(){var e=this;this.GetSequenceExam("first"),window.onscroll=function(){var t=document.documentElement.scrollTop||document.body.scrollTop;e.fixed=t>160}},methods:{isBase64:function(e){if(""===e||""===e.trim())return!1;try{return btoa(atob(e))==e}catch(t){return!1}},decode:function(e){return this.isBase64(e)?decodeURI(o["a"].decode(e)):decodeURI(e)},handleViewVideo:function(e){var t=this;e.appId&&e.fileId&&e.psign?(this.dialogVisible=!0,setTimeout((function(){t.player=TCPlayer("player-container",{fileID:e.fileId,appID:e.appId,psign:e.psign})}),1e3)):this.$message.error("视频不存在")},handleClose:function(){this.dialogVisible=!1,this.player&&this.player.dispose(),this.player=null},handleSpread:function(e){e.isSpread=!e.isSpread,this.$forceUpdate()},GetSequenceExam:function(e){var t=this,s={userId:this.$store.state.user.userId,questionId:this.currentQuestionId,examId:this.$route.query.examId,paperId:this.$route.query.paperId,questionSubject:this.$route.query.questionSubject,types:this.currentTypes,answerId:this.currentAnswerId};"next"===e&&(s.nextStatus="3"),Object(r["m"])(s).then((function(s){"2"==s.questionType&&(s.selectAnswer&&s.selectAnswer.split(",").length?(s.selectAnswer=s.selectAnswer.split(","),s.correctAnswer&&(s.correctAnswer=s.correctAnswer.split(","))):s.selectAnswer=[]),t.form=s,t.questionList=s.questionList||[],t.answerSheetList=[{name:"单选题",list:[]},{name:"多选题",list:[]},{name:"简答题",list:[]}],t.questionList.map((function(e,s){e.index=s,1==e.questionType?(e.selectAnswer=void 0,t.answerSheetList[0].list.push(e)):2==e.questionType?(e.selectAnswer=[],t.answerSheetList[1].list.push(e)):3==e.questionType&&t.answerSheetList[2].list.push(e)})),"first"===e&&t.questionList.some((function(e,s){if("3"===e.status)return t.currentQuestionIndex=s,t.currentQuestionId=e.questionId,!0})),t.getIndex(),t.$forceUpdate()}))},getAnswerKey:function(e,t){var s=[],i=[];return t?"1"===e.questionType?i=[t]:"2"===e.questionType&&(i=t):e.answerList.map((function(e){"1"===e.correctFlag&&i.push(e.id)})),e.answerList.map((function(e){i.map((function(t){e.id===t&&s.push(e.answerContentEn.split(".")[0])}))})),s.toString()},handleSign:function(e){var t=this,s=e.favorites;s="1"==e.favorites?"0":"1",Object(r["c"])({questionId:e.questionId,favStatus:s,userId:this.$store.state.user.userId}).then((function(i){1===i.status?(e.favorites=s,"1"==s?t.$message.success("收藏成功！"):t.$message.success("取消收藏成功！")):t.$message.error(i.msg),t.$forceUpdate()}))},getStatusClass:function(e){var t="";switch(e.status){case"1":t="ibs-success-bg";break;case"0":t="ibs-danger-bg";break;case"3":t="ibs-tip-bg";break}return t},goAnchor:function(e){"string"===typeof e?document.querySelector("#"+e).scrollIntoView({behavior:"smooth"}):"0"!==e.status&&"1"!==e.status||(this.currentQuestionId=e.questionId,this.currentAnswerId=e.answerId,this.GetSequenceExam("index"))},handleAnswer:function(){this.inAnswer=!0,this.init()},handleBack:function(){this.$router.push({path:"/personal/exercise"})},handleRedo:function(){var e=this;this.$confirm("确定要重做吗？","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){Object(r["k"])({userId:e.$store.state.user.userId,examId:e.$route.query.examId}).then((function(t){1==t.status?(e.$message.success("重置成功！"),e.currentQuestionId="",e.currentQuestionIndex=0,e.GetSequenceExam()):e.$message.error(t.msg)}))}))},handleSubmit:function(){var e=this;"3"==this.form.questionType||this.form.selectAnswer?this.$confirm("确定要提交吗？","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){var t=e.form.selectAnswer;"2"===e.form.questionType?t=t.toString():"3"===e.form.questionType&&(t="1"),Object(r["m"])({userId:e.$store.state.user.userId,questionId:e.form.questionId,answerId:t,answerSequence:e.form.answerSequence,examId:e.$route.query.examId,paperId:e.$route.query.paperId,types:e.currentTypes}).then((function(t){"2"==t.questionType&&(t.selectAnswer&&t.selectAnswer.split(",").length?(t.selectAnswer=t.selectAnswer.split(","),t.correctAnswer&&(t.correctAnswer=t.correctAnswer.split(","))):t.selectAnswer=[]),e.form=t,e.questionList=t.questionList||[],e.answerSheetList=[{name:"单选题",list:[]},{name:"多选题",list:[]},{name:"简答题",list:[]}],e.questionList.map((function(t,s){t.index=s,1==t.questionType?(t.selectAnswer=void 0,e.answerSheetList[0].list.push(t)):2==t.questionType?(t.selectAnswer=[],e.answerSheetList[1].list.push(t)):3==t.questionType&&e.answerSheetList[2].list.push(t)})),e.getIndex(),e.$forceUpdate()}))})):this.$alert("请选择答案后在提交","提示")},getIndex:function(){var e=this;this.questionList.length&&this.questionList.map((function(t,s){t.questionId===e.currentQuestionId&&(e.currentQuestionIndex=s)}))},handlePrev:function(){this.currentQuestionIndex--,this.currentQuestionId=this.questionList[this.currentQuestionIndex].questionId,this.currentAnswerId=this.questionList[this.currentQuestionIndex].answerId,this.GetSequenceExam("prev")},handleNext:function(){"3"!==this.questionList[this.currentQuestionIndex].status?(this.currentQuestionIndex++,this.currentQuestionId=this.questionList[this.currentQuestionIndex].questionId,this.currentAnswerId=this.questionList[this.currentQuestionIndex].answerId,"3"===this.questionList[this.currentQuestionIndex].status?this.GetSequenceExam("next"):this.GetSequenceExam()):this.$message.error("请提交完之后在进行下一题！")},handleUnanswer:function(){var e=this;this.currentQuestionIndex++,this.currentQuestionId=this.questionList[this.currentQuestionIndex].questionId,this.questionList.some((function(t,s){if("3"===t.status)return e.currentQuestionIndex=s,e.currentQuestionId=t.questionId,e.GetSequenceExam("next"),!0}))},getSubmitFlag:function(){return this.questionList.length&&this.questionList[this.currentQuestionIndex]&&("0"===this.questionList[this.currentQuestionIndex].status||"1"===this.questionList[this.currentQuestionIndex].status)}}},c=a,l=(s("366c"),s("2877")),u=Object(l["a"])(c,i,n,!1,null,"6260f4b0",null);t["default"]=u.exports}}]);