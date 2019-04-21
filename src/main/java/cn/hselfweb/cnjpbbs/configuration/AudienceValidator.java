package cn.hselfweb.cnjpbbs.configuration;

import cn.hselfweb.cnjpbbs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.StringUtils;

import java.util.Map;


public class AudienceValidator implements OAuth2TokenValidator<Jwt> {
     @Autowired
    UserRepository userRepository;
    private OAuth2Error error = new OAuth2Error("invalid_token", "The required audience is missing", null);


    @Override
    public OAuth2TokenValidatorResult validate(Jwt jwt) {
        Map<String, Object> claims = jwt.getClaims();
        if (!StringUtils.isEmpty(claims.get("phone"))) {
            return OAuth2TokenValidatorResult.success();
        } else {
            return OAuth2TokenValidatorResult.failure(error);
        }
    }
}
