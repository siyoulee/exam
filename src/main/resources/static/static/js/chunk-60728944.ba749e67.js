(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-60728944"],{"37f9":function(e,t,r){"use strict";r.r(t);var n=function(){var e=this,t=e._self._c;return t("div")},o=[],i=r("cabd"),a={name:"Index",components:{},data:function(){return{}},methods:{getData:function(){var e=this;Object(i["n"])({practiseType:"1",paperType:"CDMP",userId:this.$store.state.user.userId}).then((function(t){t.paperList&&t.paperList.length>1&&(e.list=t.paperList.splice(1))}))}}},c=a,u=r("2877"),l=Object(u["a"])(c,n,o,!1,null,null,null);t["default"]=l.exports},4127:function(e,t,r){"use strict";var n=r("d233"),o=r("b313"),i={brackets:function(e){return e+"[]"},indices:function(e,t){return e+"["+t+"]"},repeat:function(e){return e}},a=Array.isArray,c=Array.prototype.push,u=function(e,t){c.apply(e,a(t)?t:[t])},l=Date.prototype.toISOString,s={delimiter:"&",encode:!0,encoder:n.encode,encodeValuesOnly:!1,serializeDate:function(e){return l.call(e)},skipNulls:!1,strictNullHandling:!1},p=function e(t,r,o,i,c,l,p,f,d,y,b,m){var h=t;if("function"===typeof p?h=p(r,h):h instanceof Date&&(h=y(h)),null===h){if(i)return l&&!m?l(r,s.encoder):r;h=""}if("string"===typeof h||"number"===typeof h||"boolean"===typeof h||n.isBuffer(h)){if(l){var j=m?r:l(r,s.encoder);return[b(j)+"="+b(l(h,s.encoder))]}return[b(r)+"="+b(String(h))]}var g,O=[];if("undefined"===typeof h)return O;if(a(p))g=p;else{var v=Object.keys(h);g=f?v.sort(f):v}for(var w=0;w<g.length;++w){var x=g[w];c&&null===h[x]||(a(h)?u(O,e(h[x],o(r,x),o,i,c,l,p,f,d,y,b,m)):u(O,e(h[x],r+(d?"."+x:"["+x+"]"),o,i,c,l,p,f,d,y,b,m)))}return O};e.exports=function(e,t){var r=e,c=t?n.assign({},t):{};if(null!==c.encoder&&"undefined"!==typeof c.encoder&&"function"!==typeof c.encoder)throw new TypeError("Encoder has to be a function.");var l="undefined"===typeof c.delimiter?s.delimiter:c.delimiter,f="boolean"===typeof c.strictNullHandling?c.strictNullHandling:s.strictNullHandling,d="boolean"===typeof c.skipNulls?c.skipNulls:s.skipNulls,y="boolean"===typeof c.encode?c.encode:s.encode,b="function"===typeof c.encoder?c.encoder:s.encoder,m="function"===typeof c.sort?c.sort:null,h="undefined"!==typeof c.allowDots&&c.allowDots,j="function"===typeof c.serializeDate?c.serializeDate:s.serializeDate,g="boolean"===typeof c.encodeValuesOnly?c.encodeValuesOnly:s.encodeValuesOnly;if("undefined"===typeof c.format)c.format=o["default"];else if(!Object.prototype.hasOwnProperty.call(o.formatters,c.format))throw new TypeError("Unknown format option provided.");var O,v,w=o.formatters[c.format];"function"===typeof c.filter?(v=c.filter,r=v("",r)):a(c.filter)&&(v=c.filter,O=v);var x,A=[];if("object"!==typeof r||null===r)return"";x=c.arrayFormat in i?c.arrayFormat:"indices"in c?c.indices?"indices":"repeat":"indices";var P=i[x];O||(O=Object.keys(r)),m&&O.sort(m);for(var L=0;L<O.length;++L){var D=O[L];d&&null===r[D]||u(A,p(r[D],D,P,f,d,y?b:null,v,m,h,j,w,g))}var k=A.join(l),N=!0===c.addQueryPrefix?"?":"";return k.length>0?N+k:""}},4328:function(e,t,r){"use strict";var n=r("4127"),o=r("9e6a"),i=r("b313");e.exports={formats:i,parse:o,stringify:n}},"9e6a":function(e,t,r){"use strict";var n=r("d233"),o=Object.prototype.hasOwnProperty,i={allowDots:!1,allowPrototypes:!1,arrayLimit:20,decoder:n.decode,delimiter:"&",depth:5,parameterLimit:1e3,plainObjects:!1,strictNullHandling:!1},a=function(e,t){for(var r={},n=t.ignoreQueryPrefix?e.replace(/^\?/,""):e,a=t.parameterLimit===1/0?void 0:t.parameterLimit,c=n.split(t.delimiter,a),u=0;u<c.length;++u){var l,s,p=c[u],f=p.indexOf("]="),d=-1===f?p.indexOf("="):f+1;-1===d?(l=t.decoder(p,i.decoder),s=t.strictNullHandling?null:""):(l=t.decoder(p.slice(0,d),i.decoder),s=t.decoder(p.slice(d+1),i.decoder)),o.call(r,l)?r[l]=[].concat(r[l]).concat(s):r[l]=s}return r},c=function(e,t,r){for(var n=t,o=e.length-1;o>=0;--o){var i,a=e[o];if("[]"===a&&r.parseArrays)i=[].concat(n);else{i=r.plainObjects?Object.create(null):{};var c="["===a.charAt(0)&&"]"===a.charAt(a.length-1)?a.slice(1,-1):a,u=parseInt(c,10);r.parseArrays||""!==c?!isNaN(u)&&a!==c&&String(u)===c&&u>=0&&r.parseArrays&&u<=r.arrayLimit?(i=[],i[u]=n):"__proto__"!==c&&(i[c]=n):i={0:n}}n=i}return n},u=function(e,t,r){if(e){var n=r.allowDots?e.replace(/\.([^.[]+)/g,"[$1]"):e,i=/(\[[^[\]]*])/,a=/(\[[^[\]]*])/g,u=i.exec(n),l=u?n.slice(0,u.index):n,s=[];if(l){if(!r.plainObjects&&o.call(Object.prototype,l)&&!r.allowPrototypes)return;s.push(l)}var p=0;while(null!==(u=a.exec(n))&&p<r.depth){if(p+=1,!r.plainObjects&&o.call(Object.prototype,u[1].slice(1,-1))&&!r.allowPrototypes)return;s.push(u[1])}return u&&s.push("["+n.slice(u.index)+"]"),c(s,t,r)}};e.exports=function(e,t){var r=t?n.assign({},t):{};if(null!==r.decoder&&void 0!==r.decoder&&"function"!==typeof r.decoder)throw new TypeError("Decoder has to be a function.");if(r.ignoreQueryPrefix=!0===r.ignoreQueryPrefix,r.delimiter="string"===typeof r.delimiter||n.isRegExp(r.delimiter)?r.delimiter:i.delimiter,r.depth="number"===typeof r.depth?r.depth:i.depth,r.arrayLimit="number"===typeof r.arrayLimit?r.arrayLimit:i.arrayLimit,r.parseArrays=!1!==r.parseArrays,r.decoder="function"===typeof r.decoder?r.decoder:i.decoder,r.allowDots="boolean"===typeof r.allowDots?r.allowDots:i.allowDots,r.plainObjects="boolean"===typeof r.plainObjects?r.plainObjects:i.plainObjects,r.allowPrototypes="boolean"===typeof r.allowPrototypes?r.allowPrototypes:i.allowPrototypes,r.parameterLimit="number"===typeof r.parameterLimit?r.parameterLimit:i.parameterLimit,r.strictNullHandling="boolean"===typeof r.strictNullHandling?r.strictNullHandling:i.strictNullHandling,""===e||null===e||"undefined"===typeof e)return r.plainObjects?Object.create(null):{};for(var o="string"===typeof e?a(e,r):e,c=r.plainObjects?Object.create(null):{},l=Object.keys(o),s=0;s<l.length;++s){var p=l[s],f=u(p,o[p],r);c=n.merge(c,f,r)}return n.compact(c)}},b313:function(e,t,r){"use strict";var n=String.prototype.replace,o=/%20/g;e.exports={default:"RFC3986",formatters:{RFC1738:function(e){return n.call(e,o,"+")},RFC3986:function(e){return String(e)}},RFC1738:"RFC1738",RFC3986:"RFC3986"}},cabd:function(e,t,r){"use strict";r.d(t,"n",(function(){return o})),r.d(t,"c",(function(){return i})),r.d(t,"g",(function(){return a})),r.d(t,"f",(function(){return c})),r.d(t,"e",(function(){return u})),r.d(t,"a",(function(){return l})),r.d(t,"l",(function(){return s})),r.d(t,"k",(function(){return p})),r.d(t,"i",(function(){return f})),r.d(t,"b",(function(){return d})),r.d(t,"d",(function(){return y})),r.d(t,"j",(function(){return b})),r.d(t,"h",(function(){return m})),r.d(t,"m",(function(){return h}));var n=r("b775");r("4328");function o(e){return Object(n["a"])({url:"/PaperList",method:"post",data:e})}function i(e){return Object(n["a"])({url:"/GetExamPage",method:"post",data:e})}function a(e){return Object(n["a"])({url:"/PostExamPage",method:"post",data:e})}function c(e){return Object(n["a"])({url:"/admin/GetTopicList",method:"post",data:e})}function u(e){return Object(n["a"])({url:"/admin/GetTopic",method:"post",data:e})}function l(e){return Object(n["a"])({url:"/admin/AddTopic",method:"post",data:e})}function s(e){return Object(n["a"])({url:"/admin/UpdateTopic",method:"post",data:e})}function p(e){return Object(n["a"])({url:"/admin/TopicStatusModify",method:"post",data:e})}function f(e){return Object(n["a"])({url:"/SearchTopic",method:"post",data:e})}function d(e){return Object(n["a"])({url:"/FavTopic",method:"post",data:e})}function y(e){return Object(n["a"])({url:"/GetExams",method:"post",data:e})}function b(e){return Object(n["a"])({url:"/SequenceExams",method:"post",data:e})}function m(e){return Object(n["a"])({url:"/RedoSequenceExam",method:"post",data:e})}function h(e){return Object(n["a"])({url:"/VideoList",method:"post",data:e})}},d233:function(e,t,r){"use strict";var n=Object.prototype.hasOwnProperty,o=function(){for(var e=[],t=0;t<256;++t)e.push("%"+((t<16?"0":"")+t.toString(16)).toUpperCase());return e}(),i=function(e){var t;while(e.length){var r=e.pop();if(t=r.obj[r.prop],Array.isArray(t)){for(var n=[],o=0;o<t.length;++o)"undefined"!==typeof t[o]&&n.push(t[o]);r.obj[r.prop]=n}}return t},a=function(e,t){for(var r=t&&t.plainObjects?Object.create(null):{},n=0;n<e.length;++n)"undefined"!==typeof e[n]&&(r[n]=e[n]);return r},c=function e(t,r,o){if(!r)return t;if("object"!==typeof r){if(Array.isArray(t))t.push(r);else{if(!t||"object"!==typeof t)return[t,r];(o&&(o.plainObjects||o.allowPrototypes)||!n.call(Object.prototype,r))&&(t[r]=!0)}return t}if(!t||"object"!==typeof t)return[t].concat(r);var i=t;return Array.isArray(t)&&!Array.isArray(r)&&(i=a(t,o)),Array.isArray(t)&&Array.isArray(r)?(r.forEach((function(r,i){if(n.call(t,i)){var a=t[i];a&&"object"===typeof a&&r&&"object"===typeof r?t[i]=e(a,r,o):t.push(r)}else t[i]=r})),t):Object.keys(r).reduce((function(t,i){var a=r[i];return n.call(t,i)?t[i]=e(t[i],a,o):t[i]=a,t}),i)},u=function(e,t){return Object.keys(t).reduce((function(e,r){return e[r]=t[r],e}),e)},l=function(e){try{return decodeURIComponent(e.replace(/\+/g," "))}catch(t){return e}},s=function(e){if(0===e.length)return e;for(var t="string"===typeof e?e:String(e),r="",n=0;n<t.length;++n){var i=t.charCodeAt(n);45===i||46===i||95===i||126===i||i>=48&&i<=57||i>=65&&i<=90||i>=97&&i<=122?r+=t.charAt(n):i<128?r+=o[i]:i<2048?r+=o[192|i>>6]+o[128|63&i]:i<55296||i>=57344?r+=o[224|i>>12]+o[128|i>>6&63]+o[128|63&i]:(n+=1,i=65536+((1023&i)<<10|1023&t.charCodeAt(n)),r+=o[240|i>>18]+o[128|i>>12&63]+o[128|i>>6&63]+o[128|63&i])}return r},p=function(e){for(var t=[{obj:{o:e},prop:"o"}],r=[],n=0;n<t.length;++n)for(var o=t[n],a=o.obj[o.prop],c=Object.keys(a),u=0;u<c.length;++u){var l=c[u],s=a[l];"object"===typeof s&&null!==s&&-1===r.indexOf(s)&&(t.push({obj:a,prop:l}),r.push(s))}return i(t)},f=function(e){return"[object RegExp]"===Object.prototype.toString.call(e)},d=function(e){return null!==e&&"undefined"!==typeof e&&!!(e.constructor&&e.constructor.isBuffer&&e.constructor.isBuffer(e))};e.exports={arrayToObject:a,assign:u,compact:p,decode:l,encode:s,isBuffer:d,isRegExp:f,merge:c}}}]);