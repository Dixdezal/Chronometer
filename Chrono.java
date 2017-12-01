
public class Chrono {

	private long ns = 0;

	public void add(long ns) {
		this.ns += ns;
	}

	public void reset() {
		ns = 0;
	}

	public String toString() {
		long ns = this.ns;
		int h = (int) (ns / 3600000000000L);
		ns %= 3600000000000L;
		int m = (int) (ns / 60000000000L);
		ns %= 60000000000L;
		int s = (int) (ns / 1000000000L);
		ns %= 1000000000L;
		int ms = (int) (ns / 1000000L);
		return String.format("%02d:%02d:%02d.%03d", h, m, s, ms);
	}
}
