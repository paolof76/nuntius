package com.group.nuntius.service;

import com.group.nuntius.model.Account;
import com.group.nuntius.model.Institution;
import com.group.nuntius.model.Promotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/promotion")
public class PromotionRestController {

    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private InstitutionRepository institutionRepository;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Promotion info(@RequestParam("id") Long id) {
        return promotionRepository.findById(id).orElse(null);
    }

}
