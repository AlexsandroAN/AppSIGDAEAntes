package br.gov.ce.appsigdae.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import br.gov.ce.appsigdae.entity.Obra;

/**
 * Created by 39091 on 02/08/2017.
 */

public class BancoController {

    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context) {
        banco = new CriaBanco(context);
    }

    public void inserirObra(Obra obra) {
        db = banco.getWritableDatabase();
        ContentValues contentValues = getContentValuesObra(obra);

        db.insert("OBRA", null, contentValues);

        db.close();
    }

    @NonNull
    private ContentValues getContentValuesObra(Obra obra) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("CODIGO", obra.getCodigoObra());
        contentValues.put("DESCRICAO", obra.getDescricaoObra());
        contentValues.put("MUNICIPIO", obra.getMunicipio());
        contentValues.put("STATUS", obra.getStatusObra());
        contentValues.put("CONTRATANTE", obra.getContratanteNomeFantasia());
        contentValues.put("VALOR_ATUAL", obra.getValorAtual().toString());
        contentValues.put("TOTAL_EXECUTADO", obra.getTotalExecutado().toString());
        contentValues.put("SALDO_A_MEDIR", obra.getSaldoAMedir().toString());
        contentValues.put("CONTRATADA", obra.getContratadaNomeFantasia());
        return contentValues;
    }

}
