/**
 * Exceção que será lançada quando o arquivo estiver corrompido, isto é, quando ele tiver mais linhas inválidas do que válidas
 */
public class ArquivoCorrompidoException extends Exception {

    private int quantLinhasInvalidas;

    /**
     * Construtor
     * @param message a mensagem da exceção
     * @param quantNotasInvalidas a quantidade de notas inválidas do arquivo corrompido
     */
    public ArquivoCorrompidoException(String message, int quantNotasInvalidas){
        super(message);
        this.quantLinhasInvalidas = quantNotasInvalidas;
    }

    /**
     * Getter
     * @return a quantidade de linhas inválidas do arquivo
     */
    public int getQuantLinhasInvalidas() {
        return this.quantLinhasInvalidas;
    }
}
