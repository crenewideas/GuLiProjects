package cn.pxl.ossservice.service.intf;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    /**
     * 用户头像文件上传至阿里云
     * @param file
     * @return
     */
    String uploadImage(MultipartFile file);
    /**
     * excel文件上传至阿里云
     * @param file
     * @return
     */
    String uploadExcel(MultipartFile file);

    /**
     * 课程封面文件上传至阿里云
     * @param file
     * @return
     */
    String uploadCourseCover(MultipartFile file);
}
