(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-46355209"],{"31b5":function(t,e){t.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAAq5JREFUWEfNl01IFGEYx3+PuxQERZegQwQhdPESCZ2KfZUwpIwiFCK/dieTLkFEQafs2KnASxrOepAOiZUoSkbt7EqnkjoUHfs4VBBdiiQy54lZHd1dVp39rLm9M8/7/H/zf59553mFnMvY2q9Kkwh7c5+VNBbeoEw7MbmcmUcyByauD1BOAA5KMpBgDRooDlpR6hTsZEwsf84KgInrNZQ+lIuOJbcCJg0cZuK6HbiJ0u0qR1KWzHiTVwAito4JNC4ou59Z8iNw5gICja3HgAkVLiSj0p8FYGxNeDecmDQUkLPgUGOrolx3LOkLBBCJa6u41K2ptE4NCDxPRGUqq84KBUgTF385uY5W1YHFMIOzHfK5JAeKf/n8Mwt24L8BMHfUBIVxesRZK7YoBzJ2yGAMwkMnKifzBRcF0DCs9bpISzB1kBATiW6ZKxtAUOEgcUU5ECRx0JiSAcyQdiN05RUUok5U3q8HUzJAxNYBgXN5RTL2+LJ+BUHt9eOaR3TbdLt8/2dFaIbUQmgXeKkfueL0yR8fpuQlCOKGies+Ua4q1AChTWE6Zjrlpzf3kK07ZmPy1c+z2hGVuR8wQzqJcHRZ6FEoxJknXfIt9wUqBtA8pZvnv3BP4Hi68RBSYZfTjy35lAlRMQBPxCvG+QVGRWlaFn3hhmlLdcq7ii+BL+CteQjuAwfT95TXrktbqkfepp3JqM6K9YRmRHfpb8YF9ovSj7AzEZO2qgF4Qo3DWqsulxQO1LicenpWPlQVwHe6ZUC3TPTKfNVqYKN9Y6UGGmy9q3AYYbcTlV8bTSz2uYlrHGXMiclk9hKsHs3OO5bcLlZg3T+h19aFSAj0JmIymAXgDZZPR17v9wplvKwQQgSoB7Yi7PF/21mn4zTEkhMdQG1ZAZaSzalwIxmVUT/3XzrRkTAYpS3TAAAAAElFTkSuQmCC"},3846:function(t,e,s){s("9e1e")&&"g"!=/./g.flags&&s("86cc").f(RegExp.prototype,"flags",{configurable:!0,get:s("0bfb")})},"5ae2":function(t,e,s){"use strict";s.r(e);var i=function(){var t=this,e=t._self._c;return e("div",{staticClass:"examination-wrap bg-f5"},[e("div",{staticClass:"examination pt20 pb20 clearfix"},[e("div",{staticClass:"examination-left fl"},[t.inAnswer?e("div",{staticClass:"bg-white pad15 mb20"},[e("div",{staticClass:"clearfix bor-bottom-eee pb15 pt10"},[e("h1",{staticClass:"fl fz26 fb"},[t._v(t._s(t.form.paperName))]),e("span",{staticClass:"tag fr"},[t._v("答题中")])]),e("div",{staticClass:"pt10"},[e("p",{staticClass:"color-666"},[t._v(t._s(t.form.paperDesc))])])]):e("div",{staticClass:"bg-white mb20 pr",staticStyle:{"z-index":"1"}},[e("div",{staticClass:"line"}),e("div",{staticClass:"clearfix pr pb15",staticStyle:{"background-color":"#2771ea","border-radius":"6px 6px 0px 0"}},[e("div",{staticClass:"fl score-header"},[e("div",{staticClass:"clearfix pt10"},[e("h1",{staticClass:"fl fz30 fb"},[t._v(t._s(t.overForm.paperName))]),e("span",{staticClass:"tag ml15",staticStyle:{color:"#2771ea",background:"#fff","line-height":"14px",height:"auto"}},[t._v("批阅完成")])]),e("div",{staticClass:"pt5"},[e("p",{staticStyle:{opacity:"0.5"}},[e("span",{staticClass:"mr20"},[t._v("答题人："+t._s(t.overForm.userId))]),e("span",[t._v("用时："+t._s(t.overForm.useTime)+"分钟")])])])]),e("div",{staticClass:"fr br4 tc score-wrap"},[e("span",[t._v(t._s(t.overForm.getPoint))]),e("p",[t._v("总分："+t._s(t.overForm.paperPoint))])])]),e("div",{staticClass:"pr",staticStyle:{"border-radius":"6px",overflow:"hidden",width:"100%",top:"-5px",background:"#fff"}},[e("el-table",{staticClass:"theme-table-01 exam-table",attrs:{data:t.tableData}},[e("el-table-column",{attrs:{"min-width":"60px",align:"center",prop:"questionType",label:"题型"}}),e("el-table-column",{attrs:{"min-width":"50px",align:"center",prop:"paperPoint",label:"总分"}}),e("el-table-column",{attrs:{"min-width":"50px",align:"center",prop:"getPoint",label:"得分"}}),e("el-table-column",{attrs:{"min-width":"50px",align:"center",prop:"totalQuestions",label:"总共"}}),e("el-table-column",{attrs:{"min-width":"50px",align:"center",prop:"totalCorrect",label:"答对"}}),e("el-table-column",{attrs:{"min-width":"50px",align:"center",prop:"totalError",label:"答错"}}),e("el-table-column",{attrs:{"min-width":"50px",align:"center",prop:"unanswered",label:"未答"}})],1)],1)]),e("div",{directives:[{name:"show",rawName:"v-show",value:t.checked&&t.errorQuestions.length>0||!t.checked&&t.questionList.length>0,expression:"(checked && errorQuestions.length > 0) || (!checked && questionList.length > 0)"}],staticClass:"bg-white pad15 mb20"},[e("div",{staticClass:"bor-bottom-eee pb20 pt10 pl10"},[e("span",{staticClass:"title-2"},[t._v("单选题")]),e("span",{staticClass:"color-999 ml30"},[t._v("共"+t._s(t.form.totalQuestions)+"题/共"+t._s(t.form.paperPoint)+"分")]),e("el-checkbox",{staticStyle:{float:"right"},attrs:{label:"显示中文"},model:{value:t.isShowCn,callback:function(e){t.isShowCn=e},expression:"isShowCn"}})],1),t.checked?e("div",{staticClass:"topic"},t._l(t.errorQuestions,(function(s,i){return e("div",{key:i,staticClass:"topic-item clearfix",attrs:{id:"ids-"+s.id}},[e("div",{staticClass:"topic-item-label fl"},[e("div",{staticClass:"inlineb tc"},[e("span",{staticStyle:{height:"18px","background-color":"#2771ea","border-radius":"3px","line-height":"18px",color:"#fff",padding:"0 6px"}},[t._v(t._s(i+1))])])]),e("div",{staticClass:"topic-item-block fl"},[e("div",{staticClass:"clearfix"},[e("pre",{staticClass:"fl",staticStyle:{width:"calc(100% - 60px)"}},[t._v(t._s(s.questionNameEn))]),e("div",{staticClass:"fr"},[e("span",{staticClass:"topic-item-score mr10"},[t._v(t._s(s.questionPoint)+"分")]),"1"===s.favorite?e("i",{staticClass:"iconfont icon-shoucang_shixin",staticStyle:{color:"#ff5930",cursor:"pointer"},on:{click:function(e){return t.handleSign(s)}}}):e("i",{staticClass:"iconfont icon-shoucang",staticStyle:{color:"#999",cursor:"pointer"},on:{click:function(e){return t.handleSign(s)}}})])]),t.isShowCn?e("p",{staticClass:"color-999 mt5"},[t._v(t._s(s.questionNameCn))]):t._e(),e("el-radio-group",{staticClass:"mt10",model:{value:s["selectAnswer"],callback:function(e){t.$set(s,"selectAnswer",e)},expression:"item['selectAnswer']"}},t._l(s.answerList,(function(s,i){return e("el-radio",{key:i,staticClass:"mb5",attrs:{label:s.answerId,disabled:!t.inAnswer}},[e("div",{staticStyle:{display:"inline-block","vertical-align":"top"}},[e("p",{staticClass:"color-666"},[t._v(t._s(s.answerContentEn))]),t.isShowCn?e("p",{staticClass:"color-999"},[t._v(t._s(s.answerContentCn))]):t._e()])])})),1),t.inAnswer?t._e():e("div",{staticClass:"bor-top-eee mt10"},[e("div",{staticStyle:{display:"flex","justify-content":"space-between","align-items":"center"}},[e("p",{staticClass:"color-999 pt15 pb15"},[t._v("正确答案："),e("span",{staticClass:"theme-color"},[t._v(t._s(t.getAnswerKey(s,s["correctAnswer"])))]),t._v("， 你的答案： "),e("span",{staticStyle:{color:"#ff5c2f"}},[t._v(t._s(t.getAnswerKey(s,s["selectAnswer"])?t.getAnswerKey(s,s["selectAnswer"]):"未作答"))])]),s.videoUrl?e("div",{staticStyle:{color:"#2771ea"}},[e("i",{staticClass:"iconfont icon-shipinbofang mr5"}),e("span",{staticStyle:{"margin-top":"5px",display:"inline-block",color:"#2771ea",cursor:"pointer"},on:{click:function(e){return t.handleViewVideo(s)}}},[t._v("查看讲解视频")])]):t._e()]),e("div",{staticClass:"pt10 pb10 pl15 pr20",staticStyle:{"background-color":"#f5f7fb",display:"flex"}},[e("div",{staticStyle:{width:"55px"}},[t._v("解析：")]),s.clarify?e("div",{staticClass:"color-999",class:!s.isSpread&&"clarify-spread",staticStyle:{flex:"1"},domProps:{innerHTML:t._s(decodeURI(s.clarify))}}):e("div",{staticClass:"color-999"},[t._v("暂无解析")])]),s.clarify?e("div",{staticClass:"tr pr20 pb10",staticStyle:{"background-color":"#f5f7fb"}},[e("span",{staticStyle:{color:"#2771ea",cursor:"pointer"},on:{click:function(e){return t.handleSpread(s)}}},[t._v(t._s(s.isSpread?"收起":"查看更多 >"))])]):t._e()])],1)])})),0):e("div",{staticClass:"topic"},t._l(t.questionList,(function(s,i){return e("div",{key:i,staticClass:"topic-item clearfix",attrs:{id:"ids-"+s.questionId}},[e("div",{staticClass:"topic-item-label fl"},[e("div",{staticClass:"inlineb tc"},[e("span",{staticStyle:{height:"18px","background-color":"#2771ea","border-radius":"3px","line-height":"18px",color:"#fff",padding:"0 6px"}},[t._v(t._s(i+1))])])]),e("div",{staticClass:"topic-item-block fl"},[e("div",{staticClass:"clearfix"},[e("pre",{staticClass:"fl",staticStyle:{width:"calc(100% - 60px)"}},[t._v(t._s(s.questionNameEn))]),e("div",{staticClass:"fr"},[e("span",{staticClass:"topic-item-score mr10"},[t._v(t._s(s.questionPoint)+"分")]),"1"===s.favorite?e("i",{staticClass:"iconfont icon-shoucang_shixin",staticStyle:{color:"#ff5930",cursor:"pointer"},on:{click:function(e){return t.handleSign(s)}}}):e("i",{staticClass:"iconfont icon-shoucang",staticStyle:{color:"#999",cursor:"pointer"},on:{click:function(e){return t.handleSign(s)}}})])]),t.isShowCn?e("p",{staticClass:"color-999 mt5"},[t._v(t._s(s.questionNameCn))]):t._e(),e("el-radio-group",{staticClass:"mt10",model:{value:s.selectAnswer,callback:function(e){t.$set(s,"selectAnswer",e)},expression:"item.selectAnswer"}},t._l(s.answerList,(function(s,i){return e("el-radio",{key:i,staticClass:"mb5",attrs:{label:s.answerId,disabled:!t.inAnswer}},[e("div",{staticStyle:{display:"inline-block","vertical-align":"top"}},[e("p",{staticClass:"color-666"},[t._v(t._s(s.answerContentEn))]),t.isShowCn?e("p",{staticClass:"color-999"},[t._v(t._s(s.answerContentCn))]):t._e()])])})),1),t.inAnswer?t._e():e("div",{staticClass:"bor-top-eee mt10"},[e("div",{staticStyle:{display:"flex","justify-content":"space-between","align-items":"center"}},[e("p",{staticClass:"color-999 pt15 pb15"},[t._v("正确答案："),e("span",{staticClass:"theme-color"},[t._v(t._s(t.getAnswerKey(s,s["correctAnswer"])))]),t._v("， 你的答案： "),e("span",{staticStyle:{color:"#ff5c2f"}},[t._v(t._s(t.getAnswerKey(s,s["selectAnswer"])?t.getAnswerKey(s,s["selectAnswer"]):"未作答"))])]),s.videoUrl?e("div",{staticStyle:{color:"#2771ea"}},[e("i",{staticClass:"iconfont icon-shipinbofang mr5"}),e("span",{staticStyle:{"margin-top":"5px",display:"inline-block",color:"#2771ea",cursor:"pointer"},on:{click:function(e){return t.handleViewVideo(s)}}},[t._v("查看讲解视频")])]):t._e()]),e("div",{staticClass:"pt10 pb10 pl15 pr20",staticStyle:{"background-color":"#f5f7fb",display:"flex"}},[e("div",{staticStyle:{width:"55px"}},[t._v("解析：")]),s.clarify?e("div",{staticClass:"color-999",class:!s.isSpread&&"clarify-spread",staticStyle:{flex:"1"},domProps:{innerHTML:t._s(decodeURI(s.clarify))}}):e("div",{staticClass:"color-999"},[t._v("暂无解析")])]),s.clarify?e("div",{staticClass:"tr pr20 pb10",staticStyle:{"background-color":"#f5f7fb"}},[e("span",{staticStyle:{color:"#2771ea",cursor:"pointer"},on:{click:function(e){return t.handleSpread(s)}}},[t._v(t._s(s.isSpread?"收起":"查看更多 >"))])]):t._e()])],1)])})),0)])]),e("div",{staticClass:"examination-right fr"},[e("div",{staticClass:"examination-right-fixed"},[t.inAnswer&&t.remainingTime?e("div",{staticClass:"pt5 pb5 pl15 pr15 color-999 bg-white mb20"},[t._v("倒计时："+t._s(t.remainingTime))]):t._e(),e("div",{staticClass:"pad15 br4 bg-white",attrs:{id:"answerSheet"}},[e("div",{staticClass:"bor-bottom-eee pb10"},[e("span",{staticClass:"title-2 fb"},[t._v("答题卡")]),t.inAnswer?e("span",{staticClass:"fr theme-color",staticStyle:{cursor:"pointer"},on:{click:function(e){t.isShowAnswerSheet=!t.isShowAnswerSheet}}},[t._v("收起/展开答题卡>>")]):t._e()]),t.inAnswer?t._e():e("div",{staticClass:"ibs-card-body__explain pt10"},[e("span",{staticClass:"ibs-success-bg"}),e("small",[t._v("正确")]),e("span",{staticClass:"ibs-danger-bg"}),e("small",[t._v("错误")]),e("span",{staticClass:"ibs-tip-bg"}),e("small",[t._v("未答")])]),e("div",{directives:[{name:"show",rawName:"v-show",value:!t.isShowAnswerSheet,expression:"!isShowAnswerSheet"}],staticClass:"answer-sheet-wrap pt20"},[t.inAnswer?e("div",{staticClass:"answer-sheet"},t._l(t.questionList,(function(s,i){return e("a",{key:i,class:[s.selectAnswer?"active":""],on:{click:function(e){return t.goAnchor("ids-"+s.questionId)}}},[t._v(t._s(i+1)+"\n                  "),s.sign?e("i",{staticClass:"el-icon-collection-tag"}):t._e()])})),0):e("div",{staticClass:"answer-sheet"},t._l(t.questionList,(function(s,i){return e("a",{key:i,class:[t.getStatusClass(s)],on:{click:function(e){return t.goAnchor("ids-"+s.questionId)}}},[t._v(t._s(i+1)+"\n                  "),s.sign?e("i",{staticClass:"el-icon-collection-tag"}):t._e()])})),0)]),t.inAnswer?e("div",{staticClass:"pad15 tc"},[e("button",{staticClass:"theme-btn",on:{click:t.handleSubmit}},[t._v("立即提交")]),e("button",{staticClass:"theme-btn",staticStyle:{"margin-left":"15px"},on:{click:function(e){return t.$router.go(-1)}}},[t._v("返 回")])]):[e("el-checkbox",{staticClass:"mt15",on:{change:t.handleErrorAnswer},model:{value:t.checked,callback:function(e){t.checked=e},expression:"checked"}},[t._v("只看错题")]),e("div",{staticClass:"pt15 pb15 tc"},[e("button",{staticClass:"theme-btn",staticStyle:{"border-radius":"6px",width:"100%",height:"38px"},on:{click:t.handleBack}},[t._v("返回题目列表")])])]],2)])])]),e("div",{staticClass:"go-answer-sheet",on:{click:function(e){return t.goAnchor("answerSheet")}}},[e("img",{attrs:{src:s("31b5"),alt:""}})]),t.dialogVisible?e("el-dialog",{attrs:{visible:t.dialogVisible,title:"视频播放",width:"800px","append-to-body":"","before-close":t.handleClose},on:{"update:visible":function(e){t.dialogVisible=e}}},[e("div",{staticStyle:{width:"100%",height:"400px",overflow:"hidden"}},[t.dialogVisible?e("video",{staticStyle:{width:"100%",height:"100%"},attrs:{id:"player-container",preload:"auto",width:"100%",height:"400",playsinline:"","webkit-playsinline":"","x5-playsinline":""}}):t._e()])]):t._e()],1)},a=[],n=(s("6b54"),s("87b3"),s("ac6a"),s("456d"),s("28a5"),s("6d67"),s("cabd")),r={name:"Examination",components:{},data:function(){return{inAnswer:!0,isShowAnswerSheet:!1,radio:"",checked:"",tableData:[{name1:"单选题",name11:"100",name22:"数学",name2:"0",name3:"40道",name4:"0道",name5:"0道",name6:"40道"}],questions:[],questionList:[],errorQuestions:[],fixed:!1,form:{},remainingTime:"",remainingTimeS:"",timer:null,overForm:{},inputValue:"",dialogVisible:!1,isShowCn:!1,player:null}},created:function(){var t=this;this.init(),window.onscroll=function(){var e=document.documentElement.scrollTop||document.body.scrollTop;t.fixed=e>160}},methods:{handleViewVideo:function(t){var e=this;t.videoUrl?(this.dialogVisible=!0,setTimeout((function(){e.player=TCPlayer("player-container",{fileID:t.videoUrl,appID:"1309193717",psign:t.videoSign})}),1e3)):this.$message.error("视频不存在")},handleClose:function(){this.dialogVisible=!1,this.player&&this.player.dispose(),this.player=null},handleSpread:function(t){t.isSpread=!t.isSpread,this.$forceUpdate()},init:function(){var t=this;Object(n["c"])({paperId:this.$route.query.id||1,practiseType:this.$route.query.practiseType||3,userId:this.$store.state.user.userId,paperType:"CDMP"}).then((function(e){t.form=e,t.questionList=e.questionList,"0"!==e.paperTime&&t.countDown()}))},handleSearch:function(){Object(n["i"])({userId:this.$store.state.user.userId,keyWord:this.inputValue}).then((function(t){}))},getAnswerKey:function(t,e){var s="";return t.answerList.map((function(t){t.answerId===e&&(s=t.answerContentEn.split(".")[0])})),s},handleSign:function(t){var e=this,s=t.favorite;s="1"===t.favorite?"0":"1",Object(n["b"])({questionId:t.questionId,favStatus:s,userId:this.$store.state.user.userId}).then((function(i){1===i.status?(t.favorite=s,"1"===s?e.$message.success("收藏成功！"):e.$message.success("取消收藏成功！")):e.$message.error(i.msg),e.$forceUpdate()}))},countDown:function(){var t=this,e=60*parseInt(this.form.paperTime);this.remainingTime=this.formatSeconds(e),this.timer=setInterval((function(){e--,t.remainingTime=t.formatSeconds(e),t.remainingTimeS=e,e<=0&&(t.PostExamPage(),clearInterval(t.timer))}),1e3)},formatSeconds:function(t){var e=parseInt(t),s=0,i=0,a=0;e>60&&(s=parseInt(e/60),e=parseInt(e%60),s>60&&(i=parseInt(s/60),s=parseInt(s%60),i>24&&(a=parseInt(i/24),i=parseInt(i%24))));var n="";return e>0&&(n=parseInt(e)+"秒"),s>0&&(n=parseInt(s)+"分"+n),i>0&&(n=parseInt(i)+"小时"+n),a>0&&(n=parseInt(a)+"天"+n),n},getStatusClass:function(t){var e="3";e=t["selectAnswer"]?t["selectAnswer"]===t["correctAnswer"]?"1":"2":"4";var s="ibs-success-bg";switch(e){case"1":s="ibs-success-bg";break;case"2":s="ibs-danger-bg";break;case"3":s="ibs-warning-bg";break;case"4":s="ibs-tip-bg";break}return s},goAnchor:function(t){document.querySelector("#"+t).scrollIntoView({behavior:"smooth"})},handleErrorAnswer:function(t){},handleAnswer:function(){this.inAnswer=!0,this.init()},handleBack:function(){this.$router.push({path:"/personal/exercise"})},handleSave:function(){this.$confirm("确认要保存进度，下次继续吗？","提示",{confirmButtonText:"确定",cancelButtonText:"继续答题",type:"warning"}).then((function(){}))},handleSubmit:function(){var t=this,e=!1;this.questionList.map((function(t){t.selectAnswer||(e=!0)})),e?this.$alert("还有未完成的题目，请检查后再提交","提示"):this.$confirm("确定要提交吗？","提示",{confirmButtonText:"确定",cancelButtonText:"继续答题",type:"warning"}).then((function(){t.PostExamPage()}))},PostExamPage:function(){var t=this,e={paperId:this.form.paperId,userId:this.form.userId,paperType:"CDMP",paperName:this.form.paperName,practiseType:this.$route.query.practiseType,paperDesc:this.form.paperDesc,totalQuestions:this.form.totalQuestions,useTime:"0",paperTime:this.form.paperTime,paperPoint:this.form.paperPoint,favoriteIdList:"",questionIdList:"",answerList:[],jsonTopicList:this.form.jsonTopicList},s={},i=[],a=[];this.questionList.map((function(t){t.selectAnswer&&(s[t.questionId]=t.selectAnswer),"1"===t.favorite&&i.push(t.questionId),a.push(t.questionId)})),Object.keys(s).length&&(e.answerList=[s]),i.length&&(e.favoriteIdList=i.toString()),a.length&&(e.questionIdList=a.toString()),e.useTime=Math.ceil((60*parseInt(this.form.paperTime)-this.remainingTimeS)/60).toString(),Object(n["g"])(e).then((function(e){t.inAnswer=!1,t.overForm=e,t.questionList=e.questionList,t.errorQuestions=[],t.questionList.map((function(e){e["selectAnswer"]&&e["selectAnswer"]!==e["correctAnswer"]&&t.errorQuestions.push(e)})),t.tableData=[{questionType:"单选题",category:"英语",paperPoint:t.overForm.paperPoint,getPoint:t.overForm.getPoint,totalQuestions:t.overForm.totalQuestions,totalCorrect:t.overForm.getPoint,totalError:t.overForm.totalQuestions-t.overForm.getPoint,unanswered:t.overForm.totalQuestions-Object.keys(s).length}],clearInterval(t.timer)}))}}},o=r,c=(s("acf2"),s("2877")),l=Object(c["a"])(o,i,a,!1,null,"93b50484",null);e["default"]=l.exports},"6b54":function(t,e,s){"use strict";s("3846");var i=s("cb7c"),a=s("0bfb"),n=s("9e1e"),r="toString",o=/./[r],c=function(t){s("2aba")(RegExp.prototype,r,t,!0)};s("79e5")((function(){return"/a/b"!=o.call({source:"a",flags:"b"})}))?c((function(){var t=i(this);return"/".concat(t.source,"/","flags"in t?t.flags:!n&&t instanceof RegExp?a.call(t):void 0)})):o.name!=r&&c((function(){return o.call(this)}))},"87b3":function(t,e,s){var i=Date.prototype,a="Invalid Date",n="toString",r=i[n],o=i.getTime;new Date(NaN)+""!=a&&s("2aba")(i,n,(function(){var t=o.call(this);return t===t?r.call(this):a}))},acf2:function(t,e,s){"use strict";s("e5d2")},e5d2:function(t,e,s){}}]);