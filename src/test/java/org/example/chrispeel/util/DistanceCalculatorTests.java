package org.example.chrispeel.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistanceCalculatorTests {
    //London 51.5074° N, 0.1278° W
    private static final double LONDON_LAT = 51.5074;
    private static final double LONDON_LONG = -0.1278;

    @Test
    public void testDistanceToMaidstone() {
        //Maidstone 51.2704° N, 0.5227° E
        final double maidstoneLat = 51.2704;
        final double maidstoneLong = 0.5227;
        //Maidstone is ~32 miles from London

        double distance = DistanceCalculator.distanceBetween(LONDON_LAT, LONDON_LONG, maidstoneLat, maidstoneLong);
        assertEquals(32.0, distance, 1); // delta 1 basically means ignore the decimal
    }

    @Test
    public void testDistanceToLeicester() {
        //Leicester 52.6369° N, 1.1398° W
        final double leicesterLat = 52.6369;
        final double leicesterLong = -1.1398;
        //Leicester is ~89 miles from London

        double distance = DistanceCalculator.distanceBetween(LONDON_LAT, LONDON_LONG, leicesterLat, leicesterLong);
        assertEquals(89.0, distance, 1); // delta 1 basically means ignore the decimal
    }

    @Test
    public void testDistanceToCambridge() {
        //Cambridge 52.2053° N, 0.1218° E
        final double cambridgeLat = 52.2053;
        final double cambridgeLong = 0.1218;
        //Cambridge is ~49.8 miles from London

        double distance = DistanceCalculator.distanceBetween(LONDON_LAT, LONDON_LONG, cambridgeLat, cambridgeLong);
        assertEquals(49.0, distance, 1); // delta 1 basically means ignore the decimal
    }
}
