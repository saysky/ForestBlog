package com.liuyanzhao.ssm.blog.controller.common;

import com.alibaba.fastjson.JSON;
import com.liuyanzhao.ssm.blog.dto.ResultVO;
import com.liuyanzhao.ssm.blog.dto.UploadFileVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.Calendar;

/**
 * @author liuyanzhao
 */
@Controller
@Slf4j
public class UploadFileController {


    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@Param("file") MultipartFile file) {
        //上传到本地用户目录
        String rootPath = "/Users/liuyanzhao/Documents/uploads";

        //1.校验文件后缀
        //文件的完整名称,如spring.jpeg
        String filename = file.getOriginalFilename();
        //文件后缀,如.jpeg
        String suffix = filename.substring(filename.lastIndexOf("."));
        String allowSuffix = ".jpg|.png|.jpeg|.gif|.bmp|.docx|.doc|.ppt|.pptx|.zip|.rar|.7z|.gz";
        if (allowSuffix.indexOf(suffix) == -1) {
            return JSON.toJSONString(new ResultVO<>(0, "暂不支持该类型文件上传！", null));
        }

        //2.检查文件名是否存在
        //创建年月文件夹
        Calendar date = Calendar.getInstance();
        File dateDirs = new File(date.get(Calendar.YEAR)
                + File.separator + (date.get(Calendar.MONTH) + 1));

        String newFileName = System.currentTimeMillis()+suffix;

        //目标文件
        File descFile = new File(rootPath + File.separator + dateDirs + File.separator + newFileName);
        //3.判断目标文件所在的目录是否存在
        if (!descFile.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            descFile.getParentFile().mkdirs();
        }

        //4.将内存中的数据写入磁盘
        try {
            file.transferTo(descFile);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传失败，cause:{}", e);
        }
        //5.返回数据
        //文件url
        String fileUrl = "/uploads/" + dateDirs + "/" + newFileName;

        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");

        UploadFileVO uploadFileVO = new UploadFileVO();
        uploadFileVO.setTitle(filename);
        uploadFileVO.setSrc(fileUrl);
        resultVO.setData(uploadFileVO);
        return JSON.toJSONString(resultVO);
    }
}
