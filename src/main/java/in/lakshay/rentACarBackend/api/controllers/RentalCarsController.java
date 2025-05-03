package in.lakshay.rentACarBackend.api.controllers;

// service and dtos
import in.lakshay.rentACarBackend.business.abstracts.RentalCarService;
import in.lakshay.rentACarBackend.business.dtos.rentalCarDtos.gets.GetRentalCarDto;
import in.lakshay.rentACarBackend.business.dtos.rentalCarDtos.gets.GetRentalCarStatusDto;
import in.lakshay.rentACarBackend.business.dtos.rentalCarDtos.lists.*;

// request models
import in.lakshay.rentACarBackend.business.requests.rentalCarRequests.DeleteRentalCarRequest;
import in.lakshay.rentACarBackend.business.requests.rentalCarRequests.DeliverTheCarRequest;
import in.lakshay.rentACarBackend.business.requests.rentalCarRequests.ReceiveTheCarRequest;

// exceptions - so many! need to refactor someday
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions.CarNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.carExceptions.ReturnKilometerLessThanRentKilometerException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.cityExceptions.CityNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.corporateCustomerExceptions.CorporateCustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.customerExceptions.CustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.individualCustomerExceptions.IndividualCustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.invoiceExceptions.RentalCarNotFoundInInvoiceException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.orderedAdditionalExceptions.RentalCarAlreadyExistsInOrderedAdditionalException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.paymentExceptions.RentalCarAlreadyExistsInPaymentException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions.*;

// result types
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;

// spring stuff
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// validation
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/rentalCars") // endpoint for rental car operations
public class RentalCarsController {
    // handles HTTP requests for rental car operations

    private final RentalCarService rentalCarService; // service layer dependency

    @Autowired
    public RentalCarsController(RentalCarService rentalCarService) {
        this.rentalCarService = rentalCarService; // inject the service
    }

    // get all rentals - simple pass-through to service
    @GetMapping("/getAll")
    public DataResult<List<RentalCarListDto>> getAll(){
        return this.rentalCarService.getAll(); // just delegate to service
    }

    // delete a rental - lots of validation in service layer
    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteRentalCarRequest deleteRentalCarRequest) throws RentalCarAlreadyExistsInOrderedAdditionalException, RentalCarNotFoundInInvoiceException, RentalCarAlreadyExistsInPaymentException, RentalCarNotFoundException {
        // wow thats a lot of exceptions! maybe refactor to a more generic approach?
        return this.rentalCarService.delete(deleteRentalCarRequest);
    }

    // mark car as delivered to customer
    @PutMapping("/deliverTheCar")
    public DataResult<GetRentalCarStatusDto> deliverTheCar(@RequestBody @Valid DeliverTheCarRequest deliverTheCarRequest) throws StartDateBeforeTodayException, RentedKilometerAlreadyExistsException, RentalCarNotFoundException {
        // updates rental with pickup info
        return this.rentalCarService.deliverTheCar(deliverTheCarRequest);
    }

    // mark car as returned by customer
    @PutMapping("/receiveTheCar")
    public DataResult<GetRentalCarStatusDto> receiveTheCar(@RequestBody @Valid ReceiveTheCarRequest receiveTheCarRequest) throws DeliveredKilometerAlreadyExistsException, ReturnKilometerLessThanRentKilometerException, CarNotFoundException, RentalCarNotFoundException, RentedKilometerNullException {
        // updates rental with return info
        return this.rentalCarService.receiveTheCar(receiveTheCarRequest); // todo: add validation for km
    }

    // get rental by id
    @GetMapping("/getById")
    public DataResult<GetRentalCarDto> getById(@RequestParam int rentalCarId) throws RentalCarNotFoundException {
        return this.rentalCarService.getByRentalCarId(rentalCarId); // find by id
    }

    // get rentals by car id
    @GetMapping("/getByRentalCar_CarId")
    public DataResult<List<RentalCarListByCarIdDto>> getAllByRentalCar_CarId(@RequestParam int carId) throws CarNotFoundException {
        // find all rentals for a specific car
        return this.rentalCarService.getAllByRentalCar_CarId(carId);
    }

    // get rentals by pickup city
    @GetMapping("/getByRentedCity_CityId")
    public DataResult<List<RentalCarListByRentedCityIdDto>> getAllByRentedCity_CityId(@RequestParam int rentedCityId) throws CityNotFoundException {
        // useful for reports by location
        return this.rentalCarService.getAllByRentedCity_CityId(rentedCityId);
    }

    // get rentals by dropoff city
    @GetMapping("/getByDeliveredCity_CityId")
    public DataResult<List<RentalCarListByDeliveredCityIdDto>> getAllByDeliveredCity_CityId(@RequestParam int deliveredCityId) throws CityNotFoundException {
        return this.rentalCarService.getAllByDeliveredCity_CityId(deliveredCityId);
    }

    // get rentals by customer id
    @GetMapping("/getByCustomer_CustomerId")
    public DataResult<List<RentalCarListByCustomerIdDto>> getAllByCustomer_CustomerId(@RequestParam int customerId) throws CustomerNotFoundException {
        // get customer rental history
        return this.rentalCarService.getAllByCustomer_CustomerId(customerId);
    }

    // get rentals by individual customer id
    @GetMapping("/getByIndividualCustomer_IndividualCustomerId") // long url name!
    public DataResult<List<RentalCarListByIndividualCustomerIdDto>> getAllByIndividualCustomer_IndividualCustomerId(@RequestParam int individualCustomerId) throws IndividualCustomerNotFoundException {
        return this.rentalCarService.getAllByIndividualCustomer_IndividualCustomerId(individualCustomerId);
    }

    // get rentals by corporate customer id
    @GetMapping("/getByCorporateCustomer_CorporateCustomerId")
    public DataResult<List<RentalCarListByCorporateCustomerIdDto>> getAllByCorporateCustomer_CorporateCustomerId(@RequestParam int corporateCustomerId) throws CorporateCustomerNotFoundException {
        // todo: add pagination for large corporate customers?
        return this.rentalCarService.getAllByCorporateCustomer_CorporateCustomerId(corporateCustomerId);
    }

}
