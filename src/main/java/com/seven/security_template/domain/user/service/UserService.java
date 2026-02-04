package com.seven.security_template.domain.user.service;

import com.seven.security_template.domain.user.dto.UserRequestDTO;
import com.seven.security_template.domain.user.entity.UserEntity;
import com.seven.security_template.domain.user.entity.UserRole;
import com.seven.security_template.domain.user.repository.UserRepository;
import com.seven.security_template.global.error.BusinessException; // 아까 만든 커스텀 예외
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 이 메소드를 구현해두면, AuthenticationProvider가 알아서 불러서 사용 함.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity findUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessException("존재하지 않는 아이디 입니다."));

        return User.builder()
                .username(findUser.getUsername())
                .password(findUser.getPassword())
                .roles(findUser.getRole().name()).build();
    }

    @Transactional
    public void join(UserRequestDTO dto) {
        // 1. 아이디 중복 체크
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new BusinessException("이미 존재하는 아이디입니다.");
        }

        // 2. 엔티티 변환 및 저장
        UserEntity entity = UserEntity.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(UserRole.USER) // 기본 권한 부여
                .build();

        userRepository.save(entity);
    }
}