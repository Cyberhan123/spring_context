package cn.hselfweb.cnjpbbs.utils;


import cn.hselfweb.cnjpbbs.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class JwtTokenUtils {
    public final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String secret = "thisIsCyberhanTheWebsiteIsHselfwebCnuuuuccc";
    private final String subject = "cyberhan";
    private final String ID = "4c5f59c6-32e3-4d57-9690-f0a0f0476309";
    private Instant Issued = Instant.ofEpochSecond(1554252375);
    private Instant Expiration = Instant.ofEpochSecond(1554255975);
    private Map<String, Object> claims = new HashMap<String, Object>();
    private Map<String, Object> header = new HashMap<>();

    public JwtTokenUtils() {
        header.put("typ", "jwt");
        header.put("scope", "user");
        header.put("alg", "HS256");
    }

    public JwtTokenUtils(@NotNull User user) {
        claims.put("name", user.getName());
        claims.put("phone", user.getPhone());
        claims.put("email", user.getEmail());
        claims.put("scope", "user");
        claims.put("Issued", Issued);
        claims.put("Expiration", Expiration);
        header.put("typ", "jwt");
        header.put("scope", "user");
        header.put("alg", "HS256");
    }

    public Jwt getJwt() {
        Jwt jwt = new Jwt(getUserToken(), Issued, Expiration, header, claims);
        return jwt;
    }

    public String getUserToken() {
        try {
            String s = Jwts.builder()
                    .setSubject(subject)
                    .setId(ID)
                    .setIssuedAt(Date.from(Issued))
                    .setExpiration(Date.from(Expiration))
                    .setClaims(claims)
                    .signWith(SignatureAlgorithm.HS256, secret.getBytes("UTF-8"))
                    .compact();
            return s;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Jwt Decoder(String Token) {
        Claims claims;
        Map<String, Object> nClaim = new HashMap<>();

        try {
            claims = Jwts.parser()
                    .setSigningKey(secret.getBytes("UTF-8")).parseClaimsJws(Token).getBody();
            nClaim.put("name", claims.get("name"));
            nClaim.put("phone", claims.get("phone"));
            nClaim.put("email", claims.get("email"));
            nClaim.put("scope", claims.get("scope"));
            nClaim.put("Issued",  claims.get("Issued"));
            nClaim.put("Expiration",claims.get("Expiration"));
            System.out.println(Date.from(Issued));
            System.out.println(Date.from(Expiration));
            Jwt jwt = new Jwt(Token, Issued,Expiration, header, nClaim);
            return jwt;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean checkToken(String Token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret.getBytes("UTF-8"))
                    .parseClaimsJws(Token);
            return true;
        } catch (SignatureException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUserFromToken(String Token) {
        Claims claims;
        User user = new User();
        try {
            claims = Jwts.parser().setSigningKey(secret.getBytes("UTF-8")).parseClaimsJws(Token).getBody();
            user.setName((String) claims.get("name"));
            user.setPhone((String) claims.get("phone"));
            user.setEmail((String) claims.get("email"));

        } catch (Exception e) {
            claims = null;
        }
        return user;
    }


//    public static void main(String[] args) {
//        User user = new User();
//        user.setName("123");
//        user.setEmail("123");
//        user.setPhone("123");
//        JwtTokenUtils userToken = new JwtTokenUtils();
//        Jwt s = userToken.Decoder("eyJhbGciOiJIUzI1NiJ9.eyJwaG9uZSI6IjExMSIsInNjb3BlIjoidXNlciIsIm5hbWUiOiJkc2Fkc2RhIiwiZW1haWwiOiJkYXNkYXNkc2Fkc2FkcyJ9.f8COoEpOtVwKLYqXaWhFAuQMhaSfnSVBjIxr_tB7CME");
//        System.out.println(s);
//        //  System.out.println(s);
//        //  user= userToken.getUserFromToken(s);
//        //   System.out.println(is);
//    }

}
