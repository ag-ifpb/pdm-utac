package ag.utacapp.infra;


import android.util.Log;

import ag.utacapp.model.AuthService;
import ag.utacapp.model.AuthServiceException;

public class AuthServiceImpl implements AuthService{

    @Override
    public String auth(String email, String password) throws AuthServiceException {
        //TODO resolver com autenticação remota
        if ("ari@email.net".equals(email) && "123456".equals(password)){
            Log.d("AGDebug", "Login executado com sucesso");
            return "Ari Garcia";
        } else {
            throw new AuthServiceException("Usuário desconhecido");
        }

    }

}
