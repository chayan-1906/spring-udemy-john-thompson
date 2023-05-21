package guru.springframework.spring6restmvc.services;

import guru.springframework.spring6restmvc.models.BeerCSVRecord;

import java.io.File;
import java.util.List;

/**
 * @author padmanabhadas
 */

public interface IBeerCSVService {

    List<BeerCSVRecord> convertCSV(File csvFile);
}
