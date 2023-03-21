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

        // Add the new item at the end
        items[count] = item;
        count++;
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
}
