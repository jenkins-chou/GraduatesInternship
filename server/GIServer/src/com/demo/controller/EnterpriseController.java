package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.models.EnterpriseModel;
import com.demo.utils.PageJson;
import com.demo.utils.ParamUtil;
import com.demo.utils.RecordJson;
import com.demo.utils.StatusJson;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;

public class EnterpriseController extends Controller {
	
	public void getEnterpriseById(){
		EnterpriseModel model = EnterpriseModel.dao.findById(getPara("id"));
		renderJson(JsonKit.toJson(new RecordJson("200", "suc", model)));
	}
	
	public void getAllEnterprise() {
		ParamUtil param = new ParamUtil(getRequest());
		Page<EnterpriseModel> page = EnterpriseModel.dao.paginate(param.getPageNumber(),
				param.getPageSize(), "select * ", "from Enterprise where del != 'delete'");
		
		System.out.println(page.getList().toString());
		renderJson(JsonKit.toJson(new PageJson<EnterpriseModel>("0", "", page)));
	}
	
	public void addEnterprise(){
		try {
			EnterpriseModel model = getModel(EnterpriseModel.class, "", true);
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

	
	public void deleteEnterprise(){
		try {
			EnterpriseModel model = getModel(EnterpriseModel.class, "", true);
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
	
	public void deleteSelectEnterprise(){
		try {
			String[] ids = getParaValues("id");
			for (String id : ids) {
				EnterpriseModel model = EnterpriseModel.dao.findById(id);
				model.set("del", "delete");
				model.update();
			}
			renderJson(JsonKit.toJson(new StatusJson("200", "suc", true)));
		} catch (Exception e) {
			// TODO: handle exception
			renderJson(JsonKit.toJson(new StatusJson("500", "fail", true)));
		}
	}
	
	public void updateEnterprise(){
		try {
			EnterpriseModel model = getModel(EnterpriseModel.class, "", true);
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
	
	//��ʾ�б�
	public void showHtmlList() {
		render("list_enterprise.html");
	}
	
	//��ʾ���ҳ
	public void showHtmlAdd() {
		render("add_enterprise.html");
	}
	
	//��ʾ�޸�ҳ
	public void showHtmlModify() {
		setAttr("id", getPara("id"));
		render("add_enterprise.html");
	}
}
