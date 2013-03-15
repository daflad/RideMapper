/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gpsmapper.viscomps.interfaces;

import java.util.ArrayList;
import org.gpsmapper.core.LatLong;
import org.gpsmapper.viscomps.MasterMap;
import processing.core.PApplet;

/**
 *
 * @author StephenJohnRussell
 */
public interface RouteDrawerInterface {
    
    public void drawRoute(PApplet p, MasterMap theMap, ArrayList<LatLong> coords);
    
    public void drawMarker(PApplet p, MasterMap theMap, boolean drawRoute, ArrayList<LatLong> coords);
    
    public void drawElevation(PApplet p, ArrayList<LatLong> coords);    
}
