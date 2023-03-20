package com.ahmfarisi.footballplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UbahActivity extends AppCompatActivity {

    private EditText etNama, etNomor, etKlub;
    private Button btnubah;
    MyDatabaseHelper myDB = new MyDatabaseHelper(UbahActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        etNama = findViewById(R.id.et_nama);
        etNomor = findViewById(R.id.et_nomor);
        etKlub = findViewById(R.id.et_klub);
        btnubah = findViewById(R.id.btn_ubah);

        Intent varinten= getIntent();
        String id= varinten.getStringExtra("varID");
        String nama= varinten.getStringExtra("varnama");
        String nomor= varinten.getStringExtra("varnomor");
        String klub= varinten.getStringExtra("varklub");

        etNama.setText(nama);
        etNomor.setText(nomor);
        etKlub.setText(klub);

        btnubah.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {

                String getNama,getNomor,getKlub;

                getNama= etNama.getText().toString();
                getNomor= etNomor.getText().toString();
                getKlub= etKlub.getText().toString();




                if(nama.trim().equals("")){
                    etNama.setError("Nama Player Tidak Boleh Kosong");
                }
                else if(nomor.trim().equals("")){
                    etNomor.setError("Nomor Punggung Tidak Boleh Kosong");
                }
                else if(klub.trim().equals("")){
                    etKlub.setError("Klub Tidak Boleh Kosong");
                }
                else{
                    long eks = myDB.ubahplayer(id,getNama, getNomor, getKlub);
                    if(eks == -1){
                        Toast.makeText(UbahActivity.this, "Gagal Menambah Data", Toast.LENGTH_SHORT).show();
                        etNama.requestFocus();
                    }
                    else{
                        Toast.makeText(UbahActivity.this, "Berhasil Menambah Data", Toast.LENGTH_SHORT).show();
                        finish();
                    }
            }}
        });

    }
}