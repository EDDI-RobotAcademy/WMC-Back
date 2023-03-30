package com.example.Backend.service.notice.request;

import com.example.Backend.entity.notice.Notice;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;


@Getter
@ToString
@RequiredArgsConstructor
public class NoticeRequest {

    final private String title;

    final private String writer;

    final private String content;


    public Notice toNotice() {

        Notice notice = new Notice(title, writer, content);

        return notice;
    }


   /* private String title;
    private String writer;
    private String content;*/
}
