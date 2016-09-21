package com.thoughtWork.trains.controller;

import com.thoughtWork.trains.service.ITrainInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Nicolas Wu on 9/21/2016 10:55 AM.
 */
@Controller
public class WelcomeController {
	@Autowired
	private ITrainInfoService trainInfoService;

	@RequestMapping("/")
	public String welcome() {
		return "index";
	}

	@PostMapping("/upload")
	public String upload(@RequestParam("name") String name,
	                     @RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getBytes())));
				if (br != null) {
					String temp;
					StringBuilder stringBuilder = new StringBuilder();
					while ((temp = br.readLine()) != null) {
						stringBuilder.append(temp);
					}
					trainInfoService.changeInitData(stringBuilder.toString());
					return "uploadSuccess";
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return "error";
	}
}
