package com.demo.models;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class StudentTutorModel extends Model<StudentTutorModel>{
	public static final StudentTutorModel dao = new StudentTutorModel();
	
	public Page<StudentTutorModel> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *", "from student_tutor order by id asc");
	}
}
