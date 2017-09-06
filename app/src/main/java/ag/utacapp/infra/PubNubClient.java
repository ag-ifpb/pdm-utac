package ag.utacapp.infra;


import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;

import java.util.Arrays;

public class PubNubClient {
    private static final String PUBNUB_SUBTOKEN = "sub-c-2876554e-030b-11e5-897f-02ee2ddab7fe";
    private static final String PUBNUB_PUBTOKEN = "pub-c-895aaf20-3f93-43bd-aa4f-6b08d6869d96";

    private PubNub pubnub = null;

    private void init() {
        //
        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setSubscribeKey(PUBNUB_SUBTOKEN);
        pnConfiguration.setPublishKey(PUBNUB_PUBTOKEN);
        pnConfiguration.setSecure(false);
        //
        pubnub = new PubNub(pnConfiguration);
    }

    public PubNubClient() {
        init();
    }

    public PubNub connectSubscriber(final PubNubMsgCallback callbackObj){
        //
        pubnub.addListener(new SubscribeCallback() {
            @Override
            public void status(PubNub pubnub, PNStatus status) {
                Log.d("AGDebug", "Status: " + status.toString());
            }

            @Override
            public void presence(PubNub pubnub, PNPresenceEventResult presence) {
                Log.d("AGDebug", "Presence: " + presence.toString());
            }

            @Override
            public void message(PubNub pubnub, PNMessageResult message) {
                //log
                Log.d("AGDebug", "Recebendo mensagem");
                Log.d("AGDebug", "channel: " + message.getChannel());
                Log.d("AGDebug", "message: " + message.getMessage());
                Log.d("AGDebug", "timetoken: " + message.getTimetoken());
                //recuperando dados
                JsonElement element = message.getMessage();
                JsonObject json = element.getAsJsonObject();
                String name = json.get("name").getAsString();
                String latestmessage = json.get("msg").getAsString();
                //
                callbackObj.callback(name, latestmessage);
            }
        });
        pubnub.subscribe().channels(Arrays.asList("chat")).execute();
        //
        return pubnub;
    }

    public void connectPublisherAndSendMessage(String name, String msg) {
        //
        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setPublishKey(PUBNUB_PUBTOKEN);
        pnConfiguration.setSecure(false);
        //
        PubNub pubnub = new PubNub(pnConfiguration);
        //
        JsonObject json = new JsonObject();
        json.addProperty("name", name);
        json.addProperty("msg", msg);
        //
        pubnub.publish().channel("chat").message(json);
    }

}
