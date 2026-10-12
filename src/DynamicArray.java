import java.util.Arrays;

public class DynamicArray {
    private int[] data = new int[10];
    private int size;
    private long steps;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public long getSteps() {
        return steps;
    }

    public void resetSteps() {
        steps = 0;
    }

    public void clear() {
        size = 0;
    }

    private void ensureCapacity() {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of range (size " + size + ").");
        }
    }

    public void insert(int value) {
        ensureCapacity();
        data[size++] = value;
        steps++;
    }

    public void insertAt(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of range (size " + size + ").");
        }
        ensureCapacity();
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
            steps++;
        }
        data[index] = value;
        size++;
        steps++;
    }

    public int deleteAt(int index) {
        checkIndex(index);
        int removed = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
            steps++;
        }
        size--;
        steps++;
        return removed;
    }

    public int indexOf(int value) {
        for (int i = 0; i < size; i++) {
            steps++;
            if (data[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public int[] toArray() {
        return Arrays.copyOf(data, size);
    }

    public void sort() {
        Arrays.sort(data, 0, size);
    }

    public boolean isSorted() {
        for (int i = 1; i < size; i++) {
            if (data[i - 1] > data[i]) {
                return false;
            }
        }
        return true;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Array is empty.");
            return;
        }
        System.out.println(Arrays.toString(toArray()) + "  (size: " + size + ")");
    }
}
