package com.demo.models;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class CollegeModel extends Model<CollegeModel>{
	public static final CollegeModel dao = new CollegeModel();
	
	public Page<CollegeModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from college order by id asc");
	}
}
