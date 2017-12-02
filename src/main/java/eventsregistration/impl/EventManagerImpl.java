package eventsregistration.impl;

import eventsregistration.EventManager;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class EventManagerImpl implements EventManager {

    private volatile List<Instant> eventsTime;
    private final Object lock = new Object();

    public EventManagerImpl(){
        eventsTime = new ArrayList<>();
    }

    @Override
    public void considerEvent() {
        synchronized (lock) {
            eventsTime.add(Instant.now());
        }
    }

    @Override
    public long getRecentEventsCountPerMinute() {
        return getEventsCount(ChronoUnit.MINUTES);
    }

    @Override
    public long getRecentEventsCountPerHour() {
        return getEventsCount(ChronoUnit.HOURS);
    }

    @Override
    public long getRecentEventsCountPerDay() {
        return getEventsCount(ChronoUnit.DAYS);
    }

    private long getEventsCount(ChronoUnit chronoUnit){

        Instant currentInstant = Instant.now();
        Instant instantWithOffset = currentInstant.minus(1, chronoUnit);

        for (int i = eventsTime.size() - 1; i >= 0; i--) {
            if (eventsTime.get(i).isBefore(instantWithOffset)){
                return eventsTime.size() - i - 1;
            }
        }

        return eventsTime.size();

    }
}
