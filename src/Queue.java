import java.util.ArrayList;
import java.util.List;

public class Queue {
    List<Integer> elementosFila = new ArrayList<>();


    public Queue newQueue(List<Integer> u){
        Queue queue = new Queue();
        queue.enqueue(u);
        enqueue(u);
        return queue;
    }

    public void adicionar(int elemento){
        elementosFila.add(elementosFila.size(), elemento);
    }


    public void enqueue(List<Integer> elementos) {
        for (Integer elemento: elementos
             ) {
            elementosFila.add(elementosFila.size(), elemento);
        }
    }

    public int dequeue(){
        int devolver = elementosFila.get(0);
        elementosFila.remove(0);
        return devolver;
    }

    public int length() {
        return elementosFila.size();
    }
}
