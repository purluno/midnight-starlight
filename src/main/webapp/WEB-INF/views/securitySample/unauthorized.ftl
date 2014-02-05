<#assign mainDiv>
<h2>접근 권한 없음</h2>
접근 권한이 없는 페이지에 접근을 시도했습니다.
<input type="button" value="처음화면으로" onclick="location.href = '${(rc.contextPath + '/')!js_string!html}'">
</#assign>
<#include "/securitySample/layout.ftl">
