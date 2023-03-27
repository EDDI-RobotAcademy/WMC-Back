package com.example.Backend.controller.notice;

import com.example.Backend.entity.notice.Notice;
import com.example.Backend.service.notice.NoticeService;
import com.example.Backend.service.notice.request.NoticeRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/notice")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class NoticeController {

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
    public List<Notice> noitceList () {
        log.info("noitceList()");

        return noticeService.list();
    }
}

