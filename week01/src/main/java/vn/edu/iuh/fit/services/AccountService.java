package vn.edu.iuh.fit.services;

import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.DTO.AccountGrantDTO;
import vn.edu.iuh.fit.entities.DTO.Response;
import vn.edu.iuh.fit.entities.Log;
import vn.edu.iuh.fit.repositories.AccountRepository;
import vn.edu.iuh.fit.repositories.LogRepository;

import java.time.Instant;
import java.util.List;

public class AccountService {
    private final AccountRepository accountRepository;
    private final LogRepository logRepository;

    public AccountService(AccountRepository accountRepository, LogRepository logRepository) {
        this.accountRepository = accountRepository;
        this.logRepository = logRepository;
    }

    public Response<AccountGrantDTO> loginAuthentication(String username, String password, String sessionId) {
        try {
            AccountGrantDTO account = accountRepository.findByAccountId(username);
            if (account == null) {
                return new Response<>(401, "Account not found", null);
            } else if (account.getAccount().getPassword().equals(password)) {
                Log log = new Log(username, Instant.now(), null, sessionId);

                logRepository.createLog(log);
                return new Response<>(200, "Authentication successful", account);
            } else {
                return new Response<>(401, "Invalid password", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response<>(500, "Internal server error", null);
        }
    }

    public boolean logout(String sessionId) {
        boolean result = false;
        try {
            boolean check = logRepository.updateLog(sessionId, Instant.now());
            if (check) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Response<Account> loginAuthentication(String username, String password) {
        return null;
    }

    public AccountGrantDTO getAccount(String username) {
        return accountRepository.findByAccountId(username);
    }

    public List<AccountGrantDTO> getListAccountOfRole(String roleId) {
        return accountRepository.findByRoleId(roleId);
    }

}
