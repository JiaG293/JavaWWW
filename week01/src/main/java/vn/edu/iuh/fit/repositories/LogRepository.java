package vn.edu.iuh.fit.repositories;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import vn.edu.iuh.fit.entities.Log;

import java.time.Instant;

public class LogRepository {
    @PersistenceContext
    private EntityManager em;

    public LogRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void createLog(Log log) {
        try {
            em.getTransaction().begin();
            em.persist(log);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public static boolean existsBySession(String sessionNote, EntityManager em) {
        try {
            TypedQuery<Boolean> query = em.createNamedQuery("Log.existsByNotes", Boolean.class);
            query.setParameter("notes", sessionNote);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Log findBySessionId(String sessionNote) {
        try {
            TypedQuery<Log> query = em.createNamedQuery("Log.findByNotes", Log.class);
            query.setParameter("notes", sessionNote);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public boolean updateLog(String sessionNote, Instant logoutTime) {
        boolean result = false;
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Log log = findBySessionId(sessionNote);
            if (log != null) {
                log.setLogoutTime(logoutTime);
                em.merge(log);
                result = true;
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }

}
