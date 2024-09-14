package vn.edu.iuh.fit.services;

import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.DTO.Response;
import vn.edu.iuh.fit.entities.Log;

import java.time.Instant;

public interface LogService {
    public Boolean existsBySession(String sessionNote);
    public void createLog(Log log);
}
