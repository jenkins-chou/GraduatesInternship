package com.demo.models;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class ClassModel extends Model<ClassModel>{
	public static final ClassModel dao = new ClassModel();
	
	public Page<ClassModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from class order by id asc");
	}
}
