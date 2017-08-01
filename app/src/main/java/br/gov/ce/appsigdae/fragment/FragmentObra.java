package br.gov.ce.appsigdae.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

import br.gov.ce.appsigdae.R;
import br.gov.ce.appsigdae.entity.VoObras;

public class FragmentObra extends Fragment {

    private TextView descricaoObra, vlAtual, vlMedido, vlSaldo;
    private VoObras voObras;

    // Formatando BIgDecimal
    Locale ptBr = new Locale("pt", "BR");
    NumberFormat formato = NumberFormat.getCurrencyInstance(ptBr);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intent i = getActivity().getIntent();
        voObras = (VoObras) i.getSerializableExtra("obra");
        View view = inflater.inflate(R.layout.fragment_obra, container, false);

        // <editor-fold defaultstate="collapsed" desc=">>> Setando Valores">
        descricaoObra = (TextView) view.findViewById(R.id.descricaoObra);
        vlAtual = (TextView) view.findViewById(R.id.vlAtual);
        vlMedido = (TextView) view.findViewById(R.id.vlMedido);
        vlSaldo = (TextView) view.findViewById(R.id.vlSaldo);
        //
        descricaoObra.setText(voObras.getDescricaoObra());
        vlAtual.setText(formato.format(voObras.getValorAtual()));
        vlMedido.setText(formato.format(voObras.getTotalExecutado()));
        vlSaldo.setText(formato.format(voObras.getSaldoAMedir()));
        // </editor-fold>

        return view;
    }

}
