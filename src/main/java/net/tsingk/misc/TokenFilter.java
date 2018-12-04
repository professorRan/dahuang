package net.tsingk.misc;

import net.tsingk.m.ISecurityService;
import net.tsingk.m.SignException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(filterName = "TokenFilter")
public class TokenFilter implements Filter {

    private Log log = LogFactory.getLog(TokenFilter.class);

    @Value("${token.skip.url}")
    private String skipUris;

    @Autowired
    private ISecurityService ss;


    private Map<String, String> skipUriMapping;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String ctxPath = request.getContextPath();
        log.info(" Request Path " + ctxPath);
        if (skipUriMapping.containsKey(ctxPath)) {
            log.info(" Path " + ctxPath +"  skipped");
            chain.doFilter(req, resp);
        } else {
            String token = request.getParameter("token");
            if (token == null || token.isEmpty()) {
                log.error("No token Info render error");
                //TODO render json error and return
            }

            //TODO check user token
            try {
                if (ss.verifyToken(token) == false) {
                    //TODO return error
                }
            } catch (SignException e) {
                e.printStackTrace();
                //TODO sign exception
            }
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        skipUriMapping = new HashMap<>();
        String[] skipUriArray = skipUris.split(" ");
        for (String uri : skipUriArray) {
            skipUriMapping.put(uri, "skip");
        }
    }

}
