(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-80fa8592"],{"0bbb":function(e,t,i){"use strict";i("971a")},"31b5":function(e,t){e.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAAXNSR0IArs4c6QAAAq5JREFUWEfNl01IFGEYx3+PuxQERZegQwQhdPESCZ2KfZUwpIwiFCK/dieTLkFEQafs2KnASxrOepAOiZUoSkbt7EqnkjoUHfs4VBBdiiQy54lZHd1dVp39rLm9M8/7/H/zf59553mFnMvY2q9Kkwh7c5+VNBbeoEw7MbmcmUcyByauD1BOAA5KMpBgDRooDlpR6hTsZEwsf84KgInrNZQ+lIuOJbcCJg0cZuK6HbiJ0u0qR1KWzHiTVwAito4JNC4ou59Z8iNw5gICja3HgAkVLiSj0p8FYGxNeDecmDQUkLPgUGOrolx3LOkLBBCJa6u41K2ptE4NCDxPRGUqq84KBUgTF385uY5W1YHFMIOzHfK5JAeKf/n8Mwt24L8BMHfUBIVxesRZK7YoBzJ2yGAMwkMnKifzBRcF0DCs9bpISzB1kBATiW6ZKxtAUOEgcUU5ECRx0JiSAcyQdiN05RUUok5U3q8HUzJAxNYBgXN5RTL2+LJ+BUHt9eOaR3TbdLt8/2dFaIbUQmgXeKkfueL0yR8fpuQlCOKGies+Ua4q1AChTWE6Zjrlpzf3kK07ZmPy1c+z2hGVuR8wQzqJcHRZ6FEoxJknXfIt9wUqBtA8pZvnv3BP4Hi68RBSYZfTjy35lAlRMQBPxCvG+QVGRWlaFn3hhmlLdcq7ii+BL+CteQjuAwfT95TXrktbqkfepp3JqM6K9YRmRHfpb8YF9ovSj7AzEZO2qgF4Qo3DWqsulxQO1LicenpWPlQVwHe6ZUC3TPTKfNVqYKN9Y6UGGmy9q3AYYbcTlV8bTSz2uYlrHGXMiclk9hKsHs3OO5bcLlZg3T+h19aFSAj0JmIymAXgDZZPR17v9wplvKwQQgSoB7Yi7PF/21mn4zTEkhMdQG1ZAZaSzalwIxmVUT/3XzrRkTAYpS3TAAAAAElFTkSuQmCC"},"5b14":function(e,t,i){"use strict";i.r(t);i("57e7");var s=function(){var e=this,t=e._self._c;return t("div",{staticClass:"examination-wrap bg-f5"},[t("div",{staticClass:"examination pt20 pb20 clearfix"},[t("div",{staticClass:"examination-left fl"},[t("div",{staticClass:"search-wrap"},[t("div",{staticClass:"search"},[t("i",{staticClass:"iconfont icon-sousuo",staticStyle:{color:"#c8c9cc",margin:"0 10px 0 16px"}}),t("el-input",{attrs:{type:"text",placeholder:"输入题目/关键词",clearable:""},on:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleSearch.apply(null,arguments)},clear:e.handleSearch},model:{value:e.inputValue,callback:function(t){e.inputValue=t},expression:"inputValue"}}),t("span",{staticClass:"search-btn",on:{click:e.handleSearch}},[e._v("搜索")])],1),t("el-button",{staticStyle:{"margin-left":"15px"},attrs:{type:"primary",size:"small",icon:"el-icon-back"},on:{click:function(t){return e.$router.go(-1)}}},[e._v("返 回")]),t("el-checkbox",{staticStyle:{"margin-left":"15px"},attrs:{label:"显示序号"},model:{value:e.isShowNo,callback:function(t){e.isShowNo=t},expression:"isShowNo"}})],1),t("div",{directives:[{name:"show",rawName:"v-show",value:e.checked&&e.errorQuestions.length>0||!e.checked&&e.questionList.length>0,expression:"(checked && errorQuestions.length > 0) || (!checked && questionList.length > 0)"}],staticClass:"bg-white pad15 mb20"},[e._m(0),t("div",{staticClass:"topic"},e._l(e.questionList,(function(i,s){return t("div",{key:s,staticClass:"topic-item clearfix",attrs:{id:"ids-"+i.questionId}},[t("div",{staticClass:"topic-item-label fl"},[t("div",{staticClass:"inlineb tc"},[t("span",{staticStyle:{height:"18px","background-color":"#2771ea","border-radius":"3px","line-height":"18px",color:"#fff",padding:"0 6px"}},[e._v(e._s(s+1))])])]),t("div",{staticClass:"topic-item-block fl"},[t("div",{staticClass:"clearfix"},[t("pre",{staticClass:"fl",staticStyle:{width:"calc(100% - 60px)"}},[e._v(e._s(i.questionNameEn))]),t("div",{staticClass:"fr"},[t("span",{staticClass:"topic-item-score mr10"},[e._v(e._s(i.questionPoint)+"分")])])]),t("p",{staticClass:"color-999 mt5"},[e._v(e._s(i.questionNameCn))]),e._l(i.answerList,(function(s,a){return t("div",{key:a,staticClass:"mt10",staticStyle:{"line-height":"26px"}},[t("p",{staticClass:"color-666",class:i.correctAnswer===s.answerId?"green-bg":""},[e._v(e._s(e.isShowNo?s.answerContentEn:e.getAnswerContent(s.answerContentEn)))]),t("p",{staticClass:"color-999",staticStyle:{"padding-left":"17px"}},[e._v(e._s(s.answerContentCn))])])})),t("div",{staticClass:"mt10",class:e.isShowNo&&"bor-top-eee"},[t("div",{staticStyle:{display:"flex","justify-content":"space-between","align-items":"center"}},[e.isShowNo?t("p",{staticClass:"color-999 pt15 pb15"},[e._v("正确答案："),t("span",{staticClass:"theme-color"},[e._v(e._s(e.getAnswerKey(i)))])]):e._e(),i.videoUrl?t("div",{staticStyle:{color:"#2771ea"}},[t("i",{staticClass:"iconfont icon-shipinbofang mr5"}),t("span",{staticStyle:{"margin-top":"5px",display:"inline-block",color:"#2771ea",cursor:"pointer"},on:{click:function(t){return e.handleViewVideo(i)}}},[e._v("查看讲解视频")])]):e._e()]),t("div",{staticClass:"pt10 pb10 pl15 pr20 ck-content",staticStyle:{"background-color":"#f5f7fb",display:"flex"}},[t("div",{staticStyle:{width:"55px"}},[e._v("解析：")]),i.clarify?t("div",{staticClass:"color-999",class:!i.isSpread&&"clarify-spread",staticStyle:{flex:"1"},domProps:{innerHTML:e._s(decodeURI(i.clarify))}}):t("div",{staticClass:"color-999"},[e._v("暂无解析")])]),i.clarify?t("div",{staticClass:"tr pr20 pb10",staticStyle:{"background-color":"#f5f7fb"}},[t("span",{staticStyle:{color:"#2771ea",cursor:"pointer"},on:{click:function(t){return e.handleSpread(i)}}},[e._v(e._s(i.isSpread?"收起":"查看更多 >"))])]):e._e()])],2)])})),0)]),0===e.questionList.length&&e.isSearch?t("el-empty",{attrs:{description:"未能查询到您需要的试题"}}):e._e()],1)]),t("div",{staticClass:"go-answer-sheet",on:{click:function(t){return e.goAnchor("answerSheet")}}},[t("img",{attrs:{src:i("31b5"),alt:""}})]),e.dialogVisible?t("el-dialog",{attrs:{visible:e.dialogVisible,title:"视频播放",width:"800px","append-to-body":"","before-close":e.handleClose},on:{"update:visible":function(t){e.dialogVisible=t}}},[t("div",{staticStyle:{width:"100%",height:"400px",overflow:"hidden"}},[e.dialogVisible?t("video",{staticStyle:{width:"100%",height:"100%"},attrs:{id:"player-container",preload:"auto",width:"100%",height:"400",playsinline:"","webkit-playsinline":"","x5-playsinline":""}}):e._e()])]):e._e()],1)},a=[function(){var e=this,t=e._self._c;return t("div",{staticClass:"bor-bottom-eee pb20 pt10 pl10"},[t("span",{staticClass:"title-2"},[e._v("单选题")])])}],n=(i("28a5"),i("6d67"),i("7618")),o=i("cabd"),l={name:"Search",components:{},data:function(){return{inAnswer:!0,isShowAnswerSheet:!1,radio:"",checked:"",tableData:[{name1:"单选题",name11:"100",name22:"数学",name2:"0",name3:"40道",name4:"0道",name5:"0道",name6:"40道"}],questions:[],questionList:[],errorQuestions:[],fixed:!1,form:{},remainingTime:"",remainingTimeS:"",timer:null,overForm:{},inputValue:"",dialogVisible:!1,player:null,isSearch:!1,isShowNo:!1}},created:function(){var e=this;window.onscroll=function(){var t=document.documentElement.scrollTop||document.body.scrollTop;e.fixed=t>160}},methods:{getAnswerContent:function(e){return e.substring(2)},handleViewVideo:function(e){var t=this;console.log(e),e.videoUrl?(this.dialogVisible=!0,setTimeout((function(){t.player=TCPlayer("player-container",{fileID:e.videoUrl,appID:"1309193717",psign:e.videoSign})}),1e3)):this.$message.error("视频不存在")},handleClose:function(){this.dialogVisible=!1,this.player&&this.player.dispose(),this.player=null},handleSpread:function(e){e.isSpread=!e.isSpread,this.$forceUpdate()},handleSearch:function(){var e=this;Object(o["i"])({userId:this.$store.state.user.userId,keyWord:this.inputValue}).then((function(t){e.isSearch=!0,"object"===Object(n["a"])(t)?e.questionList=t.questionList:e.questionList=[]}))},getAnswerKey:function(e){var t="",i=e.correctAnswer;return e.answerList.map((function(e){e.answerId===i&&(t=e.answerContentEn.split(".")[0])})),t},handleSign:function(e){"1"===e.favorite?e.favorite="0":e.favorite="1",this.$forceUpdate()},goAnchor:function(e){document.querySelector("#"+e).scrollIntoView({behavior:"smooth"})}}},c=l,r=(i("0bbb"),i("2877")),d=Object(r["a"])(c,s,a,!1,null,"59f7e588",null);t["default"]=d.exports},"971a":function(e,t,i){}}]);