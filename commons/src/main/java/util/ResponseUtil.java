package util;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public final class ResponseUtil {

    public static void writeJson(HttpServletResponse response,Object o){
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(200);
        try {
            PrintWriter writer = response.getWriter();
            writer.write(JSONUtil.toJsonStr(o));
            writer.flush();
            writer.close();

        }catch (IOException e){
//            log.error("write json error");
            e.printStackTrace();
        }

    }
}
