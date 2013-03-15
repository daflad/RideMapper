/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gpsmapper.viscomps.interfaces;

import java.util.ArrayList;
import org.gpsmapper.core.LatLong;
import processing.xml.XMLElement;

/**
 *
 * @author StephenJohnRussell
 */
public interface RouteInterface {

    /**
     * Remove a set of coordinates from the route.
     *
     * @param coordinate
     * @return
     */
    public boolean removeCoordinate(LatLong coordinate);

    /**
     * Add a set of coordinates to the route.
     *
     * @param coordinates
     */
    public boolean addCoordinate(LatLong coordinate);

    /**
     * Return the list of coordinates for this route.
     *
     * @return
     */
    public ArrayList<LatLong> getCoords();

    /**
     * Overwrite / set the list of coordinates for this route.
     *
     * @param coords
     */
    public void setCoords(ArrayList<LatLong> coords);

    /**
     * Routes are exported by android app as an XML file.
     *
     * @param xml
     */
    public void importRoute(XMLElement xml);
}
