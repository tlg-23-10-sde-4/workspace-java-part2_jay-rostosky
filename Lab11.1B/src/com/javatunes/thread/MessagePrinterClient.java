package com.javatunes.thread;

public class MessagePrinterClient {

    public static void main(String[] args) {
        // 30 secs total
        MessagePrinter printer1 = new MessagePrinter("roses are red", 10, 3000);
        Thread thd1 = new Thread(printer1);
        thd1.setName("message-printer-1");
        thd1.start();

        // 20 secs total
        Runnable printer2 = new MessagePrinter("violets are blue", 100, 200);
        Thread thd2 = new Thread(printer2);
        thd2.setName("message-printer-2");
        thd2.start();

        // 10 times (default), 1 second each (default) = 10 secs
        Thread thd3 = new Thread(new MessagePrinter("sugar is sweet"), "message-printer-3");
        thd3.start();

        // 32 secs
        MessagePrinter printer4 = new MessagePrinter("actually, sugar is TOXIC", 16, 2000);
        Thread thd4 = new Thread(printer4, "message-printer-4");
        thd4.start();
    }
}