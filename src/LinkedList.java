import java.util.NoSuchElementException;

public class LinkedList {

    private class Node {

        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node first;
    private Node last;
    private int size;

    public void print() {
        Node current = first;

        while (current != null) {
            System.out.println(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void addFirst(int item) {
        var node = new Node(item);

        if (isEmpty())
            first = last = node;
        else {
            node.next = first;
            first = node;
        }

        size++;
    }

    public void addLast(int item) {
        var node = new Node(item);

        if (isEmpty())
            first = last = node;
        else {
            last.next = node;
            last = node;
        }

        size++;
    }


    private boolean isEmpty() {
        return first == null;
    }


    public int indexOf(int item) {
        int index = 0;
        Node current = first;
        while (current != null) {
            if (current.value == item)
                return index;

            current = current.next;
            index++;
        }
        return -1;
    }

    public boolean contains(int item) {
        return indexOf(item) != -1;
    }

    public void removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();

        if (first == last)
            first = last = null;
        else {
            // save link to the second element
            Node second = first.next;
            // remove first element;
            first.next = null;
            // Update first element
            first = second;
        }

        size--;
    }

    public void removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();

        if (first == last)
            first = last = null;
        else {
            Node previous = getPrevious(last);
            last = previous;
            last.next = null;
        }

        size--;
    }

    private Node getPrevious(Node node) {
        Node current = first;
        while (current != null) {
            if (current.next == node) return current;

            current = current.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public int[] toArray() {
        int[] array = new int[size];
        Node current = first;
        int index = 0;

        while (current != null) {
            array[index++] = current.value;
            current = current.next;
        }

        return array;
    }


    public void reverse() {
        if (isEmpty()) return;

        Node previous = first;
        Node current = first.next;

        while (current != null) {
            Node next = current.next;

            //backup of the link
            current.next = previous;
            // change links direction
            previous = current;
            current = next;
        }

        last = first;
        last.next = null;
        first = previous;
    }

    public int getKthFromTheEnd(int k) {
        if (isEmpty())
            throw new IllegalArgumentException();

        Node a = first;
        Node b = first;

        //

        // move second pointer to the correct position
        for (int i = 0; i < k - 1; i++){
            b = b.next;
            // validate
            if (b == null)
                throw new IllegalArgumentException();
        }

        // move 2 pointers till the end
        while (b != last) {
            a = a.next;
            b = b.next;
        }

        return a.value;

    }

    /**
     * we can define two pointers that reference the first node initially.
     * Then, we use a loop to move these pointers forward. In every iteration,
     * we move the first pointer one step and the second pointer two steps
     * forward. The moment the second pointer hits the tail node, the first
     * node is pointing the middle node.
     * Now, let’s expand our problem. What if the list has an even number of
     * nodes?
     *
     * We see the same pattern. In every step, the number of nodes increases
     * by two whereas the position of the middle node is increasing by one.
     * The only difference is that here we need to return two middle nodes.
     * That’s easy. Once we find the left middle node, we’ll also return the node
     * next to it.
     * How do we know if the list has an even or odd number of items? We can
     * declare a count variable and increment it in each step. But we don’t
     * really need this. If the list has an even number of nodes, at the end of
     * the last iteration, the second pointer will reference the tail node; otherwise, it’ll be null.
     */
    public void printMiddle() {
        if (isEmpty())
            throw new IllegalArgumentException();

        var a = first;
        var b  = first;

        while (b != last && b.next != last) {
            b = b.next.next;
            a = a.next;
        }

        if (b == last)
            System.out.println(a.value);
        else
            System.out.println(a.value + ", " + a.next.value);
    }

    /**
     * use two pointers (slow and fast) to traverse the list. Move the slow
     * pointer one step forward and the fast pointer two steps forward. If
     * there’s a loop, at some point, the fast pointer will meet the slow pointer
     * and overtake it. Draw this on a paper and see it for yourself. This
     * algorithm is called Floyd’s Cycle-finding Algorithm.
     *
     */
    public boolean hasLoop() {
        Node slow = first;
        Node fast = first;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast)
                return true;
        }
        return false;
    }
}
