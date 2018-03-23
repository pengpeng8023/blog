<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<link href="${ctx}/staticfile/css/login.css" rel="stylesheet">
	<script src="${ctx}/staticfile/js/bootstrap.js"></script>
	<script>
		/*$().ready(function() {
		 $("#page").animate({
		 top : "110px"
		 }, "slow", function() {
		 $("#content").css("visibility", "visible");
		 })
		 });*/
        function formSubmit(url,sTarget){
            document.forms[0].target = sTarget;//target:在哪里展现新页面,_self:在当前页面展示.
            document.forms[0].action = url;		//相对路径
            document.forms[0].submit();			//表单元素提交
            return true;
        }
	</script>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">菜鸟科技</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">主页</a></li>
				<li><a href="#about">联系方式</a></li>
				<li><a href="#contact">关于我们</a></li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">产品 <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">c语言菜鸟</a></li>
						<li><a href="#">java菜鸟</a></li>
						<li><a href="#">c++菜鸟</a></li>
						<li role="separator" class="divider"></li>
						<li class="dropdown-header">其他菜鸟</li>
						<li><a href="#">挖掘机菜鸟</a></li>
						<li><a href="#">厨师菜鸟</a></li>
					</ul>
				</li>
			</ul>
			<div class="navbar-form navbar-right">
				<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">登录</button>
			</div>
		</div><!--/.navbar-collapse -->
	</div>
</nav>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times
				</button>
				<h4 class="modal-title" id="myModalLabel">
					登录
				</h4>
			</div>
			<form action="/_login.action" method="post">
				<div class="modal-body">
					<div id="page">
						<div id="content" align="center">
							<div class="c1">
								<input type="text" name="userName" placeholder="username" />
							</div>
							<div class="c1">
								<input type="password" name="password" placeholder="password" />
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<input type="submit" class="btn btn-primary">
					<!--<button type="button" class="btn btn-primary" onsubmit="">登录</button>-->
				</div>
			</form>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

<div id="myCarousel" class="carousel slide" data-ride="carousel">
	<!-- Indicators -->
	<ol class="carousel-indicators">
		<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		<li data-target="#myCarousel" data-slide-to="1"></li>
		<li data-target="#myCarousel" data-slide-to="2"></li>
	</ol>
	<div class="carousel-inner" role="listbox">
		<div class="item active">
			<img class="first-slide" src="staticfile/image/login/banner1.jpg" alt="First slide">
			<div class="container">
				<div class="carousel-caption">
					<h1>欢迎加入</h1>
					<p>菜鸟科技公司</p>
					<p><a class="btn btn-lg btn-primary" href="#" role="button">加入</a></p>
				</div>
			</div>
		</div>
		<div class="item">
			<img class="second-slide" src="staticfile/image/login/banner2.png" alt="Second slide">
			<div class="container">
				<div class="carousel-caption">
					<h1>公司概况</h1>
					<p>公司概况</p>
					<p><a class="btn btn-lg btn-primary" href="#" role="button">公司</a></p>
				</div>
			</div>
		</div>
		<div class="item">
			<img class="third-slide" src="staticfile/image/login/banner4.jpg" alt="Third slide">
			<div class="container">
				<div class="carousel-caption">
					<h1>产品</h1>
					<p>我们的产品</p>
					<p><a class="btn btn-lg btn-primary" href="#" role="button">产品</a></p>
				</div>
			</div>
		</div>
	</div>
	<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
		<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
		<span class="sr-only">Previous</span>
	</a>
	<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
		<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
		<span class="sr-only">Next</span>
	</a>
</div><!-- /.carousel -->

<div class="container marketing">

	<!-- Three columns of text below the carousel -->
	<div class="row">
		<div class="col-lg-4">
			<img class="img-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" width="140" height="140">
			<h2>优势</h2>
			<p>质量优良</p>
			<p><a class="btn btn-default" href="#" role="button">了解详情 &raquo;</a></p>
		</div><!-- /.col-lg-4 -->
		<div class="col-lg-4">
			<img class="img-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" width="140" height="140">
			<h2>销量广</h2>
			<p>远销全球</p>
			<p><a class="btn btn-default" href="#" role="button">了解详情 &raquo;</a></p>
		</div><!-- /.col-lg-4 -->
		<div class="col-lg-4">
			<img class="img-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" width="140" height="140">
			<h2>公司构成</h2>
			<p>全部是高精尖人才</p>
			<p><a class="btn btn-default" href="#" role="button">了解详情 &raquo;</a></p>
		</div><!-- /.col-lg-4 -->
	</div><!-- /.row -->

</div>
</body>
</html>


