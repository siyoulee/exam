(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-1bce7c53"],{"206d":function(t,e,r){"use strict";r.r(e);var n=function(){var t=this,e=t._self._c;return e("div",[e("ul",{staticClass:"list"},t._l(t.list,(function(r,n){return e("li",{key:n,staticClass:"list-item"},[e("div",{staticClass:"list-item-image"},[e("img",{attrs:{src:r.paperPhoto,alt:""}})]),e("div",{staticClass:"list-item-content"},[e("div",{staticClass:"list-item-text"},[e("router-link",{staticClass:"color-333 fz18",attrs:{to:"/examination"}},[t._v(t._s(r.paperName))]),e("p",{staticClass:"color-999 mt10"},[t._v(t._s(r.paperDesc))])],1),e("button",{staticClass:"theme-btn list-item-btn",on:{click:function(e){return t.handleDetail(r)}}},[t._v("学习")])])])})),0),e("pagination",{directives:[{name:"show",rawName:"v-show",value:t.total>0,expression:"total>0"}],attrs:{total:t.total},on:{pagination:t.getList}})],1)},o=[],i=r("cabd"),a={name:"exercise",data:function(){return{total:10,list:[]}},created:function(){this.list=[],this.getList()},methods:{getList:function(){var t=this;Object(i["o"])({practiseType:0,paperType:"CDMP",userId:this.$store.state.user.userId}).then((function(e){e.paperList&&(t.list=e.paperList)}))},handleDetail:function(t){this.$router.push({path:"/examination",query:{id:t.id,practiseType:0}})}}},c=a,u=r("2877"),l=Object(u["a"])(c,n,o,!1,null,"0a295102",null);e["default"]=l.exports},4127:function(t,e,r){"use strict";var n=r("d233"),o=r("b313"),i={brackets:function(t){return t+"[]"},indices:function(t,e){return t+"["+e+"]"},repeat:function(t){return t}},a=Array.isArray,c=Array.prototype.push,u=function(t,e){c.apply(t,a(e)?e:[e])},l=Date.prototype.toISOString,s={delimiter:"&",encode:!0,encoder:n.encode,encodeValuesOnly:!1,serializeDate:function(t){return l.call(t)},skipNulls:!1,strictNullHandling:!1},p=function t(e,r,o,i,c,l,p,f,d,y,m,b){var h=e;if("function"===typeof p?h=p(r,h):h instanceof Date&&(h=y(h)),null===h){if(i)return l&&!b?l(r,s.encoder):r;h=""}if("string"===typeof h||"number"===typeof h||"boolean"===typeof h||n.isBuffer(h)){if(l){var g=b?r:l(r,s.encoder);return[m(g)+"="+m(l(h,s.encoder))]}return[m(r)+"="+m(String(h))]}var j,v=[];if("undefined"===typeof h)return v;if(a(p))j=p;else{var O=Object.keys(h);j=f?O.sort(f):O}for(var w=0;w<j.length;++w){var x=j[w];c&&null===h[x]||(a(h)?u(v,t(h[x],o(r,x),o,i,c,l,p,f,d,y,m,b)):u(v,t(h[x],r+(d?"."+x:"["+x+"]"),o,i,c,l,p,f,d,y,m,b)))}return v};t.exports=function(t,e){var r=t,c=e?n.assign({},e):{};if(null!==c.encoder&&"undefined"!==typeof c.encoder&&"function"!==typeof c.encoder)throw new TypeError("Encoder has to be a function.");var l="undefined"===typeof c.delimiter?s.delimiter:c.delimiter,f="boolean"===typeof c.strictNullHandling?c.strictNullHandling:s.strictNullHandling,d="boolean"===typeof c.skipNulls?c.skipNulls:s.skipNulls,y="boolean"===typeof c.encode?c.encode:s.encode,m="function"===typeof c.encoder?c.encoder:s.encoder,b="function"===typeof c.sort?c.sort:null,h="undefined"!==typeof c.allowDots&&c.allowDots,g="function"===typeof c.serializeDate?c.serializeDate:s.serializeDate,j="boolean"===typeof c.encodeValuesOnly?c.encodeValuesOnly:s.encodeValuesOnly;if("undefined"===typeof c.format)c.format=o["default"];else if(!Object.prototype.hasOwnProperty.call(o.formatters,c.format))throw new TypeError("Unknown format option provided.");var v,O,w=o.formatters[c.format];"function"===typeof c.filter?(O=c.filter,r=O("",r)):a(c.filter)&&(O=c.filter,v=O);var x,A=[];if("object"!==typeof r||null===r)return"";x=c.arrayFormat in i?c.arrayFormat:"indices"in c?c.indices?"indices":"repeat":"indices";var P=i[x];v||(v=Object.keys(r)),b&&v.sort(b);for(var C=0;C<v.length;++C){var L=v[C];d&&null===r[L]||u(A,p(r[L],L,P,f,d,y?m:null,O,b,h,g,w,j))}var k=A.join(l),D=!0===c.addQueryPrefix?"?":"";return k.length>0?D+k:""}},4328:function(t,e,r){"use strict";var n=r("4127"),o=r("9e6a"),i=r("b313");t.exports={formats:i,parse:o,stringify:n}},"9e6a":function(t,e,r){"use strict";var n=r("d233"),o=Object.prototype.hasOwnProperty,i={allowDots:!1,allowPrototypes:!1,arrayLimit:20,decoder:n.decode,delimiter:"&",depth:5,parameterLimit:1e3,plainObjects:!1,strictNullHandling:!1},a=function(t,e){for(var r={},n=e.ignoreQueryPrefix?t.replace(/^\?/,""):t,a=e.parameterLimit===1/0?void 0:e.parameterLimit,c=n.split(e.delimiter,a),u=0;u<c.length;++u){var l,s,p=c[u],f=p.indexOf("]="),d=-1===f?p.indexOf("="):f+1;-1===d?(l=e.decoder(p,i.decoder),s=e.strictNullHandling?null:""):(l=e.decoder(p.slice(0,d),i.decoder),s=e.decoder(p.slice(d+1),i.decoder)),o.call(r,l)?r[l]=[].concat(r[l]).concat(s):r[l]=s}return r},c=function(t,e,r){for(var n=e,o=t.length-1;o>=0;--o){var i,a=t[o];if("[]"===a&&r.parseArrays)i=[].concat(n);else{i=r.plainObjects?Object.create(null):{};var c="["===a.charAt(0)&&"]"===a.charAt(a.length-1)?a.slice(1,-1):a,u=parseInt(c,10);r.parseArrays||""!==c?!isNaN(u)&&a!==c&&String(u)===c&&u>=0&&r.parseArrays&&u<=r.arrayLimit?(i=[],i[u]=n):"__proto__"!==c&&(i[c]=n):i={0:n}}n=i}return n},u=function(t,e,r){if(t){var n=r.allowDots?t.replace(/\.([^.[]+)/g,"[$1]"):t,i=/(\[[^[\]]*])/,a=/(\[[^[\]]*])/g,u=i.exec(n),l=u?n.slice(0,u.index):n,s=[];if(l){if(!r.plainObjects&&o.call(Object.prototype,l)&&!r.allowPrototypes)return;s.push(l)}var p=0;while(null!==(u=a.exec(n))&&p<r.depth){if(p+=1,!r.plainObjects&&o.call(Object.prototype,u[1].slice(1,-1))&&!r.allowPrototypes)return;s.push(u[1])}return u&&s.push("["+n.slice(u.index)+"]"),c(s,e,r)}};t.exports=function(t,e){var r=e?n.assign({},e):{};if(null!==r.decoder&&void 0!==r.decoder&&"function"!==typeof r.decoder)throw new TypeError("Decoder has to be a function.");if(r.ignoreQueryPrefix=!0===r.ignoreQueryPrefix,r.delimiter="string"===typeof r.delimiter||n.isRegExp(r.delimiter)?r.delimiter:i.delimiter,r.depth="number"===typeof r.depth?r.depth:i.depth,r.arrayLimit="number"===typeof r.arrayLimit?r.arrayLimit:i.arrayLimit,r.parseArrays=!1!==r.parseArrays,r.decoder="function"===typeof r.decoder?r.decoder:i.decoder,r.allowDots="boolean"===typeof r.allowDots?r.allowDots:i.allowDots,r.plainObjects="boolean"===typeof r.plainObjects?r.plainObjects:i.plainObjects,r.allowPrototypes="boolean"===typeof r.allowPrototypes?r.allowPrototypes:i.allowPrototypes,r.parameterLimit="number"===typeof r.parameterLimit?r.parameterLimit:i.parameterLimit,r.strictNullHandling="boolean"===typeof r.strictNullHandling?r.strictNullHandling:i.strictNullHandling,""===t||null===t||"undefined"===typeof t)return r.plainObjects?Object.create(null):{};for(var o="string"===typeof t?a(t,r):t,c=r.plainObjects?Object.create(null):{},l=Object.keys(o),s=0;s<l.length;++s){var p=l[s],f=u(p,o[p],r);c=n.merge(c,f,r)}return n.compact(c)}},b313:function(t,e,r){"use strict";var n=String.prototype.replace,o=/%20/g;t.exports={default:"RFC3986",formatters:{RFC1738:function(t){return n.call(t,o,"+")},RFC3986:function(t){return String(t)}},RFC1738:"RFC1738",RFC3986:"RFC3986"}},cabd:function(t,e,r){"use strict";r.d(e,"o",(function(){return o})),r.d(e,"d",(function(){return i})),r.d(e,"h",(function(){return a})),r.d(e,"g",(function(){return c})),r.d(e,"f",(function(){return u})),r.d(e,"a",(function(){return l})),r.d(e,"m",(function(){return s})),r.d(e,"l",(function(){return p})),r.d(e,"j",(function(){return f})),r.d(e,"c",(function(){return d})),r.d(e,"e",(function(){return y})),r.d(e,"k",(function(){return m})),r.d(e,"i",(function(){return b})),r.d(e,"n",(function(){return h})),r.d(e,"b",(function(){return g}));var n=r("b775");r("4328");function o(t){return Object(n["a"])({url:"/PaperList",method:"post",data:t})}function i(t){return Object(n["a"])({url:"/GetExamPage",method:"post",data:t})}function a(t){return Object(n["a"])({url:"/PostExamPage",method:"post",data:t})}function c(t){return Object(n["a"])({url:"/admin/GetTopicList",method:"post",data:t})}function u(t){return Object(n["a"])({url:"/admin/GetTopic",method:"post",data:t})}function l(t){return Object(n["a"])({url:"/admin/AddTopic",method:"post",data:t})}function s(t){return Object(n["a"])({url:"/admin/UpdateTopic",method:"post",data:t})}function p(t){return Object(n["a"])({url:"/admin/TopicStatusModify",method:"post",data:t})}function f(t){return Object(n["a"])({url:"/SearchTopic",method:"post",data:t})}function d(t){return Object(n["a"])({url:"/FavTopic",method:"post",data:t})}function y(t){return Object(n["a"])({url:"/GetExams",method:"post",data:t})}function m(t){return Object(n["a"])({url:"/SequenceExams",method:"post",data:t})}function b(t){return Object(n["a"])({url:"/RedoSequenceExam",method:"post",data:t})}function h(t){return Object(n["a"])({url:"/VideoList",method:"post",data:t})}function g(t){return Object(n["a"])({method:"POST",url:"/ExchangeCode",data:t})}},d233:function(t,e,r){"use strict";var n=Object.prototype.hasOwnProperty,o=function(){for(var t=[],e=0;e<256;++e)t.push("%"+((e<16?"0":"")+e.toString(16)).toUpperCase());return t}(),i=function(t){var e;while(t.length){var r=t.pop();if(e=r.obj[r.prop],Array.isArray(e)){for(var n=[],o=0;o<e.length;++o)"undefined"!==typeof e[o]&&n.push(e[o]);r.obj[r.prop]=n}}return e},a=function(t,e){for(var r=e&&e.plainObjects?Object.create(null):{},n=0;n<t.length;++n)"undefined"!==typeof t[n]&&(r[n]=t[n]);return r},c=function t(e,r,o){if(!r)return e;if("object"!==typeof r){if(Array.isArray(e))e.push(r);else{if(!e||"object"!==typeof e)return[e,r];(o&&(o.plainObjects||o.allowPrototypes)||!n.call(Object.prototype,r))&&(e[r]=!0)}return e}if(!e||"object"!==typeof e)return[e].concat(r);var i=e;return Array.isArray(e)&&!Array.isArray(r)&&(i=a(e,o)),Array.isArray(e)&&Array.isArray(r)?(r.forEach((function(r,i){if(n.call(e,i)){var a=e[i];a&&"object"===typeof a&&r&&"object"===typeof r?e[i]=t(a,r,o):e.push(r)}else e[i]=r})),e):Object.keys(r).reduce((function(e,i){var a=r[i];return n.call(e,i)?e[i]=t(e[i],a,o):e[i]=a,e}),i)},u=function(t,e){return Object.keys(e).reduce((function(t,r){return t[r]=e[r],t}),t)},l=function(t){try{return decodeURIComponent(t.replace(/\+/g," "))}catch(e){return t}},s=function(t){if(0===t.length)return t;for(var e="string"===typeof t?t:String(t),r="",n=0;n<e.length;++n){var i=e.charCodeAt(n);45===i||46===i||95===i||126===i||i>=48&&i<=57||i>=65&&i<=90||i>=97&&i<=122?r+=e.charAt(n):i<128?r+=o[i]:i<2048?r+=o[192|i>>6]+o[128|63&i]:i<55296||i>=57344?r+=o[224|i>>12]+o[128|i>>6&63]+o[128|63&i]:(n+=1,i=65536+((1023&i)<<10|1023&e.charCodeAt(n)),r+=o[240|i>>18]+o[128|i>>12&63]+o[128|i>>6&63]+o[128|63&i])}return r},p=function(t){for(var e=[{obj:{o:t},prop:"o"}],r=[],n=0;n<e.length;++n)for(var o=e[n],a=o.obj[o.prop],c=Object.keys(a),u=0;u<c.length;++u){var l=c[u],s=a[l];"object"===typeof s&&null!==s&&-1===r.indexOf(s)&&(e.push({obj:a,prop:l}),r.push(s))}return i(e)},f=function(t){return"[object RegExp]"===Object.prototype.toString.call(t)},d=function(t){return null!==t&&"undefined"!==typeof t&&!!(t.constructor&&t.constructor.isBuffer&&t.constructor.isBuffer(t))};t.exports={arrayToObject:a,assign:u,compact:p,decode:l,encode:s,isBuffer:d,isRegExp:f,merge:c}}}]);