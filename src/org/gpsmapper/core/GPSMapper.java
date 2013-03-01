package org.gpsmapper.core;

/**
 * GPSMapper.java
 *
 */
import codeanticode.glgraphics.GLConstants;
import de.fhpotsdam.unfolding.geo.Location;
import org.gpsmapper.viscomps.Dock;
import org.gpsmapper.viscomps.MyMap;
import org.gpsmapper.viscomps.RouteDrawer;
import org.gpsmapper.viscomps.ToolBox;
import processing.core.*;

public class GPSMapper extends PApplet {

    public boolean dock = true;
    public boolean drawRoute = false;
    public boolean elevation = false;
    public boolean flagElev = false;
    public boolean toolBox = false;
    public boolean recordRoute = false;
    public boolean map = false;
    private PFont font;
    private MyMap theMap;
    private Dock theDock;
    private ToolBox theToolBox;
    private RouteList theList;
    private Location home;
    private RouteDrawer routeDrawer;
    private int tempX = 0;
    private int tempY = 0;

    public static void main(String[] args) {
        // must match the name of your class ie "letsp5.Main" = packageName.className 
        PApplet.main(new String[]{"org.gpsmapper.core.GPSMapper"});
    }

    @Override
    public void setup() {
        size(screenWidth, screenHeight, GLConstants.GLGRAPHICS);
        smooth();
        strokeCap(ROUND);
        font = loadFont("/Users/StephenJohnRussell/NetBeansProjects/GPSMapper/data/MyriadApple-Medium-24.vlw");
        textFont(font, 12);
        textAlign(CENTER);
        theDock = new Dock(this);
        home = new Location(53.15f, -4f);
        theMap = new MyMap(this, home, true);
        theList = new RouteList(this, theMap, drawRoute);
        routeDrawer = new RouteDrawer();
    }

    @Override
    public void draw() {
        smooth();
        theMap.draw();
        booleanControl();
    }

    void booleanControl() {
        if (mousePressed && mouseButton == RIGHT) {
            tempX = mouseX;
            tempY = mouseY;
            toolBox = true;
        }
        if (mouseY > 0 && mouseY < 80) {
            dock = true;
        } else {
            dock = false;
        }
        if (dock) {
            theDock.drawDock(theList);
        }
        if (drawRoute) {
            routeDrawer.drawRoute(this, theMap, theList.getCurrentRoute().getCoords());

        }
        if (elevation) {
            routeDrawer.drawElevation(this, theList.getCurrentRoute().getCoords());
            routeDrawer.drawMarker(this, theMap, drawRoute, theList.getCurrentRoute().getCoords());
        }
        if (toolBox) {
            theToolBox = new ToolBox(tempX, tempY, home, this);
            theToolBox.drawToolBox(theMap);
        }
    }
}