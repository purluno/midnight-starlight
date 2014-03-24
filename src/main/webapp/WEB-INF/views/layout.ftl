<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Midnight Starlight</title>
<link rel="stylesheet" type="text/css" href="${rc.contextPath}/resources/style/default.css">
<#if env.acceptsProfiles("production")>
	<script src="${rc.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
	<script src="${rc.contextPath}/resources/js/autolink-min.js"></script>
<#else>
	<script src="${rc.contextPath}/resources/js/jquery-1.11.0.js"></script>
	<script src="${rc.contextPath}/resources/js/autolink.js"></script>
</#if>
</head>
<body>
<h1>Midnight Starlight</h1>
<div id="menu">
<a href="${rc.contextPath}/">대문</a>
<a href="${rc.contextPath}/profile">프로필</a>
<a href="${rc.contextPath}/dbconsole/">DB 콘솔</a>
</div>
<hr>
<#if submenuDiv??>
	<div id="submenu">
	${submenuDiv}
	</div>
	<hr>
</#if>
<div id="main">
${mainDiv}
</div>
<hr>
<div id="copyright">
&copy; 2014 Song Younghwan
</div>
</body>
</html>
