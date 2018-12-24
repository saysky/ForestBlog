package com.hfanss.blog.service;


import java.io.File;
import java.io.IOException;

/**
 * hfanss  2018/12/22
 */
public interface CosService {
	
	public String uploadFileToCos(File file,String secretId, String secretKey,String region,String bucketName, String suffix) throws IOException;
}
