package com.alan.headers.function;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@FunctionalInterface
public interface HeaderFunction {
    String HEADER_SEPARATOR = ",";

    Function<Enumeration<String>, Stream<String>> toStream = e -> StreamSupport.stream(
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

    Function<Enumeration<String>, List<String>> collectHeadersByName =
            e -> toStream.apply(e)
                    .flatMap(headerValue -> Arrays.stream(headerValue.split(HEADER_SEPARATOR)))
                    .map(String::trim)
                    .collect(Collectors.toList());

    BiFunction<Stream<String>, HttpServletRequest, Map<String, List<String>>> collectResultHeaders =
            (stream, req) -> stream
                    .collect(
                            Collectors.toMap(
                                    String::toString,
                                    headerName -> collectHeadersByName.apply(req.getHeaders(headerName)))
                    );

    Function<List<String>, Set<String>> normalizeFilterHeaders = filters -> filters.stream()
            .map(String::toLowerCase)
            .collect(Collectors.toSet());

    BiFunction<HttpServletRequest, Set<String>, Map<String, List<String>>> collectResultHeadersFilter =
            (req, filters) -> collectResultHeaders.apply(toStream.apply(req.getHeaderNames())
                    .filter(filters::contains), req);

    void apply();
}
