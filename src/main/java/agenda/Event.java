package agenda;

import java.time.*;

public class Event {

    /**
     * The myTitle of this event
     */
    private String myTitle;

    /**
     * The starting time of the event
     */
    private LocalDateTime myStart;

    /**
     * The durarion of the event
     */
    private Duration myDuration;


    /**
     * Constructs an event
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     */
    public Event(String title, LocalDateTime start, Duration duration) {
        this.myTitle = title;
        this.myStart = start;
        this.myDuration = duration;
    }

    /**
     * Tests if an event occurs on a given day
     *
     * @param aDay the day to test
     * @return true if the event occurs on that day, false otherwise
     */
    public boolean isInDay(LocalDate aDay) {
        if(aDay.equals(myStart.toLocalDate())){
            return true;
        }
        for (int i = 1; i < myDuration.toDays(); i++) {
            if(aDay.equals(myStart.toLocalDate().plusDays(i))) {
                return true;
            }
        }
        if(aDay.equals(myStart.plusMinutes(myDuration.toMinutes()).toLocalDate())){
            return true;
        }
        return false;
    }

    public String toString(){
        return "Nom de l'event : " + this.myTitle ;
    }

    /**
     * @return the myTitle
     */
    public String getTitle() {
        return myTitle;
    }

    /**
     * @return the myStart
     */
    public LocalDateTime getStart() {
        return myStart;
    }


    /**
     * @return the myDuration
     */
    public Duration getDuration() {
        return myDuration;
    }


}