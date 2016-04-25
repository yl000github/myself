/**
 * 每一个文件所公用的
 */
console={};
console.log=function(){
	logger.info(JSON.stringify(arguments));
}