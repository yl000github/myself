angular.module('myApp',[]).controller('userCtrl',function($scope,$http){
	//页面初始化
	$http.get("/myself/affair/getAll")
	.success(function(res){
		console.log(res);
		$scope.rawData=res;
		if(!$scope.rawData.code){
			$scope.rawAffairs=$scope.rawData.data;
			$scope.affairs=[];
			$scope.dialogShow=true;
			$scope.dialogContent='没有事务';
		}
		$scope.affairs=$scope.rawData.data;
//		console.log($scope.affairs);
		$scope.dialogShow=false;
		$scope.dialogContent='一切ok';
		
	});
	$http.get('/myself/affair/getTypes')
	.success(function(res){
		console.log(res);
		if(res.code){
			$scope.rawTypes=res.data;
			$scope.dialogShow=false;
			$scope.dialogContent='一切ok';
		}else{
			$scope.dialogShow=true;
			$scope.dialogContent=res.msg;
		}
	});
//	$scope.res='{"code":1,"msg":null,"data":[{"id":1,"what":"bootstrap学习","why":"为了不断的进步","how":"coding","comment":null,"status":"0","createTime":null,"startTime":null,"doneTime":null,"duration":null},{"id":2,"what":"angularjs 学习","why":"全新的数据到view的映射","how":"coding","comment":null,"status":"0","createTime":null,"startTime":null,"doneTime":null,"duration":null}]}';
//	$scope.rawData=JSON.parse($scope.res);
	
	//新增
	$scope.createFn=function(){
		console.log($scope.newAffair);
		$http.post('/myself/affair/createOne',{
			what:$scope.newAffair.what,
			why:$scope.newAffair.why,
			how:$scope.newAffair.how,
			comment:$scope.newAffair.comment
		}).success(function(res){
			if(res.code){
				$("#create-modal").modal('hide');
				$scope.dialogShow=true;
				$scope.dialogContent="成功";
			}else{
				$("#create-modal").modal('hide');
				$scope.dialogShow=true;
				$scope.dialogContent=res.msg;
			}
		});
	}
	//开始
	$scope.setCurId=function(id){
//		$log.info(id);
		console.log(id);
		$scope.curId=id;
	}
	$scope.startFn=function(){
		console.log($scope.newAffair);
		$http.post('/myself/affair/startOne',{
			id:$scope.curId,
			how:$scope.startAffair.how,
		}).success(function(res){
			if(res.code){
				$("#start-modal").modal('hide');
				$scope.dialogShow=true;
				$scope.dialogContent="成功";
			}else{
				$("#start-modal").modal('hide');
				$scope.dialogShow=true;
				$scope.dialogContent=res.msg;
			}
		});
	}
	
	//完成
	$scope.completeFn=function(){
		$http.post('/myself/affair/completeOne',{
			id:$scope.curId,
			comment:$scope.completeAffair.comment,
		}).success(function(res){
			if(res.code){
				$("#complete-modal").modal('hide');
				$scope.dialogShow=true;
				$scope.dialogContent="成功";
			}else{
				$("#complete-modal").modal('hide');
				$scope.dialogShow=true;
				$scope.dialogContent=res.msg;
			}
		});
	}
	//终止
	$scope.terminateFn=function(){
		$http.post('/myself/affair/terminateOne',{
			id:$scope.curId,
			reason:$scope.terminateAffair.reason,
		}).success(function(res){
			if(res.code){
				$("#terminate-modal").modal('hide');
				$scope.dialogShow=true;
				$scope.dialogContent="成功";
			}else{
				$("#terminate-modal").modal('hide');
				$scope.dialogShow=true;
				$scope.dialogContent=res.msg;
			}
		});
	}
	//根据状态筛选
	$scope.changeStatus=function(code){
		if(code==-1){
			$scope.affairs=$scope.rawAffairs;
		}
		var arr=new Array();
		for(var i=0;i<$scope.rawAffairs.length;i++){
			if($scope.rawAffairs[i].status==code){
				arr.push($scope.rawAffairs[i]);
			}
		}
		$scope.affairs=arr;
	}
}
)