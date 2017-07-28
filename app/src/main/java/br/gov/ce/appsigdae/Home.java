package br.gov.ce.appsigdae;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.gov.ce.appsigdae.async.AsyncLoginHttpClient;
import br.gov.ce.appsigdae.entity.VoObras;
import cz.msebera.android.httpclient.Header;

public class Home extends AppCompatActivity {

    private Drawer drawer;
    private ViewPager viewPager;
    private static final long ID_ND_FOOTER = 500;
    private List<VoObras> listaVoObras = new ArrayList<VoObras>();
    private ListView listaObras;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // <editor-fold defaultstate="collapsed" desc=">>> Configurações do Toolbar">
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("SIGDAE Mobile");
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc=">>> Buscando as Preferências do App">
        SharedPreferences pref = getSharedPreferences("pref", 0);
        String nome = pref.getString("nome", null);
        String matricula = pref.getString("usuario", null);
        String nomeCompleto = pref.getString("nomeCompleto", null);
        String email = pref.getString("email", null);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc=">>> Adicionando Parametros para busca no WebService">
        RequestParams params = new RequestParams();
        params.put("usuario", matricula);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc=">>> Método para buscar obras do Fiscal no WebService">
        AsyncLoginHttpClient.post("voDadosObraRest/voDadosObrasFiscal?", params, new JsonHttpResponseHandler() {

            // called when response HTTP status is "200 OK"
            // Chamado quando o status HTTP de resposta é "200 OK"
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                final Gson gson = new Gson();

                Type type = new TypeToken<List<VoObras>>() {
                }.getType();
                listaVoObras = gson.fromJson(response.toString(), type);

                listaObras = (ListView) findViewById(R.id.listaObras);
                adapter = new ArrayAdapter(Home.this, android.R.layout.simple_list_item_1);
                setArrayAdapterObras();

                //listaObras.setOnItemClickListener(clickListenerObras);

                Integer qtdObra = listaVoObras.size();
                Toast toast = Toast.makeText(Home.this, "Encontrado " + qtdObra.toString() + " Obra(s)", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc=">>> Filtro Inativo">
        /* EditText filter = (EditText) findViewById(R.id.edtText);
        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String string = s.toString();
                if (!s.equals(string.toUpperCase())) {
                    string = string.toUpperCase();
                }
                adapter.getFilter().filter(string.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = s.toString();
                if (!s.equals(string.toUpperCase())) {
                    string = string.toUpperCase();
                }
                adapter.getFilter().filter(string.toString());
            }
        });*/
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc=">>> NavigationView">
        final PrimaryDrawerItem itemPerfil = new PrimaryDrawerItem()
                .withName("Perfil")
                .withIcon(GoogleMaterial.Icon.gmd_person);
        final PrimaryDrawerItem itemProdutos = new PrimaryDrawerItem()
                .withName("Obras")
                .withBadge("43")
                .withIcon(FontAwesome.Icon.faw_th_list)
                .withBadgeStyle(new BadgeStyle()
                        .withTextColor(Color.WHITE)
                        .withColorRes(R.color.md_orange_700));
        final PrimaryDrawerItem itemCompras = new PrimaryDrawerItem()
                .withName("Medições")
                .withBadge("2")
                .withIcon(GoogleMaterial.Icon.gmd_shop_two)
                .withBadgeStyle(new BadgeStyle()
                        .withTextColor(Color.WHITE)
                        .withColorRes(R.color.md_orange_700));
        // Create the AccountHeader / Criando o Cabeçalho
        AccountHeader drawerHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header1)
                .addProfiles(
                        new ProfileDrawerItem().withName(nome + " / " + matricula).withEmail(email)
                ).build();

        //create the drawer and remember the `Drawer` result object
        drawer = new DrawerBuilder()
                .withAccountHeader(drawerHeader)
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        new SecondaryDrawerItem().withName("Conta do Usuário"),
                        itemPerfil,
                        new SecondaryDrawerItem().withName("Ações do Sistema"),
                        itemProdutos,
                        itemCompras
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        configuraItensDrawer(position, drawerItem);
                        return true;
                    }
                })
                .build();
        drawer.addStickyFooterItem(new PrimaryDrawerItem().withName("Sobre o App")
                .withIcon(GoogleMaterial.Icon.gmd_info)
                .withIdentifier(ID_ND_FOOTER));
        // </editor-fold>
    }

    // <editor-fold defaultstate="collapsed" desc=">>> Métodos">
    private void setArrayAdapterObras() {
        List<String> valores = new ArrayList<String>();
        int qtdObra = 1;
        for (VoObras obras : listaVoObras) {
            valores.add(obras.getCodigoObra());
            qtdObra++;
        }
        adapter.clear();
        adapter.addAll(valores);
        listaObras.setAdapter(adapter);
    }

    private void configuraItensDrawer(int position, IDrawerItem drawerItem) {
        viewPager.setCurrentItem(position);
        switch ((int) drawerItem.getIdentifier()) {
            case (int) ID_ND_FOOTER:
                try {
                    PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
                    String versao = info.versionName;
                    Toast.makeText(this, "Versão" + versao, Toast.LENGTH_SHORT).show();
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
        drawer.closeDrawer();
    }
    // </editor-fold>


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
