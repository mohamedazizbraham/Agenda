package agenda;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class AgendaTest {
    Agenda agenda;

    // November 1st, 2020
    LocalDate nov_1_2020 = LocalDate.of(2020, 11, 1);

    // January 5, 2021
    LocalDate jan_5_2021 = LocalDate.of(2021, 1, 5);

    // November 1st, 2020, 22:30
    LocalDateTime nov_1__2020_22_30 = LocalDateTime.of(2020, 11, 1, 22, 30);

    // 120 minutes
    Duration min_120 = Duration.ofMinutes(120);

    // A simple event
    // November 1st, 2020, 22:30, 120 minutes
    Event simple = new Event("Simple event", nov_1__2020_22_30, min_120);

    // A Weekly Repetitive event ending at a given date
    Repetition fixedTermination = new Termination("Fixed termination weekly", nov_1__2020_22_30, min_120, ChronoUnit.WEEKS, jan_5_2021);

    // A Weekly Repetitive event ending after a give number of occurrrences
    Repetition fixedRepetitions = new Termination("Fixed termination weekly", nov_1__2020_22_30, min_120, ChronoUnit.WEEKS, 10);

    // A daily repetitive event, never ending
    // November 1st, 2020, 22:30, 120 minutes
    Repetition neverEnding = new Repetition("Never Ending", nov_1__2020_22_30, min_120, ChronoUnit.DAYS);

    // Events to test the false issue in the test testIsFreeFor
    Event simpleStartIn = new Event("Simple event", nov_1__2020_22_30.plusMinutes(10), min_120);
    Event simpleFinishIn = new Event("Simple event", nov_1__2020_22_30.minusMinutes(10), min_120);
    Event simpleIncludeIt = new Event("Simple event", nov_1__2020_22_30.minusMinutes(10), min_120.plusMinutes(20));

    // Event to test the true issue in the test testIsFreeFor
    Event otherSimple = new Event("Simple event", nov_1__2020_22_30.plusMinutes(130), min_120);

    @BeforeEach
    public void setUp() {
        agenda = new Agenda();
        agenda.addEvent(simple);
        agenda.addEvent(fixedTermination);
        agenda.addEvent(fixedRepetitions);
        agenda.addEvent(neverEnding);
    }

    @Test
    public void testMultipleEventsInDay() {
        assertEquals(4, agenda.eventsInDay(nov_1_2020).size(), "Il y a 4 événements ce jour là");
        assertTrue(agenda.eventsInDay(nov_1_2020).contains(neverEnding));
    }

    @Test
    public void testFindByTitle() {
        assertEquals(1, agenda.findByTitle("Simple event").size(), "il y a un seul événement: Simple event");
    }

    @Test
    public void testIsFreeFor() {
        assertFalse(agenda.isFreeFor(simpleStartIn), "un événement ne peux pas commencer en meme temps qu'un autre");
        assertFalse(agenda.isFreeFor(simpleFinishIn), "un événement ne peux pas terminer en meme temps qu'un autre");
        assertFalse(agenda.isFreeFor(simpleIncludeIt), "un événement ne peux pas en contenir un autre");
        assertTrue(agenda.isFreeFor(otherSimple), "ce creneau est sense etre libre");
    }
}