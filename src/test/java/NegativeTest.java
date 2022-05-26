import org.example.Main;
import org.example.ticketType;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NegativeTest {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void InvalidBuyingDateTest() {
        var actualResult = Main.createTicket(
                ticketType.valueOf("BUS"),
                "ticket on bus",
                2000,
                "Anton",
                LocalDateTime.parse("0001-05-10 15:00", formatter),
                LocalDateTime.parse("2022-05-27 15:00", formatter));
        String result = String.valueOf(actualResult);
        Assert.assertEquals("Invalid buying date", result);
    }

    @Test
    public void InvalidEndingDateTest() {
        var actualResult = Main.createTicket(
                ticketType.valueOf("BUS"),
                "ticket on bus",
                2000,
                "Anton",
                LocalDateTime.parse("2022-05-10 15:00", formatter),
                LocalDateTime.parse("5000-05-27 15:00", formatter));
        String result = String.valueOf(actualResult);
        Assert.assertEquals("Invalid ending date", result);
    }

    @Test
    public void InvalidBuyingEndingDate() {
        var actualResult = Main.createTicket(
                ticketType.valueOf("BUS"),
                "ticket on bus",
                2000,
                "Anton",
                LocalDateTime.parse("2022-05-10 15:00", formatter),
                LocalDateTime.parse("2021-05-27 15:00", formatter));
        String result = String.valueOf(actualResult);
        Assert.assertEquals("Ending date cannot be less buying date", result);
    }

    @Test
    public void InvalidPriceTest() {
        var actualResult = Main.createTicket(
                ticketType.valueOf("BUS"),
                "ticket on bus",
                -2000,
                "Anton",
                LocalDateTime.parse("2022-05-10 15:00", formatter),
                LocalDateTime.parse("2022-05-27 15:00", formatter));
        String result = String.valueOf(actualResult);
        Assert.assertEquals("Invalid price", result);
    }

    @Test
    public void EmptyFiledTest() {
        var actualResult = Main.createTicket(
                ticketType.valueOf("BUS"),
                "",
                -2000,
                "",
                LocalDateTime.parse("2022-05-10 15:00", formatter),
                LocalDateTime.parse("2022-05-27 15:00", formatter));
        String result = String.valueOf(actualResult);
        Assert.assertEquals("Invalid description and name", result);
    }


}
