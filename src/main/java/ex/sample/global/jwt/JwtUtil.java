package ex.sample.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import java.time.Duration;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j(topic = "JwtUtil")
@Component
public class JwtUtil {

    public static final String ACCESS_TOKEN_HEADER = "Authorization"; // Access Token Header KEY 값
    public static final String REFRESH_TOKEN_HEADER = "Refresh-Token"; // Refresh Token Header KEY 값
    public static final String AUTHORIZATION_KEY = "auth"; // JWT 내의 사용자 권한 값의 KEY
    public static final String BEARER_PREFIX = "Bearer "; // Token 식별자
    public static final int ACCESS_TOKEN_TTL_SECONDS = 60 * 60; // Access token 만료시간 60분, 초 단위
    public static final int REFRESH_TOKEN_TTL_SECONDS = 14 * 24 * 60 * 60; // Refresh token 만료시간 2주, 초 단위

    @Value("${jwt.secret.key}")
    private String JWT_SECRET_KEY; // JWT 생성 및 검증에 사용할 비밀키 (Base 64로 Encode)
    private SecretKey secretKey; // JWT 생성 및 검증에 사용할 Key 인터페이스를 구현한 객체
    private JwtParser jwtParser; // JWT 검증을 위한 JwtParser 객체 (유효성 검사 및 Payload 추출)

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(JWT_SECRET_KEY); // secretKey -> Base64 디코딩하여 byte 배열로 저장
        secretKey = Keys.hmacShaKeyFor(bytes);
        jwtParser = Jwts.parser().verifyWith(secretKey).build();
    }

    /**
     * Access Token 생성
     */
    public String createAccessToken(String nickname, String role) {
        return createToken(nickname, role, Duration.ofSeconds(ACCESS_TOKEN_TTL_SECONDS).toMillis());
    }

    /**
     * Refresh Token 생성
     */
    public String createRefreshToken(String nickname, String role) {
        return createToken(nickname, role, Duration.ofSeconds(REFRESH_TOKEN_TTL_SECONDS).toMillis());
    }

    private String createToken(String nickname, String role, long expiration) {
        Date now = new Date();

        return Jwts.builder()
            .subject(nickname)
            .claim(AUTHORIZATION_KEY, role)
            .expiration(new Date(now.getTime() + expiration))
            .issuedAt(now)
            .signWith(secretKey, SIG.HS512)
            .compact();
    }

    /**
     * Refresh token 담은 쿠키 생성
     */
    public Cookie createRefreshTokenCookie(String cookieValue) {
        Cookie cookie = new Cookie(JwtUtil.REFRESH_TOKEN_HEADER, cookieValue);

        cookie.setMaxAge(JwtUtil.REFRESH_TOKEN_TTL_SECONDS);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        return cookie;
    }

    /**
     * Bearer prefix 없는 토큰 가져오기
     */
    public String getTokenWithoutBearer(String token) {
        if (hasTokenBearerPrefix(token)) {
            return token.substring(7);
        }

        return token;
    }

    /**
     * 토큰에 Bearer prefix 추가하기
     */
    public String setTokenWithBearer(String token) {
        if (!hasTokenBearerPrefix(token)) {
            return BEARER_PREFIX + token;
        }

        return token;
    }

    private boolean hasTokenBearerPrefix(String token) {
        return StringUtils.hasText(token) && token.startsWith(BEARER_PREFIX);
    }

    /**
     * 토큰에서 nickname 가져오기
     */
    public String getNicknameFromToken(String token) {
        return getUserInfoFromToken(token).getSubject();
    }

    /**
     * 토큰에서 남은 시간을 초 단위로 가져오기
     */
    public int getTtlInSecondsFromToken(String token) {
        Date expiration = getUserInfoFromToken(token).getExpiration();

        int expirationTimeInSeconds = (int) (expiration.getTime() / 1000);
        int nowTimeInSeconds = (int) (new Date().getTime() / 1000);

        return expirationTimeInSeconds - nowTimeInSeconds;
    }

    /**
     * 토큰에서 role 가져오기
     */
    public String getRoleFromToken(String token) {
        return (String) getUserInfoFromToken(token).get(AUTHORIZATION_KEY);
    }

    /**
     * 토큰에서 claims 가져오기
     */
    private Claims getUserInfoFromToken(String token) {
        return jwtParser.parseSignedClaims(token).getPayload();
    }

    /**
     * 쿠키에서 Refresh token 가져오기
     */
    public String getRefreshTokenFromCookies(Cookie[] cookies) {
        return getTokenFromCookies(cookies, REFRESH_TOKEN_HEADER);
    }

    private String getTokenFromCookies(Cookie[] cookies, String cookieName) {
        if (cookies == null) {
            return null;
        }

        return Arrays.stream(cookies)
            .filter(cookie -> cookie.getName().equals(cookieName))
            .map(Cookie::getValue)
            .findAny()
            .orElse(null);
    }

    /**
     * 토큰 검증
     */
    public JwtStatus validateToken(String token) {

        try {
            jwtParser.parseSignedClaims(token);
            return JwtStatus.VALID;
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token, 만료된 JWT token 입니다.");
            return JwtStatus.EXPIRED;
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (JwtException e) {
            log.error("Invalid JWT, 유효하지 않는 JWT 서명 입니다.");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }

        return JwtStatus.INVALID;
    }
}
