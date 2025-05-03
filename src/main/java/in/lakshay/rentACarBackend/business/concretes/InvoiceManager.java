package in.lakshay.rentACarBackend.business.concretes;

// sooo many imports for invoice stuff
import in.lakshay.rentACarBackend.business.abstracts.*;
import in.lakshay.rentACarBackend.business.constants.messaaages.BusinessMessages;

import in.lakshay.rentACarBackend.business.dtos.invoiceDtos.gets.*;
import in.lakshay.rentACarBackend.business.dtos.invoiceDtos.lists.InvoiceListDto;
import in.lakshay.rentACarBackend.business.requests.invoiceRequests.CreateInvoiceRequest;
import in.lakshay.rentACarBackend.business.requests.invoiceRequests.InvoiceGetDateBetweenRequest;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.BusinessException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.additionalExceptions.AdditionalNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.corporateCustomerExceptions.CorporateCustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.customerExceptions.CustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.individualCustomerExceptions.IndividualCustomerNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.invoiceExceptions.CustomerNotFoundInInvoiceException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.invoiceExceptions.InvoiceNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.invoiceExceptions.PaymentNotFoundInInvoiceException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.invoiceExceptions.RentalCarNotFoundInInvoiceException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.paymentExceptions.PaymentNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.exceptions.businessExceptions.rentalCarExceptions.RentalCarNotFoundException;
import in.lakshay.rentACarBackend.core.utilities.generate.GenerateRandomCode;
import in.lakshay.rentACarBackend.core.utilities.mapping.ModelMapperService;
import in.lakshay.rentACarBackend.core.utilities.result.DataResult;
import in.lakshay.rentACarBackend.core.utilities.result.Result;
import in.lakshay.rentACarBackend.core.utilities.result.SuccessDataResult;
import in.lakshay.rentACarBackend.core.utilities.result.SuccessResult;
import in.lakshay.rentACarBackend.dataAccess.abstracts.InvoiceDao;
import in.lakshay.rentACarBackend.entities.concretes.CorporateCustomer;
import in.lakshay.rentACarBackend.entities.concretes.IndividualCustomer;
import in.lakshay.rentACarBackend.entities.concretes.Invoice;
import in.lakshay.rentACarBackend.entities.concretes.RentalCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// handles all invoice operations - creation, lookup, etc
@Service
public class InvoiceManager implements InvoiceService {

    private final InvoiceDao invoiceDao;  // db access
    private final CarService carService;   // need this for pricing
    private final CustomerService customerService;
    private final IndividualCustomerService individualCustomerService;
    private final CorporateCustomerService corporateCustomerService;
    private final RentalCarService rentalCarService;  // rental info
    private final OrderedAdditionalService orderedAdditionalService; // extras
    private final PaymentService paymentService;  // payment tracking
    private final ModelMapperService modelMapperService;  // dto mapping

    // wow thats a lot of dependencies! maybe too many?
    @Autowired
    public InvoiceManager(InvoiceDao invoiceDao, CarService carService, CustomerService customerService,
                          @Lazy IndividualCustomerService individualCustomerService, @Lazy CorporateCustomerService corporateCustomerService,
                          RentalCarService rentalCarService, ModelMapperService modelMapperService, @Lazy OrderedAdditionalService orderedAdditionalService, PaymentService paymentService) {
        this.invoiceDao = invoiceDao;
        this.carService = carService;
        this.customerService = customerService;
        this.individualCustomerService = individualCustomerService;
        this.corporateCustomerService = corporateCustomerService;
        this.rentalCarService = rentalCarService;
        this.orderedAdditionalService = orderedAdditionalService;
        this.modelMapperService = modelMapperService;
        this.paymentService = paymentService;
    }

    // get all invoices - prob not a good idea in prod with lots of data
    @Override
    public DataResult<List<InvoiceListDto>> getAll() {

        List<Invoice> invoices = this.invoiceDao.findAll();  // grab everything

        // map to dtos using streams - java 8 ftw
        List<InvoiceListDto> result = invoices.stream()
            .map(invoice -> this.modelMapperService.forDto().map(invoice, InvoiceListDto.class))
            .collect(Collectors.toList());
        manuelFieldSetter(invoices, result);  // fix ids that mapper misses

        return new SuccessDataResult<>(result, BusinessMessages.GlobalMessages.DATA_LISTED_SUCCESSFULLY);
    }

