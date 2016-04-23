var QQSanGongDao={
		queryAll:function(sqlExecute){
			var sql="select * from qqsangong";
			var rs=sqlExecute.query({
				sql:sql,
				param:{},
				recordType:"object",
				resultType:"string"
			});
			return JSON.parse(rs);
		},
		queryAllWhat:function(sqlExecute){
			var sql="select what from qqsangong";
			var rs=sqlExecute.query({
				sql:sql,
				param:{},
				recordType:"object",
				resultType:"string"
			});
			return JSON.parse(rs);
		},
		queryById:function(sqlExecute,id){
			
		}
}