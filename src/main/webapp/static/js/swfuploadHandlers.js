/**
 * Created by zhang on 14-2-25.
 */
var queueErrorArray;
var setting = {
    upload_url: "/upload",       //后台处理的程序路径
    flash_url: "/static/test/ie8up/up3/swfupload.swf", //swf程序路径
    file_types: "*.jpg;*.png;*.gif;*.mp4;*.wmv",     //允许上传文件种类
    file_types_description: "Web Image", //对上传文件的描述
    file_size_limit: "200MB",         //文件上传的大小单位默认为KB
    file_upload_limit: 0,

    //debug
    debug: false,

    //handlers
    file_dialog_start_handler: fileDialogStar,   //打开选择对话框触发的事件
    file_queued_handler: fileQueued,             //把文件加入上传队列触发的事件
    file_queue_error_handler: fileError,       //文件加入队列错误时触发的事件
    file_dialog_complete_handler: fileDialogComplete, //文件选择完成触发的事件
    upload_start_handler: uploadStar,               //文件上传触发的事件
    upload_progress_handler: uploadprogress,        //上传中触发的事件
    upload_complete_handler: uploadComplete,        //上传完成触发的事件
    upload_success_handler: uploadSuccess,         //上传成功时触发的事件

    //button
    button_placeholder_id: "swfPlaceHold",
    button_text: "请选择上传文件",
    button_width: 270,
    button_height: 20,
    button_cursor: SWFUpload.CURSOR.HAND,
    button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT
};

var swfup = new SWFUpload(setting);

/*********************************************handler************************************/

/**
 * 打开文件对话框时的触发的事件
 */
function fileDialogStar() {
    if (queueErrorArray) {
        queueErrorArray = null;
    }
}

/**
 * 文件加入上传队列时触发的事件
 * @param 选择上传的文件对象
 */
function fileQueued(file) {
    var swfup = this;        //当前的swfupload实例对象
    var listItem = "<li id='" + file.id + "'>";
    listItem += "文件：<em>" + file.name + "</em>(" + Math.round(file.size / 1024) + "KB)<span id='progressValue'></span>";
    listItem += "<div id='progressBar'><div id='progress'></div></div>";
    listItem += "<p id='statue'></p><span id='cancle'></span></li>";
    $("#fileList").append(listItem);

    //按钮的取消事件
    $("li#" + file.id + " #cancle").click(function (e) {
        swfup.cancelUpload(file.id);
        $("li#" + file.id).remove();
    });
}

/**
 * 文件加入队列错误触发的事件
 * @param file上传的文件对象
 * @param errorCode返回的错误代码
 * @param msg 错误信息
 */
function fileError(file, errorCode, msg) {
    //错误队列数组
    if (!queueErrorArray) {
        queueErrorArray = [];
    }
    errorFile = {
        file: file,
        code: errorCode,
        error: ''
    };

    switch (errorCode) {
        case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
            errorFile.error = '文件大小超出限制';
            break;
        case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
            errorFile.error = '文件类型不允许';
            break;
        case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
            errorFile.error = '超出文件数量限制.';
            break;
        case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
            errorFile.error = '你选择的文件是空的';
            break;

        case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
            errorFile.error = '服务器出错';
            break;
        case SWFUpload.UPLOAD_ERROR.MISSING_UPLOAD_URL:
            errorFile.error = '程序没有设置upload_url属性';
            break;
        case SWFUpload.UPLOAD_ERROR.IO_ERROR:
            errorFile.error = '读取或传输文件时发生错误';
            break;
        case SWFUpload.UPLOAD_ERROR.ZERO_BYTE_FILE:
            errorFile.error = '文件为空文件';
            break;
        case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
            errorFile.error = '上传受到了安全方面的限制';
            break;
        case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
            errorFile.error = '上传出现错误';
            break;
        default :
            errorFile.error = '上传文件出错，错误代码：' + errorCode;
            break;

    }
    queueErrorArray.push(errorFile);
}

/**
 * 选择文件对话框关闭时触发,报告所选文件个数、加入上传队列文件数及上传队列文件总数
 * @param numSelected 选择的文件数目
 * @param numQueued 加入队列的文件数目
 * @param numTotalInQueued 上传文件队列中文件总数
 */
function fileDialogComplete(numSelected, numQueued, numTotalInQueued) {
    if (queueErrorArray && queueErrorArray.length) {    //如果有错误文件

        var table = $('<table><tr><td>文件</td><td>大小</td></tr></table>');
        for (var i in queueErrorArray) {
            var tr = $('<tr></tr>');
            var info = '<td>' + queueErrorArray[i].file.name + '<span style="color:red">(' + queueErrorArray[i].error + ')</span></td>'
                + '<td>' + queueErrorArray[i].file.size + 'bytes</td>';
            table.append(tr.append(info));
        }
        $('body').append(table);
    } else {
        this.startUpload();             //开始上传
    }

}

/**
 * 文件上传触发的事件
 * @param file一个文件对象
 */
function uploadStar(file) {
    if (file) {
        $('#fileList li#' + file.id).find('p#statue').text("上传中。。。。。");
        $('#fileList li#' + file.id).find('p#progress').width("0%");
    }
}

/**
 *文件上传过程Flash定时调用的方法用来返回当前上传的进度
 * @param file 上传文件对象
 * @param byteCompleted 上传的字节
 * @param byteTotal 总的字节
 */
function uploadprogress(file, byteCompleted, byteTotal) {
    var percentByte = Math.round((byteCompleted / byteTotal) * 100);    //当前进度
    $('#fileList li#' + file.id).find('span#progressValue').text(percentByte + "%");
    $('#fileList li#' + file.id).find('div#progress').css('width', percentByte + '%');

}


/**
 * 文件上传完毕并且服务器返回200状态码时触发
 * @param file 上传的文件
 * @param serverData
 * @param response
 */
function uploadSuccess(file, serverData, response) {
    var $item = $("#fileList li#" + file.id);
    $item.find('div#progress').css('width', '100%');
    $item.find('span#progressValue').css('color', 'blue').text('100%');
    $item.find('p#statue').html('上传完成!').css('color', 'green');
}

/**
 * 在一个上传周期结束后触发(uploadError及uploadSuccess均触发)
 * 在此可以开始下一个文件上传(通过上传组件的uploadStart()方法)
 * @param file 上传完成的文件对象
 */
function uploadComplete(file) {
    //判断队列中还有没有文件

    if (this.getStats().files_queued > 0) {
        this.startUpload();
    } else {
        this.cancelUpload(this.fileProgressID);
        $("#fileList li").remove();
    }
}

/********************************按钮事件绑定*********************************************/
$('#submit').click(function () {
    swfup.startUpload();
});