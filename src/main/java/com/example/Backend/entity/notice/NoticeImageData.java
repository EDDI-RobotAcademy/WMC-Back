package com.example.Backend.entity.notice;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
public class NoticeImageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "notice_id")
    private Notice notice;

    @Column
    private String noticeImageData;
}
