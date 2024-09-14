package vn.edu.iuh.fit.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.DTO.AccountGrantDTO;

import java.util.Collections;
import java.util.List;


public class AccountRepository {
    @PersistenceContext
    private EntityManager em;

    public AccountRepository(EntityManager em) {
        this.em = em;
    }

    public AccountGrantDTO findByAccountId(String accountId) {
        try {
            String jpql = "SELECT a, g.role, g.isGrant " +
                    "FROM Account a LEFT JOIN a.grantAccesses g " +
                    "WHERE a.accountId = :accountId";
            TypedQuery<AccountGrantDTO> query = em.createQuery(jpql, AccountGrantDTO.class);
            query.setParameter("accountId", accountId);

            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<AccountGrantDTO> findByRoleId(String roleId) {
        try {
            String jpql = "SELECT new vn.edu.iuh.fit.entities.DTO.AccountGrantDTO(a, g.role, g.isGrant) " +
                    "FROM Account a LEFT JOIN a.grantAccesses g " +
                    "WHERE g.role.roleId = :roleId";
            TypedQuery<AccountGrantDTO> query = em.createQuery(jpql, AccountGrantDTO.class);
            query.setParameter("roleId", roleId);

            return query.getResultList();
        } catch (NoResultException e) {
            return Collections.emptyList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void save(Account account) {
        em.persist(account);
    }
}
