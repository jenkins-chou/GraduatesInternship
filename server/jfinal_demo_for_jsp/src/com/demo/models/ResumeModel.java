package com.demo.models;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class ResumeModel extends Model<ResumeModel>{
	public static final ResumeModel dao = new ResumeModel();
	
	public Page<ResumeModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from resume order by id asc");
	}
}
