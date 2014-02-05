<#import "/spring.ftl" as spring/>
<#assign mainDiv>
<h2>보안 예제</h2>

<h3>로그인/로그아웃</h3>

<#if subject.principal??>
	로그인한 계정이름: ${subject.principal}
	<form method="post" action="${rc.contextPath}/securitySample/logout">
		<input type="submit" value="로그아웃">
	</form>
<#else>
	<form method="post" action="${rc.contextPath}/securitySample/login">
	<table>
	<tr>
		<th>계정이름</th>
		<td><input type="text" name="username"></td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td><input type="password" name="password"></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="로그인">
		</td>
	</tr>
	</table>
	</form>
</#if>

<ul>
	<li>john / john100 - "known-guest" (알려진 손님)
	<li>alice / alice100 - "admin" (관리자)
</ul>

<h3>테스트용 페이지</h3>

<ul>
	<li><a href="${rc.contextPath}/securitySample/allowAnonymous">로그인 안한 사람도 들어갈 수 있는 페이지</a>
	<li><a href="${rc.contextPath}/securitySample/forKnown">로그인한 사람 전용 페이지(관리자 포함)</a>
	<li><a href="${rc.contextPath}/securitySample/adminOnly">관리자 전용 페이지</a>
</ul>
</#assign>
<#include "/securitySample/layout.ftl">
