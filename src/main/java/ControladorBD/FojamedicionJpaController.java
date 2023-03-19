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
import modelo.Certificadopago;
import modelo.Obra;
import modelo.Renglonfoja;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import modelo.Fojamedicion;

/**
 *
 * @author alejo
 */
public class FojamedicionJpaController implements Serializable {

    public FojamedicionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_tpiPAII_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fojamedicion fojamedicion) {
        if (fojamedicion.getRenglonfojaList() == null) {
            fojamedicion.setRenglonfojaList(new ArrayList<Renglonfoja>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Certificadopago certificadopago = fojamedicion.getCertificadopago();
            if (certificadopago != null) {
                certificadopago = em.getReference(certificadopago.getClass(), certificadopago.getVIdCertificado());
                fojamedicion.setCertificadopago(certificadopago);
            }
            Obra VObra = fojamedicion.getVObra();
            if (VObra != null) {
                VObra = em.getReference(VObra.getClass(), VObra.getVIdObra());
                fojamedicion.setVObra(VObra);
            }
            List<Renglonfoja> attachedRenglonfojaList = new ArrayList<Renglonfoja>();
            for (Renglonfoja renglonfojaListRenglonfojaToAttach : fojamedicion.getRenglonfojaList()) {
                renglonfojaListRenglonfojaToAttach = em.getReference(renglonfojaListRenglonfojaToAttach.getClass(), renglonfojaListRenglonfojaToAttach.getVIdRenglon());
                attachedRenglonfojaList.add(renglonfojaListRenglonfojaToAttach);
            }
            fojamedicion.setRenglonfojaList(attachedRenglonfojaList);
            em.persist(fojamedicion);
            if (certificadopago != null) {
                Fojamedicion oldVFojaOfCertificadopago = certificadopago.getVFoja();
                if (oldVFojaOfCertificadopago != null) {
                    oldVFojaOfCertificadopago.setCertificadopago(null);
                    oldVFojaOfCertificadopago = em.merge(oldVFojaOfCertificadopago);
                }
                certificadopago.setVFoja(fojamedicion);
                certificadopago = em.merge(certificadopago);
            }
            if (VObra != null) {
                VObra.getFojamedicionList().add(fojamedicion);
                VObra = em.merge(VObra);
            }
            for (Renglonfoja renglonfojaListRenglonfoja : fojamedicion.getRenglonfojaList()) {
                Fojamedicion oldVIdFojaOfRenglonfojaListRenglonfoja = renglonfojaListRenglonfoja.getVIdFoja();
                renglonfojaListRenglonfoja.setVIdFoja(fojamedicion);
                renglonfojaListRenglonfoja = em.merge(renglonfojaListRenglonfoja);
                if (oldVIdFojaOfRenglonfojaListRenglonfoja != null) {
                    oldVIdFojaOfRenglonfojaListRenglonfoja.getRenglonfojaList().remove(renglonfojaListRenglonfoja);
                    oldVIdFojaOfRenglonfojaListRenglonfoja = em.merge(oldVIdFojaOfRenglonfojaListRenglonfoja);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fojamedicion fojamedicion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fojamedicion persistentFojamedicion = em.find(Fojamedicion.class, fojamedicion.getVIdFoja());
            Certificadopago certificadopagoOld = persistentFojamedicion.getCertificadopago();
            Certificadopago certificadopagoNew = fojamedicion.getCertificadopago();
            Obra VObraOld = persistentFojamedicion.getVObra();
            Obra VObraNew = fojamedicion.getVObra();
            List<Renglonfoja> renglonfojaListOld = persistentFojamedicion.getRenglonfojaList();
            List<Renglonfoja> renglonfojaListNew = fojamedicion.getRenglonfojaList();
            List<String> illegalOrphanMessages = null;
            if (certificadopagoOld != null && !certificadopagoOld.equals(certificadopagoNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Certificadopago " + certificadopagoOld + " since its VFoja field is not nullable.");
            }
            for (Renglonfoja renglonfojaListOldRenglonfoja : renglonfojaListOld) {
                if (!renglonfojaListNew.contains(renglonfojaListOldRenglonfoja)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Renglonfoja " + renglonfojaListOldRenglonfoja + " since its VIdFoja field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (certificadopagoNew != null) {
                certificadopagoNew = em.getReference(certificadopagoNew.getClass(), certificadopagoNew.getVIdCertificado());
                fojamedicion.setCertificadopago(certificadopagoNew);
            }
            if (VObraNew != null) {
                VObraNew = em.getReference(VObraNew.getClass(), VObraNew.getVIdObra());
                fojamedicion.setVObra(VObraNew);
            }
            List<Renglonfoja> attachedRenglonfojaListNew = new ArrayList<Renglonfoja>();
            for (Renglonfoja renglonfojaListNewRenglonfojaToAttach : renglonfojaListNew) {
                renglonfojaListNewRenglonfojaToAttach = em.getReference(renglonfojaListNewRenglonfojaToAttach.getClass(), renglonfojaListNewRenglonfojaToAttach.getVIdRenglon());
                attachedRenglonfojaListNew.add(renglonfojaListNewRenglonfojaToAttach);
            }
            renglonfojaListNew = attachedRenglonfojaListNew;
            fojamedicion.setRenglonfojaList(renglonfojaListNew);
            fojamedicion = em.merge(fojamedicion);
            if (certificadopagoNew != null && !certificadopagoNew.equals(certificadopagoOld)) {
                Fojamedicion oldVFojaOfCertificadopago = certificadopagoNew.getVFoja();
                if (oldVFojaOfCertificadopago != null) {
                    oldVFojaOfCertificadopago.setCertificadopago(null);
                    oldVFojaOfCertificadopago = em.merge(oldVFojaOfCertificadopago);
                }
                certificadopagoNew.setVFoja(fojamedicion);
                certificadopagoNew = em.merge(certificadopagoNew);
            }
            if (VObraOld != null && !VObraOld.equals(VObraNew)) {
                VObraOld.getFojamedicionList().remove(fojamedicion);
                VObraOld = em.merge(VObraOld);
            }
            if (VObraNew != null && !VObraNew.equals(VObraOld)) {
                VObraNew.getFojamedicionList().add(fojamedicion);
                VObraNew = em.merge(VObraNew);
            }
            for (Renglonfoja renglonfojaListNewRenglonfoja : renglonfojaListNew) {
                if (!renglonfojaListOld.contains(renglonfojaListNewRenglonfoja)) {
                    Fojamedicion oldVIdFojaOfRenglonfojaListNewRenglonfoja = renglonfojaListNewRenglonfoja.getVIdFoja();
                    renglonfojaListNewRenglonfoja.setVIdFoja(fojamedicion);
                    renglonfojaListNewRenglonfoja = em.merge(renglonfojaListNewRenglonfoja);
                    if (oldVIdFojaOfRenglonfojaListNewRenglonfoja != null && !oldVIdFojaOfRenglonfojaListNewRenglonfoja.equals(fojamedicion)) {
                        oldVIdFojaOfRenglonfojaListNewRenglonfoja.getRenglonfojaList().remove(renglonfojaListNewRenglonfoja);
                        oldVIdFojaOfRenglonfojaListNewRenglonfoja = em.merge(oldVIdFojaOfRenglonfojaListNewRenglonfoja);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fojamedicion.getVIdFoja();
                if (findFojamedicion(id) == null) {
                    throw new NonexistentEntityException("The fojamedicion with id " + id + " no longer exists.");
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
            Fojamedicion fojamedicion;
            try {
                fojamedicion = em.getReference(Fojamedicion.class, id);
                fojamedicion.getVIdFoja();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fojamedicion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Certificadopago certificadopagoOrphanCheck = fojamedicion.getCertificadopago();
            if (certificadopagoOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Fojamedicion (" + fojamedicion + ") cannot be destroyed since the Certificadopago " + certificadopagoOrphanCheck + " in its certificadopago field has a non-nullable VFoja field.");
            }
            List<Renglonfoja> renglonfojaListOrphanCheck = fojamedicion.getRenglonfojaList();
            for (Renglonfoja renglonfojaListOrphanCheckRenglonfoja : renglonfojaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Fojamedicion (" + fojamedicion + ") cannot be destroyed since the Renglonfoja " + renglonfojaListOrphanCheckRenglonfoja + " in its renglonfojaList field has a non-nullable VIdFoja field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Obra VObra = fojamedicion.getVObra();
            if (VObra != null) {
                VObra.getFojamedicionList().remove(fojamedicion);
                VObra = em.merge(VObra);
            }
            em.remove(fojamedicion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fojamedicion> findFojamedicionEntities() {
        return findFojamedicionEntities(true, -1, -1);
    }

    public List<Fojamedicion> findFojamedicionEntities(int maxResults, int firstResult) {
        return findFojamedicionEntities(false, maxResults, firstResult);
    }

    private List<Fojamedicion> findFojamedicionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Fojamedicion.class));
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

    public Fojamedicion findFojamedicion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fojamedicion.class, id);
        } finally {
            em.close();
        }
    }
    
    public Fojamedicion findFojamedicionPorObraYFecha(Integer vIdObra, String fechaEmision) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Fojamedicion> query = em.createQuery(
                "SELECT f FROM Fojamedicion f WHERE f.vObra = :obra AND f.vFechaEmision = :fechaEmision", 
                Fojamedicion.class);
            query.setParameter("obra", vIdObra);
            query.setParameter("fechaEmision", fechaEmision);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }
    
    /*public Fojamedicion findUltimaFoja(Integer vIdObra) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Fojamedicion> query = em.createQuery(
            "SELECT f FROM Fojamedicion f WHERE f.vObra = :obra AND f.vIdFoja = (SELECT MAX(f2.vIdFoja) FROM Fojamedicion f2 WHERE f2.vObra = :obra)",
            Fojamedicion.class);
            query.setParameter("obra", vIdObra);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }*/
    
    public Fojamedicion findUltimaFoja(Obra obra) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Fojamedicion> query = em.createQuery(
                "SELECT f FROM Fojamedicion f WHERE f.vObra = :obra AND f.vIdFoja = (SELECT MAX(f2.vIdFoja) FROM Fojamedicion f2 WHERE f2.vObra = :obra)",
                Fojamedicion.class);
            query.setParameter("obra", obra);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }
    
    public Integer findCantidadFoja(Obra obra) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(f.vIdFoja) FROM Fojamedicion f WHERE f.vObra = :obra",
                Long.class);
            query.setParameter("obra", obra);
            Long result = query.getSingleResult();
            return result.intValue();
        } finally {
            em.close();
        }
    }

    public int getFojamedicionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Fojamedicion> rt = cq.from(Fojamedicion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
