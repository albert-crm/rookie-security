//package com.rookie.bigdata.filter;
//
//import org.springframework.core.Ordered;
//import org.springframework.stereotype.Component;
//
///**
// * @Class AuthorizeFilter
// * @Description TODO
// * @Author rookie
// * @Date 2023/12/25 10:21
// * @Version 1.0
// */
//@Component
//public class AuthorizeFilter implements GlobalFilter, Ordered {
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        //获取请求对象
//        ServerHttpRequest request = exchange.getRequest();
//        //获取响应对象
//        ServerHttpResponse response = exchange.getResponse();
//        //判断当前的请求是否为登录请求,如果是登录请求，直接放行
//        if(request.getURI().getPath().contains("/admin/login")){
//            //放行
//            return chain.filter(exchange);
//        }
//        //获取所有的请求头信息
//        HttpHeaders headers = request.getHeaders();
//        //获取jwt令牌信息
//        String jwtToken = headers.getFirst("token");
//        //判断当前令牌是否存在
//        if(StringUtils.isEmpty(jwtToken)){
//            //令牌为空或不存在，返回错误信息
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);//返回一个状态，状态为未认证
//            return response.setComplete();//设置完成，类似与break
//        }
//        //如果当前令牌存在，解析令牌，判断是否合法，如果不合法，则向客户端返回错误提示
//        try {
//            //解析令牌  通过密钥解析令牌，如果能正常解析，说明令牌是正确的
//            JwtUtil.parseJWT(jwtToken);
//        }catch (Exception e){
//            e.printStackTrace();
//            //令牌解析失败
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            return response.setComplete();
//        }
//        //如果当前令牌合法，则放行
//        return chain.filter(exchange);
//    }
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//}
