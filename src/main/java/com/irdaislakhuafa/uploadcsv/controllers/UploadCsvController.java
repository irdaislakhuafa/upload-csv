package com.irdaislakhuafa.uploadcsv.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.irdaislakhuafa.uploadcsv.entities.User;
import com.irdaislakhuafa.uploadcsv.services.UserService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
// @EnableAsync
public class UploadCsvController {
    private static final String APP_TITLE = "Upload CSV";

    @Autowired
    private UserService userService;

    // @Async
    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", APP_TITLE);
        return "index";
    }

    // @Async
    @PostMapping("/upload/csv")
    public String uploadCsv(
            Model model,
            @RequestParam(value = "file") MultipartFile fileCsv,
            RedirectAttributes redirectAttributes) {
        try (
                // create object buffered reader
                Reader fileCsvReader = new BufferedReader(
                        // create input stream reader
                        new InputStreamReader(
                                // get input stream from fileCsv
                                fileCsv.getInputStream()))) {

            // if file is csv
            if (fileCsv.getContentType().equalsIgnoreCase("text/csv")) {
                CsvToBean<User> userBean = new CsvToBeanBuilder<User>(fileCsvReader)
                        // with type of class
                        .withType(User.class)

                        // ignore leading with space(abaikan depan dengan spasi)
                        .withIgnoreLeadingWhiteSpace(true)

                        // disable default throw exception
                        .withThrowExceptions(true)

                        // build object
                        .build();

                // parse userBean to List<>
                List<User> tempUsers = userBean.parse();

                // hash set to remove duplicate
                Set<User> users = new HashSet<>();

                // input list to set
                tempUsers.forEach((value) -> users.add(value));

                System.out.println(users);
                // save data
                userService.saveAll(users);

                String message = "Berhasil menyimpan file \"" + fileCsv.getOriginalFilename() + "\"";
                System.out.println(message);
                redirectAttributes.addFlashAttribute("success", message);
            } else {
                redirectAttributes.addFlashAttribute("errorNotCsv", "maaf file yang anda upload bukan CSV!");
            }
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("warningMessage",
                    "data dari file \"" + fileCsv.getOriginalFilename() + "\" sudah ada!");
        } catch (Exception e) {
            // System.out.println("\033\143");
            redirectAttributes.addFlashAttribute("errorParseCsvMessage",
                    "terjadi kesalahan saat memproses file \"" + fileCsv.getOriginalFilename() + "\"");
            System.out.println("terjadi kesalahan saat memproses file \"" + fileCsv.getOriginalFilename() + "\"");
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
