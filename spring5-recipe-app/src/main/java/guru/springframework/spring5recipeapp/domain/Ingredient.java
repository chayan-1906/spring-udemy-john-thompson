package guru.springframework.spring5recipeapp.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
//@AllArgsConstructor
@NoArgsConstructor
//@Getter
//@Setter
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	private BigDecimal amount;

	@ManyToOne
	private Recipe recipe;

	@OneToOne(fetch = FetchType.EAGER)
	private UnitOfMeasure unitOfMeasure;

	public Ingredient(String description, BigDecimal amount, Recipe recipe, UnitOfMeasure unitOfMeasure) {
		this.description = description;
		this.amount = amount;
		this.recipe = recipe;
		this.unitOfMeasure = unitOfMeasure;
	}
}
