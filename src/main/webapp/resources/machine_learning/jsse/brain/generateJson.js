(function(request,header){
	load("/lib/brain-0.6.3.min.js");
	load("/lib/response.js");
	load("/service/QQSanGongService.js");
	
	var rs=QQSanGongService.queryAllWhat();
	var period=100;
	logger.info("======="+rs.length)
	if(rs&&(rs.length<=period)){
		var msg="数据太少，没法学习";
		response(0,msg);
		return;
	}
	//学习
	var trainData=[];
	for(var i=0;i<rs.length-period;i++){
		var input=rs.slice(i,i+period-1);
		var output=rs.slice(i,i+period);
		trainData.push({input:input,output:output});
	}
	var net=new brain.NeuralNetwork();
	net.train(trainData);
	//存到文件中去
	var json=net.toJSON();
	var txt=JSON.stringify(json);
	require("ymt.jsse.FileEnabler");
	var fileEnabler=ymt.jsse.FileEnabler;
	require("ymt.jsse.ConfigEnabler");
	var config = ymt.jsse.ConfigEnabler.readPropertyConfig("config.properties");
	var path=config.getProperty("sangong-brain");
	fileEnabler.saveContent2File(path,txt);
	response(1,null,{
		content:"success"
	});
})($_request_param_$,$_request_header_$);