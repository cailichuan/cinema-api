package api.write;

import model.entity.Activity;
import model.entity.Arrangement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 写api
 */


@FeignClient(name = "WRITE-API")
public interface WriteApi {
    //==================Activity==================================
    @PostMapping("/writeapi/activity")
    public void createActivity(@RequestBody Activity activity);

    @DeleteMapping("/writeapi/activity{id}")
    public void deleteActivity(@PathVariable(value = "id") Long id);


    //===================Arrangement===============================
    @PostMapping("/writeapi/arrangement")
    public void createArrangement(@RequestBody Arrangement arrangement);

    //=================Upload===========================
    @PostMapping("/writeapi/upload")
    public Integer savaUpload(MultipartFile file, @RequestParam(name = "path") String path);
}
