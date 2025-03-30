package in.lakshay.rentACarBackend.business.requests.carCrashRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCarCrashRequest {

    @NotNull
    @Min(1)
    private int carCrashId;

}
