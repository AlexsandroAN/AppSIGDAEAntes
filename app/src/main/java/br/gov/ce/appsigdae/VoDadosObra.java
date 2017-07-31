package br.gov.ce.appsigdae;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

import br.gov.ce.appsigdae.entity.VoObras;

public class VoDadosObra extends AppCompatActivity {

    private VoObras voObras;
    private TextView descricaoObra, vlAtual, vlMedido, vlSaldo;

    // Formatando BIgDecimal
    Locale ptBr = new Locale("pt", "BR");
    NumberFormat formato = NumberFormat.getCurrencyInstance(ptBr);

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

        voObras = (VoObras) getIntent().getExtras().getSerializable("obra");
        descricaoObra = (TextView) findViewById(R.id.descricaoObra);
        vlAtual = (TextView) findViewById(R.id.vlAtual);
        vlMedido = (TextView) findViewById(R.id.vlMedido);
        vlSaldo = (TextView) findViewById(R.id.vlSaldo);


        // Setando Valores
        descricaoObra.setText(voObras.getDescricaoObra());
        vlAtual.setText(formato.format(voObras.getValorAtual()));
        vlMedido.setText(formato.format(voObras.getTotalExecutado()));
        vlSaldo.setText(formato.format(voObras.getSaldoAMedir()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(this, Home.class);
        startActivity(i);
        finish();
        return super.onOptionsItemSelected(item);
    }
}
