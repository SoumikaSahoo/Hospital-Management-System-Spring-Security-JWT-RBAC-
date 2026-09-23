package com.hospitalManagement.security;

import com.hospitalManagement.dto.LoginRequestDto;
import com.hospitalManagement.dto.LoginResponseDto;
import com.hospitalManagement.dto.SignUpRequestDto;
import com.hospitalManagement.dto.SignupResponseDto;
import com.hospitalManagement.entity.Patient;
import com.hospitalManagement.entity.User;
import com.hospitalManagement.entity.type.AuthProviderType;
import com.hospitalManagement.repository.PatientRepository;
import com.hospitalManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PatientRepository patientRepository;

    // 🔐 LOGIN
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword()
                )
        );

        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(user);

        return new LoginResponseDto(token, user.getId());
    }

    // 🧾 SIGNUP
    public SignupResponseDto signup(SignUpRequestDto signupRequestDto) {

        // Check if user already exists
        User existingUser = userRepository.findByUsername(signupRequestDto.getUsername()).orElse(null);
        if (existingUser != null) {
            throw new IllegalArgumentException("User already exists");
        }

        // Create user
        User user = User.builder()
                .username(signupRequestDto.getUsername())
                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                .providerType(AuthProviderType.EMAIL) // Only EMAIL now
                .roles(signupRequestDto.getRoles())
                .build();

        user = userRepository.save(user);

        // Create patient
        Patient patient = Patient.builder()
                .name(signupRequestDto.getName())
                .email(signupRequestDto.getUsername())
                .user(user)
                .build();

        patientRepository.save(patient);

        return new SignupResponseDto(user.getId(), user.getUsername());
    }
}