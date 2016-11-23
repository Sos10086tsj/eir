eir.po={
	loadPos : function(){
		$.getJSON(ctx + '/po/list',function(result){
			if(result.success){
				var pos = result.data.data;
				var tbody = $('#js_po_table tbody');
				tbody.empty();
				for(var i = 0; i < pos.length; i++){
					var po = pos[i];
					var tr = '';
					tr += '<tr>'
						+ '<td>' + po.fileName + '</td>'
						+ '<td>' + eir.format.date(po.uploadDate,'yyyy-MM-dd') + '</td>'
						+ '<td>' + po.status + '</td>'
						+ '<td><a>重新处理</a>  <a>重新上传</a>  <a>删除</a>  <a>查看处理结果</a></td>'
						+ '</tr>';
					tbody.append(tr);
				}
				//刷新分页
			}else{
				alter("数据加载失败...")
			}
		});
	}
}
$(function(){
	eir.amezeui.file.initSigleFileUpload($('#js_po_file_input'),$('#js_po_file_display'));
	eir.po.loadPos();
})