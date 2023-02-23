package com.cinema.write_api;

import model.dto.PosterDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import util.PathUtil;
import util.RDTCUitl;

import java.io.FileNotFoundException;

@SpringBootTest
class WriteApiApplicationTests {

    @Test
    void contextLoads() throws FileNotFoundException {

        String s  ="PosterDto{id:null, title:'黑恶黑可那', upid:0, status:true, createAt:'2012-11-22', uuid:'b61b814e-f8b7-4127-9baf-ff2987ccb8bd'}";
        String json = RDTCUitl.getJson(s);
        System.out.println(json);

        PosterDto data = null;
        try {
            data = new RDTCUitl<PosterDto>().getData(s, RDTCUitl.ClassName.POSTERDTO);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println(data);
    }

}
