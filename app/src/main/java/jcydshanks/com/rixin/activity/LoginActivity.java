package jcydshanks.com.rixin.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.EditText;
import android.widget.TextView;

import net.tsz.afinal.annotation.view.ViewInject;

import jcydshanks.com.rixin.R;


public class LoginActivity extends AppCompatActivity {

    @ViewInject(id = R.id.edit_user)EditText editUser;
    @ViewInject(id = R.id.passwd_et)EditText editPasswd;
    @ViewInject(id = R.id.login_btn,click = "OnClick")TextView login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.login_btn:
                break;
        }

    }
    private void Login(){

    }



}
