<#assign mainDiv>
<h2>방명록</h2>
<h3>작성하기</h3>
<form method="post" action="${contextPath}/guestbook/add">
	<#if userProfileImageUrl??>
		<img src="${userProfileImageUrl}" alt="${principal}">
	</#if>
	<textarea name="text"></textarea>
	<input type="submit" value="등록">
</form>
<h3>목록</h3>
</#assign>
<#include "/layout.ftl">
