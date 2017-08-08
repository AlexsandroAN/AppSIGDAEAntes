package br.gov.ce.appsigdae;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import br.gov.ce.appsigdae.adapter.ViewPagerAdapter;
import br.gov.ce.appsigdae.entity.Obra;
import br.gov.ce.appsigdae.fragment.FragmentMedicoes;
import br.gov.ce.appsigdae.fragment.FragmentObra;
import br.gov.ce.appsigdae.repository.CriaBanco;

public class VoDadosObra extends AppCompatActivity {

    private Obra obra;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentObra fragmentObra;
    private FragmentMedicoes fragmentMedicoes;
    private CriaBanco dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vo_dados_obra);

        obra = (Obra) getIntent().getExtras().getSerializable("obra");

        // <editor-fold defaultstate="collapsed" desc=">>> Configurações do Toolbar">
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(obra.getCodigoObra());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        // </editor-fold>

        viewPager = (android.support.v4.view.ViewPager) findViewById(R.id.viewPager);
        configurarViewPager(viewPager);
        enviaDadosParaOFragment(fragmentObra, obra);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void configurarViewPager(ViewPager viewPager) {
        fragmentObra = new FragmentObra();
        fragmentMedicoes = new FragmentMedicoes();
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(fragmentObra, "Dados");
        viewPagerAdapter.addFragment(fragmentMedicoes, "Medições");
        viewPager.setAdapter(viewPagerAdapter);
    }

    //Envia dados para o fragment
    public void enviaDadosParaOFragment(Fragment fragment, Obra obra) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("obra", obra);
        fragment.setArguments(bundle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(this, Home.class);
        startActivity(i);
        finish();
        return super.onOptionsItemSelected(item);
    }
}
