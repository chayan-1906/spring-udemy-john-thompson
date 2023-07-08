package guru.springframework.spring6reactiveexamples.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author padmanabhadas
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private Integer id;

    private String firstName;

    private String lastName;
}
