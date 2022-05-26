package org.example;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;

public class Main {
    private Scanner scanner;
    private PrintStream printStream;

    public static void main(String[] args) {
        Main main = new Main(System.in, System.out);
        main.startWork();
    }

    public Main(InputStream inputStream, PrintStream printStream) {
        this.scanner = new Scanner(inputStream);
        this.printStream = printStream;
    }

    public void startWork() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        printStream.println("Enter one of the ticket types:\n" + Arrays.toString(ticketType.values()) + "");
        ticketType ticket = ticketType.valueOf(scanner.nextLine());

        printStream.println("Enter the ticket description");
        String description = scanner.nextLine();

        printStream.println("Enter your full name");
        String ownerName = scanner.nextLine();

        printStream.println("Enter the date of ticket purchase in the format: \"yyyy-MM-dd HH:mm\"");
        LocalDateTime buyingDate = LocalDateTime.parse(scanner.nextLine(), formatter);

        printStream.println("Enter the date of ticket execution in the format: \"yyyy-MM-dd HH:mm\"");
        LocalDateTime endingDate = LocalDateTime.parse(scanner.nextLine(), formatter);

        printStream.println("Enter ticket price");
        double price = scanner.nextDouble();

        price = createTicket(ticket, description, price, ownerName, buyingDate, endingDate);
        System.out.printf("%.2f", price);
    }

    public static double createTicket(ticketType ticket,
                                      String description,
                                      double price,
                                      String ownerName,
                                      LocalDateTime buyingDate,
                                      LocalDateTime endingDate) {

        Ticket ourTicket = new Ticket(
                ticket,
                description,
                price,
                ownerName,
                buyingDate,
                endingDate);

        return checkTicketType(ourTicket);
    }

    public static double checkTicketType(Ticket ticket) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime ticketDateTime = ticket.getEndingDate();

        double backPrice = ticket.getPrice();
        double coefficient;

        switch (ticket.getTicketType()) {
            case CONCERT -> {
                coefficient = 0.7;
                backPrice = changePrice(currentDateTime, ticketDateTime, backPrice, coefficient);
                break;
            }
            case CINEMA -> {
                coefficient = 0.95;
                backPrice = changePrice(currentDateTime, ticketDateTime, backPrice, coefficient);
                break;
            }
            case FLIGHTS -> {
                coefficient = 0.6;
                backPrice = changePrice(currentDateTime, ticketDateTime, backPrice, coefficient);
                break;
            }
            case BUS -> {
                coefficient = 0.9;
                backPrice = changePrice(currentDateTime, ticketDateTime, backPrice, coefficient);
                break;
            }
            case TRAIN -> {
                coefficient = 0.8;
                backPrice = changePrice(currentDateTime, ticketDateTime, backPrice, coefficient);
                break;
            }
        }

        return backPrice;
    }

    public static double changePrice(
            LocalDateTime currentDateTime,
            LocalDateTime ticketDateTime,
            double backPrice,
            double coefficient) {

        LocalDate currentDate = currentDateTime.toLocalDate();
        LocalDate ticketDate = ticketDateTime.toLocalDate();

        LocalTime currentTime = currentDateTime.toLocalTime();
        LocalTime ticketTime = ticketDateTime.toLocalTime();

        int differenceDay = (int) DAYS.between(currentDate, ticketDate);
        int differenceTime = (int) HOURS.between(currentTime, ticketTime);

        if (differenceDay > 0) {
            if (differenceDay < 31 && differenceDay > 10) {
                backPrice *= 1 * coefficient;
            } else if (differenceDay <= 10 && differenceDay > 7) {
                backPrice *= 0.9 * coefficient;
            } else if (differenceDay <= 7 && differenceDay > 3) {
                backPrice *= 0.8 * coefficient;
            } else if (differenceDay <= 3 && differenceDay > 1) {
                backPrice *= 0.7 * coefficient;
            } else {
                backPrice *= 0.6 * coefficient;
            }
        } else if (differenceDay < 0) {
            backPrice = 0;
        } else {
            if (differenceTime <= 24 && differenceTime > 12) {
                backPrice *= 0.5 * coefficient;
            } else if (differenceTime <= 12 && differenceTime > 5) {
                backPrice *= 0.4 * coefficient;
            } else if (differenceTime <= 5 && differenceTime > 3) {
                backPrice *= 0.35 * coefficient;
            } else if (differenceTime <= 3 && differenceTime > 1) {
                backPrice *= 0.3 * coefficient;
            } else if (differenceTime == 1) {
                backPrice *= 0.2 * coefficient;
            } else if (differenceTime < 0 && differenceTime > -3) {
                backPrice *= 0.1 * coefficient;
            } else backPrice = 0;
        }
        return backPrice;
    }
}