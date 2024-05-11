package com.myrsoft.lab3trabajopractico2.ui.registro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.myrsoft.lab3trabajopractico2.R;
import com.myrsoft.lab3trabajopractico2.databinding.ActivityRegistroBinding;
import com.myrsoft.lab3trabajopractico2.modelo.Usuario;

public class RegistroActivity extends AppCompatActivity {
private RegistroActivityViewModel vm;
private ActivityRegistroBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);
        Intent intent = getIntent();
        vm.getUsuarioMutable().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.etDni.setText(usuario.getDni() + "");
                binding.etApellido.setText(usuario.getApellido());
                binding.etNombre.setText(usuario.getNombre());
                binding.etMail.setText(usuario.getMail());
                binding.etPass.setText(usuario.getPassword());
            }
        });

        vm.leerUsuario(getIntent());
        binding.btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long dni = Long.parseLong(binding.etDni.getText().toString() + "");
                String apellido = binding.etApellido.getText().toString();
                String nombre = binding.etNombre.getText().toString();
                String email = binding.etMail.getText().toString();
                String password = binding.etPass.getText().toString();
                Usuario user = new Usuario(dni, apellido, nombre, email, password);
                vm.guardarUsuario(user);
            }
        });
    }
}