package com.example.Backend.controller.review;

import com.example.Backend.controller.review.form.ReviewRegisterForm;
import com.example.Backend.service.review.ReviewService;
import com.example.Backend.service.review.request.ReviewRegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
// 초기화가 필요한 생성과 동시에 초기화가 필요한 (iv)들을 초기화 시켜줌
@CrossOrigin(origins = "http://localhost:8080",allowedHeaders = "*")
public class ReviewController {

    final private ReviewService reviewService;

    @PostMapping(value = "/register", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public boolean reviewRegister(@ModelAttribute ReviewRegisterForm form) throws IOException {
        log.info("reviewRegister(): " + form);
        log.info("Files received: " + form.getFileList().size());

        List<String> savedFiles = saveFiles(form.getFileList());

        ReviewRegisterRequest request = new ReviewRegisterRequest(form.getTitle(), form.getWriter(), form.getRating(), form.getContent(), savedFiles);

        return reviewService.register(request);
    }
    private List<String> saveFiles(List<MultipartFile> fileList) {
        List<String> savedFilePaths = new ArrayList<>();
        String basePath = "../../../finalProject/WMC-Front/src/assets/productImages/";

        for (MultipartFile multipartFile : fileList) {
            log.info("saveFiles() - filename: " + multipartFile.getOriginalFilename());
            log.info("saveFiles() - file size: " + multipartFile.getSize());

            String savedFileName = basePath + multipartFile.getOriginalFilename();
            savedFilePaths.add("assets/productImages/"+multipartFile.getOriginalFilename());

            try {
                FileOutputStream writer = new FileOutputStream(savedFileName);
                writer.write(multipartFile.getBytes());
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return savedFilePaths;
    }

}
