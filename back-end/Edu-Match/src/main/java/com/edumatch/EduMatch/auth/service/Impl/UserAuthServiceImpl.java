package com.edumatch.EduMatch.auth.service.Impl;

import com.edumatch.EduMatch.auth.service.JwtUtil;
import com.edumatch.EduMatch.auth.service.UserAuthService;
import com.edumatch.EduMatch.auth.service.UserDetailsCustomService;
import com.edumatch.EduMatch.models.UserEntity;
import com.edumatch.EduMatch.models.mappers.AuthenticationMapper;
import com.edumatch.EduMatch.models.mappers.RoleMapper;
import com.edumatch.EduMatch.models.request.AuthenticationRequest;
import com.edumatch.EduMatch.models.response.AuthenticationResponse;
import com.edumatch.EduMatch.models.response.ProjectResponse;
import com.edumatch.EduMatch.models.response.UserResponse;
import com.edumatch.EduMatch.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserAuthServiceImpl implements UserAuthService {

    private final JwtUtil jwtTokenUtil;

    private final AuthenticationManager authenticationManager;

    private final AuthenticationMapper authenticationMapper;

    private final UserDetailsCustomService userDetailsCustomService;

    private final UserRepository userRepository;

    private final RoleMapper roleMapper;

    @Override
    public AuthenticationResponse loginAttempt(AuthenticationRequest authenticationRequest) {
        UserDetails userDetails = userDetailsCustomService.loadUserByUsername(authenticationRequest.getEmail());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(),
                authenticationRequest.getPassword(),
                userDetails.getAuthorities()
        );
        authenticationManager.authenticate(authenticationToken);
        // Build Response:
        String jwt = jwtTokenUtil.generateToken(userDetails);
        Date expDate = jwtTokenUtil.extractExpiration(jwt);
        SimpleDateFormat formatear = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formatedDate = formatear.format(expDate);
        return authenticationMapper.userDetailsAndJwt2LoginResponseDTO(userDetails, jwt, formatedDate);
    }

    @Override
    public UserResponse meData(String userMail){

        Optional<UserEntity> user = userRepository.findByEmail(userMail);

        return UserResponse.builder()
                .firstName(user.get().getFirstName())
                .lastName(user.get().getLastName())
                .email(user.get().getEmail())
                .about(user.get().getPhoto())
                .build();
    }


}