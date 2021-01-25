package com.alan.headers.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class HeaderServiceImpl implements HeaderService {

    public static final String HEADER_SEPARATOR = ",";

    public Map<String, List<String>> getHeaders(HttpServletRequest request, List<String> filterHeader) {
        List<String> finalFilterHeader = filterHeader.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        return toStream(request.getHeaderNames())
                .filter(headerName -> finalFilterHeader.contains(headerName.toLowerCase()))
                .collect(Collectors.toMap(String::toString,
                        headerName -> toStream(request.getHeaders(headerName))
                                .flatMap(headerValue -> Arrays.stream(headerValue.split(HEADER_SEPARATOR)))
                                .map(String::trim)
                                .collect(Collectors.toList())));
    }

    public Map<String, List<String>> getHeaders(HttpServletRequest request) {
        return toStream(request.getHeaderNames())
                .collect(Collectors.toMap(String::toString,
                        headerName -> toStream(request.getHeaders(headerName))
                                .flatMap(headerValue -> Arrays.stream(headerValue.split(HEADER_SEPARATOR)))
                                .map(String::trim)
                                .collect(Collectors.toList()))
                );
    }


    private Stream<String> toStream(Enumeration<String> e) {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(
                        new Iterator<String>() {
                            public String next() {
                                return e.nextElement();
                            }

                            public boolean hasNext() {
                                return e.hasMoreElements();
                            }
                        },
                        Spliterator.ORDERED), false);
    }

}
