<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" href="/resources/image/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>welcome to login!</title>
<!-- jquery -->
<script type='application/javascript' src='/resources/js/jquery-3.2.1.min.js'></script>
<!-- bootstrap -->
<link rel='stylesheet' href='/resources/bootstrap/css/bootstrap.min.css'>
<script type='application/javascript' src='/resources/bootstrap/js/bootstrap.min.js'></script>
<style type="text/css">
	.header {
		height: 30px;
		background-color: #e3e3e3;
	}
	
	.header ul {
		list-style: none;
		display: inline-block;
		float: right;
		margin-right: 50px;
		padding: 0;
	}
	
	.header ul li {
		display: list-item;
		float: left;
		width: 100px;
		height: 30px;
		line-height: 30px;
		text-align: center;
	}
	
	.header ul li span {
		margin-right: 5px;
	}
</style>
</head>
<body>
	<div>
		<div class="header">
			<span style="line-height: 30px; margin-left: 10px;">[valar morghulis]</span>
			<ul>
				<li><span class="glyphicon glyphicon-user"></span><a href="#">${username}</a></li>
				<li><span class="glyphicon glyphicon-log-out"></span><a href="/logout">退出</a></li>
			</ul>
		</div>
	</div>
</body>
</html>