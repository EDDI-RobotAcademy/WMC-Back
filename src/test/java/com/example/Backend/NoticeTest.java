package com.example.Backend;


import com.example.Backend.repository.notice.NoticeImageDataRepository;
import com.example.Backend.repository.notice.NoticeRepository;
import com.example.Backend.service.notice.NoticeService;
import com.example.Backend.service.notice.request.NoticeRequest;
import com.example.Backend.service.notice.response.NoticeListResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NoticeTest {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private NoticeImageDataRepository noticeImageDataRepository;


    @Test
    public void 공지사항등록_확인() {
        List<String> savedFilePaths = Arrays.asList(
                "src/assets/noticeImages/image1.jpg",
                "src/assets/noticeImages/image1.jpg",
                "src/assets/noticeImages/image1.jpg"
        );
        assertTrue(noticeService.register(new NoticeRequest(
                "test", "test입니다", "test내용", savedFilePaths
        )));
    }

    @Test
    public void 공지사항_리스트_테스트() {
        List<NoticeListResponse> notices = noticeService.getAllNotices();
        assertNotNull(notices);
        assertTrue(notices.size() > 0);
    }


}