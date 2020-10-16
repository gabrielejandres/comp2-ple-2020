/**
 * Exceção para o caso em que o tuite excede o tamanho máximo permitido no TuiterLite
 */
public class TamanhoMaximoExcedidoException extends Exception {

    public TamanhoMaximoExcedidoException(String message) {
        super(message);
    }
}
