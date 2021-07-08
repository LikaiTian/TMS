package com.example.web.enm;

/**
 * @Author Memory
 * @Date 2021/7/8 21:20
 * @Version 1.0
 */
public enum Message {
    DOT_NO_ID(2, "请不要传入ID，作为参数！"),
    GOODS_NOT_EXIST(5, "该商品不存在或者已下架！"),
    LACK_SHANGJIA_ID(11, "请输入查询商家id！"),
    MERCHANT_NOT_EXIST(6, "商家不存在或者已注销！"),
    NAME_TOO_LONG(1, "名字太长了！"),
    NO_ID(3, "缺少Id参数！"),
    ORDERS_NOT_EXIST(7, "订单不存在或者被删除！"),
    RIDER_NOT_EXIST(8, "不存在该骑手！"),
    TOO_Expensive(10, "商品太贵了没钱就别买了！"),
    USER_ERR_PASS(9, "用户不存在或密码错误！"),
    USER_NOT_EXIST(4, "用户不存在！"),
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
