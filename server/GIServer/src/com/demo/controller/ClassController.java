package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.models.ClassModel;
import com.demo.utils.PageJson;
import com.demo.utils.ParamUtil;
import com.demo.utils.RecordJson;
import com.demo.utils.StatusJson;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;

public class ClassController extends Controller {
	
	public void getClassById(){
		ClassModel model = ClassModel.dao.findById(getPara("id"));
		renderJson(JsonKit.toJson(new RecordJson("200", "suc", model)));
	}
	
	public void getAllClass() {
		ParamUtil param = new ParamUtil(getRequest());
		Page<ClassModel> page = ClassModel.dao.paginate(param.getPageNumber(),
				param.getPageSize(), "select * ", "from class where del != 'delete'");
		
		System.out.println(page.getList().toString());
		renderJson(JsonKit.toJson(new PageJson<ClassModel>("0", "", page)));
	}
	
	public void addClass(){
		try {
			ClassModel model = getModel(ClassModel.class, "", true);
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

	
	public void deleteClass(){
		try {
			ClassModel model = getModel(ClassModel.class, "", true);
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
	
	public void deleteSelectClass(){
		try {
			String[] ids = getParaValues("id");
			for (String id : ids) {
				ClassModel model = ClassModel.dao.findById(id);
				model.set("del", "delete");
				model.update();
			}
			renderJson(JsonKit.toJson(new StatusJson("200", "suc", true)));
		} catch (Exception e) {
			// TODO: handle exception
			renderJson(JsonKit.toJson(new StatusJson("500", "fail", true)));
		}
	}
	
	public void updateClass(){
		try {
			ClassModel model = getModel(ClassModel.class, "", true);
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
		render("list_class.html");
	}
	
	//显示添加页
	public void showHtmlAdd() {
		render("add_class.html");
	}
	
	//显示修改页
	public void showHtmlModify() {
		setAttr("id", getPara("id"));
		render("add_class.html");
	}
}
