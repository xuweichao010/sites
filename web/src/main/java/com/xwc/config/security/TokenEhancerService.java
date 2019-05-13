package com.xwc.config.security;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

/**
 * 创建人：徐卫超
 * 创建时间：2019/3/22  15:59
 * 业务：
 * 功能：
 */
public class TokenEhancerService implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        return accessToken;
    }
}
