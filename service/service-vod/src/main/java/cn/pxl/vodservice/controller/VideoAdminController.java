package cn.pxl.vodservice.controller;

import cn.pxl.result.ResultEntity;
import cn.pxl.vodservice.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(description="阿里云视频点播微服务")
@CrossOrigin //跨域
@RestController
@RequestMapping("/vodService/video")
public class VideoAdminController {

	@Autowired
	private VideoService videoService;

	@PostMapping("upload")
	public ResultEntity uploadVideo(
			@ApiParam(name = "file", value = "文件", required = true)
			@RequestParam("file") MultipartFile file) throws Exception {

		String videoId = videoService.uploadVideo(file);
		return ResultEntity.success(videoId);
	}
}