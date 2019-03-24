package com.group.nuntius.service;

import com.group.nuntius.model.Promotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promotion")
@CrossOrigin(origins = "http://localhost:4200")
public class PromotionRestController {

    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private InstitutionRepository institutionRepository;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Promotion info(@RequestParam("code") String code) {
        return promotionRepository.getByCode(code).orElse(null);
    }

}
