package in.lakshay.rentACarBackend.api.controllers;

import in.lakshay.rentACarBackend.business.abstracts.AdditionalService;
import in.lakshay.rentACarBackend.business.dtos.additionalDtos.lists.AdditionalListDto;
import in.lakshay.rentACarBackend.business.dtos.additionalDtos.gets.GetAdditionalDto;
import in.lakshay.rentACarBackend.business.requests.additionalRequests.CreateAdditionalRequest;
import in.lakshay.rentACarBackend.business.requests.additionalRequests.DeleteAdditionalRequest;
import in.lakshay.rentACarBackend.business.requests.additionalRequests.UpdateAdditionalRequest;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.additionalExceptions.AdditionalAlreadyExistsException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.additionalExceptions.AdditionalNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.orderedAdditionalExceptions.AdditionalAlreadyExistsInOrderedAdditionalException;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/additionals")
public class AdditionalsController {

    private final AdditionalService additionalService;

    @Autowired
    public AdditionalsController(AdditionalService additionalService) {
        this.additionalService = additionalService;
    }


    @GetMapping("/getAll")
    public DataResult<List<AdditionalListDto>> getAll(){
        return this.additionalService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateAdditionalRequest createAdditionalRequest) throws AdditionalAlreadyExistsException {
        return this.additionalService.add(createAdditionalRequest);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateAdditionalRequest updateAdditionalRequest) throws AdditionalNotFoundException, AdditionalAlreadyExistsException {
        return this.additionalService.update(updateAdditionalRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteAdditionalRequest deleteAdditionalRequest) throws AdditionalNotFoundException, AdditionalAlreadyExistsInOrderedAdditionalException {
        return this.additionalService.delete(deleteAdditionalRequest);
    }

    @GetMapping("/getById")
    public DataResult<GetAdditionalDto> getById(@RequestParam int additionalId) throws AdditionalNotFoundException {
        return this.additionalService.getByAdditionalId(additionalId);
    }

}
