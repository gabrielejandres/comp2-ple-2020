import java.util.ArrayList;

public class Tuite<T> {

    private final Usuario autor;
    private final String texto;
    private ArrayList<String> hashTags;
    private T anexo;

    /**
     * Construtor
     * @param autor o usuario autor desse tuite
     * @param texto o texto desse tuite
     */
    public Tuite(Usuario autor, String texto) {
        this.autor = autor;
        this.texto = texto;
        this.hashTags = new ArrayList<>();

        verificaHashTagNoTexto(texto);
    }

    /**
     * Verifica se o texto desse tuite tem alguma hashtag
     * @param texto o texto desse tuite
     */
    private void verificaHashTagNoTexto(String texto) {
        String[] palavras = texto.split(" ");
        for (String palavra : palavras) {
            if (palavra.startsWith("#")) {
                hashTags.add(palavra);
            }
        }
    }

    /**
     * Coloca um anexo do tipo T ao tuíte
     * @param anexo o anexo
     */
    public void anexarAlgo(T anexo) {
        this.anexo = anexo;
    }

    /**
     * Getter
     * @return o anexo desse tuite
     */
    public Object getAnexo() {
        return this.anexo;
    }

    /**
     * Getter
     * @return o autor do tuite
     */
    public Usuario getAutor() {
        return this.autor;
    }

    /**
     * Getter
     * @return o texto do tuite
     */
    public String getTexto() {
        return this.texto;
    }

    /**
     * Getter
     * @return as hashtags desse tuite
     */
    public ArrayList<String> getHashtags() {
        return this.hashTags;
    }
}
