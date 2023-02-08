package cn.pxl.ossservice.service;

import cn.pxl.exception.CommonException;
import cn.pxl.ossservice.beans.AliOssProperties;
import cn.pxl.ossservice.service.intf.FileUploadService;
import cn.pxl.result.ResultEnum;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Override
    public String uploadImage(MultipartFile file) {
        String filePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        return doUpload(file,filePath);
    }

    @Override
    public String uploadExcel(MultipartFile file) {
        return doUpload(file,"template/excel","courseExample.xls");
    }

    @Override
    public String uploadCourseCover(MultipartFile file) {
        return doUpload(file,"images/courseCover",null);
    }

    private String doUpload(MultipartFile file,String filePath){
        return doUpload(file,filePath,null);
    }

    private String doUpload(MultipartFile file,String filePath,String fileName){
        String fileUrl;
        if(fileName != null){
            fileUrl = filePath + "/" + fileName;
        } else {
            String originalFilename = file.getOriginalFilename();
            String suffex = originalFilename.substring(originalFilename.lastIndexOf(".") - 1);
            fileUrl = filePath + "/" + UUID.randomUUID() + suffex;
        }
        byte[] fileBytes;
        try {
            fileBytes = file.getBytes();
        } catch (IOException e) {
            throw new CommonException(ResultEnum.read_upload_file_field.getCode(),ResultEnum.read_upload_file_field.getMessage() + ":"  + e.getMessage());
        }
        String endPoint = AliOssProperties.END_POINT;
        String accessKeyId = AliOssProperties.ACCESS_KEY_ID;
        String accessKeySecret = AliOssProperties.ACCESS_KEY_SECRET;
        String bucketName = AliOssProperties.BUCKET_NAME;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endPoint,accessKeyId,accessKeySecret);
        try {
            ossClient.putObject(AliOssProperties.BUCKET_NAME, fileUrl , new ByteArrayInputStream(fileBytes));
            // 获取url地址
            return "http://" + bucketName + "." + endPoint + "/" + fileUrl;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

}
