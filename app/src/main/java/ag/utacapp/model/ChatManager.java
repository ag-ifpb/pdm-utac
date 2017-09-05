package ag.utacapp.model;

public interface ChatManager {
    void registerLikeSubscriber();
    void sendMessage(String msg);
    void receiveMessage(String name, String message);
}
