var eir = {
	loadMenu : function(url){
		window.open(url,'js_main_frame');
	},
	amezeui : {
		file : {
			initSigleFileUpload : function(fileComp,displayComp){
				$(fileComp).on('change', function() {
					var fileNames = '';
					$.each(this.files, function(index) {
						var id = 'js_file_' + chance.string({length:5}) + '_' + index;
				    	fileNames += '<span class="am-badge" id="' + id + '" onclick="eir.amezeui.file.removeFile(\'' + id + '\')">' + this.name + '<i class="am-icon-remove"></i></span> ';
				    });
					displayComp.html(fileNames);
				});
			},
			
			removeFile : function(id){
				$('#' + id).remove();
			}
		}
	},
	format : {
		date : function(date,pattern){
			return (new Date(date)).format(pattern);
		}
	},
	formular : {
		pageNums : function(currentPage, total, pageSize){
			if(typeof pageSize == 'undefined'){
				pageSize = 5;
			}
			if(total < pageSize){
				pageSize = total;
			}
			var half = (pageSize - currentPage) / 2;
			var right = half;
			var left = half;
			if(half * 2 < pageSize){
				left ++;
			}
			var startNum = currentPage - left;
			if(startNum < 1){
				startNum = 1;
			}
			var nums = new Array();
			for(var i = 0; i < pageSize; i++){
				nums[i] = startNum + i;
			}
			return nums;
		}
	}
}
$(function () {
	$.ajaxSetup({
        complete: function (response, textStatus) {
            var c = parseInt(response.status);
            if (c == 401) {
                // TODO
                top.location.href = ctx;
                return false;
            }
        }
    });
	
	Date.prototype.format = function (format) {
        /*
         * eg:format="yyyy-MM-dd hh:mm:ss";
         */
        var o = {
            "M+": this.getMonth() + 1, // month
            "d+": this.getDate(), // day
            "h+": this.getHours(), // hour
            "m+": this.getMinutes(), // minute
            "s+": this.getSeconds(), // second
            "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
            "S": this.getMilliseconds()
            // millisecond
        }

        if (/(y+)/.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4
                - RegExp.$1.length));
        }

        for (var k in o) {
            if (new RegExp("(" + k + ")").test(format)) {
                format = format.replace(RegExp.$1, RegExp.$1.length == 1
                    ? o[k]
                    : ("00" + o[k]).substr(("" + o[k]).length));
            }
        }
        return format;
    }
})