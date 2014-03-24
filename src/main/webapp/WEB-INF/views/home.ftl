<#assign mainDiv>
<script type="text/javascript">
$(function() {
	$('.twitter_status_text').each(function() {
		var text = $(this).text().autoLink({ target: '_blank', class: 'autolink' });
		$(this).html(text);
	});
});
</script>
<h2>대문</h2>
<h3>인사말</h3>
제 홈페이지에 오신 것을 환영합니다.
즐거운 시간 보내시기 바랍니다.
<h3>트위터 최근 활동</h3>
<ul>
<#list twitterTimeline as status>
	<li>
		<#if status.retweet>
			<#assign retweet = status.retweetedStatus>
			<span class="twitter_retweet_message">리트윗</span>
			<img src="${retweet.user.miniProfileImageURL?html}">
			<span class="twitter_user_screen_name" title="${retweet.user.name?html}">
				@${retweet.user.screenName?html}</span>
			<span class="twitter_status_text">${retweet.text?html}</span>
		<#else>
			<img src="${status.user.miniProfileImageURL?html}">
			<span class="twitter_user_screen_name" title="${status.user.name?html}">
				@${status.user.screenName?html}</span>
			<span class="twitter_status_text">${status.text?html}</span>
		</#if>
	</li>
</#list>
</ul>
<h3>통계</h3>
전체 방문자 수: ${totalHits}<br>
오늘 방문자 수: ${todayHits}
</#assign>
<#include "/layout.ftl">
