package agenda;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Description : An agenda that stores events
 */
public class Agenda {
    private Set<Event> events = new HashSet<>();

    /**
     * Adds an event to this agenda
     *
     * @param e the event to add
     */
    public void addEvent(Event e) {
        events.add(e);
    }

    /**
     * Computes the events that occur on a given day
     *
     * @param day the day toi test
     * @return a list of events that occur on that day
     */
    public List<Event> eventsInDay(LocalDate day) {
        List<Event> eventsOfTheDay = new ArrayList<>();
        for (Event e : events) {
            if (e.isInDay(day)) {
                eventsOfTheDay.add(e);
            }
        }
        return eventsOfTheDay;
    }
    /**
     * Trouver les événements de l'agenda en fonction de leur titre
     * @param title le titre à rechercher
     * @return les événements qui ont le même titre
     */
    public List<Event> findByTitle(String title) {
        List<Event> eventList = new ArrayList<>();
        for (Event e: events) {
            if (e.getTitle().equals(title)) eventList.add(e);
        }
        return eventList;
    }

}
