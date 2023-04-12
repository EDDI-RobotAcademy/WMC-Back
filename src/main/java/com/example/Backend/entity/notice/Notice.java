package com.example.Backend.entity.notice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeId;

    @Column(length = 128, nullable = false)
    private String title;

    @Column(length = 32, nullable = false)
    private String writer;

    @Lob
    private String content;

    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<NoticeImageData> noticeImageDataList = new ArrayList<>();

    @CreationTimestamp
    private Date regDate;

    @UpdateTimestamp
    private Date updDate;

    public Notice(String title, String writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    public void addNoticeImageData(NoticeImageData noticeImageData) {
        noticeImageData.setNotice(this);
        noticeImageDataList.add(noticeImageData);
    }

    public Notice(String title, String writer, String content, List<NoticeImageData> noticeImageDataList) {
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.noticeImageDataList = noticeImageDataList;
    }

}
