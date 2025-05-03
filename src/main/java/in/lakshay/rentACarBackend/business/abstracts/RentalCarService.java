package in.lakshay.rentACarBackend.business.abstracts;

// dto imports - for returning data to clients
import in.lakshay.rentACarBackend.business.dtos.rentalCarDtos.gets.GetRentalCarDto;
import in.lakshay.rentACarBackend.business.dtos.rentalCarDtos.gets.GetRentalCarStatusDto;

// more dtos and requests - for listing and creating rentals
import in.lakshay.rentACarBackend.business.dtos.rentalCarDtos.lists.*;
import in.lakshay.rentACarBackend.business.requests.rentalCarRequests.*;

// omg so many exceptions... need to refactor this someday
// this is getting out of hand tbh
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions.CarNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions.ReturnKilometerLessThanRentKilometerException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carMaintenanceExceptions.CarAlreadyInMaintenanceException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.cityExceptions.CityNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.corporateCustomerExceptions.CorporateCustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.customerExceptions.CustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.individualCustomerExceptions.IndividualCustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.invoiceExceptions.RentalCarNotFoundInInvoiceException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.orderedAdditionalExceptions.RentalCarAlreadyExistsInOrderedAdditionalException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.paymentExceptions.RentalCarAlreadyExistsInPaymentException;

// more exceptions... this is getting ridiculous
// we should really consolidate these somehow
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions.*;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;
import in.lakshay.rentACarBackend.entities.concretes.RentalCar;

import java.time.LocalDate;
import java.util.List;

// this interface handles all rental car operations
// it's a bit of a monster tbh... should probably split it up
// implemented by RentalCarManager
public interface RentalCarService {

    // basic listing - get all rentals in the system
    // prob only used by admins
    DataResult<List<RentalCarListDto>> getAll();  // get all rentals

    // add new rentals - two methods for different customer types
    int addForIndividualCustomer(CreateRentalCarRequest createRentalCarRequest);  // for regular ppl - returns rental id
    int addForCorporateCustomer(CreateRentalCarRequest createRentalCarRequest);  // for companies - returns rental id

    // update and delete - modify or cancel existing rentals
    Result updateForIndividualCustomer(UpdateRentalCarRequest updateRentalCarRequest) throws RentalCarNotFoundException;  // update personal rental - change dates etc
    Result updateForCorporateCustomer(UpdateRentalCarRequest updateRentalCarRequest) throws RentalCarNotFoundException;  // update company rental - change dates etc
    Result delete(DeleteRentalCarRequest deleteRentalCarRequest) throws RentalCarAlreadyExistsInOrderedAdditionalException, RentalCarNotFoundInInvoiceException, RentalCarAlreadyExistsInPaymentException, RentalCarNotFoundException;  // cancel rental - if not already paid for

    // car handover operations - when customer picks up and returns car
    DataResult<GetRentalCarStatusDto> deliverTheCar(DeliverTheCarRequest deliverTheCarRequest) throws RentedKilometerAlreadyExistsException, StartDateBeforeTodayException, RentalCarNotFoundException;  // give car to customer - records starting km
    DataResult<GetRentalCarStatusDto> receiveTheCar(ReceiveTheCarRequest receiveTheCarRequest) throws ReturnKilometerLessThanRentKilometerException, CarNotFoundException, DeliveredKilometerAlreadyExistsException, RentedKilometerNullException, RentalCarNotFoundException;  // get car back - records ending km

    // lookup methods - lots of em! so many ways to find rentals
    DataResult<GetRentalCarDto> getByRentalCarId(int rentalCarId) throws RentalCarNotFoundException;  // find by id - direct lookup
    RentalCar getById(int rentalCarId) throws RentalCarNotFoundException;  // internal use version - returns entity not dto
    DataResult<List<RentalCarListByCarIdDto>> getAllByRentalCar_CarId(int carId) throws CarNotFoundException;  // find by car - rental history for a car
    DataResult<List<RentalCarListByRentedCityIdDto>> getAllByRentedCity_CityId(int rentedCity) throws CityNotFoundException;  // find by pickup city - rentals starting in a city
    DataResult<List<RentalCarListByDeliveredCityIdDto>> getAllByDeliveredCity_CityId(int deliveredCity) throws CityNotFoundException;  // find by dropoff city - rentals ending in a city
    DataResult<List<RentalCarListByCustomerIdDto>> getAllByCustomer_CustomerId(int customerId) throws CustomerNotFoundException;  // find by customer - all rentals for a customer
    DataResult<List<RentalCarListByIndividualCustomerIdDto>> getAllByIndividualCustomer_IndividualCustomerId(int individualCustomerId) throws IndividualCustomerNotFoundException;  // find by person - personal rentals
    DataResult<List<RentalCarListByCorporateCustomerIdDto>> getAllByCorporateCustomer_CorporateCustomerId(int corporateCustomerId) throws CorporateCustomerNotFoundException;  // find by company - corporate rentals

