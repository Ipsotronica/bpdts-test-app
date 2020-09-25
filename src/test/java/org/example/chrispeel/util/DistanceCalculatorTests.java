package org.example.chrispeel.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistanceCalculatorTests {
    //51.5074° N, 0.1278° W
    private static final double londonLat = 51.5074;
    private static final double londonLong = -0.1278;

    //51.2704° N, 0.5227° E
    private static final double maidstoneLat = 51.2704;
    private static final double maidstoneLong = 0.5227;
    //According to Google Maidstone is 32 miles from London

    @Test
    public void testDistance() {
        double distance = DistanceCalculator.distanceBetween(londonLat, londonLong, maidstoneLat, maidstoneLong);
        assertEquals(32.0, distance, 1E-10);
    }
}
