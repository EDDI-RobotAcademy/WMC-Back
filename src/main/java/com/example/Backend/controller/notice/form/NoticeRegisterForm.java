package com.example.Backend.controller.notice.form;

import com.example.Backend.service.notice.request.NoticeRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NoticeRegisterForm {

    private String title;

    private String writer;

    private String content;

    private List<MultipartFile> fileList;


    public NoticeRequest toNoticeRequest(List<String> savedFilePaths) {

        return new NoticeRequest(title, writer, content, savedFilePaths);
    }
}
