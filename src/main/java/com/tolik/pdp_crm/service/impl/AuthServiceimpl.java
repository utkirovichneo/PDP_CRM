package com.tolik.pdp_crm.service.impl;

import com.tolik.pdp_crm.config.service.JWTService;
import com.tolik.pdp_crm.dto.token.RefreshTokenRequestDTO;
import com.tolik.pdp_crm.dto.token.RefreshTokenResponseDTO;
import com.tolik.pdp_crm.dto.user.UserRequestDTO;
import com.tolik.pdp_crm.dto.user.UserResponseDTO;
import com.tolik.pdp_crm.entity.User;
import com.tolik.pdp_crm.mapper.UserMapper;
import com.tolik.pdp_crm.repository.RoleRepository;
import com.tolik.pdp_crm.repository.UserRepository;
import com.tolik.pdp_crm.service.AuthService;
import com.tolik.pdp_crm.util.AuthResponseDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AuthServiceimpl implements AuthService {

    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public AuthServiceimpl(UserMapper userMapper, AuthenticationManager authenticationManager, JWTService jwtService, UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public AuthResponseDTO login(UserRequestDTO userRequestDTO) {

        var user = userRepository.findUserByPhoneNumber(userRequestDTO.getPhoneNumber())
                .orElseThrow(() -> new UsernameNotFoundException("Bunday raqamli USER mavjud emas"));

        if(!passwordEncoder.matches(userRequestDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Password doesn't match");
        }

        var authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userRequestDTO.getPhoneNumber(),
                userRequestDTO.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        return AuthResponseDTO
                .builder()
                .accessToken(jwtService.accessToken(userRequestDTO.getPhoneNumber()))
                .refreshToken(jwtService.refreshToken(userRequestDTO.getPhoneNumber()))
                .build();
    }

    @Override
    public UserResponseDTO register(UserRequestDTO userRequestDTO) {
        var user = User
                .builder()
                .phoneNumber(userRequestDTO.getPhoneNumber())
                .password(passwordEncoder.encode(userRequestDTO.getPassword()))
                .roles(Set.of(roleRepository.findById(1L).orElseThrow(()-> new RuntimeException("Role not found"))))
                .build();
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public RefreshTokenResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO) {
        var refreshToken = refreshTokenRequestDTO.getRefreshToken();
        var phoneNumber = jwtService.getUsername(refreshToken);
        return RefreshTokenResponseDTO.builder()
                .accessToken(jwtService.accessToken(phoneNumber))
                .build();
    }

    @Override
    public List<UserResponseDTO> getAll() {
        return userMapper.toDto(userRepository.findAll());
    }
}