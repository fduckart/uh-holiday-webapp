package edu.hawaii.its.holiday.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.hawaii.its.holiday.type.Action;

@Repository
public class ActionLoggerImpl implements ActionLogger {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    @Transactional(readOnly = true)
    public List<Action> findActions() {
        String qlString = "select a from Action a order by a.id";
        return em.createQuery(qlString, Action.class).getResultList();
    }
}