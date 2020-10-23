/**
 * Exceção que será lançada quando o arquivo estiver corrompido, isto é, quando ele tiver mais linhas inválidas do que válidas
 */
public class ArquivoCorrompidoException extends Exception {

    private int quantLinhasInvalidas;

    public ArquivoCorrompidoException(String message, int quantNotasInvalidas){
        super(message);
        this.quantLinhasInvalidas = quantNotasInvalidas;
    }

    public int getQuantLinhasInvalidas() {
        return quantLinhasInvalidas;
    }
}
