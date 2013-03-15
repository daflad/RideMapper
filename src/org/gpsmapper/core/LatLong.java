/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gpsmapper.core;

import java.io.Serializable;

/**
 * Class containing data from phone sensors.
 * @author Stephen John Russell
 * @version 0.1
 */

public final class LatLong implements Serializable {

  private float lat;
  private float longitude;
  private float altitude;
  private float accuracy;
  private long time;
  private float speed;
  private float totalDistance;

  public LatLong(float _lat, float _longitude, float _totalDistance) {
    lat = _lat;
    longitude = _longitude;
    altitude = 0;
    accuracy = 0;
    time = 0;
    speed = 0;
    totalDistance = _totalDistance;
  }

  public LatLong(float lat, float longitude, float altitude, float accuracy, float speed, long time, float totalDistance) {
    this.lat = lat;
    this.longitude = longitude;
    this.altitude = altitude;
    this.accuracy = accuracy;
    this.time = time;
    this.speed = speed;
    this.totalDistance = totalDistance;
  }
  
  public float getLat() {
    return lat;
  }
  
  public float getLongitude() {
    return longitude;
  }
  
  public float getAltitude() {
    return altitude;
  }
  
  public float getAccuracy() {
    return accuracy;
  }
          
  public float getSpeed() {
    return speed;
  }
  
  public long getTime() {
    return time;
  }
  
  public float getTotalDistance() {
    return totalDistance;
  }
}