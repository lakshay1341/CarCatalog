package in.lakshay.rentACarBackend.core.utilities.generate;

import java.time.LocalDate;
import java.util.Random;

// makes random codes for invoices and stuff
// format is YYYYMMDD + 8 random digits
public class GenerateRandomCode {

    // makes a random code for orders, invoices etc
    public static String generate() {
        // ascii stuff for digits
        int leftLimit = 48; // ascii '0'
        int rightLimit = 57; // ascii '9'

        int targetStringLength = 8; // 8 digits shud be enough
        Random random = new Random(); // not super secure but whatevs

        // fancy java 8 stream magic to make random string
        // total overkill but i wanted to try streams lol
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();  // could just use a loop but this looks cooler

        // get date parts
        String year =  String.valueOf(LocalDate.now().getYear());
        String month = String.valueOf(LocalDate.now().getMonthValue());
        String day = String.valueOf(LocalDate.now().getDayOfMonth());  // not zero-indexed

        // pad with zeros if needed
        if(month.length()==1){
            month = "0"+month; // add leading zero
        }
        if(day.length()==1){
            day = "0"+day; // same for day
        }

        // TODO: use String.format? meh this works fine

        // stick it all together - YYYYMMDD12345678
        return year + month + day + generatedString;  // done!
    }

    // might add more generators later idk
}
