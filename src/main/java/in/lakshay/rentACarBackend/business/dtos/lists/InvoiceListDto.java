package in.lakshay.rentACarBackend.business.dtos.lists;

// dto for listing invoices in the system
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data  // lombok ftw
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceListDto {

    // basic invoice info
    private int invoiceId;  // pk
    private String invoiceNo;  // human readable invoice number
    private LocalDate creationDate;  // when invoice was created

    // rental info
    private int rentalCarId;  // fk to rental
    private LocalDate startDate;  // when rental started
    private LocalDate finishDate;  // when rental ended
    private short totalRentalDay;  // how many days rented

    // financial info
    private double rentalCarTotalPrice;  // total amount

    // customer info
    private String email;  // customer email
    //todo:buraya ad soyad vs - need to add name/surname

}
