package com.demo.models;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class InternshipExperienceModel extends Model<InternshipExperienceModel>{
	public static final InternshipExperienceModel dao = new InternshipExperienceModel();
	
	public Page<InternshipExperienceModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from internship_exprience order by id asc");
	}
}
