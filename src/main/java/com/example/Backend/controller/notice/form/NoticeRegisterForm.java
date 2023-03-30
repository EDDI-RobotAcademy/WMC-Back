package com.example.Backend.controller.notice.form;

import com.example.Backend.service.notice.request.NoticeRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NoticeRegisterForm {

    private String title;

    private String writer;

    private String content;


    public NoticeRequest toNoticeRequest() {
        return new NoticeRequest(title, writer, content);
    }
}