    // create a new invoice - transactional to ensure atomicity
    @Override
    @Transactional(rollbackFor = BusinessException.class) // rollback on biz exceptions
    public Result add(CreateInvoiceRequest createInvoiceRequest) throws RentalCarNotFoundException {

        this.rentalCarService.checkIsExistsByRentalCarId(createInvoiceRequest.getRentalCarId()); // validate rental exists
        createInvoiceRequest.setInvoiceNo(generateCode()); // generate unique invoice number

        // map request to entity
        Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
        invoice.setInvoiceId(0); // ensure new record
        invoice.setCustomer(this.customerService.getCustomerById(createInvoiceRequest.getCustomerId()));

        this.invoiceDao.save(invoice); // persist to db

        return new SuccessResult(BusinessMessages.GlobalMessages.DATA_ADDED_SUCCESSFULLY);
    }

    // get invoice details for individual customer by invoice id
    @Override
    public DataResult<GetIndividualCustomerInvoiceByInvoiceIdDto> getIndividualCustomerInvoiceByInvoiceId(int invoiceId) throws IndividualCustomerNotFoundException, InvoiceNotFoundException {

        checkIfInvoiceIdExists(invoiceId); // validate invoice exists
        Invoice invoice = this.invoiceDao.getById(invoiceId);

        // make sure its actually an individual customer
        this.individualCustomerService.checkIfIndividualCustomerIdExists(invoice.getRentalCar().getCustomer().getCustomerId());

        // map to dto
        GetIndividualCustomerInvoiceByInvoiceIdDto result = this.modelMapperService.forDto().map(invoice, GetIndividualCustomerInvoiceByInvoiceIdDto.class);

        // add customer details - mapper doesnt handle this right
        IndividualCustomer individualCustomer = this.individualCustomerService.getIndividualCustomerById(invoice.getCustomer().getCustomerId());
        result.setFirstName(individualCustomer.getFirstName());
        result.setLastName(individualCustomer.getLastName());
        result.setNationalIdentity(individualCustomer.getNationalIdentity());
        manuelFieldSetter(invoice, result); // fix other fields

        return new SuccessDataResult<>(result, BusinessMessages.InvoiceMessages.INDIVIDUAL_CUSTOMER_INVOICE_LISTED_BY_INVOICE_ID + invoiceId);
    }

    @Override
    public DataResult<GetCorporateCustomerInvoiceByInvoiceIdDto> getCorporateCustomerInvoiceByInvoiceId(int invoiceId) throws CorporateCustomerNotFoundException, InvoiceNotFoundException {

        checkIfInvoiceIdExists(invoiceId);

        Invoice invoice = this.invoiceDao.getById(invoiceId);
        this.corporateCustomerService.checkIfCorporateCustomerIdExists(invoice.getRentalCar().getCustomer().getCustomerId());

        GetCorporateCustomerInvoiceByInvoiceIdDto result = this.modelMapperService.forDto().map(invoice, GetCorporateCustomerInvoiceByInvoiceIdDto.class);
        CorporateCustomer corporateCustomer = this.corporateCustomerService.getCorporateCustomerById(invoice.getCustomer().getCustomerId());
        result.setCompanyName(corporateCustomer.getCompanyName());
        result.setTaxNumber(corporateCustomer.getTaxNumber());
        manuelFieldSetter(invoice, result);

        return new SuccessDataResult<>(result, BusinessMessages.InvoiceMessages.CORPORATE_CUSTOMER_INVOICE_LISTED_BY_INVOICE_ID + invoiceId);
    }

    @Override
    public DataResult<GetIndividualCustomerInvoiceByInvoiceNoDto> getIndividualCustomerInvoiceByInvoiceNo(String invoiceNo) throws IndividualCustomerNotFoundException, InvoiceNotFoundException {

        checkIfInvoiceNoExists(invoiceNo);

        Invoice invoice = this.invoiceDao.getInvoiceByInvoiceNo(invoiceNo);
        this.individualCustomerService.checkIfIndividualCustomerIdExists(invoice.getRentalCar().getCustomer().getCustomerId());

        GetIndividualCustomerInvoiceByInvoiceNoDto result = this.modelMapperService.forDto().map(invoice, GetIndividualCustomerInvoiceByInvoiceNoDto.class);
        IndividualCustomer individualCustomer = this.individualCustomerService.getIndividualCustomerById(invoice.getCustomer().getCustomerId());
        result.setFirstName(individualCustomer.getFirstName());
        result.setLastName(individualCustomer.getLastName());
        result.setNationalIdentity(individualCustomer.getNationalIdentity());
        manuelFieldSetter(invoice, result);

        return new SuccessDataResult<>(result, BusinessMessages.InvoiceMessages.INDIVIDUAL_CUSTOMER_INVOICE_LISTED_BY_INVOICE_NO + invoiceNo);
    }

