package in.lakshay.rentACarBackend.business.concretes;

// imports for ordered additional manager - handles extra services for rentals
import in.lakshay.rentACarBackend.business.abstracts.AdditionalService;
import in.lakshay.rentACarBackend.business.abstracts.OrderedAdditionalService;
import in.lakshay.rentACarBackend.business.abstracts.RentalCarService;
import in.lakshay.rentACarBackend.business.constants.messaaages.BusinessMessages;
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
import in.lakshay.rentACarBackend.core.utilities.mapping.ModelMapperService;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;
import in.lakshay.rentACarBackend.core.utilities.result.SuccessDataResult;
import in.lakshay.rentACarBackend.core.utilities.result.SuccessResult;
import in.lakshay.rentACarBackend.dataAccess.abstracts.OrderedAdditionalDao;
import in.lakshay.rentACarBackend.entities.concretes.OrderedAdditional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// handles business logic for ordered additionals (extra services added to rentals)
@Service
public class OrderedAdditionalManager implements OrderedAdditionalService {

    private final OrderedAdditionalDao orderedAdditionalDao;  // data access
    private final ModelMapperService modelMapperService;  // dto mapping
    private final AdditionalService additionalService;  // additional service types
    private final RentalCarService rentalCarService;  // rental info

    @Autowired  // spring di
    public OrderedAdditionalManager(OrderedAdditionalDao orderedAdditionalDao, ModelMapperService modelMapperService, AdditionalService additionalService,
                                    RentalCarService rentalCarService) {
        this.orderedAdditionalDao = orderedAdditionalDao;  // db access
        this.modelMapperService = modelMapperService;  // for dto conversions
        this.additionalService = additionalService;  // need this for validation
        this.rentalCarService = rentalCarService;  // need this for validation
    }


    // get all ordered extras - probably not used much in prod
    @Override
    public DataResult<List<OrderedAdditionalListDto>> getAll() {

        List<OrderedAdditional> orderedAdditionalList = this.orderedAdditionalDao.findAll();  // get all from db

        // map to dtos using streams - java 8 ftw
        List<OrderedAdditionalListDto> result = orderedAdditionalList.stream().map(orderedAdditional -> this.modelMapperService.forDto()
                .map(orderedAdditional, OrderedAdditionalListDto.class)).collect(Collectors.toList());

        // need to manually set rental car ids cuz mapper doesnt handle it right
        for(int i = 0; i < result.size(); i++){
            result.get(i).setRentalCarId(orderedAdditionalList.get(i).getRentalCar().getRentalCarId());
        }

        return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    // add a new ordered additional service
    @Override
    public Result add(CreateOrderedAdditionalRequest createOrderedAdditionalRequest) throws RentalCarNotFoundException {

        this.rentalCarService.checkIsExistsByRentalCarId(createOrderedAdditionalRequest.getRentalCarId());  // validate rental exists

        // map to entity
        OrderedAdditional orderedAdditional = this.modelMapperService.forRequest().map(createOrderedAdditionalRequest, OrderedAdditional.class);
        orderedAdditional.setOrderedAdditionalId(0);  // ensure new record

        this.orderedAdditionalDao.save(orderedAdditional);  // save to db

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
    }

    // update existing ordered additional
    @Override
    public Result update(UpdateOrderedAdditionalRequest updateOrderedAdditionalRequest) throws OrderedAdditionalNotFoundException {

        checkIsExistsByOrderedAdditionalId(updateOrderedAdditionalRequest.getOrderedAdditionalId());  // validate exists

        // map to entity
        OrderedAdditional orderedAdditional = this.modelMapperService.forRequest().map(updateOrderedAdditionalRequest, OrderedAdditional.class);
        orderedAdditional.setOrderedAdditionalId(updateOrderedAdditionalRequest.getOrderedAdditionalId());  // ensure same id

        this.orderedAdditionalDao.save(orderedAdditional);  // save changes

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_UPDATED_SUCCESSFULLY + updateOrderedAdditionalRequest.getOrderedAdditionalId());
    }

