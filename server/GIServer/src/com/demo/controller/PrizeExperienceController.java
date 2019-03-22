package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.models.PrizeExperienceModel;
import com.demo.utils.PageJson;
import com.demo.utils.ParamUtil;
import com.demo.utils.RecordJson;
import com.demo.utils.StatusJson;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;

public class PrizeExperienceController extends Controller {
	
	public void getPrizeExperienceById(){
		PrizeExperienceModel model = PrizeExperienceModel.dao.findById(getPara("id"));
		renderJson(JsonKit.toJson(new RecordJson("200", "suc", model)));
	}
	
	public void getAllPrizeExperience() {
		ParamUtil param = new ParamUtil(getRequest());
		Page<PrizeExperienceModel> page = PrizeExperienceModel.dao.paginate(param.getPageNumber(),
				param.getPageSize(), "select * ", "from prize_experience where del != 'delete'");
		
		System.out.println(page.getList().toString());
		renderJson(JsonKit.toJson(new PageJson<PrizeExperienceModel>("0", "", page)));
	}
	
	public void addPrizeExperience(){
		try {
			PrizeExperienceModel model = getModel(PrizeExperienceModel.class, "", true);
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

	
	public void deletePrizeExperience(){
		try {
			PrizeExperienceModel model = getModel(PrizeExperienceModel.class, "", true);
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
	
	public void deleteSelectPrizeExperience(){
		try {
			String[] ids = getParaValues("id");
			for (String id : ids) {
				PrizeExperienceModel model = PrizeExperienceModel.dao.findById(id);
				model.set("del", "delete");
				model.update();
			}
			renderJson(JsonKit.toJson(new StatusJson("200", "suc", true)));
		} catch (Exception e) {
			// TODO: handle exception
			renderJson(JsonKit.toJson(new StatusJson("500", "fail", true)));
		}
	}
	
	public void updatePrizeExperience(){
		try {
			PrizeExperienceModel model = getModel(PrizeExperienceModel.class, "", true);
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
		render("list_prize_experience.html");
	}
	
	//��ʾ���ҳ
	public void showHtmlAdd() {
		render("add_prize_experience.html");
	}
	
	//��ʾ�޸�ҳ
	public void showHtmlModify() {
		setAttr("id", getPara("id"));
		render("add_prize_experience.html");
	}
}
