package ag.utacapp.presenter;


/**
 * Representa a interação do usuário
 * com a visão (Login)
 */
public interface LoginPresenter {

    /**
     * Recupera o email digitado pelo usuário
     *
     * @return
     */
    String getEmail();

    /**
     * Recupera a senha digitada pelo usuário
     *
     * @return
     */
    String getPassword();

    /**
     * Executa a ação do acionamento do botão login
     *
     */
    void loginButtonClick();

    /**
     * Apresenta uma mensagem de erro para o usuário
     *
     * @param msg
     */
    void showMessageError(String msg);
}

