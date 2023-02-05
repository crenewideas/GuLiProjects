package cn.pxl.ossservice.service;

import cn.pxl.exception.CommonException;
import cn.pxl.ossservice.beans.AliOssProperties;
import cn.pxl.ossservice.service.intf.FileUploadInterface;
import cn.pxl.result.ResultEnum;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FileUploadService implements FileUploadInterface {

    @Override
    public String upload(MultipartFile file) {
        byte[] fileBytes;
        String fileName;
        try {
            String frontStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            fileName = frontStr + "/" + file.getName();
            fileBytes = file.getBytes();
        } catch (IOException e) {
            throw new CommonException(ResultEnum.read_upload_file_field.getCode(),ResultEnum.read_upload_file_field.getMessage() + ":"  + e.getMessage());
        }

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(AliOssProperties.END_POINT, AliOssProperties.ACCESS_KEY_ID, AliOssProperties.ACCESS_KEY_SECRET);
        try {
            ossClient.putObject(AliOssProperties.BUCKET_NAME, fileName, new ByteArrayInputStream(fileBytes));
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return null;
    }
}
