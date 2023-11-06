package com.training;

class Client {

    public static void main(String[] args) {
        // classloader loads Instructor.class, which initializes that static (shared) Projector object
        // Instructor inst1 = new Instructor("Jay", 28);

        // Instructor inst2 = new Instructor("Ed", 24);
        Instructor.touch();
        Instructor.touch();
    }
}