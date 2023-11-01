/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */

package com.javatunes.catalog.test;

import com.javatunes.catalog.Catalog;
import com.javatunes.catalog.InMemoryCatalog;
import com.javatunes.catalog.MusicCategory;
import com.javatunes.catalog.MusicItem;
import java.util.Collection;
import java.util.Map;

class InMemoryCatalogTest {

    /*
     * One by one, complete each test method below, and then "activate" it by
     * uncommenting the call to that method in main().
     *
     * Once you see that the test method verifies the corresponding business method
     * works correctly, you can comment out that call in main() and proceed to the next one.
     */
    public static void main(String[] args) {

        // interface contract (Catalog) tests

        // testFindById();
        // testFindByKeyword();
        // testFindByCategory();
        // testSize();
        // testGetAll();

        // TASK method tests

        // testFindSelfTitled();
        // testFindCheapRock();
        // testNumberInGenre();
        // testGetAveragePrice();
        // testFindCheapestInGenre();
        // testHasGenre();
        testGetAllGenreMap();
    }

    private static void testGetAllGenreMap() {
        InMemoryCatalog catalog = new InMemoryCatalog();

        Map<MusicCategory,Collection<MusicItem>> genreMap = catalog.getAllGenreMap();
        dump(genreMap);
    }

    private static void testHasGenre() {
        InMemoryCatalog catalog = new InMemoryCatalog();

        boolean hasBlues = catalog.hasGenre(MusicCategory.BLUES);
        System.out.println(hasBlues);  // true

        boolean hasJazz = catalog.hasGenre(MusicCategory.JAZZ);
        System.out.println(hasJazz);   // false
    }

    private static void testFindCheapestInGenre() {
        // TODO
    }

    private static void testGetAveragePrice() {
        // TODO
    }

    private static void testNumberInGenre() {
        // TODO
    }

    private static void testFindCheapRock() {
        // TODO
    }

    private static void testFindSelfTitled() {
        // TODO
    }

    private static void testFindById() {
        InMemoryCatalog catalog = new InMemoryCatalog();

        MusicItem found = catalog.findById(9L);
        System.out.println(found);

        MusicItem notFound = catalog.findById(20L);
        System.out.println(notFound);
    }

    private static void testFindByKeyword() {
    }

    private static void testFindByCategory() {
        InMemoryCatalog catalog = new InMemoryCatalog();

        Collection<MusicItem> popItems = catalog.findByCategory(MusicCategory.POP);
        dump(popItems);
    }

    private static void testSize() {
        InMemoryCatalog catalog = new InMemoryCatalog();
        System.out.println(catalog.size());  // should be 18
    }

    private static void testGetAll() {
        InMemoryCatalog catalog = new InMemoryCatalog();

        Collection<MusicItem> allItems = catalog.getAll();
        allItems.clear();  // should throw exception
    }


    // helper method to show the collection "vertically"
    private static void dump(Collection<MusicItem> items) {
        for (MusicItem item : items) {
            System.out.println(item);
        }
    }

    private static void dump(Map<MusicCategory,Collection<MusicItem>> map) {
        for (var entry : map.entrySet()) {
            System.out.println(entry.getKey());
            dump(entry.getValue());
            System.out.println();
        }
    }
}