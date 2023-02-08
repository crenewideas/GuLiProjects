package cn.pxl.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "课时信息")
@Data
public class VideoVo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String chapterId;
	private String id;
	private String title;
	private Boolean free;
}