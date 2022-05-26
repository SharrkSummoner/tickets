import org.example.Main;
import org.example.ticketType;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PositiveTest {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void normalDataBusTicketTest() {
        var actualResult = Main.createTicket(
                ticketType.valueOf("BUS"),
                "ticket on bus",
                2000,
                "Anton",
                LocalDateTime.parse("2022-05-10 15:00", formatter),
                LocalDateTime.parse("2022-05-27 15:00", formatter));
        String result = String.valueOf(actualResult);
        Assert.assertEquals("1080.0", result);
    }

    @Test
    public void normalDataCinemaTicketTest() {
        var actualResult = Main.createTicket(
                ticketType.valueOf("CINEMA"),
                "ticket on cinema",
                2000,
                "Dima",
                LocalDateTime.parse("2022-05-10 15:00", formatter),
                LocalDateTime.parse("2022-05-27 15:00", formatter));
        String result = String.valueOf(actualResult);
        Assert.assertEquals("1140.0",  result);
    }

    @Test
    public void normalDataConcertTicketTest() {
        var actualResult = Main.createTicket(
                ticketType.valueOf("CONCERT"),
                "ticket on concert",
                2000,
                "Vlad",
                LocalDateTime.parse("2022-05-10 15:00", formatter),
                LocalDateTime.parse("2022-05-27 15:00", formatter));
        String result = String.valueOf(actualResult);
        Assert.assertEquals("840.0",  result);
    }

    @Test
    public void normalDataFlightsTicketTest() {
        var actualResult = Main.createTicket(
                ticketType.valueOf("FLIGHTS"),
                "ticket on flights",
                2000,
                "Misha",
                LocalDateTime.parse("2022-05-10 15:00", formatter),
                LocalDateTime.parse("2022-05-27 15:00", formatter));
        String result = String.valueOf(actualResult);
        Assert.assertEquals("720.0",  result);
    }

    @Test
    public void normalDataTrainTicketTest() {
        var actualResult = Main.createTicket(
                ticketType.valueOf("TRAIN"),
                "ticket on train",
                2000,
                "Denis",
                LocalDateTime.parse("2022-05-10 15:00", formatter),
                LocalDateTime.parse("2022-05-27 15:00", formatter));
        String result = String.valueOf(actualResult);
        Assert.assertEquals("960.0",  result);
    }
}
