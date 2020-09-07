function showdiv(i) {   
			document.body.parentNode.style.overflow = "hidden";
            document.getElementById("bg" + i).style.display ="block";
            document.getElementById("show" + i).style.display ="block";
        }
function hidediv(i) {
			document.body.parentNode.style.overflow = "auto";
            document.getElementById("bg" + i).style.display ='none';
            document.getElementById("show" + i).style.display ='none';
        }

function showdiv_del() {   
			document.body.parentNode.style.overflow = "hidden";
            document.getElementById("bg").style.display ="block";
            document.getElementById("divDel").style.display ="block";
        }
function hidediv_del() {
			document.body.parentNode.style.overflow = "auto";
            document.getElementById("bg").style.display ='none';
            document.getElementById("divDel").style.display ='none';
        }


function showCard(cardid,infoid,clsName)
	{
	 var cardList = cardid.parentNode.getElementsByTagName("span");
	  for(i=0;i<cardList.length;i++){
	   if(cardid == cardList[i]) {
			cardList[i].className = clsName + "On";
			eval("document.getElementById('"+infoid + i + "').style.display=\"inline\";");
	   }
	   if(cardid != cardList[i]){
			cardList[i].className = clsName + "Off";
			eval("document.getElementById('"+infoid + i + "').style.display=\"none\";"); 
		}
		
	   }
		
	}

//隐藏弹出框
function hide(i) {
	if ($(document).contents().find("#iframe" + i)[0].contentWindow != null 
			&& $(document).contents().find("#iframe" + i)[0].contentWindow.$ != null
			&& $(document).contents().find("#iframe" + i)[0].contentWindow.$("#file-list") != null)
		$(document).contents().find("#iframe" + i)[0].contentWindow.$("#file-list").html("");
//	$(document).contents().find("#iframe" + i)[0].style.height="0px";
//	$(document).contents().find("#iframe" + i)[0].contentWindow.location.reload();
	hidediv(i);
}

//文字过多，省略号代替
function textEllipsis(text, length) {
//	sLen = 0;  
//    try{  
//        //先将回车换行符做特殊处理  
//        str = str.replace(/(\r\n+|\s+|　+)/g,"龘");  
//        //处理英文字符数字，连续字母、数字、英文符号视为一个单词  
//        str = str.replace(/[\x00-\xff]/g,"m");    
//        //合并字符m，连续字母、数字、英文符号视为一个单词  
//        str = str.replace(/m+/g,"*");  
//        //去掉回车换行符  
//        str = str.replace(/龘+/g,"");  
//        //返回字数  
//        sLen = str.length;  
//    }catch(e){  
//          
//    }  
//    return sLen;  
	if (text.length > length)
		return text.substring(0, length-1) + "...";
	else
		return text;
}  


/**
 * 弹出框居中
 */

//设置弹出框居中
function showDivAlign0(frameId, showId) {
	$("#" + frameId)
	.height(470)
	.width(iWidth);
	
	var iWidth=$("#" + showId).width(); //弹出窗口的宽度;
	var iHeight=$("#" + showId).height(); //弹出窗口的高度;
//	alert(iHeight);
//	alert(document.documentElement.clientHeight);
	//alert(document.documentElement.clientWidth);
	var iLeft = (document.documentElement.clientWidth-iWidth)/2; //获得窗口的水平位置;
	var iTop = (document.documentElement.clientHeight-iHeight)/2; //获得窗口的垂直位置;
	$("#" + showId)
//		.height(iHeight)
//	    .width(iWidth)
	    .css('position','absolute')
	    .css("top",iTop)
	    .css("left",iLeft);
}

