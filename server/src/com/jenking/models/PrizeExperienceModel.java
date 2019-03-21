package com.jenking.models;

import com.jenking.blog.Blog;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class PrizeExperienceModel extends Model<PrizeExperienceModel>{
	public static final PrizeExperienceModel dao = new PrizeExperienceModel();
	
	public Page<PrizeExperienceModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from prize_experience order by id asc");
	}
}
