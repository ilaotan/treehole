package com.zhangyingwei.treehole.admin.log.model;

/**
 * Created by zhangyw on 2017/6/14.
 */
public class LogModel {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 来源 IP
     */
    private String ip;
    /**
     * IP 所在位置
     * 中国,北京,北京
     * 考虑到时间问题，这个值放在队列后边获取
     */
    private String ip_locatoin;
    /**
     * 来源网址
     */
    private String referer;
    /**
     * 访问地址
     */
    private String url;
    /**
     * 来源浏览器类型
     */
    private String agent;
    /**
     * 访问动作
     */
    private String action;
    /**
     * 时间戳
     */
    private Long timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp_locatoin() {
        return ip_locatoin;
    }

    public void setIp_locatoin(String ip_locatoin) {
        this.ip_locatoin = ip_locatoin;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "LogModel{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", ip_locatoin='" + ip_locatoin + '\'' +
                ", referer='" + referer + '\'' +
                ", url='" + url + '\'' +
                ", agent='" + agent + '\'' +
                ", action='" + action + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
