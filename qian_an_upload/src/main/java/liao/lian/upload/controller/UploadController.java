package liao.lian.upload.controller;

import entity.Result;
import entity.StatusCode;
import liao.lian.upload.service.UploadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("upload")
@CrossOrigin
public class UploadController {

    @Autowired
    private  UploadService uploadService;

    @PostMapping("image")
    public Result uploadImage(@RequestPart("f") MultipartFile file){
        String url = uploadService.upload(file);
        if (StringUtils.isBlank(url)){
            //url为空，上传失败
            return new Result(false, StatusCode.ERROR.getCode(),"上传失败");
        }
        return new Result(true,StatusCode.OK.getCode(),"上传成功",url);
    }

}
