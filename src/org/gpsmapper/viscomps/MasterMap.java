/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gpsmapper.viscomps;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.*;
import processing.core.PApplet;

/**
 * Wrapper type class to tidy away map generation.
 *
 * @author StephenJohnRussell
 * @version 0.1 * 
 */
public class MasterMap {

    public static final String JDBC_CONN_STRING_MAC = ""
            + "jdbc:sqlite:data/NorthWales.mbtiles";
    private UnfoldingMap theMap;

    /**
     * Construct an unfolding map with either MBTiles(MBT) or OpenStreetMap(OSM)
     *
     * Setting openOnline to true will use OSM, false will load local MBT data
     *
     * Location home is where map will pan to on load.
     *
     * @param p
     * @param home
     */
    public MasterMap(PApplet p, Location home, boolean openOnline) {
        if (openOnline) {
            //this will conect via internet to OpenStreetMaps tile provider
            theMap = new UnfoldingMap(p, "UnfoldingMap", 0, 0, p.width, p.height, 
                    true, false, new OpenStreetMap.OpenStreetMapProvider());            
        } else {
            //this will conect to local MBTiles SQLite DB
            theMap = new UnfoldingMap(p, "Map", 0, 0, p.width, p.height, true, 
                    false, new MBTilesMapProvider(JDBC_CONN_STRING_MAC));
        }
        theMap.zoomAndPanTo(home, 11);
        theMap.setZoomRange(1, 21);
        MapUtils.createDefaultEventDispatcher(p, theMap);
    }

    /**
     * Draw map to screen.
     */
    public void draw() {
        theMap.draw();
    }

    /**
     * Return float[] of x & y for given lat/long.
     *
     * @param loc
     * @return
     */
    public float[] getScreenPositionFromLocation(Location loc) {
        return theMap.getScreenPosition(loc).array();
    }

    /**
     * pan from lat a, long b to lat c, long d.
     *
     * @param a
     * @param b
     * @param c
     * @param d
     */
    public void pan(float a, float b, float c, float d) {
        theMap.pan(a, b, c, d);
    }

    /**
     * Pan to loc & set zoom level.
     *
     * @param loc
     * @param zoom
     */
    public void zoomAndPanTo(Location loc, int zoom) {
        theMap.zoomAndPanTo(loc, zoom);
    }

    /**
     * Return Location from given screen position.
     *
     * @param x
     * @param y
     * @return
     */
    public Location getLocationFromScreenPosition(float x, float y) {
        return theMap.getLocation(x, y);
    }

    /**
     * Zoom out.
     */
    public void zoomOut() {
        theMap.zoomOut();
    }

    /**
     * Pan to loc.
     *
     * @param loc
     */
    public void panTo(Location loc) {
        theMap.panTo(loc);
    }

    /**
     * Zoom in.
     */
    public void zoomIn() {
        theMap.zoomIn();
    }
}
