package com.alan.headers.service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface HeaderService {

    Map<String, List<String>> getHeaders(HttpServletRequest request, List<String> filterHeader);
    Map<String, List<String>> getHeaders(HttpServletRequest request);

}
