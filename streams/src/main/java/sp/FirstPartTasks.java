package sp;

import com.google.common.collect.Sets;
import sp.Album;
import java.util.*;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class FirstPartTasks {

    private FirstPartTasks() {}

    // Список названий альбомов
    public static List<String> allNames(Stream<Album> albums) {
        return albums
                .map(Album::getName)
                .collect(Collectors.toList());
    }

    // Список названий альбомов, отсортированный лексикографически по названию
    public static List<String> allNamesSorted(Stream<Album> albums) {
        return albums
                .map(Album::getName)
                .sorted()
                .collect(Collectors.toList());
    }

    // Список треков, отсортированный лексикографически по названию, включающий все треки альбомов из 'albums'
    public static List<String> allTracksSorted(Stream<Album> albums) {
        return albums
                .flatMap(album -> album.getTracks().stream())
                .map(Track::getName)
                .sorted()
                .collect(Collectors.toList());
    }

    // Список альбомов, в которых есть хотя бы один трек с рейтингом более 95, отсортированный по названию
    public static List<Album> sortedFavorites(Stream<Album> s) {
        return s
                .filter(album -> album.getTracks().stream().anyMatch(track -> track.getRating() > 95))
                .sorted((a, b) -> a.getName().compareTo(b.getName()))
                .collect(Collectors.toList());
    }

    // Сгруппировать альбомы по артистам
    public static Map<Artist, List<Album>> groupByArtist(Stream<Album> albums) {
        return albums
                .collect(Collectors.groupingBy(Album::getArtist));
    }

    // Сгруппировать альбомы по артистам (в качестве значения вместо объекта 'Artist' использовать его имя)
    public static Map<Artist, List<String>> groupByArtistMapName(Stream<Album> albums) {
        return albums
                .collect(Collectors.groupingBy(Album::getArtist, Collectors.mapping(Album::getName, Collectors.toList())));

    }

    // Число повторяющихся альбомов в потоке
    public static long countAlbumDuplicates(Stream<Album> albums) {
        return albums
                .collect(Collectors.groupingBy(a->a, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(a -> a.getValue() > 1)
                .count();
    }

    // Альбом, в котором максимум рейтинга минимален
    // (если в альбоме нет ни одного трека, считать, что максимум рейтинга в нем --- 0)
    public static Optional<Album> minMaxRating(Stream<Album> albums) {
        return albums
                .map(album -> new AbstractMap.SimpleEntry<>(album, album
                        .getTracks()
                        .stream()
                        .mapToInt(Track::getRating)
                        .max()
                        .orElse(0)))
                .min((a, b) -> a.getValue().compareTo(b.getValue()))
                .map(AbstractMap.SimpleEntry::getKey);
    }

    // Список альбомов, отсортированный по убыванию среднего рейтинга его треков (0, если треков нет)
    public static List<Album> sortByAverageRating(Stream<Album> albums) {
        return albums
                .map(album -> new AbstractMap.SimpleEntry<>(album, album
                        .getTracks()
                        .stream()
                        .mapToInt(Track::getRating)
                        .average()
                        .orElse(0.0)))
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .map(AbstractMap.SimpleEntry::getKey)
                .collect(Collectors.toList());
    }

    // Произведение всех чисел потока по модулю 'modulo'
    // (все числа от 0 до 10000)
    public static int moduloProduction(IntStream stream, int modulo) {
        return stream
                .reduce(1, (acc, n) -> {
                    return (int)(((long)acc * n) % modulo);
                });
    }

    // Вернуть строку, состояющую из конкатенаций переданного массива, и окруженную строками "<", ">"
    // см. тесты
    public static String joinTo(String... strings) {
        return Arrays
                .stream(strings)
                .collect(Collectors.joining(", ", "<", ">"));
    }

    // Вернуть поток из объектов класса 'clazz'
    public static <R> Stream<R> filterIsInstance(Stream<?> s, Class<R> clazz) {
        return s
                .filter(clazz::isInstance)
                .map(clazz::cast);
    }
}