//设置弹出框居中
function showDivAlign(frameId, showId, frameDivId) {
//	$("#frameDiv3").height(300);
//	$("#" + frameId).height(300);
////	$("#" + showId).height(300);
//	$("#" + showId).css("top",0).css("left",0);
//	return;
	
    var ifr = document.getElementById(frameId);
    var doc = ifr.contentWindow.document;
    if (doc == null || doc.documentElement == null)return false;
    var scrollWidth = doc.documentElement.scrollWidth; //弹出窗口的宽度;
	var scrollHeight = doc.documentElement.scrollHeight; //弹出窗口的高度;
// 	alert("scrollWidth: " + scrollWidth);
// 	alert("scrollHeight: " + scrollHeight);
	$("#" + frameId).height(scrollHeight);
	$("#" + frameDivId).height(scrollHeight);
	var divWidth = document.getElementById(showId).offsetWidth;
	var divHeight = document.getElementById(showId).offsetHeight;
//	alert("divWidth: " + divWidth);
//	alert("divHeight: " + divHeight);
    var clientWidth = document.documentElement.clientWidth; //屏幕的宽度;
	var clientHeight = document.documentElement.clientHeight; //屏幕的高度;
// 	alert("clientWidth: " + clientWidth);
// 	alert("clientHeight: " + clientHeight);
	var left = (clientWidth-divWidth)/2; //获得窗口的水平位置;
// 	var top = (clientHeight-divHeight)/2; //获得窗口的垂直位置;
	var top = 0; //获得窗口的垂直位置;
	if (clientHeight * 0.9 < divHeight) {
		$("#" + frameId).css("height", clientHeight * 0.9 - 145);
		$("#" + frameId).height(clientHeight * 0.9 - 145);
		$("#" + frameDivId).css("height", clientHeight * 0.9 - 145);
		$("#" + frameDivId).height(clientHeight * 0.9 - 145);
		top = (clientHeight * 0.1)/2;
	}
	else {
		var height = document.getElementById(frameId).contentWindow.document.getElementsByTagName("div")[0].scrollHeight;
		$("#" + frameId).css("height", height);
		$("#" + frameId).height(height);
		$("#" + frameDivId).css("height", height);
		$("#" + frameDivId).height(height);
		top = (clientHeight-divHeight)/2; //获得窗口的垂直位置;
	}
// 	alert("divWidth: " + divWidth);
// 	alert("divHeight: " + divHeight);
// 	alert("left: " + left);
// 	alert("top: " + top);
	$("#" + showId)
//	    .width(divWidth)
//		.height(divHeight)
	    .css('position','absolute')
	    .css("top",top)
	    .css("left",left);
//	alert($("#" + frameId).height());
//	alert($("#" + showId).height());
}  


function showFrame(i) {
	showdiv(i);
	showDivAlign("iframe" + i, "show" + i, "frameDiv" + i);
}

//格式化文件大小
function formatSize(n) {
	if (n < 0) 
		return "";
	else if (n >= 0 && n < 1000)
		return n + "字节";
	else if (n >= 1000 && n < 1000 * 1000) {
		var n1 = n / 1000;
		var n2 = n1.toFixed(2);//保留两位小数
		return n2 + "K";
	}
	else if (n >= 1000 * 1000 && n < 1000 * 1000 * 1000) {
		var n1 = n / (1000 * 1000);
		var n2 = n1.toFixed(2);//保留两位小数
		return n2 + "M";
	}
	else if (n >= 1000 * 1000 * 1000 && n < 1000 * 1000 * 1000 * 1000) {
		var n1 = n / (1000 * 1000 * 1000);
		var n2 = n1.toFixed(2);//保留两位小数
		return n2 + "G";
	}
	else if (n >= 1000 * 1000 * 1000 * 1000 && n < 1000 * 1000 * 1000 * 1000 * 1000) {
		var n1 = n / (1000 * 1000 * 1000 * 1000);
		var n2 = n1.toFixed(2);//保留两位小数
		return n2 + "T";
	}
	else {
		return "太大了";
	}
	
}



/**
 * 浏览器版本
 */
function BrowserType() { 
  var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串 
//  alert(userAgent);
  var isOpera = userAgent.indexOf("Opera") > -1; //判断是否Opera浏览器 
  var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera; //判断是否IE浏览器 
  var isEdge = userAgent.indexOf("Windows NT") > -1 && userAgent.indexOf("Trident/7.0") > -1 && !isIE; //判断是否IE的Edge浏览器 
  var isFF = userAgent.indexOf("Firefox") > -1; //判断是否Firefox浏览器 
  var isSafari = userAgent.indexOf("Safari") > -1 && userAgent.indexOf("Chrome") == -1; //判断是否Safari浏览器 
  var isChrome = userAgent.indexOf("Chrome") > -1 && userAgent.indexOf("Safari") > -1; //判断Chrome浏览器 
 
  if (isIE) { 
     var reIE = new RegExp("MSIE (\\d+\\.\\d+);"); 
     reIE.test(userAgent); 
     var fIEVersion = parseFloat(RegExp["$1"]); 
     if(fIEVersion == 7) 
     { return "IE7";} 
     else if(fIEVersion == 8) 
     { return "IE8";} 
     else if(fIEVersion == 9) 
     { return "IE9";} 
     else if(fIEVersion == 10) 
     { return "IE10";} 
     else if(fIEVersion == 11) 
     { return "IE11";} 
     else
     { return "IE"}//IE版本过低 
   }//isIE end 
     
   if (isFF) { return "FF";} 
   if (isOpera) { return "Opera";} 
   if (isSafari) { return "Safari";} 
   if (isChrome) { return "Chrome";} 
   if (isEdge) { return "IEEdge";} 
   return "";
 }


