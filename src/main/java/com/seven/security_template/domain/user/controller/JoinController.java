package com.seven.security_template.domain.user.controller;

import com.seven.security_template.domain.user.dto.UserRequestDTO;
import com.seven.security_template.domain.user.service.UserService;
import com.seven.security_template.global.error.BusinessException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class JoinController {

    private final UserService userService;

    @GetMapping("/join")
    public String joinPage(@ModelAttribute("userRequestDTO") UserRequestDTO userRequestDTO) {
        // th:object 사용을 위해 빈 객체를 모델에 담아 전달
        return "join";
    }

    @PostMapping("/join")
    public String join(@Valid @ModelAttribute("userRequestDTO") UserRequestDTO dto,
            BindingResult bindingResult,
            Model model) {

        // 1. 유효성 검사(@Valid) 에러 처리
        if (bindingResult.hasErrors()) {
            return "join";
        }

        try {
            // 2. 회원가입 로직 실행
            userService.join(dto);
        } catch (BusinessException e) {
            // 3. 중복 아이디 등 비즈니스 예외 처리
            bindingResult.rejectValue("username", "duplicate", e.getMessage());
            return "join";
        }

        return "redirect:/login"; // 가입 성공 시 로그인 페이지로
    }
}