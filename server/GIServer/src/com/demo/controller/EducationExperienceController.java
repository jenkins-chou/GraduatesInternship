package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.models.EducationExperienceModel;
import com.demo.utils.PageJson;
import com.demo.utils.ParamUtil;
import com.demo.utils.RecordJson;
import com.demo.utils.StatusJson;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;

public class EducationExperienceController extends Controller {
	
	public void getEducationExperienceById(){
		EducationExperienceModel model = EducationExperienceModel.dao.findById(getPara("id"));
		renderJson(JsonKit.toJson(new RecordJson("200", "suc", model)));
	}
	
	public void getAllEducationExperience() {
		ParamUtil param = new ParamUtil(getRequest());
		Page<EducationExperienceModel> page = EducationExperienceModel.dao.paginate(param.getPageNumber(),
				param.getPageSize(), "select * ", "from education_experience where del != 'delete'");
		
		System.out.println(page.getList().toString());
		renderJson(JsonKit.toJson(new PageJson<EducationExperienceModel>("0", "", page)));
	}
	
	public void addEducationExperience(){
		try {
			EducationExperienceModel model = getModel(EducationExperienceModel.class, "", true);
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

	
	public void deleteEducationExperience(){
		try {
			EducationExperienceModel model = getModel(EducationExperienceModel.class, "", true);
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
	
	public void deleteSelectEducationExperience(){
		try {
			String[] ids = getParaValues("id");
			for (String id : ids) {
				EducationExperienceModel model = EducationExperienceModel.dao.findById(id);
				model.set("del", "delete");
				model.update();
			}
			renderJson(JsonKit.toJson(new StatusJson("200", "suc", true)));
		} catch (Exception e) {
			// TODO: handle exception
			renderJson(JsonKit.toJson(new StatusJson("500", "fail", true)));
		}
	}
	
	public void updateEducationExperience(){
		try {
			EducationExperienceModel model = getModel(EducationExperienceModel.class, "", true);
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
		render("list_education_experience.html");
	}
	
	//显示添加页
	public void showHtmlAdd() {
		render("add_education_experience.html");
	}
	
	//显示修改页
	public void showHtmlModify() {
		setAttr("id", getPara("id"));
		render("add_education_experience.html");
	}
}
