<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Midnight Starlight</title>
<#if env.acceptsProfiles("production")>
	<script src="${rc.contextPath}/resources/js/jquery-1.11.0.min.js"></script>
<#else>
	<script src="${rc.contextPath}/resources/js/jquery-1.11.0.js"></script>
</#if>
</head>
<body>
<h1>Midnight Starlight</h1>
<div id="menu">
<a href="${rc.contextPath}/">대문</a>
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
