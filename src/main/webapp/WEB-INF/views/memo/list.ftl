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
<tr>
	<td>홍길동</td>
	<td>2014-01-01</td>
	<td>가나다라마</td>
</tr>
</table>

<form>
<table>
<tr>
	<th>작성자</th>
	<td><input type="text"></td>
</tr>
<tr>
	<th>내용</th>
	<td><textarea></textarea></td>
</tr>
<tr>
	<td colspan="2">
		<input type="submit" value="등록">
	</td>
</tr>
</table>
</form>
</#assign>
<#include "/layout.ftl">
