import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Principal {

    private static Scanner scanner;

    private static class ResultadosTurma {
        float mediaDaTurma;
        int quantAlunosAprovados;
        int getQuantAlunosReprovados;
        long dreDoAlunoComMaiorMedia;

        @Override
        public String toString() {
            return "-------- Resultados da turma: --------" + "\n" +
                    "Média:" + mediaDaTurma + "\n" +
                    "Aprovados: " + quantAlunosAprovados + "\n" +
                    "Reprovados: " + getQuantAlunosReprovados + "\n" +
                    "DRE do aluno com maior média: " + dreDoAlunoComMaiorMedia;
        }
    }

    private static Scanner abrirArquivo(String arquivo) throws FileNotFoundException {
        File arq = new File(arquivo);

        // se o arquivo não puder ser aberto, uma FileNotFoundException será lançada
        scanner = new Scanner(arq);

        return scanner;
    }

    private static ResultadosTurma calcularMedia(String arquivo) throws ArquivoCorrompidoException, FileNotFoundException {

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

        scanner = abrirArquivo(arquivo);

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
                System.out.println(resultados.toString());
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
}
