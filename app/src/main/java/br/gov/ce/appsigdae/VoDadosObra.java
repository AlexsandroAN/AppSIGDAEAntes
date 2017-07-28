package br.gov.ce.appsigdae;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import br.gov.ce.appsigdae.entity.VoObras;

public class VoDadosObra extends AppCompatActivity {

    private VoObras voObras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vo_dados_obra);

        voObras = (VoObras) getIntent().getExtras().getSerializable("obra");

        // <editor-fold defaultstate="collapsed" desc=">>> Configurações do Toolbar">
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(voObras.getCodigoObra());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        // </editor-fold>

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(this, Home.class);
        startActivity(i);
        finish();
        return super.onOptionsItemSelected(item);
    }
}
