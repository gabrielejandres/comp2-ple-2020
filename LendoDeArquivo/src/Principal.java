import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Principal {

    private static Scanner scanner;

    private static ResultadosTurma calcularMedia(String arquivo) throws ArquivoCorrompidoException, FileNotFoundException {

        int quantNotasInvalidas = 0;
        int quantNotasValidas = 0;
        float maiorNota = 0;
        float somaNotas = 0;

        File arq = new File(arquivo);
        ResultadosTurma resultados = new ResultadosTurma();

        // se o arquivo não puder ser aberto, uma FileNotFoundException será lançada
        scanner = new Scanner(arq);

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

                if(nota >= 5.0) {
                    resultados.quantAlunosAprovados++;
                } else {
                    resultados.getQuantAlunosReprovados++;
                }

                somaNotas += nota;
                quantNotasValidas++;

            } catch (Exception e) {
                quantNotasInvalidas++;
            }
        }

        if (quantNotasValidas >= quantNotasInvalidas) {
            resultados.mediaDaTurma = somaNotas / quantNotasValidas;;
        } else {
            throw new ArquivoCorrompidoException("O arquivo está corrompido. " +
                    "A quantidade de notas inválidas é maior do que a quantidade de notas válidas.", quantNotasInvalidas);
        }

        return resultados;
    }


    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        boolean fileNotFound;

        do {
            System.out.println("Olá, usuário! Para começar, digite o nome de um arquivo, por favor: ");
            String nomeArquivo = scanner.nextLine();

            fileNotFound = false;

            try {
                ResultadosTurma resultados = calcularMedia(nomeArquivo);
                imprimirResultados(resultados);
            } catch (ArquivoCorrompidoException e) {
                System.out.println(e.getMessage());
                System.out.println("Esse arquivo tem " + e.getQuantLinhasInvalidas() + " linhas inválidas.");
            } catch(FileNotFoundException e) {
                fileNotFound = true;
                System.out.println("O arquivo " + nomeArquivo +
                        " não foi encontrado. Por favor, forneça um nome válido: ");
            }
        } while(fileNotFound);

    }

    private static class ResultadosTurma {
        float mediaDaTurma;
        int quantAlunosAprovados;
        int getQuantAlunosReprovados;
        long dreDoAlunoComMaiorMedia;
    }

    private static void imprimirResultados(ResultadosTurma resultados) {
        System.out.println("-------- Resultados da turma: --------");
        System.out.println("Média: " + resultados.mediaDaTurma);
        System.out.println("Aprovados: " + resultados.quantAlunosAprovados);
        System.out.println("Reprovados: " + resultados.getQuantAlunosReprovados);
        System.out.println("DRE do aluno com maior média: " + resultados.dreDoAlunoComMaiorMedia);
    }
}
