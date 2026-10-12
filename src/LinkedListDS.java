public class LinkedListDS {
    private static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    private Node head;
    private int size;
    private long steps;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public long getSteps() {
        return steps;
    }

    public void resetSteps() {
        steps = 0;
    }

    public void insertAtHead(int value) {
        Node node = new Node(value);
        node.next = head;
        head = node;
        size++;
        steps++;
    }

    public void insertAtTail(int value) {
        Node node = new Node(value);
        if (head == null) {
            head = node;
        } else {
            Node current = head;
            steps++;
            while (current.next != null) {
                current = current.next;
                steps++;
            }
            current.next = node;
        }
        size++;
    }

    public void insertAt(int position, int value) {
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("Position " + position + " is out of range (size " + size + ").");
        }
        if (position == 0) {
            insertAtHead(value);
            return;
        }
        Node current = head;
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
            steps++;
        }
        Node node = new Node(value);
        node.next = current.next;
        current.next = node;
        size++;
        steps++;
    }

    public boolean deleteValue(int value) {
        if (head == null) {
            return false;
        }
        if (head.value == value) {
            head = head.next;
            size--;
            steps++;
            return true;
        }
        Node current = head;
        while (current.next != null) {
            steps++;
            if (current.next.value == value) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int search(int value) {
        Node current = head;
        int position = 0;
        while (current != null) {
            steps++;
            if (current.value == value) {
                return position;
            }
            current = current.next;
            position++;
        }
        return -1;
    }

    public void display() {
        if (head == null) {
            System.out.println("Linked list is empty.");
            return;
        }
        StringBuilder sb = new StringBuilder("HEAD -> ");
        Node current = head;
        while (current != null) {
            sb.append(current.value).append(" -> ");
            current = current.next;
        }
        sb.append("NULL  (size: ").append(size).append(")");
        System.out.println(sb);
    }
}
