/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gpsmapper.core;

import java.io.File;
import java.util.ArrayList;
import org.gpsmapper.viscomps.MasterMap;
import org.gpsmapper.viscomps.interfaces.RouteListInterface;
import processing.core.PApplet;
import processing.xml.XMLElement;

/**
 * Class to find all xml file in a given directory and serve them to othe
 * program.
 *
 * @author StephenJohnRussell
 */
public final class RouteList implements RouteListInterface {

    private ArrayList<Route> routes;
    private Route currentRoute;
    private PApplet p;

    //Searches a given directory for all xml files.
    public RouteList(PApplet p, MasterMap theMap, boolean drawRoute) {
        this.p = p;
        routes = findRoutes("data/RouteFiles/");
        //Set the final route as the curent route
        currentRoute = routes.get(routes.size() - 1);
    }

    @Override
    public ArrayList<Route> findRoutes(String path) {
        
        ArrayList<Route> r = new ArrayList<Route>();
        
        File dir = new File(path);
        File[] files = dir.listFiles();

        for (int i = 0; i < files.length; i++) {
            String absPath = files[i].getAbsolutePath();
            if (absPath.toLowerCase().endsWith(".xml")) {
                Route myRoute = new Route();
                myRoute.importRoute(new XMLElement(p, absPath));
                r.add(myRoute);
                PApplet.println(absPath);
            }
        }
        
        return r;

    }

    @Override
    public Route getRoute(int i) {
        return routes.get(i);
    }

    @Override
    public Route getCurrentRoute() {
        return currentRoute;
    }

    @Override
    public ArrayList<Route> getRoutes() {
        return routes;
    }

    @Override
    public void setCurrentRoute(Route r) {
        currentRoute = r;
    }
}
