package com.group.nuntius.service;

import com.group.nuntius.Tools;
import com.group.nuntius.model.Institution;
import com.group.nuntius.model.Promotion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PromotionRepository extends CrudRepository<Promotion, Long> {

    List<Promotion> findByCode(String name);

    default Optional<Promotion> getByCode(String name) {
        return Tools.getFirst(findByCode(name));
    }

}
