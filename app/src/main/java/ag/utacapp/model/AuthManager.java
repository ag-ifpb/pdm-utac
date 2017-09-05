package ag.utacapp.model;


import ag.utacapp.model.AuthServiceException;

/**
 * Representa um serviço de autenticação remoto
 */
public interface AuthManager {
    String auth(String email, String password) throws AuthServiceException;
}
