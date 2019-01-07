package cn.minsin.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.minsin.core.init.FileConfig;
import cn.minsin.core.tools.StringUtil;

public class MutilsWebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		boolean local = FileConfig.fileConfig.isLocal();
		String saveDisk = FileConfig.fileConfig.getSaveDisk();
		if (local && StringUtil.isNotBlank(saveDisk)) {
			registry.addResourceHandler("/files/**").addResourceLocations("file:" + saveDisk);
		}
	}

}
