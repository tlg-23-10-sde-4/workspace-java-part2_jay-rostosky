package com.entertainment.client;

import com.entertainment.Television;
import java.util.HashSet;
import java.util.Set;

class TelevisionClient {

    public static void main(String[] args) {
        // show behavior of == versus equals()
        Television tvA = new Television("Sony", 50);
        Television tvB = new Television("Sony", 50);

        System.out.println("tvA == tvB: "      + (tvA == tvB));     // obviously false
        System.out.println("tvA.equals(tvB): " + tvA.equals(tvB));  // this is true now
        System.out.println();

        Set<Television> tvs = new HashSet<>();
        tvs.add(tvA);
        tvs.add(tvB);  // should be rejected as a duplicate
        System.out.println("The size of the set is: " + tvs.size());
    }
}