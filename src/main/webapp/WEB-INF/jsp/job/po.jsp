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
					<form id="js_po_upload_form" target="js_po_upload_iframe" action="${ctx }/job/test">
						<div>
							<label>工厂</label> <select>
								<option>佳楠</option>
								<option>普天母</option>
							</select>
							
							<label>服装类型</label> <select>
								<option>男装</option>
								<option>女装</option>
							</select>
						</div>

						<div>
							<div class="am-form-group am-form-file">
								<button type="button" class="am-btn am-btn-default am-btn-sm">
									<i class="am-icon-cloud-upload"></i> 选择要上传的文件
								</button>
								<input id="doc-form-file" type="file" multiple>
							</div>
							<div id="file-list"></div>
						</div>
						<div class="am-cf"></div>
						<button type="button" class="am-btn am-btn-default am-btn-xs" onclick="eir.po.uploadPo()">上传</button>
					</form>
				</div>
				
				<iframe id="js_po_upload_iframe" class="am-hide" name="js_po_upload_iframe"></iframe>
			</div>
		</div>

	</div>

	<!-- PO列表 -->
	<div>
		<table class="am-table am-table-bordered am-table-radius am-table-striped am-table-striped am-table-hover">
			<thead>
				<tr>
					<th>PO文件</th>
					<th>上传时间</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Head First Design Patterns(英文版).pdf</td>
					<td>2016-11-22 11:13:12</td>
					<td>待处理</td>
					<td>
						<a>重新处理</a>
						<a>重新上传</a>
						<a>删除</a>
						<a>查看处理结果</a>
					</td>
				</tr>
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
<script src="${ctx }/resources/js/job/po.js"></script>
</html>
