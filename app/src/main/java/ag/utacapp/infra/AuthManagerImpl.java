package ag.utacapp.infra;


import java.util.HashMap;
import java.util.Map;

import ag.utacapp.model.AuthManager;
import ag.utacapp.model.AuthServiceException;

public class AuthManagerImpl implements AuthManager {
    private static Map<String, String> rep = new HashMap<>();

    static {
        rep.put("aristofanio@hotmail.com", "123456");
        rep.put("natarajan@gmail.com", "123456");
        rep.put("rodrigobento@gmail.com", "123456");
        rep.put("juanpabloescobar@gmail.com", "123456");
    }

    @Override
    public String auth(String email, String password) throws AuthServiceException {
        String pass = rep.get(email);
        if (pass != null && pass.equals(password)){
            return email.split("@")[0];//todo: capitalizar
        } else {
            throw new AuthServiceException("Usuário desconhecido");
        }
    }

}
