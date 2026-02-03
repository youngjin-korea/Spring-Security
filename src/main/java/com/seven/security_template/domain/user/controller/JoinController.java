package com.seven.security_template.domain.user.controller;

import com.seven.security_template.domain.user.dto.UserRequestDTO;
import com.seven.security_template.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class JoinController {

    private final UserService userService;

    @PostMapping("/join")
    public String join(@Valid @ModelAttribute UserRequestDTO dto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            log.info("로그인 검증 오류 발생: {}", bindingResult);
            return "/join";
        }

        userService.join(dto);
        return "redirect:/";
    }

    // 회원가입 페이지
    @GetMapping("/join")
    public String joinPage() {
        return "join";
    }
}
