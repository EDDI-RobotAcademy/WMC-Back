package com.example.Backend.service.notice.response;

import com.example.Backend.entity.notice.NoticeImageData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class NoticeReadResponse {

    private Long noticeId;
    private String title;
    private String writer;
    private String content;
    private Date regDate;
    private List<NoticeImageData> images;
}
