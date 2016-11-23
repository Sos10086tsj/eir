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
})