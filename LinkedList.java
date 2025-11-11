public class LinkedList<T> {
    public Node head;
    public Node last;
    // size instance variable

    public LinkedList() {
        head = null;
        last = null;
    }

    public void addFirst(T value) {
        if (head == null) { // if list is empty before this method was called
            head = new Node(value, null);
            last = head;
        } else {
            Node n = new Node(value, head);
            head = n;
        }
        // size ++
    }

    public void addLast(T value) {
        if (head == null) { // if list is empty before this method was called
            head = new Node(value, null);
            last = head;
        } else {
            last.next = new Node(value, null);
            last = last.next;
        }
        // size ++
    }

    // iterative
    // precondition: index is some index that lives in current list or one greater
    // ie no index 10 in list of size 2
    // index from 0 to a inclusive in list of size a (if a then add to end of list)
    public void add(int index, T value) {
        if (index == 0) {
            addFirst(value);
        } else {
            Node temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            Node n = new Node<>(value, temp.next);
            temp.next = n;
            // size ++
            // update last
            if (n.next == null){ // if new node inserted at end of tail, new node is last
                last = n;
            }
        }

        // account to update last??

    }

    public void addR(int index, T value) {
        if (index == 0) { // handle insert at head
            addFirst(value);
            return;
        }
        if (head == null) {  // case where list doesn't exist and index is not 0
            return;
        }
        addR(index, value, head);
        // size ++
    }

    // how update last
    public void addR(int index, T value, Node h) {
        if (index == 1) {
            Node n = new Node(value, h.next);
            h.next = n;
            if (n.next == null) { //update last
                last = n;
            }
        } else {
            if (h.next == null) { // if index not exist in list
                return;
            }
            addR(index - 1, value, h.next);
        }
    }


    // deal w case 1: list is size 0, first doesnt exist -> so dont need to do anything
    // case 2: list is size 1 or more
    public void removeFirst() {
        if (head != null) {
            head = head.next;
            // size--
            // update last
            if (head == null) { // if list becomes empty
                last = null;
            }
        }
    }

    // deal w case 1: list is size 0, last doesnt exist -> so dont need to do anything
    // case 2: list is size 1 or more
    public void removeLast() {
        if (head != null) {
            if (head.next == null) { // if list is of size 1
                head = null;
                last = null;
            } else {
                Node current = head;
                while (current.next != null && current.next != last) { // iterate through list so current ends up holding second to last in list
                    current = current.next;
                }
                current.next = null;
                last = current; // update last
            }
            // size--
        }
    }

    // precondition: valid index, index points to some node in list
    // case 1: index is 0: remove first -> remove first
    // case 2: index is 1 to listsize-1 -> remove that element
    public void remove(int index) {
        if (index == 0) {
            removeFirst();
        } else {
            Node temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next; // move forward in list by i-1 val
            }
            Node removedNode = temp.next;
            temp.next = removedNode.next;
            if (removedNode == last) { // if removed last node, update new last
                last = temp;
            }

            // size--;
        }

    }

    // precondition: assume index is index that exists in list
    public void removeR(int index) {
        if (index == 0) {
            removeFirst();
            return;
        }
        removeR(index,head);
    }


    // FOR ALL THE BELOW, NEED TO UPDATE TAIL
    public void removeR(int index, Node h) {
        if (index == 1) {
            h.next = h.next.next;
        } else {
            removeR(index - 1, h.next);
        }
    }

    // case: object not in list -> !!! NEED TO COMPLETE THIS
    // object is in list (at beg, middle, or end)
    public void remove(Object o) {
        Node current = head;
        while (current.next.value != o) { // when loop ends, current.next will have value o
            current = current.next; // move forward in list by i-1 val
            // attempt at checking when you have reached the end of the list and can conlcude object is not in list
            if (current.next == null) {
                return;
            }
        }
        current.next = current.next.next; // remove node current.next from linked list bc lose its marker
        // size--;
    }

    // iteratively
    // precondition: index exists in list
    // case 0: head doesnt exist -> so by precondition this case will nevr happen
    // case 1: index points to head
    // case 2: index points to val in list not head
    public Object get(int index) {
        Node current = head;
        for (int i = 0; i < index; i++) { // get current to node at index index
            current = current.next;
        }
        return current.value;
    }

    // recursively
    public Object getR(int index) {
        return getR(index, head);
    }

    public Object getR(int index, Node h){
        if (index == 0) {
            return h.value;
        } else {
            return getR(index - 1, h.next);
        }
    }


    // deal w case 1: list is size 0, first doesnt exist
    // case 2: list is size 1 or more
    public Object getFirst() {
        if (head == null) {
            return null;
        }
        return last.value;
    }

    // deal w case 1: list is size 0, first doesnt exist
    // case 2: list is size 1 or more
    public Object getLast() {
        if (head == null) {
            return null;
        }
        return last.value;
    }


    public boolean contains(Object o) {
        Node current = head;
        while (current != null) { // will occur when traversed off list
            if (current.value.equals(o)) {
                return true;
            }
            current = current.next; // move forward in list by 1
        }
        return false;
    }

    public boolean containsR(Object o) {
        return containsR(o, head);
    }

    public boolean containsR(Object o, Node h) {
        if (h.next == null) { // reached end of list
            return h.value.equals(o);
        }
        if (h.value.equals(o)) {
            return true;
        }
        return containsR(o, h.next);
    }

    public int indexOf(Object o) {
        int index = 0;
        Node current = head;
        while (!current.value.equals(o)) { //while current does not have object o
            current = current.next;
            index++;
        }
        return index;
    }

    public int indexOfR(Object o) {
        return indexOfR(o, head, 0);
    }

    // precondition, assume object o exists in list
    public int indexOfR(Object o, Node h, int index) {
        // index tracks index of node h
        if(h.value.equals(o)) {
            return index;
        }
        return indexOfR(o, h.next, index + 1);
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        int count = 1;
        Node current = head;
        while (current.next != null) { // while current is not end of list
            current = current.next;
            count++;
        }
        return count;
    }

    public void clear() {
        head = null;
    }

    public void printList() {
        String s = new String("");
    }
}
