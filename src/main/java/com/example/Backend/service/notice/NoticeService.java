package com.example.Backend.service.notice;

import com.example.Backend.entity.notice.Notice;
import com.example.Backend.service.notice.request.NoticeRequest;
import com.example.Backend.service.notice.response.NoticeListResponse;

import java.util.List;

public interface NoticeService {

    Boolean register(NoticeRequest noticeRequest);


    /*public void register(NoticeRequest noticeRequest);*/

    List<NoticeListResponse> getAllNotices();
}
