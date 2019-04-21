package cn.hselfweb.cnjpbbs.utils;


import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Map;

public class TokenDecoder implements JwtDecoder {
    private static final String DECODING_ERROR_MESSAGE_TEMPLATE =
            "An error occurred while attempting to decode the Jwt: %s";

    private OAuth2TokenValidator<Jwt> jwtValidator = JwtValidators.createDefault();

    private Converter<Map<String, Object>, Map<String, Object>> claimSetConverter =
            MappedJwtClaimSetConverter.withDefaults(Collections.emptyMap());


    @Override
    public Jwt decode(String s) throws JwtException {
        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();
        Jwt jwt = jwtTokenUtils.Decoder(s);
        OAuth2TokenValidatorResult result = this.jwtValidator.validate(jwt);
        if (result.hasErrors()) {
            String description = result.getErrors().iterator().next().getDescription();
            throw new JwtValidationException(
                    String.format(DECODING_ERROR_MESSAGE_TEMPLATE, description),
                    result.getErrors());
        }
        return jwt;
    }

    public void setJwtValidator(OAuth2TokenValidator<Jwt> jwtValidator) {
        Assert.notNull(jwtValidator, "jwtValidator cannot be null");
        this.jwtValidator = jwtValidator;
    }
}
