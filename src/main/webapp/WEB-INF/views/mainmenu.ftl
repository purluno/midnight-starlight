<a href="${contextPath}/">대문</a>
<a href="${contextPath}/profile">프로필</a>
<a href="${contextPath}/guestbook">방명록</a>
<a href="${contextPath}/dashboard">대시보드</a>
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
