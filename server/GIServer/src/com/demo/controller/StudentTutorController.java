package com.demo.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.demo.models.CollegeModel;
import com.demo.models.StudentTutorModel;
import com.demo.models.UserModel;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;

public class StudentTutorController extends Controller{
	
	//��ȡѧ���ĵ�ʦ��Ϣ
	public void getStudentTutor(){
		String sql = "select * from student_tutor where student_id = '"+getPara("student_id")+"' and del != 'delete'";
		List<StudentTutorModel> datas = StudentTutorModel.dao.find(sql);
		JSONObject js = new JSONObject();
		js.put("code", "200");
		js.put("data", datas);
		renderJson(JsonKit.toJson(js));
	}
	
	//��ӵ�ʦѧ����ϵ
	public void addStudentTutor(){
		try {
			String sql = "select * from user_base where useridentify = '"+getPara("teacher_useridentify")+"' and realname = '"+getPara("teacher_name")+"' and del != 'delete'";
			List<UserModel> userModels = UserModel.dao.find(sql);
			if(userModels!=null&&userModels.size()>0){
				
				List<StudentTutorModel> models = StudentTutorModel.dao.find("select * from student_tutor where student_id = '"+getPara("student_id")+"' and del != 'delete'");
				if(models!=null&&models.size()>0){
					for(StudentTutorModel studentTutorModel:models){
						studentTutorModel.set("del", "delete");
						studentTutorModel.update();
					}
				}
				
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
			js.put("message", e.toString());
			renderJson(js.toJSONString());
		}
		
	}
	
	//ɾ��ѧ��-��ʦ��ϵ
	public void deleteStudentTutor(){
		try {
			List<StudentTutorModel> models = StudentTutorModel.dao.find("select * from student_tutor where student_id = '"+getPara("student_id")+"' and del != 'delete'");
			if(models!=null&&models.size()>0){
				for(StudentTutorModel studentTutorModel:models){
					studentTutorModel.set("del", "delete");
					studentTutorModel.update();
				}
			}
			
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
	
	//���µ�ʦѧ����ϵ
	public void updateStudentTutor(){
		try{
			String sql = "select * from user_base where useridentify = '"+getPara("teacher_useridentify")+"' and realname = '"+getPara("teacher_name")+"' and del != 'delete'";
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
				js.put("message", "���޵�ʦ��¼");
				renderJson(js.toJSONString());
			}
		}catch (Exception e) {
			System.out.println(e.toString());
			JSONObject js = new JSONObject();
			js.put("code", 500);
			js.put("message", e.toString());
			renderJson(js.toJSONString());
		}
	}
	
	//��ȡ��ʦ�����ѧ���б�
	public void getTeachersStudents(){
		try{
			String sql = "select * from user_base where id in (select student_id as id from student_tutor where teacher_useridentify = '"+getPara("teacher_useridentify")+"' and del != 'delete') and del != 'delete'";
			List<UserModel> userModels = UserModel.dao.find(sql);
			System.out.println(sql);
			System.out.println(userModels.toString());
			if(userModels!=null&&userModels.size()>0){
				JSONObject js = new JSONObject();
				js.put("code", 200);
				js.put("data",userModels);
				renderJson(JsonKit.toJson(js));
			}else{
				JSONObject js = new JSONObject();
				js.put("code", 201);
				js.put("message","�޼�¼");
				renderJson(js.toJSONString());
			}
		}catch (Exception e) {
			System.out.println(e.toString());
			JSONObject js = new JSONObject();
			js.put("code", 500);
			js.put("message", e.toString());
			renderJson(js.toJSONString());
		}
	}

}
