package in.lakshay.rentACarBackend.dataAccess.abstracts;

// data access for invoices
// spring data jpa is pretty amazing
import in.lakshay.rentACarBackend.entities.concretes.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository  // spring component
public interface InvoiceDao extends JpaRepository<Invoice, Integer> {  // entity + id type

    // existence checks
    boolean existsByInvoiceId(int invoiceId);  // by pk
    boolean existsByInvoiceNo(String invoiceNo);  // by invoice number
    boolean existsByPayment_PaymentId(int paymentId);  // by payment
    boolean existsByCustomer_CustomerId(int customerId);  // by customer
    boolean existsByRentalCar_RentalCarId(int rentalCarId);  // by rental

    // single invoice lookups
    Invoice getInvoiceByInvoiceNo(String invoiceNo);  // find by number
    Invoice getInvoiceByPayment_PaymentId(int paymentId);  // find by payment

    // multiple invoice lookups
    List<Invoice> getAllByCustomer_CustomerId(int customerId);  // all for customer
    List<Invoice> getAllByRentalCar_RentalCarId(int rentalCarId);  // all for rental

    // date range query - for reports n stuff
    List<Invoice> getByCreationDateBetween(Date startDate, Date endDate);

    // todo: add query for total revenue in date range?
    // would need @Query annotation + jpql

}
