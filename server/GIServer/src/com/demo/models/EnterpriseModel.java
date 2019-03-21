package com.demo.models;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class EnterpriseModel extends Model<EnterpriseModel>{
	public static final EnterpriseModel dao = new EnterpriseModel();
	
	public Page<EnterpriseModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from enterprise order by id asc");
	}
}
