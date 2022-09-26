package com.github.org.todaybread.todaybread.auth.application.token;

import com.github.org.todaybread.todaybread.auth.domain.auth.Auth;
import com.github.org.todaybread.todaybread.auth.domain.role.Role;
import com.github.org.todaybread.todaybread.auth.domain.token.Token;
import com.github.org.todaybread.todaybread.auth.exceptions.NotFoundRoleException;
import com.github.org.todaybread.todaybread.auth.infra.persistence.auth.AuthRepositoryImpl;
import com.github.org.todaybread.todaybread.auth.infra.persistence.token.TokenRepositoryImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
public class TokenServiceImpl implements TokenService {

    private final Key key;
    private final Long accessTokenExpiredDate;
    private final Long refreshTokenExpiredDate;
    private final TokenRepositoryImpl tokenRepository;
    private final AuthRepositoryImpl authRepository;

    public TokenServiceImpl(
        @Value("${jwt.secret}") String secret,
        @Value("${jwt.expired.access}") Long accessTokenExpiredDate,
        @Value("${jwt.expired.refresh}") Long refreshTokenExpiredDate,
        @Autowired TokenRepositoryImpl tokenRepository,
        @Autowired AuthRepositoryImpl authRepository
    ) {
        byte[] bytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(bytes);
        this.accessTokenExpiredDate = accessTokenExpiredDate;
        this.refreshTokenExpiredDate = refreshTokenExpiredDate;
        this.tokenRepository = tokenRepository;
        this.authRepository = authRepository;
    }

    @Override
    @Transactional
    public Token create(Auth auth) {
        Long currentTime = new Date().getTime();

        String accessToken = Jwts.builder()
            .setClaims(setClaims(auth))
            .setSubject(auth.getMember().getId().toString())
            .setExpiration(new Date(currentTime + accessTokenExpiredDate))
            .signWith(key, SignatureAlgorithm.HS512)
            .compact();

        String refreshToken = Jwts.builder()
            .setClaims(setClaims(auth))
            .setSubject(auth.getMember().getId().toString())
            .setExpiration(new Date(currentTime + refreshTokenExpiredDate))
            .signWith(key, SignatureAlgorithm.HS512)
            .compact();

        return tokenRepository.save(
            auth.getId().toString(),
            Token.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build(),
            refreshTokenExpiredDate
        );
    }

    @Override
    @Transactional
    public Boolean remove(Auth auth) {
        tokenRepository.delete(auth.getId().toString());
        return true;
    }

    @Override
    public Boolean validation(String token) {
        Claims claims = getClaims(token);

        Auth auth = authRepository.getByMemberId(claims.get("memberId").toString())
            .orElse(null);

        return claims.getExpiration().after(new Date())
            && auth != null
            && tokenRepository.getByKey(auth.getId().toString()) != null;
    }

    @Override
    public String parse(String token) {
        return getClaims(token).get("memberId", String.class);
    }

    @Override
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        if (claims.get("roles") == null) {
            throw new NotFoundRoleException();
        }

        Collection<? extends GrantedAuthority> authorities =
            Arrays.stream(
                    claims.get("roles").toString()
                        .replaceAll("^\\[", "")
                        .replaceAll("]$", "")
                        .split(", ")
                ).map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(
            new User(claims.getSubject(), "", authorities),
            "",
            authorities
        );
    }

    private Claims setClaims(Auth auth) {
        Claims claims = Jwts.claims();
        claims.put("memberId", auth.getMember().getId());
        claims.put(
            "roles",
            auth.getRoles().stream().map(Role::getType).collect(Collectors.toList())
        );
        return claims;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
