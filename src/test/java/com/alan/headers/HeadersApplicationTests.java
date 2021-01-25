package com.alan.headers;

import com.alan.headers.controller.HeaderController;
import com.alan.headers.service.HeaderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HeadersApplicationTests {


    @Test
    void contextLoads() {
    }

    @Test
    public void main() {
        HeadersApplication.main(new String[] {});
    }


}
