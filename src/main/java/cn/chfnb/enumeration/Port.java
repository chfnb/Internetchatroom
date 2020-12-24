package cn.chfnb.enumeration;

public enum Port {
    /**
     * UDPPORT udp端口
     */
    UDPPORT(10086,"udp端口"),
    /**
     * TCPPORT tcp端口
     */
    TCPPORT(10010,"tcp端口");
    private Integer code;
    private String desc;

    Port(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }


}
