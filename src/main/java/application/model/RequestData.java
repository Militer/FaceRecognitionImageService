package application.model;

/**
 * User: militer
 * Date: 02.06.2017.
 */
public class RequestData {
    private String id, uri, method;
    private long startTime, endTime;

    public RequestData(String id, String uri, String method, long startTime) {
        this.id = id;
        this.uri = uri;
        this.method = method;
        this.startTime = startTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
