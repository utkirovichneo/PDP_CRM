package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.config.service.CurrentUser;
import com.pdp.pdp_crm.config.service.JWTService;
import com.pdp.pdp_crm.config.service.StorageService;
import com.pdp.pdp_crm.dto.center.CenterDTO;
import com.pdp.pdp_crm.dto.center.CenterRequestDTO;
import com.pdp.pdp_crm.dto.token.RefreshTokenRequestDTO;
import com.pdp.pdp_crm.dto.token.RefreshTokenResponseDTO;
import com.pdp.pdp_crm.dto.user.UserRequestDTO;
import com.pdp.pdp_crm.dto.user.UserResponseDTO;
import com.pdp.pdp_crm.entity.Center;
import com.pdp.pdp_crm.entity.Image;
import com.pdp.pdp_crm.entity.User;
import com.pdp.pdp_crm.exception.NotFoundException;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    private final CurrentUser currentUser;
    private final StorageService storageService;
    private static final String CENTER_FOLDER = "center";

    @Override
    public AuthResponseDTO login(UserRequestDTO userRequestDTO) {

        var user = userRepository.findUserByPhoneNumber(userRequestDTO.getPhoneNumber())
                .orElseThrow(() -> new UsernameNotFoundException("Bunday raqamli USER mavjud emas"));

        if (!passwordEncoder.matches(userRequestDTO.getPassword(), user.getPassword())) {
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
                .roles(Set.of(roleRepository.findById(1L).orElseThrow(() -> new NotFoundException("Role"))))
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
                        .findUserByPhoneNumber(currentUser.getCurrentUsername())
                        .orElseThrow(() -> new NotFoundException("User")));
    }

    @Override
    public CenterDTO createCenter(CenterRequestDTO centerRequestDTO) {
        return centerService.save(centerRequestDTO);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public CenterDTO saveLogo(MultipartFile logo) {
        String path = storageService.uploadFile(logo, CENTER_FOLDER);
        Center center = centerService.findByOwnerId(userRepository.findByUsername(currentUser.getCurrentUsername()).orElseThrow(() -> new NotFoundException("User")).getId());
        center.setLogo(
                Image.builder()
                        .url(path)
                        .name(center.getName())
                        .prefix(CENTER_FOLDER)
                        .build()
        );
        return centerService.save(center);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username: " + username));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }
}