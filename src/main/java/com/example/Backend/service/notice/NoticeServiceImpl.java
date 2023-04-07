package com.example.Backend.service.notice;

import com.example.Backend.entity.notice.Notice;
import com.example.Backend.entity.notice.NoticeImageData;
import com.example.Backend.repository.notice.NoticeRepository;
import com.example.Backend.repository.notice.NoticeImageDataRepository;
import com.example.Backend.service.notice.request.NoticeRequest;
import com.example.Backend.service.notice.response.NoticeReadResponse;
import com.example.Backend.service.notice.response.NoticeListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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



}