package com.jenking.models;

import com.jenking.blog.Blog;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class SchoolModel extends Model<SchoolModel>{
	public static final SchoolModel dao = new SchoolModel();
	
	public Page<SchoolModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from school order by id asc");
	}
}
