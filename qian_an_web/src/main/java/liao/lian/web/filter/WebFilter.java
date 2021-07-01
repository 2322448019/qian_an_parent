package liao.lian.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;

public class WebFilter{

}
//@Component
//public class WebFilter extends ZuulFilter {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Override
//    public String filterType() {
//        return "pre";
//    }
//
//    @Override
//    public int filterOrder() {
//        return 0;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    @Override
//    public Object run() throws ZuulException {
//        RequestContext currentContext = RequestContext.getCurrentContext();
//        // 获取request域
//        HttpServletRequest request = currentContext.getRequest();
//        if (request.getMethod().equals("OPTIONS")) {
//            return null;
//        }
//        // 登录不需要判断
//        if (request.getRequestURL().toString().indexOf("login") > 0) {
//            return null;
//        }
//
//        // 得到头信息
//        String header = request.getHeader("Authorization");
//        if (!StringUtils.isEmpty(header)) {
//            if (header.startsWith("Bearer ")) {
//                String token = header.substring(7);
//                try {
//                    Claims claims = jwtUtil.parseJWT(token);
//                    String role = (String) claims.get("roles");
//                    if (role.equals("user")) {
//                        // 把头信息转发下去， 并且放行
//                        currentContext.addZuulRequestHeader("Authorization", header);
//                        return null;
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    currentContext.setSendZuulResponse(false);  // 终止运行
//                }
//            }
//        }
//        currentContext.setSendZuulResponse(false);      // 终止运行
//        currentContext.setResponseStatusCode(403);      // 权限不足
//        currentContext.setResponseBody("权限不足");
//        currentContext.getResponse().setContentType("text/html;charset=utf-8");
//        return null;
//    }
//}
