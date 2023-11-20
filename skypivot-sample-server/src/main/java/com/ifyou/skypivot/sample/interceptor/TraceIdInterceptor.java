package com.ifyou.skypivot.sample.interceptor;

import com.ifyou.skypivot.sample.utils.GlobalTraceIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author xueyu
 * @Date 2021/4/15 16:09
 * @Version 1.0
 */
@Slf4j
@Component
public class TraceIdInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //添加traceId
        String traceId = request.getHeader(GlobalTraceIdUtil.TRACE_ID);
        if (StringUtils.isEmpty(traceId)) {
            GlobalTraceIdUtil.generate();
        } else {
            GlobalTraceIdUtil.putTraceId(traceId);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {
        GlobalTraceIdUtil.clearTraceId();
    }
}
