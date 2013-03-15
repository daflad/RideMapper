/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gpsmapper.viscomps.interfaces;

import org.gpsmapper.core.RouteList;
import org.gpsmapper.viscomps.Bang;

/**
 *
 * @author StephenJohnRussell
 */
public interface DockInterface {

    /**
     * Draw dock to screen when mouse in upper section.
     * 
     * @param routes 
     */
    public void drawDock(RouteList routes);

    /**
     * Add a button to the dock.
     * 
     * @param but
     * @param d
     * @param lineOne
     * @param lineTwo
     * @param bool 
     */
    public void addButtonToDock(Bang but, int d, String lineOne, String lineTwo, boolean bool);

    /**
     * Perform behaviour of button presses.
     */
    public void buttonBehaviour();

    /**
     * Load all routes from a given directory.
     * 
     * @param routes 
     */
    public void loadedRoutes(RouteList routes);
}
