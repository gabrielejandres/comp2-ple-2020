import java.io.FileNotFoundException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // indica se o arquivo não foi encontrado
        boolean fileNotFound;

        do {
            System.out.println("Olá, usuário! Para começar, digite o nome de um arquivo, por favor: ");
            String nomeArquivo = scanner.nextLine();

            fileNotFound = false;

            try {
                Utils.ResultadosTurma resultados = Utils.calcularMedia(nomeArquivo);
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
