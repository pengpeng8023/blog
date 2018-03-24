//var _APINAME = 'http://120.26.132.123:9080/iima-server';
//var _APINAME = 'http://172.19.3.93:8080/poseidon-server';
var _APINAME = getRootPath();
var API ={
    NOTETYPE:_APINAME + "/blog/invokeWebService/getNoteTypes",//获取目录树
    REGISTER: _APINAME + "/base/register",//注冊
    LOGIN:  _APINAME + "/base/login",   //登录
    VERCODE:_APINAME + "/authImage?date="+new Date().getMilliseconds(),//生成校验码
    CHECKVERCODE:_APINAME + "/base/checkVerCode",   //校验码验证
    CHECKACCOUNTNO:_APINAME +"/base/checkAccountNo", //用户名校验
    GETACCOUNTINFO:_APINAME + "/blog/invokeWebService/getLogAccountInfo",//获取登录人信息
    GETGIRDDATA:_APINAME + "/blog/invokeWebService/getGirdData",//获取日记列表信息
    GETNOTEMANAGE:_APINAME+"/blog/invokeWebService/getNoteManage",//获取日记信息
    SAVENOTEMANAGE:_APINAME+"/blog/invokeWebService/saveNoteManage",//保存日记信息
    UPDATENOTEMANAGE:_APINAME+"/blog/invokeWebService/updateNoteManage",//修改日记信息
};

function getRootPath(){  
    //获取当前网址，如： http://localhost:8083/poseidon-web/demo/meun.jsp  
    var curWwwPath=window.document.location.href;  
    //获取主机地址之后的目录，如：poseidon-web/demo/meun.jsp  
    var pathName=window.document.location.pathname;  
    var pos=curWwwPath.indexOf(pathName);  
    //获取主机地址，如： http://localhost:9000  
    var localhostPaht=curWwwPath.substring(0,pos);  
    return localhostPaht;
}  
var JsUtil={
    isEmpty:function (data){
        var t = typeof(data);
        if(t === "undefined" ){
            return true;
        }
        if(t === "string" ){
            if(data == null || data == ""){
                return true;
            }
        }
        if(t === "object"){
            if(data.length == 0 ){
                return true;
            }
        }
        return false;
    },
    isNotEmpty:function (data) {
        var t = typeof(data);
        if(t === "undefined" ){
            return false;
        }
        if(t === "string" ){
            if(data == null || data == ""){
                return false;
            }
        }
        if(t === "object"){
            if(data.length == 0 ){
                return false;
            }
        }
        return true;
    }
}
//日期转换公共方法（日期，类型）
function dateToString(date,type){
    var n = date.getFullYear();
    var y = date.getMonth()+1;
    var r = date.getDate();
    var s = date.getHours();
    var f = date.getMinutes();
    var m = date.getSeconds();
    if(y<10){
        y= "0"+y
    }
    if(r<10){
        r= "0"+r
    }
    if(s<10){
        s= "0"+s
    }
    if(f<10){
        f= "0"+f
    }
    if(m<10){
        m= "0"+m
    }
    if(type == 'yyyy-MM-dd HH:mm:ss'){
        return n+"-"+y+"-"+r+" "+s+":"+f+":"+m
    }else if(type == 'yyyy-MM-dd'){
        return n+"-"+y+"-"+r
    }else{
        return n+y+r
    }

}