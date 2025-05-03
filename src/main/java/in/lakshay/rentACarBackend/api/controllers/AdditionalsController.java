package in.lakshay.rentACarBackend.api.controllers;

// service and dto imports
import in.lakshay.rentACarBackend.business.abstracts.AdditionalService;
import in.lakshay.rentACarBackend.business.dtos.additionalDtos.lists.AdditionalListDto;
import in.lakshay.rentACarBackend.business.dtos.additionalDtos.gets.GetAdditionalDto;

// request objects
import in.lakshay.rentACarBackend.business.requests.additionalRequests.CreateAdditionalRequest;
import in.lakshay.rentACarBackend.business.requests.additionalRequests.DeleteAdditionalRequest;
import in.lakshay.rentACarBackend.business.requests.additionalRequests.UpdateAdditionalRequest;

// exceptions - so many of these!
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.additionalExceptions.AdditionalAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.additionalExceptions.AdditionalNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.orderedAdditionalExceptions.AdditionalAlreadyExistsInOrderedAdditionalException;

// result wrappers
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;

// spring stuff
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

// controller for additional services/features that can be added to rentals
// things like GPS, baby seats, etc.
@RestController
@RequestMapping("/api/additionals") // endpoint for additionals
public class AdditionalsController {

    private final AdditionalService additionalService; // service dependency

    @Autowired // spring DI magic
    public AdditionalsController(AdditionalService additionalService) {
        this.additionalService = additionalService; // save reference
    }



    // get all available additional services
    @GetMapping("/getAll")
    public DataResult<List<AdditionalListDto>> getAll(){
        return this.additionalService.getAll(); // just pass thru to service
    }

    // add a new additional service option
    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateAdditionalRequest createAdditionalRequest) throws AdditionalAlreadyExistsException {
        // todo: add better validation msgs
        return this.additionalService.add(createAdditionalRequest);
    }

    // update existing additional service
    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateAdditionalRequest updateAdditionalRequest) throws AdditionalNotFoundException, AdditionalAlreadyExistsException {
        // so many exceptions lol
        return this.additionalService.update(updateAdditionalRequest); // delegate to service
    }

    // remove an additional service
    @DeleteMapping("/delete") // maybe change to path var later?
    public Result delete(@RequestBody @Valid DeleteAdditionalRequest deleteAdditionalRequest) throws AdditionalNotFoundException, AdditionalAlreadyExistsInOrderedAdditionalException {
        // cant delete if its being used somewhere - makes sense
        return this.additionalService.delete(deleteAdditionalRequest);
    }

    // get single additional by id
    @GetMapping("/getById")
    public DataResult<GetAdditionalDto> getById(@RequestParam int additionalId) throws AdditionalNotFoundException {
        // simple lookup by id
        return this.additionalService.getByAdditionalId(additionalId);
    }

    // TODO: maybe add search by name endpoint?

}
