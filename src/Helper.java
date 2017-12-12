// Authors: Ali Hashim and James Emery
public class Helper {
    // Haversine formula
	public static double getWeight(Vertex start2, Vertex end2) {
		final double R = 6372.8;
		double latDiff = Math.toRadians(end2.getLatitude() - start2.getLatitude());
		double longDiff = Math.toRadians(end2.getLongitude() - start2.getLongitude());
		double startLat = Math.toRadians(start2.getLatitude());
		double endLat = Math.toRadians(end2.getLatitude());
		
		double a = Math.pow(Math.sin(latDiff/2), 2) + Math.pow(Math.sin(longDiff)/2, 2) * Math.cos(startLat) * Math.cos(endLat);
		double c = 2 * Math.asin(Math.sqrt(a));
		return R * c;
	}
}
