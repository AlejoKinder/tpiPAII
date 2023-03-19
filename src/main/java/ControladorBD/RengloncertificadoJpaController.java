/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControladorBD;

import ControladorBD.exceptions.NonexistentEntityException;
import ControladorBD.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Certificadopago;
import modelo.Rengloncertificado;

/**
 *
 * @author alejo
 */
public class RengloncertificadoJpaController implements Serializable {

    public RengloncertificadoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_tpiPAII_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rengloncertificado rengloncertificado) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Certificadopago VIdCertificado = rengloncertificado.getVIdCertificado();
            if (VIdCertificado != null) {
                VIdCertificado = em.getReference(VIdCertificado.getClass(), VIdCertificado.getVIdCertificado());
                rengloncertificado.setVIdCertificado(VIdCertificado);
            }
            em.persist(rengloncertificado);
            if (VIdCertificado != null) {
                VIdCertificado.getRengloncertificadoList().add(rengloncertificado);
                VIdCertificado = em.merge(VIdCertificado);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRengloncertificado(rengloncertificado.getVIdRenglon()) != null) {
                throw new PreexistingEntityException("Rengloncertificado " + rengloncertificado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rengloncertificado rengloncertificado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rengloncertificado persistentRengloncertificado = em.find(Rengloncertificado.class, rengloncertificado.getVIdRenglon());
            Certificadopago VIdCertificadoOld = persistentRengloncertificado.getVIdCertificado();
            Certificadopago VIdCertificadoNew = rengloncertificado.getVIdCertificado();
            if (VIdCertificadoNew != null) {
                VIdCertificadoNew = em.getReference(VIdCertificadoNew.getClass(), VIdCertificadoNew.getVIdCertificado());
                rengloncertificado.setVIdCertificado(VIdCertificadoNew);
            }
            rengloncertificado = em.merge(rengloncertificado);
            if (VIdCertificadoOld != null && !VIdCertificadoOld.equals(VIdCertificadoNew)) {
                VIdCertificadoOld.getRengloncertificadoList().remove(rengloncertificado);
                VIdCertificadoOld = em.merge(VIdCertificadoOld);
            }
            if (VIdCertificadoNew != null && !VIdCertificadoNew.equals(VIdCertificadoOld)) {
                VIdCertificadoNew.getRengloncertificadoList().add(rengloncertificado);
                VIdCertificadoNew = em.merge(VIdCertificadoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rengloncertificado.getVIdRenglon();
                if (findRengloncertificado(id) == null) {
                    throw new NonexistentEntityException("The rengloncertificado with id " + id + " no longer exists.");
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
            Rengloncertificado rengloncertificado;
            try {
                rengloncertificado = em.getReference(Rengloncertificado.class, id);
                rengloncertificado.getVIdRenglon();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rengloncertificado with id " + id + " no longer exists.", enfe);
            }
            Certificadopago VIdCertificado = rengloncertificado.getVIdCertificado();
            if (VIdCertificado != null) {
                VIdCertificado.getRengloncertificadoList().remove(rengloncertificado);
                VIdCertificado = em.merge(VIdCertificado);
            }
            em.remove(rengloncertificado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rengloncertificado> findRengloncertificadoEntities() {
        return findRengloncertificadoEntities(true, -1, -1);
    }

    public List<Rengloncertificado> findRengloncertificadoEntities(int maxResults, int firstResult) {
        return findRengloncertificadoEntities(false, maxResults, firstResult);
    }

    private List<Rengloncertificado> findRengloncertificadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rengloncertificado.class));
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

    public Rengloncertificado findRengloncertificado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rengloncertificado.class, id);
        } finally {
            em.close();
        }
    }

    public int getRengloncertificadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rengloncertificado> rt = cq.from(Rengloncertificado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
