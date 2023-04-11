package com.example.Backend;

import com.example.Backend.entity.notice.Notice;
import com.example.Backend.entity.notice.NoticeImageData;
import com.example.Backend.repository.notice.NoticeImageDataRepository;
import com.example.Backend.repository.notice.NoticeRepository;
import com.example.Backend.service.notice.NoticeService;
import com.example.Backend.service.notice.request.NoticeRequest;
import com.example.Backend.service.notice.response.NoticeListResponse;
import com.example.Backend.service.notice.response.NoticeReadResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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

    @Test
    public void 상품삭제_확인() {

        noticeRepository.deleteAll();
        assertEquals(0, noticeRepository.count());

        List<String> savedFilePaths = Arrays.asList(
                "src/assets/noticeImages/image1.jpg",
                "src/assets/noticeImages/image1.jpg",
                "src/assets/noticeImages/image1.jpg"
        );

        assertTrue(noticeService.register(new NoticeRequest(
                "test", "test입니다", "test내용", savedFilePaths
        )));
        assertEquals(1, noticeRepository.count());

        List<Notice> notices = noticeRepository.findAll();
        Notice notice = notices.get(0);

        boolean result = noticeService.delete(notice.getNoticeId());
        assertTrue(result);
        assertEquals(0, noticeRepository.count());

        result = noticeService.delete(notice.getNoticeId());

        assertFalse(result);
    }

    @Test
    @Transactional
    public void 공지사항_수정_확인() {
        // Prepare test data
        Notice initialNotice = new Notice("Title", "Writer", "Content");
        noticeRepository.save(initialNotice);

        List<String> savedFilePaths = Arrays.asList(
                "src/assets/noticeImages/abc.jpg",
                "src/assets/noticeImages/abc.jpg",
                "src/assets/noticeImages/abc.jpg"
        );

        NoticeRequest modifyRequest = new NoticeRequest(
                "modify Title", "modify Writer", "modify Content", savedFilePaths
        );

        // Test modify
        boolean modifyResult = noticeService.modify(initialNotice.getNoticeId(), modifyRequest);
        assertTrue(modifyResult);

        // Check modifyd data
        Optional<Notice> modifydNoticeOptional = noticeRepository.findById(initialNotice.getNoticeId());
        assertTrue(modifydNoticeOptional.isPresent());
        Notice modifydNotice = modifydNoticeOptional.get();

        assertEquals(modifyRequest.getTitle(), modifydNotice.getTitle());
        assertEquals(modifyRequest.getWriter(), modifydNotice.getWriter());
        assertEquals(modifyRequest.getContent(), modifydNotice.getContent());

        // Check images
        List<String> imagePaths = modifydNotice.getNoticeImageDataList().stream()
                .map(NoticeImageData::getNoticeImageData)
                .collect(Collectors.toList());

        assertEquals(modifyRequest.getSavedFilePaths().size(), imagePaths.size());
        assertTrue(imagePaths.containsAll(modifyRequest.getSavedFilePaths()));
    }
}