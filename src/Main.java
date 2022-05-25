import java.sql.Time;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;

public class Main {

    public static void main(String[] args) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        String description;
        double price;
        String ownerName;
        LocalDateTime buyingDate;
        LocalDateTime endingDate;

        ticketType ticket;
        try (Scanner sc = new Scanner(System.in)) {

            System.out.println("Enter one of the ticket types:\n" + Arrays.toString(ticketType.values()) + "");
            ticket = ticketType.valueOf(sc.nextLine());

            System.out.println("Enter the ticket description");
            description = sc.nextLine();

            System.out.println("Enter your full name");
            ownerName = sc.nextLine();

            System.out.println("Enter the date of ticket purchase in the format: \"yyyy-MM-dd HH:mm\"");
            buyingDate = LocalDateTime.parse(sc.nextLine(), formatter);

            System.out.println("Enter the date of ticket execution in the format: \"yyyy-MM-dd HH:mm\"");
            endingDate = LocalDateTime.parse(sc.nextLine(), formatter);

            System.out.println("Enter ticket price");
            price = sc.nextDouble();
        }

        Ticket ourTicket = new Ticket(
                ticket,
                description,
                price,
                ownerName,
                buyingDate,
                endingDate);

        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime ticketDateTime = ourTicket.getEndingDate();

        double backPrice = ourTicket.getPrice();
        double coefficient = 0.95;

        switch (ourTicket.getTicketType()) {
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

        System.out.printf("You will receive a refund in the amount of %.2f", backPrice);
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