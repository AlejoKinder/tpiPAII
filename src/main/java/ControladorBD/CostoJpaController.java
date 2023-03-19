/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControladorBD;

import ControladorBD.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Costo;
import modelo.Item;

/**
 *
 * @author alejo
 */
public class CostoJpaController implements Serializable {

    public CostoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_tpiPAII_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Costo costo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Item VIdItem = costo.getVIdItem();
            if (VIdItem != null) {
                VIdItem = em.getReference(VIdItem.getClass(), VIdItem.getVIdItem());
                costo.setVIdItem(VIdItem);
            }
            em.persist(costo);
            if (VIdItem != null) {
                VIdItem.getCostoList().add(costo);
                VIdItem = em.merge(VIdItem);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Costo costo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Costo persistentCosto = em.find(Costo.class, costo.getVIdCosto());
            Item VIdItemOld = persistentCosto.getVIdItem();
            Item VIdItemNew = costo.getVIdItem();
            if (VIdItemNew != null) {
                VIdItemNew = em.getReference(VIdItemNew.getClass(), VIdItemNew.getVIdItem());
                costo.setVIdItem(VIdItemNew);
            }
            costo = em.merge(costo);
            if (VIdItemOld != null && !VIdItemOld.equals(VIdItemNew)) {
                VIdItemOld.getCostoList().remove(costo);
                VIdItemOld = em.merge(VIdItemOld);
            }
            if (VIdItemNew != null && !VIdItemNew.equals(VIdItemOld)) {
                VIdItemNew.getCostoList().add(costo);
                VIdItemNew = em.merge(VIdItemNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = costo.getVIdCosto();
                if (findCosto(id) == null) {
                    throw new NonexistentEntityException("The costo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Costo costo;
            try {
                costo = em.getReference(Costo.class, id);
                costo.getVIdCosto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The costo with id " + id + " no longer exists.", enfe);
            }
            Item VIdItem = costo.getVIdItem();
            if (VIdItem != null) {
                VIdItem.getCostoList().remove(costo);
                VIdItem = em.merge(VIdItem);
            }
            em.remove(costo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Costo> findCostoEntities() {
        return findCostoEntities(true, -1, -1);
    }

    public List<Costo> findCostoEntities(int maxResults, int firstResult) {
        return findCostoEntities(false, maxResults, firstResult);
    }

    private List<Costo> findCostoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Costo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Costo findCosto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Costo.class, id);
        } finally {
            em.close();
        }
    }

    public int getCostoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Costo> rt = cq.from(Costo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
