/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControladorBD;

import ControladorBD.exceptions.IllegalOrphanException;
import ControladorBD.exceptions.NonexistentEntityException;
import ControladorBD.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Obra;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import modelo.Financiacion;

/**
 *
 * @author alejo
 */
public class FinanciacionJpaController implements Serializable {

    public FinanciacionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_tpiPAII_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Financiacion financiacion) throws PreexistingEntityException, Exception {
        if (financiacion.getObraList() == null) {
            financiacion.setObraList(new ArrayList<Obra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Obra> attachedObraList = new ArrayList<Obra>();
            for (Obra obraListObraToAttach : financiacion.getObraList()) {
                obraListObraToAttach = em.getReference(obraListObraToAttach.getClass(), obraListObraToAttach.getVIdObra());
                attachedObraList.add(obraListObraToAttach);
            }
            financiacion.setObraList(attachedObraList);
            em.persist(financiacion);
            for (Obra obraListObra : financiacion.getObraList()) {
                Financiacion oldVFinanciacionOfObraListObra = obraListObra.getVFinanciacion();
                obraListObra.setVFinanciacion(financiacion);
                obraListObra = em.merge(obraListObra);
                if (oldVFinanciacionOfObraListObra != null) {
                    oldVFinanciacionOfObraListObra.getObraList().remove(obraListObra);
                    oldVFinanciacionOfObraListObra = em.merge(oldVFinanciacionOfObraListObra);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFinanciacion(financiacion.getVIdFinanciacion()) != null) {
                throw new PreexistingEntityException("Financiacion " + financiacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Financiacion financiacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Financiacion persistentFinanciacion = em.find(Financiacion.class, financiacion.getVIdFinanciacion());
            List<Obra> obraListOld = persistentFinanciacion.getObraList();
            List<Obra> obraListNew = financiacion.getObraList();
            List<String> illegalOrphanMessages = null;
            for (Obra obraListOldObra : obraListOld) {
                if (!obraListNew.contains(obraListOldObra)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Obra " + obraListOldObra + " since its VFinanciacion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Obra> attachedObraListNew = new ArrayList<Obra>();
            for (Obra obraListNewObraToAttach : obraListNew) {
                obraListNewObraToAttach = em.getReference(obraListNewObraToAttach.getClass(), obraListNewObraToAttach.getVIdObra());
                attachedObraListNew.add(obraListNewObraToAttach);
            }
            obraListNew = attachedObraListNew;
            financiacion.setObraList(obraListNew);
            financiacion = em.merge(financiacion);
            for (Obra obraListNewObra : obraListNew) {
                if (!obraListOld.contains(obraListNewObra)) {
                    Financiacion oldVFinanciacionOfObraListNewObra = obraListNewObra.getVFinanciacion();
                    obraListNewObra.setVFinanciacion(financiacion);
                    obraListNewObra = em.merge(obraListNewObra);
                    if (oldVFinanciacionOfObraListNewObra != null && !oldVFinanciacionOfObraListNewObra.equals(financiacion)) {
                        oldVFinanciacionOfObraListNewObra.getObraList().remove(obraListNewObra);
                        oldVFinanciacionOfObraListNewObra = em.merge(oldVFinanciacionOfObraListNewObra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = financiacion.getVIdFinanciacion();
                if (findFinanciacion(id) == null) {
                    throw new NonexistentEntityException("The financiacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Financiacion financiacion;
            try {
                financiacion = em.getReference(Financiacion.class, id);
                financiacion.getVIdFinanciacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The financiacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Obra> obraListOrphanCheck = financiacion.getObraList();
            for (Obra obraListOrphanCheckObra : obraListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Financiacion (" + financiacion + ") cannot be destroyed since the Obra " + obraListOrphanCheckObra + " in its obraList field has a non-nullable VFinanciacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(financiacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Financiacion> findFinanciacionEntities() {
        return findFinanciacionEntities(true, -1, -1);
    }
    
    public ArrayList<Financiacion> findFinanciacionArrayList() {
        List<Financiacion> financiacionList = findFinanciacionEntities();
        return new ArrayList<>(financiacionList);
    }

    public List<Financiacion> findFinanciacionEntities(int maxResults, int firstResult) {
        return findFinanciacionEntities(false, maxResults, firstResult);
    }

    private List<Financiacion> findFinanciacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Financiacion.class));
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

    public Financiacion findFinanciacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Financiacion.class, id);
        } finally {
            em.close();
        }
    }
    
    public Financiacion findFinanciacionByDescription(String description) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Financiacion> query = em.createQuery("SELECT f FROM Financiacion f WHERE f.vDescripcion = :description", Financiacion.class);
            query.setParameter("description", description);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public int getFinanciacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Financiacion> rt = cq.from(Financiacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    }
