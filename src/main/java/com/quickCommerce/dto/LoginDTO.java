package com.quickCommerce.dto;

public class LoginDTO {

    private String email;
    private String password;

    private String currentLocation;
    private String latitude;
    private String longitude;

    public LoginDTO(String email, String password, String currentLocation, String latitude, String longitude) {
        this.email = email;
        this.password = password;
        this.currentLocation = currentLocation;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LoginDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
