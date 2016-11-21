package sp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SecondPartTasks {

    private SecondPartTasks() {}

    // Найти строки из переданных файлов, в которых встречается указанная подстрока.
    public static List<String> findQuotes(List<String> paths, CharSequence sequence) {
        return paths
                .stream()
                .flatMap(path -> {
                    try {
                        return Files.lines(Paths.get(path));
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .filter(line -> line.contains(sequence))
                .collect(Collectors.toList());
    }

    // В квадрат с длиной стороны 1 вписана мишень.
    // Стрелок атакует мишень и каждый раз попадает в произвольную точку квадрата.
    // Надо промоделировать этот процесс с помощью класса java.util.Random и посчитать, какова вероятность попасть в мишень.
    public static double piDividedBy4() {
        Random rand = new Random();
        final int it = 800000;
        return Stream
                .generate(() ->
                        new AbstractMap.SimpleEntry<>(rand.nextDouble(), rand.nextDouble()))
                .limit(it)
                .mapToInt(d -> {
                    return Math.pow(d.getKey() - 0.5, 2) + Math.pow(d.getValue() - 0.5, 2)
                            <=  Math.pow(0.5, 2) ? 1 : 0;})
                .filter(i -> (i == 1))
                .count() / (double)it;
    }

    // Дано отображение из имени автора в список с содержанием его произведений.
    // Надо вычислить, чья общая длина произведений наибольшая.
    public static String findPrinter(Map<String, List<String>> compositions) {
        return compositions
                .entrySet()
                .stream()
                .map(e -> new AbstractMap.SimpleEntry<>(
                        e.getKey(),
                        e.getValue().stream().reduce(0, (acc, x) ->
                                acc + x.length(), Integer::sum)
                ))
                .max((a, b) -> a.getValue().compareTo(b.getValue()))
                .map(AbstractMap.SimpleEntry::getKey)
                .orElse(null);
    }

    // Вы крупный поставщик продуктов. Каждая торговая сеть делает вам заказ в виде Map<Товар, Количество>.
    // Необходимо вычислить, какой товар и в каком количестве надо поставить.
    public static Map<String, Integer> calculateGlobalOrder(List<Map<String, Integer>> orders) {
        return orders
                .stream()
                .flatMap(x -> x.entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.reducing(0, Map.Entry::getValue, Integer::sum)));
    }
}