package com.zsl.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zsl.common.exception.AuthenticationFailedException;
import com.zsl.core.cache.TokenServer;
import com.zsl.entity.User;

import java.util.Objects;
import java.util.UUID;

/**
 * @Author zsl
 * @Date 2021/12/30 15:26
 */
public class TokenUtil {

    //    @Value("${token.secret}")
    public static String secret = "4sd65fb1456vb456fsg1nbdf1g32bf1gb";

    //    @Value("${token.issuer}")
    public static String issuer = "zsl";

    private static TokenServer tokenServer;

    /**
     * 创建用户token
     *
     */
    public static String createToken(User user) {
        /*
        // 使用 uuid 作为 键值
        String uuid = getUUID();
        String token = getToken(uuid);
        saveToken(uuid, user);*/
        // 变更通过用户邮箱作为唯一key
        String token = getToken(user.getUserEmail());
        saveToken(user.getUserEmail(), user);
        return token;
    }

    /**
     * 获取唯一凭证uuid
     */
    private static String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成token
     */
    private static String getToken(String uuid) {
        String token = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            token = JWT.create()
                    .withIssuer(issuer)
                    .withClaim("uuid", uuid)
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        return token;
    }

    /**
     * 将token保存在缓存中
     */
    private static void saveToken(String uuid, User user) {
        if (Objects.isNull(tokenServer)) {
            tokenServer = ApplicationContextUtils.getBeanByClass(TokenServer.class);
        }
        tokenServer.set(uuid, JsonUtils.obj2Str(user));
    }

    /**
     * 获取凭证
     */
    public static User getAuthority(String token) {
        DecodedJWT decodedJWT = verityToken(token);
        String uuid = decodedJWT.getClaim("uuid").asString();
        return getUser(uuid);
    }

    /**
     * 解析token
     */
    private static DecodedJWT verityToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build(); //Reusable verifier instance
            return verifier.verify(token);
        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
            throw new AuthenticationFailedException("无效token");
        }
    }

    /**
     * 缓存中获取用户
     */
    private static User getUser(String uuid) {
        if (Objects.isNull(tokenServer)) {
            tokenServer = ApplicationContextUtils.getBeanByClass(TokenServer.class);
        }
        String userStr = tokenServer.get(uuid);
        User user = JsonUtils.str2Obj(userStr, User.class);
        if (user == null) {
            throw new AuthenticationFailedException("用户凭证已过期,请重新登录");
        }
        return user;
    }

}
