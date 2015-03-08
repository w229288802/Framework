<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<div class="pageContent">
	<form method="post" onsubmit="return validateCallback(this,dialogAjaxDone);" class="pageForm required-validate" action="${actionPath}!save.action" >
		<input name="id" type="hidden"  value="${id }" />
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>角色名：</label>
				<input name="name" type="text" size="30" value="${name }" class="required" />
			</p>
			<p>
				<label>描述：</label>
				<input name="description" type="text" class="required" value="${description}" size="30"/>
			</p> 
			
			<div class="divider"></div>
			
			
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
