package com.xtxk.core.encrypt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xtxk.core.json.JsonUtil;
import com.xtxk.core.util.LogUtil;
import com.xtxk.core.util.U;
import com.xtxk.core.vo.Payload;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.xtxk.core.encrypt.Encrypt.JWT_SECRET;

/**
 * @Description jwt工具类
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/4/21
 */
public abstract class JwtUtil {
    //密钥
    //10s有效期
    public static final int SECOND_10 = 1000 * 10;
    //30s有效期
    public static final int SECOND_30 = 1000 * 30;
    //1分钟有效期
    public static final int MINUTE = SECOND_30 * 2;
    //30分钟
    public static final int MINUTE_30 = MINUTE * 30;
    //1小时
    public static final int HOUR = MINUTE * 60;
    //12小时
    public static final int HOUR_12 = HOUR * 12;
    //一天
    public static final int DAY = HOUR * 24;
    //一周
    public static final int WEEK = DAY * 7;
    //一个月
    public static final int MONTH = DAY * 30;
    public static final String DEFAULT_UID = U.uuid();
    public static final String DEFAULT_ISSUER = "admin";
    public static final String DEFAULT_SUBJECT = "JWT_TOKEN";
    public static final String DEFAULT_IP = "127.0.0.1";
    public static final String LOGIN_IP = "loginIp";
    private static final Base64 decoder = new Base64(true);

    /**
     * 获取key
     */
    private static SecretKey generalKey() {
        LogUtil.LOG.info("========加密规则：JWT_SECRET========="+JWT_SECRET);
        byte[] encodeKey = JWT_SECRET.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec keySpec = new SecretKeySpec(encodeKey, 0, encodeKey.length, SignatureAlgorithm.HS256.getValue());
        return keySpec;
    }

    /**
     * 创建TOKEN
     *
     * @param id 唯一标识符(用户ID)
     * @param issuer 签发者
     * @param subject 主题
     * @param ttlMillis 超时时间
     */
    public static String createToken(String id, String issuer, String subject, String loginIp, long ttlMillis) {
        long nowMillis = System.currentTimeMillis();
        Map<String,Object> params = new HashMap<>();
        params.put(LOGIN_IP,U.isBlank(loginIp)?DEFAULT_IP:loginIp);
        JwtBuilder builder = Jwts.builder()
                .setClaims(params)
                .setId(U.isBlank(id) ? DEFAULT_UID : id)
                .setIssuedAt(new Date(nowMillis))
                .setIssuer(U.isBlank(issuer) ? DEFAULT_ISSUER : issuer)
                .setSubject(U.isBlank(subject)?DEFAULT_SUBJECT:subject)
                .signWith(SignatureAlgorithm.HS256, generalKey());
        //设置失效事件
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    public static String createToken(String id,String issuser,String loginIp){
      //  return createToken(id,issuser,DEFAULT_SUBJECT,loginIp,MINUTE*5);
        return createToken(id,issuser,DEFAULT_SUBJECT,loginIp,DAY);

    }

    public static Claims parseJWT(String jwt) {
        SecretKey key = generalKey();
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
        return claims;
    }

    /**
     * 验证TOKEN
     *
     * @param token
     */
    public static Payload verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).acceptLeeway(60).build();
            DecodedJWT jwt = verifier.verify(token);
            String payloadStr = new String(decoder.decode(jwt.getPayload()), StandardCharsets.UTF_8);
            Payload payload = JsonUtil.fromJson(payloadStr, Payload.class);
            return payload;
        } catch (Exception e) {
            LogUtil.LOG.error(e.getMessage(), e);
            throw e;
        }
    }

    public static void main(String[] args) {
        String jwt = createToken("10", "admin", "test","127.0.0.1", JwtUtil.DAY);
        System.out.println(jwt);
        Payload isTrue = verify(jwt);
        System.out.println(isTrue);
    }
}
