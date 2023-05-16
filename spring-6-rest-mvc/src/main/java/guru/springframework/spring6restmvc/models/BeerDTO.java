package guru.springframework.spring6restmvc.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDTO {

    private UUID id;

    @NotBlank
    @NotNull
    private String beerName;

    private Integer version;

    @NotNull
    private BeerStyle beerStyle;

    @NotBlank
    @NotNull
    private String upc;

    private Integer quantityOnHand;

    @NotNull
    private BigDecimal price;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
