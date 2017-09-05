package core.shiro;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;

import org.springframework.stereotype.Service;


@Service("casRealm")
public class YCasRealm extends CasRealm {

    public YCasRealm() {
        super();
    }


    // 获取授权信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
}