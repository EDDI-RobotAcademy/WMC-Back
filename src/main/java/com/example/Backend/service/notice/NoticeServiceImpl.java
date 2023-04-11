package com.example.Backend.service.notice;

import com.example.Backend.entity.notice.Notice;
import com.example.Backend.entity.notice.NoticeImageData;
import com.example.Backend.repository.notice.NoticeRepository;
import com.example.Backend.repository.notice.NoticeImageDataRepository;
import com.example.Backend.service.notice.request.NoticeRequest;
import com.example.Backend.service.notice.response.NoticeListResponse;
import com.example.Backend.service.notice.response.NoticeReadResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    final private NoticeRepository noticeRepository;

    final private NoticeImageDataRepository noticeImageDataRepository;

    public Boolean register(NoticeRequest noticeRequest) {
        final Notice notice = noticeRequest.toNotice();
        noticeRepository.save(notice);

        return true;
    }


    @Override
    public List<NoticeListResponse> getAllNotices() {

        List<Notice> notices = noticeRepository.findAll();
        List<NoticeListResponse> noticeListResponses = new ArrayList<>();

        for (Notice notice : notices) {
            NoticeListResponse response = new NoticeListResponse(
                    notice.getNoticeId(),
                    notice.getTitle(),
                    notice.getWriter(),
                    notice.getContent(),
                    notice.getRegDate()
            );
            noticeListResponses.add(response);
        }
        return noticeListResponses;
    }

    @Override
    public NoticeReadResponse read(Long noticeId) {
        Optional<Notice> maybeNotice = noticeRepository.findById(noticeId);

        if (maybeNotice.isEmpty()) {
            log.info("읽을 수가 없습니다. {}", noticeId);
            return null;
        }

        Notice notice = maybeNotice.get();
        List<NoticeImageData> images = noticeImageDataRepository.findAllImagesByNoticeId(noticeId);

        NoticeReadResponse response = new NoticeReadResponse(
                notice.getNoticeId(),
                notice.getTitle(),
                notice.getWriter(),
                notice.getContent(),
                notice.getRegDate(),
                images
        );
        return response;
    }

    @Override
    public boolean delete(Long noticeId) {
        if (noticeRepository.existsById(noticeId)) {
            List<NoticeImageData> images = noticeImageDataRepository.findAllImagesByNoticeId(noticeId);
            for (NoticeImageData image : images) {
                noticeImageDataRepository.deleteById(image.getId());
            }

            noticeRepository.deleteById(noticeId);
            return true;
        } else {
            return false;
        }
    }


    @Override
    @Transactional
    public boolean modify(Long noticeId, NoticeRequest noticeRequest) {
        Optional<Notice> maybeNotice = noticeRepository.findById(noticeId);

        if (maybeNotice.isEmpty()) {
            log.info("Notice not found for modify. NoticeId: {}", noticeId);
            return false;
        }

        Notice notice = maybeNotice.get();

        try {
            notice.setTitle(noticeRequest.getTitle());
            notice.setWriter(noticeRequest.getWriter());
            notice.setContent(noticeRequest.getContent());

            List<NoticeImageData> existingImages = noticeImageDataRepository.findAllImagesByNoticeId(noticeId);
            existingImages.forEach(image -> noticeImageDataRepository.deleteById(image.getId()));

            List<String> newFilePaths = noticeRequest.getSavedFilePaths();
            newFilePaths.forEach(path -> {
                NoticeImageData noticeImageData = new NoticeImageData();
                noticeImageData.setNoticeImageData(path);
                notice.addNoticeImageData(noticeImageData);
            });

            noticeRepository.save(notice);
            return true;
        } catch (Exception e) {
            log.error("Error modifying notice with id: {}", noticeId, e);
            return false;
        }
    }
}