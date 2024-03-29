package br.gov.ce.appsigdae.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.Toast;


/**
 * Created by 39091 on 30/06/2016.
 */
public class Util {

    public static void showMsgToast(Activity activity, String msg) {
        // Retorna uma mensagem
        LayoutInflater inflater = activity.getLayoutInflater();
      //  View lytToast = inflater.inflate(R.layout R.layout.toast_template, (ViewGroup) activity.findViewById(R.id.lytToast));

      //  TextView txtTosat = (TextView) lytToast.findViewById(R.id.txtToast);
       // txtTosat.setText(msg);

        Toast toast = new Toast(activity);
       // toast.setView(lytToast);
        //toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showMsgSimpleToast(Activity activity, String txt) {
        Toast.makeText(activity, txt, Toast.LENGTH_SHORT).show();
    }

   public static void showMsgConfirm(final Activity activity, String titulo, String txt, TipoMsg tipoMsg, DialogInterface.OnClickListener listener) {
        int theme = 0, icone = 0;

        switch (tipoMsg) {
            case ERROR:
              ///  theme = R.style.AppTheme_Dark_Dialog_Error;
               // icone = R.drawable.error;
                break;
            case INFO:
              //  theme = R.style.AppTheme_Dark_Dialog_Info;
              //  icone = R.drawable.info;
                break;
            case SUCESSO:
              //  theme = R.style.AppTheme_Dark_Dialog_Sucesso;
              //  icone = R.drawable.ok;
                break;
            case ALERTA:
             //   theme = R.style.AppTheme_Dark_Dialog_Alerta;
              //  icone = R.drawable.alert;
                break;
        }

        final AlertDialog alertDialog = new AlertDialog.Builder(activity, theme).create();
        alertDialog.setTitle(titulo);
        alertDialog.setMessage(txt);
        alertDialog.setIcon(icone);

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", listener);
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.copyFrom(alertDialog.getWindow().getAttributes());
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        alertDialog.show();
        alertDialog.getWindow().setAttributes(params);
    }


    public static void showMsgAlertOK(final Activity activity, String titulo, String txt, TipoMsg tipoMsg) {
        int theme = 0, icone = 0;

        switch (tipoMsg) {
            case ERROR:
            //    theme = R.style.AppTheme_Dark_Dialog_Error;
            //    icone = R.drawable.error;
                break;
            case INFO:
              //  theme = R.style.AppTheme_Dark_Dialog_Info;
              //  icone = R.drawable.info;
                break;
            case SUCESSO:
              //  theme = R.style.AppTheme_Dark_Dialog_Sucesso;
             //   icone = R.drawable.ok;
                break;
            case ALERTA:
           //     theme = R.style.AppTheme_Dark_Dialog_Alerta;
           //     icone = R.drawable.alert;
                break;
        }

        final AlertDialog alertDialog = new AlertDialog.Builder(activity, theme).create();
        alertDialog.setTitle(titulo);
        alertDialog.setMessage(txt);
        alertDialog.setIcon(icone);

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Util.showMsgToast(activity, "Loja Virtual App v1.0");
                alertDialog.dismiss();
            }
        });

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.copyFrom(alertDialog.getWindow().getAttributes());
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        alertDialog.show();
        alertDialog.getWindow().setAttributes(params);
    }
}

