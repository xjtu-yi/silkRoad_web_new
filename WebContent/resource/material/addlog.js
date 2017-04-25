/**
 * 使用ajax记录日志
 */
//创建request对象
function initRequest() {
    var request = false;
    if(window.XMLHttpRequest) {         //FireFox
	    request = new XMLHttpRequest();
	    if (request.overrideMimeType) {
	      request.overrideMimeType('text/xml');
	    }
	} else if (window.ActiveXObject) {    //IE
    try {
      request = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
      try {
        request = new ActiveXObject("Microsoft.XMLHTTP");
      } catch (e) {}
    }
  }
  if (!request) {
    window.alert("Create request error!");
    return false;
  }
  return request;
}

var http_request;
function send(sendUrl,sendData) {
  http_request = initRequest();
  //指定请求返回时的回调函数
  http_request.onreadystatechange = ajax_call_back;
  //使用post请求
  http_request.open("POST", sendUrl, true);
  http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
  http_request.send(sendData);
}
//ajax回调函数
function ajax_call_back() {
  if (http_request.readyState == 4) {
    if (http_request.status == 200) {
    }
  }
}
//记录日志所需要的参数：资源Id、资源类型、栏目类型、操作类型、检索关键字
function addlog(id, res_type, banner, optiontype, keywords, url) {
	//设置请求的url;
	var sendUrl = "/system/resource/logs/addlog.jsp";
	//设置请求需要传递的数据
	var sendData = "id="+id+"&res_type="+res_type+"&banner="+banner+"&contenturl="+url+"&optiontype="+optiontype+"&nowdate="+new Date();
	//alert(sendData);
	if(keywords!=null&&""!=keywords) {
		sendData = sendData +"&keywords="+keywords;
	}
	//alert(sendData);
	//发送请求
	send(sendUrl,sendData);
}