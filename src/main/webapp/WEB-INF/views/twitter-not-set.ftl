<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Midnight Starlight</title>
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/style/default.css">
<#if env.acceptsProfiles("production")>
	<script src="${contextPath}/resources/js/jquery-1.11.0.min.js"></script>
	<script src="${contextPath}/resources/js/autolink-min.js"></script>
<#else>
	<script src="${contextPath}/resources/js/jquery-1.11.0.js"></script>
	<script src="${contextPath}/resources/js/autolink.js"></script>
</#if>
</head>
<body>
<h1>Midnight Starlight</h1>
<hr>
아직 트위터 설정이 되지 않아 홈페이지가 동작하지 않습니다.
twitter4j.default.properties를 참조하여 twitter4j.properties 파일을 작성해주시기 바랍니다.
<p>
해당 파일은 배포 시에 /WEB-INF/classes 안에 위치해야 하며 프로젝트 폴더에서는 /src/main/resources에 위치해있습니다.
<hr>
<div id="copyright">
&copy; 2014 Song Younghwan
</div>
</body>
</html>
