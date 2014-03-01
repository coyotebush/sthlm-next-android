package com.saboonchi.sthlmnext.model;

/**
 * Encapsulates information about one particular travel route: a combination of
 * starting station, the line's end destination, and the line number.
 * The starting station name and line type are also stored here for convenience.
 * However, they are not used in uniquely identifying a Departure.
 * 
 * This object is passed to DepartureActivity by both DestinationActivity and
 * FavoritesFragment, and then used as a parameter to methods of
 * FavoritesDBAdapter.
 */
public class Destination {
    public int stationId;
    public String stationName;
    public String destinationName;
    public String lineType;
    public int lineNumber;

    public Destination(int stationId, String stationName, String destinationName,
                        String lineType, int lineNumber) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.destinationName = destinationName;
        this.lineType = lineType;
        this.lineNumber = lineNumber;
    }

}
