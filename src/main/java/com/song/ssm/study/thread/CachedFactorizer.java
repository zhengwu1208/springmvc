package com.song.ssm.study.thread;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by Administrator on 2017/5/3.
 */
@Component
public class CachedFactorizer implements Servlet {
    private BigInteger lastNumber;
    private BigInteger[] lastFactors;
    private Long hits;
    private Long cacheHits;


    public synchronized Long getHits() {
        return hits;
    }

    public synchronized Long getCacheHitRadio() {
        return cacheHits / hits;
    }


    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger i = extractFromRequest(servletRequest);
        BigInteger[] factors = null;

        synchronized (this) {
            hits++;
            if (i.equals(lastNumber)) {
                cacheHits++;
                factors = lastFactors.clone();
            }
        }

        if (factors == null) {
            factors = factor(i);
            synchronized (this) {
                lastNumber = i;
                lastFactors = factors.clone();
            }
        }
        servletResponse.getWriter().print(factors);
    }

    private BigInteger[] factor(BigInteger i) {
        return new BigInteger[0];
    }

    private BigInteger extractFromRequest(ServletRequest servletRequest) {
        String lastNumberStr = servletRequest.getParameter("lastNumber");
        if (StringUtils.isBlank(lastNumberStr)) {
            return null;
        }
        return new BigInteger(lastNumberStr);
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }
}
