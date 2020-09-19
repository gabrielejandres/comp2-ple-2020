public class Album {

    public static final float PREENCHIMENTO_MINIMO_PARA_PERMITIR_AUTO_COMPLETAR = 0.9f;  // 90%

    // array de figurinhas que armazena as figurinhas desse álbum
    private Figurinha[] figurinhas;

    // array de inteiros que armazena quantas figurinhas repetidas existem de acordo com sua posição
    private int[] figurinhasRepetidas;

    // quantidade total de figurinhas repetidas
    private int quantFigurinhasRepetidas;

    // quantidade máxima que esse álbum pode ter
    private int totalFigurinhas;

    // quantidade de figurinhas em um pacote
    private int quantFigurinhasPorPacotinho;

    // quantos pacotes já foram recebidos para preencer esse álbum
    private int pacotinhosRecebidos = 0;


    /**
     * Construtor
     * @param totalFigurinhas quantidade máxima que esse álbum pode ter
     * @param quantFigurinhasPorPacotinho quantidade de figurinhas em um pacote
     */
    public Album(int totalFigurinhas, int quantFigurinhasPorPacotinho) {
        // as figurinhas ocupam posições de 1 a totalFigurinhas, inclusive, por isso a posição 0 dos arrays não será usada
        figurinhas = new Figurinha[totalFigurinhas + 1];
        figurinhasRepetidas = new int[totalFigurinhas + 1];

        this.totalFigurinhas = totalFigurinhas;
        this.quantFigurinhasPorPacotinho = quantFigurinhasPorPacotinho;
    }

    /**
     * O álbum recebe um novo pacote de figurinhas
     * @param pacotinho um pacote com quantFigurinhasPorPacotinho figurinhas
     */
    public void receberNovoPacotinho(Figurinha[] pacotinho) {
        this.pacotinhosRecebidos++;

        for (Figurinha figurinha : pacotinho) {
            boolean figurinhaRepetida = figurinhas[figurinha.getPosicao()] != null;

            if (figurinhaRepetida) {
                figurinhasRepetidas[figurinha.getPosicao()]++;
                this.quantFigurinhasRepetidas++;
            } else {
                figurinhas[figurinha.getPosicao()] = figurinha;
            }
        }
    }

    /**
     * Termina de preencher o álbum, desde que ele já esteja preenchido além de certa fração
     * mínima definida em Album.PREENCHIMENTO_MINIMO_PARA_PERMITIR_AUTO_COMPLETAR.
     *
     * Se o álbum não estiver ainda completo o suficiente para isso, este método simplesmente
     * não faz nada.
     */
    public void encomendarFigurinhasRestantes() {

        // validação da regra do mínimo de figurinhas necessárias
        boolean minimamentePreenchido = this.getQuantFigurinhasColadas() >= (this.totalFigurinhas * Album.PREENCHIMENTO_MINIMO_PARA_PERMITIR_AUTO_COMPLETAR);

        if(minimamentePreenchido) {

            this.pacotinhosRecebidos += this.getQuantPacotinhosNecessariosParaCompletarAlbum();

            for (int i = 1; i < this.totalFigurinhas; i++) {
                if (this.figurinhas[i] == null) {
                    this.figurinhas[i] = new Figurinha(i, String.format("http://urlFakeDaFigurinha%d.jpg", i));
                }
            }
        }
    }

    /**
     * Indica se determinada figurinha já foi colada no álbum
     * @param posicao a posição da figurinha que deseja-se descobrir se está no álbum
     * @return um booleano que indica se ela está ou não no álbum
     */
    public boolean possuiFigurinhaColada(int posicao) {
        return this.figurinhas[posicao] != null;
    }

    /**
     * Indica se determinada figurinha já foi colada no álbum
     * @param figurinha a figurinha que deseja-se descobrir se está no álbum
     * @return um booleano que indica se ela está ou não no álbum
     */
    public boolean possuiFigurinhaColada(Figurinha figurinha) {
        return possuiFigurinhaColada(figurinha.getPosicao());
    }

    /**
     * Indica se determinada figurinha é repetida
     * @param posicao a posição da figurinha que deseja-se descobrir se é repetida
     * @return um booleano que indica se ela é única ou repetida
     */
    public boolean possuiFigurinhaRepetida(int posicao) {
        return this.figurinhasRepetidas[posicao] != 0;
    }

    /**
     * Indica se determinada figurinha é repetida
     * @param figurinha a figurinha que deseja-se descobrir se é repetida
     * @return um booleano que indica se ela é única ou repetida
     */
    public boolean possuiFigurinhaRepetida(Figurinha figurinha) {
        return possuiFigurinhaRepetida(figurinha.getPosicao());
    }

    /**
     * Verifica quantos pacotes de figurinhas faltam com base na quantidade de figurinhas faltantes
     * @return a quantidade de pacotes de figurinhas necessária para completar o álbum (arrendondada para cima)
     */
    public int getQuantPacotinhosNecessariosParaCompletarAlbum() {
        int pacotinhosNecessarios = this.getQuantFigurinhasFaltando() / this.quantFigurinhasPorPacotinho;
        boolean divisaoExata = this.getQuantFigurinhasFaltando() % this.quantFigurinhasPorPacotinho == 0;

        if (!divisaoExata) {
            pacotinhosNecessarios++;
        }

        return pacotinhosNecessarios;
    }

    /**
     * Getter
     * @return a quantidade de figurinhas coladas que esse álbum possui
     */
    public int getQuantFigurinhasColadas() {
        int quantFigurinhasColadas = (this.pacotinhosRecebidos * this.quantFigurinhasPorPacotinho) - this.getQuantFigurinhasRepetidas();
        return quantFigurinhasColadas > this.totalFigurinhas ? this.totalFigurinhas : quantFigurinhasColadas;
    }

    /**
     * Getter
     * @return a quantidade de figurinhas que faltam para completar o álbum
     */
    public int getQuantFigurinhasFaltando() {
        return this.totalFigurinhas - this.getQuantFigurinhasColadas();
    }

    /**
     * Getter
     * @return quantos pacotinhos de figurinhas já foram recebidos por esse álbum
     */
    public int getTotalPacotinhosRecebidos() {
        return this.pacotinhosRecebidos;
    }

    /**
     * Getter
     * @return quantas figurinhas repetidas esse álbum possui
     */
    public int getQuantFigurinhasRepetidas() {
        return this.quantFigurinhasRepetidas;
    }

}
