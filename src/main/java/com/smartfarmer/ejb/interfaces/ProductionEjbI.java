package com.smartfarmer.ejb.interfaces;

import com.smartfarmer.util.ModelListWrapper;
import com.smartfarmer.entities.Production;

import java.util.Optional;

public interface ProductionEjbI {
    Production addProduction(Production production) throws Exception;
    Production editProduction(Production production);
    ModelListWrapper<Production> listProductions(Production filter, int start, int limit);
    void deleteProduction(Long id);
    Optional<Production> findById(Long id);
}
