package com.demo.common;

import java.util.HashMap;


import java.util.Map;

import org.beetl.core.GroupTemplate;
import org.beetl.ext.jfinal3.JFinal3BeetlRenderFactory;

import com.demo.controller.ClassController;
import com.demo.controller.CollegeController;
import com.demo.controller.EducationExperienceController;
import com.demo.controller.EnterpriseController;
import com.demo.controller.InternshipExperienceController;
import com.demo.controller.MessageController;
import com.demo.controller.PersonalCertController;
import com.demo.controller.PersonalSkillController;
import com.demo.controller.PrizeExperienceController;
import com.demo.controller.RecruitController;
import com.demo.controller.RecruitmentCollectionController;
import com.demo.controller.RecruitmentDeliveryController;
import com.demo.controller.ResumeController;
import com.demo.controller.ResumeEnclosureController;
import com.demo.controller.SchoolController;
import com.demo.controller.StudentTutorController;
import com.demo.controller.UserController;
import com.demo.index.IndexController;
import com.demo.models.ClassModel;
import com.demo.models.CollegeModel;
import com.demo.models.EducationExperienceModel;
import com.demo.models.EnterpriseModel;
import com.demo.models.InternshipExperienceModel;
import com.demo.models.MessageModel;
import com.demo.models.PersonalCertModel;
import com.demo.models.PersonalSkillModel;
import com.demo.models.PrizeExperienceModel;
import com.demo.models.RecruitModel;
import com.demo.models.RecruitmentCollectionModel;
import com.demo.models.RecruitmentDeliveryModel;
import com.demo.models.ResumeEnclosureModel;
import com.demo.models.ResumeModel;
import com.demo.models.SchoolModel;
import com.demo.models.StudentTutorModel;
import com.demo.models.UserModel;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

public class DemoConfig extends JFinalConfig {
	
	public void configConstant(Constants me) {
		PropKit.use("db_config.txt");				
		me.setDevMode(PropKit.getBoolean("devMode", false));
		me.setViewType(ViewType.JSP); 	
		
		JFinal3BeetlRenderFactory rf = new JFinal3BeetlRenderFactory();
		rf.config();
		me.setRenderFactory(rf);
		GroupTemplate gt = rf.groupTemplate;
		Map<String, Object> shard = new HashMap<String, Object>();// 共享变量
		shard.put("ctx", JFinal.me().getContextPath());// 添加共享变量上下文路�?
		
		System.out.println("----->"+JFinal.me().getContextPath());
		gt.setSharedVars(shard);// 设置共享变量
		me.setMaxPostSize(1200000000);
		
		//me.setBaseUploadPath("/upload");
	}
	
	public void configRoute(Routes me) {
//		me.add("/", IndexController.class,"/index");	//外加路由路径
		me.add("/", IndexController.class);	
		me.add("/class", ClassController.class);
		me.add("/college", CollegeController.class);
		me.add("/education_experience", EducationExperienceController.class);
		me.add("/enterprise",EnterpriseController.class);
		me.add("/internship_experience",InternshipExperienceController.class);
		me.add("/message", MessageController.class);
		me.add("/personal_cert", PersonalCertController.class);
		me.add("/personal_skill", PersonalSkillController.class);
		me.add("/prize_experience",PrizeExperienceController.class);
		me.add("/recruit",RecruitController.class);
		me.add("/recruitment_collection", RecruitmentCollectionController.class);
		me.add("/recruitment_delivery", RecruitmentDeliveryController.class);
		me.add("/resume", ResumeController.class);
		me.add("/resume_enclosure", ResumeEnclosureController.class);
		me.add("/school", SchoolController.class);
		me.add("/user", UserController.class);
		me.add("/student_tutor",StudentTutorController.class);
	}
	 
	public void configPlugin(Plugins me) {
		C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
		me.add(c3p0Plugin);
		
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
		arp.addMapping("class", ClassModel.class);
		arp.addMapping("college", CollegeModel.class);
		arp.addMapping("education_experience", EducationExperienceModel.class);
		arp.addMapping("enterprise", EnterpriseModel.class);
		arp.addMapping("internship_experience", InternshipExperienceModel.class);
		arp.addMapping("message",MessageModel.class);
		arp.addMapping("personal_cert", PersonalCertModel.class);
		arp.addMapping("personal_skill", PersonalSkillModel.class);
		arp.addMapping("prize_experience", PrizeExperienceModel.class);
		arp.addMapping("recruitment_collection", RecruitmentCollectionModel.class);
		arp.addMapping("recruitment_delivery", RecruitmentDeliveryModel.class);
		arp.addMapping("recruit", RecruitModel.class);
		arp.addMapping("resume", ResumeModel.class);
		arp.addMapping("resume_enclosure", ResumeEnclosureModel.class);
		arp.addMapping("school", SchoolModel.class);
		arp.addMapping("user_base", UserModel.class);
		arp.addMapping("student_tutor", StudentTutorModel.class);
	}
	
	public void configInterceptor(Interceptors me) {
		
	}
	
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("ctx"));
	}
	
	public static void main(String[] args) {
		JFinal.start("WebRoot", 8007, "/", 5);
	}

	@Override
	public void configEngine(Engine arg0) {
		// TODO Auto-generated method stub
	}
}
