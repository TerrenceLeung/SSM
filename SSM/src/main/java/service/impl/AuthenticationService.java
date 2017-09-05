package service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

/**
 * Created by YannLeung on 2017/8/10.
 */
@Service("authenticationService")
public class AuthenticationService {

    public void logout(){
        Subject subject = SecurityUtils.getSubject();
        if(subject != null && subject.isAuthenticated()){
            subject.logout();
            System.out.print("本系统已执行logout");
        }
    }

}
