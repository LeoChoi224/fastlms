package com.zerobase.fastlms.course.repository;

import com.zerobase.fastlms.course.entity.Course;
import com.zerobase.fastlms.course.entity.TakeCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface TakeCourseRepository extends JpaRepository<TakeCourse, Long> {




    long countByCourseIdAndUserIdAndStatusIn(Long courseId, String userId, Collection<String> statuses);
}
