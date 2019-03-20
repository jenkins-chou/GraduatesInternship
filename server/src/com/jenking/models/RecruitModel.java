package com.jenking.models;

import com.jenking.blog.Blog;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class RecruitModel extends Model<RecruitModel>{
	public static final RecruitModel dao = new RecruitModel();
	
	public Page<RecruitModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from recruit order by id asc");
	}
}