/**
 * js 缓存
 */
//定义缓存对象
var localStorage;
if (window.localStorage) {//支持localStorage
//	alert('This browser supports localStorage');
	localStorage = window.localStorage;
} else {//不支持localStorage
//	alert('This browser does NOT support localStorage');
	if (typeof (localStorage) == 'undefined') {
		var box = document.body || document.getElementsByTagName("head")[0]
				|| document.documentElement;
		userdataobj = document.createElement('input');
		userdataobj.type = "hidden";
		userdataobj.addBehavior("#default#userData");
		box.appendChild(userdataobj);
		// 设定对象
		localStorage = {
			setItem : function(nam, val) {
				userdataobj.load(nam);
				userdataobj.setAttribute(nam, val);
				var d = new Date();
				d.setDate(d.getDate() + 700);
				userdataobj.expires = d.toUTCString();
				userdataobj.save(nam);
				userdataobj.load("userdata_record");
				var dt = userdataobj.getAttribute("userdata_record");
				if (dt == null)
					dt = '';
				dt = dt + nam + ",";
				userdataobj.setAttribute("userdata_record", dt);
				userdataobj.save("userdata_record");
			},
			// 模拟 setItem
			getItem : function(nam) {
				userdataobj.load(nam);
				return userdataobj.getAttribute(nam);
			},
			// 模拟 getItem
			removeItem : function(nam) {
				userdataobj.load(nam);
				clear_userdata(nam)
				userdataobj.load("userdata_record");
				var dt = userdataobj.getAttribute("userdata_record");
				var reg = new RegExp(nam + ",", "g");
				dt = dt.replace(reg, '');
				var d = new Date();
				d.setDate(d.getDate() + 700);
				userdataobj.expires = d.toUTCString();
				userdataobj.setAttribute("userdata_record", dt);
				userdataobj.save("userdata_record");
			},
			// 模拟 removeItem
			clear : function() {
				userdataobj.load("userdata_record");
				var dt = userdataobj.getAttribute("userdata_record").split(",");
				for ( var i in dt) {
					if (dt[i] != '')
						clear_userdata(dt[i])
				}
				clear_userdata("userdata_record")
			}
		// 模拟 clear();
		}
		function clear_userdata(keyname) {// 将名字为keyname的变量消除
			var keyname;
			var d = new Date();
			d.setDate(d.getDate() - 1);
			userdataobj.load(keyname);
			userdataobj.expires = d.toUTCString();
			userdataobj.save(keyname);
		}
	}

}

/**
 * IE indexOf
 */
if (!Array.prototype.indexOf) {
    Array.prototype.indexOf = function (elt /*, from*/) {
        var len = this.length >>> 0;
        var from = Number(arguments[1]) || 0;
        from = (from < 0)
             ? Math.ceil(from)
             : Math.floor(from);
        if (from < 0)
            from += len;
        for (; from < len; from++) {
            if (from in this &&
                this[from] === elt)
                return from;
        }
        return -1;
    };
}


/**
 * 键盘事件
 */
//回车键事件 
$(document).keypress(function(e) {  
	if(e.which == 13) {  
		$("#search").click();  
	}
}); 

/**
 * 分页跳转
 */
//首页
function firstPage() {
	if (pageIndex == 1)
		return;
	else {
		pageIndex = 1;
		list();
	}
}
//上一页
function prevPage() {
	if (checkSearchItem()) {
		if (pageIndex == 1)
			return;
		else {
			pageIndex = parseInt(pageIndex) - 1;
			list();
		}
	}
	else {
		pageIndex = 1;
		list();
	}
}
//下一页
function nextPage() {
	if (checkSearchItem()) {
		if (pageIndex == pageNum)
			return;
		else {
			pageIndex = parseInt(pageIndex) + 1;
			list();
		}
	}
	else {
		pageIndex = 1;
		list();
	}
}
//末页
function lastPage() {
	if (checkSearchItem()) {
		if (pageIndex == pageNum)
			return;
		else {
			pageIndex = pageNum;
			list();
		}
	}
	else {
		pageIndex = 1;
		list();
	}
}
//跳转到某页
function gotoPage() {
	if (checkSearchItem()) {
		var index = $("#pageIndex").val();
		if (index > pageNum) {
			alert("最多只有" + pageNum + "页");
			return;
		}
		else {
			if (pageIndex == index)
				return;
			else {
				pageIndex = index;
				list();
			}
		}
	}
	else {
		pageIndex = 1;
		list();
	}
}


