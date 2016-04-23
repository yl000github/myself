(function(request,header){
	load("/lib/brain-0.6.3.min.js");
	load("/lib/response.js");
	load("/service/QQSanGongService.js");
	require("ymt.jsse.FileEnabler");
	require("ymt.jsse.ConfigEnabler");
	var fileEnabler=ymt.jsse.FileEnabler;
	//获得文件路径
	var config = ymt.jsse.ConfigEnabler.readPropertyConfig("config.properties");
	var path=config.getProperty("sangong-brain");
	logger.info("========="+path);
	//读取学到的东西
	var txt=fileEnabler.readFileContent({
		srcPath:path
	});
	var json=JSON.parse(txt);
	var net=new brain.NeuralNetwork();
	net.fromJSON(json);
	var period=100;
	//获取最近99次的结果
	var rs=QQSanGongService.queryAllWhat();
	logger.info("======="+JSON.stringify(rs));
	var arr=[];
	for(var i=rs.length-period+1;i<rs.length;i++){
		arr.push(rs[i]);
	}
	//推断下一次
	var output=net.run({
		input:arr
	});
	response(1,null,{
		content:output
	});
})($_request_param_$,$_request_header_$);