import java.util.ArrayList;

/**
 *  Esta classe implementa um sistema de mensagens curtas estilo Twitter.
 *  É preciso cadastrar um usuário, identificado pelo seu e-mail, para que tuítes possam ser feitos.
 *  Usuários começam como iniciantes, depois são promovidos a senior e a ninja, em função do número de tuítes.
 *  Existe um tamanho máximo permitido por mensagem (constante TAMANHO_MAXIMO_TUITES).
 *  As mensagens podem conter hashtags (palavras iniciadas por #), que são detectadas automaticamente.
 *  Os tuítes podem conter, além da mensagem de texto, um anexo qualquer.
 *  Há um método para retornar, a qualquer momento, qual a hashtag mais usada em toda a história do sistema.
 */
public class TuiterLite<T> {

    public static final int TAMANHO_MAXIMO_TUITES = 120;

    // os usuários cadastrados no tuiter lite
    private ArrayList<Usuario> usuarios = new ArrayList<>();

    // hashTags utilizadas
    private ArrayList<String> hashTags = new ArrayList<>();

    // qtd de cada uma das hashTags
    private ArrayList<Integer> qtdHashTags = new ArrayList<>();

    /**
     * Cadastra um usuário, retornando o novo objeto Usuario criado.
     * Se o email informado já estiver em uso, não faz nada e retorna null.
     * @param nome O nome do usuário.
     * @param email O e-mail do usuário (precisa ser único no sistema).
     * @return O Usuario criado.
     */
    public Usuario cadastrarUsuario(String nome, String email) {

        for(Usuario usuario : usuarios) {
            if(usuario.getEmail().equals(email)) {
                return null;
            }
        }

        Usuario novoUsuario = new Usuario(nome, email);
        this.usuarios.add(novoUsuario);
        return novoUsuario;
    }

    /**
     * Tuíta algo, retornando o objeto Tuíte criado.
     * Se o tamanho do texto exceder o limite pré-definido, não faz nada e retorna null.
     * Se o usuário não estiver cadastrado, não faz nada e retorna null.
     *
     * @param usuario O autor do tuíte
     * @param texto O texto desejado
     * @return Um "tuíte", que será devidamente publicado no sistema
     */
    public Tuite tuitarAlgo(Usuario usuario, String texto) {
        boolean autorDesconhecido = usuario.getNome().equals("Usuário Desconhecido") || usuario.getEmail().equals("unknown@void.com");
        boolean tamanhoMaximoExcedido = texto.length() > TuiterLite.TAMANHO_MAXIMO_TUITES;

        if(autorDesconhecido || tamanhoMaximoExcedido || !usuarios.contains(usuario)) {
            return null;
        }

        atualizaUsuario(usuario);

        Tuite tuite = new Tuite<>(usuario, texto);

        adicionarHashTag(tuite.getHashtags());
        return tuite;
    }

    private void atualizaUsuario(Usuario usuario) {
        int qtdTuitesUsuario = usuario.getQtdTuites();

        // incrementa porque o usuário fez um novo tuite
        qtdTuitesUsuario++;
        usuario.setQtdTuites(qtdTuitesUsuario);

        // verificação e promoção do usuário, se for o caso
        verificaPromocaoUsuarioASenior(usuario, qtdTuitesUsuario);
        verificaPromocaoUsuarioANinja(usuario, qtdTuitesUsuario);
    }

    private void verificaPromocaoUsuarioASenior(Usuario usuario, int qtdTuitesUsuario) {
        boolean promoverUsuarioASenior = qtdTuitesUsuario >= Usuario.MIN_TUITES_SENIOR;

        if(promoverUsuarioASenior) {
            usuario.setNivel(NivelUsuario.SENIOR);
        }
    }

    private void verificaPromocaoUsuarioANinja(Usuario usuario, int qtdTuitesUsuario) {
        boolean promoverUsuarioANinja = qtdTuitesUsuario >= Usuario.MIN_TUITES_NINJA;

        if(promoverUsuarioANinja) {
            usuario.setNivel(NivelUsuario.NINJA);
        }
    }

    /**
     * Retorna a hashtag mais comum dentre todas as que já apareceram.
     * A cada tuíte criado, hashtags devem ser detectadas automaticamente para que este método possa funcionar.
     * @return A hashtag mais comum, ou null se nunca uma hashtag houver sido tuitada.
     */
    public String getHashtagMaisComum() {
        String maisComum = "";
        int qtdMaisComum = 0;
        for(String hashtag : this.hashTags) {
            int index = this.hashTags.indexOf(hashtag);
            int qtdHashTag = this.qtdHashTags.get(index);

            if(qtdHashTag > qtdMaisComum) {
                qtdMaisComum = qtdHashTag;
                maisComum = hashtag;
            }
        }

        return maisComum;
    }

    private void adicionarHashTag(ArrayList<String> hashtags) {
        for(String hashtag : hashtags) {
            boolean hashtagNaoCadastrada = !this.hashTags.contains(hashtag);
            if(hashtagNaoCadastrada) {
                this.hashTags.add(hashtag);
                this.qtdHashTags.add(1);
            } else {
                int index = this.hashTags.indexOf(hashtag);
                int qtdAtual = this.qtdHashTags.get(index);
                this.qtdHashTags.set(index, ++qtdAtual);
            }
        }
    }

    // Mainzinho bobo, apenas ilustrando String.split(regexp), e o String.startsWith()

//    public static void main(String[] args) {
//        String frase = "Testando algo,sdf com #hashtags no meio #teste vamos ver!fdfgf";
//        String[] palavras = frase.split("[\\s,!]");
//        for (String palavra : palavras) {
//            if (palavra.startsWith("#")) {
//                System.out.println(palavra);
//            }
//        }
//    }
}
