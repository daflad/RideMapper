/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gpsmapper.viscomps.interfaces;

import java.util.ArrayList;
import org.gpsmapper.core.Route;

/**
 * 
 * @author StephenJohnRussell
 */
public interface RouteListInterface {

    /**
     * Search a given directory for all xml files.
     *
     * @param path
     * @return 
     */
    public ArrayList<Route> findRoutes(String path);
    
    /**
     * Return a given route from the list.
     * 
     * @param i
     * @return 
     */
    public Route getRoute(int i);

    /**
     * Return the current route from the list.
     * 
     * @return 
     */
    public Route getCurrentRoute();

    /**
     * Return all routes in list.
     * @return 
     */
    public ArrayList<Route> getRoutes();

    /**
     * Set the current route.
     * @param r 
     */
    public void setCurrentRoute(Route r);
}
