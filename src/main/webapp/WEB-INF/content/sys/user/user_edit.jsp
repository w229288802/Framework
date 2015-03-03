<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<div class="pageContent">
	<form method="post" onsubmit="return validateCallback(this,dialogAjaxDone);" class="pageForm required-validate" action="${actionPath}!saveWithResponse.action" >
		<input name="id" type="hidden"  value="${id }" />
		<div class="pageFormContent" layoutH="56">
			 <p>
				<label>用户名：</label>
				<input name="username" type="text" size="30" value="${username }" class="required" />
			</p>
			<p>
				<label>姓名：</label>
				<input name="name" class="required" value="${name }" type="text" size="30"  />
			</p> 
			<p>
				<label>密码：</label>
				<input name="password" class="required" value="${password }" type="password" size="30"  />
			</p> 
			<p>
				<label>类型：</label>
				<%-- <input name="type" class="required" value="${type }" type="text" size="30"  /> --%>
				<select  class="required combox" size="30">
					<option value="A">激活</option>
					<option value="B">停用</option>
				</select>
			</p> 
			<p>
				<label>手机：</label>
				<input name="phone" class="required" value="${phone }" type="text" size="30"  />
			</p> 
			<p>
				<label>邮箱：</label>
				<input name="email" class="required" value="${email }" type="text" size="30"  />
			</p> 
			<p>
				<label>部门：</label>
				<input name="departmentId" class="required" value="${departmentId }" type="text" size="30"  />
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
