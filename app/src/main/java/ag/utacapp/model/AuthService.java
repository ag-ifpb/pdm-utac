package ag.utacapp.model;


/**
 * Representa um serviço de autenticação remoto
 */
public interface AuthService {
    String auth(String email, String password) throws AuthServiceException;
}
