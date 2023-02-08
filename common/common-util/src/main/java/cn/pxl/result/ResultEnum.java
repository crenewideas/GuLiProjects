package cn.pxl.result;

public enum ResultEnum {
    common_success("success",20000,true),
    common_field("field",11101,false),
    common_exception("业务处理异常！",11102,false),
    upload_field("文件上传失败！",11201,false),
    read_upload_file_field("文件读取失败！",11202,false),
    upload_client_field("文件上传失败，客户端异常！",11203,false),
    excel_data_read_field("excel数据读取失败，请检查是否按模版填写，或重试！",11301,false);

    private String message;
    private Integer code;
    private boolean isSuccess;

    ResultEnum(String message, Integer code, boolean isSuccess) {
        this.message = message;
        this.code = code;
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
