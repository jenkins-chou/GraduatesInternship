package com.demo.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.demo.models.CollegeModel;
import com.demo.models.StudentTutorModel;
import com.demo.models.UserModel;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;

public class StudentTutorController extends Controller{
	
	//获取学生的导师信息
	public void getStudentTutor(){
		String sql = "select * from student_tutor where student_id = '"+getPara("student_id")+"' and del != 'delete'";
		List<StudentTutorModel> datas = StudentTutorModel.dao.find(sql);
		JSONObject js = new JSONObject();
		js.put("code", "200");
		js.put("data", datas);
		renderJson(JsonKit.toJson(js));
	}
	
	//添加导师学生关系
	public void addStudentTutor(){
		try {
			String sql = "sqlect * from user_base where useridentify = '"+getPara("teacher_id")+"' and realname = '"+getPara("teacher_name")+"' and del != 'delete'";
			List<UserModel> userModels = UserModel.dao.find(sql);
			if(userModels!=null&&userModels.size()>0){
				StudentTutorModel model = getModel(StudentTutorModel.class, "", true);
				model.set("status", "normal");
				model.set("del", "normal");
				model.save();
				JSONObject js = new JSONObject();
				js.put("code", "200");
				renderJson(js.toJSONString());
			}else{
				JSONObject js = new JSONObject();
				js.put("code", "201");
				renderJson(js.toJSONString());
			}
			
		}catch (Exception e) {
			System.out.println(e.toString());
			JSONObject js = new JSONObject();
			js.put("code", 500);
			js.put("msg", e.toString());
			renderJson(js.toJSONString());
		}
		
	}
	
	//删除学生-导师关系
	public void deleteStudentTutor(){
		try {
			StudentTutorModel model = getModel(StudentTutorModel.class, "", true);
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
	
	//更新导师学生关系
	public void updateStudentTutor(){
		String sql = "sqlect * from user_base where useridentify = '"+getPara("teacher_id")+"' and realname = '"+getPara("teacher_name")+"' and del != 'delete'";
		List<UserModel> userModels = UserModel.dao.find(sql);
		if(userModels!=null&&userModels.size()>0){
			StudentTutorModel model = getModel(StudentTutorModel.class, "", true);
			model.update();
			JSONObject js = new JSONObject();
			js.put("code", "200");
			renderJson(js.toJSONString());
		}else{
			JSONObject js = new JSONObject();
			js.put("code", "201");
			js.put("message", "暂无导师记录");
			renderJson(js.toJSONString());
		}
	}
	
	//获取教师带领的学生列表
	public void getTeachersStudents(){
		
	}

}
