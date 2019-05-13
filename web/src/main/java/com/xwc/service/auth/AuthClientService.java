package com.xwc.service.auth;

import com.xwc.commons.expception.BusinessException;
import com.xwc.entity.base.Client;
import com.xwc.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

/**
 * 创建人：徐卫超
 * 创建时间：2019/4/28  11:17
 * 业务：
 * 功能：
 */
@Component
public class AuthClientService implements ClientDetailsService {
    @Autowired
    private ClientMapper clientMapper;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Client client = clientMapper.selectKey(clientId);
        if (client == null) throw new BusinessException("客户端认证出错", clientId);
        IClient iClient = new IClient(client.getClientId(), null, client.getScope(), client.getGrantType(), null);
        return iClient;
    }
}
