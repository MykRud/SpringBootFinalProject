package com.spring_final.SpringFinalProject;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {SpringSecurityWebAuxTestConfig.class})
@AutoConfigureMockMvc
class SpringFinalProjectApplicationTests {

    @Test
    @Disabled
    void contextLoads() {
    } // TODO: find out why test is not ignored

}
