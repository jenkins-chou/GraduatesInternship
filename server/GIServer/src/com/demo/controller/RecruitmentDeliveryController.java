package com.demo.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.demo.models.RecruitModel;
import com.demo.models.RecruitmentDeliveryModel;
import com.demo.models.RecruitmentDeliveryModel;
import com.demo.utils.PageJson;
import com.demo.utils.ParamUtil;
import com.demo.utils.RecordJson;
import com.demo.utils.StatusJson;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Page;

public class RecruitmentDeliveryController extends Controller {
	
	/**
	 * -------------------------移动端接口
	 */
	public void getMineRecruitmentDeliveryMobile(){
		String sql = "select a.*,b.status,b.user_id,b.recruit_id from recruit a , recruitment_Delivery b where a.id = b.recruit_id and b.user_id = "+getPara("user_id")+"  and a.del != 'delele' and b.del != 'delete' order by b.id DESC";
		List<RecruitModel> datas = RecruitModel.dao.find(sql);
		System.out.println(datas);
		JSONObject js = new JSONObject();
		js.put("code", "200");
		js.put("data",datas);
		renderJson(JsonKit.toJson(js));
	}
	
	public void addRecruitmentDeliveryMobile(){
		try {
			
			String user_id = getPara("user_id");
			String recruit_id = getPara("recruit_id");
			String sql = "select * from recruitment_delivery where user_id = '"+user_id+"' and recruit_id = '"+recruit_id+"' and del != 'delete'";
			List<RecruitmentDeliveryModel> models = RecruitmentDeliveryModel.dao.find(sql);
			
			System.out.println(models);
			if(models!=null&&models.size()>=1){
				JSONObject js = new JSONObject();
				js.put("code", "201");
				js.put("message", "已投递过了");
				renderJson(js.toJSONString());
			}else{
				RecruitmentDeliveryModel model = getModel(RecruitmentDeliveryModel.class, "", true);
				model.set("create_time", System.currentTimeMillis()/1000+"");
				model.set("del","normal");
				System.out.println("model:"+model);
				model.save();
				JSONObject js = new JSONObject();
				js.put("code", "200");
				renderJson(js.toJSONString());
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
			JSONObject js = new JSONObject();
			js.put("code", 500);
			js.put("msg", e.toString());
			renderJson(js.toJSONString());
		}
	}
	public void modifyRecruitmentDeliveryMobile(){
		try {
			RecruitmentDeliveryModel model = getModel(RecruitmentDeliveryModel.class, "", true);
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
	public void deleteRecruitmentDeliveryMobile(){
		try {
			String user_id = getPara("user_id");
			String recruit_id = getPara("recruit_id");
			String sql = "select * from recruitment_delivery where user_id = '"+user_id+"' and recruit_id = '"+recruit_id+"' and del != 'delete'";
			List<RecruitmentDeliveryModel> models = RecruitmentDeliveryModel.dao.find(sql);
			
			System.out.println(models);
			for (RecruitmentDeliveryModel model : models) {
				model.set("del", "delete");
				model.update();
			}
			renderJson(JsonKit.toJson(new StatusJson("200", "success", true)));
		} catch (Exception e) {
			// TODO: handle exception
			renderJson(JsonKit.toJson(new StatusJson("500", "fail", true)));
		}
	}
	
	/**
	 * -------------------------移动端接口
	 */
	
	public void getRecruitmentDeliveryById(){
		RecruitmentDeliveryModel model = RecruitmentDeliveryModel.dao.findById(getPara("id"));
		renderJson(JsonKit.toJson(new RecordJson("200", "suc", model)));
	}
	
	public void getAllRecruitmentDelivery() {
		ParamUtil param = new ParamUtil(getRequest());
		Page<RecruitmentDeliveryModel> page = RecruitmentDeliveryModel.dao.paginate(param.getPageNumber(),
				param.getPageSize(), "select * ", "from recruitment_delivery where del != 'delete'");
		
		System.out.println(page.getList().toString());
		renderJson(JsonKit.toJson(new PageJson<RecruitmentDeliveryModel>("0", "", page)));
	}
	
	public void addRecruitmentDelivery(){
		try {
			RecruitmentDeliveryModel model = getModel(RecruitmentDeliveryModel.class, "", true);
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

	
	public void deleteRecruitmentDelivery(){
		try {
			RecruitmentDeliveryModel model = getModel(RecruitmentDeliveryModel.class, "", true);
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
	
	public void deleteSelectRecruitmentDelivery(){
		try {
			String[] ids = getParaValues("id");
			for (String id : ids) {
				RecruitmentDeliveryModel model = RecruitmentDeliveryModel.dao.findById(id);
				model.set("del", "delete");
				model.update();
			}
			renderJson(JsonKit.toJson(new StatusJson("200", "suc", true)));
		} catch (Exception e) {
			// TODO: handle exception
			renderJson(JsonKit.toJson(new StatusJson("500", "fail", true)));
		}
	}
	
	public void updateRecruitmentDelivery(){
		try {
			RecruitmentDeliveryModel model = getModel(RecruitmentDeliveryModel.class, "", true);
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
		render("list_recruitment_delivery.html");
	}
	
	//显示添加页
	public void showHtmlAdd() {
		render("add_recruitment_delivery.html");
	}
	
	//显示修改页
	public void showHtmlModify() {
		setAttr("id", getPara("id"));
		render("add_recruitment_delivery.html");
	}
}
