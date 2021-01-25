package com.alan.headers.controller;

import com.alan.headers.controller.HeaderController;
import com.alan.headers.service.HeaderService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
class HeaderControllerTest {

    @Autowired
    private HeaderController headerController;

    @Mock
    MockHttpServletRequest request;

    @MockBean
    private HeaderService headerService;

    @Before
    public void before() {

    }

    @Test
    public void getHeadersNull() {
        //given
        Map<String, List<String>> map = Stream.of(
                Pair.of("accept-language", Stream.of("es", "en;q=0.9").collect(Collectors.toList())),
                Pair.of("cache-control", Stream.of("no-cache").collect(Collectors.toList()))
        ).collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));

        //when
        Mockito.when(headerService.getHeaders(request))
                .thenReturn(map);

        //then
        Assertions.assertEquals(0, headerController.getHeaders(null, null).getBody().size());
    }

    @Test
    public void getHeaders() {
        //given
        Map<String, List<String>> map = Stream.of(
                Pair.of("accept-language", Stream.of("es", "en;q=0.9").collect(Collectors.toList())),
                Pair.of("cache-control", Stream.of("no-cache").collect(Collectors.toList()))
        ).collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));

        //when
        Mockito.when(headerService.getHeaders(request))
                .thenReturn(map);

        //then
        Assertions.assertEquals(2, headerController.getHeaders(request, null).getBody().size());
    }

    @Test
    public void getHeadersWithFilter() {
        //given
        Map<String, List<String>> map = Stream.of(
                Pair.of("accept-language", Stream.of("es", "en;q=0.9").collect(Collectors.toList()))
        ).collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));

        //when
        List<String> headers = Stream.of("Accept-Language").collect(Collectors.toList());
        Mockito.when(headerService.getHeaders(request, headers))
                .thenReturn(map);

        //then
        Assertions.assertEquals(1, headerController.getHeaders(request, headers).getBody().size());
    }

}