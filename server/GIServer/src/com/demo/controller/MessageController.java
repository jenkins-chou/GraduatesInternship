package com.demo.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.demo.models.MessageModel;
import com.demo.models.MessageModel;
import com.demo.utils.PageJson;
import com.demo.utils.ParamUtil;
import com.demo.utils.RecordJson;
import com.demo.utils.StatusJson;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;

public class MessageController extends Controller {
	
	/**
	 * -------------------------移动端接口
	 */
	public void getMineMessageMobile(){
		String sql = "select * from message where receive_user_id = '"+getPara("receive_user_id")+"' and del != 'delete' order by id DESC";
		List<MessageModel> datas = MessageModel.dao.find(sql);
		System.out.println(datas.toString());
		
		JSONObject js = new JSONObject();
		js.put("code", "200");
		js.put("data",datas);
		renderJson(JsonKit.toJson(js));
	}
	
	public void addMessageMobile(){
		try {
			MessageModel model = getModel(MessageModel.class, "", true);
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
	public void modifyMessageMobile(){
		try {
			MessageModel model = getModel(MessageModel.class, "", true);
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
	public void deleteMessageMobile(){
		try {
			String[] ids = getParaValues("id");
			for (String id : ids) {
				MessageModel model = MessageModel.dao.findById(id);
				model.set("del", "delete");
				model.update();
			}
			renderJson(JsonKit.toJson(new StatusJson("200", "suc", true)));
		} catch (Exception e) {
			renderJson(JsonKit.toJson(new StatusJson("500", "fail", true)));
		}
	}
	
	/**
	 * -------------------------移动端接口
	 */
	
	public void getMessageById(){
		MessageModel model = MessageModel.dao.findById(getPara("id"));
		renderJson(JsonKit.toJson(new RecordJson("200", "suc", model)));
	}
	
	public void getAllMessage() {
		ParamUtil param = new ParamUtil(getRequest());
		Page<MessageModel> page = MessageModel.dao.paginate(param.getPageNumber(),
				param.getPageSize(), "select * ", "from Message where del != 'delete'");
		
		System.out.println(page.getList().toString());
		renderJson(JsonKit.toJson(new PageJson<MessageModel>("0", "", page)));
	}
	
	public void addMessage(){
		try {
			MessageModel model = getModel(MessageModel.class, "", true);
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

	
	public void deleteMessage(){
		try {
			MessageModel model = getModel(MessageModel.class, "", true);
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
	
	public void deleteSelectMessage(){
		try {
			String[] ids = getParaValues("id");
			for (String id : ids) {
				MessageModel model = MessageModel.dao.findById(id);
				model.set("del", "delete");
				model.update();
			}
			renderJson(JsonKit.toJson(new StatusJson("200", "suc", true)));
		} catch (Exception e) {
			// TODO: handle exception
			renderJson(JsonKit.toJson(new StatusJson("500", "fail", true)));
		}
	}
	
	public void updateMessage(){
		try {
			MessageModel model = getModel(MessageModel.class, "", true);
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
		render("list_message.html");
	}
	
	//显示添加页
	public void showHtmlAdd() {
		render("add_message.html");
	}
	
	//显示修改页
	public void showHtmlModify() {
		setAttr("id", getPara("id"));
		render("add_message.html");
	}
}
