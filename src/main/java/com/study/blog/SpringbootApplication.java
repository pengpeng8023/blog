package com.study.blog;

import com.study.blog.tool.AuthImage;
import com.study.blog.tool.PropertiesListener;
import com.study.blog.tool.PropertiesListenerConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.study.blog.*.mapper") //扫描Mapper包 为其创建代理对象
public class SpringbootApplication extends SpringBootServletInitializer {
	@Bean//注册Servlet
	public ServletRegistrationBean servletRegistrationBean() {
		return new ServletRegistrationBean(new AuthImage(), "/authImage");
	}
//	@Bean//注册Filter
//	public FilterRegistrationBean filterRegistrationBean() {
//		return new FilterRegistrationBean(new CustomFilter());
//	}
//	@Bean//注册Listener
//	public ServletListenerRegistrationBean servletListenerRegistrationBean() {
//		return new ServletListenerRegistrationBean(new PropertiesListener("platformConfig.properties"));
//	}

	/**
	 * 继承 SpringBootServletInitializer 重写configure方法 确认启动方法 打包
	 * @param application
	 * @return
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(SpringbootApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(SpringbootApplication.class);
		//注册监听器
		application.addListeners(new PropertiesListener("platformConfig.properties"));
		application.run(args);
//		SpringApplication.run(SpringbootApplication.class,args);
	}
}
