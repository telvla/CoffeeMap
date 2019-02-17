package at.telvla.coffeemap;

public class GoogleAnswerInfo {

    String geocoded_waypoints;
    String routes;
    String status;


    GoogleAnswerInfo (String geocoded_waypoints, String routes, String status) {
        this.geocoded_waypoints = geocoded_waypoints;
        this.routes = routes;
        this.status = status;
    }

    public void setGeocodedWaypoints (String geocoded_waypoints) {
        this.geocoded_waypoints = geocoded_waypoints;
    }

    public String getGeocodedWaypoints () {
        return geocoded_waypoints;
    }

    public void setRoutes (String routes) {
        this.routes = routes;
    }

    public String getRoutes () {
        return routes;
    }

    public void setStatus (String status) {
        this.status = status;
    }

    public String getStatus () {
        return status;
    }
}


/*
    String geocoded_waypoints;
    String routes;
    String status;


    GoogleAnswerInfo (String geocoded_waypoints, String routes, String status) {
        this.geocoded_waypoints = geocoded_waypoints;
        this.routes = routes;
        this.status = status;
    }

    public void setGeocodedWaypoints (String geocoded_waypoints) {
        this.geocoded_waypoints = geocoded_waypoints;
    }

    public String getGeocodedWaypoints () {
        return geocoded_waypoints;
    }

    public void setRoutes (String routes) {
        this.routes = routes;
    }

    public String getRoutes () {
        return routes;
    }

    public void setStatus (String status) {
        this.status = status;
    }

    public String getStatus () {
        return status;
    }
*/