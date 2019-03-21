package com.demo.models;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class MessageModel extends Model<MessageModel>{
	public static final MessageModel dao = new MessageModel();
	
	public Page<MessageModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from message order by id asc");
	}
}
