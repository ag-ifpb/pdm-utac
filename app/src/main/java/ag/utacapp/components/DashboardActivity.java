package ag.utacapp.components;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import ag.utacapp.R;
import ag.utacapp.presenter.DasboardTabPresenter;
import ag.utacapp.presenter.LatestMessagePresenter;
import ag.utacapp.presenter.LogoPanelPresenter;

public class DashboardActivity extends AppCompatActivity implements LogoPanelPresenter,
        LatestMessagePresenter, DasboardTabPresenter{
    private TextView tvUserName;
    private Button btTabList;
    private Button btTabMenu;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //recuperar o controle dos componentes visuais
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        btTabList = (Button) findViewById(R.id.btTabList);
        btTabMenu = (Button) findViewById(R.id.btTabMenu);
        btTabList.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                goToListAction();
            }
        });
        btTabMenu.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                goToMenuAction();
            }
        });
        //
        String userName = getIntent().getStringExtra("username");
        setUserName(userName);
        //
        Log.d("AGDebug", "Registrando o broadcast");
        IntentFilter intentFilter = new IntentFilter("ag.utacapp.UPDATE_LISTENER");
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
        broadcastManager.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //
                String name = intent.getStringExtra("name");
                String message = intent.getStringExtra("latestmessage");
                //
                Log.d("AGDebug", "Recebendo última mensagem");
                Log.d("AGDebug", "Name: " + name);
                Log.d("AGDebug", "Message: " + message);
                //
                //TODO adicionar item no listview
                //-- criar um item do listview
                //-- popular o item
                //-- adicionar no listview
                //-- remover o primeiro quando houver 3
            }
        }, intentFilter);
        //
        Log.d("AGDebug", "Solicitando inicialização do serviço");
        Intent intent = new Intent(this, UpdatingService.class);
        startService(intent);
    }

    @Override
    public void goToListAction() {
        //
        findViewById(R.id.tabList).setVisibility(View.VISIBLE);
        findViewById(R.id.tabMenu).setVisibility(View.GONE);
        //
        btTabList.setBackgroundResource(R.color.boxButtonActiveBackgroundColor);
        btTabMenu.setBackgroundResource(R.color.boxButtonInactiveBackgroundColor);
    }

    @Override
    public void goToMenuAction() {
        findViewById(R.id.tabList).setVisibility(View.GONE);
        findViewById(R.id.tabMenu).setVisibility(View.VISIBLE);
        //
        btTabList.setBackgroundResource(R.color.boxButtonInactiveBackgroundColor);
        btTabMenu.setBackgroundResource(R.color.boxButtonActiveBackgroundColor);
    }

    @Override
    public void setUserName(String name) {
        tvUserName.setText(name);
    }

    @Override
    public void setLatestMessage(String msg) {
        //adicionar uma mensagem o item da listagem
    }

    @Override
    public void goToChatAction() {
        //TODO abrir a tela de chat
    }
}
