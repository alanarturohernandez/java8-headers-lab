package com.alan.headers.service;

import com.alan.headers.function.HeaderFunction;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class HeaderServiceImpl implements HeaderService {

    public Map<String, List<String>> getHeaders(HttpServletRequest request, List<String> filterHeader) {
        return HeaderFunction.collectResultHeadersFilter
                .apply(request, HeaderFunction.normalizeFilterHeaders.apply(filterHeader));
    }

    public Map<String, List<String>> getHeaders(HttpServletRequest request) {
        return HeaderFunction.collectResultHeaders
                .apply(HeaderFunction.toStream.apply(request.getHeaderNames()), request);
    }


}
