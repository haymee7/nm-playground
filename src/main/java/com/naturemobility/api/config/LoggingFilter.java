package com.naturemobility.api.config;

import com.google.gson.Gson;
import com.naturemobility.api.dao.ApiLogDao;
import com.naturemobility.api.dto.ApiLogDto;
import com.naturemobility.api.util.CommonUtil;
import com.naturemobility.api.util.LoggingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class LoggingFilter extends OncePerRequestFilter {
    private final ApiLogDao logDao;

    public LoggingFilter(ApiLogDao logDao) {
        this.logDao = logDao;
    }

    private final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final HttpServletRequest req = new RequestWrapper(request);
            final HttpServletResponse res = new ResponseWrapper(response);

            Map<String, Object> requestMap = LoggingUtil.makeLoggingRequestMap(req);

            filterChain.doFilter(req, res);

            Map<String, Object> responseMap = LoggingUtil.makeLoggingResponseMap(res);

            // 로그 저장 > api_log
            saveLog(request.getServletPath(), requestMap, responseMap);

            ((ResponseWrapper) res).copyBodyToResponse();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "서비스 점검중");
        }

    }

    private void saveLog(String requestPath, Map<String, Object> request, Map<String, Object> response) {
        String service = "";

        if (request.containsKey("header")) {
            Map<String, Object> headers = (Map<String, Object>) request.get("header");

            if (headers.containsKey("xclienttoken")) {
                service = CommonUtil.getClientName();
            }
        }

        String jsonReq = new Gson().toJson(request, request.getClass());
        String jsonRes = new Gson().toJson(response, response.getClass());

        ApiLogDto logDto = new ApiLogDto(requestPath, service, jsonReq, jsonRes);
        logger.info(logDto.toString());
        logDao.save(logDto);
    }
}
