import java.awt.*;

public class Usuario {

    public static final int MIN_TUITES_SENIOR = 200;
    public static final int MIN_TUITES_NINJA = 1000;

    // o email do usuário
    private final String email;

    // o nome do usuário
    private String nome;

    // a foto do usuário
    private Image foto;

    // a quantidade de tuites desse usuário
    private int qtdTuites;

    // Pode ser INICIANTE, SENIOR ou NINJA
    private NivelUsuario nivel;

    /**
     * Construtor
     * @param nome o nome do usuário
     * @param email o email do usuário
     */
    public Usuario(String nome, String email) {
        this.email = email;
        this.nome = nome;
        this.nivel = NivelUsuario.INICIANTE;
    }

    /**
     * Setter
     * @param foto uma foto para esse usuário
     */
    public void setFoto(Image foto) {
        this.foto = foto;
    }

    /**
     * Getter
     * @return a foto desse usuário
     */
    public Image getFoto() {
        return this.foto;
    }

    /**
     * Getter
     * @return o email desse usuário
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Getter
     * @return o nome desse usuário
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Getter
     * @return o nível desse usuário
     */
    public NivelUsuario getNivel() {
        return nivel;
    }

    /**
     * Setter
     * @param nivel um novo nível para esse usuário
     */
    public void setNivel(NivelUsuario nivel) {
        this.nivel = nivel;
    }

    /**
     * Getter
     * @return a quantidade de tuites desse usuário
     */
    public int getQtdTuites() {
        return qtdTuites;
    }

    /**
     * Setter
     * @param qtdTuites uma nova quantidade de tuites para esse usuário
     */
    public void setQtdTuites(int qtdTuites) {
        this.qtdTuites = qtdTuites;
    }

    /**
     * Verifica se o usuário é o mesmo de acordo com o email
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return email.equals(usuario.email);
    }
}
