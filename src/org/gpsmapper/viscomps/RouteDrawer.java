/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gpsmapper.viscomps;

import de.fhpotsdam.unfolding.geo.Location;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import org.gpsmapper.core.LatLong;
import processing.core.PApplet;

/**
 *
 * @author StephenJohnRussell
 */
public class RouteDrawer {
    
    private int flag;
    private LatLong marker;
    private Bang elevAdj;
    private int elevBoxHeight = 0;
    private float maxElev = 0;    
    private int[] xs;
    private int[] ys;

    //Convert Meters per Second to Miles per Hour.
    //Coould add here the ability to switch between mph & kph
    private double speedConvert(float mps) {
        return mps * (3600 / 1609.344);
    }

    //Draw current route to the screen
    public void drawRoute(PApplet p, MyMap theMap, ArrayList<LatLong> coords) {
        Location firstPoint = new Location((float) coords.get(0).getLat(), (float) coords.get(0).getLongitude());
        p.beginShape(PApplet.LINES);
        for (LatLong e : coords) {
            Location loc = new Location(e.getLat(), e.getLongitude());
            float xy[] = theMap.getScreenPositionFromLocation(loc);
            float xy1[] = theMap.getScreenPositionFromLocation(firstPoint);
            //If mouse over the route: store position for drawMarker();
            if (p.mouseX > xy[0] - 5 && p.mouseX < xy1[0] + 5) {
                if (p.mouseY > xy[1] - 5 && p.mouseY < xy1[1] + 5) {
                    flag = coords.indexOf(e);
                }
            }
            p.strokeWeight(2);
            p.stroke(0);
            p.vertex(xy[0], xy[1]);
            p.vertex(xy1[0], xy1[1]);
            firstPoint.setLat(loc.getLat());
            firstPoint.setLon(loc.getLon());
        }
        p.endShape();
    }

    //Draw a marker on route & elevation
    public void drawMarker(PApplet p, MyMap theMap, boolean drawRoute, ArrayList<LatLong> coords) {
        p.textAlign(PApplet.RIGHT);
        marker = coords.get(flag);
        float xy[] = theMap.getScreenPositionFromLocation(new Location((float) marker.getLat(), (float) marker.getLongitude()));
        p.stroke(180, 0, 0);
        p.fill(180, 0, 0);
        p.ellipseMode(PApplet.CENTER);
        //Altitude marker
        p.ellipse(PApplet.map(flag, 0, coords.size(), 0, p.width), p.screenHeight - PApplet.map((float) marker.getAltitude(), 0, maxElev, 0, elevBoxHeight - 40), 10, 10);
        //Position marker        
        if (drawRoute) {            
            p.ellipse(xy[0], xy[1], 10, 10);
        }
        //StatBox numbers
        p.fill(0, 0, 0);
        p.text(marker.getAltitude() + " (m)", 195, p.screenHeight - elevBoxHeight - 55);
        p.text(PApplet.nf((float) speedConvert((float) marker.getSpeed()), 3, 2) + " (mph)", 195, p.screenHeight - elevBoxHeight - 40);
        Date d = new Date(marker.getTime() - coords.get(0).getTime());
        SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
        f.setTimeZone(TimeZone.getTimeZone("GMT"));
        p.text(f.format(d), 195, p.screenHeight - elevBoxHeight - 25);
        p.text(PApplet.nf((float) marker.getTotalDistance(), 3, 3) + " (miles)", 195, p.screenHeight - elevBoxHeight - 10);
        p.stroke(2);
        p.textAlign(PApplet.CENTER);
    }

    //Draw Eleveation box
    public void drawElevation(PApplet p, ArrayList<LatLong> coords) {
        float m;
        float i;
        float a;
        adjElevBoxHeight(p, coords);
        markerStatBox(p, coords);
        p.beginShape(PApplet.LINES);
        for (LatLong e : coords) {
            float altitude = (float) e.getAltitude();
            if (altitude > elevBoxHeight) {
                maxElev = altitude;
                elevBoxHeight = (int) altitude + 40;
            }
            a = PApplet.map(altitude, 0, maxElev, 0, elevBoxHeight - 40);
            i = coords.indexOf(e);
            m = PApplet.map(i, 0, coords.size(), 0, p.width);
            p.stroke(255, 255, 224);
            p.vertex(m, p.screenHeight);
            p.vertex(m, p.screenHeight - a);
            //If mouse over the elevation: store position for drawMarker();
            if (p.mouseX < m + 1 && p.mouseX > m - 1) {
                if (p.mouseY > (p.screenHeight - elevBoxHeight) && p.mouseY < p.screenHeight) {
                    flag = coords.indexOf(e);
                }
            }
        }
        p.endShape();
        p.stroke(0);
        p.textAlign(PApplet.CENTER);
    }
    
    //Method to control adjustment of elevaation box.
    //::NEXT STEP:: If mouse interation with map could be turned off that would be better.
    private void adjElevBoxHeight(PApplet p, ArrayList<LatLong> coords) {
        p.noStroke();
        rectButSetup(0, (p.screenHeight - elevBoxHeight - 5), p.screenWidth, 10);
        elevAdj = new Bang(xs, ys, 4, p);
        p.fill(220, 135, 146, 100);
        elevAdj.drawMe();
        if (elevAdj.mouseMoved(p.mouseX, p.mouseY)) {
            p.fill(125, 135, 146, 100);
            elevAdj.drawMe();
            p.cursor(PApplet.HAND);
        } else {
            p.cursor(PApplet.ARROW);
        }
        if (elevAdj.doubleClick()) {
            elevBoxHeight = p.screenHeight - p.mouseY;
        }
    }

    //Draw stat box above elevation box
    private void markerStatBox(PApplet p, ArrayList<LatLong> coords) {
        p.noStroke();
        p.fill(135, 206, 235, 180);
        //background for elevation
        p.rect(0, p.screenHeight - elevBoxHeight, p.width, elevBoxHeight);
        //background for stat box
        p.rect(5, p.screenHeight - elevBoxHeight - 5, 200, -65);
        p.fill(0);
        p.textAlign(PApplet.RIGHT);
        p.text("Elevation:", 100, p.screenHeight - elevBoxHeight - 55);
        p.text("Speed:", 100, p.screenHeight - elevBoxHeight - 40);
        p.text("Time Ellapsed:", 100, p.screenHeight - elevBoxHeight - 25);
        p.text("Distance:", 100, p.screenHeight - elevBoxHeight - 10);
    }

    private void rectButSetup(int xPos, int yPos, int w, int h) {
        xs = new int[]{xPos, xPos + w, xPos + w, xPos};
        ys = new int[]{yPos, yPos, yPos + h, yPos + h};
    }
}
