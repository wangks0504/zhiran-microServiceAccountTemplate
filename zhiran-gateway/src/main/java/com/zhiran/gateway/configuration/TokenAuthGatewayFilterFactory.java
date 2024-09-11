package com.zhiran.gateway.configuration;

import com.zhiran.common.constant.ZhiranConstants;
import com.zhiran.common.entity.SessionContext;
import com.zhiran.common.helper.RedisSessionHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class TokenAuthGatewayFilterFactory extends AbstractGatewayFilterFactory<TokenAuthGatewayFilterFactory.PathConfig> {
    @Autowired
    private RedisSessionHelper redisSessionHelper;
    //把配置类交到父类中
    TokenAuthGatewayFilterFactory(){
        super(PathConfig.class);
    }
    //把path放在第一位
    @Override
    public List<String> shortcutFieldOrder() {
        return new ArrayList<String>() {{
            add("path");
        }};
    }
    @Override
    public GatewayFilter apply(PathConfig config) {
        return ((exchange, chain) -> {
            //获取请求和回应
            //注意这里选择响应式编程不是阻塞式的ServerHttpRequest
            ServerHttpRequest request = exchange.getRequest();
            RequestPath requestPath = request.getPath();
            String sessionToken = getSessionToken(request);

            ServerHttpResponse response = exchange.getResponse();
            //判断是否是那几个不用认证的请求
            if(verifyPassPath(requestPath,config)){
                return chain.filter(exchange);
            }
            //判断sessionToken是否存在如果不存在则代表未认证
            if(StringUtils.isEmpty(sessionToken)){
                 response.setStatusCode(HttpStatus.UNAUTHORIZED);
                 return response.setComplete();
            }
            SessionContext sessionContext = redisSessionHelper.getSession(sessionToken);
            boolean isisValid = redisSessionHelper.isValid(sessionContext);
            if(!isisValid){
                //响应未认证
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
            String accountID  = sessionContext.getAccountID();
            exchange.getRequest().mutate().headers(httpHeaders->{
                httpHeaders.add(ZhiranConstants.HEADER_ACCOUNT_KEY,accountID);
            });
            //认证通过放行
            return chain.filter(exchange);

        });
    }
    /**
     * 判断请求是否符合过滤器要求
     * @param requestPath
     * @param pathConfig
     * @return true or false
     */
    public static boolean verifyPassPath(RequestPath requestPath,PathConfig pathConfig){
        String[] passPathArray = pathConfig.getPathArray();
        if(null == passPathArray){
            return false;
        }
        if(passPathArray.length == 0){
            return false;
        }
        List<String> filterList = Arrays.stream(passPathArray).filter(path->{
            if(path.equals(requestPath.toString())){
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        if(!filterList.isEmpty()){
            return true;
        }
        return false;
    }
    /**
     *对Path处理成数组
     */
    public static class PathConfig{
        String path;
        String[] pathArray;
        public String getPath(){
            return path;
        }
        public String[] getPathArray(){
            return pathArray;
        }

        public void setPath(String path){
            if(StringUtils.isNotEmpty(path)){
                pathArray = path.split(";");
            }
            this.path=path;
        }
    }
    /**
     * 从请求中获取SessionToken
     * @param request
     * @return
     */
    public static String getSessionToken(ServerHttpRequest request){
        String sessionToken = request.getHeaders().getFirst(ZhiranConstants.SESSION_TOKEN_KEY);
        //注意这里isEmpty函数会判断是否为"" 或者为NULL 正常使用sessionToken会导致为NULL时抛出异常
        if(StringUtils.isEmpty(sessionToken)){
            sessionToken = request.getQueryParams().getFirst(ZhiranConstants.SESSION_TOKEN_KEY);
        }
        return sessionToken;
    }
}
