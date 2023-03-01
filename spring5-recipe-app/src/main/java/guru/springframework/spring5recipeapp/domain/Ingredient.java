package guru.springframework.spring5recipeapp.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@EqualsAndHashCode(exclude = {"recipe"})
@Entity
@NoArgsConstructor
@Getter
@Setter
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

	public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure) {
		this.description = description;
		this.amount = amount;
		this.unitOfMeasure = unitOfMeasure;
	}

	public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure, Recipe recipe) {
		this.description = description;
		this.amount = amount;
		this.unitOfMeasure = unitOfMeasure;
		this.recipe = recipe;
	}
}
