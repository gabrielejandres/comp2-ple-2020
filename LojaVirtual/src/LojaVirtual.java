import java.util.ArrayList;

public class LojaVirtual {

    // o nome desta loja
    private String nome;

    // valor total das vendas desta loja
    private static double totalValorVendas;

    // a quantidade total de itens no estoque
    private int tamanhoEstoque = 0;

    // arraylist de produtos no estoque desta loja
    private ArrayList<Produto> produtos;

    /**
     * Construtor da loja
     * @param nome o nome desta loja
     */
    public LojaVirtual(String nome) {
        this.nome = nome;
        produtos = new ArrayList<>();
    }

    /**
     * Inclui um produto no estoque da loja
     * @param produto o produto a ser incluído
     * @param quantidade a quantidade que será adicionada ao estoque
     */
    public void incluirProdutoNoEstoque(Produto produto, int quantidade) {
        boolean temProduto = this.verificarExistenciaProdutoNoEstoque(produto);

        if (!temProduto) {
            produtos.add(produto);
        }
        this.atualizarEstoque(produto, quantidade);
    }

    /**
     * Efetua a venda de uma determinada quantidade de um produto
     * @param produto o produto a ser vendido
     * @param quantidade a quantidade desse produto
     * @return um recibo dessa venda
     */
    public String efetuarVenda(Produto produto, int quantidade) {
        boolean temProduto = this.verificarExistenciaProdutoNoEstoque(produto);

        String recibo = "";

        if (temProduto) {
            boolean quantidadeSuficiente = produto.getQuantidadeEmEstoque() >= quantidade;

            if(quantidadeSuficiente) {
                float totalVenda = produto.getPrecoEmReais() * quantidade;

                if (this.receberPagamento(totalVenda)) {
                    this.atualizarEstoque(produto, -quantidade);

                    recibo += produto.toString();
                    recibo += "\nInformações da venda: " + "\nTotal = R$" + totalVenda;

                    adicionarValorVendaAoTotal(totalVenda);
                }
            }
        }

        return recibo;
    }

    private static void adicionarValorVendaAoTotal(float totalVenda) {
        totalValorVendas += totalVenda;
    }

    /**
     * Getter
     * @return a quantidade total de itens no estoque
     */
    public int getTamanhoEstoque() {
        return this.tamanhoEstoque;
    }

    /**
     * Getter
     * @return a quantidade total de um determinado produto no estoque
     */
    public int getTamanhoEstoque(Produto produto) {
        return produto.quantidadeEmEstoque;
    }

    /**
     * Getter
     * @return o valor total das vendas desta loja
     */
    public static double getTotalValorVendas() {
        return totalValorVendas;
    }

    /**
     * Método auxiliar para receber pagamento
     * @param valor valor total da venda
     * @return
     */
    private boolean receberPagamento(float valor) {
        totalValorVendas += valor;
        return true;
    }

    /**
     * Método auxiliar para atualizar o estoque do produto
     * @param produto o produto que terá sua quantidade modificada
     * @param quantidade a quantidade do produto que será retirada (se for negativa) ou adicionada (se for positiva) no estoque
     */
    private void atualizarEstoque(Produto produto, int quantidade) {
        int quantAtual = produto.getQuantidadeEmEstoque();
        produto.setQuantidadeEmEstoque(quantAtual + quantidade);
        tamanhoEstoque += quantidade;
    }

    /**
     * Método auxiliar para verificar se o produto existe no estoque
     * @param produto o produto que deseja-se saber se existe no estoque
     * @return um booleano que indica se o produto está ou não no estoque
     */
    private boolean verificarExistenciaProdutoNoEstoque(Produto produto) {
        for (Produto prod : produtos) {
            if(prod.equals(produto)) {
                return true;
            }
        }
        return false;
    }
}
