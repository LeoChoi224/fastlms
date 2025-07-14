package com.zerobase.fastlms.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/member")
@Controller
public class AdminMemberController {

    @GetMapping("/list.do")
    public String list() {

        return "admin/member/list";
    }
}
