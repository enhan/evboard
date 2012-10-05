/**
 * 
 */
package eu.enhan.evboard.core.event;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;

import static com.google.common.base.Preconditions.*;

/**
 * @author manu
 *
 */
public class Event {
	
	public static EventBuilder newEventBuilder(){
		return new EventBuilder();
	}
	
	Event(String name, Interval iv){
		this.name = checkNotNull(name);
		this.interval = checkNotNull(iv);
	}
	
	
	private String name;
	private String description;
	private Interval interval;
	
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Interval getInterval() {
		return interval;
	}
	
	public boolean isPast(){
		return interval.isBeforeNow();
	}

	public boolean isPending(){
		return interval.isAfterNow();
	}
	
	public boolean isInProcess(){
		return interval.containsNow();
	}

	public Duration beforeStart(){
		return new Duration(DateTime.now(), interval.getStart());
	}

	/**
	 * 
	 * @author manu
	 *
	 */
	public static final class EventBuilder{
		
		private String name;
		private String description;
		private Interval interval;
		
		private EventBuilder() {
		}
		
		public EventBuilder withName(String name){
			this.name = checkNotNull(name);
			checkArgument(!name.isEmpty());
			return this;
		}
		
		public EventBuilder withDescription(String description){
			this.description = checkNotNull(description);
			return this;
		}
		
		public EventBuilder withInterval(Interval interval){
			this.interval = checkNotNull(interval);
			return this;
		}
		
		public EventBuilder withDates(DateTime start, DateTime end){
			start.isBefore(end);
			this.interval = new Interval(start, end);
			return this;
		}
		
		public Event build(){
			checkNotNull(name);
			checkNotNull(interval);
			
			Event res = new Event(name, interval);
			
			return res;
		}
	}

}
