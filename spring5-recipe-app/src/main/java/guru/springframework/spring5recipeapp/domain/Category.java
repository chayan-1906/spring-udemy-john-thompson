package guru.springframework.spring5recipeapp.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;

	@ManyToMany(mappedBy = "categories")
	private Set<Recipe> recipes;

}
