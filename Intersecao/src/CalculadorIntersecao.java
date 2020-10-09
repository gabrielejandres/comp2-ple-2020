import java.util.List;

public abstract class CalculadorIntersecao<T extends Comparable> {

    /**
     * Método responsável por calcular a quantidade de elementos em comum entre duas listas
     * @param lista1 a primeira lista
     * @param lista2 a segunda lista
     * @return a quantidade de elementos em comum entre duas listas
     */
    public abstract int getQuantidadeElementosEmComum(List<T> lista1, List<T> lista2);
}
