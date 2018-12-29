/**
 * 
 */
package cn.minsin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.minsin.core.init.FileConfig;
import cn.minsin.core.tools.StringUtil;

/**
 * 配置文件上传默认路径及文件默认映射
 * 
 * @author mintonzhang
 * @date 2018年6月22日
 */
@Configuration
public class MyWebMvcCinfigurer implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String saveDisk = FileConfig.fileConfig.getSaveDisk();
		if (StringUtil.isNotBlank(saveDisk)) {
			registry.addResourceHandler("/files/**").addResourceLocations("file:" + saveDisk);
		}
	}

}
