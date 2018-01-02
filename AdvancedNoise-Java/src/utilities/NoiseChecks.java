package utilities;

public final class NoiseChecks {
	
	private NoiseChecks() { }
	
	public static void checkAttributes(int octaves, double persistence) throws IllegalArgumentException {
		if (octaves <= 0) {
			throw new IllegalArgumentException(
					"The number of octaves must be greater than zero.");
		}
		if (persistence == 0) {
			throw new IllegalArgumentException(
					"The persistance cannot be zero.");
		}
	}

}
