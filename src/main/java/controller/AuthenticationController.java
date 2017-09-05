package controller;

import core.shiro.ShiroManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.impl.AuthenticationService;
import utils.SpringUtil;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by YannLeung on 2017/8/10.
 */

@Controller
@RequestMapping(value = "authentication")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    public void login(){

    }


    @RequestMapping(value = "logout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = GET)
    public String logout(){

        //登出,清空session等
        authenticationService.logout();

        //根据是否使用cas跳转到登录地址
        ShiroManager shiroManager = SpringUtil.getBean(ShiroManager.class);
        Boolean useCas = shiroManager.getUseCas();
        String loginUrl = useCas?shiroManager.getCasLogoutUrl():shiroManager.getShiroLoginUrl();
        return "redirect:"+loginUrl;
//        return "redirect:/"+loginUrl;


    }
}
