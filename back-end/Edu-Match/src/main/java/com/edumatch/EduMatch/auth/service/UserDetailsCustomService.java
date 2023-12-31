package com.edumatch.EduMatch.auth.service;

import com.edumatch.EduMatch.models.UserEntity;
import com.edumatch.EduMatch.models.mappers.AuthenticationMapper;
import com.edumatch.EduMatch.models.request.RegisterRequest;
import com.edumatch.EduMatch.models.response.RegisterResponse;
import com.edumatch.EduMatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthenticationMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtTokenUtil;


    @Autowired
    public UserDetailsCustomService(
            @Lazy AuthenticationMapper userMapper,
            @Lazy UserRepository userRepository,
            @Lazy PasswordEncoder passwordEncoder,
            @Lazy JwtUtil jwtTokenUtil
    ) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;

    }


    public RegisterResponse signupUser(RegisterRequest userToCreate) {
        var matchingUser = userRepository.findByEmail(userToCreate.getEmail());
        if (matchingUser.isPresent()) throw new IllegalArgumentException("El usuario ya existe");

        userToCreate.setPassword(passwordEncoder.encode(userToCreate.getPassword()));
        UserEntity newUser = userMapper.registerRequestDTO2Entity(userToCreate);
        newUser = userRepository.save(newUser);

        UserDetails newUserDetail = new User(
                newUser.getEmail(),
                newUser.getPassword(),
                List.of()
        );

        String jwt = jwtTokenUtil.generateToken(newUserDetail);
        return userMapper.entity2RegisterResponseDTO(newUser, jwt);

    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var foundUser = userRepository.findByEmail(username)
                .orElseThrow();

        // Authorities List
        Collection<GrantedAuthority> authorities = foundUser.getRoles().stream()
                .map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getName()))
                .collect(Collectors.toList());

        // === Set Spring Security USER en el Context ===
        return new User(
                foundUser.getEmail(),
                foundUser.getPassword(),
                authorities
        );

    }


}