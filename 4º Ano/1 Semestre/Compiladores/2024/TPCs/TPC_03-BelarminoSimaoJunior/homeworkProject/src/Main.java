import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int estadoInicial = 25;
        int estadoFinal = 26;

        Map<String, Integer> tabelaTransicao = new HashMap<>();
        tabelaTransicao.put("25true", estadoFinal);
        tabelaTransicao.put("26true", estadoFinal);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite uma sequência: ");
        String sequencia = scanner.nextLine().trim();
        char[] cadeia = sequencia.toCharArray();
        int estado = estadoInicial;

        for (int i = 0; i < cadeia.length; i++) {
            boolean isDigit = Character.isDigit(cadeia[i]);
            int proximoEstado = tabelaTransicao.getOrDefault(estado + "" + isDigit, 0);

            estado = proximoEstado;

            if (estado == 0) {
                System.out.println(sequencia + " não é um número inteiro.");
                System.exit(0);
            }
        }

        System.out.println(sequencia + " é um número inteiro.");
        scanner.close();
    }
}
