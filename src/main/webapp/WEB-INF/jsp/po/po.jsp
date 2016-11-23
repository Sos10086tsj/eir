<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/base/baselib.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Eir</title>
<meta name="description" content="index page">
<meta name="keywords" content="index">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="icon" type="image/png"
	href="${ctx }/resources/component/AmazeUI-2.7.2/i/favicon.png">
<link rel="apple-touch-icon-precomposed"
	href="${ctx }/resources/component/AmazeUI-2.7.2/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="EIR" />
<link rel="stylesheet"
	href="${ctx }/resources/component/AmazeUI-2.7.2/css/amazeui.min.css" />
<link rel="stylesheet"
	href="${ctx }/resources/component/AmazeUI-2.7.2/css/admin.css">
<link rel="stylesheet"
	href="${ctx }/resources/component/AmazeUI-2.7.2/css/app.css">
</head>
<body data-type="po">

<!-- 	 <div class="tpl-content-page-title"> -->
<!--     	首页 -->
<!--      </div> -->
	
	<!-- 上传新的PO -->
	<div>
		<button class="am-btn am-btn-primary"
			data-am-modal="{target: '#js_po_upload_div', closeViaDimmer: 0, width: 400, height: 225}">
			上传PO <i class="am-icon-upload"></i>
		</button>

		<!-- PO上传弹窗 -->
		<div class="am-modal am-modal-no-btn" tabindex="-1" id="js_po_upload_div">
			<div class="am-modal-dialog">
				<div class="am-modal-hd">PO上传</div>
				<div>
					<form target="js_main_frame" action="${ctx }/po/upload" method="post" enctype="multipart/form-data">
						<div>
							<label>服装类型</label> 
							<select name="clothingType">
								<option value="BOY">男装</option>
								<option value="GIRL">女装</option>
							</select>
						</div>

						<div>
							<div class="am-form-group am-form-file">
								<button type="button" class="am-btn am-btn-default am-btn-sm">
									<i class="am-icon-cloud-upload"></i> 选择要上传的文件
								</button>
								<input id="js_po_file_input" type="file" name="file">
							</div>
							<div id="js_po_file_display"></div>
						</div>
						<div class="am-cf"></div>
						<button type="submit" class="am-btn am-btn-default am-btn-xs">上传</button>
					</form>
				</div>
			</div>
		</div>

	</div>

	<!-- PO列表 -->
	<div>
		<table class="am-table am-table-bordered am-table-radius am-table-striped am-table-striped am-table-hover" id="js_po_table">
			<thead>
				<tr>
					<th>PO文件</th>
					<th>上传时间</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>

</body>
<script src="${ctx }/resources/component/jQuery/jquery.min.js"></script>
<script src="${ctx }/resources/component/AmazeUI-2.7.2/js/amazeui.min.js"></script>
<script src="${ctx }/resources/component/AmazeUI-2.7.2/js/iscroll.js"></script>
<script>
var ctx = '${ctx}';
</script>
<script src="${ctx }/resources/component/chance/chance.min.js"></script>
<script src="${ctx }/resources/js/eir.js"></script>
<script src="${ctx }/resources/js/po/po.js"></script>
</html>
