package org.gopathon;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Gaurav on 3/22/2020.
 */
public class Gaushala {

    private String gaushalaName;

    private String gaushalaAddress;

    private String contactNumber;

    private String website;

    private String state;

    private String city;

    private String email;

    private String zone;

    public static List<String> northZone = Arrays.asList("J&K", "Jammu", "Punjab", "H.P", "Himachal Pradesh",
            "U.P", "Delhi", "Haryana", "Uttarakhand", "Uttar Pradesh", "Rajasthan");

    public static List<String> westZone = Arrays.asList("Gujarat", "Maharashtra", "Goa", "M.P", "Madhya Pradesh");

    public static List<String> eastZone = Arrays.asList("West Bengal",
            "Orrisa","Odhisha", "Chattisgarh", "Jharkhand", "Meghalay", "Assam", "Sikkim", "Mizoram", "Tripura", "Arunachal Pradesh", "Manipur");

    public static List<String> southZone = Arrays.asList("Tamil Nadu","Pondicherry", "Karnataka", "Kerala", "Telangana", "Andhra Pradesh", "Andaman Nicobar");

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGaushalaName() {
        return gaushalaName;
    }

    public void setGaushalaName(String gaushalaName) {
        this.gaushalaName = gaushalaName;
    }

    public String getGaushalaAddress() {
        return gaushalaAddress;
    }

    public void setGaushalaAddress(String gaushalaAddress) {
        this.gaushalaAddress = gaushalaAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
