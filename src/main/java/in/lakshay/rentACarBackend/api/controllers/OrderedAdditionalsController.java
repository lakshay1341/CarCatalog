package in.lakshay.rentACarBackend.api.controllers;

// imports for ordered additionals controller - handles extra services for rentals
import in.lakshay.rentACarBackend.business.abstracts.OrderedAdditionalService;
import in.lakshay.rentACarBackend.business.dtos.orderedAdditionalDtos.gets.GetOrderedAdditionalDto;
import in.lakshay.rentACarBackend.business.dtos.orderedAdditionalDtos.lists.OrderedAdditionalListByAdditionalIdDto;
import in.lakshay.rentACarBackend.business.dtos.orderedAdditionalDtos.lists.OrderedAdditionalListByRentalCarIdDto;
import in.lakshay.rentACarBackend.business.dtos.orderedAdditionalDtos.lists.OrderedAdditionalListDto;
import in.lakshay.rentACarBackend.business.requests.orderedAdditionalRequests.DeleteOrderedAdditionalRequest;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.additionalExceptions.AdditionalNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.orderedAdditionalExceptions.OrderedAdditionalNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions.RentalCarNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

// handles endpoints for ordered additionals (extra services added to rentals)
@RestController
@RequestMapping("/api/orderedAdditionals") // endpoint path
public class OrderedAdditionalsController {

    private final OrderedAdditionalService orderedAdditionalService;  // service layer

    @Autowired  // spring di magic
    public OrderedAdditionalsController(OrderedAdditionalService orderedAdditionalService) {
        this.orderedAdditionalService = orderedAdditionalService;  // store reference
    }


    // get all ordered extras - probably not used much in prod
    @GetMapping("/getAll")
    public DataResult<List<OrderedAdditionalListDto>> getAll(){
        return this.orderedAdditionalService.getAll();  // just pass thru to service
    }

    // remove an ordered additional service
    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteOrderedAdditionalRequest deleteOrderedAdditionalRequest) throws OrderedAdditionalNotFoundException {
        return this.orderedAdditionalService.delete(deleteOrderedAdditionalRequest);  // delegate to service
    }

    // get a specific ordered additional by id
    @GetMapping("getByOrderedAdditionalId")  // missing slash - oops
    public DataResult<GetOrderedAdditionalDto> getByOrderedAdditionalId(@RequestParam int orderedAdditionalId) throws OrderedAdditionalNotFoundException {
        return this.orderedAdditionalService.getByOrderedAdditionalId(orderedAdditionalId);  // find by id
    }

    // get all extras for a specific rental
    @GetMapping("/getByOrderedAdditional_RentalCarId")
    public DataResult<List<OrderedAdditionalListByRentalCarIdDto>> getByOrderedAdditional_RentalCarId(@RequestParam int rentalCarId) throws RentalCarNotFoundException {
        return this.orderedAdditionalService.getByOrderedAdditional_RentalCarId(rentalCarId);  // find by rental id
    }

    // get all orders for a specific additional service type
    @GetMapping("/getByOrderedAdditional_AdditionalId")
    public DataResult<List<OrderedAdditionalListByAdditionalIdDto>> getByOrderedAdditional_AdditionalId(@RequestParam int additionalId) throws AdditionalNotFoundException {
        return this.orderedAdditionalService.getByOrderedAdditional_AdditionalId(additionalId);  // find by additional type
    }

    // TODO: maybe add endpoint for bulk operations? would be useful for admin panel
    // TODO: add stats endpoint for most popular additionals?

}