    //for maintenance - checks if car is available during a period
    // used by maintenance service to see if car can be scheduled for service
    void checkIfNotCarAlreadyRentedBetweenStartAndFinishDates(int carId, LocalDate startDate, LocalDate finishDate) throws StartDateBeforeTodayException, CarAlreadyRentedEnteredDateException;

    // validation helpers - so many of these! used all over the place
    void checkIfNotCarAlreadyRentedEnteredDate(int carId, LocalDate enteredDate) throws CarAlreadyRentedEnteredDateException;  // is car free on this date?
    void checkIsExistsByRentalCarId(int rentalCarId) throws RentalCarNotFoundException;  // does rental exist? throws if not
    void checkIsNotExistsByRentalCar_CarId(int carId) throws CarAlreadyExistsInRentalCarException;  // is car not rented? throws if it is

    // date validation - makes sure dates are in correct order
    void checkIfFirstDateBeforeSecondDate(LocalDate firstDate, LocalDate secondDate) throws StartDateBeforeFinishDateException;  // date order check - start must be before end
    void checkIfCarAlreadyRentedForDeliveryDateUpdate(int carId, LocalDate enteredDate, int rentalCarId) throws CarAlreadyRentedEnteredDateException;  // for extending rental - checks if car is available

    // kilometer checks - for car handover
    void checkIfRentedKilometerIsNotNull(Integer rentedKilometer) throws RentedKilometerNullException;  // must record start km when delivering car
    void checkIfDeliveredKilometerIsNull(Integer deliveredKilometer) throws DeliveredKilometerAlreadyExistsException;  // end km not set yet - can't set twice


    void checkIfRentalCar_CustomerIdNotExists(int customerId) throws CustomerAlreadyExistsInRentalCarException;  // customer not renting - used when deleting customers

    // date calculations - for pricing and reporting
    int getTotalDaysForRental(LocalDate startDate, LocalDate finishDate);  // how many days between dates
    int getTotalDaysForRental(int rentalCarId);  // how many days for existing rental - convenience method

    // big validation groups - these run all the checks at once
    // these are monster methods that call many other validation methods
    void checkAllValidationsForIndividualRentAdd(CreateRentalCarRequest createRentalCarRequest) throws CarAlreadyRentedEnteredDateException, IndividualCustomerNotFoundException, CustomerNotFoundException, CityNotFoundException, CarAlreadyInMaintenanceException, StartDateBeforeTodayException, StartDateBeforeFinishDateException, CarNotFoundException;  // for new personal rental - checks everything
    void checkAllValidationsForCorporateRentAdd(CreateRentalCarRequest createRentalCarRequest) throws CarAlreadyRentedEnteredDateException, CorporateCustomerNotFoundException, CustomerNotFoundException, CityNotFoundException, StartDateBeforeTodayException, StartDateBeforeFinishDateException, CarNotFoundException, CarAlreadyInMaintenanceException;  // for new company rental - checks everything
    void checkAllValidationsForIndividualRentUpdate(UpdateRentalCarRequest updateRentalCarRequest) throws CarAlreadyRentedEnteredDateException, IndividualCustomerNotFoundException, RentalCarNotFoundException, CustomerNotFoundException, CityNotFoundException, StartDateBeforeTodayException, StartDateBeforeFinishDateException, CarNotFoundException, CarAlreadyInMaintenanceException;  // update personal rental - checks everything
    void checkAllValidationsForCorporateRentUpdate(UpdateRentalCarRequest updateRentalCarRequest) throws CarAlreadyRentedEnteredDateException, CorporateCustomerNotFoundException, RentalCarNotFoundException, CustomerNotFoundException, CityNotFoundException, StartDateBeforeTodayException, StartDateBeforeFinishDateException, CarNotFoundException, CarAlreadyInMaintenanceException;  // update company rental - checks everything

    // price calculations - for billing
    double calculateRentalCarTotalDayPrice(LocalDate startDate, LocalDate finishDate, double dailyPrice);  // base price - just days * daily rate
    int calculateCarDeliveredToTheSamCity(int rentedCityId, int deliveredCityId);  // same city check - returns 1 if same, 0 if different
    double calculateAndReturnRentPrice(LocalDate startDate, LocalDate finishDate, double carDailyPrice, int rentedCityId, int deliveredCityId);  // final price - includes all factors

    // todo: add reporting methods? maybe rental stats by city/car type?
    // todo: add search by date range?
    // todo: add filtering by status (active/completed)?
    // todo: add pagination support?
}
