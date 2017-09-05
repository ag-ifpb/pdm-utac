package ag.utacapp.infra;

import com.pubnub.api.PubNub;

import java.util.Observable;
import java.util.Observer;

import ag.utacapp.model.ChatManager;


public class ChatManagerImpl extends Observable implements ChatManager {
    private final PubNubClient client;
    private final Observer observer;
    private final String userName;

    public ChatManagerImpl(String userName, Observer observer){
        this.userName = userName;
        this.observer = observer;
        this.client = new PubNubClient();
    }

    @Override
    public void registerLikeSubscriber() {
        client.connectSubscriber(new PubNubMsgCallback() {
            @Override
            public void callback(String name, String msg) {
                receiveMessage(name, msg);
            }
        });
    }

    @Override
    public void sendMessage(String msg) {
        client.connectPublisherAndSendMessage(userName, msg);
    }

    @Override
    public void receiveMessage(String name, String msg) {
        observer.update(this, new String[]{name, msg});
    }

}
