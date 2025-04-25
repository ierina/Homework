package Task2;

import java.util.*;
import java.util.stream.Collectors;

public class GroupingEmailsByDomain {
public static void main(String[] args) {
    List<String> emails = Arrays.asList(
            "alice@gmail.com", "bob@yahoo.com", "carol@gmail.com", "david@hotmail.com",
            "eve@yahoo.com", "frank@gmail.com"
    );

    // grouping emails and domains
    Map<String, List<String>> groupedByDomain = emails.stream()
                                                        .collect(Collectors.groupingBy(
                                                                email -> email.substring(email.indexOf('@') + 1),
                                                                Collectors.collectingAndThen(
                                                                        Collectors.toList(),
                                                                        emailList -> emailList.stream().sorted().toList()//collect(Collectors.toList())
                                                                )
                                                        ));

    // Sorting domains in descending order
    List<Map.Entry<String, List<String>>> sortedByDomainSize = groupedByDomain.entrySet().stream()
                                                                       .sorted((domainEntry1, domainEntry2) ->
                                                                                       Integer.compare(domainEntry2.getValue().size(), domainEntry1.getValue().size()))
                                                                       .toList();

    // output
    sortedByDomainSize.forEach(domainEntry -> {
        String domain = domainEntry.getKey();
        List<String> domainEmails = domainEntry.getValue();

        System.out.println(domain + ":");
        domainEmails.forEach(email -> System.out.println("- " + email));
    });
}
}