package com.demo.models;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class ResumeEnclosureModel extends Model<ResumeEnclosureModel>{
	public static final ResumeEnclosureModel dao = new ResumeEnclosureModel();
	
	public Page<ResumeEnclosureModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from resume order by id asc");
	}
}
