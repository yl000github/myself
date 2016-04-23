var QQSanGongService={
		
	init:function(){
	},
	queryAll:function(){
		load("/lib/DBUtils.js");
		load("/dao/QQSanGongDao.js");
		var sqlExecute=sqlAdapterHandler.getInstance(false);
		var rs=QQSanGongDao.queryAll(sqlExecute);
		return rs;
	},
	queryAllWhat:function(){
		load("/lib/DBUtils.js");
		load("/dao/QQSanGongDao.js");
		var sqlExecute=sqlAdapterHandler.getInstance(false);
		var rs=QQSanGongDao.queryAllWhat(sqlExecute);
		//[{"what":"0"}]转换为[0]
		var arr=[];
		for(var i=0;i<rs.length;i++){
			arr.push(rs[i].what);
		}
		return arr;
	}
}