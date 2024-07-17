package vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import vn.lenamLaptopstore.LaptopstoreLeNamSpringBootRestful.domain.Response.ResLoginDTO;

@Service
public class SecurityUtil {

    private final JwtEncoder jwtEncoder;

    public static final MacAlgorithm JWT_ALGORITHM = MacAlgorithm.HS512;

    @Value("${lenam.jwt.base64-secret}")
    private String jwtKey;

    @Value("${lenam.jwt.access-token-validity-in-seconds}")
    private long accessTokenExpiration;

    @Value("${lenam.jwt.refresh-token-validity-in-seconds}")
    private long refreshTokenExpiration;

    public SecurityUtil(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    // create token
    public String createAccessToken(Authentication authentication, ResLoginDTO resLoginDTO) {

        ResLoginDTO.UserInsideToken userInsideToken = new ResLoginDTO.UserInsideToken();
        userInsideToken.setId(resLoginDTO.getUser().getId());
        userInsideToken.setEmail(resLoginDTO.getUser().getEmail());
        userInsideToken.setFullName(resLoginDTO.getUser().getFullName());

        List<String> listAuthority = new ArrayList<String>();
        listAuthority.add("ROLE_USER_CREATE");
        listAuthority.add("ROLE_USER_UPDATE");

        Instant now = Instant.now();
        Instant validity = now.plus(this.accessTokenExpiration, ChronoUnit.SECONDS);

        // @formatter:off
        JwtClaimsSet claims = JwtClaimsSet.builder().issuedAt(now)
        .expiresAt(validity)
        .subject(authentication.getName())
        .claim("user", userInsideToken)
        .claim("permission", listAuthority)
        .build();

        JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader,claims)).getTokenValue();

    }

    public String createRefreshToken(String email, ResLoginDTO resLoginDTO) {

        ResLoginDTO.UserInsideToken userInsideToken = new ResLoginDTO.UserInsideToken();
        userInsideToken.setId(resLoginDTO.getUser().getId());
        userInsideToken.setEmail(resLoginDTO.getUser().getEmail());
        userInsideToken.setFullName(resLoginDTO.getUser().getFullName());


        Instant now = Instant.now();
        Instant validity = now.plus(this.refreshTokenExpiration, ChronoUnit.SECONDS);

        // @formatter:off
        JwtClaimsSet claims = JwtClaimsSet.builder().issuedAt(now)
        .expiresAt(validity)
        .subject(email)
        .claim("user", userInsideToken)
        .build();

        JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader,claims)).getTokenValue();

    }

}
