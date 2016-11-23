eir.index = {
	//加载菜单
	loadMenu : function(){
		$.ajax({
			url : ctx + '/menu',
			type : 'get',
			success : function(response){
				response = $.parseJSON(response);
				if(response.success){
					var menus = response.data;
					var menuHtml = '';
					for(var i in menus){				
						var menu = menus[i];
						var hasSubMenu = false;
						if(null != menu.subMenus && menu.subMenus.length > 0){
							hasSubMenu = true;
						}
						menuHtml += '<li class="tpl-left-nav-item"><a href="javascript:void(0);"';
						if(null != menu.url && menu.url.length > 0){
							menuHtml += ' onclick="eir.loadMenu(\''
								+ ctx + '/' + menu.url
								+ '\')"';
						}
						menuHtml += ' class="nav-link ';
						if(i == 0){
							menuHtml += 'active';
						}else{
							menuHtml += 'tpl-left-nav-link-list';
						}
						menuHtml += '"><i class="'
							+ menu.iconCss
							+ '"></i><span>'
							+ menu.name
							+ '</span>';
						if(hasSubMenu){
							if(i == 0){
								menuHtml += '<i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right tpl-left-nav-more-ico-rotate"></i>';
							}else{
								menuHtml += '<i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>';
							}
						}
						menuHtml += '</a>';
						if(hasSubMenu){
							menuHtml += '<ul class="tpl-left-nav-sub-menu"><li>';
							for(var j in menu.subMenus){
								var subMenu = menu.subMenus[j];
								menuHtml += '<a href="javascript:void(0);"' 
									+ ' onclick="eir.loadMenu(\''
									+ ctx + '/' + subMenu.url
									+ '\')"'
									+ '>'
									+ ' <i class="am-icon-angle-right"></i><span>'
									+ subMenu.name
									+ '</span></a>';
							}
							menuHtml += '</li></ul></li>';
						}
					}
					$('#js_menu_ul').html(menuHtml);
					
					$('.tpl-left-nav-link-list').on('click', function() {
				        $(this).siblings('.tpl-left-nav-sub-menu').slideToggle(80)
				            .end()
				            .find('.tpl-left-nav-more-ico').toggleClass('tpl-left-nav-more-ico-rotate');
				    });
					
					//加载第一个菜单
					eir.loadMenu(ctx + '/job/po');
				}else{
					alert(respose.errorCode);
				}
			},
			failure : function(error){
				alert(error);
			}
		});
	}
}
$(function () {
	eir.index.loadMenu();
	
	// 头部导航隐藏菜单
	$('.tpl-header-nav-hover-ico').on('click', function() {
	    $('.tpl-left-nav').toggle();
	    $('.tpl-content-wrapper').toggleClass('tpl-content-wrapper-hover');
	});
})