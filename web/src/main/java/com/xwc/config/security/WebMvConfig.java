package com.xwc.config.security;

import com.xwc.commons.utils.StringUtils;
import com.xwc.config.security.anno.Privilege;
import com.xwc.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 创建人：徐卫超
 * 创建时间：2019/3/19  10:01
 * 业务：配置spring mvc拦截器
 * 功能：用于自定义拦截
 */
@Configuration
public class WebMvConfig implements WebMvcConfigurer {

    @Autowired
    private CacheService cacheService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PrivilegeInterceptor());
    }

    public class PrivilegeInterceptor implements HandlerInterceptor {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                //校验权限
                Privilege anno = handlerMethod.getMethodAnnotation(Privilege.class);
                if (anno == null) return true;
                if (!anno.ignore()) {
                    if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority(anno.value()))) {
                        throw new AccessDeniedException("访问受限");
                    }
                }
            }
            return true;
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            //TODO  业务逻辑处理之后执行
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            //TODO 出现异常被调用
        }

        private String getClientIp(HttpServletRequest request) {
            //X-Forwarded-For，不区分大小写
            String possibleIpStr = request.getHeader("X-Forwarded-For");
            String remoteIp = request.getRemoteAddr();
            String clientIp;
            if (StringUtils.isNotBlank(possibleIpStr) && !"unknown".equalsIgnoreCase(possibleIpStr)) {
                //可能经过好几个转发流程，第一个是用户的真实ip，后面的是转发服务器的ip
                clientIp = possibleIpStr.split(",")[0].trim();
            } else {
                //如果转发头ip为空，说明是直接访问的，没有经过转发
                clientIp = remoteIp;
            }
            return clientIp;
        }
    }
}
