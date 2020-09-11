public class Fracao {

    private int numerador;
    private int denominador;
    private boolean positiva;

    /**
     * Construtor.
     * O sinal da fração é passado no parâmetro específico.
     *
     * @param numerador O numerador (inteiro não-negativo)
     * @param denominador O denominador (inteiro positivo)
     * @param positiva se true, a fração será positiva; caso contrário, será negativa
     */
    public Fracao(int numerador, int denominador, boolean positiva) {
        this.numerador = numerador;
        this.denominador = denominador;
        this.positiva = positiva;
    }

    // Getters and setters
    public int getNumerador() {
        return this.numerador;
    }

    public int getDenominador() {
        return this.denominador;
    }

    public boolean isPositiva() {
        if(this.numerador == 0)
            return false;
        return this.positiva;
    }

    /**
     * @return um double com o valor numérico desta fração
     */
    public double getValorNumerico() {
        if (this.numerador == 0) {
            return 0;
        }
        int mult = this.isPositiva() ? 1 : -1;
        return (mult * ((double) this.numerador / (double) this.denominador));
    }

    /**
     * Retorna uma fração que é equivalente a esta fração (this),
     * e que é irredutível (numerador e denominador primos entre si).
     * Em outras palavras, retorna a fração geratriz desta fração.
     *
     * @return uma fração irredutível equivalente a esta;
     *         no caso desta fração JÁ SER ela própria irredutível, retorna this
     */
    public Fracao getFracaoGeratriz() {
        int mdc = mdc(this.numerador, this.denominador);
        if (mdc == 1) {
            return this;
        }
        int novoNumerador = this.numerador / mdc;
        int novoDenominador = this.denominador / mdc;
        return new Fracao(novoNumerador, novoDenominador, this.positiva);
    }

    /**
     *
     * @param dividendo O número que será dividido (maior número)
     * @param divisor O número pelo qual o dividendo será dividido
     * @return maior divisor comum entre dividendo e divisor
     */
    private int mdc(int dividendo, int divisor) {
        return dividendo % divisor == 0 ? divisor : mdc(divisor, dividendo % divisor);
    }

    /**
     *
     * @param o Um objeto, que deverá ser uma conta
     * @return um booleano que indica se as frações são equivalentes
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fracao novaFracao = (Fracao) o;

        boolean mesmoNumerador = novaFracao.getFracaoGeratriz().getNumerador() == this.getFracaoGeratriz().getNumerador();
        boolean mesmoDenominador = novaFracao.getFracaoGeratriz().getDenominador() == this.getFracaoGeratriz().getDenominador();
        boolean mesmoSinal = novaFracao.getFracaoGeratriz().isPositiva() == this.getFracaoGeratriz().isPositiva();

        return mesmoNumerador && mesmoDenominador && mesmoSinal;
    }
}