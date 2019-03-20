package com.jenking.common;

import com.jenking.blog.Blog;
import com.jenking.blog.BlogController;
import com.jenking.controller.IndexController;
import com.jenking.models.ClassModel;
import com.jenking.models.CollegeModel;
import com.jenking.models.EducationExperienceModel;
import com.jenking.models.EnterpriseModel;
import com.jenking.models.InternshipExperienceModel;
import com.jenking.models.MessageModel;
import com.jenking.models.PersonalCertModel;
import com.jenking.models.PersonalSkillModel;
import com.jenking.models.PrizeExperienceModel;
import com.jenking.models.RecruitModel;
import com.jenking.models.RecruitmentCollectionModel;
import com.jenking.models.RecruitmentDeliveryModel;
import com.jenking.models.ResumeModel;
import com.jenking.models.SchoolModel;
import com.jenking.models.UserModel;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;

public class StartupConfig extends JFinalConfig {
	
	//启动入口函数：右键工程，选择run as application
	public static void main(String[] args) {
		JFinal.start("WebRoot", 80, "/", 5);
	}

	public void configConstant(Constants me) {
		PropKit.use("mysql_config.txt");
		me.setDevMode(PropKit.getBoolean("devMode", false));
		me.setViewType(ViewType.JSP); 							
	}
	
	public void configRoute(Routes me) {
		me.add("/", IndexController.class, "/index");	
		me.add("/blog", BlogController.class);			
	}
	
	public void configPlugin(Plugins me) {
		C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
		me.add(c3p0Plugin);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
		arp.addMapping("class", ClassModel.class);
		arp.addMapping("college", CollegeModel.class);
		arp.addMapping("education_exprience", EducationExperienceModel.class);
		arp.addMapping("enterprise", EnterpriseModel.class);
		arp.addMapping("internship_experience", InternshipExperienceModel.class);
		arp.addMapping("message",MessageModel.class);
		arp.addMapping("personal_cert", PersonalCertModel.class);
		arp.addMapping("personal_skill", PersonalSkillModel.class);
		arp.addMapping("prize_exprience", PrizeExperienceModel.class);
		arp.addMapping("recruitment_collection", RecruitmentCollectionModel.class);
		arp.addMapping("recruitment_delivery", RecruitmentDeliveryModel.class);
		arp.addMapping("recruit", RecruitModel.class);
		arp.addMapping("resume", ResumeModel.class);
		arp.addMapping("school", SchoolModel.class);
		arp.addMapping("user_base", UserModel.class);
	}
	
	public void configInterceptor(Interceptors me) {
		
	}
	
	public void configHandler(Handlers me) {
		
	}
	
	
}