//清空下拉列表
function clearSelect(i) {
	 var n = "";
	if (i != 0)
		n = i;
	$('div.cate_inp', $('#cate' + n))[0].innerHTML = "请选择";
	$('.cate_tri', $('#cate' + n)).css("color","gray");
}

//遍历下拉框，取消非焦点下拉
function getCates(id) {
	var cates = $(".cate");
	for (var i = 0; i < $(".cate").length; i++) {
		if ($(".cate")[i].id != id) {
			$('div.cate_drop', $(".cate")[i])[0].style.display = 'none';
			$('.cate_tri', $(".cate")[i]).data('active', 'off');
		}
	}
}

//下拉列表js
function Selection(i, name, k, n) {
	if (i == 0)
		i = "";
	var $cate = $('#cate' + i), 
	$tri = $('.cate_tri', $cate), 
	$drop = $('div.cate_drop', $cate), 
	$inp = $('div.cate_inp', $cate);
	if (k != 0)
		$tri.css("color", "gray");
//	if (n > 0)
//		$drop.css("max-height", 35 * n + 2);
	
	$tri.on('click', function(event) {
	    getCates('#cate' + i);
		event.stopPropagation();
		var $el = $(this);
		if ($el.data('active') !== 'on') {
			$drop[0].style.display = 'block';
			$el.data('active', 'on');
		} else {
			$drop[0].style.display = 'none';
			$el.data('active', 'off')
		}
	});
	
	$(document).on('click', function(event){
		if ($drop[0] == '' || $drop[0] == null || $drop[0] == 'undefined')
			return;
		$drop[0].style.display = 'none';
		$tri.data('active', 'off');
	});
	
	$drop.on('mouseover', 'li', function(event) {
		$inp[0].innerHTML = this.innerHTML;
		if (this.innerHTML == "请选择")
			$tri.css("color", "gray");
		else
			$tri.css("color", "black");
		$("#" + name).val(this.value == 0 ? "" : this.value);
	}).on('click', 'li', function(event) {
		// do something
		event.stopPropagation();
		$drop[0].style.display = 'none';
		$tri.data('active', 'off');
	});
}

//显示下拉框数据
function selectData(i, id, data) {
	if (i == 0)
		i = "";
	var dataList = $(window.parent.document).contents().find("#iframe2")[0].contentWindow.$('#' + id);
	var $cate = $(window.parent.document).contents().find("#iframe2")[0].contentWindow.$('#cate' + i);
    var $inp = $(window.parent.document).contents().find("#iframe2")[0].contentWindow.$('div.cate_inp', $cate);
	var datas = $('li', dataList);
	for (var i = 0; i < datas.length; i++) {
		//alert(datas[i].value + ", " + datas[i].innerHTML);
		if (datas[i].value == data)
			$inp[0].innerHTML = datas[i].innerHTML;
	}
}
function selectData0(i, id, data) {
	if (i == 0)
		i = "";
	var dataList = $('#' + id);
	var $cate = $('#cate' + i);
    var $inp = $('div.cate_inp', $cate);
	var datas = $('li', dataList);
	for (var i = 0; i < datas.length; i++) {
		//alert(datas[i].value + ", " + datas[i].innerHTML);
		if (datas[i].value == data)
			$inp[0].innerHTML = datas[i].innerHTML;
	}
}

// 下拉列表数据
function dictList(name, type, k) {
	if (k == 0)
		url = "/api/classify/members";
	else
		url = "/api/dict/list";
	$.ajax({
		async : false,
		type : "post",
		url : url,
		dataType : "json",
		data : {
			'type' : type
		},
		success : function(resp) {
			if(resp.code == 200){
				$("#" + name + "List").html("<li value='0'>请选择</li>");
				for (var i = 0; i < resp.data.length; i++) {
					$("#" + name + "List").append("<li value='" + resp.data[i].id + "'>" + (k==0?resp.data[i].name:resp.data[i].value) + "</li>");
				}
			} else {
//				alert(resp.msg);
			}
		}
	});
}

//多选框
function selectSingle() {
	//alert(document.activeElement.checked);
	var check = true;
	var arr = document.getElementsByName("dataList");
	for (var i = 0; i < arr.length; i++) {
		if (!arr[i].checked) {
			check = false;
			break;
		}
	}
	if (check) {
		$("#checkItem").html("全不选");
		selectTab = true;
	}
	else {
		$("#checkItem").html("全选");
		selectTab = false;
	}
}
function selectAll() {
	//alert(document.activeElement.checked);
	if (selectTab) {
		$("#checkItem").html("全选");
		selectTab = false;
	}
	else {
		$("#checkItem").html("全不选");
		selectTab = true;
	}
	var arr = document.getElementsByName("dataList");
	for (var i = 0; i < arr.length; i++) {
		if (selectTab) {
			arr[i].checked = true;
		}
		else {
			arr[i].checked = false;
		}
	}
}


