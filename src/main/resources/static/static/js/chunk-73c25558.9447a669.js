(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-73c25558"],{"31b5":function(e,t){e.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAAq5JREFUWEfNl01IFGEYx3+PuxQERZegQwQhdPESCZ2KfZUwpIwiFCK/dieTLkFEQafs2KnASxrOepAOiZUoSkbt7EqnkjoUHfs4VBBdiiQy54lZHd1dVp39rLm9M8/7/H/zf59553mFnMvY2q9Kkwh7c5+VNBbeoEw7MbmcmUcyByauD1BOAA5KMpBgDRooDlpR6hTsZEwsf84KgInrNZQ+lIuOJbcCJg0cZuK6HbiJ0u0qR1KWzHiTVwAito4JNC4ou59Z8iNw5gICja3HgAkVLiSj0p8FYGxNeDecmDQUkLPgUGOrolx3LOkLBBCJa6u41K2ptE4NCDxPRGUqq84KBUgTF385uY5W1YHFMIOzHfK5JAeKf/n8Mwt24L8BMHfUBIVxesRZK7YoBzJ2yGAMwkMnKifzBRcF0DCs9bpISzB1kBATiW6ZKxtAUOEgcUU5ECRx0JiSAcyQdiN05RUUok5U3q8HUzJAxNYBgXN5RTL2+LJ+BUHt9eOaR3TbdLt8/2dFaIbUQmgXeKkfueL0yR8fpuQlCOKGies+Ua4q1AChTWE6Zjrlpzf3kK07ZmPy1c+z2hGVuR8wQzqJcHRZ6FEoxJknXfIt9wUqBtA8pZvnv3BP4Hi68RBSYZfTjy35lAlRMQBPxCvG+QVGRWlaFn3hhmlLdcq7ii+BL+CteQjuAwfT95TXrktbqkfepp3JqM6K9YRmRHfpb8YF9ovSj7AzEZO2qgF4Qo3DWqsulxQO1LicenpWPlQVwHe6ZUC3TPTKfNVqYKN9Y6UGGmy9q3AYYbcTlV8bTSz2uYlrHGXMiclk9hKsHs3OO5bcLlZg3T+h19aFSAj0JmIymAXgDZZPR17v9wplvKwQQgSoB7Yi7PF/21mn4zTEkhMdQG1ZAZaSzalwIxmVUT/3XzrRkTAYpS3TAAAAAElFTkSuQmCC"},3846:function(e,t,s){s("9e1e")&&"g"!=/./g.flags&&s("86cc").f(RegExp.prototype,"flags",{configurable:!0,get:s("0bfb")})},"5ae2":function(e,t,s){"use strict";s.r(t);s("7f7f");var i=function(){var e=this,t=e._self._c;return t("div",{staticClass:"examination-wrap bg-f5"},[t("div",{staticClass:"examination pt20 pb20 clearfix"},[t("div",{staticClass:"examination-left fl"},[e.inAnswer?t("div",{staticClass:"bg-white pad15 mb20"},[t("div",{staticClass:"clearfix bor-bottom-eee pb15 pt10"},[t("h1",{staticClass:"fl fz26 fb"},[e._v(e._s(e.examName)+": "+e._s(e.form.paperName))]),t("span",{staticClass:"tag fr"},[e._v("答题中")])]),t("div",{staticClass:"pt10"},[t("p",{staticClass:"color-666"},[e._v(e._s(e.form.paperDesc))])])]):t("div",{staticClass:"bg-white mb20 pr",staticStyle:{"z-index":"1"}},[t("div",{staticClass:"line"}),t("div",{staticClass:"clearfix pr pb15",staticStyle:{"background-color":"#2771ea","border-radius":"6px 6px 0px 0"}},[t("div",{staticClass:"fl score-header"},[t("div",{staticClass:"clearfix pt10"},[t("h1",{staticClass:"fl fz30 fb"},[e._v(e._s(e.overForm.paperName))]),t("span",{staticClass:"tag ml15",staticStyle:{color:"#2771ea",background:"#fff","line-height":"14px",height:"auto"}},[e._v("批阅完成")])]),t("div",{staticClass:"pt5"},[t("p",{staticStyle:{opacity:"0.5"}},[t("span",{staticClass:"mr20"},[e._v("答题人："+e._s(e.overForm.userId))]),t("span",[e._v("用时："+e._s(e.overForm.useTime)+"分钟")])])])]),t("div",{staticClass:"fr br4 tc score-wrap"},[t("span",[e._v(e._s(e.overForm.getPoint))]),t("p",[e._v("总分："+e._s(e.overForm.paperPoint))])])]),t("div",{staticClass:"pr",staticStyle:{"border-radius":"6px",overflow:"hidden",width:"100%",top:"-5px",background:"#fff"}},[t("el-table",{staticClass:"theme-table-01 exam-table",attrs:{data:e.tableData}},[t("el-table-column",{attrs:{"min-width":"50px",align:"center",prop:"paperPoint",label:"总分"}}),t("el-table-column",{attrs:{"min-width":"50px",align:"center",prop:"getPoint",label:"得分"}}),t("el-table-column",{attrs:{"min-width":"50px",align:"center",prop:"totalQuestions",label:"总共"}}),t("el-table-column",{attrs:{"min-width":"50px",align:"center",prop:"totalCorrect",label:"答对"}}),t("el-table-column",{attrs:{"min-width":"50px",align:"center",prop:"totalError",label:"答错"}}),t("el-table-column",{attrs:{"min-width":"50px",align:"center",prop:"unanswered",label:"未答"}})],1)],1)]),t("div",{directives:[{name:"show",rawName:"v-show",value:e.checked&&e.errorQuestions.length>0||!e.checked&&e.questionList.length>0,expression:"(checked && errorQuestions.length > 0) || (!checked && questionList.length > 0)"}],staticClass:"bg-white pad15 mb20"},["en"===e.form.examLanguage?t("el-checkbox",{staticStyle:{float:"right"},attrs:{label:"显示中文"},model:{value:e.isShowCn,callback:function(t){e.isShowCn=t},expression:"isShowCn"}}):e._e(),e._l(e.questionList,(function(s,i){return[s.list.length?t("div",{staticClass:"bor-bottom-eee pb20 pt10 pl10"},[t("span",{staticClass:"title-2"},[e._v(e._s(s.name))]),t("span",{staticClass:"color-999 ml30"},[e._v("共"+e._s(s.list.length)+"题/共"+e._s(s.paperPoint)+"分")])]):e._e(),t("div",{staticClass:"topic"},e._l(s.list,(function(s,i){return t("div",{key:i,staticClass:"topic-item clearfix",attrs:{id:"ids-"+s.questionId,hidden:e.isShowError&&e.getAnswerKey(s,s["correctAnswer"])==e.getAnswerKey(s,s["selectAnswer"])}},[t("div",{staticClass:"topic-item-label fl"},[t("div",{staticClass:"inlineb tc"},[t("span",{staticStyle:{height:"18px","background-color":"#2771ea","border-radius":"3px","line-height":"18px",color:"#fff",padding:"0 6px"}},[e._v(e._s(s.index+1))])])]),t("div",{staticClass:"topic-item-block fl"},[t("div",{staticClass:"clearfix"},[t("pre",{staticClass:"fl",staticStyle:{width:"calc(100% - 60px)"},domProps:{innerHTML:e._s(s.questionNameEn)}}),t("div",{staticClass:"fr"},[t("span",{staticClass:"topic-item-score mr10"},[e._v(e._s(s.questionPoint)+"分")]),"1"==s.favorite?t("i",{staticClass:"iconfont icon-shoucang_shixin",staticStyle:{color:"#ff5930",cursor:"pointer"},on:{click:function(t){return e.handleSign(s)}}}):t("i",{staticClass:"iconfont icon-shoucang",staticStyle:{color:"#999",cursor:"pointer"},on:{click:function(t){return e.handleSign(s)}}})])]),e.isShowCn?t("p",{staticClass:"color-999 mt5",domProps:{innerHTML:e._s(s.questionNameCn)}}):e._e(),"1"==s.questionType?[t("el-radio-group",{staticClass:"mt10",model:{value:s.selectAnswer,callback:function(t){e.$set(s,"selectAnswer",t)},expression:"item.selectAnswer"}},e._l(s.answerList,(function(s,i){return t("el-radio",{key:i,staticClass:"mb5",attrs:{label:s.answerId,disabled:!e.inAnswer}},[t("div",{staticStyle:{display:"inline-block","vertical-align":"top"}},[t("p",{staticClass:"color-666",staticStyle:{"white-space":"pre-line"},domProps:{innerHTML:e._s(s.answerContentEn)}}),e.isShowCn?t("p",{staticClass:"color-999",staticStyle:{"white-space":"pre-line"},domProps:{innerHTML:e._s(s.answerContentCn)}}):e._e()])])})),1)]:"2"==s.questionType?t("el-checkbox-group",{staticClass:"mt10",model:{value:s.selectAnswer,callback:function(t){e.$set(s,"selectAnswer",t)},expression:"item.selectAnswer"}},e._l(s.answerList,(function(s,i){return t("div",{key:i},[t("el-checkbox",{staticClass:"mb5",staticStyle:{display:"block"},attrs:{label:s.answerId,disabled:!e.inAnswer}},[t("div",{staticStyle:{display:"inline-block","vertical-align":"top"}},[t("p",{staticClass:"color-666",staticStyle:{"white-space":"pre-line"},domProps:{innerHTML:e._s(s.answerContentEn)}}),e.isShowCn?t("p",{staticClass:"color-999",staticStyle:{"white-space":"pre-line"},domProps:{innerHTML:e._s(s.answerContentCn)}}):e._e()])])],1)})),0):t("div",{staticStyle:{"padding-top":"20px"}},[t("el-input",{attrs:{type:"textarea",placeholder:"请输入",disabled:!e.inAnswer},model:{value:s.selectAnswer,callback:function(t){e.$set(s,"selectAnswer",t)},expression:"item.selectAnswer"}})],1),e.inAnswer?e._e():t("div",{staticClass:"bor-top-eee mt10"},[t("div",{staticStyle:{display:"flex","justify-content":"space-between","align-items":"center"}},["3"==s.questionType?t("p",{staticClass:"color-999 pt15 pb15"},[s["selectAnswer"]?t("span",[e._v("你的答案："+e._s(s["selectAnswer"]))]):e._e()]):t("p",{staticClass:"color-999 pt15 pb15"},[e._v("正确答案："),t("span",{staticClass:"theme-color mr15"},[e._v(e._s(e.getAnswerKey(s,s["correctAnswer"])))]),e._v("你的答案： "),t("span",{staticStyle:{color:"#ff5c2f"}},[e._v(e._s(e.getAnswerKey(s,s["selectAnswer"])?e.getAnswerKey(s,s["selectAnswer"]):"未作答"))])]),s.appId&&s.fileId&&s.psign?t("div",{staticStyle:{color:"#2771ea"}},[t("i",{staticClass:"iconfont icon-shipinbofang mr5"}),t("span",{staticStyle:{"margin-top":"5px",display:"inline-block",color:"#2771ea",cursor:"pointer"},on:{click:function(t){return e.handleViewVideo(s)}}},[e._v("查看讲解视频")])]):e._e()]),t("div",{staticClass:"pt10 pb10 pl15 pr20",staticStyle:{"background-color":"#f5f7fb",display:"flex"}},[t("div",{staticStyle:{width:"55px"}},[e._v("解析：")]),s.clarify?t("div",{staticClass:"color-999",class:!s.isSpread&&"clarify-spread",staticStyle:{flex:"1"},domProps:{innerHTML:e._s(e.decode(s.clarify))}}):t("div",{staticClass:"color-999"},[e._v("暂无解析")])]),s.clarify?t("div",{staticClass:"tr pr20 pb10",staticStyle:{"background-color":"#f5f7fb"}},[t("span",{staticStyle:{color:"#2771ea",cursor:"pointer"},on:{click:function(t){return e.handleSpread(s)}}},[e._v(e._s(s.isSpread?"收起":"查看更多 >"))])]):e._e()])],2)])})),0)]}))],2)]),t("div",{staticClass:"examination-right fr"},[t("div",{staticClass:"examination-right-fixed"},[e.inAnswer&&e.remainingTime?t("div",{staticClass:"pt5 pb5 pl15 pr15 color-999 bg-white mb20"},[e._v("倒计时："+e._s(e.remainingTime))]):e._e(),t("div",{staticClass:"pad15 br4 bg-white",attrs:{id:"answerSheet"}},[t("div",{staticClass:"bor-bottom-eee pb10"},[t("span",{staticClass:"title-2 fb"},[e._v("答题卡")]),e.inAnswer?t("span",{staticClass:"fr theme-color",staticStyle:{cursor:"pointer"},on:{click:function(t){e.isShowAnswerSheet=!e.isShowAnswerSheet}}},[e._v("收起/展开答题卡>>")]):e._e()]),e.inAnswer?e._e():t("div",{staticClass:"ibs-card-body__explain pt10"},[t("span",{staticClass:"ibs-success-bg"}),t("small",[e._v("正确")]),t("span",{staticClass:"ibs-danger-bg"}),t("small",[e._v("错误")]),t("span",{staticClass:"ibs-tip-bg"}),t("small",[e._v("未答")])]),t("div",{directives:[{name:"show",rawName:"v-show",value:!e.isShowAnswerSheet,expression:"!isShowAnswerSheet"}],staticClass:"answer-sheet-wrap pt20"},[e.inAnswer?e._l(e.questionList,(function(s,i){return t("div",{key:i,staticClass:"answer-sheet"},[s.list.length?t("div",{staticStyle:{"margin-bottom":"5px"}},[e._v(e._s(s.name))]):e._e(),e._l(s.list,(function(s,i){return t("a",{key:i,class:[s.selectAnswer&&s.selectAnswer.length?"active":""],on:{click:function(t){return e.goAnchor("ids-"+s.questionId)}}},[e._v("\n                    "+e._s(s.index+1)+"\n                    "),s.sign?t("i",{staticClass:"el-icon-collection-tag"}):e._e()])}))],2)})):e._l(e.questionList,(function(s,i){return t("div",{key:i,staticClass:"answer-sheet"},[s.list.length?t("div",{staticStyle:{"margin-bottom":"5px"}},[e._v(e._s(s.name))]):e._e(),e._l(s.list,(function(s,i){return t("a",{key:i,class:[e.getStatusClass(s)],on:{click:function(t){return e.goAnchor("ids-"+s.questionId)}}},[e._v("\n                    "+e._s(s.index+1)+"\n                    "),s.sign?t("i",{staticClass:"el-icon-collection-tag"}):e._e()])}))],2)}))],2),e.inAnswer?t("div",{staticClass:"pad15 tc"},[t("button",{staticClass:"theme-btn",on:{click:e.handleSubmit}},[e._v("立即提交")]),t("button",{staticClass:"theme-btn",staticStyle:{"margin-left":"15px"},on:{click:e.handleBack}},[e._v("返 回")])]):[t("el-checkbox",{staticClass:"mt15",model:{value:e.isShowError,callback:function(t){e.isShowError=t},expression:"isShowError"}},[e._v("只看错题")]),t("div",{staticClass:"pt15 pb15 tc"},[t("button",{staticClass:"theme-btn",staticStyle:{"border-radius":"6px",width:"100%",height:"38px"},on:{click:e.handleBack}},[e._v("返回题目列表")])])]],2)])])]),t("div",{staticClass:"go-answer-sheet",on:{click:function(t){return e.goAnchor("answerSheet")}}},[t("img",{attrs:{src:s("31b5"),alt:""}})]),e.dialogVisible?t("el-dialog",{attrs:{visible:e.dialogVisible,title:"视频播放",width:"800px","append-to-body":"","before-close":e.handleClose},on:{"update:visible":function(t){e.dialogVisible=t}}},[t("div",{staticStyle:{width:"100%",height:"400px",overflow:"hidden"}},[e.dialogVisible?t("video",{staticStyle:{width:"100%",height:"100%"},attrs:{id:"player-container",preload:"auto",width:"100%",height:"400",playsinline:"","webkit-playsinline":"","x5-playsinline":""}}):e._e()])]):e._e()],1)},n=[],a=s("7618"),r=(s("6b54"),s("87b3"),s("28a5"),s("c5f6"),s("6d67"),s("4f37"),s("cabd")),o=s("e762"),c={name:"Examination",components:{},data:function(){return{inAnswer:!0,isShowAnswerSheet:!1,radio:"",checked:"",tableData:[{name1:"单选题",name11:"100",name22:"数学",name2:"0",name3:"40道",name4:"0道",name5:"0道",name6:"40道"}],questions:[],questionList:[],errorQuestions:[],fixed:!1,form:{},remainingTime:"",remainingTimeS:"",timer:null,overForm:{},inputValue:"",dialogVisible:!1,isShowCn:!1,player:null,answerSheetList:[],isShowError:!1}},computed:{examName:function(){return this.$route.query.examName}},created:function(){var e=this;this.init(),window.onscroll=function(){var t=document.documentElement.scrollTop||document.body.scrollTop;e.fixed=t>160}},methods:{isBase64:function(e){if(""===e||""===e.trim())return!1;try{return btoa(atob(e))==e}catch(t){return!1}},decode:function(e){return e?this.isBase64(e)?o["a"].decode(e):decodeURI(e):""},handleViewVideo:function(e){var t=this;e.appId&&e.fileId&&e.psign?(this.dialogVisible=!0,setTimeout((function(){t.player=TCPlayer("player-container",{fileID:e.fileId,appID:e.appId,psign:e.psign})}),1e3)):this.$message.error("视频不存在")},handleClose:function(){this.dialogVisible=!1,this.player&&this.player.dispose(),this.player=null},handleSpread:function(e){e.isSpread=!e.isSpread,this.$forceUpdate()},init:function(){var e=this;Object(r["d"])({examId:this.$route.query.examId,questionSubject:this.$route.query.questionSubject,paperId:this.$route.query.id,practiseType:this.$route.query.practiseType||3,userId:this.$store.state.user.userId}).then((function(t){e.form=t;var s=[{name:"单选题",list:[],paperPoint:0},{name:"多选题",list:[],paperPoint:0},{name:"简答题",list:[],paperPoint:0}];t.questionList.map((function(e,t){e.index=t,1==e.questionType?(e.selectAnswer="",s[0].list.push(e),s[0].paperPoint+=Number(e.questionPoint)):2==e.questionType?(e.selectAnswer=[],s[1].list.push(e),s[1].paperPoint+=Number(e.questionPoint)):3==e.questionType&&(e.selectAnswer="",s[2].list.push(e),s[2].paperPoint+=Number(e.questionPoint))})),e.questionList=JSON.parse(JSON.stringify(s)),"0"!==t.paperTime&&e.countDown()}))},getAnswerKey:function(e,t){var s=[],i=[];return t?"1"==e.questionType?i=[t]:"2"==e.questionType&&(i=JSON.parse(JSON.stringify(t))):e.answerList.map((function(e){"1"==e.correctFlag&&i.push(e.answerId)})),e.answerList.map((function(e){i.map((function(t){e.answerId==t&&s.push(e.answerContentEn.split(".")[0])}))})),s.toString()},handleSign:function(e){var t=this,s=e.favorite;s="1"==e.favorite?"0":"1",Object(r["c"])({questionId:e.questionId,favStatus:s,userId:this.$store.state.user.userId}).then((function(i){1===i.status?(e.favorite=s,"1"==s?t.$message.success("收藏成功！"):t.$message.success("取消收藏成功！")):t.$message.error(i.msg),t.$forceUpdate()}))},countDown:function(){var e=this,t=60*parseInt(this.form.paperTime);this.remainingTime=this.formatSeconds(t),this.timer=setInterval((function(){t--,e.remainingTime=e.formatSeconds(t),e.remainingTimeS=t,t<=0&&(e.remainingTimeS=0,clearInterval(e.timer))}),1e3)},formatSeconds:function(e){var t=parseInt(e),s=0,i=0,n=0;t>60&&(s=parseInt(t/60),t=parseInt(t%60),s>60&&(i=parseInt(s/60),s=parseInt(s%60),i>24&&(n=parseInt(i/24),i=parseInt(i%24))));var a="";return t>0&&(a=parseInt(t)+"秒"),s>0&&(a=parseInt(s)+"分"+a),i>0&&(a=parseInt(i)+"小时"+a),n>0&&(a=parseInt(n)+"天"+a),a},getStatusClass:function(e){var t="3",s=[];e.answerList.map((function(e){"1"===e.correctFlag&&s.push(e.answerId)})),"2"!==e.questionType&&(s=s.toString()),t=e["selectAnswer"]?e["selectAnswer"].toString()==s.toString()?"1":"2":"4","3"==e.questionType&&(t="1");var i="ibs-success-bg";switch(t){case"1":i="ibs-success-bg";break;case"2":i="ibs-danger-bg";break;case"3":i="ibs-warning-bg";break;case"4":i="ibs-tip-bg";break}return i},goAnchor:function(e){document.querySelector("#"+e).scrollIntoView({behavior:"smooth"})},handleAnswer:function(){this.inAnswer=!0,this.init()},handleBack:function(){var e=this.$route.query.practiseType,t="/personal/exercise";1==e?t="/personal/mistakes":2==e?t="/personal/collect":4==e&&(t="/personal/simulation"),this.$router.push({path:t})},handleSave:function(){this.$confirm("确认要保存进度，下次继续吗？","提示",{confirmButtonText:"确定",cancelButtonText:"继续答题",type:"warning"}).then((function(){}))},handleSubmit:function(){var e=this,t=!1;this.answerSheetList.map((function(e){e.list.map((function(e){"3"==e.questionType||e.selectAnswer||(t=!0)}))})),t?this.$alert("还有未完成的题目，请检查后再提交","提示"):this.$confirm("确定要提交吗？","提示",{confirmButtonText:"确定",cancelButtonText:"继续答题",type:"warning"}).then((function(){e.PostExamPage()}))},PostExamPage:function(){var e=this,t={examLanguage:this.form.examLanguage,paperId:this.form.paperId,userId:this.form.userId,paperName:this.form.paperName,practiseType:this.$route.query.practiseType,examId:this.$route.query.examId,paperDesc:this.form.paperDesc,totalQuestions:this.form.totalQuestions,useTime:"0",paperTime:this.form.paperTime,paperPoint:this.form.paperPoint,favoriteIdList:void 0,jsonTopicList:this.form.jsonTopicList},s={},i=[],n=[],o=[];this.questionList.map((function(e){e.list.map((function(e){if(e.selectAnswer||"3"==e.questionType){var t="object"==Object(a["a"])(e.selectAnswer)?e.selectAnswer.toString():e.selectAnswer;"3"==e.questionType&&(t=""),o.push({questionId:e.questionId,answerId:t,answerSequence:e.answerSequence}),s[e.questionId]=t}"1"==e.favorite&&i.push(e.questionId),n.push(e.questionId)}))})),i.length&&(t.favoriteIdList=i.toString()),t.questionList=o,t.useTime=Math.ceil((60*parseInt(this.form.paperTime)-this.remainingTimeS)/60).toString(),Object(r["j"])(t).then((function(t){e.inAnswer=!1,e.overForm=t;var s=0,i=0,a=0,r=n.length-t.questionList.length;t.questionList.map((function(e){var t=[];e.answerList&&e.answerList.map((function(e){"1"===e.correctFlag&&t.push(e.answerId)})),"1"===e.questionType?e.correctAnswer=t.toString():"2"===e.questionType&&(e.correctAnswer=t,e.selectAnswer&&(e.selectAnswer=e.selectAnswer.split(","))),e.selectAnswer==e.correctAnswer?(s++,a+=Number(e.questionPoint)):i++}));var o=[{name:"单选题",list:[],paperPoint:0},{name:"多选题",list:[],paperPoint:0},{name:"简答题",list:[],paperPoint:0}];t.questionList.map((function(e,t){e.index=t,1==e.questionType?(o[0].list.push(e),o[0].paperPoint+=Number(e.questionPoint)):2==e.questionType?(o[1].list.push(e),o[1].paperPoint+=Number(e.questionPoint)):3==e.questionType&&(o[2].list.push(e),o[2].paperPoint+=Number(e.questionPoint))})),e.questionList=o,e.errorQuestions=[],e.tableData=[{paperPoint:e.overForm.paperPoint,getPoint:a,totalQuestions:e.overForm.totalQuestions,totalCorrect:s,totalError:i,unanswered:r}],clearInterval(e.timer)}))}}},l=c,p=(s("fcc1"),s("2877")),u=Object(p["a"])(l,i,n,!1,null,"45be9a4e",null);t["default"]=u.exports},"6b54":function(e,t,s){"use strict";s("3846");var i=s("cb7c"),n=s("0bfb"),a=s("9e1e"),r="toString",o=/./[r],c=function(e){s("2aba")(RegExp.prototype,r,e,!0)};s("79e5")((function(){return"/a/b"!=o.call({source:"a",flags:"b"})}))?c((function(){var e=i(this);return"/".concat(e.source,"/","flags"in e?e.flags:!a&&e instanceof RegExp?n.call(e):void 0)})):o.name!=r&&c((function(){return o.call(this)}))},"7f7f":function(e,t,s){var i=s("86cc").f,n=Function.prototype,a=/^\s*function ([^ (]*)/,r="name";r in n||s("9e1e")&&i(n,r,{configurable:!0,get:function(){try{return(""+this).match(a)[1]}catch(e){return""}}})},"87b3":function(e,t,s){var i=Date.prototype,n="Invalid Date",a="toString",r=i[a],o=i.getTime;new Date(NaN)+""!=n&&s("2aba")(i,a,(function(){var e=o.call(this);return e===e?r.call(this):n}))},ddad:function(e,t,s){},fcc1:function(e,t,s){"use strict";s("ddad")}}]);