<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" href="/resources/image/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login page</title>
<!-- jquery -->
<script type='application/javascript' src='/resources/js/jquery-3.2.1.min.js'></script>
<!-- bootstrap -->
<link rel='stylesheet' href='/resources/bootstrap/css/bootstrap.min.css'>
<script type='application/javascript' src='/resources/bootstrap/js/bootstrap.min.js'></script>
<style type="text/css">
	.loginForm {
		width: 500px;
		height: 300px;
		border: solid 1px #4f9ec3;
		margin-top: 100px;
	}
	
	.loginForm #header {
		height: 70px;
		background-color: #4f9ec3;
		line-height: 70px;
		padding-left: 20px;
		font-size: 25px;
		color: #ffffff;
	}
	
	.loginForm form >label {
		width: 100px;
		height: 30px;
		text-align: right;
	}
	
	.loginForm form >input {
		width: 300px;
		height: 30px;
		text-indent: 4px;
	}
	
	.loginForm .form-btn {
		width: 100px;
		height: 30px;
		margin-left: 100px;
	}
</style>
</head>
<body>
	<div style="width: 500px; margin: 0 auto;">
		<div class="loginForm">
			<div id="header"><span class="glyphicon glyphicon-user"></span> 用户登录</div>
			<div style="padding: 10px; margin-top: 30px;">
				<form action="/login" method="post">
					<label>账号：</label><input type="text" id="username" name="username"><br>
					<label>密码：</label><input type="password" id="password" name="password"><br>
					<#if loginFail??>
						<span style="color: red; margin-left: 100px; font-size: 12px;">登录失败：${loginFail}</span>
					</#if>
					<div style="margin-top: 30px;">
						<input type="submit" value="提交" class="form-btn" id="submit">
						<input type="reset" value="重置" class="form-btn">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script>
	$('#submit').click(function() {
		if ($('#username').val() == '') {
			alert('login-error: username is empty');
			return false;
		}
		if ($('#password').val() == '') {
			alert('login-error: password is empty');
			return false;
		}		
	})
</script>
</html>