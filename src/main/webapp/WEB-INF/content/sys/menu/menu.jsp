<%@ include  file="/common/taglib.jsp"%>
<style type="text/css">
	ul.rightTools {float:right; display:block;}
	ul.rightTools li{float:left; display:block; margin-left:5px}
</style>
<div class="pageContent">
<div class="panel" defH="40">
		<h1>病人基本信息</h1>
		<div>
			病人编号：<input type="text" name="patientNo" />
			<ul class="rightTools">
				<li><a class="button" target="dialog" href="demo/pagination/dialog1.html" mask="true"><span>创建病例</span></a></li>
				<li><div class="buttonDisabled"><div class="buttonContent"><button>病人治疗流程</button></div></div></li>
				<li><div class="buttonDisabled"><div class="buttonContent"><button>按病人编号检索病例</button></div></div></li>
				<li><div class="buttonDisabled"><div class="buttonContent"><button>从病人列表选取病例</button></div></div></li>
			</ul>
		</div>
</div>
<div class="panel" defH="40">	

	<%-- <div layoutH="146" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
		<ul class="tree " oncheck="kkk">
			<li><a href="${base }/sys/department/department.action" rel="jbsxBox"  target="ajax" tname="name" tvalue="value" checked="true">第一级菜单项 A</a>
			<ul>
				<li><a href="#" tname="name" tvalue="value" checked="true">第二级菜单项 A </a></li>
				<li><a href="#">第二级菜单项 B </a></li>
				<li><a href="#">第二级菜单项 C </a>
					<ul>
						<li><a href="#">第三级菜单项 A </a></li>
						<li><a href="#">第三级菜单项 B </a></li>
					</ul>
				</li>
			</ul>
			</li>
			<li><a href="#">第一级菜单项 B</a></li>
		</ul>
	</div>
	<div id="jbsxBox" class="unitBox" style="margin-left:246px;">
	</div> --%>
</div>
</div>
	


	

