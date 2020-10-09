import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
