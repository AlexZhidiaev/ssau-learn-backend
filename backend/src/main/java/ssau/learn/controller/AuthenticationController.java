package ssau.learn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ssau.learn.dao.UserRepository;
import ssau.learn.dto.JwtDto;
import ssau.learn.dto.LoginDto;
import ssau.learn.dto.MessageDto;
import ssau.learn.dto.UserDto;
import ssau.learn.entity.Role;
import ssau.learn.entity.User;
import ssau.learn.security.JwtUtils;
import ssau.learn.service.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getLogin(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .findFirst().orElse(null);

        return ResponseEntity.ok(new JwtDto(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), role));
    }

    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        if (userRepository.existsByLoginOrEmail(userDto.getLogin(), userDto.getEmail()))
            return ResponseEntity.badRequest().body(new MessageDto("Invalid login or email"));

        User user = new User(userDto.getLogin(), userDto.getEmail(), encoder.encode(userDto.getPassword()), Role.ROLE_USER);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageDto("User registered successfully"));
    }
}