<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>ECharts</title>
<!-- 引入 echarts.js -->
<script src="js/jquery.min.js"></script>
<script src="js/echarts.min.js" charset="utf-8"></script>
</head>
<body>
<!-- 	<input type="butoon" text="refresh" id="r" /> -->
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div id="main" style="width: 600px; height: 400px;"></div>
	<div id="main-ano" style="width: 600px; height: 400px;"></div>
	<script >
		<?php
			require_once('DB.php');
			$sql="select * from qqsangong";
			$rs=DB::query($sql)->fetchAll();
			$rs=json_encode($rs);
			echo 'var rs='.$rs.';'
		?>
	
	</script>
	
	<script type="text/javascript">
		$(function() {
			init();
		})
		function init() {
			var c = rs;
			var date = [];
			var data = [];
			for(var i=0;i<c.length;i++){
				var e=c[i];
				date.push(e.create_time);
				data.push(e.what);
			}
			initEcharts(date, data);
			anoEcharts();
		}
		function initEcharts(date, data) {
			// 基于准备好的dom，初始化echarts实例
			var myChart = echarts.init(document.getElementById('main'));
			option = {
				tooltip : {
					trigger : 'axis'
				},
				title : {
					left : 'center',
					text : '大数据量折线图',
				},
				legend : {
					top : 'bottom',
					data : [ '意向' ]
				},
				toolbox : {
					show : true,
					feature : {
						dataView : {
							show : true,
							readOnly : false
						},
						magicType : {
							show : true,
							type : [ 'line', 'bar', 'stack', 'tiled' ]
						},
						restore : {
							show : true
						},
						saveAsImage : {
							show : true
						}
					}
				},
				xAxis : {
					type : 'category',
					boundaryGap : false,
					data : date
				},
				yAxis : {
					type : 'value',
					boundaryGap : [ 0, '100%' ]
				},
				dataZoom : [ {
					type : 'inside',
					start : 0,
					end : 10
				}, {
					start : 0,
					end : 10
				} ],
				series : [ {
					name : '模拟数据',
					type : 'line',
					smooth : true,
					symbol : 'none',
					sampling : 'average',
					itemStyle : {
						normal : {
							color : 'rgb(255, 70, 131)'
						}
					},
					areaStyle : {
						normal : {
							color : new echarts.graphic.LinearGradient(0, 0, 0,
									1, [ {
										offset : 0,
										color : 'rgb(255, 158, 68)'
									}, {
										offset : 1,
										color : 'rgb(255, 70, 131)'
									} ])
						}
					},
					data : data
				} ]
			};
			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
		}
		//另外一个较为客观的分析
		function anoEcharts(){
			var myChart = echarts.init(document.getElementById('main-ano'));
			var c = rs;
			var total=c.length;
			//生成理想曲线
			var xCount=10;
			var axis=[];
			for(var x=1;x<=xCount;x=x+0.01){
				var y=Math.pow(0.5,x)*total*0.5;
				var t=[x,y];
				axis.push(t);
			}
			console.dir(axis);
			//生成实际曲线
			var actualData = [];
			for(var i=0;i<=xCount;i++) actualData[i]=0;
			var b,cur;
			var sum=1;
			for(var i=0;i<c.length;i++){
				var e=c[i];
				cur=e.what;
				if(b){
					if(b==cur){
						sum++;
					}else{
						actualData[sum]++;
						sum=1;
					}
				}
				b=cur;
			}
			//构造数据
			var dataAll = [];
			console.dir(actualData)
			for(var i=0;i<actualData.length;i++){
				var t=[i,actualData[i]];
				dataAll.push(t);
			}

	       var markLineOpt = {
	           animation: false,
	           label: {
	               normal: {
	                   formatter: 'y = 0.5^x',
	                   textStyle: {
	                       align: 'right'
	                   }
	               }
	           },
	           lineStyle: {
	               normal: {
	                   type: 'solid'
	               }
	           },
	           tooltip: {
	               formatter: 'y = 0.5^x'
	           },
	           data: []
	       };
	       var lineOpt = {
	           animation: false,
	           lineStyle: {
	               normal: {
	                   type: 'solid',
	                   width:0.05
	               }
	           }
	       };

	       option = {
	           title: {
	               text: 'anaylise',
	               x: 'center',
	               y: 0
	           },
	           grid: [
	               {x: '7%', y: '7%', width: '100%', height: '80%'}
	           ],
	           tooltip: {
	               formatter: 'Group {a}: ({c})'
	           },
	           xAxis: [
	               {gridIndex: 0, min: 1, max: xCount,interval :1}
	           ],
	           yAxis: [
	               {gridIndex: 0, min: 0, max: total/2}
	           ],
	           series: [
	               {
	                   name: 'I',
	                   type: 'scatter',
	                   xAxisIndex: [0],
	                   yAxisIndex: [0],
	                   data: dataAll,
	                   markLine: markLineOpt
	               },
	               {
	                   name: 'II',
	                   type: 'line',
	                   xAxisIndex: [0],
	                   yAxisIndex: [0],
	                   data: axis,
	                   symbolSize: 1//完全靠模仿
	               }
	           ]
	       };
	       myChart.setOption(option);
		}
	</script>

</body>
</html>