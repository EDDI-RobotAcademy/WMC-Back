package com.example.Backend.service.notice;

import com.example.Backend.entity.notice.Notice;
import com.example.Backend.repository.notice.NoticeRepository;
import com.example.Backend.service.notice.request.NoticeRequest;
import com.example.Backend.service.notice.response.NoticeListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service

public class NoticeServiceImpl implements NoticeService {
    final private NoticeRepository noticeRepository;

    public NoticeServiceImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

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
}