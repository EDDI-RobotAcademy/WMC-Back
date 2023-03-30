package com.example.Backend;

import ch.qos.logback.core.CoreConstants;
import com.example.Backend.service.notice.NoticeService;
import com.example.Backend.service.notice.request.NoticeRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class NoticeTest {

    @Autowired
    private NoticeService noticeService;

    @Test
    public void 공지사항등록_확인() {
        assertTrue(noticeService.register(new NoticeRequest(
                "test", "test입니다", "test내용"
        )));
    }

    @Test
    public void 공지사항_리스트_테스트() {
        System.out.println(noticeService.getAllNotices());
    }
}
