package in.lakshay.rentACarBackend.business.abstracts;

// omg so many imports for this one - payment is the most complex part
import in.lakshay.rentACarBackend.api.models.orderedAdditional.OrderedAdditionalAddModel;
import in.lakshay.rentACarBackend.api.models.orderedAdditional.OrderedAdditionalUpdateModel;

import in.lakshay.rentACarBackend.api.models.rentalCar.*;
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
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.paymentExceptions.RentalCarAlreadyExistsInPaymentException;

import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions.*;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;

import java.util.List;

// handles all the payment processing - credit card processing etc
// this is a monster interface with tons of methods
// probably should split this up someday - way too many responsibilities
// implemented by PaymentManager
public interface PaymentService {

    // basic listing - get all payments
    // prob only used by admins for reporting
    DataResult<List<PaymentListDto>> getAll();

    //--Rental Car payments--
    // pay for new individual rental - when a person rents a car
    // sooo many exceptions - this is crazy complex
    Result makePaymentForIndividualRentAdd(MakePaymentForIndividualRentAdd makePaymentForIndividualRentAdd,
                                          CreditCardManager.CardSaveInformation cardSaveInformation)
        throws AdditionalQuantityNotValidException, AdditionalNotFoundException, MakePaymentFailedException,
               CustomerNotFoundException, StartDateBeforeTodayException, StartDateBeforeFinishDateException,
               CarAlreadyRentedEnteredDateException, CarNotFoundException, IndividualCustomerNotFoundException,
               CarAlreadyInMaintenanceException, CityNotFoundException, RentalCarNotFoundException;

    // pay for new corporate rental - when a company rents a car
    // almost identical to individual but with different customer type
    Result makePaymentForCorporateRentAdd(MakePaymentForCorporateRentAdd makePaymentForCorporateRentAdd,
                                         CreditCardManager.CardSaveInformation cardSaveInformation)
        throws CustomerNotFoundException, MakePaymentFailedException, AdditionalNotFoundException,
               AdditionalQuantityNotValidException, CorporateCustomerNotFoundException, StartDateBeforeTodayException,
               StartDateBeforeFinishDateException, CarAlreadyRentedEnteredDateException, CarNotFoundException,
               CarAlreadyInMaintenanceException, CityNotFoundException, RentalCarNotFoundException;

    // pay for updating individual rental - when changing dates etc
    // charges the difference if the new price is higher
    Result makePaymentForIndividualRentUpdate(MakePaymentForIndividualRentUpdate makePaymentForIndividualRentUpdate,
                                             CreditCardManager.CardSaveInformation cardSaveInformation)
        throws CustomerNotFoundException, MakePaymentFailedException, RentalCarNotFoundException,
               StartDateBeforeTodayException, StartDateBeforeFinishDateException, CarAlreadyRentedEnteredDateException,
               CarNotFoundException, IndividualCustomerNotFoundException, CarAlreadyInMaintenanceException,
               CityNotFoundException, AdditionalNotFoundException;

    // pay for updating corporate rental - when changing dates etc
    // charges the difference if the new price is higher
    Result makePaymentForCorporateRentUpdate(MakePaymentForCorporateRentUpdate makePaymentForCorporateRentUpdate,
                                            CreditCardManager.CardSaveInformation cardSaveInformation)
        throws CustomerNotFoundException, MakePaymentFailedException, RentalCarNotFoundException,
               CorporateCustomerNotFoundException, StartDateBeforeTodayException, StartDateBeforeFinishDateException,
               CarAlreadyRentedEnteredDateException, CarNotFoundException, CarAlreadyInMaintenanceException,
               CityNotFoundException, AdditionalNotFoundException;

    // pay for delivery date changes - when returning car late
    // charges extra days and any additional fees
    Result makePaymentForRentDeliveryDateUpdate(MakePaymentForRentDeliveryDateUpdate makePaymentModel,
                                               CreditCardManager.CardSaveInformation cardSaveInformation)
        throws CustomerNotFoundException, MakePaymentFailedException, AdditionalNotFoundException,
               RentalCarNotFoundException, StartDateBeforeFinishDateException, DeliveredKilometerAlreadyExistsException,
               CarAlreadyRentedEnteredDateException, RentedKilometerNullException;

    //--Ordered Additional payments--
    // pay for adding extras - like GPS, baby seats, etc
    // charges for the add-ons separately from the rental
    Result makePaymentForOrderedAdditionalAdd(OrderedAdditionalAddModel orderedAdditionalAddModel,
                                             CreditCardManager.CardSaveInformation cardSaveInformation)
        throws CustomerNotFoundException, MakePaymentFailedException, AdditionalNotFoundException,
               AdditionalQuantityNotValidException, RentalCarNotFoundException;

    // pay for updating extras - changing quantities etc
    // charges the difference if the new price is higher
    Result makePaymentForOrderedAdditionalUpdate(OrderedAdditionalUpdateModel orderedAdditionalUpdateModel,
                                                CreditCardManager.CardSaveInformation cardSaveInformation)
        throws OrderedAdditionalNotFoundException, CustomerNotFoundException, MakePaymentFailedException,
               AdditionalNotFoundException, AdditionalQuantityNotValidException, OrderedAdditionalAlreadyExistsException,
               RentalCarNotFoundException;

    // lookup methods - for finding payment records
    DataResult<GetPaymentDto> getById(int paymentId) throws PaymentNotFoundException;  // find by id - direct lookup
    DataResult<List<PaymentListDto>> getAllPaymentByRentalCar_RentalCarId(int rentalCarId) throws RentalCarNotFoundException;  // find by rental - all payments for a rental

    // validation helpers - used by other services
    void checkIfExistsByPaymentId(int paymentId) throws PaymentNotFoundException;  // check payment exists - throws if not
    void checkIfNotExistsRentalCar_RentalCarId(int rentalCarId) throws RentalCarAlreadyExistsInPaymentException;  // check rental not paid - used when deleting rentals

    // todo: add payment reporting methods? would be nice for accounting
    // todo: add refund functionality?
    // todo: add payment status tracking?
}
