package Task1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Player {
    String name;
    int score;
    int age;

    public Player(String name, int score, int age){
        this.name = name;
        this.score = score;
        this.age = age;
    }
    public int getScore() {
        return score;
    }
    public int getAge() {
        return age;
    }
    @Override
    public String toString() {
        return name + " - Score: " + score + ", Age: " + age;
    }
}

public class TestPlayerComparator {
    public static void main(String[] args) {
        List<Player> players = Arrays.asList(
                new Player("Alice", 80, 24),
                new Player("Bob", 90, 26),
                new Player("Michael", 95, 30),
                new Player("Helen", 95, 22),
                new Player("Ann", 95, 25),
                new Player("David", 95, 26),
                new Player("Eva", 90, 30),
                new Player("Frank", 90, 27),
                new Player("Grace", 85, 29)
        );

        List<Player> sortedPlayers = players.stream()
                .sorted(Comparator.comparing(Player::getScore).reversed()
                        .thenComparing(Player::getAge))
                .toList();

        System.out.println("Top 3 Players:");
        for(int i = 0; i < 3; i++){
            System.out.println(players.get(i));
        }
    }}
