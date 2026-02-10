package com.example.bakend.auth.service;
import com.example.bakend.auth.dto.JwtResponse;
import com.example.bakend.auth.dto.LoginRequest;
import com.example.bakend.auth.dto.RegisterRequest;
import com.example.bakend.security.JwtUtil;
import com.example.bakend.user.entity.User;
import com.example.bakend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public void register(RegisterRequest request) {

        if (userRepository.existsByMobile(request.getMobile())) {
            throw new RuntimeException("Mobile number already registered");
        }

        User user = User.builder()
                .name(request.getName())
                .mobile(request.getMobile())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .active(true)
                .verified(false)
                .build();

        userRepository.save(user);
        // Later: send OTP
    }

    public JwtResponse login(LoginRequest request) {

        User user = userRepository.findByMobile(request.getMobile())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getMobile(), user.getRole().name());

        return new JwtResponse(token, user.getRole(), user.getId());
    }
}
