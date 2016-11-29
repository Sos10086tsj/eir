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
<body data-type="poDetail">
	<div>
		<div id="js_po_detail_main">
<!-- 			<table class="am-table am-table-bordered am-table-radius am-table-striped"> -->
<!-- 			<thead> -->
<!--   <tr> -->
<!--     <th>网站名称</th> -->
<!--     <th>网址</th> -->
<!--     <th>创建时间</th> -->
<!--   </tr> -->
<!--   </thead> -->
  
<!--    <tbody> -->
<!--   <tr> -->
<!--     <td>Amaze UI</td> -->
<!--     <td>http://amazeui.org</td> -->
<!--     <td>2012-10-01</td> -->
<!--   </tr> -->
<!--   </tbody> -->
  
<!-- 			</table> -->
		</div>
	</div>
</body>
<script src="${ctx }/resources/component/jQuery/jquery.min.js"></script>
<script src="${ctx }/resources/component/AmazeUI-2.7.2/js/amazeui.min.js"></script>
<script src="${ctx }/resources/component/AmazeUI-2.7.2/js/iscroll.js"></script>
<script>
var ctx = '${ctx}';
var poId = '${poId}';
</script>
<script src="${ctx }/resources/js/eir.js"></script>
<script src="${ctx }/resources/js/po/poDetail.js"></script>
</html>
