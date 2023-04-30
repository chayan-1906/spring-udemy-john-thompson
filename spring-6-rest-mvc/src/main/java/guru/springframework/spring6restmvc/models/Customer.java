package guru.springframework.spring6restmvc.models;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private UUID id;

    private String customerName;

    private Integer version;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}