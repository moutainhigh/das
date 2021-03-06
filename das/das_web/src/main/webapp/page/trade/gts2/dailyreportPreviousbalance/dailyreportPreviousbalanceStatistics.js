$(function() {
	dailyreportPreviousbalanceStatistics.init();
});

var dailyreportPreviousbalanceStatistics = {
	dataGridId: "dataGrid",
	searchFormId: "searchForm",
	/**
	 * 初始化
	 */
	init:function(){
		dailyreportPreviousbalanceStatistics.loadDataGrid();
	},
	/**
	 * 加载dataGrid
	 */
	loadDataGrid : function(){
		// 初始参数
		var url = BASE_PATH + 'tradeBordereauxController/findDailyreportPageList';
		var queryParams = {
		};
		queryParams['startTime'] = $("#startTimeSearch").datebox('getValue');
		queryParams['endTime'] = $("#endTimeSearch").datebox('getValue');
		queryParams['detailed'] = "1";
		
		var columns = [ 
			[
				{field : 'exectime', title : '日期',  sortable : true, width : 100},
				{field : 'mt4balance', title : 'MT4结余', sortable : false, width : 50},
				{field : 'gts2balance', title : 'GTS2结余', sortable : false, width : 50},
				{field : 'balance', title : '汇总', sortable : false, width : 50}
			]
			];
		$('#' + dailyreportPreviousbalanceStatistics.dataGridId).datagrid(easyui.defaultOption).datagrid({
			url : url,
			queryParams : queryParams,
			columns : columns,
			idField : 'rowKey', // 唯一字段
			sortName : 'exectime',
			sortOrder : 'desc',
            showFooter: true,
			onDblClickRow : function(rowIndex, rowData) {
			},
			onLoadSuccess : function(data) {
				$("div.datagrid-footer [class$='exectime']").text("小计：");
				
				dailyreportPreviousbalanceStatisticsChart.loadChartData();
				common.iFrameHeight();
			}
		});
	},
	getQueryParams: function() {
		var queryParams = $("#" + dailyreportPreviousbalanceStatistics.dataGridId).datagrid('options').queryParams;
		queryParams['startTime'] = $("#startTimeSearch").datebox('getValue');
		queryParams['endTime'] = $("#endTimeSearch").datebox('getValue');
		queryParams['detailed'] = "1";	
		return queryParams;
	},
	/**
	 * 条件查询
	 */
	find: function(){
		dailyreportPreviousbalanceStatistics.getQueryParams();
		common.loadGrid(dailyreportPreviousbalanceStatistics.dataGridId);
	},
	/**
	 * reset条件查询
	 */
	reset: function(){
		$('#' + dailyreportPreviousbalanceStatistics.searchFormId).form('reset');
		
		$('#startTimeSearch').datebox({
			formatter:easyui.formatterYYYYMMDD,
			parser:easyui.parserYYYYMMDD
		});
		$('#endTimeSearch').datebox({
			formatter:easyui.formatterYYYYMMDD,
			parser:easyui.parserYYYYMMDD
		});
		
		common.setEasyUiCss();
	},
	
	/**
	 * 导出excel
	 */
	exportExcel : function(){
		if(!common.exportExcelValidate(this.dataGridId)){
			return;
		}
		var queryParams = dailyreportPreviousbalanceStatistics.getQueryParams();
		queryParams['sort'] = "exectime";
		queryParams['order'] = "desc";
		queryParams['reportType'] = 3;
		var url = BASE_PATH + "tradeBordereauxController/exportExcelDailyreport?";
		url = common.formatUrl(url, queryParams);
		window.location.href = url;
	}
}

