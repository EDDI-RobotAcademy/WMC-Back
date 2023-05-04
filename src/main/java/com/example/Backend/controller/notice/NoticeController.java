package com.example.Backend.controller.notice;

import com.example.Backend.controller.member.MemberController;
import com.example.Backend.controller.notice.form.NoticeRegisterForm;
import com.example.Backend.entity.notice.Notice;
import com.example.Backend.entity.notice.NoticeImageData;
import com.example.Backend.repository.jpa.notice.NoticeImageDataRepository;
import com.example.Backend.service.notice.NoticeService;
import com.example.Backend.service.notice.request.NoticeRequest;
import com.example.Backend.service.notice.response.NoticeListResponse;
import com.example.Backend.service.notice.response.NoticeReadResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {


    final private NoticeService noticeService;

    @Autowired
    private NoticeImageDataRepository noticeImageDataRepository;

    @Autowired
    private MemberController memberController;


    @PostMapping(value = "/register", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public boolean noticeRegister(@RequestBody NoticeRegisterForm form) throws IOException {

        log.info("noticeRegister(): " + form);

        List<String> savedFiles = form.getFileNames();

        NoticeRequest request = new NoticeRequest(form.getTitle(), form.getWriter(), form.getContent(), savedFiles);

        return noticeService.register(request);
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

    @DeleteMapping("/delete/{noticeId}")
    public boolean deleteNotice(@PathVariable("noticeId") Long noticeId) {
        log.info("deleteNotice(): " + noticeId);
        return noticeService.delete(noticeId);
    }


    @PutMapping(value = "/modify/{noticeId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    //public boolean modifyNotice(@PathVariable("noticeId") Long noticeId,
                                //@ModelAttribute NoticeRegisterForm form) throws IOException {
    public boolean modifyNotice(@RequestHeader("Authorization") String token, @PathVariable("noticeId") Long noticeId,
                                @ModelAttribute NoticeRegisterForm form) throws IOException {
        if (!memberController.isManager(token)) {
            return false;
        }

        log.info("modifyNotice(): " + form);

        List<String> savedFiles = form.getFileNames();

        NoticeRequest request = new NoticeRequest(form.getTitle(), form.getWriter(), form.getContent(), savedFiles);

        return noticeService.modify(noticeId, request);
    }
}