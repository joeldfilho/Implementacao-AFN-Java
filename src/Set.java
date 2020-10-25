import java.util.ArrayList;
import java.util.List;

public class Set {

    List<Integer> elementos = new ArrayList<>();

    public Set newSet(){
        return new Set();
    }

    public Set newSet(List<Integer> U){
        Set novoSet = new Set();
        novoSet.insere(U);
        return novoSet;
    }

    public void insere(List<Integer> u) {
        elementos.addAll(u);
    }

    public int length(){
        return elementos.size();
    }

    public Set intersect(Set v, List<Integer> u){
        Set intersecao = new Set();
        for (Integer numero: u
             ) {
            if(v.elementos.contains(numero)){
                intersecao.elementos.add(numero);
            }
        }
        return intersecao;
    }
}
