package com.demo.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.demo.models.RecruitModel;
import com.demo.utils.PageJson;
import com.demo.utils.ParamUtil;
import com.demo.utils.RecordJson;
import com.demo.utils.StatusJson;
import com.demo.utils.StringUtil;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;

public class RecruitController extends Controller {
	
	public void getRecruitById(){
		RecruitModel model = RecruitModel.dao.findById(getPara("id"));
		renderJson(JsonKit.toJson(new RecordJson("200", "suc", model)));
	}
	
	public void getAllRecruit() {
		System.out.println(getPara("id"));
		ParamUtil param = new ParamUtil(getRequest());
		Page<RecruitModel> page = RecruitModel.dao.paginate(param.getPageNumber(),
				param.getPageSize(), "select * ", "from Recruit where del != 'delete'");
		renderJson(JsonKit.toJson(new PageJson<RecruitModel>("0", "", page)));
	}
	
	/**
	 * 分页获取全部招聘信息
	 **/
	public void getAllRecruitMobile() {
		int pagenum = Integer.parseInt(getPara("pageNum")!=null?getPara("pageNum"):"0");
		ParamUtil param = new ParamUtil(getRequest());
		Page<RecruitModel> page = RecruitModel.dao.paginate(pagenum<=0?param.getPageNumber():pagenum,
				param.getPageSize(), "select * ", "from Recruit where del != 'delete' order by id desc");
		renderJson(JsonKit.toJson(new PageJson<RecruitModel>("200", "", page)));
	}
	
	/**
	 * 获取筛选招聘信息
	 */
	public void getFilterRecruitMobile(){
		String working_day = getPara("working_day");
		String salary = getPara("salary");
		System.out.println(working_day);
		System.out.println(salary);
		
		String sqlPart1 = "select * ";
		String sqlPart2 = "from Recruit where ";
		
		//两个同事为空
		if(!StringUtil.isNotEmpty(working_day)&&!StringUtil.isNotEmpty(salary)){
			sqlPart2 += " del != 'delete' order by id desc";
		}else{
			if(StringUtil.isNotEmpty(working_day)){
				sqlPart2 += " working_day = '"+working_day+"'";
				if(StringUtil.isNotEmpty(salary)){
					sqlPart2 += " and salary = '"+salary+"'";
				}
			}else{
				if(StringUtil.isNotEmpty(salary)){
					sqlPart2 += " salary = '"+salary+"'";
				}
			}
			sqlPart2 += " and del != 'delete' order by id desc";
		}
		
		System.out.println(sqlPart1+sqlPart2);
		
		int pagenum = Integer.parseInt(getPara("pageNum")!=null?getPara("pageNum"):"0");
		ParamUtil param = new ParamUtil(getRequest());
		Page<RecruitModel> page = RecruitModel.dao.paginate(pagenum<=0?param.getPageNumber():pagenum,
				param.getPageSize(), sqlPart1, sqlPart2);
		renderJson(JsonKit.toJson(new PageJson<RecruitModel>("200", "", page)));
		
	}

	/**
	 * 搜索接口
	 */
	public void searchRecruitMobile(){
		String keyword = getPara("keyword");
		
		String sql = "select * from recruit where job_name like '%"+keyword+"%' or job_content like '%"+keyword+"%' and del != 'delete' order by id DESC";
		
		System.out.println(sql);
		List<RecruitModel> datas = RecruitModel.dao.find(sql);
		System.out.println(datas);
		
		JSONObject js = new JSONObject();
		js.put("code", "200");
		js.put("message", "");
		js.put("data", datas);
		renderJson(JsonKit.toJson(js));
	}
	
	public void addRecruit(){
		try {
			RecruitModel model = getModel(RecruitModel.class, "", true);
			
			model.set("create_time", System.currentTimeMillis()/1000+"");
			System.out.println("model:"+model);
			model.save();
			JSONObject js = new JSONObject();
			js.put("code", "200");
			renderJson(js.toJSONString());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			JSONObject js = new JSONObject();
			js.put("code", 500);
			js.put("msg", e.toString());
			renderJson(js.toJSONString());
		}
	}

	
	public void deleteRecruit(){
		try {
			RecruitModel model = getModel(RecruitModel.class, "", true);
			model.set("del", "delete");
			model.update();
			JSONObject js = new JSONObject();
			js.put("code", "200");
			renderJson(js.toJSONString());
		} catch (Exception e) {
			// TODO: handle exception
			JSONObject js = new JSONObject();
			js.put("code", 500);
			js.put("msg", e.toString());
			renderJson(js.toJSONString());
		}
	}
	
	public void deleteSelectRecruit(){
		try {
			String[] ids = getParaValues("id");
			for (String id : ids) {
				RecruitModel model = RecruitModel.dao.findById(id);
				model.set("del", "delete");
				model.update();
			}
			renderJson(JsonKit.toJson(new StatusJson("200", "suc", true)));
		} catch (Exception e) {
			// TODO: handle exception
			renderJson(JsonKit.toJson(new StatusJson("500", "fail", true)));
		}
	}
	
	public void updateRecruit(){
		try {
			RecruitModel model = getModel(RecruitModel.class, "", true);
			System.out.println(model.toJson());
			model.update();
			JSONObject js = new JSONObject();
			js.put("code", "200");
			renderJson(js.toJSONString());
		} catch (Exception e) {
			// TODO: handle exception
			JSONObject js = new JSONObject();
			js.put("code", 500);
			js.put("msg", e.toString());
			renderJson(js.toJSONString());
		}
	}
	
	//显示列表
	public void showHtmlList() {
		render("list_recruit.html");
	}
	
	//显示添加页
	public void showHtmlAdd() {
		render("add_recruit.html");
	}
	
	//显示修改页
	public void showHtmlModify() {
		setAttr("id", getPara("id"));
		render("add_recruit.html");
	}
}
