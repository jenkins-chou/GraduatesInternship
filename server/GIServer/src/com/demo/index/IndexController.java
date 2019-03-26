package com.demo.index;

import com.jfinal.core.Controller;

/**
 * IndexController
 */
public class IndexController extends Controller {
	public void index() {
		setAttr("status","");
		render("manager/login.html");
	}
	
	public void manager() {
		setAttr("status","");
		render("manager/index.html");
	}
}





