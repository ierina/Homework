package Task3;

import java.util.*;

public class DuplicateRemover {
    public static <T> List<T> removeDuplicatesPreserveOrder(List<T> input) { //Universal method (generic), works with any data type — T can be an Integer, String, Double
        return new ArrayList<>(new LinkedHashSet<>(input)); //Deletes duplicates and saves the order
    }

    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(3, 5, 3, 2, 5, 1);
        intList = removeDuplicatesPreserveOrder(intList);
        System.out.println("Unique Integers : " + intList);

        List<String> strList = Arrays.asList("apple", "banana", "apple", "orange", "banana", "kiwi");
        strList = removeDuplicatesPreserveOrder(strList);
        System.out.println("Unique Strings : " + strList);

    }
}