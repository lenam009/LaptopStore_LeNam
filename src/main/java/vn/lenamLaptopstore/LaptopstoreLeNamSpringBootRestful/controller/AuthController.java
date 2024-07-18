package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.User;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Request.ReqLoginDTO;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Response.ResLoginDTO;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.service.UserService;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.util.SecurityUtil;
import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.util.annotation.ApiMessage;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

        @Value("${lenam.jwt.refresh-token-validity-in-seconds}")
        private long refreshTokenExpiration;

        private final AuthenticationManagerBuilder authenticationManagerBuilder;
        private final SecurityUtil securityUtil;
        private final UserService userService;
        private final PasswordEncoder passwordEncoder;

        public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder, SecurityUtil securityUtil,
                        UserService userService, PasswordEncoder passwordEncoder) {
                this.authenticationManagerBuilder = authenticationManagerBuilder;
                this.securityUtil = securityUtil;
                this.userService = userService;
                this.passwordEncoder = passwordEncoder;
        }

        @PostMapping("/login")
        @ApiMessage(value = "Login")
        public ResponseEntity<ResLoginDTO> postMethodName(@Valid @RequestBody ReqLoginDTO reqLoginDTO) {

                // Nạp input gồm username/password vào Security
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                reqLoginDTO.getEmail(), reqLoginDTO.getPassword());

                // xác thực người dùng và lấy info người dùng => cần viết hàm loadUserByUsername
                Authentication authentication = this.authenticationManagerBuilder.getObject()
                                .authenticate(authenticationToken);

                // Set info user into SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // Create ResLoginDTO
                ResLoginDTO resLoginDTO = new ResLoginDTO();
                Optional<User> currentUser = this.userService.getUserByEmail(reqLoginDTO.getEmail());

                ResLoginDTO.UserLogin userLogin = new ResLoginDTO.UserLogin(currentUser.get().getId(),
                                currentUser.get().getEmail(),
                                currentUser.get().getFullName());

                resLoginDTO.setUser(userLogin);

                // Create access token
                String access_token = this.securityUtil.createAccessToken(authentication, resLoginDTO);

                // Create refresh token and Update it for user
                String refresh_token = this.securityUtil.createRefreshToken(reqLoginDTO.getEmail(), resLoginDTO);

                // Set cookie
                ResponseCookie responseCookie = ResponseCookie.from("refresh_token", refresh_token)
                                // Only server get cookie
                                .httpOnly(true)
                                // Cookie only is used with https instead of http
                                .secure(true)
                                // Set cookie is send when match patch ('/' is all path)
                                .path("/")
                                .maxAge(refreshTokenExpiration)
                                .build();

                resLoginDTO.setAccessToken(access_token);

                return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, responseCookie.toString()).body(resLoginDTO);
        }

}
