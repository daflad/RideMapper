/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gpsmapper.core;

import java.io.File;
import java.util.ArrayList;
import org.gpsmapper.viscomps.MyMap;
import processing.core.PApplet;
import processing.xml.XMLElement;

/**
 * Class to find all xml file in a given directory and  serve them to othe program.
 * @author StephenJohnRussell
 */
public final class RouteList {

  private ArrayList<Route> routes = new ArrayList<Route>();
  private Route currentRoute;  

  //Searches a given directory for all xml files.
  public RouteList(PApplet p, MyMap theMap, boolean drawRoute) {
    File dir = new File("/Users/StephenJohnRussell/NetBeansProjects/GPSMapper/data/RouteFiles/");
    File[] files = dir.listFiles(); 

    for (int i = 0; i < files.length; i++) {
      String path = files[i].getAbsolutePath();
      if (path.toLowerCase().endsWith(".xml")) {
        Route myRoute = new Route();
        myRoute.importRoute(new XMLElement(p, path));
        routes.add(myRoute);
        PApplet.println(path);
      }
    }
    //Set the final route as the curent route
    currentRoute = routes.get(routes.size() - 1);
  }

  public Route getRoute(int i) {
    return routes.get(i);
  }

  public Route getCurrentRoute() {
    return currentRoute;
  }

  public ArrayList<Route> getRoutes() {
    return routes;
  }

  public void setCurrentRoute(Route r) {
    currentRoute = r;
  }
}
