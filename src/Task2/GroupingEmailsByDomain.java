package Task2;

import java.util.*;
import java.util.stream.Collectors;

public class GroupingEmailsByDomain {
    public static void main(String[] args) {
        List<String> emails = Arrays.asList(
                "alice@gmail.com", "bob@yahoo.com", "carol@gmail.com", "david@hotmail.com", "eve@yahoo.com",
                "frank@gmail.com"
        );

        Map<String, List<String>> grouped = emails.stream()
                .collect(Collectors.groupingBy(
                        email -> email.substring(email.indexOf('@') + 1), // убираем '@'
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream().sorted().collect(Collectors.toList())
                        )
                ));

        // Сортировка по количеству email в домене (по убыванию)
        List<Map.Entry<String, List<String>>> sortedByFrequency = grouped.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))
                .collect(Collectors.toList());

        // Вывод результата
        for (Map.Entry<String, List<String>> entry : sortedByFrequency) {
            System.out.println(entry.getKey() + ":");
            entry.getValue().forEach(email -> System.out.println("- " + email));
        }
    }
}