import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HashTableExercises {
    /*
    Find the most repeated element in an array of integers. W(A variation of this exercise is finding
the most repeated word in a sentence. The algorithm is the same. Here
we use an array of numbers for simplicity.)
Input: [1, 2, 2, 3, 3, 3, 4]
Output: 3
     */

    public int mostFrequent(int[] arr) {
        /*
        To find the most frequent item in an array, we have to count the
        number of times each item has been repeated. We can use a hash
        table to store the items and their frequencies.
         */

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int number : arr) {
            Integer count = map.getOrDefault(number, 0);
            map.put(number,++count);
        }

        /*
        Once we've populated(fill) our hash table, we need to iterate over all
        key/value pairs and find the one with the highest frequency.
         */
        int max = Integer.MIN_VALUE;
        int result = arr[0];

        for (Map.Entry<Integer, Integer> item : map.entrySet()) {
            if (item.getValue() > max){
                max = item.getValue();
                result = item.getKey();
            }
        }

        // Runtime complexity of this method is O(n) because we have to
       // iterate the entire array to fill our hash table.
        return result;
    }

    /*
    Given an array of integers, return indices of the two numbers such
that they add up to a specific target.Input: [2, 7, 11, 15] - target = 9
Output: [0, 1] (because 2 + 7 = 9)
Assume that each input has exactly one solution, and you may not use
the same element twice.
     */

    public int[] twoSum(int[] arr, int target) {
        /*
        if a + b = target, then b = target - a;

        In hash table we store numbers and their indexes.
        If we find two numbers that add up to the target, we simply
        return their indexes.
         */

        // O(n)
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(target - arr[i])) {
                return new int[]{map.get(target - arr[i]), i};
            }
            map.putIfAbsent(arr[i], i);
        }
        return new int[0];

        // Time complexity of this method is O(n) because we need to iterate
        // the array only once.
    }

    /*
    - Given an array of integers, count the number of unique pairs of
integers that have difference k.
Input: [1, 7, 5, 9, 2, 12, 3] K=2
Output: 4
We have four pairs with difference 2: (1, 3), (3, 5), (5, 7), (7, 9). Note
that we only want the number of these pairs, not the pairs themselves.
     */
    // O(n)
    public int countPairsWithDiff(int[] numbers, int difference) {
        // For a given number (a) and difference (diff), number (b) can be:
        //
        // b = a + diff
        // b = a - diff
        //
        // We can iterate over our array of numbers, and for each number,
        // check to see if we have (current + diff) or (current - diff).
        // But looking up items in an array is an O(n) operation. With this
        // algorithm, we need two nested loops (one to pick a,
        // and the other to find b). This will be an O(n^2) operation.
        //
        // We can optimize this by using a set. Sets are like hash tables
        // but they only store keys. We can look up a number in constant time.
        // No need to iterate the array to find it.

        // So, we start by adding all the numbers to a set for quick look up.
        Set<Integer> set = new HashSet<>();
        for (var number : numbers)
            set.add(number);

        // Now, we iterate over the array of numbers one more time,
        // and for each number check to see if we have (a + diff) or
        // (a - diff) in our set.
        //
        // Once we're done, we should remove this number from our set
        // so we don't double count it.
        var count = 0;
        for (var number : numbers) {
            if (set.contains(number + difference))
                count++;
            if (set.contains(number - difference))
                count++;
            set.remove(number);
        }

        // Time complexity of this method is O(n).

        return count;
    }
}
