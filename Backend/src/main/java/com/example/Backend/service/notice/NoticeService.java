package com.example.Backend.service.notice;

import com.example.Backend.entity.notice.Notice;
import com.example.Backend.service.notice.request.NoticeRequest;

import java.util.List;

public interface NoticeService {
    public void register(NoticeRequest noticeRequest);

    List<Notice> list();
}
