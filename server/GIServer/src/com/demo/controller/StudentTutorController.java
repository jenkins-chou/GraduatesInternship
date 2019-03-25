package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.models.CollegeModel;
import com.demo.models.StudentTutorModel;
import com.jfinal.core.Controller;

public class StudentTutorController extends Controller{
	
	//��ȡѧ���ĵ�ʦ��Ϣ
	public void getStudentTutor(){
		
	}
	
	//���ӵ�ʦѧ����ϵ
	public void addStudentTutor(){
		try {
			StudentTutorModel model = getModel(StudentTutorModel.class, "", true);
			model.set("status", "normal");
			model.set("del", "normal");
			model.save();
			JSONObject js = new JSONObject();
			js.put("code", "200");
			renderJson(js.toJSONString());
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			JSONObject js = new JSONObject();
			js.put("code", 500);
			js.put("msg", e.toString());
			renderJson(js.toJSONString());
		}
		
	}
	
	//ɾ��ѧ��-��ʦ��ϵ
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
	
	//��ȡ��ʦ�����ѧ���б�
	public void getTeachersStudents(){
		
	}

}