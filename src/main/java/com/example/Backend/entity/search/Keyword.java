package com.example.Backend.entity.search;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keywordNo;

    @Column(nullable = false)
    private String keyword;

    @Column(nullable = false)
    private Integer searchedCnt;

    @CreationTimestamp
    private LocalDate regDate;

    @UpdateTimestamp
    private LocalDate upDate;
}
