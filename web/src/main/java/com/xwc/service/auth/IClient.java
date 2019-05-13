package com.xwc.service.auth;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

/**
 * 创建人：徐卫超
 * 创建时间：2019/4/28  11:17
 * 业务：
 * 功能：
 */
public class IClient extends BaseClientDetails {

    public IClient() {
    }

    public IClient(ClientDetails prototype) {
        super(prototype);
    }

    public IClient(String clientId, String resourceIds, String scopes, String grantTypes, String authorities) {
        super(clientId, resourceIds, scopes, grantTypes, authorities);
    }

    public IClient(String clientId, String resourceIds, String scopes, String grantTypes, String authorities, String redirectUris) {
        super(clientId, resourceIds, scopes, grantTypes, authorities, redirectUris);
    }
}
