<%@ include file="/common/taglib.jsp" %>
<div class="pageContent"> 
	<form action="${base }/sys/menu/sys-menu!save.action" method="post" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<table>
				<tr>
				<td><input name="name" class="required" type="text"> </td>
				</tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>