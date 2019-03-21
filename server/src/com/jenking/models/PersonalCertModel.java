package com.jenking.models;

import com.jenking.blog.Blog;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class PersonalCertModel extends Model<PersonalCertModel>{
	public static final PersonalCertModel dao = new PersonalCertModel();
	
	public Page<PersonalCertModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from personal_cert order by id asc");
	}
}
