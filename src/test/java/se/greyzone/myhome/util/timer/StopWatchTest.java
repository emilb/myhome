package se.greyzone.myhome.util.timer;

import org.junit.Test;

public class StopWatchTest {

	@Test
	public void testSimpleStopWatch() {
		String tName = "Pi calculation";
		String t2 = "Another timer";
		
		StopWatch sw = new StopWatch();
		sw.startTimerFor(tName);
		
		holdForMs(24);
		sw.addLapTimePoint(tName, "lap1");
		holdForMs(123);
		sw.addLapTimePoint(tName, "lap2");
		sw.startTimerFor(t2);
		holdForMs(78);
		sw.addLapTimePoint(tName, "lap3");
		
		sw.stopTimerFor(tName);
		System.out.println(sw);
	}
	
	private void holdForMs(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {}
	}
}
