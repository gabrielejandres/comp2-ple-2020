public class Livro extends Produto {

    private final String titulo;
    private final String autor;
    private final int anoDePublicacao;
    private final int numeroDePaginas;

    /**
     * Construtor
     * @param precoEmReais o preço deste livro
     * @param titulo o título deste livro
     * @param autor o autor deste livro
     * @param anoDePublicacao o ano de publicação deste livro
     * @param numeroDePaginas o número de paginas deste livro
     */
    public Livro(float precoEmReais, String titulo, String autor, int anoDePublicacao, int numeroDePaginas) {
        super(precoEmReais);
        this.titulo = titulo;
        this.autor = autor;
        this.anoDePublicacao = anoDePublicacao;
        this.numeroDePaginas = numeroDePaginas;

        // a categoria de qualquer livro1 deve ser automaticamente "Publicações"
        super.setCategoria("Publicações");
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnoDePublicacao() {
        return anoDePublicacao;
    }

    public int getNumeroDePaginas() {
        return numeroDePaginas;
    }

    /**
     * Exibir informações essenciais do livro
     * @return uma string com as informações essenciais do livro
     */
    @Override
    public String toString() {
        return super.toString() +
                "\nInformações particulares deste Livro: " +
                "\nTítulo = " + titulo +
                "\nAutor = " + autor +
                "\nAno de Publicação = " + anoDePublicacao +
                "\nNúmero de páginas = " + numeroDePaginas;
    }
}
