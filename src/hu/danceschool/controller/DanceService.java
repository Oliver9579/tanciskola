package hu.danceschool.controller;

import hu.danceschool.model.domain.Dance;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DanceService {

    private final List<Dance> dances;

    public DanceService(List<Dance> dances) {
        this.dances = dances;
    }

    public String getFirstDanceName() {
        int first = 0;
        return dances.get(first).getDanceName();
    }
    public String getLastDanceName() {
        int last = dances.size() - 1;
        return dances.get(last).getDanceName();
    }

    public Long countDances(String danceName) {
        return dances.stream()
                .filter(i -> i.isDance(danceName))
                .count();

    }
    public String getDancesByGirlName(String girlName) {
        return dances.stream()
                .filter(i -> i.isGirl(girlName))
                .map(i -> i.getDanceName())
                .collect(Collectors.joining(", "));
    }

    public String getBoyName(String danceName, String girlName) {
        return getBoyNameByDanceAndGirlName(danceName, girlName)
                .map(boyName -> String.format("A %s bemutatóján %s párja %s volt.", danceName, girlName, boyName))
                .orElse(String.format("%s nem táncolt %s-t.", girlName, danceName));
    }

    private Optional<String> getBoyNameByDanceAndGirlName(String danceName, String girlName) {
        return dances.stream()
                .filter(i -> i.isDance(danceName) && i.isGirl(girlName))
                .map(i -> i.getBoyName())
                .findFirst();
    }

    public List<String> getNames() {
        return List.of(String.format("Lányok: %s", getNames(i -> i.getGirlName())),String.format("Fiúk: %s", getNames(i -> i.getBoyName())));
    }

    private String getNames(Function<Dance, String> names) {
        return dances.stream()
                .map(names)
                .distinct()
                .collect(Collectors.joining(", "));
    }

    private Map<String, Long> createNameDanceCountMap() {

    }
}
