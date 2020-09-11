public class Correntista {

    private String nome;
    private int senhaNumerica;

    public Correntista(String nome, int senhaNumerica) {
        this.nome = nome;
        this.senhaNumerica = senhaNumerica;
    }

    public String getNome() {
        return nome;
    }

    public boolean verificaSenha(int senha) {
        return this.senhaNumerica == senha;
    }
}
