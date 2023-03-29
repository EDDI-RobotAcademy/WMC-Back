package com.example.Backend.service.notice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NoticeListResponse {

    private long noticeId;
    private String title;
    private String writer;
    private String content;
}
