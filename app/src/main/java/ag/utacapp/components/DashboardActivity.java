package ag.utacapp.components;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
