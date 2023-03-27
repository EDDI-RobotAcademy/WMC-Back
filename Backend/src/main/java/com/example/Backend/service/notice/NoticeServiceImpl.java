package com.example.Backend.service.notice;

import com.example.Backend.entity.notice.Notice;
import com.example.Backend.repository.notice.NoticeRepository;
import com.example.Backend.service.notice.request.NoticeRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NoticeServiceImpl implements NoticeService {
    final private NoticeRepository noticeRepository;

    public NoticeServiceImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Override
    public void register(NoticeRequest noticeRequest) {
        Notice notice = new Notice();
        notice.setTitle(noticeRequest.getTitle());
        notice.setWriter(noticeRequest.getWriter());
        notice.setContent(noticeRequest.getContent());

        noticeRepository.save(notice);
    }

    public List<Notice> list() {
        return noticeRepository.findAll(Sort.by(Sort.Direction.DESC, "noticeId"));
    }
}
