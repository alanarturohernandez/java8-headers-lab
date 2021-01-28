package com.alan.headers.service;

import com.alan.headers.controller.HeaderController;
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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
class HeaderServiceTest {

    @Autowired
    private HeaderService headerService;

    @Test
    public void getHeaders() {
        MockHttpServletRequest request = getHttpServletRequest();
        Assertions.assertEquals(16, headerService.getHeaders(request).size());
    }

    @Test
    public void getHeadersWithFilter() {
        MockHttpServletRequest request = getHttpServletRequest();
        List<String> headers = Stream.of("Accept-Language").collect(Collectors.toList());
        Assertions.assertEquals(1, headerService.getHeaders(request, headers).size());
    }

    private MockHttpServletRequest getHttpServletRequest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        request.addHeader("Accept-Encoding", "gzip, deflate, br");
        request.addHeader("Accept-Language", "es,en;q=0.9");
        request.addHeader("Cache-Control", "no-cache");
        request.addHeader("Connection", "keep-alive");
        request.addHeader("Cookie", "Webstorm-ed163f43=2970e04f-3c88-44ca-a15c-325f9315dbd1");
        request.addHeader("Host", "localhost:8080");
        request.addHeader("Pragma", "no-cache");
        request.addHeader("sec-ch-ua", "sec-ch-ua\",\"\"Google Chrome\";v=\"87\", \" Not;A Brand\";v=\"99\", \"Chromium\";v=\"87\"");
        request.addHeader("sec-ch-ua-mobile", "?0");
        request.addHeader("Sec-Fetch-Dest", "document");
        request.addHeader("Sec-Fetch-Mode", "navigate");
        request.addHeader("Sec-Fetch-Site", "none");
        request.addHeader("Sec-Fetch-User", "?1");
        request.addHeader("Upgrade-Insecure-Requests", "1");
        request.addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36");
        return request;
    }
}