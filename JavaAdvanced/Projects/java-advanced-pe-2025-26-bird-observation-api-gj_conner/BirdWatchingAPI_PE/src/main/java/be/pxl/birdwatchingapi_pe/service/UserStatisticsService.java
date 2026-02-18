package be.pxl.birdwatchingapi_pe.service;

import be.pxl.birdwatchingapi_pe.domain.Observation;
import be.pxl.birdwatchingapi_pe.api.FamilyObservationsDto;
import be.pxl.birdwatchingapi_pe.api.MostObservedFamily;
import be.pxl.birdwatchingapi_pe.api.MostObservedSpecies;
import be.pxl.birdwatchingapi_pe.api.UserStatisticsDto;
import be.pxl.birdwatchingapi_pe.repository.ObservationRepository;
import be.pxl.birdwatchingapi_pe.util.DurationFormatter;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserStatisticsService {

    private final ObservationRepository observationRepository;
    private final DurationFormatter durationFormatter;

    public UserStatisticsService(ObservationRepository observationRepository, DurationFormatter durationFormatter) {
        this.observationRepository = observationRepository;
        this.durationFormatter = durationFormatter;
    }

    public UserStatisticsDto getStatistics(String email) {
        List<Observation> observationList = observationRepository.findAllByUserEmailOrderByTimestampAsc(email);
        UserStatisticsDto userStatistics = new UserStatisticsDto();

        if (observationList.isEmpty()) {
            userStatistics.setTotalObservations(0);
            userStatistics.setUniqueSpecies(0);
            userStatistics.setUniqueFamilies(0);
            userStatistics.setAverageTimeBetweenObservations(
                    durationFormatter.format(Duration.ZERO)
            );
            userStatistics.setMostObservedSpecies(null);
            userStatistics.setMostObservedFamily(List.of());
            return userStatistics;
        }

        userStatistics.setTotalObservations(observationList.size());

        userStatistics.setUniqueSpecies((int) observationList.stream()
                .map(observation -> observation.getSpeciesCode())
                .distinct()
                .count());

        userStatistics.setUniqueFamilies((int) observationList.stream()
                .map(observation -> observation.getFamilyCode())
                .distinct()
                .count());

        userStatistics.setAverageTimeBetweenObservations(calculateAverageDuration(observationList));

        userStatistics.setMostObservedSpecies(getMostObservedSpecies(observationList));

        userStatistics.setMostObservedFamily(getMostObservedFamily(observationList));

        return userStatistics;
    }

    private String calculateAverageDuration(List<Observation> observations) {
        if (observations.size() < 2) {
            return durationFormatter.format(Duration.ZERO);
        }

        List<Duration> durations = new ArrayList<>();

        for (int i = 1; i < observations.size(); i++) {
            durations.add(Duration.between(
                    observations.get(i - 1).getTimestamp(),
                    observations.get(i).getTimestamp()
            ));
        }

        Duration average = durations.stream()
                .reduce(Duration.ZERO, Duration::plus)
                .dividedBy(durations.size());

        return durationFormatter.format(average);
    }

    private MostObservedSpecies getMostObservedSpecies(List<Observation> observations) {
        return observations.stream()
                .collect(Collectors.groupingBy(
                        observation -> observation.getSpeciesCode(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> {
                    String speciesCode = entry.getKey();
                    long count = entry.getValue();

                    Observation latest = observations.stream()
                            .filter(o -> o.getSpeciesCode().equals(speciesCode))
                            .max(Comparator.comparing(Observation::getTimestamp))
                            .orElseThrow();

                    MostObservedSpecies dto = new MostObservedSpecies();
                    dto.setSpeciesCode(speciesCode);
                    dto.setFamilyCode(latest.getFamilyCode());
                    dto.setNumberOfObservations((int)count);
                    dto.setLatestObservation(latest.getTimestamp());
                    return dto;
                })
                .orElse(null);
    }

    private List<MostObservedFamily> getMostObservedFamily(List<Observation> observations) {

        String mostObservedFamilyCode = observations.stream()
                .collect(Collectors.groupingBy(
                        Observation::getFamilyCode,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow();

        long totalObservationsForFamily = observations.stream()
                .filter(o -> o.getFamilyCode().equals(mostObservedFamilyCode))
                .count();

        LocalDateTime latestObservation = observations.stream()
                .filter(o -> o.getFamilyCode().equals(mostObservedFamilyCode))
                .map(Observation::getTimestamp)
                .max(LocalDateTime::compareTo)
                .orElseThrow();

        return observations.stream()
                .filter(o -> o.getFamilyCode().equals(mostObservedFamilyCode))
                .map(o -> new MostObservedFamily(
                        o.getSpeciesCode(),
                        o.getFamilyCode(),
                        (int) totalObservationsForFamily,
                        latestObservation
                ))
                .toList();
    }

    public List<FamilyObservationsDto> getObservationCountPerFamily() {
        List<Observation> observations = observationRepository.findAll();

        if (observations.isEmpty()) {
            throw new IllegalArgumentException("No observations found in the system.");
        }

        return observations.stream()
                .collect(Collectors.groupingBy(
                        o -> o.getFamilyCode(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .map(entry -> new FamilyObservationsDto(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(FamilyObservationsDto::getNumberOfObservations).reversed())
                .toList();
    }

}