    @Override
    public DataResult<GetCorporateCustomerInvoiceByInvoiceNoDto> getCorporateCustomerInvoiceByInvoiceNo(String invoiceNo) throws CorporateCustomerNotFoundException, InvoiceNotFoundException {

        checkIfInvoiceNoExists(invoiceNo);

        Invoice invoice = this.invoiceDao.getInvoiceByInvoiceNo(invoiceNo);

        this.corporateCustomerService.checkIfCorporateCustomerIdExists(invoice.getRentalCar().getCustomer().getCustomerId());

        GetCorporateCustomerInvoiceByInvoiceNoDto result = this.modelMapperService.forDto().map(invoice, GetCorporateCustomerInvoiceByInvoiceNoDto.class);
        CorporateCustomer corporateCustomer = this.corporateCustomerService.getCorporateCustomerById(invoice.getCustomer().getCustomerId());
        result.setCompanyName(corporateCustomer.getCompanyName());
        result.setTaxNumber(corporateCustomer.getTaxNumber());
        manuelFieldSetter(invoice, result);

        return new SuccessDataResult<>(result, BusinessMessages.InvoiceMessages.CORPORATE_CUSTOMER_INVOICE_LISTED_BY_INVOICE_NO + invoiceNo);

    }

    @Override
    public DataResult<GetInvoiceDto> getInvoiceByPayment_PaymentId(int paymentId) throws PaymentNotFoundInInvoiceException, PaymentNotFoundException {

        this.paymentService.checkIfExistsByPaymentId(paymentId);
        checkIfExistsByPaymentId(paymentId);

        Invoice invoice = this.invoiceDao.getInvoiceByPayment_PaymentId(paymentId);

        GetInvoiceDto result = this.modelMapperService.forDto().map(invoice, GetInvoiceDto.class);
        manuelFieldSetter(invoice, result);

        return new SuccessDataResult<>(result, BusinessMessages.InvoiceMessages.INVOICE_LISTED_BY_PAYMENT_ID + paymentId);
    }

    @Override
    public DataResult<List<InvoiceListDto>> getAllByRentalCar_RentalCarId(int rentalCarId) throws RentalCarNotFoundException {

        this.rentalCarService.checkIsExistsByRentalCarId(rentalCarId);

        List<Invoice> invoiceList = this.invoiceDao.getAllByRentalCar_RentalCarId(rentalCarId);

        List<InvoiceListDto> result = invoiceList.stream().map(invoice -> this.modelMapperService.forDto().map(invoice, InvoiceListDto.class)).collect(Collectors.toList());
        manuelFieldSetter(invoiceList, result);

        return new SuccessDataResult<>(result, BusinessMessages.InvoiceMessages.INVOICE_LISTED_BY_RENTAL_ID + rentalCarId);
    }

    @Override
    public DataResult<List<InvoiceListDto>> getAllByCustomer_CustomerId(int customerId) throws CustomerNotFoundException {

        this.customerService.checkIfCustomerIdExists(customerId);

        List<Invoice> invoiceList = this.invoiceDao.getAllByCustomer_CustomerId(customerId);

        List<InvoiceListDto> result = invoiceList.stream().map(invoice -> this.modelMapperService.forDto().map(invoice, InvoiceListDto.class)).collect(Collectors.toList());
        manuelFieldSetter(invoiceList, result);

        return new SuccessDataResult<>(result, BusinessMessages.InvoiceMessages.INVOICE_LISTED_BY_CUSTOMER_ID + customerId);
    }

    @Override
    public DataResult<List<InvoiceListDto>> findByInvoiceDateBetween(InvoiceGetDateBetweenRequest invoiceGetDateBetweenRequest) {

        List<Invoice> invoices = this.invoiceDao.getByCreationDateBetween(invoiceGetDateBetweenRequest.getStartDate(), invoiceGetDateBetweenRequest.getEndDate());

        List<InvoiceListDto> result = invoices.stream().map(invoice -> this.modelMapperService.forDto().map(invoice, InvoiceListDto.class)).collect(Collectors.toList());
        manuelFieldSetter(invoices, result);

        return new SuccessDataResult<>(result, BusinessMessages.InvoiceMessages.INVOICE_LISTED_BY_BETWEEN_DATE);
    }

    // helper to fix fields that mapper misses - typo in name but too lazy to fix everywhere
    private void manuelFieldSetter(Invoice invoice, GetInvoiceDto result) {
        // manually set ids that mapper doesnt handle right
        result.setRentalCarId(invoice.getRentalCar().getRentalCarId());
        result.setCustomerId(invoice.getRentalCar().getCustomer().getCustomerId());
    }

    // same but for lists - wish java had better generics sometimes
    private void manuelFieldSetter(List<Invoice> invoices, List<InvoiceListDto> invoiceListDtoList){
        // old school for loop cuz we need indexes
        for(int i=0; i<invoices.size();i++){
            invoiceListDtoList.get(i).setRentalCarId(invoices.get(i).getRentalCar().getRentalCarId());
            invoiceListDtoList.get(i).setCustomerId(invoices.get(i).getRentalCar().getCustomer().getCustomerId());
        }
    }

