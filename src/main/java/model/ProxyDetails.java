package model;

public class ProxyDetails {
    private String ip;
    private String port;

    public ProxyDetails(String ip, String port){
        this.ip=ip;
        this.port=port;
    }

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(String port){
        this.port = port;
    }
}
