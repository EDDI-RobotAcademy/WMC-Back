package com.example.Backend.controller.notice;

import com.example.Backend.controller.notice.form.NoticeRegisterForm;
import com.example.Backend.entity.notice.Notice;
import com.example.Backend.service.notice.NoticeService;
import com.example.Backend.service.notice.request.NoticeRequest;
import com.example.Backend.service.notice.response.NoticeListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
        log.info("noticeRegister(): " + form);

        NoticeRequest request = new NoticeRequest(form.getTitle(), form.getWriter(), form.getContent());

        return noticeService.register(request);
    }

    @GetMapping("/list")
    public List<NoticeListResponse> getAllNotice() {
        return noticeService.getAllNotices();
    }

    /*
    final private NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }



    @PostMapping("/register")
    public void noticeRegister (@RequestBody NoticeRequest noticeRequest) {
        log.info("noticeRegister()");

        noticeService.register(noticeRequest);
    }

    @GetMapping("/list")
    public List<Notice> noticeList () {
        log.info("noticeList()");

        return noticeService.list();
    }*/
}

