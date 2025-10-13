//import javax.xml.soap.Node;
//
//public class Recursion {
//
//    Object x;
//    Node n;
//
//
//    public Recursion(int index, int first, int last) {
//        removeRecursively(index);
//    }
//
//    public int removeRecursively(int index, Node head) {
//        Node temp = head.next;
//        if (index == 1) temp.next = null;
//        else return removeRecursively(index - 1, temp);
//    }
//
//    private Object getRecursively(int index, Node head) {
//        Node current = head;
//        if (index == 1) return current.next;
//        else return getRecursively(index - 1, current.next);
//    }
//
//    private boolean contains(Object x, Node head) {
//        Node current = head;
//        if (current == x) return true;
//        else if (current.next == null) return false;
//        else return contains(x, current.next);
//    }
//
//    /// recursive method for contains(Object o) - boolean
//    // recursive method return int indexOf(Object o)
//}
