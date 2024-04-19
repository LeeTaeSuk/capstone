package com.example.bangbang_gotgot.member.controller;

import com.example.bangbang_gotgot.member.dto.AllUserInfoDto;
import com.example.bangbang_gotgot.member.dto.MemberDto;
import com.example.bangbang_gotgot.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    // 회원가입
    @GetMapping("/bangbang/auth/sign-in")
    public String join(){

        return "member/signUp/signUp";
    }

    @PostMapping("/bangbang/auth/sign-in")
    public String joinProc(AllUserInfoDto allUserInfoDto, Model model) {
        model.addAttribute("uesrInfo",allUserInfoDto);
        return "member/signUp/signUp2";
    }



//    @GetMapping("/bangbang/auth/sign-up")
//    public String login(){
//
//        return "member/login";
//    }
//
//
//
//    @GetMapping("/signup")
//    public String signupForm(Model model) {
//        model.addAttribute("memberDto", new MemberDto());
//        return "signup";
//    }

//    @PostMapping("/signup")
//    public String signup(MemberDto memberDto) {
//        memberService.signup(memberDto);
//        return "redirect:/login";
//    }


    // 로그인

    @GetMapping("/bangbang/auth/sign-up")
    public String loginForm(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "member/login";
    }

    @PostMapping("/bangbang/auth/sign-up")
    public String login(MemberDto memberDto) {
        try {
            MemberDto loggedInMember = memberService.login(memberDto.getPersonId(), memberDto.getPassWd());
            // 세션에 로그인 정보 저장
            // ...
            return "redirect:/"; // 메인 페이지로 리디렉션
        } catch (IllegalArgumentException e) {
            // 로그인 실패 시 처리
            return "redirect:/error/404";
        }
    }



}
