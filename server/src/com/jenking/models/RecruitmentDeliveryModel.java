package com.jenking.models;

import com.jenking.blog.Blog;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class RecruitmentDeliveryModel extends Model<RecruitmentDeliveryModel>{
	public static final RecruitmentDeliveryModel dao = new RecruitmentDeliveryModel();
	
	public Page<RecruitmentDeliveryModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from recruitment_delivery order by id asc");
	}
}
