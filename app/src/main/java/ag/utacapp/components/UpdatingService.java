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
import java.util.Observable;
import java.util.Observer;

import ag.utacapp.infra.ChatManagerImpl;


public class UpdatingService extends IntentService implements Observer{
    private static final String ACTION_UPDATING = "ag.utacapp.service.action.UPDATING";

    private ChatManagerImpl instance;

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("AGDebug", "Inicializando  o serviço");
        Log.d("AGDebug", "Registrando o serviço como observador do chat");
        String userName = intent.getStringExtra("name");
        //inicializando o singleton
        ChatManagerImpl.init(userName);
        //
        instance = ChatManagerImpl.instance(this);
    }

    public UpdatingService() {
        super("UpdatingService");
    }

    @Override
    public void update(Observable observable, Object o) {
        //
        Log.d("AGDebug", "Recevendo notificação do sujeito");
        //
        String[] datas = (String[]) o;
        String nam = datas[0];
        String msg = datas[1];
        //
        Intent intentMsg = new Intent("ag.utacapp.UPDATE_LISTENER");
        intentMsg.putExtra("name", nam);
        intentMsg.putExtra("latestmessage", msg);
        //
        LocalBroadcastManager lm = LocalBroadcastManager.getInstance(getApplicationContext());
        lm.sendBroadcast(intentMsg);
    }

    public static void startUpdating(Context context) {
        Intent intent = new Intent(context, UpdatingService.class);
        intent.setAction(ACTION_UPDATING);
        context.startService(intent);
    }

}
