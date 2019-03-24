package com.group.nuntius.obp.clientapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.group.nuntius.obp.domain.*;
import feign.Headers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@FeignClient(name="account", url="${obp.api.versionedUrl}")
public interface ObpApiClient {

    //tag::my-account[]
    @GetMapping(value = "my/accounts")
    List<Account> getPrivateAccountsNoDetails(@RequestHeader("Authorization") String token);

    default List<Account> getPrivateAccountsWithDetails(@RequestHeader("Authorization") String token) {
        List<Account> accountsNoDetails = getPrivateAccountsNoDetails(token);
        return accountsNoDetails.stream().map(account -> getAccount(token, account.getBankId(), account.getId())).collect(Collectors.toList());
    }

    @GetMapping(value = "my/banks/{bankId}/accounts/{accountId}/account")
    Account getAccount(@RequestHeader("Authorization") String token, @PathVariable("bankId") String bankId, @PathVariable("accountId") String accountId);

    @GetMapping(value = "banks/{bankId}/accounts/{accountId}/views")
    AccountViews getViewsForAccount(@RequestHeader("Authorization") String token, @PathVariable("bankId") String bankId, @PathVariable("accountId") String accountId);

    @GetMapping(value = "banks/{bankId}/accounts/{accountId}/owner/transactions")
    Transactions getTransactionsForAccount(@RequestHeader("Authorization") String token,
                                            @PathVariable("bankId") String bankId,
                                           @PathVariable("accountId") String accountId);

    @GetMapping(value = "banks/{bankId}/accounts/{accountId}/owner/transactions/{transactionId}/transaction")
    Transaction getTransactionById(@RequestHeader("Authorization") String token,
                                   @PathVariable("bankId") String bankId,
                                   @PathVariable("accountId") String accountId,
                                   @PathVariable("transactionId") String transactionId);

    @GetMapping(value = "banks/{bankId}/accounts/{accountId}/owner/transactions")
    String transferMoney(@RequestHeader("Authorization") String token,
                         @PathVariable("bankId") String bankId,
                         @PathVariable("accountId") String accountId,
                         @RequestBody TransactionRequest transfer);
    //end::my-account[]

    @GetMapping(value = "banks/{bankId}/accounts/{accountId}/owner/transaction-request-types")
    TransactionRequestTypes getTransactionTypes(@RequestHeader("Authorization") String token,
                                                @PathVariable("bankId") String bankId,
                                                @PathVariable("accountId") String accountId);

    @PostMapping(value = "banks/{bankId}/accounts/{accountId}/owner/transaction-request-types/{transactionReqType}/transaction-requests")
    String initiateTransaction(@RequestHeader("Authorization") String token,
                               @PathVariable("bankId") String bankId,
                               @PathVariable("accountId") String accountId,
                               @PathVariable("transactionReqType") String transactionReqType,
                               @RequestBody TransactionRequest txRequest);

    //tag::public-accounts[]
    @GetMapping(value = "accounts")
    List<Account> getAllPublicAccountsAtAllBanks(@RequestHeader("Authorization") String token);

    @PutMapping("/banks/{bankId}/accounts/{accountId}")
    Account createAccount(@RequestHeader("Authorization") String token,
                          @PathVariable("bankId") String bankId,
                          @PathVariable("accountId") String accountId,
                          @RequestBody Account accountRequest);

    @GetMapping("users/current")
    User getCurrentUser(@RequestHeader("Authorization") String token);

    //end::public-accounts[]

    //tag::tx-metadata[]
    @PostMapping(value = "banks/{bankId}/accounts/{accountId}/owner/transactions/{transactionId}/metadata/tags")
    Transaction.Tag tagTransaction(@RequestHeader("Authorization") String token,
                                   @PathVariable("bankId") String bankId,
                                   @PathVariable("accountId") String accountId,
                                   @PathVariable("transactionId") String transactionId,
                                   @RequestBody Transaction.Tag tag);

    @DeleteMapping(value = "banks/{bankId}/accounts/{accountId}/owner/transactions/{transactionId}/metadata/tags/{tagId}")
    void deleteTransactionTag(@RequestHeader("Authorization") String token,
                              @PathVariable("bankId") String bankId,
                              @PathVariable("accountId") String accountId,
                              @PathVariable("transactionId") String transactionId,
                              @PathVariable("tagId") String tagId);

    @PostMapping(value = "banks/{bankId}/accounts/{accountId}/owner/transactions/{transactionId}/metadata/where")
    void addLocation(@RequestHeader("Authorization") String token,
                     @PathVariable("bankId") String bankId,
                     @PathVariable("accountId") String accountId,
                     @PathVariable("transactionId") String transactionId,
                     @RequestBody Where location);

    @DeleteMapping(value = "banks/{bankId}/accounts/{accountId}/owner/transactions/{transactionId}/metadata/where")
    void deleteLocation(@RequestHeader("Authorization") String token,
                        @PathVariable("bankId") String bankId,
                        @PathVariable("accountId") String accountId,
                        @PathVariable("transactionId") String transactionId);
    //end::tx-metadata[]

    @Data
    class Transactions {
        private List<Transaction> transactions;

        public List<Transaction> getTransactions() {
            return transactions;
        }

        public void setTransactions(List<Transaction> transactions) {
            this.transactions = transactions;
        }
    }

    @Data
    @NoArgsConstructor @AllArgsConstructor
    class Where {
        @JsonProperty("where")
        private Location location;

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }
    }

    @Data
    class TransactionRequestTypes {
        @JsonProperty("transaction_request_types")
        private List<TransactionRequestType> transactionRequests;

        public List<TransactionRequestType> getTransactionRequests() {
            return transactionRequests;
        }

        public void setTransactionRequests(List<TransactionRequestType> transactionRequests) {
            this.transactionRequests = transactionRequests;
        }
    }

    @Data
    class AccountViews {
        private List<AccountView> views;

        public List<AccountView> getViews() {
            return views;
        }

        public void setViews(List<AccountView> views) {
            this.views = views;
        }
    }
}
