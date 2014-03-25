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
<div id="menu">
<a href="${contextPath}/">대문</a>
<a href="${contextPath}/profile">프로필</a>
<a href="${contextPath}/guestbook">방명록</a>
<a href="${contextPath}/dbconsole/">DB 콘솔</a>
<#if principal??>
	<#if userProfileImageUrl??>
		<img src="${userProfileImageUrl}" alt="사용자 프로필 사진">
	</#if>
	${principal}
	<a href="${contextPath}/logout">로그아웃</a>
<#else>
	<a href="${contextPath}/sign-in-with-twitter"><img src="${contextPath}/resources/images/sign-in-with-twitter-gray.png" alt="Sign in with Twitter"></a>
</#if>
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