/**
 * 上传文件
 */

//判断文件大小
function checkFileSizeIE() {
	alert("ie file size");
	var obj = document.getElementById('file');      
	obj.select();
	var path = file_url = document.selection.createRange().text; 
	alert(path);
	var fso = new ActiveXObject("Scripting.FileSystemObject");   
	var size = fso.GetFile(path).size;
//	var image=new Image();
//	  image.dynsrc=path;
//	  alert(image.fileSize);return false;
	alert("文件大小为："+size);
	if (size <= 0) {
		alert("请上传文件！");
		return false;
	}
	else if (size > 20*1024*1024) {
		alert("文件太大，请上传小于20M的文件！");
		return false;
	}
	else
		return true;
}
//判断文件大小
function checkFileSize() {
	var size = $("#file")[0].files[0].size;
	if (size <= 0) {
		alert("请上传文件！");
		return false;
	}
	else if (size > 20*1024*1024) {
		alert("文件太大，请上传小于20M的文件！");
		return false;
	}
	else
		return true;
}

//上传文件(标准)
function upFile(name, msg, i) {
	//判断文件大小
//	if (!checkFileSize())
//		return;
    var fileObj = document.getElementById("file").files[0]; // js 获取文件对象
    if (typeof (fileObj) == "undefined" || fileObj.size <= 0) {
        alert("请选择" + msg);
        return;
    }
    var formFile = new FormData();
    formFile.append("file", fileObj); //加入文件对象

    var data = formFile;
    $.ajax({
        url: "/file/upload/" + name,
        data: data,
        type: "Post",
        dataType: "json",
        cache: false,//上传文件无需缓存
        processData: false,//用于对data参数进行序列化处理 这里必须false
        contentType: false, //必须
        success: function (resp) {
        	if(resp.code == 200){
	            files.push(resp.data);
//	            changeWinHeight();
	            $("#file-list").append("<span id='fSpan" + resp.data + "' class='fr w610 mt5'>"
            		+ $("#tmpName").val()
            		+ "	<a href='javascript:' onclick='delFile(\"fSpan" + resp.data + "\", \"" + resp.data + "\", \"" + name + "\")' class='ml10'><img src='/static/images/icon_del.png'></a>"
            		+ "</span>");
//	    		showDivAlign("iframe" + i, "show" + i);
//	            changeWinHeight();
//	            document.documentElement.clientHeight = 330;//document.documentElement.scrollHeight;
//	            $(document).contents().find("#iframe" + i)[0].contentWindow.$("body").height(document.documentElement.scrollHeight);
//	            document.getElementById("iframe" + i).style.height=400;
//	            changeWinHeight();
		        $("#file").val("");
		        $("#fName").val("");
	            $("#tmpName").val("");
		        alert("上传成功!");
			} else {
				alert(resp.msg);
			}
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) { 
        	 alert("上传失败！请上传不大于20M的文件，压缩文件请勿加密！请重试");
        } 
    })
}

function changeWinHeight() {

    var scrollWidth = document.documentElement.scrollWidth; //弹出窗口的宽度;
	var scrollHeight = document.documentElement.scrollHeight; //弹出窗口的高度;
 	alert("scrollWidth: " + scrollWidth);
 	alert("scrollHeight: " + scrollHeight);
    var clientWidth = document.documentElement.clientWidth; //屏幕的宽度;
	var clientHeight = document.documentElement.clientHeight; //屏幕的高度;
 	alert("clientWidth: " + clientWidth);
 	alert("clientHeight: " + clientHeight);
	
}

//文件名
function getFileName(name) {
	 if (name.indexOf("C:\\fakepath\\") != -1)
		 return name.replace("C:\\fakepath\\","");
	 else
		 return name;
}


