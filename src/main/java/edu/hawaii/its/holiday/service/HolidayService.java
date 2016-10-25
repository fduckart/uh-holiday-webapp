package edu.hawaii.its.holiday.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hawaii.its.holiday.type.Holiday;
import edu.hawaii.its.holiday.type.HolidayType;
import edu.hawaii.its.holiday.type.HolidayTypeMapping;
import edu.hawaii.its.holiday.type.UserRole;

@Service("holidayService")
@Repository
public class HolidayService {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Holiday> findHolidays() {
        String qlString = "select a from Holiday a "
                + "order by a.observedDate, a.id, a.version";
        return em.createQuery(qlString).getResultList();
    }

    public Holiday findHoliday(Integer id) {
        return em.find(Holiday.class, id);
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<HolidayType> findHolidayTypes() {
        String qlString = "select a from HolidayType a "
                + "order by a.id, a.version";
        return em.createQuery(qlString).getResultList();
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<HolidayTypeMapping> findHolidayTypeMappings() {
        String qlString = "select a from HolidayTypeMapping a "
                + "order by a.typeId, a.holidayId";
        return em.createQuery(qlString).getResultList();
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<UserRole> findUserRoles() {
        String qlString = "select a from UserRole a "
                + "order by a.id, a.version";
        return em.createQuery(qlString).getResultList();
    }

}
