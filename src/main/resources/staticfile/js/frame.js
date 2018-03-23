/*
 ajax定制方法
 * mode:调用模式（"default"：默认;"push"：跨域）
 * type：调用方式（"get";"post"）
 * path:路径
 * param:传递到后台的数据
 * successfn：成功的回调函数
 * errorfn:失败的回调函数
 * */
function invocationMode(type,path,dataType,param,successfn,errorfn){
	var modeT = "default";
	var str = "";
	if(param != ""){
		str += "?"
		for(var item in param){
         	str += item+"="+param[item]+"&";
    	};
	}
	if(modeT === "default"){
		$.ajax({
		  type: type,
		  url: path,
		  data: param,
		  dataType: dataType,
		  success: function(data){
//		  	if(data.indexOf("[") == 0){
//      			var arr = eval(data);
//      		}else{
//      			arr = data;
//      		}
				if(typeof(data) == 'object'){
					successfn(data);
				}else{					
					successfn(JSON.parse(data));
				}
		  },
		  error:function(e){
		  	if( typeof(errorfn)=="undefined"){
         		//alert("请求失败："+JSON.stringify(e));
         	}else{
         		errorfn(e);
         	}
		  }
		});
	}else if(modeT == "push"){
		$.ajax({
        	type:type,
        	url:path +str.substr(0,str.length-1),
        	async:true,
        	contentType:'application/json',
        	dataType:"jsonp",
        	data: param,
          	jsonp: "callbackparam",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(默认为:callback)
        	success:function(data) {
//      		if(data.indexOf("[") == 0){
//      			var arr = eval(data);
//      		}else{
//      			arr = data;
//      		}
//				console.log(JSON.stringify(data));
				successfn(JSON.parse(data));
	        },
         error:function(e){
//       	console.log(JSON.stringify(e));
         	if( typeof(errorfn)=="undefined"){
         		//alert("请求失败："+JSON.stringify(e));
         	}else{
         		errorfn(e);
         	}
         }
        });
	}else{
		//alert("参数有误");
	}
}

//格式化日期字符串
/*
 mm:日期
 param：辅助参数---"d"=年月日；"m"=年月
 * 
 * */
function datetochar(mm,param){
    var d = new Date(mm);
    var str = "";
    if(mm != ""){
    	if((d.getMonth() + 1)>9){
        	str = d.getFullYear() + '' + (d.getMonth() + 1)
	    }else{
	        str = d.getFullYear() + '0' + (d.getMonth() + 1)
	    }
	    if(param == "d"){
	    	if(d.getDate()>9){
		    	str += d.getDate();
		    }else{
		    	str += '0' + d.getDate();
		    }
	    }
    }
    return str;
}
