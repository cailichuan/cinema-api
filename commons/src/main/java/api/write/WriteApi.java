package api.write;

import model.entity.Activity;
import model.entity.Arrangement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 写api
 */


@FeignClient(name = "WRITE-API")
public interface WriteApi {
    //==================Activity==================================
    @PostMapping("/writeapi/activity")
    public void createActivity(@RequestBody Activity activity);

    @DeleteMapping("/writeapi/activity{id}")
    public void deleteActivity(@PathVariable(value = "id") Integer id);


    //===================Arrangement===============================
    @PostMapping("/writeapi/arrangement")
    public void createArrangement(@RequestBody Arrangement arrangement);
}
