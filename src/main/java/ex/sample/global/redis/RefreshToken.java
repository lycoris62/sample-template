package ex.sample.global.redis;

import ex.sample.global.jwt.JwtUtil;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@RedisHash(value = "refreshToken", timeToLive = JwtUtil.REFRESH_TOKEN_TTL_SECONDS)
public class RefreshToken {

    @Id
    private String nickname;

    private String refreshToken;
}
