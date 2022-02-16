package com.irdaislakhuafa.uploadcsv.controllers;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
// @EnableAsync
public class UploadCsvController {
    private static final String APP_TITLE = "Upload CSV";

    // @Async
    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", APP_TITLE);
        return "index";
    }

    // @Async
    @PostMapping("/upload/csv")
    public String uploadCsv(Model model, @RequestParam(value = "file") MultipartFile fileCsv) {
        try {
            System.out.println(fileCsv.getOriginalFilename() + " => " + fileCsv.getContentType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
