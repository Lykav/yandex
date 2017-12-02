package eventsregistration;

import eventsregistration.EventManager;
import eventsregistration.impl.EventManagerImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EventManagerTestOneThread {

    private EventManager eventManager;

    @Before
    public void init(){
        eventManager = new EventManagerImpl();
    }

    @Test
    public void getRecentEventsCountPerMinutes() throws Exception {
        for (int i = 0; i < 10; i++) {
            eventManager.considerEvent();
        }
        assertEquals("Events count returned by getRecentEventsCountPerMinute doesn't match with correct result",10 ,eventManager.getRecentEventsCountPerMinute());
    }

    @Test
    public void getRecentEventsCountPerHour() throws Exception {
        for (int i = 0; i < 10; i++) {
            eventManager.considerEvent();
        }
        assertEquals("Events count returned by getRecentEventsCountPerHour doesn't match with correct result",10 ,eventManager.getRecentEventsCountPerHour());
    }

    @Test
    public void getRecentEventsCountPerDay() throws Exception {
        for (int i = 0; i < 10; i++) {
            eventManager.considerEvent();
        }
        assertEquals("Events count returned by getRecentEventsCountPerDay doesn't match with correct result",10 ,eventManager.getRecentEventsCountPerDay());
    }

    @Test
    public void testWithDelay() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            eventManager.considerEvent();
        }
        Thread.sleep(61000);
        for (int i = 0; i < 10; i++) {
            eventManager.considerEvent();
        }

        assertEquals("Events count returned by getRecentEventsCountPerMinute doesn't match with correct result in case with delay",10 ,eventManager.getRecentEventsCountPerMinute());
    }

    @Test
    public void testWithoutEvents(){
        assertEquals("Events count returned by getRecentEventsCountPerMinute doesn't match with correct result in case without events",0 ,eventManager.getRecentEventsCountPerMinute());
        assertEquals("Events count returned by getRecentEventsCountPerHour doesn't match with correct result in case without events",0 ,eventManager.getRecentEventsCountPerHour());
        assertEquals("Events count returned by getRecentEventsCountPerDay doesn't match with correct result in case without events",0 ,eventManager.getRecentEventsCountPerDay());

    }

}