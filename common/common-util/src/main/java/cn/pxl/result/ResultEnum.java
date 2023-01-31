package cn.pxl.result;

public enum ResultEnum {
    common_success("success","000000",true),
    common_field("field","111111",false),
    common_exception("业务处理异常！","111112",false);
    private String message;
    private String code;
    private boolean isSuccess;

    ResultEnum(String message, String code, boolean isSuccess) {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
