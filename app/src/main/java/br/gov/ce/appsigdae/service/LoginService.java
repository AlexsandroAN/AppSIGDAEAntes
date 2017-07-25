package br.gov.ce.appsigdae.service;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import br.gov.ce.appsigdae.entity.User;
import br.gov.ce.appsigdae.validation.LoginValidation;

/**
 * Created by 39091 on 04/07/2016.
 */
public class LoginService {

    private User user = new User();
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
    }

}
