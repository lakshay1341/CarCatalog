package in.lakshay.rentACarBackend.api.controllers;

// lots of imports... this is getting out of hand
import in.lakshay.rentACarBackend.api.models.orderedAdditional.OrderedAdditionalAddModel;
import in.lakshay.rentACarBackend.api.models.orderedAdditional.OrderedAdditionalUpdateModel;
import in.lakshay.rentACarBackend.api.models.rentalCar.*;
import in.lakshay.rentACarBackend.business.abstracts.PaymentService;
import in.lakshay.rentACarBackend.business.concretes.CreditCardManager;
import in.lakshay.rentACarBackend.business.dtos.paymentDtos.gets.GetPaymentDto;
import in.lakshay.rentACarBackend.business.dtos.paymentDtos.lists.PaymentListDto;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.PosServiceExceptions.MakePaymentFailedException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.additionalExceptions.AdditionalNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions.CarNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carMaintenanceExceptions.CarAlreadyInMaintenanceException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.cityExceptions.CityNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.corporateCustomerExceptions.CorporateCustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.customerExceptions.CustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.individualCustomerExceptions.IndividualCustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.orderedAdditionalExceptions.AdditionalQuantityNotValidException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.orderedAdditionalExceptions.OrderedAdditionalAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.orderedAdditionalExceptions.OrderedAdditionalNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.paymentExceptions.PaymentNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions.*;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

// handles all payment related endpoints
@RestController
@RequestMapping("/api/payments") // payment api routes
public class PaymentsController {

    private final PaymentService paymentService; // service layer for payments

    @Autowired // dependency injection
    public PaymentsController(PaymentService paymentService) {
        this.paymentService = paymentService; // store reference
    }

    // TODO: add more payment methods in future


    // get all payments - probably admin only
    @GetMapping("/getAll")
    public DataResult<List<PaymentListDto>> getAll(){
        return this.paymentService.getAll(); // just pass to service
    }

    // process payment for individual customer rental
    @PostMapping("/makePaymentForIndividualRentAdd")
    public Result makePaymentForIndividualRentAdd(@RequestBody @Valid MakePaymentForIndividualRentAdd makePaymentForIndividualRentAdd, @RequestParam CreditCardManager.CardSaveInformation cardSaveInformation) throws AdditionalQuantityNotValidException, AdditionalNotFoundException, CustomerNotFoundException, MakePaymentFailedException, StartDateBeforeTodayException, StartDateBeforeFinishDateException, CarAlreadyRentedEnteredDateException, CarNotFoundException, IndividualCustomerNotFoundException, CarAlreadyInMaintenanceException, CityNotFoundException, RentalCarNotFoundException {
        // wow thats a lot of exceptions lol
        return this.paymentService.makePaymentForIndividualRentAdd(makePaymentForIndividualRentAdd, cardSaveInformation);
    }

    @PostMapping("/makePaymentForCorporateRentAdd")
    public Result makePaymentForCorporateRentAdd(@RequestBody @Valid MakePaymentForCorporateRentAdd makePaymentForCorporateRentAdd, @RequestParam CreditCardManager.CardSaveInformation cardSaveInformation) throws AdditionalQuantityNotValidException, AdditionalNotFoundException, CustomerNotFoundException, MakePaymentFailedException, CorporateCustomerNotFoundException, StartDateBeforeTodayException, StartDateBeforeFinishDateException, CarAlreadyRentedEnteredDateException, CarNotFoundException, CarAlreadyInMaintenanceException, CityNotFoundException, RentalCarNotFoundException {
        return this.paymentService.makePaymentForCorporateRentAdd(makePaymentForCorporateRentAdd, cardSaveInformation);
    }

    // update payment for individual rental
    @PutMapping("/makePaymentForIndividualRentUpdate")
    public Result makePaymentForIndividualRentUpdate(@RequestBody @Valid MakePaymentForIndividualRentUpdate makePaymentForIndividualRentUpdate, @RequestParam CreditCardManager.CardSaveInformation cardSaveInformation) throws CustomerNotFoundException, MakePaymentFailedException, StartDateBeforeTodayException, StartDateBeforeFinishDateException, CarAlreadyRentedEnteredDateException, CarNotFoundException, IndividualCustomerNotFoundException, RentalCarNotFoundException, CarAlreadyInMaintenanceException, CityNotFoundException, AdditionalNotFoundException {
        return this.paymentService.makePaymentForIndividualRentUpdate(makePaymentForIndividualRentUpdate, cardSaveInformation);
    }

