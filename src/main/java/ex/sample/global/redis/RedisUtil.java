package ex.sample.global.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j(topic = "RedisUtil")
@Component
@RequiredArgsConstructor
public class RedisUtil {

    private final RefreshTokenRepository refreshTokenRepository;

    public boolean isUserLogout(String nickname) {
        boolean isLogin = refreshTokenRepository.existsById(nickname);
        return !isLogin;
    }

    public void setUserLogin(String nickname, String refreshToken) {
        RefreshToken refreshTokenItem = RefreshToken.builder()
            .nickname(nickname)
            .refreshToken(refreshToken)
            .build();

        refreshTokenRepository.save(refreshTokenItem);
    }

    public void setUserLogout(String nickname) {
        refreshTokenRepository.findById(nickname)
            .ifPresent(refreshTokenRepository::delete);
    }

    public String getUserRefreshToken(String nickname) {
        return refreshTokenRepository.findById(nickname)
            .map(RefreshToken::getRefreshToken)
            .orElse("");
    }
}
