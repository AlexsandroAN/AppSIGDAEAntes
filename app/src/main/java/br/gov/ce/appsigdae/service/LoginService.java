package br.gov.ce.appsigdae.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import br.gov.ce.appsigdae.Home;
import br.gov.ce.appsigdae.async.AsyncLoginHttpClient;
import br.gov.ce.appsigdae.entity.User;
import br.gov.ce.appsigdae.util.Util;
import br.gov.ce.appsigdae.validation.LoginValidation;
import cz.msebera.android.httpclient.Header;

/**
 * Created by 39091 on 04/07/2016.
 */
public class LoginService {

    private List<User> listaUser = new ArrayList<User>();

    public void validarCamposLogin(final LoginValidation validation) {
        final Activity activity = validation.getActivity();
        boolean resultado = true;
        if (validation.getUsuario() == null || "".equals(validation.getUsuario())) {
            validation.getEdtUsuario().setError("Campo obrigatorio");
            resultado = false;
        }
        if (validation.getSenha() == null || "".equals(validation.getSenha())) {
            validation.getEdtSenha().setError("Campo obrigatorio");
            resultado = false;
        }
        if (resultado) {
            RequestParams params = new RequestParams();
            params.put("usuario", validation.getUsuario());
            params.put("senha", validation.getSenha());

            AsyncLoginHttpClient.post("user/login?", params, new JsonHttpResponseHandler() {

                @Override
                public void onFailure(int statusCode, Header[] headers, String resultado, Throwable throwable) {
                    Log.e(LoginService.class.getName(), "Erro no login do usuario! Http Code:" + statusCode, throwable);
                    Util.showMsgSimpleToast(activity, "Erro no login do usuario!");
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                    Gson gson = new Gson();

                    Type type = new TypeToken<List<User>>() {
                    }.getType();

                    listaUser = gson.fromJson(response.toString(), type);

                    if (!listaUser.isEmpty()) {
                        SharedPreferences.Editor editor = activity.getSharedPreferences("pref", Context.MODE_PRIVATE).edit();
                        editor.putString("usuario", validation.getUsuario());
                        editor.putString("senha", validation.getSenha());
                        editor.putString("nome", listaUser.get(0).getFirstname());
                        editor.putString("nomeCompleto", listaUser.get(0).getNomeCompleto());
                        editor.putString("email", listaUser.get(0).getEmail());
                        editor.putString("logado", "sim");
                        editor.commit();

                        // Chamar a Tela de Home
                        Intent intent = new Intent(activity, Home.class);
                        activity.startActivity(intent);
                        activity.finish();
                    } else {
                        Util.showMsgSimpleToast(activity, "Login/Senha Invalidos!");
                    }
                }
            });
        }
    }
}
