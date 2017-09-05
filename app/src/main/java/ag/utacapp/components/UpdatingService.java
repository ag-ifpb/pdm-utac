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
