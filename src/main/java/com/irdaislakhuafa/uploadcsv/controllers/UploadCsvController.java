package com.irdaislakhuafa.uploadcsv.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.irdaislakhuafa.uploadcsv.entities.User;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

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
        try (
                // create object buffered reader
                Reader fileCsvReader = new BufferedReader(
                        // create input stream reader
                        new InputStreamReader(
                                // get input stream from fileCsv
                                fileCsv.getInputStream()))) {

            System.out.println("\033\143");
            System.out.println(fileCsv.getOriginalFilename() + " => " + fileCsv.getContentType());

            CsvToBean<User> userBean = new CsvToBeanBuilder<User>(fileCsvReader)
                    // with type of class
                    .withType(User.class)

                    // ignore leading with space(abaikan depan dengan spasi)
                    .withIgnoreLeadingWhiteSpace(true)

                    // disable default throw exception
                    .withThrowExceptions(false)

                    // build object
                    .build();

            // parse userBean to List<>
            List<User> tempUsers = userBean.parse();

            // hash set to remove duplicate
            Set<User> users = new HashSet<>();

            // input list to set
            tempUsers.forEach(users::add);

            new Thread(() -> {

            }).start();
        } catch (Exception e) {
            // System.out.println("\033\143");
            System.out.println("terjadi kesalahan saat memproses file \"" + fileCsv.getOriginalFilename() + "\"");
        }
        return "redirect:/";
    }
}
