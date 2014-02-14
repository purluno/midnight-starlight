<#assign mainDiv>
<h2>사람들 목록</h2>
<div>
<a href="${rc.contextPath}/people/initialize">초기화</a>
<a href="${rc.contextPath}/people/list">목록</a>
</div>

<h3>개인별</h3>
<ul>
<#list people as person>
	<li>${person.family.name}${person.name} ${person.gender}
</#list>
</ul>

<h3>가문별</h3>
<ul>
<#list families as family>
	<li>${family.name}
	<ul>
	<#list family.people as person>
		<li>${family.name}${person.name} ${person.gender}
	</#list>
	</ul>
</#list>
</ul>
</#assign>
<#include "/layout.ftl">
