package com.demo.common;

import com.demo.blog.Blog;
import com.demo.blog.BlogController;
import com.demo.index.IndexController;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;

/**
 * API寮曞寮忛厤缃�
 */
public class DemoConfig extends JFinalConfig {
	
	/**
	 * 
	 */
	public void configConstant(Constants me) {
		PropKit.use("a_little_config.txt");				// 鍔犺浇灏戦噺蹇呰閰嶇疆锛岄殢鍚庡彲鐢≒ropKit.get(...)鑾峰彇鍊�
		me.setDevMode(PropKit.getBoolean("devMode", false));
		me.setViewType(ViewType.JSP); 							// 璁剧疆瑙嗗浘绫诲瀷涓篔sp锛屽惁鍒欓粯璁や负FreeMarker
	}
	
	/**
	 * 閰嶇疆璺敱
	 */
	public void configRoute(Routes me) {
		me.add("/", IndexController.class, "/index");	// 绗笁涓弬鏁颁负璇ontroller鐨勮鍥惧瓨鏀捐矾寰�
		me.add("/blog", BlogController.class);			// 绗笁涓弬鏁扮渷鐣ユ椂榛樿涓庣涓�涓弬鏁板�肩浉鍚岋紝鍦ㄦ鍗充负 "/blog"
	}
	
	/**
	 * 閰嶇疆鎻掍欢
	 */
	public void configPlugin(Plugins me) {
		// 閰嶇疆C3p0鏁版嵁搴撹繛鎺ユ睜鎻掍欢
		C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
		me.add(c3p0Plugin);
		
		// 閰嶇疆ActiveRecord鎻掍欢
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
		arp.addMapping("blog", Blog.class);	// 鏄犲皠blog 琛ㄥ埌 Blog妯″瀷
	}
	
	/**
	 * 閰嶇疆鍏ㄥ眬鎷︽埅鍣�
	 */
	public void configInterceptor(Interceptors me) {
		
	}
	
	/**
	 * 閰嶇疆澶勭悊鍣�
	 */
	public void configHandler(Handlers me) {
		
	}
	
	/**
	 * 寤鸿浣跨敤 JFinal 鎵嬪唽鎺ㄨ崘鐨勬柟寮忓惎鍔ㄩ」鐩�
	 * 杩愯姝� main 鏂规硶鍙互鍚姩椤圭洰锛屾main鏂规硶鍙互鏀剧疆鍦ㄤ换鎰忕殑Class绫诲畾涔変腑锛屼笉涓�瀹氳鏀句簬姝�
	 */
	public static void main(String[] args) {
		JFinal.start("WebRoot", 80, "/", 5);
	}
}
