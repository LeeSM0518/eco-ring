package dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class Dust {

  private int id;
  private int density;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Timestamp measuredDate;
  private String severity;
  private int measuredDeviceId;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public int getMeasuredDeviceId() {
    return measuredDeviceId;
  }

  public void setMeasuredDeviceId(int measuredDeviceId) {
    this.measuredDeviceId = measuredDeviceId;
  }

}
