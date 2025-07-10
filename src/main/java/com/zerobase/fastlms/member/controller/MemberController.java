package com.zerobase.fastlms.member.controller;

import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @RequestMapping("/login")
    public String login() {

        return "member/login";
    }

    @GetMapping("/register")
    public String register() {
        return "member/register";
    }

    // request -> WEB -> SERVER
    // response -> SERVER -> WEB
    @PostMapping("/register")
    public String registerSubmit(
            Model model,
            HttpServletRequest request,
            MemberInput parameter
    ) {
        boolean result = memberService.register(parameter);

        model.addAttribute("result", result);

        return "member/register_complete";
    }

    // http://www.naver.com/news/list.do?id=123&key=124&text=쿼리
    // https://
    // 프로토콜://도메인(IP)/news/List.do?쿼리스트링 (파라미터)
    // https://www.naver.com/cafe/detail.do?id=1111
    // https://www.naver.com/cafe/detail.do?id=2222

    @GetMapping("/email-auth")
    public String emailAuth(Model model, HttpServletRequest request) {

        String uuid = request.getParameter("id");
        System.out.println(uuid);

        boolean result = memberService.emailAuth(uuid);
        model.addAttribute("result", result);

        return "member/email_auth";
    }

    @GetMapping("/info")
    public String memberInfo() {

        return "member/info";
    }

}