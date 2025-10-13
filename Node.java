public class Node<T>{
    public Node next;
    public T value;

    public Node(T value, Node next){
        this.value = value;
        this.next = next;
    }
}