package be.pxl.student.eventplatform.service;

//TODO vul de klasse aan

import be.pxl.student.eventplatform.api.RegistrationRequest;
import be.pxl.student.eventplatform.api.StatisticsDTO;
import be.pxl.student.eventplatform.domain.*;
import be.pxl.student.eventplatform.exception.EventFullException;
import be.pxl.student.eventplatform.exception.NotFoundException;
import be.pxl.student.eventplatform.repository.AttendeeRepository;
import be.pxl.student.eventplatform.repository.EventRepository;
import be.pxl.student.eventplatform.repository.RegistrationRepository;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;
    private final RegistrationRepository registrationRepository;

    @Autowired
    public EventService(EventRepository eventRepository,  AttendeeRepository attendeeRepository,  RegistrationRepository registrationRepository) {
        this.eventRepository = eventRepository;
        this.attendeeRepository = attendeeRepository;
        this.registrationRepository = registrationRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public RegistrationRequest createRegistration(long eventId, String email) {
        if (eventRepository.findById(eventId).isEmpty()) {
            throw new NotFoundException("The event does not exist");
        }
        if (attendeeRepository.findByEmail(email) == null) {
            throw new NotFoundException("The attendee does not exist");
        }
        if (eventRepository.findById(eventId).get().getCapacity() < eventRepository.findById(eventId).get().availableSeats()) {
            throw new EventFullException("Capacity exceeded");
        }
        Registration registration = new Registration();
        Attendee attendee = attendeeRepository.findByEmail(email);
        registration.setEvent(eventRepository.findById(eventId).get());
        registration.setAttendee(attendee);
        registrationRepository.save(registration);

        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.status = registration.getStatus();
        registrationRequest.fullName = registration.getAttendee().getFullName();
        registrationRequest.email = registration.getAttendee().getEmail();
        registrationRequest.eventName = registration.getEvent().getName();
        registrationRequest.createdAt = registration.getCreatedAt();
        return registrationRequest;
    }


    //TODO USER STORY 7 — Statistieken opvragen
    // Gebruik zoveel mogelijk Java Streams API voor de berekeningen

    // TIP zoals beschreven in de java api doc voor het gebruik van Collectors
    //    // Accumulate names into a List
    //    List<String> list = people.stream()
    //            .map(Person::getName)
    //            .collect(Collectors.toList());
    //
    //    // Accumulate names into a TreeSet
    //    Set<String> set = people.stream()
    //            .map(Person::getName)
    //            .collect(Collectors.toCollection(TreeSet::new));
    //
    //    // Convert elements to strings and concatenate them, separated by commas
    //    String joined = things.stream()
    //            .map(Object::toString)
    //            .collect(Collectors.joining(", "));
    //
    //    // Compute sum of salaries of employee
    //    int total = employees.stream()
    //            .collect(Collectors.summingInt(Employee::getSalary));
    //
    //    // Group employees by department
    //    Map<Department, List<Employee>> byDept = employees.stream()
    //            .collect(Collectors.groupingBy(Employee::getDepartment));
    //
    //    // Compute sum of salaries by department
    //    Map<Department, Integer> totalByDept = employees.stream()
    //            .collect(Collectors.groupingBy(Employee::getDepartment,
    //                    Collectors.summingInt(Employee::getSalary)));
    //
    //    // Partition students into passing and failing
    //    Map<Boolean, List<Student>> passingFailing = students.stream()
    //            .collect(Collectors.partitioningBy(s -> s.getGrade() >= PASS_THRESHOLD));

    public StatisticsDTO getStatistics() {
        StatisticsDTO statistics = new StatisticsDTO();
        List<Event> events = eventRepository.findAll();
        List<Registration> registrations = registrationRepository.findAll();

        //TODO StatusCounts -> I tried...
//        Map<RegistrationStatus, Long> statusCount = registrations.stream()
//                .collect(Collectors.groupingBy(Event::getRegistrations, Collectors.summingLong(Registration::getStatus)));
        //TODO MostPopularVenueArea
        Map<VenueArea, Long> mostpopularVenueAreaMap = events.stream()
                .collect(Collectors.groupingBy(Event::getVenueArea, Collectors.summingLong(e -> 1)));
        String mostpopularVenueArea = mostpopularVenueAreaMap.values().stream().sorted().findFirst().get().toString();
        statistics.setMostPopularVenueArea(mostpopularVenueArea);
        //TODO TopEventByCheckIns
        String biggestEvent = events.stream()
                .max(Comparator.comparingInt(event -> event.getRegistrations().size())).toString();
        statistics.setTopEventByCheckIns(biggestEvent);
        //TODO AverageRegistrationsPerEvent
        Double averageRegistrations = events.stream()
                .mapToDouble(event -> event.getRegistrations().size() / events.stream().count()).sum();
        statistics.setAverageRegistrationsPerEvent(averageRegistrations);
        //TODO SoldOutEvents
        long soldOutEvents = events.stream()
                .filter(event -> event.availableSeats() == 0)
                .count();
        statistics.setSoldOutEvents(soldOutEvents);

        return statistics;
    }

}
