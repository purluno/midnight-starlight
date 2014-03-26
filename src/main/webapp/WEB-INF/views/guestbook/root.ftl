<#assign mainDiv>
<script type="text/javascript">
var first = 0;

$(function() {
	$('.load_button').click(function() {
		var url = '${contextPath}/guestbook/ajax/list.json';
		var reqData = {'first': first};
		$.getJSON(url, reqData, function(data) {
			if (data.items.length == 0) {
				$('.load_button').css('display', 'none');
				return;
			}
			for (var i = 0; i < data.items.length; i++) {
				var item = data.items[i];
				var $e = $('#guestbook_item').clone();
				$e.find('.guestbook_item_author_id').text(item.authorId);
				$e.find('.guestbook_item_author_name').text(item.authorName);
				$e.find('.guestbook_item_author_profile_image').attr('src', item.authorProfileImageUrl);
				$e.find('.guestbook_item_text').text(item.text);
				$e.css('display', 'block');
				$e.appendTo('#guestbook_list');
			}
			first += data.items.length;
		});
	});
	$('.load_button').click();
});
</script>
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
<div id="guestbook_list">
</div>
<input type="button" class="load_button" value="더 불러오기">
<div id="guestbook_item" style="display: none">
	<span class="guestbook_item_author_id">ID</span>
	<span class="guestbook_item_author_name">name</span>
	<img class="guestbook_item_author_profile_image">
	<span class="guestbook_item_text">text</span>
</div>
</#assign>
<#include "/layout.ftl">
