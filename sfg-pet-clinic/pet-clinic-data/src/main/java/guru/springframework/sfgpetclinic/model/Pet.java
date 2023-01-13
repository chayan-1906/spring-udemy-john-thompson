package guru.springframework.sfgpetclinic.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
	public Set<Visit> visits = new HashSet<> ( );

	@Column(name = "name")
	private String name;
	@ManyToOne
	@JoinColumn(name = "type_id")

	private PetType petType;
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Owner owner;

	@Column(name = "birth_date")
	private LocalDate birthDate;
}
