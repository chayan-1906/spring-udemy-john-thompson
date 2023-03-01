package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.repositories.IUnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements IUnitOfMeasureService {

	private final IUnitOfMeasureRepository unitOfMeasureRepository;

	private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

	public UnitOfMeasureServiceImpl(IUnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
	}

	/**
	 * @return
	 */
	@Override
	public Set<UnitOfMeasureCommand> listAllUoms() {
		return StreamSupport.stream ( unitOfMeasureRepository.findAll ( )
						.spliterator ( ), false )
				.map ( unitOfMeasureToUnitOfMeasureCommand::convert )
				.collect ( Collectors.toSet ( ) );
	}
}
