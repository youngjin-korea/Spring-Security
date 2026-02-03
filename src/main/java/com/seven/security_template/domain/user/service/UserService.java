package com.seven.security_template.domain.user.service;

import com.seven.security_template.domain.user.dto.UserRequestDTO;
import com.seven.security_template.domain.user.entity.UserEntity;
import com.seven.security_template.domain.user.entity.UserRole;
import com.seven.security_template.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // TODO:: 디테일을 추가하시려면 : 이미 동일한 username이 존재하는지 체크, 성공 여부에 따른 return 값 추가
    public void join(UserRequestDTO dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();

        UserEntity entity = UserEntity.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(UserRole.USER).build();

        userRepository.save(entity);
    }
}