//uluploader组件上传
function uluploader0(url) {
	var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
		browse_button : 'browse',
		url : url,
		flash_swf_url : 'js/Moxie.swf',
		silverlight_xap_url : 'js/Moxie.xap',
	});
	uploader.init(); //初始化
 
	//绑定文件添加进队列事件
	uploader.bind('FilesAdded',function(uploader,files){
		for(var i = 0, len = files.length; i<len; i++){
			var file_name = files[i].name; //文件名
			//构造html来更新UI
			var html = '<li id="file-' + files[i].id +'">'
				+ ' <p class="file-name">' + file_name + ' [' + formatSize(files[i].size) +'] ' + '<label id="file-' + files[i].id +'-perc"></label>' + '</p>'
				+ ' <p class="progress"></p>'
				+ '</li>';
			$(html).appendTo('#file-list');
		}
	});
 
	//绑定文件上传进度事件
	uploader.bind('UploadProgress',function(uploader,file){
		$('#file-'+file.id+' .progress').css('width',file.percent + '%');//控制进度条
		$('#file-'+file.id+'-perc').html("(" + file.percent + "%)");
	});
 
	//绑定错误事件
	uploader.bind('Error',function(uploader,errObject){
		alert(errObject.file.name + "上传失败！请上传不大于20M的文件，压缩文件请勿加密！请重试");
		uploader.removeFile(errObject.file);
		errObject.file.destroy();
		$('#file-list').find('#file-' + errObject.file.id).remove();
	});
 
	//上传按钮
	$('#upload-btn').click(function(){
		uploader.start(); //开始上传
	});
}



//上传文件(ie8 -暂废弃)
function upFile_(type, msg) {
	 var fo = document.getElementById("file");
	 if (fo.value.length>0) {
		$("#tmpName").val(getFileName(fo.value));
		document.getElementById('uf-form').action="/file/upload/" + type;
		document.getElementById('uf-form').submit();
	 }
	 else {
	 	alert("请选择" + msg);
	 }
}

//删除文件
//function delFile(spanId, fId, type) {
//	if (!confirm("确定删除？"))
//		return;
//	$.ajax({
//		async : false,
//		type : "post",
//		url : "/file/del",
//		dataType : "json",
//		data : {
//			id : fId,
//			type : type
//		},
//		success : function(resp) {
//			if(resp.code == 200){
//				removeByValue(files, fId);
//				var browser = BrowserType();
//				if (browser.indexOf("IE") != -1) 
//					document.getElementById("file-list").removeChild(document.getElementById(spanId));
//				else
//					$("#file-list").find("#" + spanId)[0].remove();
//			} else {
//				alert(resp.msg);
//			}
//		}
//	});
//}
function delFile0(spanId, fId, type) {
	 if (!confirm("确定删除？"))
	 return;
	 removeByValue(files, fId);
	 var browser = BrowserType();
//	 console.info("browser:" + browser);
	 if (browser.indexOf("IE") != -1) 
		 document.getElementById("file-list").removeChild(document.getElementById(spanId));
	 else
		 $("#file-list").find("#" + spanId)[0].remove();
}


//显示文件
function viewFiles() {
	 alert(files);
}

//根据浏览器显示文件上传控件
function FileOpt() {
	 var browser = BrowserType();
	 if (browser.indexOf("IE") != -1) {
		 $("#seniorFDiv").css("display","none");
		 $("#fName").css("display","none");
		 $("#file").css("width","600px");
		 $("#file").css("height","32px");
		 $("#file").css("line-height","32px");
	 }
	 else {
		 $("#seniorFDiv").css("display","block");
		 $("#fName").css("display","block");
		 $("#file").css("width","0px");
		 $("#file").css("height","0px");
	 }
}



/**
 * 用户登录
 */

//检测用户登录
function checkLogin() {
	$.ajax({
		async : false,
		type : "post",
		url : "/checkLogin",
		dataType : "json",
		data : {},
		success : function(resp) {
			if(resp.code == 200){
//				topInfo(resp.data);
				perInfo(resp.data);
//				loadIframe();
			} else {
				if (window.location.href.indexOf("/view/index") == -1) {
//					alert("用户未登录");
					top.location.href = "/view/index";
				}
			}
		}
	});
}

//用户信息
function topInfo(user) {
//	alert("0:" + user.name);
	var iframe = document.getElementById("topFrame");
	if (iframe.attachEvent) {   
//		iframe.contentWindow.$("#userName").text(user.name);
		iframe.attachEvent("onload", function() { 
			iframe.contentWindow.$("#userName").text(user.name);
			if (iframe.contentWindow.document.getElementById("#userName") != null)
				iframe.contentWindow.document.getElementById("#userName").innerText = user.name;
		});      
	} else {      
		iframe.onload = function() {      
			iframe.contentWindow.$("#userName").text(user.name);
			if (iframe.contentWindow.document.getElementById("#userName") != null)
				iframe.contentWindow.document.getElementById("#userName").innerText = user.name;
		};      
	} 
}

