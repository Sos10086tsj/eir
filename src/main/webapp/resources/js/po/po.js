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
						+ '<td>' + eir.format.date(po.uploadDate,'yyyy-MM-dd hh:mm:ss') + '</td>'
						+ '<td>' + po.status + '</td>'
						+ '<td><a href="' + ctx + '/po/detail/' + po.id + '" target="js_main_frame">查看处理结果</a></td>'
						+ '</tr>';
					tbody.append(tr);
				}
				//刷新分页
				var pagination = $('#js_po_table_pagination');
				pagination.empty();
				var paginationHtml = '<li ';
				if(result.data.pageNum == 1){
					paginationHtml += 'class="am-disabled"';
				}
				paginationHtml += '"><a href="#">&laquo;</a></li>';
				var pageNums = eir.formular.pageNums(result.data.pageNum,result.data.total);
				for(var i in pageNums){
					if(i == result.data.pageNum){
						paginationHtml += '<li class="am-active"><a href="#">' + pageNums[i] + '</a></li>';
					}else{
						paginationHtml += '<li><a href="#">' + pageNums[i] + '</a></li>';
					}
				}
				if(pageNums.length > result.data.total){
					paginationHtml += '<li><a href="#">&raquo;</a></li>';
				}else{
					paginationHtml += '<li class="am-disabled"><a href="#">&raquo;</a></li>';
				}
				pagination.html(paginationHtml);
			}else{
				alter("数据加载失败...");
			}
		});
	}
}
$(function(){
	eir.amezeui.file.initSigleFileUpload($('#js_po_file_input'),$('#js_po_file_display'));
	eir.po.loadPos();
})