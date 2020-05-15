package hu.danceschool.model.service;

import hu.danceschool.model.domain.Dance;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataParser {

    public List<Dance> parse(List<String> lines) {
        return IntStream.iterate(0, i -> i + 3).limit(lines.size() / 3)
                .mapToObj(i -> createDance(lines.subList(i, i + 3)))
                .collect(Collectors.toList());
    }

    private Dance createDance(List<String> lines) {
        String danceName = lines.get(0);
        String girlName = lines.get(1);
        String boyName = lines.get(2);
        return new Dance(danceName, girlName, boyName);

    }
}
