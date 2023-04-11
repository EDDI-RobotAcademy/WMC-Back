package com.example.Backend.controller.notice;

import com.example.Backend.controller.notice.form.NoticeRegisterForm;
import com.example.Backend.entity.notice.Notice;
import com.example.Backend.service.notice.NoticeService;
import com.example.Backend.service.notice.request.NoticeRequest;
import com.example.Backend.service.notice.response.NoticeListResponse;
import com.example.Backend.service.notice.response.NoticeReadResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class NoticeController {


    final private NoticeService noticeService;


    @PostMapping(value = "/register", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public boolean noticeRegister(@ModelAttribute NoticeRegisterForm form) throws IOException {
        if (form.getFileList() == null) {
            form.setFileList(new ArrayList<>());
        }

        log.info("noticeRegister(): " + form);
        log.info("Files received: " + form.getFileList().size());

        List<String> savedFiles = saveFiles(form.getFileList());

        NoticeRequest request = new NoticeRequest(form.getTitle(), form.getWriter(), form.getContent(), savedFiles);

        return noticeService.register(request);
    }

    private List<String> saveFiles(List<MultipartFile> fileList) {
        List<String> savedFilePaths = new ArrayList<>();
        String basePath = "../../WMC/WMC-Front/src/assets/noticeImages/";

        for (MultipartFile multipartFile : fileList) {
            log.info("saveFiles() - filename: " + multipartFile.getOriginalFilename());
            log.info("saveFiles() - file size: " + multipartFile.getSize());

            String savedFileName = basePath + multipartFile.getOriginalFilename();
            savedFilePaths.add("assets/noticeImages/"+multipartFile.getOriginalFilename());

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

    @GetMapping("/list")
    public List<NoticeListResponse> getAllNotice() {
        log.info("Entering getAllNotice() method");
        List<NoticeListResponse> result = noticeService.getAllNotices();
        log.info("Exiting getAllNotice() method with result: " + result);
        return result;
    }

    @GetMapping("/{noticeId}")
    public NoticeReadResponse noticeRead(@PathVariable("noticeId") Long noticeId) {
        log.info("noticeRead()");

        return noticeService.read(noticeId);
    }

    @DeleteMapping("/{noticeId}")
    public boolean deleteNotice(@PathVariable("noticeId") Long noticeId) {
        log.info("deleteNotice(): " + noticeId);
        return noticeService.delete(noticeId);
    }

    @PutMapping(value = "/modify/{noticeId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public boolean modifyNotice(@PathVariable("noticeId") Long noticeId,
                                @ModelAttribute NoticeRegisterForm form) throws IOException {
        if (form.getFileList() == null) {
            form.setFileList(new ArrayList<>());
        }

        log.info("modifyNotice(): " + form);
        log.info("Files received: " + form.getFileList().size());

        List<String> savedFiles = saveFiles(form.getFileList());

        NoticeRequest request = new NoticeRequest(form.getTitle(), form.getWriter(), form.getContent(), savedFiles);

        return noticeService.modify(noticeId, request);
    }
}