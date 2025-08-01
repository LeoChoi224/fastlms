package com.zerobase.fastlms.course.service;

import com.zerobase.fastlms.course.dto.TakeCourseDto;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.course.model.TakeCourseParam;

import java.util.List;

public interface TakeCourseService {

    /**
     * 수강 목록
     */
    List<TakeCourseDto> list(TakeCourseParam parameter);

    /**
     * 수강 내용 상태 변경
     */
    ServiceResult updateStatus(long id, String status);

    /**
     * 내 수강내역 목록
     */
    List<TakeCourseDto> myCourse(String userId);
}
