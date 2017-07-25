package br.gov.ce.appsigdae;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Configurações do Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("SIGDAE Mobile");


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        SharedPreferences.Editor editor;
        switch (item.getItemId()) {
            case R.id.sair:
                editor = getSharedPreferences("pref", Context.MODE_PRIVATE).edit();
                editor.remove("usuario");
                editor.remove("senha");
                editor.clear();
                finish();
                break;
            case R.id.logout:
                editor = getSharedPreferences("pref", Context.MODE_PRIVATE).edit();
                editor.remove("usuario");
                editor.remove("senha");
                editor.commit();

                // Chamar a Tela de Login
                intent = new Intent(this, Login.class);
                startActivity(intent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
