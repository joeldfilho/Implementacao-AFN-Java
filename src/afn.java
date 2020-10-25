import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class afn {

    public static void main(String[] args) throws IOException {

        /* Lê o arquivo de entrada*/
        String caminho = System.getProperty("user.dir");
        String arquivoHardCoded = caminho + "\\entrada.txt";
        FileReader fileReader = new FileReader(arquivoHardCoded);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Scanner arquivoEntrada = new Scanner(bufferedReader);


        /* primeira linha do arquivo de entrada tem o número de automatos*/
        int numeroDeAutomatos = arquivoEntrada.nextInt();   //

        /*pra cada automato vou ter que fazer os próximos passos*/

        /* O arquivo de saída deverá ser um só para todos os autômatos, então vou criar antes de criá-los */
        String saida = "";

        for (int i = 0; i < numeroDeAutomatos; i++) {

            /* primeira linha do automato atual é no formato q s  t q0 a */
            int[] cabecalhoAutomato = leCabecalho(arquivoEntrada);
            Automato automato = new Automato(cabecalhoAutomato);
            List<Integer> estadosAceitacao = new ArrayList<>();
            for (int j = 0; j < automato.numeroDestadosDeAceitacao; j++) {
                estadosAceitacao.add(arquivoEntrada.nextInt());
            }
            automato.estadosAceitacao = estadosAceitacao;
            for (int j = 0; j < automato.numeroDeTransicoes; j++) {
                automato.adicionaTransicao(arquivoEntrada.nextInt(), arquivoEntrada.nextInt(), arquivoEntrada.nextInt());
            }
            int numeroTestes = arquivoEntrada.nextInt();
            arquivoEntrada.nextLine();
            for (int j = 0; j < numeroTestes; j++) {
                String cadeia = arquivoEntrada.nextLine();
                String[] arrayCadeia = cadeia.split("\\s+");
                int[] arrayVerificar = new int[arrayCadeia.length];
                for (int k = 0; k < arrayCadeia.length; k++) {
                    arrayVerificar[k] = Integer.valueOf(arrayCadeia[k]);
                }
                automato.determinaAlcancaveisComZero();
                String adicionarSaida = (automato.verificaCadeia(arrayVerificar)) ? "1 " : "0 ";
                saida += adicionarSaida;
            }
            saida+= "\n";
        }
        String arquivoSaida = caminho + "\\saida.txt";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(arquivoSaida));
        bufferedWriter.write(saida);
        bufferedWriter.flush();
        bufferedWriter.close();
        fileReader.close();
    }



    private static int[] leCabecalho(Scanner arquivoEntrada) {
        int[] dadosAutomato = new int[5];
        //String cabecalho = arquivoEntrada.nextLine();

        int numeroDeEstados = arquivoEntrada.nextInt();             //q
        int tamanhoAlfabeto = arquivoEntrada.nextInt();             //s
        int numeroDeTransicoes = arquivoEntrada.nextInt();          //t
        int estadoInicial = arquivoEntrada.nextInt();               //q0
        int numeroDestadosDeAceitacao = arquivoEntrada.nextInt();   //a

        dadosAutomato[0] = numeroDeEstados;
        dadosAutomato[1] = tamanhoAlfabeto;
        dadosAutomato[2] = numeroDeTransicoes;
        dadosAutomato[3] = estadoInicial;
        dadosAutomato[4] = numeroDestadosDeAceitacao;

        return dadosAutomato;
    }

}
