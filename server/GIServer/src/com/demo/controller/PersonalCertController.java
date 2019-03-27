package com.demo.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.demo.models.PersonalCertModel;
import com.demo.models.PersonalCertModel;
import com.demo.utils.PageJson;
import com.demo.utils.ParamUtil;
import com.demo.utils.RecordJson;
import com.demo.utils.StatusJson;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;

public class PersonalCertController extends Controller {
	
	/**
	 * -------------------------�ƶ��˽ӿ�
	 */
	public void getMinePersonalCertMobile(){
		String sql = "select * from personal_cert where user_id = '"+getPara("user_id")+"' and del != 'delete' order by id DESC";
		List<PersonalCertModel> datas = PersonalCertModel.dao.find(sql);
		JSONObject js = new JSONObject();
		js.put("code", "200");
		js.put("data",datas);
		renderJson(JsonKit.toJson(js));
	}
	
	public void addPersonalCertMobile(){
		try {
			PersonalCertModel model = getModel(PersonalCertModel.class, "", true);
			model.set("create_time", System.currentTimeMillis()/1000+"");
			model.set("del","normal");
			System.out.println("model:"+model);
			model.save();
			JSONObject js = new JSONObject();
			js.put("code", "200");
			renderJson(js.toJSONString());
		} catch (Exception e) {
			System.out.println(e.toString());
			JSONObject js = new JSONObject();
			js.put("code", 500);
			js.put("msg", e.toString());
			renderJson(js.toJSONString());
		}
	}
	public void modifyPersonalCertMobile(){
		try {
			PersonalCertModel model = getModel(PersonalCertModel.class, "", true);
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
	public void deletePersonalCertMobile(){
		try {
			String[] ids = getParaValues("id");
			for (String id : ids) {
				PersonalCertModel model = PersonalCertModel.dao.findById(id);
				model.set("del", "delete");
				model.update();
			}
			renderJson(JsonKit.toJson(new StatusJson("200", "suc", true)));
		} catch (Exception e) {
			// TODO: handle exception
			renderJson(JsonKit.toJson(new StatusJson("500", "fail", true)));
		}
	}
	
	/**
	 * -------------------------�ƶ��˽ӿ�
	 */
	
	public void getPersonalCertById(){
		PersonalCertModel model = PersonalCertModel.dao.findById(getPara("id"));
		renderJson(JsonKit.toJson(new RecordJson("200", "suc", model)));
	}
	
	public void getAllPersonalCert() {
		ParamUtil param = new ParamUtil(getRequest());
		Page<PersonalCertModel> page = PersonalCertModel.dao.paginate(param.getPageNumber(),
				param.getPageSize(), "select * ", "from personal_cert where del != 'delete'");
		
		System.out.println(page.getList().toString());
		renderJson(JsonKit.toJson(new PageJson<PersonalCertModel>("0", "", page)));
	}
	
	public void getAllPersonalCertByUserId() {
		ParamUtil param = new ParamUtil(getRequest());
		Page<PersonalCertModel> page = PersonalCertModel.dao.paginate(param.getPageNumber(),
				param.getPageSize(), "select * ", "from personal_cert where user_id = "+getPara("user_id")+" and del != 'delete'");
		
		System.out.println(page.getList().toString());
		renderJson(JsonKit.toJson(new PageJson<PersonalCertModel>("0", "", page)));
	}
	
	
	public void addPersonalCert(){
		try {
			PersonalCertModel model = getModel(PersonalCertModel.class, "", true);
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

	
	public void deletePersonalCert(){
		try {
			PersonalCertModel model = getModel(PersonalCertModel.class, "", true);
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
	
	public void deleteSelectPersonalCert(){
		try {
			String[] ids = getParaValues("id");
			for (String id : ids) {
				PersonalCertModel model = PersonalCertModel.dao.findById(id);
				model.set("del", "delete");
				model.update();
			}
			renderJson(JsonKit.toJson(new StatusJson("200", "suc", true)));
		} catch (Exception e) {
			// TODO: handle exception
			renderJson(JsonKit.toJson(new StatusJson("500", "fail", true)));
		}
	}
	
	public void updatePersonalCert(){
		try {
			PersonalCertModel model = getModel(PersonalCertModel.class, "", true);
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
		render("list_personal_cert.html");
	}
	
	//��ʾ���ҳ
	public void showHtmlAdd() {
		render("add_personal_cert.html");
	}
	
	//��ʾ�޸�ҳ
	public void showHtmlModify() {
		setAttr("id", getPara("id"));
		render("add_personal_cert.html");
	}
}
