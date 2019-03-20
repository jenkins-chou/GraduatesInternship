package com.jenking.models;

import com.jenking.blog.Blog;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class RecruitmentCollectionModel extends Model<RecruitmentCollectionModel>{
	public static final RecruitmentCollectionModel dao = new RecruitmentCollectionModel();
	
	public Page<RecruitmentCollectionModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from recruitment_collection order by id asc");
	}
}
