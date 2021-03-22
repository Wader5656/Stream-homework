package countries;

import java.io.IOException;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.*;

import java.time.ZoneId;

public class Homework2 {

    private List<Country> countries;

    public Homework2() {
        countries = new CountryRepository().getAll();
    }

    /**
     * Returns the longest country name translation.
     */
    public Optional<String> streamPipeline1() {

        return countries.stream()
                .flatMap(c -> c.getTranslations().values().stream())
                .max(Comparator.comparingInt(String::length));
    }

    /**
     * Returns the longest Italian (i.e., {@code "it"}) country name translation.
     */
    public Optional<String> streamPipeline2() {

        return countries.stream()
                .flatMap(c -> c.getTranslations().entrySet().stream()
                        .filter(ck -> ck.getKey().equals("it")))
                .map(cv -> cv.getValue())
                .max(Comparator.comparingInt(String::length));
    }

    /**
     * Prints the longest country name translation together with its language code in the form language=translation.
     */
    public void streamPipeline3() {
        countries.stream()
                .flatMap(c -> c.getTranslations().entrySet().stream())
                .max(Comparator.comparingInt(cv -> cv.getValue().length()))
                .stream()
                .forEach(System.out::println);
    }

    /**
     * Prints single word country names (i.e., country names that do not contain any space characters).
     */
    public void streamPipeline4() {
        countries.stream()
                .map(Country::getName)
                .filter(cn -> !cn.contains(" "))
                .forEach(System.out::println);
    }

    /**
     * Returns the country name with the most number of words.
     */
    public Optional<String> streamPipeline5() {

        return countries.stream()
                .max(Comparator.comparingInt(c -> c.getName().split(" ").length))
                .map(c -> c.getName());
    }

    /**
     * Returns whether there exists at least one capital that is a palindrome.
     */
    public boolean streamPipeline6() {

        return countries.stream()
                .anyMatch(c -> c.getCapital().equals(new StringBuilder(c.getCapital()).reverse().toString())
                        && !c.getCapital().isEmpty());
    }

    /**
     * Returns the country name with the most number of {@code 'e'} characters ignoring case.
     */
    public Optional<String> streamPipeline7() {

        return countries.stream()
                .max(Comparator.comparingInt(c -> {
                    return (int) c.getName().toLowerCase()
                            .chars()
                            .filter(k -> k == 'e')
                            .count();
                }))
                .map(c -> c.getName());
    }

    /**
     *  Returns the capital with the most number of English vowels (i.e., {@code 'a'}, {@code 'e'}, {@code 'i'}, {@code 'o'}, {@code 'u'}).
     */
    public Optional<String> streamPipeline8() {

        return countries.stream()
                .max(Comparator.comparingInt(c ->{
                    return (int) c.getCapital().toLowerCase()
                            .chars()
                            .filter(k -> k == 'e' || k == 'a' || k == 'i' || k == 'o' || k == 'u')
                            .count();
                }))
                .map(c -> c.getCapital());
    }

    /**
     * Returns a map that contains for each character the number of occurrences in country names ignoring case.
     */
    public Map<Character, Long> streamPipeline9() {
        // TODO
        return null;
    }

    /**
     * Returns a map that contains the number of countries for each possible timezone.
     */
    public Map<ZoneId, Long> streamPipeline10() {
        
        return countries.stream()

                .collect(groupingBy(c -> c.getTimezones().get(0),counting()));
    }

    /**
     * Returns the number of country names by region that starts with their two-letter country code ignoring case.
     */
    public Map<Region, Long> streamPipeline11() {

        return countries.stream()
                .filter(c -> c.getName().toLowerCase().charAt(0) == c.getCode().toLowerCase().charAt(0)
                        && c.getName().toLowerCase().charAt(1) == c.getCode().toLowerCase().charAt(1))
                .collect(groupingBy(Country::getRegion,counting()));
    }

    /**
     * Returns a map that contains the number of countries whose population is greater or equal than the population average versus the the number of number of countries with population below the average.
     */
    public Map<Boolean, Long> streamPipeline12() {
        // TODO
        return null;
    }

    /**
     * Returns a map that contains for each country code the name of the corresponding country in Portuguese ({@code "pt"}).
     */
    public Map<String, String> streamPipeline13() {
        // TODO
        return null;
    }

    /**
     * Returns the list of capitals by region whose name is the same is the same as the name of their country.
     */
    public Map<Region, List<String>> streamPipeline14() {
        // TODO
        return null;
    }

    /**
     *  Returns a map of country name-population density pairs.
     */
    public Map<String, Double> streamPipeline15() {
        // TODO
        return null;
    }

}
