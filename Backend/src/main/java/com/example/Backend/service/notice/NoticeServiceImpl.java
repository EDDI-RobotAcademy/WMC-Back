package com.example.Backend.service.notice;

import com.example.Backend.entity.notice.Notice;
import com.example.Backend.entity.product.ImageData;
import com.example.Backend.entity.product.Product;
import com.example.Backend.repository.notice.NoticeRepository;
import com.example.Backend.service.notice.request.NoticeRequest;
import com.example.Backend.service.notice.response.NoticeListResponse;
import com.example.Backend.service.product.response.ProductListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
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
                    notice.getContent()

            );
            noticeListResponses.add(response);
        }
        return noticeListResponses;

    /*@Override
    public void register(NoticeRequest noticeRequest) {
        Notice notice = new Notice();
        notice.setTitle(noticeRequest.getTitle());
        notice.setWriter(noticeRequest.getWriter());
        notice.setContent(noticeRequest.getContent());

        noticeRepository.save(notice);
    }*/
    }
}