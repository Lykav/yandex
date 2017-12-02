package eventsregistration;

/**
 * The <tt>Event Manager</tt> interface provides four methods for registering events and getting the number of events per time unit
 */
public interface EventManager {

    /**
     * Registers event. Doesn't have arguments in scope of task.
     */
    void considerEvent();

    /**
     * Returns number of events per minute
     * @return number of events per minute
     */
    long getRecentEventsCountPerMinute();

    /**
     * Returns number of events per hour
     * @return number of events per hour
     */
    long getRecentEventsCountPerHour();

    /**
     * Returns number of events per day
     * @return number of events per day
     */
    long getRecentEventsCountPerDay();
}
