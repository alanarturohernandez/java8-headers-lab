package com.alan.headers.controller;

import com.alan.headers.service.HeaderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/api")
public class HeaderController {

    private final HeaderService headerService;

    @GetMapping("/headers")
    public ResponseEntity<Map<String, List<String>>> getHeaders(HttpServletRequest request,
                                                                @RequestParam(value = "filter", required = false)
                                                                        List<String> headers) {
        if (headers != null) {
            return ResponseEntity.ok(headerService.getHeaders(request, headers));
        }

        return ResponseEntity.ok(headerService.getHeaders(request));
    }

}
