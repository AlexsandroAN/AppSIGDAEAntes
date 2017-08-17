package br.gov.ce.appsigdae.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import br.gov.ce.appsigdae.R;
import br.gov.ce.appsigdae.entity.MedicaoObra;

/**
 * Created by 39091 on 10/04/2017.
 */

public class MedicaoRecyclerAdapter extends RecyclerView.Adapter<MedicaoRecyclerAdapter.ViewHolder> {

    private final List<MedicaoObra> medicaoObras;

    public MedicaoRecyclerAdapter(final List<MedicaoObra> medicaoObras) {
        this.medicaoObras = medicaoObras;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nrMedicao, dataInicio, dataFim, valorMedido, status;
        Button btnDetalhes;

        public ViewHolder(final View itemView) {
            super(itemView);
            nrMedicao = (TextView) itemView.findViewById(R.id.nrMedicao);
            dataInicio = (TextView) itemView.findViewById(R.id.dataInicio);
            dataFim = (TextView) itemView.findViewById(R.id.dataFim);
            valorMedido = (TextView) itemView.findViewById(R.id.valorMedido);
            status = (TextView) itemView.findViewById(R.id.status);

            //btnDetalhes = (Button) itemView.findViewById(R.id.btnDetalhes);
       /*     btnDetalhes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Card Clicado!", Toast.LENGTH_SHORT).show();
                }
            });*/
        }
    }

    @Override
    public MedicaoRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.linha_medicao, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MedicaoRecyclerAdapter.ViewHolder medicaoViewHolder, int position) {
        MedicaoObra medicao = medicaoObras.get(position);
        medicaoViewHolder.nrMedicao.setText(medicao.getNrMedicao());

        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");

        medicaoViewHolder.dataInicio.setText(f.format(medicao.getDataInicio()));
        medicaoViewHolder.dataFim.setText(f.format(medicao.getDataFim()));

        NumberFormat format = NumberFormat.getCurrencyInstance();
        medicaoViewHolder.valorMedido.setText(format.format(medicao.getValorMedido()));
        medicaoViewHolder.status.setText(medicao.getStatus());

      /*  Picasso.with(medicaoViewHolder.imgProduto.getContext())
                .load(Constantes.URL_WEB_BASE + produto.getUrlImg())
                .into(produtoViewHolder.imgProduto);*/
    }

    @Override
    public int getItemCount() {
        return medicaoObras.size();
    }
}
