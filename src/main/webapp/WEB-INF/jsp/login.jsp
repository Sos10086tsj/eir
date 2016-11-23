<%@ page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/base/baselib.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Eir</title>
<meta name="description" content="login page">
<meta name="keywords" content="login">
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
<body data-type="login">
	<div class="am-g myapp-login">
		<div class="myapp-login-logo-block  tpl-login-max">
			<div class="myapp-login-logo-text">
				<div class="myapp-login-logo-text">
					EIR<span> Login</span> <i class="am-icon-skyatlas"></i>

				</div>
			</div>

<!-- 			<div class="login-font"> -->
<!-- 				<i>Log In </i> or <span> Sign Up</span> -->
<!-- 			</div> -->
			<c:if test="${not empty  errorMessage}">
				<div class="login-font">${errorMessage }</div>
			</c:if>
			<div class="am-u-sm-10 login-am-center">
				<form class="am-form" action="${ctx }/login" method="post">
					<fieldset>
						<div class="am-form-group">
							<input type="email" class="" id="doc-ipt-email-1" name="username"
								placeholder="请输入邮箱">
						</div>
						<div class="am-form-group">
							<input type="password" class="" id="doc-ipt-pwd-1" name="password"
								placeholder="请输入密码">
						</div>
						<p>
							<button type="submit" class="am-btn am-btn-default">登录</button>
						</p>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</body>
<script src="${ctx }/resources/component/jQuery/jquery.min.js"></script>
<script src="${ctx }/resources/component/AmazeUI-2.7.2/js/amazeui.min.js"></script>
</html>
