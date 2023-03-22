import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CharFinder {

    public char findFirstNonRepeatingChar(String str) {
        // key - char, value - amount of char appearance in String
        Map<Character, Integer> map = new HashMap<>();


        for (char c : str.toCharArray()) {
            Integer count = map.getOrDefault(c, 0);
            map.put(c, ++count);
        }

        for (char ch : str.toCharArray()) {
            if (map.get(ch) == 1)
                return ch;
        }

        return Character.MIN_VALUE;
    }

    public char findFirstRepeatedChar(String str) {
        Set<Character> set = new HashSet<>();

        for (char ch : str.toCharArray()) {
            if (set.contains(ch))
                return ch;

            set.add(ch);
        }

        return Character.MIN_VALUE;
    }
}
