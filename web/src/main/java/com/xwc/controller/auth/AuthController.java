package com.xwc.controller.auth;

import com.xwc.commons.expception.SystemException;
import com.xwc.commons.model.JsonMessage;
import com.xwc.commons.utils.CodingUtils;
import com.xwc.commons.utils.StringUtils;
import com.xwc.controller.auth.dto.ClientLoginDto;
import com.xwc.controller.auth.dto.UserLoginDto;
import com.xwc.service.CacheService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.Base64Utils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 创建人：徐卫超
 * 创建时间：2019/3/18  15:17
 * 业务：
 * 功能：
 */
@RestController
@RequestMapping("/auth")
@Api(tags = "auth", description = "认证中心")
public class AuthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private CacheService cacheService;

    @GetMapping("/secret/{account}")
    @ApiOperation("获取秘钥(秘钥有效时间为5分钟且只能使用一次)")
    public JsonMessage<String> secret(@PathVariable("account") String account) throws HttpRequestMethodNotSupportedException {
        String secret = UUID.randomUUID().toString().replaceAll("-", "");
        cacheService.setValue(CacheService.AUT_HOME_SERCRT + account, String.valueOf(secret), 5 * 60L);
        return JsonMessage.succeed(secret);
    }

    @PostMapping("/user/login")
    @ApiOperation("用户认证(不推荐生产使用)")
    public JsonMessage<OAuth2AccessToken> userLogin(@RequestBody UserLoginDto login) throws HttpRequestMethodNotSupportedException {
        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));//此处不能为空
        UsernamePasswordAuthenticationToken clientAuthenticaiton = new UsernamePasswordAuthenticationToken("user", "user", grantedAuthorities);
        HashMap<String, String> param = new HashMap<>();
        param.put("username", login.getUsername());
        param.put("password", login.getPassword());
        param.put("grant_type", "password");
        param.put("scope", "user");
        param.put("clientId", "user");
        param.put("client_secret", "user");
        ResponseEntity<OAuth2AccessToken> accessToken = tokenEndpoint.getAccessToken(clientAuthenticaiton, param);
        saveTokenAndIp(accessToken.getBody().getValue());
        return JsonMessage.succeed(accessToken.getBody());
    }

    private void saveTokenAndIp(String token) {
        httpSession.setAttribute(OAuth2AccessToken.ACCESS_TOKEN, token);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object details = authentication.getDetails();
        if (details instanceof OAuth2AuthenticationDetails) {
            OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) details;
            String ip = oAuth2AuthenticationDetails.getRemoteAddress();
            cacheService.setValue(CacheService.AUT_HOME_IP + token, ip);
        } else if (details instanceof WebAuthenticationDetails) {
            WebAuthenticationDetails webAuthenticationDetails = (WebAuthenticationDetails) details;
            String ip = webAuthenticationDetails.getRemoteAddress();
            cacheService.setValue(CacheService.AUT_HOME_IP + token, ip);
        }
    }

    @PostMapping("/refresh/token")
    @ApiOperation("刷新token")
    public JsonMessage<OAuth2AccessToken> refreshToken(@RequestParam("refreshToken") String refreshToken) throws HttpRequestMethodNotSupportedException {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("grant_type", "refresh_token");
        parameters.put("refresh_token", refreshToken);
        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("client"));
        Authentication authentication = new UsernamePasswordAuthenticationToken("scm", "scm", grantedAuthorities);
        ResponseEntity<OAuth2AccessToken> responseEntity = tokenEndpoint.postAccessToken(authentication, parameters);
        this.saveTokenAndIp(responseEntity.getBody().getValue());
        return JsonMessage.succeed(responseEntity.getBody());
    }

    @PostMapping("/user/secret/login")
    @ApiOperation("带秘钥用户认证(推荐使用)")
    public JsonMessage<OAuth2AccessToken> userSecretLogin(@RequestBody UserLoginDto login) throws HttpRequestMethodNotSupportedException {
        String secret = cacheService.getValue(CacheService.AUT_HOME_SERCRT + login.getUsername());
        if (StringUtils.isBlank(secret)) return JsonMessage.failed("秘钥失效");
        try {
            String password = passwordDecode(login.getPassword(), secret);
            login.setPassword(password);
        } catch (IOException e) {
            throw new SystemException(e);
        }
        return userLogin(login);
    }

    @PostMapping("/client/login")
    @ApiOperation("客户端认证")
    public JsonMessage<Object> clientLogin(@RequestBody ClientLoginDto login) throws HttpRequestMethodNotSupportedException {
        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));//此处不能为空
        UsernamePasswordAuthenticationToken clientAuthenticaiton = new UsernamePasswordAuthenticationToken(login.getClientId(), login.getClientSecret(), grantedAuthorities);
        HashMap<String, String> param = new HashMap<>();
        param.put("grant_type", "client_credentials");
        param.put("scope", "client");
        param.put("clientId", login.getClientId());
        param.put("client_secret", login.getClientSecret());
        ResponseEntity<OAuth2AccessToken> accessToken = tokenEndpoint.getAccessToken(clientAuthenticaiton, param);
        return JsonMessage.succeed(accessToken.getBody());
    }


    /**
     * 1、把base64解析成字节
     * 2、对字节流和秘钥进行一定的规律异或得到密码字节流
     * 3、把字节流转换成密码
     */
    private String passwordDecode(String password, String secret) throws IOException {
        byte[] contenByte = Base64Utils.decodeFromString(password);
        byte[] secretByte = secret.getBytes("utf-8");
        for (int i = 0; i < contenByte.length; ++i) {
            int secretIndex = i % secretByte.length;
            byte b = secretByte[secretIndex];
            contenByte[i] = (byte) (contenByte[i] ^ b);
        }
        return new String(contenByte, "utf-8");
    }

    /**
     * 1、获取密码字节流，
     * 2、对字节流和秘钥进行一定的规律异或
     * 3、把结果转换成字符串
     * 3、把结果转换成base64
     */
    @SuppressWarnings("unused")
    private String passwordEncode(String password, String secret) throws UnsupportedEncodingException {
        byte[] contenByte = password.getBytes("utf-8");
        byte[] secretByte = secret.getBytes("utf-8");
        for (int i = 0; i < contenByte.length; ++i) {
            int secretIndex = i % secretByte.length;
            byte b = secretByte[secretIndex];
            contenByte[i] = (byte) (contenByte[i] ^ b);
        }
        return Base64Utils.encodeToString(contenByte);

    }


}
