<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<style type="text/css" media="screen">
.my-uploadify-button {
	background:none;
	border: none;
	text-shadow: none;
	border-radius:0;
}

.uploadify:hover .my-uploadify-button {
	background:none;
	border: none;
}

.fileQueue {
	width: 400px;
	height: 150px;
	overflow: auto;
	border: 1px solid #E5E5E5;
	margin-bottom: 10px;
}
</style>

<div class="pageContent">
	<div class="pageFormContent" layoutH="56">
		<p>
			<input id="testFileInput2" type="file" name="file" 
				uploaderOption="{
					swf:'${base}/resources/widget/dwz/uploadify/scripts/uploadify.swf',
					uploader:'${base }/upload',
					formData:{PHPSESSID:'xxx', ajax:1},
					queueID:'fileQueue',
					buttonImage:'${base}/resources/widget/dwz/uploadify/img/add.jpg',
					buttonClass:'my-uploadify-button',
					width:102,
					auto:false
				}"
			/>
		</p>
	</div>
	<div id="fileQueue" class="fileQueue"></div>
	<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="$('#testFileInput2').uploadify('upload', '*');">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" onclick="$('#testFileInput2').uploadify('cancel', '*');">取消</button></div></div>
				</li>
			</ul>
	</div>
</div>