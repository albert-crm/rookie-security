package com.rookie.bigdata.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 * @Classname ResourceServerConfiguration
 * @Description 资源服务配置
 * @Author rookie
 * @Date 2023/3/13 11:09
 * @Version 1.0
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "resource-server";

    /**
     * 为资源服务器配置特定属性, 如 resource-id.
     * Details: 查看 {@link ResourceServerSecurityConfigurer} 的源代码可以知道, 默认情况下,
     * 已经为 {@link ResourceServerSecurityConfigurer} 注入了 {@link OAuth2WebSecurityExpressionHandler}
     *
     * @see org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer#configure(ResourceServerSecurityConfigurer)
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(true).tokenServices(remoteTokenServices());
    }

    /**
     * Description: 配置资源的访问规则. 默认情况下, 除了 /oauth/** 之外的所有资源都被保护<br>
     * Details: 默认情况下, {@link OAuth2WebSecurityExpressionHandler} 已经被注入, 形如 {@code http.authorizeRequests().expressionHandler(new OAuth2WebSecurityExpressionHandler())}
     *
     * @see org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer#configure(HttpSecurity)
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                //session管理器
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers("/resource-server/access").access("#oauth2.hasScope('access')");
    }

    // ~ bean
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 查询 /check_token 端点获取 access-token 的内容
     */
    private RemoteTokenServices remoteTokenServices() {
        //内部使用RestTemplate进行系统之间的调用
        final RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:18900/authorization-server/oauth/check_token");
        remoteTokenServices.setClientId("resource-server-id");
        remoteTokenServices.setClientSecret("resource-server-secret");
        return remoteTokenServices;
    }
}