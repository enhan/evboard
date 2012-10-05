/**
 * 
 */
package eu.enhan.evboard.test.core.event;

import org.joda.time.DateTime;
import static org.junit.Assert.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.enhan.evboard.core.event.Event;

/**
 * @author manu
 * 
 */
public class EventTest {

	private static final Logger LOG = LoggerFactory.getLogger(EventTest.class);
	
	@Test
	public void eventInTheFuture() {
		// given
		Event ev = Event
				.newEventBuilder()
				.withName("test")
				.withDates(DateTime.now().plusHours(2),
						DateTime.now().plusHours(4)).build();

		// when
		// then
		assertFalse(ev.isPast());
		assertTrue(ev.isPending());
		assertFalse(ev.isInProcess());
		
	}
	
	public void eventInThePast(){
		// given
		Event ev = Event.newEventBuilder().withName("test").withDates(DateTime.now().minusHours(6), DateTime.now().minus(5)).build();
		
		// when
		// then
		assertTrue(ev.isPast());
		assertFalse(ev.isInProcess());
		assertFalse(ev.isInProcess());
		
	}

	
}
