package com.jenking.models;

import com.jenking.blog.Blog;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class EducationExperienceModel extends Model<EducationExperienceModel>{
	public static final EducationExperienceModel dao = new EducationExperienceModel();
	
	public Page<EducationExperienceModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from education_experience order by id asc");
	}
}
