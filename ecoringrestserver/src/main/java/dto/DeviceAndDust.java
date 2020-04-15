package dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class DeviceAndDust {

  private int id;
  private double latitude;
  private double longitude;
  private String address;
  private int density;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Timestamp measuredDate;
  private String severity;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getDensity() {
    return density;
  }

  public void setDensity(int density) {
    this.density = density;
  }

  public Timestamp getMeasuredDate() {
    return measuredDate;
  }

  public void setMeasuredDate(Timestamp measuredDate) {
    this.measuredDate = measuredDate;
  }

  public String getSeverity() {
    return severity;
  }

  public void setSeverity(String severity) {
    this.severity = severity;
  }
}
