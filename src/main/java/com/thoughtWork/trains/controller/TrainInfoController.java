package com.thoughtWork.trains.controller;

import com.thoughtWork.trains.service.ITrainInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Nicolas Wu on 9/21/2016 10:55 AM.
 */
@Controller
@RequestMapping("/train/view")
public class TrainInfoController {
    @Autowired
    private ITrainInfoService trainInfoService;

    @RequestMapping("")
    public String welcome(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }

    @PostMapping("/upload")
    public String upload(Model model, @RequestParam("file") MultipartFile file) {
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
                    model.addAttribute("graph", stringBuilder.toString());
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

    @RequestMapping("/homework")
    public String homework(Model model) {
        model.addAttribute("answers", trainInfoService.calcGroups());
        return "detail";
    }
}
