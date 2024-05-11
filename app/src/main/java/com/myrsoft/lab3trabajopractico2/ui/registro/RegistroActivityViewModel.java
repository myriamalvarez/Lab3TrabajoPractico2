package com.myrsoft.lab3trabajopractico2.ui.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.myrsoft.lab3trabajopractico2.modelo.Usuario;
import com.myrsoft.lab3trabajopractico2.request.ApiClient;
import com.myrsoft.lab3trabajopractico2.ui.loguin.MainActivity;

public class RegistroActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> usuarioMutable;
    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public LiveData<Usuario> getUsuarioMutable(){
        if (usuarioMutable == null){
            usuarioMutable = new MutableLiveData<>();
        }
        return usuarioMutable;
    }
    public void guardarUsuario(Usuario user){
        ApiClient.guardar(context, user);
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    public void leerUsuario(Intent intent){
        if (intent.getIntExtra("Registro", -1) == 0){
            Usuario user = ApiClient.leer(context);
            if (user.getApellido() != null){
                usuarioMutable.setValue(user);
            }
        }
    }
}
