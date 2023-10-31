package com.entertainment;

import java.util.Objects;

public class Television {
    // instance variables
    private String brand;
    private int volume;

    // Television HAS-A Tuner (for all things related to channel)
    private final Tuner tuner = new Tuner();  // instantiated internally, not exposed

    // constructors
    public Television() {
    }

    public Television(String brand, int volume) {
        setBrand(brand);
        setVolume(volume);
    }

    // business ("action") methods
    public int getCurrentChannel() {
        return tuner.getChannel();  // delegate to contained Tuner object
    }

    public void changeChannel(int channel) {
        tuner.setChannel(channel);  // delegate to contained Tuner object
    }

    // accessor methods
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public int hashCode() {
        /*
         * This is a poorly written hash function, because it easily yields "hash collisions."
         * A hash collision is when "different" objects have the same hash code (just by coincidence).
         */
        // return getBrand().length() + getVolume();

        // Instead, we rely on Objects.hash() to give us a "scientifically correct" hash function.
        return Objects.hash(getBrand(), getVolume());
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;

        // proceed only if 'obj' is really referencing a Television object
        if (obj instanceof Television) {
            // safely downcast 'obj' to more specific reference type Television
            Television other = (Television) obj;

            // do the checks: business equality is defined by brand, volume being the same
            result = Objects.equals(this.getBrand(), other.getBrand()) &&  // null-safe check
                     this.getVolume() == other.getVolume();                // primitives can't be null
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s [brand=%s, volume=%s, currentChannel=%s]",
                getClass().getSimpleName(), getBrand(), getVolume(), getCurrentChannel());
    }
}