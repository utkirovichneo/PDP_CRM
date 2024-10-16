package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.config.service.CurrentUser;
import com.pdp.pdp_crm.config.service.JWTService;
import com.pdp.pdp_crm.dto.center.CenterDTO;
import com.pdp.pdp_crm.dto.center.CenterRequestDTO;
import com.pdp.pdp_crm.dto.token.RefreshTokenRequestDTO;
import com.pdp.pdp_crm.dto.token.RefreshTokenResponseDTO;
import com.pdp.pdp_crm.dto.user.UserRequestDTO;
import com.pdp.pdp_crm.dto.user.UserResponseDTO;
import com.pdp.pdp_crm.entity.User;
import com.pdp.pdp_crm.mapper.UserMapper;
import com.pdp.pdp_crm.repository.RoleRepository;
import com.pdp.pdp_crm.repository.UserRepository;
import com.pdp.pdp_crm.service.CenterAuthService;
import com.pdp.pdp_crm.service.CenterService;
import com.pdp.pdp_crm.util.AuthResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CenterAuthServiceImpl implements CenterAuthService {

    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final CenterService centerService;

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
    public UserResponseDTO me() {
        return userMapper.toDto(
                userRepository
                        .findUserByPhoneNumber(CurrentUser.getCurrentUsername())
                        .orElseThrow(() -> new RuntimeException("User not found")));
    }

    @Override
    public CenterDTO createCenter(CenterRequestDTO centerRequestDTO) {
        return centerService.save(centerRequestDTO);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}