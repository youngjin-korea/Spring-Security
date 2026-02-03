package com.seven.security_template.global.error;

/**
 * 프로젝트 전반에서 발생하는 비즈니스 로직 오류를 처리하기 위한 예외 RuntimeException을 상속받아야 트랜잭션 발생 시 자동으로 롤백됩니다.
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    // 예외 원인(Throwable)까지 함께 전달하고 싶을 때 사용
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
