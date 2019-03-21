package com.demo.models;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class UserModel extends Model<UserModel>{
	public static final UserModel dao = new UserModel();
	
	public Page<UserModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from user_base order by id asc");
	}
}
