(function(request,header){
	load("/lib/common.js");
	load("/lib/response.js");
	load("/lib/brain-0.6.3.min.js");
	//联系y=2x+1  100组输入，测试输出
	var trainData=[];
	for(var i=0;i<1000;i++){
		var o=2*i+1;
		trainData.push({input:[i],output:[o]});
	}
	var net=new brain.NeuralNetwork();
	logger.info("==========="+JSON.stringify(trainData))
	logger.info("============准备学习");
	net.train(trainData);
	logger.info("============学习完成");
	logger.info(JSON.stringify(net.run([5])));
	logger.info(JSON.stringify(net.run([5.1])));
	logger.info(JSON.stringify(net.run([5.2])));
	logger.info(JSON.stringify(net.run([5.3])));
	logger.info(JSON.stringify(net.run([5.4])));
	logger.info(JSON.stringify(net.run([6])));
	response(1,null,"success");
	
})($_request_param_$,$_request_header_$);