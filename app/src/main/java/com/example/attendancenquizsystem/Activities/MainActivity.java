package com.example.attendancenquizsystem.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attendancenquizsystem.Presenter.LoginPresenter;
import com.example.attendancenquizsystem.R;
import com.example.attendancenquizsystem.View.LoginView;

public class MainActivity extends AppCompatActivity implements LoginView {
    TextView register;
    EditText email;
    EditText password;
    LoginPresenter presenter;
    //test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register=findViewById(R.id.register);
        email=findViewById(R.id.login_email);
        password=findViewById(R.id.login_password);
        presenter=new LoginPresenter(this,this);
    }
    protected void onStart(){
        super.onStart();
        String text="Don't have an account?Register";
        ForegroundColorSpan foregroundColorSpan=new ForegroundColorSpan(getResources().getColor(R.color.logo));
        ClickableSpan clickableSpan= new ClickableSpan() {
            @Override
            public void onClick(View view) {
                //Intent x = new Intent(MainActivity.this,Register.class);
                //startActivity(x);
            }
        };
        SpannableString spannableString=new SpannableString(text);
        spannableString.setSpan(foregroundColorSpan,22,30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(clickableSpan,22,30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        register.setText(spannableString);
        register.setMovementMethod(new LinkMovementMethod());

    }
    protected void onResume(){
        super.onResume();


    }

    @Override
    public void loginsucessful() {
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginfailed() {
        Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
    }
    public void login(View v){
        if((!(TextUtils.isEmpty(email.getText().toString())&&TextUtils.isEmpty(password.getText().toString()))))
            presenter.login(email.getText().toString(),password.getText().toString());
    }
}
