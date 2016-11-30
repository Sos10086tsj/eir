//eir.poDetail={
//	loadPoDetail : function(){
//		$.getJSON(ctx + '/po/detail/data/' + poId,function(result){
//			if(result.success){
//				var html = '';
//				for(var i = 0; i < result.data.items.length; i++){
//					var orderItem = result.data.items[i];
//					var itemTable = '';
//					itemTable += '<div>ordrNo#' + orderItem.orderNo + '    styleNo#' + orderItem.styleNo + '</div>'
//						+ '<div><table class="am-table am-table-bordered am-table-radius am-table-striped"><thead><tr><th></th>';
//					
//					//获取颜色
//					var theadColors = new Array();
//					for(var j = 0; j < result.data.sizes.length; j ++){
//						var size = result.data.sizes[j];
//						var itemSize = orderItem.items[size.key];
//						if(null != itemSize){
//							$.each(itemSize,function(color,value){
//								if(theadColors.indexOf(color) == -1){
//									theadColors.push(color);
//								}
//							});
//						}
//					}
//					for(var color in theadColors){
//						itemTable += '<th>' + theadColors[color] + '</th>';
//					}
//					itemTable += '</tr></thead><tbody>';
//					for(var j = 0; j < result.data.sizes.length; j ++){
//						var size = result.data.sizes[j];
//						itemTable += '<tr>';
//						itemTable += '<td>' + size.value +'</td>';
//						var itemSize = orderItem.items[size.key];
//						for(var color in theadColors){
//							var value = itemSize[theadColors[color]];
//							if(null == value){
//								value = '';
//							}
//							itemTable += '<td>' + value +'</td>';
//						}
//						itemTable += '</tr>';
//					}
//					itemTable += '</tbody></table></div>';
//					itemTable += '<hr data-am-widget="divider" style="" class="am-divider am-divider-default" />';
//					html += itemTable;
//				}
//				$("#js_po_detail_main").html(html);
//			}else{
//				alter("数据加载失败...");
//			}
//		});
//	}
//}
//$(function(){
//	eir.poDetail.loadPoDetail();
//})