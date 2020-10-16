/**
 * Exceção para o caso em que um usuário desconhecido tenta fazer um tuite
 */
public class UsuarioDesconhecidoException extends Exception {

    public UsuarioDesconhecidoException(String message) {
        super(message);
    }
}
