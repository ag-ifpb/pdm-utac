package ag.utacapp.infra;

public interface PubNubMsgCallback {
    void callback(String name, String msg);
}
