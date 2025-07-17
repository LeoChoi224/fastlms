package com.zerobase.fastlms.course.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CourseDto {

    Long id;

    String imagePath;
    String keyword;
    String subject;

    String summary;
    String contents;
    long price;
    long salePrice;
    LocalDateTime saleEndDt;

    LocalDateTime regDt; // 등록일(추가 날짜)
    LocalDateTime udtDt; // 수정일(수정 날짜)

    // 추가컬럼
    long totalCount;
    long seq;
}
