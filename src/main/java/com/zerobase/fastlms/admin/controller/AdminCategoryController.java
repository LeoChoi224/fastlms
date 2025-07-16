package com.zerobase.fastlms.admin.controller;

import com.zerobase.fastlms.admin.dto.CategoryDto;
import com.zerobase.fastlms.admin.model.CategoryInput;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin/category")
@Controller
public class AdminCategoryController {

    private final CategoryService categoryService;

    @GetMapping("/list.do")
    public String list(Model model, MemberParam parameter) {

        List<CategoryDto> list = categoryService.list();
        model.addAttribute("list", list);

        return "admin/category/list";
    }

    @PostMapping("/add.do")
    public String add(Model model, CategoryInput parameter) {
        boolean result = categoryService.add(parameter.getCategoryName());

        return "redirect:/admin/category/list.do";
    }

    @PostMapping("/delete.do")
    public String del(Model model, CategoryInput parameter) {
        boolean result = categoryService.del(parameter.getId());

        return "redirect:/admin/category/list.do";
    }

    @PostMapping("/update.do")
    public String update(Model model, CategoryInput parameter) {
        boolean result = categoryService.update(parameter);

        return "redirect:/admin/category/list.do";
    }

}
