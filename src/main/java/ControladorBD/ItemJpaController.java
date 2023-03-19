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
import modelo.Obra;
import modelo.Renglonfoja;
import modelo.Costo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Item;

/**
 *
 * @author alejo
 */
public class ItemJpaController implements Serializable {

    public ItemJpaController() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_tpiPAII_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Item item) {
        if (item.getCostoList() == null) {
            item.setCostoList(new ArrayList<Costo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Obra VIdObra = item.getVIdObra();
            if (VIdObra != null) {
                VIdObra = em.getReference(VIdObra.getClass(), VIdObra.getVIdObra());
                item.setVIdObra(VIdObra);
            }
            Renglonfoja renglonfoja = item.getRenglonfoja();
            if (renglonfoja != null) {
                renglonfoja = em.getReference(renglonfoja.getClass(), renglonfoja.getVIdRenglon());
                item.setRenglonfoja(renglonfoja);
            }
            List<Costo> attachedCostoList = new ArrayList<Costo>();
            for (Costo costoListCostoToAttach : item.getCostoList()) {
                costoListCostoToAttach = em.getReference(costoListCostoToAttach.getClass(), costoListCostoToAttach.getVIdCosto());
                attachedCostoList.add(costoListCostoToAttach);
            }
            item.setCostoList(attachedCostoList);
            em.persist(item);
            if (VIdObra != null) {
                VIdObra.getItemList().add(item);
                VIdObra = em.merge(VIdObra);
            }
            if (renglonfoja != null) {
                Item oldVIdItemOfRenglonfoja = renglonfoja.getVIdItem();
                if (oldVIdItemOfRenglonfoja != null) {
                    oldVIdItemOfRenglonfoja.setRenglonfoja(null);
                    oldVIdItemOfRenglonfoja = em.merge(oldVIdItemOfRenglonfoja);
                }
                renglonfoja.setVIdItem(item);
                renglonfoja = em.merge(renglonfoja);
            }
            for (Costo costoListCosto : item.getCostoList()) {
                Item oldVIdItemOfCostoListCosto = costoListCosto.getVIdItem();
                costoListCosto.setVIdItem(item);
                costoListCosto = em.merge(costoListCosto);
                if (oldVIdItemOfCostoListCosto != null) {
                    oldVIdItemOfCostoListCosto.getCostoList().remove(costoListCosto);
                    oldVIdItemOfCostoListCosto = em.merge(oldVIdItemOfCostoListCosto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Item item) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Item persistentItem = em.find(Item.class, item.getVIdItem());
            Obra VIdObraOld = persistentItem.getVIdObra();
            Obra VIdObraNew = item.getVIdObra();
            Renglonfoja renglonfojaOld = persistentItem.getRenglonfoja();
            Renglonfoja renglonfojaNew = item.getRenglonfoja();
            List<Costo> costoListOld = persistentItem.getCostoList();
            List<Costo> costoListNew = item.getCostoList();
            List<String> illegalOrphanMessages = null;
            if (renglonfojaOld != null && !renglonfojaOld.equals(renglonfojaNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Renglonfoja " + renglonfojaOld + " since its VIdItem field is not nullable.");
            }
            for (Costo costoListOldCosto : costoListOld) {
                if (!costoListNew.contains(costoListOldCosto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Costo " + costoListOldCosto + " since its VIdItem field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (VIdObraNew != null) {
                VIdObraNew = em.getReference(VIdObraNew.getClass(), VIdObraNew.getVIdObra());
                item.setVIdObra(VIdObraNew);
            }
            if (renglonfojaNew != null) {
                renglonfojaNew = em.getReference(renglonfojaNew.getClass(), renglonfojaNew.getVIdRenglon());
                item.setRenglonfoja(renglonfojaNew);
            }
            List<Costo> attachedCostoListNew = new ArrayList<Costo>();
            for (Costo costoListNewCostoToAttach : costoListNew) {
                costoListNewCostoToAttach = em.getReference(costoListNewCostoToAttach.getClass(), costoListNewCostoToAttach.getVIdCosto());
                attachedCostoListNew.add(costoListNewCostoToAttach);
            }
            costoListNew = attachedCostoListNew;
            item.setCostoList(costoListNew);
            item = em.merge(item);
            if (VIdObraOld != null && !VIdObraOld.equals(VIdObraNew)) {
                VIdObraOld.getItemList().remove(item);
                VIdObraOld = em.merge(VIdObraOld);
            }
            if (VIdObraNew != null && !VIdObraNew.equals(VIdObraOld)) {
                VIdObraNew.getItemList().add(item);
                VIdObraNew = em.merge(VIdObraNew);
            }
            if (renglonfojaNew != null && !renglonfojaNew.equals(renglonfojaOld)) {
                Item oldVIdItemOfRenglonfoja = renglonfojaNew.getVIdItem();
                if (oldVIdItemOfRenglonfoja != null) {
                    oldVIdItemOfRenglonfoja.setRenglonfoja(null);
                    oldVIdItemOfRenglonfoja = em.merge(oldVIdItemOfRenglonfoja);
                }
                renglonfojaNew.setVIdItem(item);
                renglonfojaNew = em.merge(renglonfojaNew);
            }
            for (Costo costoListNewCosto : costoListNew) {
                if (!costoListOld.contains(costoListNewCosto)) {
                    Item oldVIdItemOfCostoListNewCosto = costoListNewCosto.getVIdItem();
                    costoListNewCosto.setVIdItem(item);
                    costoListNewCosto = em.merge(costoListNewCosto);
                    if (oldVIdItemOfCostoListNewCosto != null && !oldVIdItemOfCostoListNewCosto.equals(item)) {
                        oldVIdItemOfCostoListNewCosto.getCostoList().remove(costoListNewCosto);
                        oldVIdItemOfCostoListNewCosto = em.merge(oldVIdItemOfCostoListNewCosto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = item.getVIdItem();
                if (findItem(id) == null) {
                    throw new NonexistentEntityException("The item with id " + id + " no longer exists.");
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
            Item item;
            try {
                item = em.getReference(Item.class, id);
                item.getVIdItem();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The item with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Renglonfoja renglonfojaOrphanCheck = item.getRenglonfoja();
            if (renglonfojaOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Item (" + item + ") cannot be destroyed since the Renglonfoja " + renglonfojaOrphanCheck + " in its renglonfoja field has a non-nullable VIdItem field.");
            }
            List<Costo> costoListOrphanCheck = item.getCostoList();
            for (Costo costoListOrphanCheckCosto : costoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Item (" + item + ") cannot be destroyed since the Costo " + costoListOrphanCheckCosto + " in its costoList field has a non-nullable VIdItem field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Obra VIdObra = item.getVIdObra();
            if (VIdObra != null) {
                VIdObra.getItemList().remove(item);
                VIdObra = em.merge(VIdObra);
            }
            em.remove(item);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Item> findItemEntities() {
        return findItemEntities(true, -1, -1);
    }

    public List<Item> findItemEntities(int maxResults, int firstResult) {
        return findItemEntities(false, maxResults, firstResult);
    }

    private List<Item> findItemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Item.class));
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

    public Item findItem(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Item.class, id);
        } finally {
            em.close();
        }
    }

    public int getItemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Item> rt = cq.from(Item.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
