/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControladorBD;

import ControladorBD.exceptions.IllegalOrphanException;
import ControladorBD.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Item;
import modelo.Fojamedicion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Renglonfoja;

/**
 *
 * @author alejo
 */
public class RenglonfojaJpaController implements Serializable {

    public RenglonfojaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_tpiPAII_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Renglonfoja renglonfoja) throws IllegalOrphanException {
        List<String> illegalOrphanMessages = null;
        Item VIdItemOrphanCheck = renglonfoja.getVIdItem();
        if (VIdItemOrphanCheck != null) {
            Renglonfoja oldRenglonfojaOfVIdItem = VIdItemOrphanCheck.getRenglonfoja();
            if (oldRenglonfojaOfVIdItem != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Item " + VIdItemOrphanCheck + " already has an item of type Renglonfoja whose VIdItem column cannot be null. Please make another selection for the VIdItem field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Item VIdItem = renglonfoja.getVIdItem();
            if (VIdItem != null) {
                VIdItem = em.getReference(VIdItem.getClass(), VIdItem.getVIdItem());
                renglonfoja.setVIdItem(VIdItem);
            }
            Fojamedicion VIdFoja = renglonfoja.getVIdFoja();
            if (VIdFoja != null) {
                VIdFoja = em.getReference(VIdFoja.getClass(), VIdFoja.getVIdFoja());
                renglonfoja.setVIdFoja(VIdFoja);
            }
            em.persist(renglonfoja);
            if (VIdItem != null) {
                VIdItem.setRenglonfoja(renglonfoja);
                VIdItem = em.merge(VIdItem);
            }
            if (VIdFoja != null) {
                VIdFoja.getRenglonfojaList().add(renglonfoja);
                VIdFoja = em.merge(VIdFoja);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Renglonfoja renglonfoja) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Renglonfoja persistentRenglonfoja = em.find(Renglonfoja.class, renglonfoja.getVIdRenglon());
            Item VIdItemOld = persistentRenglonfoja.getVIdItem();
            Item VIdItemNew = renglonfoja.getVIdItem();
            Fojamedicion VIdFojaOld = persistentRenglonfoja.getVIdFoja();
            Fojamedicion VIdFojaNew = renglonfoja.getVIdFoja();
            List<String> illegalOrphanMessages = null;
            if (VIdItemNew != null && !VIdItemNew.equals(VIdItemOld)) {
                Renglonfoja oldRenglonfojaOfVIdItem = VIdItemNew.getRenglonfoja();
                if (oldRenglonfojaOfVIdItem != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Item " + VIdItemNew + " already has an item of type Renglonfoja whose VIdItem column cannot be null. Please make another selection for the VIdItem field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (VIdItemNew != null) {
                VIdItemNew = em.getReference(VIdItemNew.getClass(), VIdItemNew.getVIdItem());
                renglonfoja.setVIdItem(VIdItemNew);
            }
            if (VIdFojaNew != null) {
                VIdFojaNew = em.getReference(VIdFojaNew.getClass(), VIdFojaNew.getVIdFoja());
                renglonfoja.setVIdFoja(VIdFojaNew);
            }
            renglonfoja = em.merge(renglonfoja);
            if (VIdItemOld != null && !VIdItemOld.equals(VIdItemNew)) {
                VIdItemOld.setRenglonfoja(null);
                VIdItemOld = em.merge(VIdItemOld);
            }
            if (VIdItemNew != null && !VIdItemNew.equals(VIdItemOld)) {
                VIdItemNew.setRenglonfoja(renglonfoja);
                VIdItemNew = em.merge(VIdItemNew);
            }
            if (VIdFojaOld != null && !VIdFojaOld.equals(VIdFojaNew)) {
                VIdFojaOld.getRenglonfojaList().remove(renglonfoja);
                VIdFojaOld = em.merge(VIdFojaOld);
            }
            if (VIdFojaNew != null && !VIdFojaNew.equals(VIdFojaOld)) {
                VIdFojaNew.getRenglonfojaList().add(renglonfoja);
                VIdFojaNew = em.merge(VIdFojaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = renglonfoja.getVIdRenglon();
                if (findRenglonfoja(id) == null) {
                    throw new NonexistentEntityException("The renglonfoja with id " + id + " no longer exists.");
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
            Renglonfoja renglonfoja;
            try {
                renglonfoja = em.getReference(Renglonfoja.class, id);
                renglonfoja.getVIdRenglon();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The renglonfoja with id " + id + " no longer exists.", enfe);
            }
            Item VIdItem = renglonfoja.getVIdItem();
            if (VIdItem != null) {
                VIdItem.setRenglonfoja(null);
                VIdItem = em.merge(VIdItem);
            }
            Fojamedicion VIdFoja = renglonfoja.getVIdFoja();
            if (VIdFoja != null) {
                VIdFoja.getRenglonfojaList().remove(renglonfoja);
                VIdFoja = em.merge(VIdFoja);
            }
            em.remove(renglonfoja);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Renglonfoja> findRenglonfojaEntities() {
        return findRenglonfojaEntities(true, -1, -1);
    }

    public List<Renglonfoja> findRenglonfojaEntities(int maxResults, int firstResult) {
        return findRenglonfojaEntities(false, maxResults, firstResult);
    }

    private List<Renglonfoja> findRenglonfojaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Renglonfoja.class));
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

    public Renglonfoja findRenglonfoja(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Renglonfoja.class, id);
        } finally {
            em.close();
        }
    }

    public int getRenglonfojaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Renglonfoja> rt = cq.from(Renglonfoja.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
