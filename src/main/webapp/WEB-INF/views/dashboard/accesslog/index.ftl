<#assign mainDiv>
<h2>대시보드: 접근 로그</h2>
검색어 <input type="text">
<table class="accesslog">
<thead>
<th>순번</th>
<th>일시</th>
<th>호스트</th>
<th>세션</th>
<th>사용자</th>
<th>종류</th>
<th>세부사항</th>
</thead>
<tbody>
<#list pagedResult.result as entry>
	<tr>
		<td>${pagedResult.totalCount - pagedResult.first - entry_index}</td>
		<td>${entry.when?string("yyyy-MM-dd HH:mm:ss.SSS")}</td>
		<td>${entry.sessionInfo.host!""}</td>
		<td>
			<#if entry.sessionInfo.id??>
				<span title="${entry.sessionInfo.id}">
					${entry.sessionInfo.id?substring(0, 5)}...
				</span>
			</#if>
		</td>
		<td>${(entry.user.principal)!""}</td>
		<td>${entry.type}</td>
		<td>
			<#if entry.type == "요청">
				${entry.method!""} ${entry.url!""}
			<#elseif entry.type == "로그인 시도">
				${(entry.principal)!""}
			<#else>
				${entry.description!""}
			</#if>
		</td>
	</tr>
</#list>
</tbody>
<tfoot>
	<td colspan="7">
		<#list pagedResult.pageIterator as page>
			<#if page.current>
				<strong>${page}</strong>
			<#else>
				<a href="${contextPath}/dashboard/accesslog?page=${page}">${page}</a>
			</#if>
		</#list>
	</td>
</tfoot>
</table>
</#assign>
<#include "../layout.ftl">
