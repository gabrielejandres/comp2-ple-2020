public class Roupa extends Produto {

    private String nomeDaPeca;
    private final char tamanho;
    private String cor;

    /**
     * Construtor
     * @param precoEmReais o preço desta roupa
     * @param nomeDaPeca qual peça de roupa esta roupa é
     * @param tamanho o tamanho desta roupa
     */
    public Roupa(float precoEmReais, String nomeDaPeca, char tamanho) {
        super(precoEmReais);
        this.nomeDaPeca = nomeDaPeca;
        this.tamanho = tamanho;

        // a categoria de qualquer roupa deve ser automaticamente "Vestuário"
        super.setCategoria("Vestuário");
    }

    // Getters e setters
    public int getTamanho() {
        return tamanho;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    /**
     * Exibir informações essenciais da roupa
     * @return uma string com as informações essenciais da roupa
     */
    @Override
    public String toString() {
        return super.toString() +
                "\nInformações particulares desta Roupa: " +
                "\nNome da peça = " + this.nomeDaPeca +
                "\nTamanho = " + this.tamanho;
    }
}
