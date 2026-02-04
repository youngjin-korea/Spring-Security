# Spring Security Template

## 🛡️ Spring Security: Form Login 인증 흐름

Spring Security의 `formLogin` 방식은 사용자의 자격 증명을 확인하고 인증된 객체를 생성하기 위해 아래와 같은 체계적인 단계를 거칩니다.



### 1. UsernamePasswordAuthenticationFilter (인증의 시작)
사용자가 로그인 폼에 ID/PW를 입력하면 필터 체인의 **`UsernamePasswordAuthenticationFilter`**가 요청을 가로챕니다.
* **역할**: 요청에서 ID와 PW를 추출하여 인증용 객체인 `UsernamePasswordAuthenticationToken`을 생성합니다.

### 2. AuthenticationManager의 위임
필터는 직접 인증을 처리하지 않고 **`AuthenticationManager`**(주로 `ProviderManager`)에게 생성된 토큰을 전달하며 인증을 요청합니다.

### 3. AuthenticationProvider의 검증 로직
`AuthenticationManager`는 등록된 **`AuthenticationProvider`**들을 차례로 실행합니다. 이 단계가 실제 검증이 일어나는 핵심 지점입니다.
* **역할**: DB의 실제 정보와 사용자가 입력한 정보가 일치하는지 대조합니다.

### 4. UserDetailsService & loadUserByUsername
`AuthenticationProvider`는 사용자의 정보를 가져오기 위해 **`UserDetailsService`**의 `loadUserByUsername()` 메서드를 호출합니다.
* **조회**: 입력받은 `username`을 기반으로 저장소(DB)에서 사용자 정보를 찾습니다.
* **반환**: 정보가 존재하면 이를 **`UserDetails`** 타입의 객체로 포장하여 `AuthenticationProvider`에게 응답합니다.

### 5. 비밀번호 검증 및 인증 완료
`AuthenticationProvider`는 `UserDetails`에 담긴 암호화된 비밀번호와 사용자가 입력한 비밀번호를 `PasswordEncoder`를 통해 비교합니다.
* **성공**: 권한(Authorities) 정보가 포함된 최종 **`Authentication`** 객체를 생성하여 반환합니다.
* **실패**: 자격 증명 예외(`BadCredentialsException` 등)를 던집니다.

---

### 💡 한눈에 보는 요약 테이블

| 단계 | 구성 요소 | 주요 역할 |
| :--- | :--- | :--- |
| **입구** | `UsernamePasswordAuthenticationFilter` | 요청 가로채기 및 인증 토큰 생성 |
| **제어** | `AuthenticationManager` | 적절한 `AuthenticationProvider` 선택 및 실행 |
| **검증** | `AuthenticationProvider` | 실질적인 비밀번호 검증 로직 수행 |
| **조회** | `UserDetailsService` | DB에서 사용자 정보를 찾아 `UserDetails`로 반환 |
<img width="675" height="327" alt="Image" src="https://github.com/user-attachments/assets/5fff95a3-eedb-4808-a83f-1e8f38089af5" />