    @PutMapping("/makePaymentForCorporateRentUpdate") // corporate update endpoint
    public Result makePaymentForCorporateRentUpdate(@RequestBody @Valid MakePaymentForCorporateRentUpdate makePaymentForCorporateRentUpdate, @RequestParam CreditCardManager.CardSaveInformation cardSaveInformation) throws CustomerNotFoundException, MakePaymentFailedException, CorporateCustomerNotFoundException, StartDateBeforeTodayException, StartDateBeforeFinishDateException, CarAlreadyRentedEnteredDateException, CarNotFoundException, RentalCarNotFoundException, CarAlreadyInMaintenanceException, CityNotFoundException, AdditionalNotFoundException {
        return this.paymentService.makePaymentForCorporateRentUpdate(makePaymentForCorporateRentUpdate, cardSaveInformation); // delegate to service
    }

    // handle late returns and delivery date changes
    @PutMapping("/makePaymentForRentDeliveryDateUpdate")
    public Result makePaymentForRentDeliveryDateUpdate(@RequestBody @Valid MakePaymentForRentDeliveryDateUpdate makePaymentForRentDeliveryDateUpdate, @RequestParam CreditCardManager.CardSaveInformation cardSaveInformation) throws AdditionalNotFoundException, CustomerNotFoundException, MakePaymentFailedException, StartDateBeforeFinishDateException, DeliveredKilometerAlreadyExistsException, CarAlreadyRentedEnteredDateException, RentalCarNotFoundException, RentedKilometerNullException {
        // this handles when customer returns car late or changes delivery date
        return this.paymentService.makePaymentForRentDeliveryDateUpdate(makePaymentForRentDeliveryDateUpdate, cardSaveInformation);
    }

   // add extras to rental
   @PostMapping("/makePaymentForOrderedAdditionalAdd")
    public Result makePaymentForOrderedAdditionalAdd(@RequestBody @Valid OrderedAdditionalAddModel orderedAdditionalAddModel, @RequestParam CreditCardManager.CardSaveInformation cardSaveInformation) throws AdditionalQuantityNotValidException, AdditionalNotFoundException, CustomerNotFoundException, MakePaymentFailedException, RentalCarNotFoundException {
        // for adding extra services/items to rental
        return this.paymentService.makePaymentForOrderedAdditionalAdd(orderedAdditionalAddModel, cardSaveInformation);
    }

    // update extras on rental
    @PutMapping("/makePaymentForOrderedAdditionalUpdate")
    public Result makePaymentForOrderedAdditionalUpdate(@RequestBody @Valid OrderedAdditionalUpdateModel orderedAdditionalUpdateModel, @RequestParam CreditCardManager.CardSaveInformation cardSaveInformation) throws OrderedAdditionalNotFoundException, AdditionalQuantityNotValidException, AdditionalNotFoundException, OrderedAdditionalAlreadyExistsException, CustomerNotFoundException, MakePaymentFailedException, RentalCarNotFoundException {
        // man these method names are getting long
        return this.paymentService.makePaymentForOrderedAdditionalUpdate(orderedAdditionalUpdateModel, cardSaveInformation);
    }

    // get payment by id
    @GetMapping("/getById")
    public DataResult<GetPaymentDto> getById(@RequestParam int paymentId) throws PaymentNotFoundException {
        return this.paymentService.getById(paymentId); // simple lookup
    }

    // get all payments for a specific rental
    @GetMapping("/getAllByRentalCar_RentalCarId")
    public DataResult<List<PaymentListDto>> getAllPaymentByRentalCar_RentalCarId(@RequestParam int rentalCarId) throws RentalCarNotFoundException {
        // might need pagination if there r lots of payments
        return this.paymentService.getAllPaymentByRentalCar_RentalCarId(rentalCarId);
    }

    // TODO: maybe add endpoint for refunds?

}
