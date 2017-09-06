package br.gov.ce.appsigdae.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.gov.ce.appsigdae.R;
import br.gov.ce.appsigdae.adapter.MedicaoRecyclerAdapter;
import br.gov.ce.appsigdae.async.AsyncLoginHttpClient;
import br.gov.ce.appsigdae.entity.MedicaoObra;
import br.gov.ce.appsigdae.entity.Obra;
import cz.msebera.android.httpclient.Header;

public class FragmentMedicoes extends Fragment {

    private RecyclerView rv  ;
    private String codigoObra;
    private List<MedicaoObra> listaMedicaoObra = new ArrayList<MedicaoObra>();
    private Obra obra;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Intent i = getActivity().getIntent();
        obra = (Obra) i.getSerializableExtra("obra");
        View viewRoot = inflater.inflate(R.layout.fragment_medicoes, container, false);

        final RelativeLayout lytLoading = (RelativeLayout) viewRoot.findViewById(R.id.lytLoadingMedicoes);
        lytLoading.setVisibility(View.VISIBLE);

        rv = (RecyclerView) viewRoot.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        // <editor-fold defaultstate="collapsed" desc=">>> Adicionando Parametros para busca no WebService">
        RequestParams params = new RequestParams();
        params.put("codigoObra", obra.getCodigoObra());

        AsyncLoginHttpClient.post("voDadosObraRest/voDadosMedicaoObra?", params, new JsonHttpResponseHandler() {
            // Chamado quando o status HTTP de resposta é "200 OK"
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                if (response != null) {
                    Type type = new TypeToken<List<MedicaoObra>>() {
                    }.getType();
                    listaMedicaoObra = new Gson().fromJson(response.toString(), type);

                    MedicaoRecyclerAdapter adapter = new MedicaoRecyclerAdapter(listaMedicaoObra);
                    rv.setAdapter(adapter);
                } else {
                    Toast.makeText(getActivity(), "Houve um erro no carregamento da lista de profissões...", Toast.LENGTH_SHORT).show();
                }
                lytLoading.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(getActivity(), "Falha: " + errorResponse, Toast.LENGTH_SHORT).show();
            }
        });

        return viewRoot;
    }


}
