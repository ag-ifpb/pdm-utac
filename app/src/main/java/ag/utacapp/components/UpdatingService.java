package ag.utacapp.components;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
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


public class UpdatingService extends IntentService {
    private static final String ACTION_UPDATING = "ag.utacapp.service.action.UPDATING";

    private void handleUpdating(){
        //
        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setSubscribeKey("sub-c-2876554e-030b-11e5-897f-02ee2ddab7fe");
        pnConfiguration.setSecure(false);
        //
        PubNub pubnub = new PubNub(pnConfiguration);
        //
        pubnub.addListener(new SubscribeCallback() {
            @Override
            public void status(PubNub pubnub, PNStatus status) {
                Log.d("AGDebug", "Status: " + status.toString());
            }
            @Override
            public void presence(PubNub pubnub, PNPresenceEventResult presence) {}
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
                //mensagem para broadcast
                Intent msg = new Intent("ag.utacapp.UPDATE_LISTENER");
                msg.putExtra("name", name);
                msg.putExtra("latestmessage", latestmessage);
                //enviando para broadcast
                Context ctx = getApplicationContext();
                LocalBroadcastManager manager = LocalBroadcastManager.getInstance(ctx);
                manager.sendBroadcast(msg);
            }
        });
        pubnub.subscribe().channels(Arrays.asList("ifpb-pdm")).execute();
        Log.d("AGDebug", "Inscrição no canal 'ifpb-pdm");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("AGDebug", "Inicializando  o serviço");
        handleUpdating();
    }

    public UpdatingService() {
        super("UpdatingService");
    }

    public static void startUpdating(Context context) {
        Intent intent = new Intent(context, UpdatingService.class);
        intent.setAction(ACTION_UPDATING);
        context.startService(intent);
    }

}
