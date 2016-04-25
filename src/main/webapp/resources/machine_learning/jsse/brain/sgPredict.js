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
	logger.info("=========开始读取");
	var net=new brain.NeuralNetwork();
	net.fromJSON(json);
	logger.info("=========读取成功");
	var period=100;
	//获取最近99次的结果
	var rs=QQSanGongService.queryAllWhat();
	logger.info("======="+JSON.stringify(rs));
	var arr=[];
	for(var i=rs.length-period+1;i<rs.length;i++){
		arr.push(rs[i]);
	}
	//推断下一次
	logger.info("=========准备推断:"+arr.length);
	logger.info("=========准备推断:"+arr);
	var output=net.run(arr);
	var c=output[0]>0.5?1:0;
	logger.info("=========推断成功:"+output);
	response(1,null,c);
	logger.info("=========结束");
})($_request_param_$,$_request_header_$);