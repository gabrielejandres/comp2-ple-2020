import java.util.ArrayList;

public class Album {

    public static final float PREENCHIMENTO_MINIMO_PARA_PERMITIR_AUTO_COMPLETAR = 0.9f;  // 90%

    private Figurinha[] figurinhas;
    private int[] figurinhasRepetidas;
    private int quantFigurinhasRepetidas;

    private int totalFigurinhas;
    private int quantFigurinhasPorPacotinho;
    private int pacotinhosRecebidos = 0;


    public Album(int totalFigurinhas, int quantFigurinhasPorPacotinho) {
        figurinhas = new Figurinha[totalFigurinhas + 1];
        figurinhasRepetidas = new int[totalFigurinhas + 1];
        this.totalFigurinhas = totalFigurinhas;
        this.quantFigurinhasPorPacotinho = quantFigurinhasPorPacotinho;
    }

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

    public int getTotalPacotinhosRecebidos() {
        return this.pacotinhosRecebidos;
    }

    /**
     * Termina de preencher o álbum, desde que ele já esteja preenchido além de certa fração
     * mínima definida em Album.PREENCHIMENTO_MINIMO_PARA_PERMITIR_AUTO_COMPLETAR.
     *
     * Se o álbum não estiver ainda completo o suficiente para isso, este método simplesmente
     * não faz nada.
     */
    public void encomendarFigurinhasRestantes() {

        // validação da regra dos 90% preenchidos
        boolean minimamentePreenchido = this.getQuantFigurinhasColadas() >= (this.totalFigurinhas * Album.PREENCHIMENTO_MINIMO_PARA_PERMITIR_AUTO_COMPLETAR);

        if(minimamentePreenchido) {
            for (int i = 1; i < this.totalFigurinhas; i++) {
                if (this.figurinhas[i] == null) {
                    this.figurinhas[i] = new Figurinha(i,"http://urlFakeDaFigurinha%d.jpg");
                }
            }
            int pacotinhosNecessarios = this.getQuantFigurinhasFaltando() / this.quantFigurinhasPorPacotinho;
            boolean divisaoExata = this.getQuantFigurinhasFaltando() % this.quantFigurinhasPorPacotinho == 0;

            if (!divisaoExata) {
                pacotinhosNecessarios++;
            }

            this.pacotinhosRecebidos += pacotinhosNecessarios;
        }
    }

    public boolean possuiFigurinhaColada(int posicao) {
        return this.figurinhas[posicao] != null;
    }

    public boolean possuiFigurinhaColada(Figurinha figurinha) {  // overload
        return possuiFigurinhaColada(figurinha.getPosicao());
    }

    public boolean possuiFigurinhaRepetida(int posicao) {
        return this.figurinhasRepetidas[posicao] != 0;
    }

    public boolean possuiFigurinhaRepetida(Figurinha figurinha) {  // overload
        return possuiFigurinhaRepetida(figurinha.getPosicao());
    }

    public int getQuantFigurinhasColadas() {
        // obtenho o total de pacotinhos recebidos e então elimino a quantidade de figurinhas repetidas
        int quantFigurinhasColadas = (this.pacotinhosRecebidos * this.quantFigurinhasPorPacotinho) - this.getQuantFigurinhasRepetidas();
        return quantFigurinhasColadas > 200 ? 200 : quantFigurinhasColadas;
    }

    public int getQuantFigurinhasRepetidas() {
        return this.quantFigurinhasRepetidas;
    }

    public int getQuantFigurinhasFaltando() {
        return this.totalFigurinhas - this.getQuantFigurinhasColadas();
    }

}
