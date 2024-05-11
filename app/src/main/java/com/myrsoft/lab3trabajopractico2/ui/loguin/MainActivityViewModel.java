package com.myrsoft.lab3trabajopractico2.ui.loguin;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.myrsoft.lab3trabajopractico2.modelo.Usuario;
import com.myrsoft.lab3trabajopractico2.request.ApiClient;
import com.myrsoft.lab3trabajopractico2.ui.registro.RegistroActivity;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public void login(String mail, String pass){
        Usuario usuario = ApiClient.login(context, mail, pass);
        if (usuario != null){
            Intent intent = new Intent(context, RegistroActivity.class);
            intent.putExtra("Registro", 0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }else {
            Toast.makeText(context, "Email y/o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
    public void registrar(){
        Intent intent = new Intent(context, RegistroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("Registro", 1);
        context.startActivity(intent);
    }
}
