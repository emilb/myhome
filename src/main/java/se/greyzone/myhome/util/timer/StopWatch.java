package se.greyzone.myhome.util.timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StopWatch {

	private long initTimeStamp;
	Map<String, TimedEvent> events;
	
	public StopWatch() {
		init();
	}
	
	public void startTimerFor(String name) {
		TimedEvent event = null;
		if (events.containsKey(name))
			event = events.get(name);
		else {
			event = new TimedEvent(name, getCurrentTime());
			events.put(name, event);
		}
	}
	
	public void stopTimerFor(String name) {
		if (!events.containsKey(name))
			return;
		
		events.get(name).setEndTime(getCurrentTime());
	}

	public void addLapTimePoint(String name, String lapName) {
		if (!events.containsKey(name))
			return;
		
		Lap lap = new Lap(lapName, getCurrentTime());
		events.get(name).addLap(lap);
	}
	
	public void reset() {
		init();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("StopWatch, total running time: ").append(getCurrentTime() - initTimeStamp).append("ms\n");
		for (TimedEvent event : events.values()) {
			sb.append(event.toString());
		}
		
		return sb.toString();
	}
	
	private void init() {
		initTimeStamp = getCurrentTime();
		events = new HashMap<String, StopWatch.TimedEvent>();
	}
	
	private long getCurrentTime() {
		return System.currentTimeMillis();
	}
	
	private class TimedEvent {
		String name;
		long startTime;
		long endTime;
		List<Lap> laps;
		
		public TimedEvent(String name, long startTime) {
			this.name = name;
			this.startTime = startTime;
			laps = new ArrayList<StopWatch.Lap>();
		}
		
		public void addLap(Lap lap) {
			laps.add(lap);
		}
		
		public String getName() {
			return name;
		}

		public long getStartTime() {
			return startTime;
		}

		public long getEndTime() {
			return endTime;
		}

		public void setEndTime(long endTime) {
			this.endTime = endTime;
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(name).append(" took: ");
			if (startTime < endTime)
				sb.append(endTime - startTime);
			else {
				sb.append("*").append(getCurrentTime() - startTime);
			}
			sb.append("ms\n");
			
			Lap previousLap = null;
			for (Lap lap : laps) {
				sb.append("\t").append(lap.lapName).append(" took: ");
				if (previousLap == null) 
					sb.append(lap.timePoint - startTime);
				else 
					sb.append(lap.timePoint - previousLap.timePoint);
				sb.append("ms\n");
				previousLap = lap;
			}
			return sb.toString();
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TimedEvent other = (TimedEvent) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
		private StopWatch getOuterType() {
			return StopWatch.this;
		}
	}
	
	private class Lap implements Comparable<Lap> {
		
		String lapName;
		long timePoint;
		
		public Lap(String lapName, long timePoint) {
			this.lapName = lapName;
			this.timePoint = timePoint;
		}

		@Override
		public int compareTo(Lap o) {
			Long thisTimePoint = new Long(timePoint);
			Long otherTimePoint = new Long(o.timePoint);
			return thisTimePoint.compareTo(otherTimePoint);
		}
	}
}
