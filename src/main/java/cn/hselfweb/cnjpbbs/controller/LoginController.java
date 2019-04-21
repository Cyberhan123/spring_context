package cn.hselfweb.cnjpbbs.controller;

import cn.hselfweb.cnjpbbs.entity.User;
import cn.hselfweb.cnjpbbs.repository.UserRepository;
import cn.hselfweb.cnjpbbs.utils.JwtTokenUtils;
import cn.hselfweb.cnjpbbs.utils.StringUtils;
import com.nimbusds.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    private UserRepository userRepository;


    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> getToken(@RequestParam("EPN") String EPN, @RequestParam("password") String password) {
        User user;
        JwtTokenUtils jwtTokenUtils;
        String token;
        Map<String,Object> respone=new HashMap<>();
        if (StringUtils.isEmail(EPN)) {
            user = userRepository.findUserByEmailAndPassword(EPN, password);
            jwtTokenUtils = new JwtTokenUtils(user);
            token = jwtTokenUtils.getUserToken();
        } else if (StringUtils.isPhone(EPN)) {
            user = userRepository.findUserByPhoneAndPassword(EPN, password);
            jwtTokenUtils = new JwtTokenUtils(user);
            token = jwtTokenUtils.getUserToken();
        } else {
            user = userRepository.findUserByNameAndAndPassword(EPN, password);
            jwtTokenUtils = new JwtTokenUtils(user);
            token = jwtTokenUtils.getUserToken();
        }
        respone.put("access_token",token);
        respone.put("token_type","bearer");
        respone.put("expires_in",jwtTokenUtils.getExpiration());
        respone.put("jti",jwtTokenUtils.getID());
        return respone;
    }
}
