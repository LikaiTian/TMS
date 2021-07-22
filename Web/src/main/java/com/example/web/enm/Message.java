package com.example.web.enm;

/**
 * @Author Memory
 * @Date 2021/7/8 21:20
 * @Version 1.0
 */

//返回的信息
public enum Message {
    DOT_NO_ID(2, "请不要传入ID，作为参数！"),
    NAME_TOO_LONG(1, "名字太长了！"),
    NO_ID(3, "缺少Id参数！"),
    USER_ERR_PASS(9, "用户或密码错误！"),
    USER_NOT_EXIST(4, "用户不存在！"),
    USER_IS_EXIST(12,"用户已注册！"),
    FILE_UPLOAD_ERROR(13,"文件上传失败！"),
    EMPLOY_IN_JOB(14,"该员工不属于本公司且处于在职状态，不可查询"),
    EMPLOY_IS_EXIST(15,"员工已存在！"),
    PHONE_NOT_LEGAL(16,"电话不合法！"),
    EMAIL_NOT_LEGAL(17,"邮箱不合法！"),
    CHARACTER_EMPTY(18,"有空字符串"),
    HAS_CHINESE(19,"有中文！"),
    PASSWORD_STYLE(20,"密码必须有字母和数字!"),
    CARDID_NOT_LEGAL(21,"卡号不合法!"),
    IMG_ERROR(22,"头像修改失败！"),
    EXIT_SUCCESS(23,"退出成功！"),
    FILE_DOWNLOAD_ERROR(24,"文件下载失败！"),
    EMPLOY_NOT_COMMENT(25,"未对该员工进行评价！"),
    ;
    private Integer code;
    private String message;

    Message(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
