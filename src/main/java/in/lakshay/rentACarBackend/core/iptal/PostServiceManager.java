package in.lakshay.rentACarBackend.core.iptal;

import org.springframework.stereotype.Service;

// this was supposed to be a service manager for payment processing
// but we ended up using the posServices instead
// keeping this around just in case we need it later
@Service
public class PostServiceManager {

// old implementation - left here for reference
//    PostOutService postOutService;
//
//    @Autowired
//    public PostServiceManager(PostOutService postOutService) {
//        this.postOutService = postOutService;
//    }
//
//    public void pay(String cardNumber, String cardOwner, String cardCvv, String cardExpirationDate, double totalPrice) throws BusinessException {
//        if(!this.postOutService.payment(cardNumber,cardOwner,cardCvv,cardExpirationDate,totalPrice)){
//            throw new BusinessException("payment failed");
//        }
//    }

    // todo: either implement this properly or delete it completly

    // might be useful if we switch payment providers again..who knows

}
