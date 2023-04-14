package com.example.Backend.repository.jpa.notice;

import com.example.Backend.entity.notice.NoticeImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeImageDataRepository extends JpaRepository<NoticeImageData, Long> {
    @Query("select i from NoticeImageData i join i.notice n where n.noticeId = :noticeId")
    List<NoticeImageData> findAllImagesByNoticeId(Long noticeId);
}
