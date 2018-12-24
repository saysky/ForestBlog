package com.hfanss.blog.controller.Common;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hfanss.blog.entity.custom.ResultVO;
import com.hfanss.blog.entity.custom.UploadFileVO;
import com.hfanss.blog.service.CosService;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

@Controller
public class UploadFileController {
    
	@Autowired
	private CosService cosService;
	
	//上传文件至  腾讯云COS
	@RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO uploadFileToCOS(@Param("file")MultipartFile file) throws IOException {
		String filename = file.getOriginalFilename();
		String suffix = filename.substring(filename.lastIndexOf(".")+1);//文件后缀
		String cos_secretId="AKIDa0CAuP5hW6SZT9pooZ6TcXDhxzmLHkcF";
		String cos_secretKey="VosuiHciaDblgNs9C1c2GG1ZsxoW2NTM";
		String cos_region="ap-shanghai";
		String cos_bucketName="blog-1252958858";
        //MultipartFile转File
        File localFile = mFileToFile(file);
        String fileUrl = cosService.uploadFileToCos(localFile, cos_secretId, cos_secretKey, cos_region, cos_bucketName,suffix);
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        UploadFileVO uploadFileVO = new UploadFileVO();
        uploadFileVO.setTitle(filename);
        uploadFileVO.setSrc(fileUrl);
        resultVO.setData(uploadFileVO);
		return resultVO;
	}

	
	//上传文件  至  本地
    @RequestMapping(value = "/uploadFile2",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO uploadFile(@Param("file")MultipartFile file) throws IOException {

        //本地使用,上传位置
        //String rootPath ="/Users/liuyanzhao/Documents/uploads/";
        String rootPath ="/www/uploads/";
//        String rootPath ="G:/uploads";
        String filename = file.getOriginalFilename();
        //文件名,如spring
        String name = filename.substring(0,filename.indexOf("."));
        //文件后缀,如.jpeg
        String suffix = filename.substring(filename.lastIndexOf("."));

        //创建年月文件夹
        Calendar date = Calendar.getInstance();
        File dateDirs = new File(date.get(Calendar.YEAR)
                + File.separator + (date.get(Calendar.MONTH)+1));

        //目标文件
        File descFile = new File(rootPath+File.separator+dateDirs+File.separator+filename);
        int i = 1;
        //若文件存在重命名
        String newFilename = filename;
        while(descFile.exists()) {
            newFilename = name+"("+i+")"+suffix;
            String parentPath = descFile.getParent();
            descFile = new File(parentPath+File.separator+newFilename);
            i++;
        }
        //判断目标文件所在的目录是否存在
        if(!descFile.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            descFile.getParentFile().mkdirs();
        }
        
        //将内存中的数据写入磁盘
        file.transferTo(descFile);
        //完整的url
        String fileUrl =  "/uploads/"+dateDirs+ "/"+newFilename;

        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");

        UploadFileVO uploadFileVO = new UploadFileVO();
        uploadFileVO.setTitle(filename);
        uploadFileVO.setSrc(fileUrl);
        resultVO.setData(uploadFileVO);
        return resultVO;
    }
    
    //@Description: MultipartFile 转File  
	private File mFileToFile(MultipartFile file) {
		CommonsMultipartFile cf = (CommonsMultipartFile) file;//此处file 是你的MultipartFile
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
        File localFile = fi.getStoreLocation();//会在项目的根目录的临时文件夹下生成一个临时文件 *.tmp
		return localFile;
	}
		
}
