eir.mo={
	loadMos : function(){
		$.getJSON(ctx + '/mo/list',function(result){
			if(result.success){
				var mos = result.data.data;
				var tbody = $('#js_mo_table tbody');
				tbody.empty();
				for(var i = 0; i < mos.length; i++){
					var mo = mos[i];
					var tr = '';
					tr += '<tr>'
						+ '<td>' + mo.fileName + '</td>'
						+ '<td>' + eir.format.date(mo.uploadDate,'yyyy-MM-dd') + '</td>'
						+ '<td>' + mo.status + '</td>'
						+ '<td><a href="' + ctx + '/mo/detail/' + mo.id + '" target="js_main_frame">查看处理结果</a></td>'
						+ '</tr>';
					tbody.append(tr);
				}
				//刷新分页
				var pagination = $('#js_mo_table_pagination');
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
	eir.amezeui.file.initSigleFileUpload($('#js_mo_file_input'),$('#js_mo_file_display'));
//	eir.mo.loadMos();
})