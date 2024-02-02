// LinkedNumberSequence.java


/****************************************************************

LinkedNumberSequence represents a sequence of real numbers.
Such a sequence is defined by the interface NumberSequence.
The class uses linked nodes to store the numbers in the sequence.

Author
Fadil Galjic

****************************************************************/

public class LinkedNumberSequence implements NumberSequence {
    private class Node {
        public double number;
        public Node next;

        public Node(double number) {
            this.number = number;
            next = null;
        }
    }

    private Node first;

    public LinkedNumberSequence(double[] numbers) {
        if (numbers.length < 2)
            throw new IllegalArgumentException("not a sequence");

        first = new Node(numbers[0]);
        Node n = first;
        for (int i = 1; i < numbers.length; i++) {
            n.next = new Node(numbers[i]);
            n = n.next;
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        Node n = first;
        while (n.next != null) {
            s.append(n.number).append(", ");
            n = n.next;
        }
        s.append(n.number);

        return s.toString();
    }

    //add code here

    public int length() {
        int count = 0;
        Node n = first;
        while (n != null) {
            count++;
            n = n.next;
        }
        return count;
    }

    public double upperBound() {
        double max = first.number;
        Node n = first;
        while (n != null) {
            max = Math.max(max, n.number);
            n = n.next;
        }
        return max;
    }

    public double lowerBound() {
        double min = first.number;
        Node n = first;
        while (n != null) {
            min = Math.min(min, n.number);
            n = n.next;
        }
        return min;
    }

    public int positionOf(double number) {
        Node n = first;
        int position = 0;
        while (n != null) {
            if (n.number == number) {
                return position;
            }
            position++;
            n = n.next;
        }
        return -1;
    }

    public boolean isIncreasing() {
        Node n = first;
        while (n.next != null) {
            if (n.number >= n.next.number) {
                return false;
            }
            n = n.next;
        }
        return true;
    }

    public boolean isDecreasing() {
        Node n = first;
        while (n.next != null) {
            if (n.number <= n.next.number) {
                return false;
            }
            n = n.next;
        }
        return true;
    }

    public boolean contains(double number) {
        Node n = first;
        while (n != null) {
            if (n.number == number) {return true;
            }
            n = n.next;
        }
        return false;
    }

    public void add(double number) {
        Node newNode = new Node(number);
        if (first == null) {
            first = newNode;
        } else {
            Node n = first;
            while (n.next != null) {
                n = n.next;
            }
            n.next = newNode;
        }
    }

    public void insert(int position, double number) {
        if (position < 0 || position > length()) {
            throw new IllegalArgumentException("Invalid position");
        }
        Node newNode = new Node(number);
        if (position == 0) {
            newNode.next = first;
            first = newNode;
        } else {
            Node prev = getNodeAtPosition(position - 1);
            newNode.next = prev.next;
            prev.next = newNode;
        }
    }
    @Override
    public double numberAt(int position) throws IndexOutOfBoundsException {
        Node positionOf = getNodeAtPosition(position);
        if (positionOf == null) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
        return positionOf.number;
    }

    public void removeAt(int position) {
        if (position < 0 || position >= length()) {
            throw new IllegalArgumentException("Invalid position");
        }
        if (length() <= 2) {
            throw new IllegalStateException("There must be more than two numbers in the sequence");
        }
        if (position == 0) {
            first = first.next;
        } else {
            Node prev = getNodeAtPosition(position - 1);
            prev.next = prev.next.next;
        }
    }

    private Node getNodeAtPosition(int position) {
        Node n = first;
        for (int i = 0; i < position && n != null; i++) {
            n = n.next;
        }
        return n;
    }

    public double[] asArray() {
        double[] array = new double[length()];
        Node n = first;
        for (int i = 0; i < array.length; i++) {
            array[i] = n.number;
            n = n.next;
        }
        return array;
    }
}
               
