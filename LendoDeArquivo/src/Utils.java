import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Utils {

    /**
     * Abre um arquivo, se ele existir
     * @param arquivo o nome do arquivo que se deseja abrir
     * @return um scanner que possibilita a leitura do arquivo
     * @throws FileNotFoundException se o arquivo não for encontrado
     */
    private static Scanner abrirArquivo(String arquivo) throws FileNotFoundException {
        File arq = new File(arquivo);

        // se o arquivo não puder ser aberto, uma FileNotFoundException será lançada
        return new Scanner(arq);
    }

    /**
     * Verifica se um aluno foi aprovado ou não de acordo com sua nota
     * @param nota a nota de um aluno qualquer
     * @param resultados um objeto que contém informações dos resultados de uma turma
     */
    private static void verificaAprovacao(float nota, ResultadosTurma resultados) {
        if(nota >= 5.0) {
            resultados.quantAlunosAprovados++;
        } else {
            resultados.getQuantAlunosReprovados++;
        }
    }

    public static ResultadosTurma calcularMedia(String arquivo) throws ArquivoCorrompidoException, FileNotFoundException {

        // quantidade de notas inválidas lidas do arquivo
        int quantNotasInvalidas = 0;

        // quantidade de notas válidas lidas do arquivo
        int quantNotasValidas = 0;

        // maior nota lida do arquivo
        float maiorNota = 0;

        // soma de todas as notas válidas do arquivo
        float somaNotas = 0;

        // resultados da turma
        ResultadosTurma resultados = new ResultadosTurma();

        Scanner scanner = abrirArquivo(arquivo);

        // essa parte do código só vai acontecer se o scanner conseguir abrir o arquivo
        while (scanner.hasNext()) {
            try {
                String linha = scanner.nextLine();
                String[] aluno = linha.split(" ");
                long dre = Long.valueOf(aluno[0]);
                float nota = Float.valueOf(aluno[1]);

                // controle do aluno com maior nota
                if(nota > maiorNota) {
                    maiorNota = nota;
                    resultados.dreDoAlunoComMaiorMedia = dre;
                }

                verificaAprovacao(nota, resultados);

                somaNotas += nota;
                quantNotasValidas++;

            } catch (Exception e) {
                quantNotasInvalidas++;
            }
        }

        boolean arquivoCorrompido = quantNotasValidas <= quantNotasInvalidas;
        if (arquivoCorrompido) {
            throw new ArquivoCorrompidoException("O arquivo está corrompido. " +
                    "A quantidade de notas inválidas é maior do que a quantidade de notas válidas.", quantNotasInvalidas);
        } else {
            resultados.mediaDaTurma = somaNotas / quantNotasValidas;
        }

        return resultados;
    }

    /**
     * Classe interna que armazenará os resultados de uma turma
     */
    public static class ResultadosTurma {
        float mediaDaTurma;
        int quantAlunosAprovados;
        int getQuantAlunosReprovados;
        long dreDoAlunoComMaiorMedia;

        @Override
        public String toString() {
            return "-------- Resultados da turma: --------" + "\n" +
                    "Média: " + mediaDaTurma + "\n" +
                    "Aprovados: " + quantAlunosAprovados + "\n" +
                    "Reprovados: " + getQuantAlunosReprovados + "\n" +
                    "DRE do aluno com maior média: " + dreDoAlunoComMaiorMedia;
        }
    }
}
