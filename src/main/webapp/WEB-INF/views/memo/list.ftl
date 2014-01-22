<#-- /memo/list에 대한 뷰 -->
<#assign mainDiv>
<h2>메모</h2>
<table>
<thead>
<tr>
	<th>작성자</th>
	<th>작성일시</th>
	<th>내용</th>
</tr>
</thead>
<#if items?size == 0>
	<tr>
		<td colspan="3">메모가 없습니다.</td>
	</tr>
<#else>
	<#list items as item>
		<tr>
			<td>${item.author?html}</td>
			<td>${item.writtenDate?string("yyyy-MM-dd HH:mm:ss")}</td>
			<td>
				${item.body?html}
				<input type="button" value="삭제" onclick="deleteItem(${item.id?js_string?html})">
			</td>
		</tr>
	</#list>
</#if>
</table>

<form method="post" action="add">
<table>
<tr>
	<th>작성자</th>
	<td><input type="text" name="author"></td>
</tr>
<tr>
	<th>내용</th>
	<td><textarea name="body"></textarea></td>
</tr>
<tr>
	<td colspan="2">
		<input type="submit" value="등록">
	</td>
</tr>
</table>
</form>

<script type="text/javascript">
function deleteItem(id) {
	location.href = '${rc.contextPath}/memo/delete?id=' + id;
}
</script>
</#assign>
<#include "/layout.ftl">