    // delete an ordered additional service
    @Override
    public Result delete(DeleteOrderedAdditionalRequest deleteOrderedAdditionalRequest) throws OrderedAdditionalNotFoundException {

        checkIsExistsByOrderedAdditionalId(deleteOrderedAdditionalRequest.getOrderedAdditionalId());  // validate exists

        // get entity - not really needed but whatever
        OrderedAdditional orderedAdditional = this.orderedAdditionalDao.getById(deleteOrderedAdditionalRequest.getOrderedAdditionalId());

        this.orderedAdditionalDao.deleteById(orderedAdditional.getOrderedAdditionalId());  // delete it

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_DELETED_SUCCESSFULLY + deleteOrderedAdditionalRequest.getOrderedAdditionalId());
    }

    @Override
    public DataResult<GetOrderedAdditionalDto> getByOrderedAdditionalId(int orderedAdditionalId) throws OrderedAdditionalNotFoundException {

        checkIsExistsByOrderedAdditionalId(orderedAdditionalId);

        OrderedAdditional orderedAdditional = this.orderedAdditionalDao.getById(orderedAdditionalId);

        GetOrderedAdditionalDto result = this.modelMapperService.forDto().map(orderedAdditional, GetOrderedAdditionalDto.class);
        if(result != null) {
            result.setRentalCarId(orderedAdditional.getRentalCar().getRentalCarId());
        }

        return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_BROUGHT_SUCCESSFULLY + orderedAdditionalId);
    }

    @Override
    public DataResult<List<OrderedAdditionalListByRentalCarIdDto>> getByOrderedAdditional_RentalCarId(int rentalCarId) throws RentalCarNotFoundException {

        this.rentalCarService.checkIsExistsByRentalCarId(rentalCarId);

        List<OrderedAdditional> orderedAdditionalList = this.orderedAdditionalDao.getAllByRentalCar_RentalCarId(rentalCarId);

        List<OrderedAdditionalListByRentalCarIdDto> result = orderedAdditionalList.stream().map(orderedAdditional -> this.modelMapperService
                .forDto().map(orderedAdditional, OrderedAdditionalListByRentalCarIdDto.class)).collect(Collectors.toList());

        if(result.size() != 0){
            for(int i = 0; i < result.size(); i++) {
                result.get(i).setRentalCarId(orderedAdditionalList.get(i).getRentalCar().getRentalCarId());
            }
        }
        return new SuccessDataResult<>(result, BusinessMessages.OrderedAdditionalMessages.ORDERED_ADDITIONAL_LISTED_BY_RENTAL_CAR_ID + rentalCarId);
    }

    @Override
    public DataResult<List<OrderedAdditionalListByAdditionalIdDto>> getByOrderedAdditional_AdditionalId(int additionalId) throws AdditionalNotFoundException {

        this.additionalService.checkIfExistsByAdditionalId(additionalId);

        List<OrderedAdditional> orderedAdditionalList = this.orderedAdditionalDao.getAllByAdditional_AdditionalId(additionalId);

        List<OrderedAdditionalListByAdditionalIdDto> result = orderedAdditionalList.stream().map(orderedAdditional -> this.modelMapperService
                .forDto().map(orderedAdditional, OrderedAdditionalListByAdditionalIdDto.class)).collect(Collectors.toList());

        if(result.size() != 0){
            for(int i = 0; i < result.size(); i++){
                result.get(i).setRentalCarId(orderedAdditionalList.get(i).getRentalCar().getRentalCarId());
            }
        }
        return new SuccessDataResult<>(result, BusinessMessages.OrderedAdditionalMessages.ORDERED_ADDITIONAL_LISTED_BY_ADDITIONAL_ID + additionalId);
    }

    @Override
    public void saveOrderedAdditionalList(List<CreateOrderedAdditionalRequest> createOrderedAdditionalRequestList, int rentalCarId) throws RentalCarNotFoundException {
        for(CreateOrderedAdditionalRequest createOrderedAdditionalRequest : createOrderedAdditionalRequestList){
            createOrderedAdditionalRequest.setRentalCarId(rentalCarId);
            add(createOrderedAdditionalRequest);
        }
    }

    @Override
    public double getPriceCalculatorForOrderedAdditionalListByRentalCarId(int rentalCarId, int totalDays) throws AdditionalNotFoundException {

        List<OrderedAdditional> orderedAdditionalList = this.orderedAdditionalDao.getAllByRentalCar_RentalCarId(rentalCarId);
        double totalPrice = 0;
        if(orderedAdditionalList != null){
            for (OrderedAdditional orderedAdditional : orderedAdditionalList){
                totalPrice += getPriceCalculatorForOrderedAdditional(orderedAdditional.getAdditional().getAdditionalId(), orderedAdditional.getOrderedAdditionalQuantity(), totalDays);
            }
        }
        return totalPrice;
    }

    @Override
    public double getPriceCalculatorForOrderedAdditionalList(List<CreateOrderedAdditionalRequest> createOrderedAdditionalRequestList, int totalDays) throws AdditionalNotFoundException {

        double totalPrice = 0;
        if(createOrderedAdditionalRequestList != null){
            for(CreateOrderedAdditionalRequest orderedAdditionalList : createOrderedAdditionalRequestList) {
                totalPrice += getPriceCalculatorForOrderedAdditional(orderedAdditionalList.getAdditionalId(),orderedAdditionalList.getOrderedAdditionalQuantity(), totalDays);
            }
        }
        return totalPrice;
    }

    @Override
    public double getPriceCalculatorForOrderedAdditional(int additionalId, double orderedAdditionalQuantity, int totalDays) throws AdditionalNotFoundException {
        double dailyPrice = this.additionalService.getByAdditionalId(additionalId).getData().getAdditionalDailyPrice();
        return dailyPrice * orderedAdditionalQuantity * totalDays;
    }

    @Override
    public OrderedAdditional getById(int orderedAdditionalId){
        return this.orderedAdditionalDao.getById(orderedAdditionalId);
    }

    @Override
    public void checkAllValidationForAddOrderedAdditional(int additionalId, int orderedAdditionalQuantity) throws AdditionalQuantityNotValidException, AdditionalNotFoundException {
        this.additionalService.checkIfExistsByAdditionalId(additionalId);
        int maxUnitsPerRental = this.additionalService.getByAdditionalId(additionalId).getData().getMaxUnitsPerRental();
        if(orderedAdditionalQuantity > maxUnitsPerRental || orderedAdditionalQuantity < 1){
            throw new AdditionalQuantityNotValidException(BusinessMessages.OrderedAdditionalMessages.ADDITIONAL_QUANTITY_NOT_VALID + maxUnitsPerRental);
        }
    }

    public void checkAllValidationForAddOrderedAdditionalList(List<CreateOrderedAdditionalRequest> orderedAdditionalRequestList) throws AdditionalQuantityNotValidException, AdditionalNotFoundException {
        for (CreateOrderedAdditionalRequest orderedAdditionalRequest : orderedAdditionalRequestList){
            checkAllValidationForAddOrderedAdditional(orderedAdditionalRequest.getAdditionalId(), orderedAdditionalRequest.getOrderedAdditionalQuantity());
        }
    }

    @Override
    public void checkIsOnlyOneOrderedAdditionalByAdditionalIdAndRentalCarIdForUpdate(int additionalId, int rentalCarId) throws OrderedAdditionalAlreadyExistsException {
        if(this.orderedAdditionalDao.getAllByAdditional_AdditionalIdAndRentalCar_RentalCarId(additionalId, rentalCarId).size() > 1){
            throw new OrderedAdditionalAlreadyExistsException(BusinessMessages.OrderedAdditionalMessages.ORDERED_ADDITIONAL_ALREADY_EXISTS);
        }
    }

    @Override
    public void checkIsExistsByOrderedAdditionalId(int orderedAdditionalId) throws OrderedAdditionalNotFoundException {
        if(!this.orderedAdditionalDao.existsByOrderedAdditionalId(orderedAdditionalId)){
            throw new OrderedAdditionalNotFoundException(BusinessMessages.OrderedAdditionalMessages.ORDERED_ADDITIONAL_ID_NOT_FOUND + orderedAdditionalId);
        }
    }

    @Override
    public void checkIsNotExistsByOrderedAdditional_RentalCarId(int rentalCarId) throws RentalCarAlreadyExistsInOrderedAdditionalException {
        if(this.orderedAdditionalDao.existsByRentalCar_RentalCarId(rentalCarId)){
            throw new RentalCarAlreadyExistsInOrderedAdditionalException(BusinessMessages.OrderedAdditionalMessages.RENTAL_CAR_ID_ALREADY_EXISTS_IN_THE_ORDERED_ADDITIONAL_TABLE + rentalCarId);
        }
    }

    @Override
    public void checkIsNotExistsByOrderedAdditional_AdditionalId(int additionalId) throws AdditionalAlreadyExistsInOrderedAdditionalException {
        if(this.orderedAdditionalDao.existsByAdditional_AdditionalId(additionalId)){
            throw new AdditionalAlreadyExistsInOrderedAdditionalException(BusinessMessages.OrderedAdditionalMessages.ADDITIONAL_ID_ALREADY_EXISTS_IN_THE_ORDERED_ADDITIONAL_TABLE+ additionalId);
        }
    }

}
