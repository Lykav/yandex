package eventsregistration;

import eventsregistration.impl.EventManagerImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EventManagerMultiThreadTest {

    private EventManager eventManager;
    private ExecutorService executor;

    @Before
    public void init(){
        eventManager = new EventManagerImpl();
    }

    @Test
    public void testEventCountMultiThread() throws InterruptedException {
        executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 1000; j++) {
                    eventManager.considerEvent();
                }
            });
        }

        executor.shutdown();
        boolean done = executor.awaitTermination(1, TimeUnit.MINUTES);
        assertTrue(done);
        assertEquals("Events count returned by getRecentEventsCountPerMinute doesn't match with correct result",10000 ,eventManager.getRecentEventsCountPerMinute());
    }

    @Test
    public void testEventCountMultiThreadWithDelay() throws InterruptedException {
        executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 1000; j++) {
                    if (j==500){
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    eventManager.considerEvent();
                }
            });
        }

        executor.shutdown();
        boolean done = executor.awaitTermination(1, TimeUnit.MINUTES);
        assertTrue(done);
        assertEquals("Events count returned by getRecentEventsCountPerHour doesn't match with correct result",10000 ,eventManager.getRecentEventsCountPerHour());
    }
}
