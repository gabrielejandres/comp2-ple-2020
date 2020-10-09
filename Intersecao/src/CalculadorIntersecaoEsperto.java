import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * Encontra a interseção entre duas listas de maneira esperta: concatena as duas listas e em seguida transforma o resultado
 * dessa concatenação em um conjunto. Assim, a diferença entre o tamanho da lista concatenada e do conjunto vai representar o
 * tamanho da interseção.
 */
public class CalculadorIntersecaoEsperto extends CalculadorIntersecao<Integer> {

    @Override
    public int getQuantidadeElementosEmComum(List<Integer> lista1, List<Integer> lista2) {
        // concatenando as duas listas
        lista1.addAll(lista2);
        int tamanhoInicial = lista1.size();

        // cria um HashSet a partir da lista1
        Set<Integer> conjunto = new HashSet<>(lista1);
        int tamanhoFinal = conjunto.size();

        return tamanhoInicial - tamanhoFinal;
    }
}
