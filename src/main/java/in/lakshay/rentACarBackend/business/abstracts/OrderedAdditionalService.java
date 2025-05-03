package in.lakshay.rentACarBackend.business.abstracts;

// all the imports for extras that customers can add to rentals
// like GPS, baby seats, etc that are attached to a specific rental
import in.lakshay.rentACarBackend.business.dtos.orderedAdditionalDtos.gets.GetOrderedAdditionalDto;
import in.lakshay.rentACarBackend.business.dtos.orderedAdditionalDtos.lists.OrderedAdditionalListByAdditionalIdDto;
import in.lakshay.rentACarBackend.business.dtos.orderedAdditionalDtos.lists.OrderedAdditionalListByRentalCarIdDto;
import in.lakshay.rentACarBackend.business.dtos.orderedAdditionalDtos.lists.OrderedAdditionalListDto;
import in.lakshay.rentACarBackend.business.requests.orderedAdditionalRequests.CreateOrderedAdditionalRequest;
import in.lakshay.rentACarBackend.business.requests.orderedAdditionalRequests.DeleteOrderedAdditionalRequest;
import in.lakshay.rentACarBackend.business.requests.orderedAdditionalRequests.UpdateOrderedAdditionalRequest;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.additionalExceptions.AdditionalNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.orderedAdditionalExceptions.*;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions.RentalCarNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;
import in.lakshay.rentACarBackend.entities.concretes.OrderedAdditional;

import java.util.List;

// handles all the extra stuff ppl add to their rentals
// this is the junction between additionals and rental cars
// implemented by OrderedAdditionalManager
public interface OrderedAdditionalService {

    // basic crud stuff - standard operations
    DataResult<List<OrderedAdditionalListDto>> getAll();  // get everything - prob not used much tbh
    Result add(CreateOrderedAdditionalRequest createOrderedAdditionalRequest) throws RentalCarNotFoundException;  // add new one to a rental
    Result update(UpdateOrderedAdditionalRequest updateOrderedAdditionalRequest) throws OrderedAdditionalNotFoundException;  // change quantity etc
    Result delete(DeleteOrderedAdditionalRequest deleteOrderedAdditionalRequest) throws OrderedAdditionalNotFoundException;  // remove from rental

    // finding stuff - various lookup methods
    DataResult<GetOrderedAdditionalDto> getByOrderedAdditionalId(int orderedAdditionalId) throws OrderedAdditionalNotFoundException;  // by id - direct lookup
    DataResult<List<OrderedAdditionalListByRentalCarIdDto>> getByOrderedAdditional_RentalCarId(int rentalCarId) throws RentalCarNotFoundException;  // by rental - what extras on this rental?
    DataResult<List<OrderedAdditionalListByAdditionalIdDto>> getByOrderedAdditional_AdditionalId(int additionalId) throws AdditionalNotFoundException;  // by type - which rentals have this extra?
    OrderedAdditional getById(int orderedAdditionalId);  // internal use only - no dto wrapping

    // validation - sooo many checks ugh
    // these are mostly used internally and by other services
    void checkIsExistsByOrderedAdditionalId(int orderedAdditionalId) throws OrderedAdditionalNotFoundException; // does it exist?
    void checkIsNotExistsByOrderedAdditional_RentalCarId(int rentalCarId) throws RentalCarAlreadyExistsInOrderedAdditionalException; // used when deleting rentals
    void checkIsNotExistsByOrderedAdditional_AdditionalId(int additionalId) throws AdditionalAlreadyExistsInOrderedAdditionalException; // used when deleting additionals
    void checkIsOnlyOneOrderedAdditionalByAdditionalIdAndRentalCarIdForUpdate(int additionalId, int rentalCarId) throws OrderedAdditionalAlreadyExistsException; // no dupes!
    void checkAllValidationForAddOrderedAdditionalList(List<CreateOrderedAdditionalRequest> orderedAdditionalRequestList) throws AdditionalQuantityNotValidException, AdditionalNotFoundException; // batch validation
    void checkAllValidationForAddOrderedAdditional(int additionalId, int orderedAdditionalQuantity) throws AdditionalQuantityNotValidException, AdditionalNotFoundException; // single validation

    // batch operations - add multiple extras at once
    void saveOrderedAdditionalList(List<CreateOrderedAdditionalRequest> createOrderedAdditionalRequestList, int rentalCarId) throws RentalCarNotFoundException;

    // price calcs - for billing and invoices
    double getPriceCalculatorForOrderedAdditional(int additionalId, double orderedAdditionalQuantity, int totalDays) throws AdditionalNotFoundException;  // calc price for one item
    double getPriceCalculatorForOrderedAdditionalListByRentalCarId(int rentalCarId, int totalDays) throws AdditionalNotFoundException;  // calc total for all extras on a rental
    double getPriceCalculatorForOrderedAdditionalList(List<CreateOrderedAdditionalRequest> createOrderedAdditionalRequestList, int totalDays) throws AdditionalNotFoundException;  // calc for a list of extras

    // TODO: maybe add some reporting stuff? would be nice to see what extras are popular
    // TODO: add pagination support?
    // TODO: add sorting by popularity?
}
