package etltest;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-04-20 14:16
 */
public class LogBean implements Writable {
    private String ip;
    private String time_zone;
    private String user;
    private String request;
    private String userURL;
    private String status;
    private String body_bytes_sent;
    private String http_refer;
    private String http_user_agent;
    private boolean valid;

    public LogBean() {
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTime_zone() {
        return time_zone;
    }

    public void setTime_zone(String time_zone) {
        this.time_zone = time_zone;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getUserURL() {
        return userURL;
    }

    public void setUserURL(String userURL) {
        this.userURL = userURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBody_bytes_sent() {
        return body_bytes_sent;
    }

    public void setBody_bytes_sent(String body_bytes_sent) {
        this.body_bytes_sent = body_bytes_sent;
    }

    public String getHttp_refer() {
        return http_refer;
    }

    public void setHttp_refer(String http_refer) {
        this.http_refer = http_refer;
    }

    public String getHttp_user_agent() {
        return http_user_agent;
    }

    public void setHttp_user_agent(String http_user_agent) {
        this.http_user_agent = http_user_agent;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return valid + "\t" + ip + "\t" + time_zone + "\t" + user + "\t" + request + "\t" + userURL + "\t" + status + "\t" + body_bytes_sent + "\t" + http_refer + "\t" + http_user_agent;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeBoolean(valid);
        out.writeUTF(ip);
        out.writeUTF(time_zone);
        out.writeUTF(user);
        out.writeUTF(request);
        out.writeUTF(userURL);
        out.writeUTF(status);
        out.writeUTF(body_bytes_sent);
        out.writeUTF(http_refer);
        out.writeUTF(http_user_agent);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.valid = in.readBoolean();
        this.ip = in.readUTF();
        this.time_zone = in.readUTF();
        this.user = in.readUTF();
        this.request = in.readUTF();
        this.userURL = in.readUTF();
        this.status = in.readUTF();
        this.body_bytes_sent = in.readUTF();
        this.http_refer = in.readUTF();
        this.http_user_agent = in.readUTF();
    }
}
