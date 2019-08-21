package com.example.albert.measure;

import android.util.Pair;

public class Point {
    private double x, y, z;
    private double pitch, azimuth;

    // Zero Constructor
    public Point() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    // Origin Constructor
    public Point(double h) {
        this.x = 0;
        this.y = 0;
        this.z = h;
    }

    // Manual Constructor
    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Manual constructor for base points
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        this.z = 0;
    }

    // Constructor for base points
    public Point(double h, Pair<Double, Double> angles) {
        z = 0;
        this.pitch = angles.first;
        this.azimuth = angles.second;
        this.y = Math.tan(pitch) * h;
        this.x = this.y * Math.tan(azimuth);
    }

    // Manual constructor for non-base points given its base point
    public Point(Point p, double h) {
        z = h;
        this.y = p.getY();
        this.x = p.getX();
    }

    // TODO Controlar punts impossibles ja que els azimuths han de ser iguals
    // Constructor for non-base points given its base point
    public Point(Point p, double h, Pair<Double, Double> angles) {
        this.pitch = angles.first;
        this.azimuth = angles.second;
        this.y = p.getY();
        this.x = p.getX();
        this.z = (new DistanceUtils()).PVS(h, p.pitch, this.pitch);
    }

    public double DistanceTo(Point q) {
        return Math.sqrt(Math.pow(this.getX()-q.getX(),2) + Math.pow(this.getY()-q.getY(),2) + Math.pow(this.getZ()-q.getZ(),2));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    // TODO Calculate it if not given
    // Angle (0,0,0) -- o -- this
    public double getPitch() {
        return pitch;
    }

    public double getAzimuth() {
        return azimuth;
    }

    public boolean isBased() {
        return z == 0;
    }

    public boolean isDefault() {
        return getX()==0 && getY() == 0 && getZ() == 0;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
