package com.group.nuntius.service;

import com.group.nuntius.model.Account;
import com.group.nuntius.model.Institution;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface InstitutionRepository extends CrudRepository<Institution, Long> {

    List<Institution> findByName(String name);

    default Optional<Institution> getByName(String name) {
        List<Institution> institutions = findByName(name);
        return institutions.isEmpty() ? Optional.empty() : Optional.ofNullable(institutions.get(0));
    }

    List<Institution> findByAbbreviation(String abbreviation);

    default Optional<Institution> getByAbbreviation(String abbreviation) {
        List<Institution> institutions = findByAbbreviation(abbreviation);
        return institutions.isEmpty() ? Optional.empty() : Optional.ofNullable(institutions.get(0));
    }
}
