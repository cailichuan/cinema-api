package com.cinema.main_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MainApiApplicationTests {

    @Test
    void contextLoads() {
        int i=1,s=0;
        while (i<3){
            i++;
            s+=i;
        }
        System.out.println(s);
    }

}