//权限数据
function perInfo(user) {
	/*$.ajax({
		type : "post",
		url : "/perInfo",
		dataType : "json",
		data : {},
		success : function(resp) {
			if(resp.code == 200){
				var pers = resp.data;
				alert("获取权限数据，并存储: " + pers);
//				console.info(pers);
				localStorage.setItem("pers", pers);
			} else {
				alert(resp.msg);
			}
		}
	});*/
	var pers = "";
	if (user.roles != null) {
		for (var i = 0; i < user.roles.length; i++) {
			if (user.roles[i].permissons != null) {
				for (var j = 0; j < user.roles[i].permissons.length; j++) {
					pers = user.roles[i].permissons[j].name + "," + pers;
				}
			}
		}
	}
//	alert("获取权限数据，并存储: " + pers);
	localStorage.setItem("pers", pers);
}

//加载Iframe
function loadIframe() {
//	alert("loadIframe: " + pers);
	var pers = localStorage.getItem("pers");
	var iframe = document.getElementById("menuFrame");
	//iframe未载入
	if (iframe.attachEvent) {      
		iframe.attachEvent("onload", function() {      
			//iframe加载完成后你需要进行的操作   
//			alert("加载完成1");
			setMenuByPermission(pers);
		});      
	} else {      
		iframe.onload = function() {      
			//iframe加载完成后你需要进行的操作  
//			alert("加载完成2");
			setMenuByPermission(pers);
		};      
	} 
	//iframe已载入
	setMenuByPermission(pers);
}

//根据权限分配页面的菜单
function setMenuByPermission(pers) {
	if ($(document).contents().find("#menuFrame")[0].contentWindow.$ != null) {
		if (pers.indexOf("q_bmyb") == -1) { //标密
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu2").css("display","none");
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu21").css("display","none");
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu22").css("display","none");
		}
		if (pers.indexOf("up_bmyb") == -1) { //标密-我的资源
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu21").css("display","none");
		}
		if (pers.indexOf("q_mmyb") == -1) { //木马
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu3").css("display","none");
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu31").css("display","none");
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu32").css("display","none");
		}
		if (pers.indexOf("up_mmyb") == -1) { //木马-我的资源
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu31").css("display","none");
		}
		if (pers.indexOf("q_xnjmb") == -1) { //虚拟机
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu4").css("display","none");
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu41").css("display","none");
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu42").css("display","none");
		}
		if (pers.indexOf("up_xnjmb") == -1) { //虚拟机-我的资源
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu41").css("display","none");
		}
		if (pers.indexOf("q_wlmb") == -1) { //网络
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu5").css("display","none");
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu51").css("display","none");
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu52").css("display","none");
		}
		if (pers.indexOf("up_wlmb") == -1) { //网络-我的资源
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu51").css("display","none");
		}
		if (pers.indexOf("q_jcgj") == -1) { //检查工具
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu6").css("display","none");
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu61").css("display","none");
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu62").css("display","none");
		}
		if (pers.indexOf("up_jcgj") == -1) { //检查工具-我的资源
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu61").css("display","none");
		}
		if (pers.indexOf("q_jxkj") == -1) { //课件
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu7").css("display","none");
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu71").css("display","none");
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu72").css("display","none");
		}
		if (pers.indexOf("up_jxkj") == -1) { //课件-我的资源
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu71").css("display","none");
		}
		if (pers.indexOf("q_user") == -1) {//用户
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu81").css("display","none");
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu84").css("display","none");
		}
		if (pers.indexOf("q_role") == -1) {//角色
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu82").css("display","none");
		}
		if (pers.indexOf("q_classify") == -1) {//分类
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu83").css("display","none");
		}
		if (pers.indexOf("q_user") == -1 && pers.indexOf("q_role") == -1 && pers.indexOf("q_classify") == -1) {//系统
			$(document).contents().find("#menuFrame")[0].contentWindow.$("#menu8").css("display","none");
		}
	}
	
	if (pers.indexOf("q_bmyb") == -1) { //标密
		if (window.location.href.indexOf("/view/bmyb") == -1)
			return;
	}
	if (pers.indexOf("q_mmyb") == -1) { //木马
		if (window.location.href.indexOf("/view/mmyb") == -1)
			return;
	}
	if (pers.indexOf("q_xnjmb") == -1) { //虚拟机
		if (window.location.href.indexOf("/view/xnjmb") == -1)
			return;
	}
	if (pers.indexOf("q_wlmb") == -1) { //网络
		if (window.location.href.indexOf("/view/wlmb") == -1)
			return;
	}
	if (pers.indexOf("q_jcgj") == -1) { //检查工具
		if (window.location.href.indexOf("/view/jcgj") == -1)
			return;
	}
	if (pers.indexOf("q_jxkj") == -1) { //课件
		if (window.location.href.indexOf("/view/jxgj") == -1)
			return;
	}
	if (pers.indexOf("q_user") == -1) {//系统-用户
		if (window.location.href.indexOf("/view/user") == -1)
			return;
		if (window.location.href.indexOf("/view/log") == -1)
			return;
	}
	if (pers.indexOf("q_role") == -1) {//系统-角色
		if (window.location.href.indexOf("/view/role") == -1)
			return;
	}
	if (pers.indexOf("q_classify") == -1) {//系统-分类
		if (window.location.href.indexOf("/view/classify") == -1)
			return;
	}
}

