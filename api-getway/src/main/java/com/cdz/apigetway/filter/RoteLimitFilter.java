package com.cdz.apigetway.filter;

import com.cdz.apigetway.exception.RoteLimitFiltException;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * @author chendezhi
 * @date 2018/4/25 10:48
 * 限流
 */
@Component
public class RoteLimitFilter extends ZuulFilter {

    /**
     * 令牌桶限流
     */
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        if (!RATE_LIMITER.tryAcquire()){
            throw new RoteLimitFiltException();
        }
        return null;
    }
}
