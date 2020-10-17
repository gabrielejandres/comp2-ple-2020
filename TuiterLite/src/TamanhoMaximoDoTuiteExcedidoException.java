/**
 * Exceção para o caso em que o tuite excede o tamanho máximo permitido no TuiterLite
 */
public class TamanhoMaximoDoTuiteExcedidoException extends Exception {

    public TamanhoMaximoDoTuiteExcedidoException(String message) {
        super(message);
    }
}
