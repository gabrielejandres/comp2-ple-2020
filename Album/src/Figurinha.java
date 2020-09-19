public class Figurinha {

    private int posicao;
    private String urlImagem;

    /**
     * Construtor
     * @param posicao a posição que esta figurinha ocupa no álbum
     * @param urlImagem a url da imagem desta figurinha
     */
    public Figurinha(int posicao, String urlImagem) {
        this.posicao = posicao;
        this.urlImagem = urlImagem;
    }

    /**
     * Indica a posição, no álbum, que esta figurinha deve ocupar.
     * @return Um int dizendo a posição da figurinha
     */
    public int getPosicao() {
        return this.posicao;
    }

    /**
     * Retorna a URL de onde a imagem associada a esta figurinha deverá ser baixada.
     * @return uma String com o endereço desejado
     */
    public String getUrlImagem() {
        return this.urlImagem;
    }
}
