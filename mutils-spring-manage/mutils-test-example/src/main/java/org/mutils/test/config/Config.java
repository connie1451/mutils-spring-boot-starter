//package org.mutils.test.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.server.ErrorPage;
//import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//
//import cn.minsin.config.MutilsWebConfig;
//
//@Configuration
//public class Config  extends MutilsWebConfig{
//
//	@Value("${mutils.file.local}")
//	private boolean isLocal;
//	@Value("${mutils.file.save_disk}")
//	private String saveDisk;
//	
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
////		boolean local = FileConfig.fileConfig.isLocal();
////		String saveDisk = FileConfig.fileConfig.getSaveDisk();
////		if (local && StringUtil.isNotBlank(saveDisk)) {
////			registry.addResourceHandler("/mutils_files/**").addResourceLocations("file:" + saveDisk);
////		}
//	}
//
//	@Override
//	public void customize(ConfigurableServletWebServerFactory container) {
//		ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
//		ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
//		ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
//		container.addErrorPages(error401Page, error404Page, error500Page);
//	}
//}
