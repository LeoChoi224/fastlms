package com.zerobase.fastlms.course.controller;

import com.zerobase.fastlms.admin.service.CategoryService;
import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.model.CourseInput;
import com.zerobase.fastlms.course.model.CourseParam;
import com.zerobase.fastlms.course.service.CourseService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin/course")
@Controller
public class AdminCourseController extends BaseController {

    private final CourseService courseService;
    private final CategoryService categoryService;

    @GetMapping("/list.do")
    public String list(Model model, CourseParam parameter) {

        parameter.init();
        List<CourseDto> courseList = courseService.list(parameter);

        long totalCount = 0;
        if (!CollectionUtils.isEmpty(courseList)) {
            totalCount = courseList.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", courseList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pageUtil", pagerHtml);

        return "admin/course/list";
    }

    @GetMapping(value = {"/add.do", "edit.do"})
    public String add(Model model,
                      HttpServletRequest request,
                      CourseInput parameter
    ) {
        // 카테고리 정보를 내려줘야 함.
        model.addAttribute("category", categoryService.list());




        boolean isEditMode = request.getRequestURI().contains("/edit.do");
        CourseDto detail = new CourseDto();

        if (isEditMode) {
            long id = parameter.getId();
            CourseDto existCourse = courseService.getById(id);

            if (existCourse == null) {
                // error 처리
                model.addAttribute("message", "강좌정보가 존재하지 않습니다.");
                return "common/error";
            }
            detail = existCourse;
        }

        model.addAttribute("editMode", isEditMode);
        model.addAttribute("detail", detail);

        return "admin/course/add";
    }

    @PostMapping(value = {"/add.do", "edit.do"})
    public String addSubmit(Model model,
                            HttpServletRequest request,
                            CourseInput parameter
    ) {
        boolean isEditMode = request.getRequestURI().contains("/edit.do");

        if (isEditMode) {
            long id = parameter.getId();
            CourseDto existCourse = courseService.getById(id);

            if (existCourse == null) {
                // error 처리
                model.addAttribute("message", "강좌정보가 존재하지 않습니다.");
                return "common/error";
            }

            boolean result = courseService.set(parameter);
        } else {
            boolean result = courseService.add(parameter);
        }

        return "redirect:/admin/course/list.do";
    }

    @PostMapping("/delete.do")
    public String del(Model model,
                            HttpServletRequest request,
                            CourseInput parameter
    ) {
        boolean result = courseService.del(parameter.getIdList());


        return "redirect:/admin/course/list.do";
    }

}
