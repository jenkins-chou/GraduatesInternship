var reloadList = function() {
	layui.use([ 'layer' ], function() {
		var $ = layui.juery, layer = layui.layer;
		layer.closeAll();
		loadList(true);
	});
}
layui.use('layer', function() {
	var $ = layui.juery, layer = layui.layer;
	var index = '';
	reloadList(true);

});
var resetSwClose = function(isClose) {
	layui.use([ 'layer' ], function() {
		var $ = layui.jquery, layer = layui.layer;// 独立版的layer无需执行这一句
		if (isClose) {
			$('.layui-layer-setwin a.layui-layer-close1').hide();
		} else {
			$('.layui-layer-setwin a.layui-layer-close1').show();
		}
	});
}

var loadList = function(first) {layui.config({base : "js/"}).use([ 'form', 'layer', 'jquery', 'laypage', 'table' , 'upload'],
					function() {
						var form = layui.form, layer = layui.layer, laypage = layui.laypage, $ = layui.jquery;
						var table = layui.table;
						function getUParam(name, id) {
							var reg = new RegExp("(^|&)" + name
									+ "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
							var r = decodeURIComponent(
									$("#" + id).attr("src").substr(
											$("#" + id).attr("src")
													.indexOf("?")).substr(1))
									.match(reg); // 匹配目标参数
							if (r != null)
								return unescape(r[2]);
							return ""; // 返回参数值
						}
						var upload = layui.upload;
						var ctxPath = getUParam("ctx", "listjs");
						var user_id = getUParam("user_id", "listjs");

						table.render({
							elem : '#skill_list',
							url : ctxPath + '/personal_skill/getAllPersonalSkillByUserId?user_id='+user_id // 数据接口
							,
							cols : [ [ // 表头
							{
								field : 'skill_name',
								title : '技能名称'
							} 
							] ],
							height : 'full-80'

						});
						
						table.render({
							elem : '#cert_list',
							url : ctxPath + '/personal_cert/getAllPersonalCertByUserId?user_id='+user_id // 数据接口
							,
							cols : [ [ // 表头
							{
								field : 'cert_name',
								title : '证书名称'
							},{
								field : 'cert_time',
								title : '获得时间'
							} 
							] ],
							height : 'full-80'
						});
						
						table.render({
							elem : '#education_list',
							url : ctxPath + '/education_experience/getAllEducationExperienceByUserId?user_id='+user_id // 数据接口
							,
							cols : [ [ // 表头
							{
								field : 'education_school',
								title : '学校/机构'
							},{
								field : 'education_record',
								title : '主要经历'
							} ,{
								field : 'education_major',
								title : '专业学科'
							} 
							,{
								field : 'education_start_time',
								title : '开始时间'
							} 
							,{
								field : 'education_end_time',
								title : '结束时间'
							} 
							] ],
							height : 'full-80'
						});
						
					});
}
