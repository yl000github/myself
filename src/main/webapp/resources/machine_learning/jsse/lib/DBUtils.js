/**
 * 描述：数据库连接辅助类库
 */


// 统一库数据库连接获取
var sqlAdapterHandler = {
	sqlAdapter_nonTransaction : null,			// 统一库 sqlAdapter（不开启事务）
	sqlAdapter_transaction : null,				// 统一库sqlAdapter(开启事务)

	buildSqlAdapter : function(autoCommit){
		autoCommit = typeof autoCommit == 'boolean' ? autoCommit : false;
		require("ymt.jsse.sqlnew");
		var sqlAdapter = ymt.jsse.sqlnew.open("mydb", 'mydb_slave', autoCommit);
		return sqlAdapter;
	},
	
	getInstance : function(transaction){
		if(transaction){	//开启事务
			if(!this.sqlAdapter_transaction){
				this.sqlAdapter_transaction = this.buildSqlAdapter(true);
			}
			return this.sqlAdapter_transaction;
		}else{	//不开启事务
			if(!this.sqlAdapter_nonTransaction){
				this.sqlAdapter_nonTransaction = this.buildSqlAdapter(false);
			}
			return this.sqlAdapter_nonTransaction;
		}
	},
	
	commitAndClose : function(){
		if(this.sqlAdapter_transaction){
			this.sqlAdapter_transaction.commitAndClose();
			this.sqlAdapter_transaction = null;
		}else{
			logger.error("["+this.sqlAdapter_transaction+"]对象为空,无法手动提交事务!");
		}
	},

	rollbackAndClose : function(){
		if(this.sqlAdapter_transaction){
			this.sqlAdapter_transaction.rollbackAndClose();
			this.sqlAdapter_transaction = null;
		}else{
			logger.error("["+this.sqlAdapter_transaction+"]对象为空，无法回滚事务!");
		}
	}
};