import java.util.Arrays;

public class Array {
    private int[] items;
    // count represents total count of numbers in the array, not the size of the array.
    private int count;

    public Array(int length) {
        items = new int[length];
    }

    public void print() {
        for (int i = 0; i < count; i++) {
            System.out.println(items[i]);
        }
    }

    public void insert(int item) {
        resizeIfRequired();

        // Add the new item at the end
        items[count] = item;
        count++;
    }

    public void insertAt(int item, int index) {
        if (index < 0 || index > count)
            throw new IllegalArgumentException();

        resizeIfRequired();

        for (int i = count - 1; i >= index; i--)
            items[i + 1] = items[i];

        items[index] = item;
        count++;
    }

    private void resizeIfRequired() {
        // if the array is full, resize it
        if (items.length == count) {
            // create a new array (twice the size)
            int[] newItems = new int[count * 2];
            // Copy all the existing items
            for (int i = 0; i < count; i++)
                newItems[i] = items[i];
            // Set "items" to this new array
            items = newItems;
        }
    }

    public void removeAt(int index) {
        // Validate the index
        if (index < 0 || index >= count)
            throw new IllegalArgumentException();
        // Shift the items to the left to fill the hole
        for (int i = index; i < count; i++)
            items[i] = items[i + 1];

        count--;
    }

    // O(n)
    public int indexOf(int item) {
        // if we find item, we return index
        // Otherwise, return -1
        for (int i = 0; i < count; i++)
            if (items[i] == item)
                return i;

        return -1;
    }

    public int max() {
        // O(n): Because we have to iterate over
        // the entire array to find the largest
        // number. This number may be at the end
        // of the array (worst case scenario).
        int max = 0;
        for (int item : items)
            if (item > max)
                max = item;

        return max;
    }

    public void reverse() {
        int [] newItems = new int[count];
        for (int i = 0; i < count; i++)
            newItems[i] = items[count - i - 1];

        items = newItems;
    }

    public Array intersect(Array other) {
        Array intersection = new Array(count);

        for (int item : items)
            if (other.indexOf(item) >= 0)
                intersection.insert(item);

        return intersection;
    }

    @Override
    public String toString() {
        return "Array{" +
                "items=" + Arrays.toString(items) +
                ", count=" + count +
                '}';
    }
}
