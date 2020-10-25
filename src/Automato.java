import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Automato {
    int numeroDeEstados;             //q
    int tamanhoAlfabeto;             //s
    int numeroDeTransicoes ;          //t
    int estadoInicial;               //q0
    int numeroDestadosDeAceitacao ;   //a
    List<Integer> estadosAceitacao = new ArrayList<>();
    List<Estado> tabelaTransicao = new ArrayList<>();
    List<Set> reach;

    Set estados;

    public Automato(int[] cabecalhoAutomato) {
        this.numeroDeEstados = cabecalhoAutomato[0];
        this.tamanhoAlfabeto = cabecalhoAutomato[1];
        this.numeroDeTransicoes = cabecalhoAutomato[2];
        this.estadoInicial = cabecalhoAutomato[3];
        this.numeroDestadosDeAceitacao = cabecalhoAutomato[4];
        Set estados = new Set();
        List<Integer> listaEstados = new ArrayList<>();
        for (int i = 0; i < numeroDeEstados; i++) {
            listaEstados.add(i);
        }
        estados.insere(listaEstados);
        this.estados = estados;
        for (int i = 0; i < numeroDeEstados; i++) {
            Estado estado = new Estado();
            for (int j = 0; j < tamanhoAlfabeto; j++) {
                Transicao transicao = new Transicao();
                estado.transicoes.add(transicao);
            }
            estado.transicoes.get(0).chegada.add(i);
            tabelaTransicao.add(estado);
        }
    }

    public void determinaAlcancaveisComZero(){
        int[][] matriz = matriz(numeroDeEstados,numeroDeEstados);
        for (int i = 0; i < numeroDeEstados; i++) {
            if(tabelaTransicao.get(i).transicoes.get(0).chegada.size() > 0){
                for (int chegada: tabelaTransicao.get(i).transicoes.get(0).chegada
                     ) {
                    matriz[i][chegada] = 1;
                }
            }
        }
        Grafo grafo = new Grafo();
        grafo.newGrafo(matriz);
        List<Set> saida = new ArrayList<>();
        for (int i = 0; i < numeroDeEstados ; i++) {
            Set alcancaveisParaI = new Set();
            alcancaveisParaI.insere(Collections.singletonList(i));
            Set alcancaveis = grafo.search(i);
            if (alcancaveis.length() > 0){
                alcancaveisParaI.insere(alcancaveis.elementos);
            }
            saida.add(alcancaveisParaI);
        }
        this.reach = saida;
//        return saida;
    }

    private int[][] matriz(int linhas, int colunas) {
        int[][]matriz = new int[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                matriz[i][j] = 0;
            }
        }
        return matriz;
    }

    public void adicionaTransicao(int estadoInicial, int simbolo, int estadoFinal) {
        tabelaTransicao.get(estadoInicial).transicoes.get(simbolo).chegada.add(estadoFinal);
    }

    public boolean verificaCadeia(int[] cadeiaTeste){
        if (this.numeroDestadosDeAceitacao == 0){
            return false;
        }

        if(cadeiaTeste.length == 0){
            Set u = new Set();
            u.intersect(reach.get(0), estadosAceitacao);
            return (u.length() > 0) ? true:false;
        }
        Queue L = new Queue();
        L.newQueue(reach.get(0).elementos);
        for (int i = 0; i < cadeiaTeste.length; i++) {
            int tamanhoFila = L.length();
            if(tamanhoFila == 0){
                return false;
            }
            int atual = cadeiaTeste[i];
            for (int j = 0; j < tamanhoFila; j++) {
                int simbolo = L.dequeue();
                if (tabelaTransicao.get(simbolo).transicoes.get(atual).chegada.size() > 0){
                    for (int chegada: tabelaTransicao.get(simbolo).transicoes.get(atual).chegada
                    ) {
                        L.adicionar(chegada);
                        L.enqueue(reach.get(chegada).elementos);
                    }

                }
            }

        }
        if (L.length() == 0){
            return false;
        }
        Set u = new Set();
        Set fila = new Set();
        for (Integer elemento: L.elementosFila
             ) {
            fila.elementos.add(elemento);
        }
        u = u.intersect(fila,estadosAceitacao);
        return (u.length() > 0) ? true:false;
    }
}
