package com.group.nuntius.obp.clientapi;

import com.group.nuntius.obp.domain.ATM;
import com.group.nuntius.obp.domain.Bank;
import com.group.nuntius.obp.domain.Branch;
import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="bank", url="${obp.api.versionedUrl}")
public interface ObpBankMetaApiClient {

    @GetMapping(value = "banks", consumes = MediaType.APPLICATION_JSON_VALUE)
    Banks getBanks(@RequestHeader("Authorization") String token);

    @RequestMapping(method = RequestMethod.GET, value = "banks/{bankId}/branches")
    Branches getBranches(@RequestHeader("Authorization") String token,
                         @PathVariable("bankId") String bankId);

    @RequestMapping(method = RequestMethod.GET, value = "banks/{bankId}/branches/{branchId}")
    Branch getBranch(@RequestHeader("Authorization") String token,
                     @PathVariable("bankId") String bankId,
                     @PathVariable("branchId") String branchId);

    @RequestMapping(method = RequestMethod.GET, value = "banks/{bankId}/atms")
    ATMs getAtms(@RequestHeader("Authorization") String token,
                 @PathVariable("bankId") String bankId);

    @RequestMapping(method = RequestMethod.GET, value = "banks/{bankId}/branches/{branchId}/atms/{atmId}")
    Branch getAtm(@RequestHeader("Authorization") String token,
                  @PathVariable("bankId") String bankId,
                  @PathVariable("branchId") String branchId,
                  @PathVariable("atmId") String atmId);

    @Data
    class Banks {
        private List<Bank> banks;

        public List<Bank> getBanks() {
            return banks;
        }

        public void setBanks(List<Bank> banks) {
            this.banks = banks;
        }
    }

    @Data
    class ATMs {
        private List<ATM> atms;

        public List<ATM> getAtms() {
            return atms;
        }

        public void setAtms(List<ATM> atms) {
            this.atms = atms;
        }
    }

    @Data
    class Branches {
        private List<Branch> branches;

        public List<Branch> getBranches() {
            return branches;
        }

        public void setBranches(List<Branch> branches) {
            this.branches = branches;
        }
    }

}
