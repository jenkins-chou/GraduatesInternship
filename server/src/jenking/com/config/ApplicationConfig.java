package jenking.com.config;
import java.util.HashMap;
import java.util.Map;

import org.beetl.core.GroupTemplate;
import org.beetl.ext.jfinal3.JFinal3BeetlRenderFactory;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

import jenking.com.controller.IndexController;
import jenking.com.controller.UserController;

public class ApplicationConfig extends JFinalConfig{

	/**
	 * 配置常量，连接数据库，数据库的映射
	 */
	public void configConstant(Constants me) {
		loadPropertyFile("a_little_config.txt");
		me.setDevMode(getPropertyToBoolean("devMode", false));
		me.setViewType(ViewType.JSP);
		JFinal3BeetlRenderFactory rf = new JFinal3BeetlRenderFactory();
		rf.config();
		me.setRenderFactory(rf);
		GroupTemplate gt = rf.groupTemplate;
		Map<String, Object> shard = new HashMap<String, Object>();// 共享变量
		shard.put("ctx", JFinal.me().getContextPath());// 添加共享变量上下文路�?
		gt.setSharedVars(shard);// 设置共享变量
		me.setMaxPostSize(1200000000);
	}

	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add("/", IndexController.class); //
//		me.add("/user", UserController.class);
	}

	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
		C3p0Plugin c3p0Plugin = new C3p0Plugin("jdbc:mysql://localhost:3306/wenda?3useUnicode=true&characterEncoding=utf8",
				"root", "root",
				"com.mysql.jdbc.Driver");
		me.add(c3p0Plugin);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
//		arp.addMapping("user", user.class);
	}

	/**
	 * 配置全局拦截
	 */
	public void configInterceptor(Interceptors me) {

	}

	/**
	 * 配置处理
	 */
	public void configHandler(Handlers me) {
		// me.add(new CustomHandler());
	}

	/**
	 * 建议使用 JFinal 手册推荐的方式启动项�? 运行
	 * 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不要放于此
	 */
	public static void main(String[] args) {
		JFinal.start("WebRoot", 8009, "/", 5);
	}

	@Override
	public void configEngine(Engine arg0) {
		// TODO Auto-generated method stub

	}

}