//用户信息
function userInfo() {
	$.ajax({
		type : "post",
		url : "/userInfo",
		dataType : "json",
		data : {
			userCode : "6"
		},
		success : function(resp) {
			if(resp.code == 200){
				var user = resp.data;
//				console.info(user);
				var iframe = document.getElementById("topFrame");
				if (iframe.contentWindow.$ == null) {
					//iframe未载入
					if (iframe.attachEvent) {      
						iframe.attachEvent("onload", function() {      
							iframe.contentWindow.$("#userName").text(user.name);
						});      
					} else {      
						iframe.onload = function() {      
							iframe.contentWindow.$("#userName").text(user.name);
						};      
					} 
				}
				else {
					//iframe已载入
					iframe.contentWindow.$("#userName").text(user.name);
				}
			} else {
				if (window.location.href.indexOf("/view/index") == -1) {
//					alert("用户未登录");
					window.parent.location.href = "/view/login";
				}
			}
		}
	});
}

//退出登录
function logout() {
	$.ajax({
		type : "post",
		url : "/logout",
		dataType : "json",
		data : {},
		success : function(resp) {
			if(resp.code == 200){
				window.parent.parent.location = "/view/login";
			} else {
//				alert(resp.msg);
			}
		}
	});
}


/**
 * 操作权限
 */
function perCheck(action, type) {
	var pers = localStorage.getItem("pers");
	if (type != "") type="_" + type;
	var tmp = action + type;
//	alert(tmp + "; " + (pers.indexOf(tmp) != -1));return true;
	return (pers.indexOf(tmp) != -1);
}


/**
 * 数组
 */

//数组删除元素
function removeByValue(arr, val) {
	for (var i = 0; i < arr.length; i++) {
		if (arr[i] == val) {
			arr.splice(i, 1);
			break;
		}
	}
}


/**
 * 日期，时间
 */
function timestampToDate(t) {
	if (t == null)return "";
	var date =  new Date(t);
    var y = "";
    if (BrowserType().indexOf("IE") == -1 || BrowserType() == "IE10" || BrowserType() == "IE11" || BrowserType() == "IEEdge")
    	y = 1900+date.getYear();
    else
    	y = date.getYear();
    var m = "0"+(date.getMonth()+1);
    var d = "0"+date.getDate();
    return y+"-"+m.substring(m.length-2,m.length)+"-"+d.substring(d.length-2,d.length);
}
function timestampToTime(t) {
	if (t == null)return "";
	var date =  new Date(t);
	var y = "";
	if (BrowserType().indexOf("IE") == -1 || BrowserType() == "IE10" || BrowserType() == "IE11" || BrowserType() == "IEEdge")
		y = 1900+date.getYear();
	else
		y = date.getYear();
	var m = "0"+(date.getMonth()+1);
	var d = "0"+date.getDate();
	var h = date.getHours();
	h = h < 10 ? ("0" + h) : h;
	var min = date.getMinutes();
	min = min < 10 ? ("0" + min) : min;
	var s = date.getSeconds();
	s = s < 10 ? ("0" + s) : s;
	return y+"年"+m.substring(m.length-2,m.length)+"月"+d.substring(d.length-2,d.length) + "日 " + h + ":"  + min + ":" + s;
}
function timestampToStaticTime(t) {
	if (t == null)return "";
	var date =  new Date(t);
	var y = "";
	if (BrowserType().indexOf("IE") == -1 || BrowserType() == "IE10" || BrowserType() == "IE11" || BrowserType() == "IEEdge")
		y = 1900+date.getYear();
	else
		y = date.getYear();
	var m = "0"+(date.getMonth()+1);
	var d = "0"+date.getDate();
	var h = date.getHours();
	h = h < 10 ? ("0" + h) : h;
	var min = date.getMinutes();
	min = min < 10 ? ("0" + min) : min;
	var s = date.getSeconds();
	s = s < 10 ? ("0" + s) : s;
	return y+"-"+m.substring(m.length-2,m.length)+"-"+d.substring(d.length-2,d.length) + " " + h + ":"  + min + ":"  + s;
}
