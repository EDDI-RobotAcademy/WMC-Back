package com.example.Backend.repository.jpa.notice;

import com.example.Backend.entity.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
