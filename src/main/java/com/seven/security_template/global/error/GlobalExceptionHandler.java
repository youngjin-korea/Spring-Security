package com.seven.security_template.global.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public String handleBusinessException(BusinessException e,
            RedirectAttributes redirectAttributes) {
        log.warn("비즈니스 로직 에러 발생: {}", e.getMessage());

        // 화면에 에러 메시지를 전달하고 싶을 때
        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        return "redirect:/join"; // 혹은 적절한 에러 페이지
    }
}
