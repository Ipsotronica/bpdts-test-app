package org.example.chrispeel.util;

public class DistanceCalculator {

    //Calculate the distance between two points
    //https://www.geeksforgeeks.org/program-distance-two-points-earth/

    public static double distanceBetween(double lat1, double long1, double lat2, double long2) {
        lat1 = Math.toRadians(lat1);
        long1 = Math.toRadians(long1);
        lat2 = Math.toRadians(lat2);
        long2 = Math.toRadians(long2);

        double distanceLat = lat2 - lat1;
        double distanceLong = long2 - long1;

        double a = Math.pow(Math.sin(distanceLat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(distanceLong / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));

        double r = 3956; //miles

        return c * r;
    }
}
