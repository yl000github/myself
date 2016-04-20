<?php ?>
<html>
<head><meta charset="utf-8">
<style>
.content{
	width:100%;
}
</style>
</head>
<body>
<h2>data数据源</h2>
<input type="text" id="datasource">
<h2>controller的内容</h2>
<div id='controller' style="height:500px"></div>
<h2>domain的内容</h2>
<div id='domain' style="height:500px"></div>
<script type="text/javascript" src="js/juicer-min.js" ></script>
<script id="tpl-controller" type="text/template" >
<?php
require('template/controller.txt');
?>
</script>
<script id="tpl-domain" type="text/template" >
<?php
require('template/domain.txt');
?>
</script>
<script>
//将接口中的数据转换为对应的json对象，然后控制
// document.getElementById("datasource").onchange=function(){
// 	var raw=document.getElementById("datasource").value;
// 	var lines=raw.split("\n");
// 	for(var i=0;i<lines.length;i++){
// 		console.log(lines[i]);
// 	}
// }
var data={
	interfaceComment:"查询报价单列表",
	interfaceName:"listQuotation",
	domainFileName:"QuotationDomain",
	input:[
	       {name:"supplierID",comment:"供应商ID"},
	       {name:"cityID",comment:"城市ID"},
	       {name:"goodsID",comment:"商品ID"}
	],
	output:{
		
	}	
}

var params_build=function(data){
	var t="";
	for(var i=0;i<data.length;i++){
		t+=data[i].name;
		t+=",";
	}
	t=t.substring(0,t.length-1);
	return t;
}
juicer.register('params_build',params_build);
var tpl_controller = document.getElementById('tpl-controller').innerHTML;
var tpl_domain = document.getElementById('tpl-domain').innerHTML;
var html_controller = juicer(tpl_controller, data);
var html_domain = juicer(tpl_domain, data);
document.getElementById("controller").innerText=html_controller;
document.getElementById("domain").innerText=html_domain;
</script>
</body>
</html>