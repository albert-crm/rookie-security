package com.rookie.bigdata.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @Classname AuthorizationServerConfiguration
 * @Description 授权服务器配置
 * @Author rookie
 * @Date 2023/3/10 17:20
 * @Version 1.0
 */
@Configuration
//用 @EnableAuthorizationServer 来配置 OAuth 2.0 授权服务
//AuthorizationServerEndpointsConfigurer: 用来配置授权以及令牌的访问端点和令牌服务;
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired

    private PasswordEncoder passwordEncoder;

    /**
     * 授权服务器 安全配置，实际上是 /oauth/token 端点
     *
     * @see AuthorizationServerConfigurer#configure(AuthorizationServerSecurityConfigurer)
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        // 允许客户端表单验证
        //   只对 /oauth/token 端点有响应
        //   该设定会开启一个在 BasicAuthenticationFilter 前执行的过滤器, 名为 ClientCredentialsTokenEndpointFilter. 这个过滤器
        //   会尝试从请求参数中获取 client_id 和 client_secret 以完成认证.
        security.allowFormAuthenticationForClients();

//        BasicAuthenticationFilter

        security
                // For endpoint /oauth/check_token, 用于资源服务访问的令牌解析端点
                // 只有内部应用才能访问这个端点
                .checkTokenAccess("hasAuthority('INNER_CLIENT')")
        ;
    }

    /**
     * @param clients 用来配置客户端详情服务, 客户端详情信息在这里初始化, 你能够把客户端详情硬编码在这里或是通过数据库来存取.
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()

                // 配置一个客户端详情 (ClientDetails)
                .withClient("client-id-rookie").secret(passwordEncoder.encode("client-secret-rookie"))
                // 在客户端授权的时候, 可以设置这个客户端可以访问哪些资源服务 (如果没有设置就是对所有资源都有访问权限)
                .resourceIds("resource-server").scopes("access")
                // 配置 ClientDetails 的 redirect_uri
                // 如果配置了一个 redirect_uri, 并且 URL 上没有参数 redirect_uri, 则会直接重定向至这个唯一的地址;
                // 如果配置了多个 redirect_uri, 则会用请求地址用的 redirect_uri 与下列可选 uri 比较, 没有匹配值就会抛出异常.
                // ref: DefaultRedirectResolver#resolveRedirect
                .redirectUris("/authorization-server/access"/*, "/authorization-server/access-a", "/authorization-server/access-b"*/)
                // 授权码模式
                .authorizedGrantTypes("authorization_code", "refresh_token")

                .and()

                // 内部客户端 (Resource Server in this case)
                // -----------------------------------------------------------------------------------------------------
                .withClient("resource-server-id").secret(passwordEncoder.encode("resource-server-secret"))
                .authorities("INNER_CLIENT")
        ;
    }

    /**
     * 授权服务器的非安全特性配置. 如 token 存储, token 自定义. 默认情况不需要做任何改动, 除非是密码模式 (同时需要提供
     * {@link org.springframework.security.authentication.AuthenticationManager}).<br>
     * Details: 默认情况 tokenStore 为 InMemoryTokenStore (ref AuthorizationServerEndpointsConfigurer#tokenStore)
     *
     * @see AuthorizationServerConfigurer#configure(AuthorizationServerEndpointsConfigurer)
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
    }


}
