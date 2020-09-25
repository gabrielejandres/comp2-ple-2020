public class Produto {

    private static long controleId = 1;

    protected final long id;
    protected int pesoEmGramas;
    protected float precoEmReais;
    protected String categoria;
    protected int quantidadeEmEstoque;

    /**
     * Construtor
     * @param precoEmReais o preço em reais do produto
     */
    public Produto(float precoEmReais) {
        this.id = controleId++;
        this.precoEmReais = precoEmReais;
    }

    /**
     * Getter
     * @return o id do produto
     */
    public long getId() {
        return id;
    }

    /**
     * Getter
     * @return o peso em gramas do produto
     */
    public int getPesoEmGramas() {
        return pesoEmGramas;
    }

    /**
     * Setter
     * @param pesoEmGramas o peso em gramas do produto
     */
    public void setPesoEmGramas(int pesoEmGramas) {
        this.pesoEmGramas = pesoEmGramas;
    }

    /**
     * Getter
     * @return o preço em reais do produto
     */
    public float getPrecoEmReais() {
        return precoEmReais;
    }

    /**
     * Setter
     * @param precoEmReais o preço em reais do produto
     */
    public void setPrecoEmReais(int precoEmReais) {
        this.precoEmReais = precoEmReais;
    }

    /**
     * Getter
     * @return a categoria do produto
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Setter
     * @param categoria a categoria do produto
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Getter
     * @return a quantidade total deste produto no estoque
     */
    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    /**
     * Setter
     * @param quantidadeEmEstoque a quantidade em estoque dste produto
     */
    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    /**
     * Exibir informações essenciais do produto
     * @return uma string com as informações essenciais
     */
    @Override
    public String toString() {
        return "Informações gerais do Produto: " +
                "\nPreço unitário = R$" + precoEmReais;
    }

    /**
     * Comparar se dois produtos são iguais, de acordo com o id
     * @param o um produto
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return id == produto.id;
    }

}
