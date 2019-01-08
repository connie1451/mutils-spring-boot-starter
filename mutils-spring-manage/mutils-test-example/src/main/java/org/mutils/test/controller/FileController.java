package org.mutils.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.minsin.FileFunctions;
import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.web.Result;

@RestController
public class FileController {

	@RequestMapping("/upload")
	public Result upload(@RequestParam("file") MultipartFile file) {
		try {
			String saveFile = FileFunctions.saveFile(file);
			return Result.builderSuccess().data(saveFile);
		} catch (MutilsErrorException e) {
			e.printStackTrace();
		}
		return Result.builderException();
	}
}
