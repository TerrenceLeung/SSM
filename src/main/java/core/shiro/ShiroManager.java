package core.shiro;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import utils.SpringUtil;

/**
 * Created by YannLeung on 2017/8/10.
 */
@Configuration
public class ShiroManager {

    private Boolean useCas;

    private String casLogoutUrl;

    private String casLoginUrl;

    private String casUrl;

    private String casErrorUrl;

    private String shiroLogoutUrl;

    private String shiroLoginUrl;

    private String appCas;

    private String appIndex;

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setCacheManager(SpringUtil.getBean("cacheManager"));
        securityManager.setRealm(SpringUtil.getBean("casRealm"));
        securityManager.setSubjectFactory(SpringUtil.getBean("casSubjectFactory"));
        securityManager.setSessionManager(SpringUtil.getBean("sessionManager"));
        return securityManager;
    }

    public Boolean getUseCas() {
        return useCas;
    }

    @Value("#{propertiesReader['usecas']}")
    public void setUseCas(Boolean useCas) {
        this.useCas = useCas;
    }

    public String getCasLogoutUrl() {
        return casLogoutUrl;
    }

    @Value("#{propertiesReader['cas.logout.url']}")
    public void setCasLogoutUrl(String casLogoutUrl) {
        this.casLogoutUrl = casLogoutUrl;
    }

    public String getCasLoginUrl() {
        return casLoginUrl;
    }

    @Value("#{propertiesReader['cas.login.url']}")
    public void setCasLoginUrl(String casLoginUrl) {
        this.casLoginUrl = casLoginUrl;
    }

    public String getCasUrl() {
        return casUrl;
    }

    @Value("#{propertiesReader['cas.server.url']}")
    public void setCasUrl(String casUrl) {
        this.casUrl = casUrl;
    }

    public String getCasErrorUrl() {
        return casErrorUrl;
    }

    @Value("#{propertiesReader['cas.error.url']}")
    public void setCasErrorUrl(String casErrorUrl) {
        this.casErrorUrl = casErrorUrl;
    }

    public String getShiroLogoutUrl() {
        return shiroLogoutUrl;
    }

    @Value("#{propertiesReader['shiro.logout.url']}")
    public void setShiroLogoutUrl(String shiroLogoutUrl) {
        this.shiroLogoutUrl = shiroLogoutUrl;
    }

    public String getShiroLoginUrl() {
        return shiroLoginUrl;
    }

    @Value("#{propertiesReader['shiro.login.url']}")
    public void setShiroLoginUrl(String shiroLoginUrl) {
        this.shiroLoginUrl = shiroLoginUrl;
    }

    public String getAppCas() {
        return appCas;
    }

    @Value("#{propertiesReader['app.cas']}")
    public void setAppCas(String appCas) {
        this.appCas = appCas;
    }

    public String getAppIndex() {
        return appIndex;
    }

    @Value("#{propertiesReader['app.index']}")
    public void setAppIndex(String appIndex) {
        this.appIndex = appIndex;
    }
}
