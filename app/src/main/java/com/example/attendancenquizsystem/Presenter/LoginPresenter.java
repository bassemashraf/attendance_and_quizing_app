package com.example.attendancenquizsystem.Presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.attendancenquizsystem.View.LoginView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter {
    LoginView myview;
    Context mycontext;
    private FirebaseAuth mAuth;
   public LoginPresenter(LoginView view, Context cx){
        myview=view;
        mycontext=cx;
        mAuth = FirebaseAuth.getInstance();
    }
    public void login(String email,String password){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                    myview.loginsucessful();
                else{
                    myview.loginfailed();
                }
            }
        });
    }
}
