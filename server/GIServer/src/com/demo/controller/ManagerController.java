package com.demo.controller;

import java.util.List;

import com.demo.models.EnterpriseModel;
import com.demo.models.UserModel;
import com.jfinal.core.Controller;

public class ManagerController extends Controller {
	
	public void login(){
		String username = getPara("username");
		String password = getPara("password");
		String type = getPara("type");
		
		System.out.println(username);
		System.out.println(password);
		System.out.println(type);
		
		if(type!=null){
			if(type.equals("system")){//����Ա�û�
				
				String sql = "select * from user_base where useridentify = '"+username+"' and pass = '"+password+"' and type = 'system' and del != 'delete'";
				System.out.println(sql);
				
				List<UserModel> userModels = UserModel.dao.find(sql);
				System.out.println(userModels);
				if(userModels!=null&&userModels.size()>0){
					setAttr("manager",userModels.get(0));
					render("index.html");
				}else{
					setAttr("status","�����ڸù���Ա");  
					render("login.html");
				}
				
			}else if(type.equals("enterprise")){//��ҵ�û�
				String sql = "select * from enterprise where enterprise_email = '"+username+"' and enterprise_token = '"+password+"' and del != 'delete'";
				System.out.println(sql);
				
				List<EnterpriseModel> models = EnterpriseModel.dao.find(sql);
				
				if(models!=null&&models.size()>0){
					setAttr("enterprise_id",models.get(0).get("id"));
					setAttr("enterprise_name",models.get(0).get("enterprise_name"));
					render("enterprise.html");
				}else{
					setAttr("status","�����ڸ���ҵ�û�");  
					render("login.html");
				}
			}else{
				render("login.html");
			}
		}else{
			render("login.html");
		}
	}

}
