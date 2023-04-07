package com.example.Backend.service.notice.request;

import com.example.Backend.entity.notice.NoticeImageData;
import com.example.Backend.entity.notice.Notice;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Getter
@ToString
@RequiredArgsConstructor
public class NoticeRequest {

    final private String title;

    final private String writer;

    final private String content;

    final private List<String> savedFilePaths;


    public Notice toNotice() {

        List<NoticeImageData> noitceImageDataList = new ArrayList<>();
        Notice notice = new Notice(title, writer, content);

        for (String path : savedFilePaths) {
            NoticeImageData noticeImageData = new NoticeImageData();
            noticeImageData.setNoticeImageData(path);
            notice.addNoticeImageData(noticeImageData);
        }

        return notice;
    }

}
