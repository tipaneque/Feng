package numero2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public final class Menu {

    private Pedido pedido;
    private Pizza arrayPizza[];
    private Lanche arrayLanche[];
    private Salgadinho arraySalgadinho[];
    private Validacao vd;

    public Menu() {
        vd = new Validacao();
        float pagamento = 0;
        int op;
        char resp = ' ';
        System.out.println("BEM BINDO A ERNESTINA 3 LANCHES");
        String nome = vd.validarString("Digite o seu nome: ");
        do {
            System.out.println("""
                               
                              ---------------------- 
                              |   CARDAPIO DO DIA  |
                              | ------------------ |
                              |    1. Pizza        |
                              |    2. Lanche       |
                              |    3. Salgadinho   |
                              |    4. Sair         | 
                               -------------------- 
                               """);
            op = vd.validarInt(1, 4, "Faca o seu pedido: ");

            switch (op) {
                case 1:
                    pagamento += pedidoPizza();
                    resp = desejaContinuar();
                    break;

                case 2:
                    pagamento += pedidoLanche();
                    resp = desejaContinuar();
                    break;

                case 3:
                    pagamento += pedidoSalgadinho();
                    resp = desejaContinuar();
                    break;

                case 4:
                    System.out.println("\nVOLTE SEMPRE!!!");
                    break;

                default:
            }
        } while (op != 4 && resp != 'n' && resp != 'N');

        if (pagamento != 0) {
            pagamentoFinal(pagamento);
        }

    }
    
    //(E) - Inicio
    public float pedidoPizza() {
        int q = vd.validarInt(1, 10, "\nDeseja comprar quantas pizzas: ");
        arrayPizza = new Pizza[q];
        float totalPizza = 0;

        for (int i = 0; i < arrayPizza.length; i++) {
            float peso = vd.validarFloat(20, 150, "Introduza o peso da " + (i + 1) + "a pizza: ");
            String recheio = escolherRecheio("pizza");
            String molho = vd.validarString("Qual molho deseja: ");
            float preco = 300 * peso / 100;
            totalPizza += preco;

            arrayPizza[i] = new Pizza(peso, recheio, molho, preco);
            System.out.println("");

            try {
                FileWriter fw = new FileWriter("Pizza.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(peso + ";" + recheio + ";" + 7 + ";" + recheio + ";" + molho + ";" + preco);
                bw.newLine();
                bw.close();
            } catch (IOException io) {
                System.out.println("error: " + io.getMessage());
            }
        }
        return totalPizza;
    } //(E) - Fim

    public float pedidoLanche() {
        int q = vd.validarInt(1, 10, "\nDeseja comprar quantos lanches: ");
        arrayLanche = new Lanche[q];
        float totalLanche = 0;

        for (int i = 0; i < arrayLanche.length; i++) {
            float peso = vd.validarFloat(20, 150, "Introduza o peso do " + (i + 1) + "o lanche: ");
            String recheio = escolherRecheio("lanche");
            String molho = vd.validarString("Qual molho deseja: ");
            String tipoPao = vd.validarString("Introduza o tipo de pao: ");
            float preco = 200 * peso / 100;
            totalLanche += preco;

            arrayLanche[i] = new Lanche(peso, recheio, molho, tipoPao, preco);
            System.out.println("");
        }
        return totalLanche;
    }

    public float pedidoSalgadinho() {
        int q = vd.validarInt(1, 10, "\nDeseja comprar quantos salgadinhos: ");
        arraySalgadinho = new Salgadinho[q];
        float totalSalgadinho = 0;

        for (int i = 0; i < arraySalgadinho.length; i++) {
            float peso = vd.validarFloat(20, 150, "Introduza o peso do " + (i + 1) + "o salgadinho: ");
            String recheio = escolherRecheio("salgadinho");
            String massa = vd.validarString("Qual massa deseja: ");
            String tipo = escolherTipoSalgadinho();
            float preco = 100 * peso / 100;
            totalSalgadinho += preco;

            arraySalgadinho[i] = new Salgadinho(peso, recheio, massa, tipo, peso);
            System.out.println("");
        }
        return totalSalgadinho;
    }

    public String escolherRecheio(String item) {
        String tipoRecheio;
        System.out.println("""
                           
                           1. Bordas Recheadas
                           2. Bordas nao Recheadas""");
        int op = vd.validarInt(1, 2, "Escolha a borda da " + item + ": ");
        switch (op) {
            case 1:
                tipoRecheio = "Bordas Rechedas";
                break;

            case 2:
                tipoRecheio = "Bordas nao Rechedas";
                break;

            default:
                tipoRecheio = "Bordas Recheadas";
        }
        return tipoRecheio;
    }

    public String escolherTipoSalgadinho() {
        String tipoSalgadinho;
        System.out.println("""
                           
                           1. Frito
                           2. Assado""");
        int op = vd.validarInt(1, 2, "Escolha o tipo de Salgadinho: ");
        switch (op) {
            case 1:
                tipoSalgadinho = "Frito";
                break;

            case 2:
                tipoSalgadinho = "Assado";
                break;

            default:
                tipoSalgadinho = "Hahahaha";
        }
        return tipoSalgadinho;
    }

    public char desejaContinuar() {
        char r = vd.validarChar('S', 's', 'N', 'n', "Deseja continuar[S/N]: ");
        return r;
    }

    // (C) - Inicio
    public void lerPizza(String fileName) {
        StringTokenizer stz;
        int i = nrDeLinhas("Pizza.txt"), c = 0;
        String linha;
        float total = 0f;
        String dados[][] = new String[i][5];

        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            linha = br.readLine();

            while (linha != null) {
                stz = new StringTokenizer(linha, ";");
                dados[c][0] = stz.nextToken(); // Peso
                dados[c][1] = stz.nextToken(); // Recheio
                dados[c][2] = stz.nextToken(); // Validade
                dados[c][3] = stz.nextToken(); // Molho
                dados[c][4] = stz.nextToken(); // Preco
                total += Float.parseFloat(dados[c][4]);
                c++;
                linha = br.readLine();
            }
            br.close();
            System.out.println("Pedidos de Pizza");
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < i; k++) {
                    System.out.println("Peso da" + (j + 1) + "Pizza: " + dados[j][k]);
                    System.out.println("Recheio: " + dados[j][k]);
                    System.out.println("Validade: " + dados[j][k] + " dias");
                    System.out.println("Molho: " + dados[j][k]);
                    System.out.println("Preco da Pizza" + dados[j][k]);
                }
            }
            pagamentoFinal(total);
        } catch (FileNotFoundException fn) {
            System.out.println("error: " + fn.getMessage());
        } catch (IOException io) {
            System.out.println("error: " + io.getMessage());
        }

    } // (C) - Fim

    public int nrDeLinhas(String fileName) {
        String linha;
        int nrL = 0;
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            linha = br.readLine();

            while (linha != null) {
                nrL++;
                linha = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException fn) {
            System.out.println("error: " + fn.getMessage());
        } catch (IOException io) {
            System.out.println("error: " + io.getMessage());
        }
        return nrL;
    }

    public void pagamentoFinal(float pagamento) {
        System.out.println("TOTAL A PAGAR: " + pagamento + " MT");
        float pagar = vd.validarFloat(pagamento, 10000, "Quanto vai pagar: ");
        System.out.println("Trocos: " + (pagar - pagamento) + " MT");
    }
    
    //(D) - Inicio
    public float totalSalgadinho (Salgadinho[] sal) {
        float vt = 0;
        for (int i = 0; i < sal.length; i++) {
            vt += sal[i].getPreco();
        }
        return vt;
    } // (D) - Fim
    
    // (F) - Inicio
    public void ordenaLanche(Lanche[] lan) {
        for (int i = 0; i < lan.length - 1; i++) {
            for (int j = 1; j < lan.length; j++) {
                if(lan[i].getPreco() > lan[j].getPreco()) {
                    float aux = lan[i].getPreco();
                    lan[i].setPreco(lan[j].getPreco());
                    lan[j].setPreco(aux);
                }
            }
        }
        System.out.println(Arrays.toString(lan));
    } // (F) - Fim
}
