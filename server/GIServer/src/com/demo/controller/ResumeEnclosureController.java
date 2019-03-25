package com.demo.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.demo.models.ResumeEnclosureModel;
import com.demo.models.ResumeEnclosureModel;
import com.demo.utils.PageJson;
import com.demo.utils.ParamUtil;
import com.demo.utils.RecordJson;
import com.demo.utils.StatusJson;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;

public class ResumeEnclosureController extends Controller {
	
	//简历上传
	public void uploadFileMobile(){
		UploadFile f = getFile();
		renderJson("upload/"+f.getFileName());
	}
	
	/**
	 * -------------------------移动端接口
	 */
	public void getMineResumeEnclosureMobile(){
		String sql = "select * from resume_enclosure where user_id = '"+getPara("user_id")+"' and status = 'current' and del != 'delete' order by id DESC";
		List<ResumeEnclosureModel> datas = ResumeEnclosureModel.dao.find(sql);
		JSONObject js = new JSONObject();
		js.put("code", "200");
		js.put("data",datas);
		renderJson(JsonKit.toJson(js));
	}
	
	public void addResumeEnclosureMobile(){
		try {
			String sql = "select * from resume_enclosure where user_id = '"+getPara("user_id")+"' and del != 'delete'";
			List<ResumeEnclosureModel> datas = ResumeEnclosureModel.dao.find(sql);
			for(ResumeEnclosureModel model:datas){
				model.set("status", "Previous");
				model.update();
			}
			
			ResumeEnclosureModel model = getModel(ResumeEnclosureModel.class, "", true);
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
	public void modifyResumeEnclosureMobile(){
		try {
			ResumeEnclosureModel model = getModel(ResumeEnclosureModel.class, "", true);
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
	public void deleteResumeEnclosureMobile(){
		try {
			String[] ids = getParaValues("id");
			for (String id : ids) {
				ResumeEnclosureModel model = ResumeEnclosureModel.dao.findById(id);
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
	
	public void getResumeEnclosureById(){
		ResumeEnclosureModel model = ResumeEnclosureModel.dao.findById(getPara("id"));
		renderJson(JsonKit.toJson(new RecordJson("200", "suc", model)));
	}
	
	public void getAllResumeEnclosure() {
		ParamUtil param = new ParamUtil(getRequest());
		Page<ResumeEnclosureModel> page = ResumeEnclosureModel.dao.paginate(param.getPageNumber(),
				param.getPageSize(), "select * ", "from ResumeEnclosure where del != 'delete'");
		
		System.out.println(page.getList().toString());
		renderJson(JsonKit.toJson(new PageJson<ResumeEnclosureModel>("0", "", page)));
	}
	
	public void addResumeEnclosure(){
		try {
			ResumeEnclosureModel model = getModel(ResumeEnclosureModel.class, "", true);
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

	
	public void deleteResumeEnclosure(){
		try {
			ResumeEnclosureModel model = getModel(ResumeEnclosureModel.class, "", true);
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
	
	public void deleteSelectResumeEnclosure(){
		try {
			String[] ids = getParaValues("id");
			for (String id : ids) {
				ResumeEnclosureModel model = ResumeEnclosureModel.dao.findById(id);
				model.set("del", "delete");
				model.update();
			}
			renderJson(JsonKit.toJson(new StatusJson("200", "suc", true)));
		} catch (Exception e) {
			// TODO: handle exception
			renderJson(JsonKit.toJson(new StatusJson("500", "fail", true)));
		}
	}
	
	public void updateResumeEnclosure(){
		try {
			ResumeEnclosureModel model = getModel(ResumeEnclosureModel.class, "", true);
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
		render("list_ResumeEnclosure.html");
	}
	
	//显示添加页
	public void showHtmlAdd() {
		render("add_ResumeEnclosure.html");
	}
	
	//显示修改页
	public void showHtmlModify() {
		setAttr("id", getPara("id"));
		render("add_ResumeEnclosure.html");
	}
}
