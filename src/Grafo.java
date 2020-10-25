import java.util.ArrayList;
import java.util.List;

public class Grafo {
    List<No> nos = new ArrayList<>();


    public Grafo newGrafo(int[][]matrizAdjacencia){
        Grafo grafo = new Grafo();
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            No no = new No();
            for (int j = 0; j < matrizAdjacencia[i].length; j++) {
                if(matrizAdjacencia[i][j] == 1){
                    no.filhos.add(j);
                }
            }
            grafo.nos.add(no);
        }
        this.nos = grafo.nos;
        return grafo;
    }

    public Set search(int no){
        boolean[] visitados =new boolean[nos.size()];
        Set busca = new Set();
        visitados[no] = true;
        busca.elementos.add(no);
        Queue aBuscar = new Queue();
        aBuscar.adicionar(no);
        while(aBuscar.elementosFila.size() != 0){
            int atual = aBuscar.dequeue();
            for (int filho: nos.get(atual).filhos
                 ) {
                if (visitados[filho] == false){
                    visitados[filho] = true;
                    aBuscar.adicionar(filho);
                    busca.elementos.add(filho);
                }
            }
        }
        return busca;
    }

}
