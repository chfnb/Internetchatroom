package cn.chfnb.pojo;

/**
 * 消息实体
 */
public class Message {
    /**
     * 消息
     */
    private String msg;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 发送者
     */
    private User sender;

    public Message(String msg, Integer code, User sender) {
        this.msg = msg;
        this.code = code;
        this.sender = sender;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
}
