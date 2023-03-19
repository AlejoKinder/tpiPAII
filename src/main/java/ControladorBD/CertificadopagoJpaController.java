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
import modelo.Fojamedicion;
import modelo.Rengloncertificado;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Certificadopago;

/**
 *
 * @author alejo
 */
public class CertificadopagoJpaController implements Serializable {

    public CertificadopagoJpaController() {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_tpiPAII_jar_1.0-SNAPSHOTPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Certificadopago certificadopago) throws IllegalOrphanException {
        if (certificadopago.getRengloncertificadoList() == null) {
            certificadopago.setRengloncertificadoList(new ArrayList<Rengloncertificado>());
        }
        List<String> illegalOrphanMessages = null;
        Fojamedicion VFojaOrphanCheck = certificadopago.getVFoja();
        if (VFojaOrphanCheck != null) {
            Certificadopago oldCertificadopagoOfVFoja = VFojaOrphanCheck.getCertificadopago();
            if (oldCertificadopagoOfVFoja != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Fojamedicion " + VFojaOrphanCheck + " already has an item of type Certificadopago whose VFoja column cannot be null. Please make another selection for the VFoja field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fojamedicion VFoja = certificadopago.getVFoja();
            if (VFoja != null) {
                VFoja = em.getReference(VFoja.getClass(), VFoja.getVIdFoja());
                certificadopago.setVFoja(VFoja);
            }
            List<Rengloncertificado> attachedRengloncertificadoList = new ArrayList<Rengloncertificado>();
            for (Rengloncertificado rengloncertificadoListRengloncertificadoToAttach : certificadopago.getRengloncertificadoList()) {
                rengloncertificadoListRengloncertificadoToAttach = em.getReference(rengloncertificadoListRengloncertificadoToAttach.getClass(), rengloncertificadoListRengloncertificadoToAttach.getVIdRenglon());
                attachedRengloncertificadoList.add(rengloncertificadoListRengloncertificadoToAttach);
            }
            certificadopago.setRengloncertificadoList(attachedRengloncertificadoList);
            em.persist(certificadopago);
            if (VFoja != null) {
                VFoja.setCertificadopago(certificadopago);
                VFoja = em.merge(VFoja);
            }
            for (Rengloncertificado rengloncertificadoListRengloncertificado : certificadopago.getRengloncertificadoList()) {
                Certificadopago oldVIdCertificadoOfRengloncertificadoListRengloncertificado = rengloncertificadoListRengloncertificado.getVIdCertificado();
                rengloncertificadoListRengloncertificado.setVIdCertificado(certificadopago);
                rengloncertificadoListRengloncertificado = em.merge(rengloncertificadoListRengloncertificado);
                if (oldVIdCertificadoOfRengloncertificadoListRengloncertificado != null) {
                    oldVIdCertificadoOfRengloncertificadoListRengloncertificado.getRengloncertificadoList().remove(rengloncertificadoListRengloncertificado);
                    oldVIdCertificadoOfRengloncertificadoListRengloncertificado = em.merge(oldVIdCertificadoOfRengloncertificadoListRengloncertificado);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Certificadopago certificadopago) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Certificadopago persistentCertificadopago = em.find(Certificadopago.class, certificadopago.getVIdCertificado());
            Fojamedicion VFojaOld = persistentCertificadopago.getVFoja();
            Fojamedicion VFojaNew = certificadopago.getVFoja();
            List<Rengloncertificado> rengloncertificadoListOld = persistentCertificadopago.getRengloncertificadoList();
            List<Rengloncertificado> rengloncertificadoListNew = certificadopago.getRengloncertificadoList();
            List<String> illegalOrphanMessages = null;
            if (VFojaNew != null && !VFojaNew.equals(VFojaOld)) {
                Certificadopago oldCertificadopagoOfVFoja = VFojaNew.getCertificadopago();
                if (oldCertificadopagoOfVFoja != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Fojamedicion " + VFojaNew + " already has an item of type Certificadopago whose VFoja column cannot be null. Please make another selection for the VFoja field.");
                }
            }
            for (Rengloncertificado rengloncertificadoListOldRengloncertificado : rengloncertificadoListOld) {
                if (!rengloncertificadoListNew.contains(rengloncertificadoListOldRengloncertificado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Rengloncertificado " + rengloncertificadoListOldRengloncertificado + " since its VIdCertificado field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (VFojaNew != null) {
                VFojaNew = em.getReference(VFojaNew.getClass(), VFojaNew.getVIdFoja());
                certificadopago.setVFoja(VFojaNew);
            }
            List<Rengloncertificado> attachedRengloncertificadoListNew = new ArrayList<Rengloncertificado>();
            for (Rengloncertificado rengloncertificadoListNewRengloncertificadoToAttach : rengloncertificadoListNew) {
                rengloncertificadoListNewRengloncertificadoToAttach = em.getReference(rengloncertificadoListNewRengloncertificadoToAttach.getClass(), rengloncertificadoListNewRengloncertificadoToAttach.getVIdRenglon());
                attachedRengloncertificadoListNew.add(rengloncertificadoListNewRengloncertificadoToAttach);
            }
            rengloncertificadoListNew = attachedRengloncertificadoListNew;
            certificadopago.setRengloncertificadoList(rengloncertificadoListNew);
            certificadopago = em.merge(certificadopago);
            if (VFojaOld != null && !VFojaOld.equals(VFojaNew)) {
                VFojaOld.setCertificadopago(null);
                VFojaOld = em.merge(VFojaOld);
            }
            if (VFojaNew != null && !VFojaNew.equals(VFojaOld)) {
                VFojaNew.setCertificadopago(certificadopago);
                VFojaNew = em.merge(VFojaNew);
            }
            for (Rengloncertificado rengloncertificadoListNewRengloncertificado : rengloncertificadoListNew) {
                if (!rengloncertificadoListOld.contains(rengloncertificadoListNewRengloncertificado)) {
                    Certificadopago oldVIdCertificadoOfRengloncertificadoListNewRengloncertificado = rengloncertificadoListNewRengloncertificado.getVIdCertificado();
                    rengloncertificadoListNewRengloncertificado.setVIdCertificado(certificadopago);
                    rengloncertificadoListNewRengloncertificado = em.merge(rengloncertificadoListNewRengloncertificado);
                    if (oldVIdCertificadoOfRengloncertificadoListNewRengloncertificado != null && !oldVIdCertificadoOfRengloncertificadoListNewRengloncertificado.equals(certificadopago)) {
                        oldVIdCertificadoOfRengloncertificadoListNewRengloncertificado.getRengloncertificadoList().remove(rengloncertificadoListNewRengloncertificado);
                        oldVIdCertificadoOfRengloncertificadoListNewRengloncertificado = em.merge(oldVIdCertificadoOfRengloncertificadoListNewRengloncertificado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = certificadopago.getVIdCertificado();
                if (findCertificadopago(id) == null) {
                    throw new NonexistentEntityException("The certificadopago with id " + id + " no longer exists.");
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
            Certificadopago certificadopago;
            try {
                certificadopago = em.getReference(Certificadopago.class, id);
                certificadopago.getVIdCertificado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The certificadopago with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Rengloncertificado> rengloncertificadoListOrphanCheck = certificadopago.getRengloncertificadoList();
            for (Rengloncertificado rengloncertificadoListOrphanCheckRengloncertificado : rengloncertificadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Certificadopago (" + certificadopago + ") cannot be destroyed since the Rengloncertificado " + rengloncertificadoListOrphanCheckRengloncertificado + " in its rengloncertificadoList field has a non-nullable VIdCertificado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Fojamedicion VFoja = certificadopago.getVFoja();
            if (VFoja != null) {
                VFoja.setCertificadopago(null);
                VFoja = em.merge(VFoja);
            }
            em.remove(certificadopago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Certificadopago> findCertificadopagoEntities() {
        return findCertificadopagoEntities(true, -1, -1);
    }

    public List<Certificadopago> findCertificadopagoEntities(int maxResults, int firstResult) {
        return findCertificadopagoEntities(false, maxResults, firstResult);
    }

    private List<Certificadopago> findCertificadopagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Certificadopago.class));
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

    public Certificadopago findCertificadopago(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Certificadopago.class, id);
        } finally {
            em.close();
        }
    }

    public int getCertificadopagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Certificadopago> rt = cq.from(Certificadopago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
