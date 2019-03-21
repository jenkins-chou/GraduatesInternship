package com.jenking.models;

import com.jenking.blog.Blog;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class PersonalSkillModel extends Model<PersonalSkillModel>{
	public static final PersonalSkillModel dao = new PersonalSkillModel();
	
	public Page<PersonalSkillModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from personal_skill order by id asc");
	}
}
