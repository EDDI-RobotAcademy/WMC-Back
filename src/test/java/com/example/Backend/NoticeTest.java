package com.example.Backend;

import com.example.Backend.entity.notice.Notice;
import com.example.Backend.repository.notice.NoticeRepository;
import com.example.Backend.service.notice.NoticeService;
import com.example.Backend.service.notice.request.NoticeRequest;
import com.example.Backend.service.notice.response.NoticeListResponse;
import com.example.Backend.service.notice.response.NoticeReadResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class NoticeTest {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeRepository noticeRepository;


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

    @Test
    public void 공지사항_읽기_테스트() {
        noticeRepository.deleteAll();
        assertEquals(0, noticeRepository.count());

        List<String> savedFilePaths = Arrays.asList(
                "src/assets/noticeImages/image1.jpg",
                "src/assets/noticeImages/image1.jpg",
                "src/assets/noticeImages/image1.jpg"
        );

        Boolean isRegistered = noticeService.register(new NoticeRequest(
                "test", "test입니다", "test내용", savedFilePaths
        ));

        assertTrue(isRegistered);

        List<Notice> notices = noticeRepository.findAll();
        assertEquals(1, notices.size());

        Notice notice = notices.get(0);
        NoticeReadResponse noticeReadResponse = noticeService.read(notice.getNoticeId());
        assertEquals(notice.getNoticeId(), noticeReadResponse.getNoticeId());
    }


}


