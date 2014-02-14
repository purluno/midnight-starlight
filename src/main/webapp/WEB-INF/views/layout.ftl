<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>빠른 시작 프로젝트</title>
<script src="${rc.contextPath}/resources/js/jquery-1.11.0.js"></script>
</head>
<body>
<h1>빠른 시작 프로젝트</h1>
<div id="menu">
<a href="${rc.contextPath}/">처음 화면</a>
<a href="${rc.contextPath}/dice">주사위</a>
<a href="${rc.contextPath}/seasons">계절</a>
<a href="${rc.contextPath}/memo/list">메모</a>
<a href="${rc.contextPath}/securitySample/intro">보안 예제</a>
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
