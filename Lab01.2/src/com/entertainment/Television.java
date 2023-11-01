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

    /*
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || this.getClass() != obj.getClass()) return false;
        Television that = (Television) obj;

        return this.getVolume() == that.getVolume() &&
               Objects.equals(this.getBrand(), that.getBrand());
    }
    */

    @Override
    public int hashCode() {
        /*
         * This is a poorly written hash function, because it easily yields "hash collisions."
         * A hash collision is when "different" objects have the same hash code (just by coincidence).
         * Given our poor initial hash function below,
         *  "Sony" 50  and  "LG" 52  both have hash codes of 54, as does  "Samsung" 47.
         *  These are "different" objects per the equals() method, but have the same hash code.
         */
        // return getBrand().length() + getVolume();

        // Instead, we rely on Objects.hash() to give us a "scientifically correct" hash function.
        return Objects.hash(getBrand(), getVolume());
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;

        // 'this' (me) and 'obj' refer to the same physical object in memory!
        if (this == obj) {
            result = true;  // and we're done, return result (true)
        }
        // 'obj' is not-null and my Class object is the same as its Class object, proceed
        // otherwise, skip this whole thing and return result (false)
        else if (obj != null && (this.getClass() == obj.getClass())) {
            Television other = (Television) obj;
            result = Objects.equals(this.getBrand(), other.getBrand()) &&        // null-safe
                     this.getVolume() == other.getVolume();       // primitives can't be null
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s [brand=%s, volume=%s, currentChannel=%s]",
                getClass().getSimpleName(), getBrand(), getVolume(), getCurrentChannel());
    }
}