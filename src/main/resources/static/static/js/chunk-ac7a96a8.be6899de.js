(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-ac7a96a8"],{"0c36":function(e,t,a){"use strict";a("e529")},3846:function(e,t,a){a("9e1e")&&"g"!=/./g.flags&&a("86cc").f(RegExp.prototype,"flags",{configurable:!0,get:a("0bfb")})},"475d":function(e,t){},"6b54":function(e,t,a){"use strict";a("3846");var r=a("cb7c"),n=a("0bfb"),i=a("9e1e"),s="toString",o=/./[s],l=function(e){a("2aba")(RegExp.prototype,s,e,!0)};a("79e5")((function(){return"/a/b"!=o.call({source:"a",flags:"b"})}))?l((function(){var e=r(this);return"/".concat(e.source,"/","flags"in e?e.flags:!i&&e instanceof RegExp?n.call(e):void 0)})):o.name!=s&&l((function(){return o.call(this)}))},"759f":function(e,t,a){"use strict";var r=a("5ca1"),n=a("0a49")(3);r(r.P+r.F*!a("2f21")([].some,!0),"Array",{some:function(e){return n(this,e,arguments[1])}})},"87b3":function(e,t,a){var r=Date.prototype,n="Invalid Date",i="toString",s=r[i],o=r.getTime;new Date(NaN)+""!=n&&a("2aba")(r,i,(function(){var e=o.call(this);return e===e?s.call(this):n}))},a481:function(e,t,a){"use strict";var r=a("cb7c"),n=a("4bf8"),i=a("9def"),s=a("4588"),o=a("0390"),l=a("5f1b"),u=Math.max,c=Math.min,p=Math.floor,m=/\$([$&`']|\d\d?|<[^>]*>)/g,d=/\$([$&`']|\d\d?)/g,f=function(e){return void 0===e?e:String(e)};a("214f")("replace",2,(function(e,t,a,h){return[function(r,n){var i=e(this),s=void 0==r?void 0:r[t];return void 0!==s?s.call(r,i,n):a.call(String(i),r,n)},function(e,t){var n=h(a,e,this,t);if(n.done)return n.value;var p=r(e),m=String(this),d="function"===typeof t;d||(t=String(t));var g=p.global;if(g){var y=p.unicode;p.lastIndex=0}var v=[];while(1){var q=l(p,m);if(null===q)break;if(v.push(q),!g)break;var x=String(q[0]);""===x&&(p.lastIndex=o(m,i(p.lastIndex),y))}for(var w="",S=0,I=0;I<v.length;I++){q=v[I];for(var N=String(q[0]),k=u(c(s(q.index),m.length),0),C=[],P=1;P<q.length;P++)C.push(f(q[P]));var E=q.groups;if(d){var _=[N].concat(C,k,m);void 0!==E&&_.push(E);var $=String(t.apply(void 0,_))}else $=b(N,m,k,C,E,t);k>=S&&(w+=m.slice(S,k)+$,S=k+N.length)}return w+m.slice(S)}];function b(e,t,r,i,s,o){var l=r+e.length,u=i.length,c=d;return void 0!==s&&(s=n(s),c=m),a.call(o,c,(function(a,n){var o;switch(n.charAt(0)){case"$":return"$";case"&":return e;case"`":return t.slice(0,r);case"'":return t.slice(l);case"<":o=s[n.slice(1,-1)];break;default:var c=+n;if(0===c)return a;if(c>u){var m=p(c/10);return 0===m?a:m<=u?void 0===i[m-1]?n.charAt(1):i[m-1]+n.charAt(1):a}o=i[c-1]}return void 0===o?"":o}))}}))},bc79:function(e,t,a){"use strict";a.r(t);a("57e7");var r=function(){var e=this,t=e._self._c;return t("div",{},[t("div",{directives:[{name:"show",rawName:"v-show",value:!e.isEdit,expression:"!isEdit"}],staticClass:"card"},[t("h2",{staticClass:"personal-title",staticStyle:{display:"flex","align-items":"center","justify-content":"space-between"}},[e._v("\n      题目列表\n      "),t("el-button",{attrs:{type:"text",icon:"el-icon-circle-plus-outline"},on:{click:e.handleAdd}},[e._v("新增题目")])],1),t("el-form",{directives:[{name:"show",rawName:"v-show",value:e.showSearch,expression:"showSearch"}],ref:"queryForm",staticStyle:{padding:"15px 20px 0"},attrs:{model:e.queryParams,inline:!0,"label-width":"80px"}},[t("el-form-item",{attrs:{label:"题目名称:",prop:"questionName"}},[t("el-input",{attrs:{placeholder:"请输入题目名称",clearable:""},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery.apply(null,arguments)}},model:{value:e.queryParams.questionName,callback:function(t){e.$set(e.queryParams,"questionName",t)},expression:"queryParams.questionName"}})],1),t("el-form-item",{attrs:{label:"题目来源:",prop:"source"}},[t("el-select",{attrs:{placeholder:"请选择题目来源",clearable:""},model:{value:e.queryParams.source,callback:function(t){e.$set(e.queryParams,"source",t)},expression:"queryParams.source"}},e._l(e.sourceOptions,(function(e,a){return t("el-option",{key:a,attrs:{label:e.source,value:e.source}})})),1)],1),t("el-form-item",{attrs:{label:"所属课程:",prop:"examId"}},[t("el-select",{attrs:{placeholder:"请选择所属课程",clearable:""},on:{change:e.handleQueryExamChange},model:{value:e.queryParams.examId,callback:function(t){e.$set(e.queryParams,"examId",t)},expression:"queryParams.examId"}},e._l(e.examList,(function(e){return t("el-option",{key:e.examId,attrs:{label:e.examName,value:e.examId}})})),1)],1),t("el-form-item",{attrs:{label:"所属考点:",prop:"paperId"}},[t("el-select",{attrs:{placeholder:"请选择所属考点",clearable:""},model:{value:e.queryParams.paperId,callback:function(t){e.$set(e.queryParams,"paperId",t)},expression:"queryParams.paperId"}},e._l(e.queryPaperList,(function(e){return t("el-option",{key:e.paperId,attrs:{label:e.paperName,value:e.paperId}})})),1)],1),t("el-form-item",{attrs:{label:"问题类别:",prop:"questionSubject"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择问题类别",clearable:""},model:{value:e.queryParams.questionSubject,callback:function(t){e.$set(e.queryParams,"questionSubject",t)},expression:"queryParams.questionSubject"}},e._l(e.questionSubjectOptions,(function(e,a){return t("el-option",{key:a,attrs:{label:e.subjectName,value:e.subjectId}})})),1)],1),t("el-form-item",{attrs:{label:"题目ID:",prop:"id"}},[t("el-input",{attrs:{placeholder:"请输入题目ID",clearable:""},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery.apply(null,arguments)}},model:{value:e.queryParams.id,callback:function(t){e.$set(e.queryParams,"id",t)},expression:"queryParams.id"}})],1),t("el-form-item",{attrs:{label:"题目状态:",prop:"status"}},[t("el-radio",{staticStyle:{"margin-right":"20px"},attrs:{label:"1"},model:{value:e.queryParams.status,callback:function(t){e.$set(e.queryParams,"status",t)},expression:"queryParams.status"}},[e._v("有效")]),t("el-radio",{attrs:{label:"0"},model:{value:e.queryParams.status,callback:function(t){e.$set(e.queryParams,"status",t)},expression:"queryParams.status"}},[e._v("停用")])],1),t("el-form-item",{staticStyle:{"margin-left":"60px"}},[t("el-button",{attrs:{type:"primary",size:"small",icon:"el-icon-search"},on:{click:e.handleQuery}},[e._v("搜索")]),t("el-button",{attrs:{icon:"el-icon-refresh",size:"small"},on:{click:e.resetQuery}},[e._v("重置")])],1)],1)],1),t("div",{directives:[{name:"show",rawName:"v-show",value:!e.isEdit,expression:"!isEdit"}],staticClass:"card",staticStyle:{height:"calc(100vh - 352px)","margin-bottom":"0"}},[t("div",{staticStyle:{height:"calc(100% - 80px)"}},[t("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{data:e.list,height:"100%"},on:{"selection-change":e.handleSelectionChange}},[t("el-table-column",{attrs:{label:"ID",align:"center",prop:"id",width:"60px"}}),t("el-table-column",{attrs:{label:"所属课程",align:"center",prop:"examId",width:"120px"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("span",{staticStyle:{"font-size":"16px"}},[e._v(e._s(e.getExamName(a.row.examId)))])]}}])}),t("el-table-column",{attrs:{label:"题型",align:"center",prop:"questionType",width:"60px"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("span",{staticStyle:{"font-size":"16px"}},[e._v(e._s(e.getQuestionTypeName(a.row.questionType)))])]}}])}),t("el-table-column",{attrs:{label:"考点",align:"center",prop:"examId",width:"140px"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("span",{staticStyle:{"font-size":"16px"}},[e._v(e._s(e.getPaperName(a.row.paperId)))])]}}])}),t("el-table-column",{attrs:{label:"类别",align:"center",prop:"questionSubject",width:"80px"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("span",{staticStyle:{"font-size":"16px"}},[e._v(e._s(e.getQuestionSubjectName(a.row.questionSubject)))])]}}])}),t("el-table-column",{attrs:{label:"题目名称",align:"left",prop:"questionNameCn"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("span",{staticStyle:{"font-size":"16px"}},[e._v(e._s(a.row.questionNameCn))])]}}])}),t("el-table-column",{attrs:{label:"题目来源",align:"center",prop:"source",width:"80px","show-overflow-tooltip":!0}}),t("el-table-column",{attrs:{label:"状态",align:"center",prop:"status",width:"60px"},scopedSlots:e._u([{key:"default",fn:function(a){return["1"===a.row.status?t("span",[e._v("有效")]):e._e(),"0"===a.row.status?t("span",[e._v("停用")]):e._e()]}}])}),t("el-table-column",{attrs:{label:"操作",align:"center",width:"120px"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("el-button",{staticStyle:{"font-size":"16px"},attrs:{size:"mini",type:"text"},on:{click:function(t){return e.handleUpdate(a.row)}}},[e._v("修改")]),t("el-button",{staticStyle:{"font-size":"16px"},attrs:{size:"mini",type:"text"},on:{click:function(t){return e.changeTopicStatus(a.row)}}},[e._v(e._s("1"===a.row.status?"停用":"启用"))])]}}])})],1)],1),t("pagination",{attrs:{total:e.total,page:e.queryParams.page,limit:e.queryParams.pageSize},on:{"update:page":function(t){return e.$set(e.queryParams,"page",t)},"update:limit":function(t){return e.$set(e.queryParams,"pageSize",t)},pagination:e.getList}})],1),t("div",{directives:[{name:"show",rawName:"v-show",value:e.isEdit,expression:"isEdit"}],staticClass:"card",staticStyle:{"margin-bottom":"0"}},[t("h2",{staticClass:"personal-title",staticStyle:{display:"flex","align-items":"center","justify-content":"space-between"}},[t("span",[e._v(e._s(e.title))]),t("el-button",{attrs:{type:"primary",size:"small",icon:"el-icon-back"},on:{click:e.cancel}},[e._v("返 回")])],1),t("el-form",{ref:"form",staticClass:"edit-form",staticStyle:{padding:"10px 30px 30px"},attrs:{model:e.form,rules:e.rules,"label-width":"140px"}},[t("el-tabs",{model:{value:e.activeName,callback:function(t){e.activeName=t},expression:"activeName"}},[t("el-tab-pane",{attrs:{label:"关键信息",name:"1"}},[t("el-row",[t("el-col",{attrs:{span:12}},[t("el-form-item",{attrs:{label:"所属课程:",prop:"examId"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择所属课程",clearable:""},on:{change:e.handleExamChange},model:{value:e.form.examId,callback:function(t){e.$set(e.form,"examId",t)},expression:"form.examId"}},e._l(e.examList,(function(e,a){return t("el-option",{key:a,attrs:{label:e.examName,value:e.examId}})})),1)],1)],1),t("el-col",{attrs:{span:12}},[t("el-form-item",{attrs:{label:"所属考点:",prop:"paperId"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择所属考点",clearable:""},model:{value:e.form.paperId,callback:function(t){e.$set(e.form,"paperId",t)},expression:"form.paperId"}},e._l(e.paperList,(function(e,a){return t("el-option",{key:a,attrs:{label:e.paperName,value:e.paperId}})})),1)],1)],1)],1),t("el-row",[t("el-col",{attrs:{span:12}},[t("el-form-item",{attrs:{label:"题型:",prop:"questionType"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择题型",clearable:""},model:{value:e.form.questionType,callback:function(t){e.$set(e.form,"questionType",t)},expression:"form.questionType"}},e._l(e.questionTypeOptions,(function(e){return t("el-option",{key:e.typeId,attrs:{label:e.typeName,value:e.typeId}})})),1)],1)],1),t("el-col",{attrs:{span:12}},[t("el-form-item",{attrs:{label:"问题类别:",prop:"questionSubject"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择问题类别",clearable:""},model:{value:e.form.questionSubject,callback:function(t){e.$set(e.form,"questionSubject",t)},expression:"form.questionSubject"}},e._l(e.questionSubjectOptions,(function(e,a){return t("el-option",{key:a,attrs:{label:e.subjectName,value:e.subjectId}})})),1)],1)],1)],1),t("el-form-item",{attrs:{label:"题目名称（英文）:",prop:"questionNameEn"}},[e.questionNameEnFlag?t("CKEditor",{attrs:{height:"300px"},model:{value:e.form.questionNameEn,callback:function(t){e.$set(e.form,"questionNameEn",t)},expression:"form.questionNameEn"}}):t("el-input",{attrs:{type:"textarea",placeholder:"请输入题目名称（英文）"},model:{value:e.form.questionNameEn,callback:function(t){e.$set(e.form,"questionNameEn",t)},expression:"form.questionNameEn"}}),t("el-checkbox",{model:{value:e.questionNameEnFlag,callback:function(t){e.questionNameEnFlag=t},expression:"questionNameEnFlag"}},[e._v("是否使用富文本")])],1),t("el-form-item",{attrs:{label:"题目名称（中文）:",prop:"questionNameCn"}},[e.questionNameCnFlag?t("CKEditor",{attrs:{height:"300px"},model:{value:e.form.questionNameCn,callback:function(t){e.$set(e.form,"questionNameCn",t)},expression:"form.questionNameCn"}}):t("el-input",{attrs:{type:"textarea",placeholder:"请输入题目名称（中文）"},model:{value:e.form.questionNameCn,callback:function(t){e.$set(e.form,"questionNameCn",t)},expression:"form.questionNameCn"}}),t("el-checkbox",{model:{value:e.questionNameCnFlag,callback:function(t){e.questionNameCnFlag=t},expression:"questionNameCnFlag"}},[e._v("是否使用富文本")])],1),e._l(e.answerList,(function(a,r){return[t("el-form-item",{attrs:{label:"答案（英文）:",prop:"answerContentEn"}},[a.answerContentEnFlag?t("CKEditor",{attrs:{height:"300px"},model:{value:e.form.questionNameEn,callback:function(t){e.$set(e.form,"questionNameEn",t)},expression:"form.questionNameEn"}}):t("el-input",{staticStyle:{width:"calc(100% - 185px)"},attrs:{type:"textarea",placeholder:"请输入答案（英文）"},model:{value:a.answerContentEn,callback:function(t){e.$set(a,"answerContentEn",t)},expression:"item.answerContentEn"}},[t("template",{slot:"append"})],2),0===r?t("el-button",{staticStyle:{float:"right"},attrs:{type:"primary",icon:"el-icon-plus"},on:{click:function(t){return e.handleAddAnswer(a,r)}}},[e._v("新增答案")]):e._e(),r>0?t("el-button",{staticStyle:{float:"right"},attrs:{type:"warning",plain:"",icon:"el-icon-minus"},on:{click:function(t){return e.handleRemoveAnswer(a,r)}}},[e._v("删除答案")]):e._e(),t("div",{class:"1"==a.correctAnswerFlag&&"answer-checked",staticStyle:{display:"inline-block",cursor:"pointer","vertical-align":"middle",float:"right",padding:"0 10px"},on:{click:function(t){return e.handleAnswerClick(a,r)}}},[t("i",{staticClass:"el-icon-circle-check"}),e._v(" 正确")]),t("el-checkbox",{model:{value:a.answerContentEnFlag,callback:function(t){e.$set(a,"answerContentEnFlag",t)},expression:"item.answerContentEnFlag"}},[e._v("是否使用富文本")])],1),t("el-form-item",{attrs:{label:"答案（中文）:",prop:"answerContentCn"}},[a.answerContentCnFlag?t("CKEditor",{attrs:{height:"300px"},model:{value:e.form.questionNameEn,callback:function(t){e.$set(e.form,"questionNameEn",t)},expression:"form.questionNameEn"}}):t("el-input",{staticStyle:{width:"calc(100% - 185px)"},attrs:{type:"textarea",placeholder:"请输入答案（中文）"},model:{value:a.answerContentCn,callback:function(t){e.$set(a,"answerContentCn",t)},expression:"item.answerContentCn"}}),t("div",[t("el-checkbox",{model:{value:a.answerContentCnFlag,callback:function(t){e.$set(a,"answerContentCnFlag",t)},expression:"item.answerContentCnFlag"}},[e._v("是否使用富文本")])],1)],1)]})),t("el-form-item",{attrs:{label:"视频解析地址:",prop:"videoUrl"}},[t("el-input",{attrs:{placeholder:"请输入视频解析地址"},model:{value:e.form.videoUrl,callback:function(t){e.$set(e.form,"videoUrl",t)},expression:"form.videoUrl"}})],1),t("el-form-item",{attrs:{label:"视频签名:",prop:"videoSign"}},[t("el-input",{attrs:{type:"textarea",placeholder:"请输入视频解析地址","show-word-limit":""},model:{value:e.form.videoSign,callback:function(t){e.$set(e.form,"videoSign",t)},expression:"form.videoSign"}})],1),t("el-form-item",{attrs:{label:"题目解释:",prop:"clarify"}},[e.isEdit?t("CKEditor",{model:{value:e.form.clarify,callback:function(t){e.$set(e.form,"clarify",t)},expression:"form.clarify"}}):e._e()],1),t("el-form-item",{attrs:{label:"题目状态:",prop:"status"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择题目状态"},model:{value:e.form.status,callback:function(t){e.$set(e.form,"status",t)},expression:"form.status"}},e._l(e.statusOptions,(function(e,a){return t("el-option",{key:a,attrs:{label:e.statusName,value:e.statusId}})})),1)],1)],2),t("el-tab-pane",{attrs:{label:"其他信息",name:"2"}},[t("el-form-item",{attrs:{label:"题目难度:",prop:"difficulty"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择题目难度",clearable:""},model:{value:e.form.difficulty,callback:function(t){e.$set(e.form,"difficulty",t)},expression:"form.difficulty"}},[t("el-option",{attrs:{label:"S+",value:"4"}}),t("el-option",{attrs:{label:"S",value:"3"}}),t("el-option",{attrs:{label:"A",value:"2"}}),t("el-option",{attrs:{label:"B",value:"1"}}),t("el-option",{attrs:{label:"C",value:"0"}})],1)],1),t("el-form-item",{attrs:{label:"题目置信度:",prop:"confidence"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择题目状态",clearable:""},model:{value:e.form.confidence,callback:function(t){e.$set(e.form,"confidence",t)},expression:"form.confidence"}},[t("el-option",{attrs:{label:"正确",value:"3"}}),t("el-option",{attrs:{label:"高",value:"2"}}),t("el-option",{attrs:{label:"中",value:"1"}}),t("el-option",{attrs:{label:"低",value:"0"}})],1)],1),t("el-form-item",{attrs:{label:"fileId:",prop:"fileId"}},[t("el-input",{attrs:{type:"text",placeholder:"请输入fileId","show-word-limit":""},model:{value:e.form.fileId,callback:function(t){e.$set(e.form,"fileId",t)},expression:"form.fileId"}})],1),t("el-form-item",{attrs:{label:"appId:",prop:"appId"}},[t("el-input",{attrs:{type:"text",placeholder:"请输入appId","show-word-limit":""},model:{value:e.form.appId,callback:function(t){e.$set(e.form,"appId",t)},expression:"form.appId"}})],1),t("el-form-item",{attrs:{label:"psign:",prop:"psign"}},[t("el-input",{attrs:{type:"text",placeholder:"请输入psign","show-word-limit":""},model:{value:e.form.psign,callback:function(t){e.$set(e.form,"psign",t)},expression:"form.psign"}})],1),t("el-form-item",{attrs:{label:"微信视频地址:",prop:"wxVideoUrl"}},[t("el-input",{attrs:{type:"textarea",placeholder:"请输入微信视频地址","show-word-limit":""},model:{value:e.form.wxVideoUrl,callback:function(t){e.$set(e.form,"wxVideoUrl",t)},expression:"form.wxVideoUrl"}})],1),t("el-form-item",{attrs:{label:"问题分数:",prop:"questionPoints"}},[t("el-input-number",{attrs:{min:1,max:100,label:"请输入问题分数"},model:{value:e.form.questionPoints,callback:function(t){e.$set(e.form,"questionPoints",t)},expression:"form.questionPoints"}})],1),t("el-form-item",{attrs:{label:"题目来源:",prop:"source"}},[t("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择题目来源",clearable:""},model:{value:e.form.source,callback:function(t){e.$set(e.form,"source",t)},expression:"form.source"}},e._l(e.sourceOptions,(function(e,a){return t("el-option",{key:a,attrs:{label:e.source,value:e.source}})})),1)],1)],1)],1),t("div",{staticClass:"tc",staticStyle:{"padding-top":"10px"}},[t("el-button",{staticStyle:{width:"150px"},on:{click:e.cancel}},[e._v("返 回")]),t("el-button",{staticStyle:{width:"150px","margin-left":"50px"},attrs:{type:"primary"},on:{click:e.submitForm}},[e._v("提 交")])],1)],1)],1)])},n=[],i=(a("a481"),a("d25f"),a("6b54"),a("87b3"),a("6d67"),a("759f"),a("cabd")),s=function(){var e=this,t=e._self._c;return t("div",{attrs:{id:"editor"}})},o=[],l={name:"CKEditor",props:{value:{type:String,default:""},height:{type:String,default:"600px"}},data:function(){return{editor:null}},mounted:function(){this.init()},methods:{init:function(){var e=this,t=this;ClassicEditor.create(document.querySelector("#editor"),{ckfinder:{uploadUrl:"/ckfinder/connector?command=QuickUpload&type=Files&responseType=json"},toolbar:{shouldNotGroupWhenFull:!0},fontFamily:{options:["Arial"]}}).then((function(a){a.editing.view.change((function(r){r.setStyle("min-height",t.height,a.editing.view.document.getRoot()),a.setData(e.value)})),a.model.document.on("change:data",(function(e){t.$emit("input",a.getData())})),t.editor=a})).catch((function(e){console.error("There was a problem initializing the editor.",e)}))}},beforeDestroy:function(){this.editor&&this.editor.destroy()}},u=l,c=a("2877"),p=a("475d"),m=a.n(p),d=Object(c["a"])(u,s,o,!1,null,"74dbc6f8",null);"function"===typeof m.a&&m()(d);var f=d.exports,h=a("e762"),b={name:"topic",components:{CKEditor:f},data:function(){return{loading:!0,ids:[],single:!0,multiple:!0,showSearch:!0,total:0,queryParams:{adminId:"",questionName:"",source:"",paperId:"",status:"1",page:1,pageSize:20,questionSubject:"",id:""},list:[],sourceOptions:[],questionSubjectOptions:[],questionTypeOptions:[],title:"题目新增",form:{},rules:{examId:[{required:!0,message:"所属课程不能为空",trigger:"blur"}],questionNameEn:[{required:!0,message:"题目名称（英文）不能为空",trigger:"blur"}],paperId:[{required:!0,message:"所属考点不能为空",trigger:"blur"}],questionPoints:[{required:!0,message:"问题分数不能为空",trigger:"blur"}],difficulty:[{required:!0,message:"题目难度不能为空",trigger:"blur"}],confidence:[{required:!0,message:"问题置信度不能为空",trigger:"blur"}],questionSubject:[{required:!0,message:"问题主题不能为空",trigger:"blur"}],questionType:[{required:!0,message:"问题类型不能为空",trigger:"blur"}],source:[{required:!0,message:"题目来源不能为空",trigger:"blur"}]},isEdit:!1,answerList:[{answerContentCn:"",answerContentEn:"",correctAnswerFlag:"0"}],activeName:"1",examList:[],statusOptions:[],paperList:[],queryPaperList:[],questionNameEnFlag:!1,questionNameCnFlag:!1,allPaperList:[]}},created:function(){this.getList(1)},methods:{getExamName:function(e){var t="";return this.examList.some((function(a){if(a.examId===e)return t=a.examName,!0})),t},getQuestionTypeName:function(e){var t="";return this.questionTypeOptions.some((function(a){if(a.typeId===e)return t=a.typeName,!0})),t},getPaperName:function(e){var t="";return this.allPaperList.some((function(a){if(a.paperId===e)return t=a.paperName,!0})),t},getQuestionSubjectName:function(e){var t="";return this.questionSubjectOptions.some((function(a){if(a.subjectId===e)return t=a.subjectName,!0})),t},handleExamChange:function(){this.form.paperId=void 0,this.getPaperList()},handleQueryExamChange:function(){this.queryParams.paperId=void 0,this.getQueryPaperList()},getPaperList:function(){var e=this;this.form.examId?this.examList.map((function(t){t.examId===e.form.examId&&(e.paperList=t.paperList)})):this.paperList=[]},getQueryPaperList:function(){var e=this;this.queryParams.examId?this.examList.map((function(t){t.examId===e.queryParams.examId&&(e.queryPaperList=t.paperList)})):this.queryPaperList=[]},changeTopicStatus:function(e){var t=this,a="1"===e.status?"停用":"启用";this.$confirm("是否确认"+a+"?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){Object(i["n"])({adminId:t.$store.state.user.userId,questionId:e.id,status:"1"===e.status?"0":"1"}).then((function(e){t.msgSuccess("修改成功"),t.getList()}))}))},handleAnswerClick:function(e,t){"1"==e.correctAnswerFlag?e.correctAnswerFlag="0":e.correctAnswerFlag="1",this.$forceUpdate()},getList:function(e){var t=this,a={adminId:this.$store.state.user.userId,page:this.queryParams.page.toString(),pageSize:"20",paperId:"",questionName:"",source:"",status:""};this.loading=!0,a.page="1",a.paperId=this.queryParams.paperId,a.questionName=this.queryParams.questionName,a.difficulty=this.queryParams.difficulty,a.confidence=this.queryParams.confidence,a.source=this.queryParams.source,a.status=this.queryParams.status,a.id=this.queryParams.id,a.examId=this.queryParams.examId,a.questionSubject=this.queryParams.questionSubject,Object(i["i"])(a).then((function(e){t.list=e.TopicList||[],e.totalRecord&&(t.total=parseInt(e.totalRecord)),t.loading=!1;var a=e.optionList;a&&(t.examList=a.examList||[],t.examList.length&&(t.allPaperList=[],t.examList.map((function(e){e.paperList&&e.paperList.length&&(t.allPaperList=t.allPaperList.concat(e.paperList))}))),t.statusOptions=a.status||[],t.sourceOptions=a.source||[],t.questionSubjectOptions=a.questionSubject||[],t.questionTypeOptions=a.questionType||[])}))},handleAddAnswer:function(){this.answerList.push({answerContentCn:"",answerContentEn:"",correctAnswerFlag:"0"})},handleRemoveAnswer:function(e,t){this.answerList.splice(t,1)},handleAdd:function(){this.reset(),this.isEdit=!0,this.title="题目新增"},handleSelectionChange:function(e){this.ids=e.map((function(e){return e.jobId})),this.single=1!=e.length,this.multiple=!e.length},handleQuery:function(){this.queryParams.page=1,this.getList()},resetQuery:function(){this.$refs["queryForm"]&&this.$refs["queryForm"].resetFields(),this.queryParams.status="1",this.handleQuery()},handleUpdate:function(e){var t=this;this.reset();var a=e.id||this.ids;Object(i["h"])({questionId:a,adminId:this.$store.state.user.userId}).then((function(e){"string"!==typeof e?(e.clarify=h["a"].decode(e.clarify),t.form=e,t.answerList=t.form.answerList,t.isEdit=!0,t.title="题目编辑",t.getPaperList()):t.$message.error("数据解析失败")}))},cancel:function(){this.isEdit=!1},submitForm:function(){var e=this;this.$refs["form"].validate((function(t){if(t){var a=e.answerList.filter((function(e){return"1"==e.correctAnswerFlag}));if(0===a.length&&"3"!=e.form.questionType)return e.$message.error("请设置正确答案"),!1;if(void 0!=e.form.questionId){var r="试卷";e.paperList.map((function(t){t.paperName===e.form.paperId&&(r=t.paperName)}));var n={adminId:e.$store.state.user.userId,paperId:e.form.paperId,examId:e.form.examId,paperName:r,questionId:e.form.questionId,questionNameEn:e.form.questionNameEn,questionNameCn:e.form.questionNameCn,difficulty:e.form.difficulty,confidence:e.form.confidence,clarify:h["a"].encode(e.form.clarify),videoUrl:e.form.videoUrl,videoSign:e.form.videoSign,questionPoints:e.form.questionPoints.toString(),questionSubject:e.form.questionSubject,questionType:e.form.questionType,source:e.form.source,status:e.form.status,fileId:e.form.fileId,appId:e.form.appId,psign:e.form.psign,wxVideoUrl:e.form.wxVideoUrl,answerList:e.answerList};console.log(JSON.stringify(n)),Object(i["o"])(n).then((function(t){e.msgSuccess("修改成功"),e.isEdit=!1,e.getList()}))}else{var s="试卷";e.paperList.map((function(t){t.paperName===e.form.paperId&&(s=t.paperName)}));var o={adminId:e.$store.state.user.userId,paperId:e.form.paperId,examId:e.form.examId,paperName:s,questionId:e.form.questionId,questionNameEn:e.form.questionNameEn,questionNameCn:e.form.questionNameCn,difficulty:e.form.difficulty,confidence:e.form.confidence,clarify:e.encode(e.form.clarify),videoUrl:e.form.videoUrl,videoSign:e.form.videoSign,questionPoints:e.form.questionPoints.toString(),questionSubject:e.form.questionSubject,questionType:e.form.questionType,source:e.form.source,status:e.form.status,fileId:e.form.fileId,appId:e.form.appId,psign:e.form.psign,wxVideoUrl:e.form.wxVideoUrl,answerList:e.answerList};console.log(JSON.stringify(o)),Object(i["a"])(o).then((function(t){e.msgSuccess("新增成功"),e.isEdit=!1,e.getList()}))}}else e.$nextTick((function(){e.getParentNode(document.querySelector(".el-form-item__error"))}))}))},getParentNode:function(e){if(e.getAttribute&&"tabpanel"===e.getAttribute("role")){var t=e.getAttribute("aria-labelledby").replace("tab-","");this.activeName=t}e.parentNode&&this.getParentNode(e.parentNode)},encode:function(e){if(!e)return"";var t=encodeURI(e),a=btoa(t);return a},reset:function(){this.form={questionNameEn:void 0,questionNameCn:void 0,paperId:void 0,paperName:void 0,difficulty:void 0,confidence:void 0,clarify:void 0,videoUrl:void 0,videoSign:void 0,source:"OS",questionType:"1",questionSubject:"1",questionPoints:1,status:"1"},this.answerList=[{answerContentCn:"",answerContentEn:"",correctAnswerFlag:"0"}],this.$refs["form"]&&this.$refs["form"].resetFields()}}},g=b,y=(a("0c36"),Object(c["a"])(g,r,n,!1,null,"558931da",null));t["default"]=y.exports},e529:function(e,t,a){}}]);