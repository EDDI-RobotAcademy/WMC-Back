package com.example.Backend.service.notice;

import com.example.Backend.entity.notice.Notice;
import com.example.Backend.service.notice.request.NoticeRequest;
import com.example.Backend.service.notice.response.NoticeListResponse;
import com.example.Backend.service.notice.response.NoticeReadResponse;

import java.util.List;

public interface NoticeService {

    Boolean register(NoticeRequest noticeRequest);

    List<NoticeListResponse> getAllNotices();

    NoticeReadResponse read(Long noticeId);
}
