import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LojaVirtualTest {

    private Livro livro1;
    private Livro livro2;
    private Roupa roupa1;
    private Roupa roupa2;
    private LojaVirtual loja;

    @Before
    public void setUp() {
        livro1 = new Livro(40,"Um Caso Perdido", "Coollen Hoover", 2012, 404);
        roupa1 = new Roupa(85,"Vestido", 'M');

        livro2 = new Livro(40,"Em busca de Cinderela", "Coollen Hoover", 2012, 404);
        roupa2 = new Roupa(85,"Calça jeans", 'G');

        loja = new LojaVirtual("Evolution Store");
    }

    /**
     * Testa a inclusão de alguns produtos no estoque e a incrementação da quantidade de produtos no estoque
     */
    @Test
    public void testarInclusaoProdutoNoEstoque() {
        loja.incluirProdutoNoEstoque(livro1, 1);
        assertEquals("O estoque da loja deve ser 1 após a inclusão de 1 produto", 1, loja.getTamanhoEstoque());
        assertEquals("O estoque do produto deve ser 1 após a inclusão de 1 produto",1, loja.getTamanhoEstoque(livro1));

        loja.incluirProdutoNoEstoque(roupa1, 1);
        assertEquals("O estoque da loja deve ser 2 após a inclusão de mais 1 produto",2, loja.getTamanhoEstoque());
        assertEquals("O estoque do produto deve ser 1 após a inclusão de 1 produto",1, loja.getTamanhoEstoque(roupa1));

        loja.incluirProdutoNoEstoque(roupa1, 5);
        assertEquals("O estoque da loja deve ser 7 após a inclusão de mais 5 produtos",7, loja.getTamanhoEstoque());
        assertEquals("O estoque do produto deve ser 6 após a inclusão de mais 5 produtos",6, loja.getTamanhoEstoque(roupa1));

        loja.incluirProdutoNoEstoque(livro2, 3);
        assertEquals("O estoque da loja deve ser 10 após a inclusão de mais 3 produtos",10, loja.getTamanhoEstoque());
        assertEquals("O estoque do produto deve ser 3 após a inclusão de 3 produtos",3, loja.getTamanhoEstoque(livro2));
    }

    /**
     * Testa a venda de um livro em uma quantidade maior do que há no estoque
     */
    @Test
    public void testarVendaDeLivroSemSucesso() {
        loja.incluirProdutoNoEstoque(livro2, 1);
        assertEquals("Se não houver quantidade suficiente, a venda desse livro não pode ser feita","", loja.efetuarVenda(livro1, 3));
    }

    /**
     * Testa a venda de uma roupa em uma quantidade maior do que há no estoque
     */
    @Test
    public void testarVendaDeRoupaSemSucesso() {
        loja.incluirProdutoNoEstoque(roupa1, 1);
        assertEquals("Se não houver quantidade suficiente, a venda dessa roupa não pode ser feita","", loja.efetuarVenda(roupa1, 3));
    }

    /**
     * Testa a venda de uma roupa em uma quantidade menor ou igual a que está no estoque
     */
    @Test
    public void testarVendaDeRoupaComSucesso() {
        loja.incluirProdutoNoEstoque(roupa2, 2);

        String reciboEsperado = "Informações gerais do Produto: " +
                "\nPreço unitário = R$85.0" +
                "\nInformações particulares desta Roupa: " +
                "\nNome da peça = Calça jeans" +
                "\nTamanho = G" +
                "\nInformações da venda: " +
                "\nTotal = R$170.0";

        assertEquals("A venda deve ser efetuada, se houver quantidade, e o recibo deve ser retornado",reciboEsperado, loja.efetuarVenda(roupa2, 2));
        assertEquals(170, loja.getTotalValorVendas(), 0);
    }

    /**
     * Testa a venda de um livro em uma quantidade menor ou igual a que está no estoque
     */
    @Test
    public void testarVendaDeLivroComSucesso() {
        loja.incluirProdutoNoEstoque(livro2, 1);

        String reciboEsperado = "Informações gerais do Produto: " +
                "\nPreço unitário = R$40.0" +
                "\nInformações particulares deste Livro: " +
                "\nTítulo = Em busca de Cinderela" +
                "\nAutor = Coollen Hoover" +
                "\nAno de Publicação = 2012" +
                "\nNúmero de páginas = 404" +
                "\nInformações da venda: " +
                "\nTotal = R$40.0";

        assertEquals("A venda deve ser efetuada, se houver quantidade, e o recibo deve ser retornado",reciboEsperado, loja.efetuarVenda(livro2, 1));
        assertEquals(40, loja.getTotalValorVendas(), 0);
    }

    /**
     * Testa a venda de vários produtos
     */
    @Test
    public void testarVendaDeVariosProdutos() {
        loja.incluirProdutoNoEstoque(livro1, 2);
        loja.incluirProdutoNoEstoque(livro2, 1);
        loja.incluirProdutoNoEstoque(roupa1, 15);

        loja.efetuarVenda(livro2, 1);
        assertEquals(40, loja.getTotalValorVendas(), 0);

        loja.efetuarVenda(livro1, 2);
        assertEquals(120, loja.getTotalValorVendas(), 0);

        loja.efetuarVenda(roupa1, 2);
        assertEquals(290, loja.getTotalValorVendas(), 0);
    }

}