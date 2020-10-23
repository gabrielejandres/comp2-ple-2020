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
