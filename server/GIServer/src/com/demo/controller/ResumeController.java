package com.demo.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.demo.models.ResumeModel;
import com.demo.models.ResumeModel;
import com.demo.utils.PageJson;
import com.demo.utils.ParamUtil;
import com.demo.utils.RecordJson;
import com.demo.utils.StatusJson;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;

public class ResumeController extends Controller {
	
	/**
	 * -------------------------移动端接口
	 */
	public void getMineResumeMobile(){
		String sql = "select * from resume where user_id = '"+getPara("user_id")+"' and del != 'delete' order by id DESC";
		List<ResumeModel> datas = ResumeModel.dao.find(sql);
		JSONObject js = new JSONObject();
		js.put("code", "200");
		js.put("data",datas);
		renderJson(JsonKit.toJson(js));
	}
	
	public void addResumeMobile(){
		try {
			ResumeModel model = getModel(ResumeModel.class, "", true);
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
	public void modifyResumeMobile(){
		try {
			ResumeModel model = getModel(ResumeModel.class, "", true);
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
	public void deleteResumeMobile(){
		try {
			String[] ids = getParaValues("id");
			for (String id : ids) {
				ResumeModel model = ResumeModel.dao.findById(id);
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
	 * -------------------------移动端接口
	 */
	
	public void getResumeById(){
		ResumeModel model = ResumeModel.dao.findById(getPara("id"));
		renderJson(JsonKit.toJson(new RecordJson("200", "suc", model)));
	}
	
	public void getAllResume() {
		ParamUtil param = new ParamUtil(getRequest());
		Page<ResumeModel> page = ResumeModel.dao.paginate(param.getPageNumber(),
				param.getPageSize(), "select * ", "from Resume where del != 'delete'");
		
		System.out.println(page.getList().toString());
		renderJson(JsonKit.toJson(new PageJson<ResumeModel>("0", "", page)));
	}
	
	public void addResume(){
		try {
			ResumeModel model = getModel(ResumeModel.class, "", true);
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

	
	public void deleteResume(){
		try {
			ResumeModel model = getModel(ResumeModel.class, "", true);
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
	
	public void deleteSelectResume(){
		try {
			String[] ids = getParaValues("id");
			for (String id : ids) {
				ResumeModel model = ResumeModel.dao.findById(id);
				model.set("del", "delete");
				model.update();
			}
			renderJson(JsonKit.toJson(new StatusJson("200", "suc", true)));
		} catch (Exception e) {
			// TODO: handle exception
			renderJson(JsonKit.toJson(new StatusJson("500", "fail", true)));
		}
	}
	
	public void updateResume(){
		try {
			ResumeModel model = getModel(ResumeModel.class, "", true);
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
		render("list_resume.html");
	}
	
	//显示添加页
	public void showHtmlAdd() {
		render("add_resume.html");
	}
	
	//显示修改页
	public void showHtmlModify() {
		setAttr("id", getPara("id"));
		render("add_resume.html");
	}
}
