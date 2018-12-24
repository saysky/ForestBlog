package com.hfanss.blog.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.hfanss.blog.service.CosService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;

/**
 * 上传文件至 腾讯云COS
 * 
 * @author hfanss
 *
 */
@Service
public class CosServiceImpl implements CosService {

	@Override
	public String uploadFileToCos(File localFile, String secretId, String secretKey, String region, String bucketName,String suffix)
			throws IOException {
		// 文件的完整名称,如spring.jpeg,替换临时文件的后缀为原始后缀
		String filename = localFile.getName().replaceAll("tmp", suffix);
		// 指定要上传到 COS 上对象键.指相对路径  例子：2018/12/sjfh.jpg
		String key = dateToyyyy1mm() + filename;
		// 1 初始化用户身份信息(secretId, secretKey)
		COSCredentials cred = new BasicCOSCredentials(secretId,secretKey);
		// 2 设置bucket的区域
		ClientConfig clientConfig = new ClientConfig(new Region(region));
		// 3 生成cos客户端
		COSClient cosClient = new COSClient(cred, clientConfig);
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
		PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
		// 关闭客户端(关闭后台线程)
		cosClient.shutdown();
		// 获取url，并返回
		StringBuffer url = new StringBuffer("https://");
		String fileUrl = url.append(bucketName).append(".file.myqcloud.com/").append(key)
				.toString();
		return fileUrl;
	}
	// 根据年月分文件夹
	private String dateToyyyy1mm() {
		String date = new SimpleDateFormat("yyyyMM").format(new Date());
		StringBuffer sb = new StringBuffer(date);// 构造一个StringBuilder对象
		date = sb.insert(4, "/").append("/").toString();//   2018/12/
		return date;
	}

}