    // convenience method to create invoice from rental and payment ids
    // does all the price calculations too
    @Override
    public void createAndAddInvoice(int rentalCarId, int paymentId) throws AdditionalNotFoundException, RentalCarNotFoundException {

        // get rental details
        RentalCar rentalCar = this.rentalCarService.getById(rentalCarId);

        // calculate all the price components
        int totalDays = this.rentalCarService.getTotalDaysForRental(rentalCar.getStartDate(), rentalCar.getFinishDate());
        double priceOfDays = this.rentalCarService.calculateRentalCarTotalDayPrice(rentalCar.getStartDate(),
                rentalCar.getFinishDate(), this.carService.getDailyPriceByCarId(rentalCar.getCar().getCarId()));
        double priceOfDiffCity = this.rentalCarService.calculateCarDeliveredToTheSamCity(
                rentalCar.getRentedCity().getCityId(), rentalCar.getDeliveredCity().getCityId());
        double priceOfAdditionals = this.orderedAdditionalService.getPriceCalculatorForOrderedAdditionalListByRentalCarId(
                rentalCar.getRentalCarId(), totalDays);

        // sum it all up
        double totalPrice = priceOfDays + priceOfDiffCity + priceOfAdditionals;

        // build the request obj
        CreateInvoiceRequest createInvoiceRequest = new CreateInvoiceRequest();
        createInvoiceRequest.setStartDate(rentalCar.getStartDate());
        createInvoiceRequest.setFinishDate(rentalCar.getFinishDate());
        createInvoiceRequest.setTotalRentalDay((short) totalDays);
        createInvoiceRequest.setPriceOfDays(priceOfDays);
        createInvoiceRequest.setPriceOfDiffCity(priceOfDiffCity);
        createInvoiceRequest.setPriceOfAdditionals(priceOfAdditionals);
        createInvoiceRequest.setRentalCarTotalPrice(totalPrice); // total
        createInvoiceRequest.setRentalCarId(rentalCarId);
        createInvoiceRequest.setCustomerId(rentalCar.getCustomer().getCustomerId());
        createInvoiceRequest.setPaymentId(paymentId);

        // create it!
        add(createInvoiceRequest);
    }

    // generate a unique invoice number
    private String generateCode() {
        while (true){ // keep trying till we get a unique one
            String code = GenerateRandomCode.generate(); // get random code
            if(!this.invoiceDao.existsByInvoiceNo(code)){
                return code; // found a unique one!
            }
            // if we get here, code already exists, try again
        }
    }

    // validation helpers below

    // check if invoice exists by id
    private void checkIfInvoiceIdExists(int invoiceId) throws InvoiceNotFoundException {
        if(!this.invoiceDao.existsByInvoiceId(invoiceId)){
            throw new InvoiceNotFoundException(BusinessMessages.InvoiceMessages.INVOICE_ID_NOT_FOUND + invoiceId);
        }
    }

    // check if invoice exists by invoice number
    private void checkIfInvoiceNoExists(String invoiceNo) throws InvoiceNotFoundException {
        if(!this.invoiceDao.existsByInvoiceNo(invoiceNo)){
            throw new InvoiceNotFoundException(BusinessMessages.InvoiceMessages.INVOICE_NO_NOT_FOUND + invoiceNo);
        }
    }

    // check if payment has an invoice
    private void checkIfExistsByPaymentId(int paymentId) throws PaymentNotFoundInInvoiceException {
        if(!this.invoiceDao.existsByPayment_PaymentId(paymentId)){
            throw new PaymentNotFoundInInvoiceException(BusinessMessages.InvoiceMessages.PAYMENT_ID_NOT_FOUND_IN_THE_INVOICE_TABLE + paymentId);
        }
    }

    @Override
    public void checkIfNotExistsByCustomer_CustomerId(int customerId) throws CustomerNotFoundInInvoiceException {
        if(this.invoiceDao.existsByCustomer_CustomerId(customerId)){
            throw new CustomerNotFoundInInvoiceException(BusinessMessages.InvoiceMessages.CUSTOMER_ID_ALREADY_EXISTS_IN_THE_INVOICE_TABLE + customerId);
        }
    }

    @Override
    public void checkIfNotExistsByRentalCar_RentalCarId(int rentalCarId) throws RentalCarNotFoundInInvoiceException {
        if(this.invoiceDao.existsByRentalCar_RentalCarId(rentalCarId)){
            throw new RentalCarNotFoundInInvoiceException(BusinessMessages.InvoiceMessages.RENTAL_CAR_ID_ALREADY_EXISTS_IN_THE_INVOICE_TABLE + rentalCarId);
        }
    }
}
