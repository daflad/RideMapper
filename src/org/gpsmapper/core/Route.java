/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gpsmapper.core;

import de.fhpotsdam.unfolding.utils.GeoUtils;
import java.util.ArrayList;
import java.util.Calendar;
import processing.xml.*;

/**
 * Class to manage & organise LatLong points
 *
 * @author StephenJohnRussell
 */
public final class Route {

    /**
     * The list of coordinates.
     */
    private ArrayList<LatLong> coordinates;
    /**
     * Variables used to calculate distance.
     */
    private double newLastLat;
    private double newLastLong;
    private float totalDistance;    

    /**
     * Initiate & construct list.
     */
    public Route() {
        this.coordinates = new ArrayList<LatLong>();
        this.totalDistance = 0;
    }
    
    

    /**
     * Remove a set of coordinates from the route.
     * @param coordinate
     * @return 
     */
    public boolean removeCoordinate(LatLong coordinate) {
        return this.coordinates.remove(coordinate);

    }

    /**
     * Add a set of coordinates to the route.
     *
     * @param coordinates
     */
    public boolean addCoordinate(LatLong coordinate) {
        return this.coordinates.add(coordinate);
    }

    /**
     * Return the list of coordinates for this route.
     *
     * @return
     */
    public ArrayList<LatLong> getCoords() {
        return coordinates;
    }

    /**
     * Overwrite / set the list of coordinates for this route.
     *
     * @param coords
     */
    public void setCoords(ArrayList<LatLong> coords) {
        this.coordinates = coords;
    }

    /**
     * Routes are exported by android app as an XML file.
     *
     * @param xml
     */
    public void importRoute(XMLElement xml) {
        XMLElement trk = xml.getChild(0);
        XMLElement trkSeg = trk.getChild(1);
        
        for (int i = 0; i < trkSeg.getChildCount(); i++) {
            XMLElement trkpt = trkSeg.getChild(i);
            //Calculate the total distance before calling LatLong constructor
            //Conver from Kilometers to Miles.(/8*5)
            if (coordinates.size() > 2) {
                totalDistance += (GeoUtils.getDistance(newLastLat, newLastLong,
                        (double) trkpt.getFloat("lat"),
                        (double) trkpt.getFloat("lon")) / 8) * 5;
            }
            String t = trkpt.getChild(1).getContent();
            
            Calendar c = Calendar.getInstance();
            
            c.set(Integer.parseInt(t.substring(0, 4)), Integer.parseInt(t.substring(5, 7)), Integer.parseInt(t.substring(8, 10)), 
                    Integer.parseInt(t.substring(11, 13)), Integer.parseInt(t.substring(14, 16)), Integer.parseInt(t.substring(17, 19)));
            LatLong wayPoint = new LatLong(trkpt.getFloat("lat"), trkpt.getFloat("lon"), Float.parseFloat(trkpt.getChild(0).getContent()), 0, 0, c.getTimeInMillis(), totalDistance);
            coordinates.add(wayPoint);
            //Store current Lat & Long values to calculate the total distance on next itteration.
            newLastLat = trkpt.getFloat("lat");
            newLastLong = trkpt.getFloat("lon");        
        }
    }
}
