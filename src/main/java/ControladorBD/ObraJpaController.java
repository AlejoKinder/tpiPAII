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
import modelo.Financiacion;
import modelo.Empresa;
import modelo.Item;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import modelo.Fojamedicion;
import modelo.Obra;

/**
 *
 * @author alejo
 */
public class ObraJpaController implements Serializable {

    public ObraJpaController() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_tpiPAII_jar_1.0-SNAPSHOTPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Obra obra) {
        if (obra.getItemList() == null) {
            obra.setItemList(new ArrayList<Item>());
        }
        if (obra.getFojamedicionList() == null) {
            obra.setFojamedicionList(new ArrayList<Fojamedicion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Financiacion VFinanciacion = obra.getVFinanciacion();
            if (VFinanciacion != null) {
                VFinanciacion = em.getReference(VFinanciacion.getClass(), VFinanciacion.getVIdFinanciacion());
                obra.setVFinanciacion(VFinanciacion);
            }
            Empresa VEmpresa = obra.getVEmpresa();
            if (VEmpresa != null) {
                VEmpresa = em.getReference(VEmpresa.getClass(), VEmpresa.getVCuit());
                obra.setVEmpresa(VEmpresa);
            }
            List<Item> attachedItemList = new ArrayList<Item>();
            for (Item itemListItemToAttach : obra.getItemList()) {
                itemListItemToAttach = em.getReference(itemListItemToAttach.getClass(), itemListItemToAttach.getVIdItem());
                attachedItemList.add(itemListItemToAttach);
            }
            obra.setItemList(attachedItemList);
            List<Fojamedicion> attachedFojamedicionList = new ArrayList<Fojamedicion>();
            for (Fojamedicion fojamedicionListFojamedicionToAttach : obra.getFojamedicionList()) {
                fojamedicionListFojamedicionToAttach = em.getReference(fojamedicionListFojamedicionToAttach.getClass(), fojamedicionListFojamedicionToAttach.getVIdFoja());
                attachedFojamedicionList.add(fojamedicionListFojamedicionToAttach);
            }
            obra.setFojamedicionList(attachedFojamedicionList);
            em.persist(obra);
            if (VFinanciacion != null) {
                VFinanciacion.getObraList().add(obra);
                VFinanciacion = em.merge(VFinanciacion);
            }
            if (VEmpresa != null) {
                VEmpresa.getObraList().add(obra);
                VEmpresa = em.merge(VEmpresa);
            }
            for (Item itemListItem : obra.getItemList()) {
                Obra oldVIdObraOfItemListItem = itemListItem.getVIdObra();
                itemListItem.setVIdObra(obra);
                itemListItem = em.merge(itemListItem);
                if (oldVIdObraOfItemListItem != null) {
                    oldVIdObraOfItemListItem.getItemList().remove(itemListItem);
                    oldVIdObraOfItemListItem = em.merge(oldVIdObraOfItemListItem);
                }
            }
            for (Fojamedicion fojamedicionListFojamedicion : obra.getFojamedicionList()) {
                Obra oldVObraOfFojamedicionListFojamedicion = fojamedicionListFojamedicion.getVObra();
                fojamedicionListFojamedicion.setVObra(obra);
                fojamedicionListFojamedicion = em.merge(fojamedicionListFojamedicion);
                if (oldVObraOfFojamedicionListFojamedicion != null) {
                    oldVObraOfFojamedicionListFojamedicion.getFojamedicionList().remove(fojamedicionListFojamedicion);
                    oldVObraOfFojamedicionListFojamedicion = em.merge(oldVObraOfFojamedicionListFojamedicion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Obra obra) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Obra persistentObra = em.find(Obra.class, obra.getVIdObra());
            Financiacion VFinanciacionOld = persistentObra.getVFinanciacion();
            Financiacion VFinanciacionNew = obra.getVFinanciacion();
            Empresa VEmpresaOld = persistentObra.getVEmpresa();
            Empresa VEmpresaNew = obra.getVEmpresa();
            List<Item> itemListOld = persistentObra.getItemList();
            List<Item> itemListNew = obra.getItemList();
            List<Fojamedicion> fojamedicionListOld = persistentObra.getFojamedicionList();
            List<Fojamedicion> fojamedicionListNew = obra.getFojamedicionList();
            List<String> illegalOrphanMessages = null;
            for (Item itemListOldItem : itemListOld) {
                if (!itemListNew.contains(itemListOldItem)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Item " + itemListOldItem + " since its VIdObra field is not nullable.");
                }
            }
            for (Fojamedicion fojamedicionListOldFojamedicion : fojamedicionListOld) {
                if (!fojamedicionListNew.contains(fojamedicionListOldFojamedicion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Fojamedicion " + fojamedicionListOldFojamedicion + " since its VObra field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (VFinanciacionNew != null) {
                VFinanciacionNew = em.getReference(VFinanciacionNew.getClass(), VFinanciacionNew.getVIdFinanciacion());
                obra.setVFinanciacion(VFinanciacionNew);
            }
            if (VEmpresaNew != null) {
                VEmpresaNew = em.getReference(VEmpresaNew.getClass(), VEmpresaNew.getVCuit());
                obra.setVEmpresa(VEmpresaNew);
            }
            List<Item> attachedItemListNew = new ArrayList<Item>();
            for (Item itemListNewItemToAttach : itemListNew) {
                itemListNewItemToAttach = em.getReference(itemListNewItemToAttach.getClass(), itemListNewItemToAttach.getVIdItem());
                attachedItemListNew.add(itemListNewItemToAttach);
            }
            itemListNew = attachedItemListNew;
            obra.setItemList(itemListNew);
            List<Fojamedicion> attachedFojamedicionListNew = new ArrayList<Fojamedicion>();
            for (Fojamedicion fojamedicionListNewFojamedicionToAttach : fojamedicionListNew) {
                fojamedicionListNewFojamedicionToAttach = em.getReference(fojamedicionListNewFojamedicionToAttach.getClass(), fojamedicionListNewFojamedicionToAttach.getVIdFoja());
                attachedFojamedicionListNew.add(fojamedicionListNewFojamedicionToAttach);
            }
            fojamedicionListNew = attachedFojamedicionListNew;
            obra.setFojamedicionList(fojamedicionListNew);
            obra = em.merge(obra);
            if (VFinanciacionOld != null && !VFinanciacionOld.equals(VFinanciacionNew)) {
                VFinanciacionOld.getObraList().remove(obra);
                VFinanciacionOld = em.merge(VFinanciacionOld);
            }
            if (VFinanciacionNew != null && !VFinanciacionNew.equals(VFinanciacionOld)) {
                VFinanciacionNew.getObraList().add(obra);
                VFinanciacionNew = em.merge(VFinanciacionNew);
            }
            if (VEmpresaOld != null && !VEmpresaOld.equals(VEmpresaNew)) {
                VEmpresaOld.getObraList().remove(obra);
                VEmpresaOld = em.merge(VEmpresaOld);
            }
            if (VEmpresaNew != null && !VEmpresaNew.equals(VEmpresaOld)) {
                VEmpresaNew.getObraList().add(obra);
                VEmpresaNew = em.merge(VEmpresaNew);
            }
            for (Item itemListNewItem : itemListNew) {
                if (!itemListOld.contains(itemListNewItem)) {
                    Obra oldVIdObraOfItemListNewItem = itemListNewItem.getVIdObra();
                    itemListNewItem.setVIdObra(obra);
                    itemListNewItem = em.merge(itemListNewItem);
                    if (oldVIdObraOfItemListNewItem != null && !oldVIdObraOfItemListNewItem.equals(obra)) {
                        oldVIdObraOfItemListNewItem.getItemList().remove(itemListNewItem);
                        oldVIdObraOfItemListNewItem = em.merge(oldVIdObraOfItemListNewItem);
                    }
                }
            }
            for (Fojamedicion fojamedicionListNewFojamedicion : fojamedicionListNew) {
                if (!fojamedicionListOld.contains(fojamedicionListNewFojamedicion)) {
                    Obra oldVObraOfFojamedicionListNewFojamedicion = fojamedicionListNewFojamedicion.getVObra();
                    fojamedicionListNewFojamedicion.setVObra(obra);
                    fojamedicionListNewFojamedicion = em.merge(fojamedicionListNewFojamedicion);
                    if (oldVObraOfFojamedicionListNewFojamedicion != null && !oldVObraOfFojamedicionListNewFojamedicion.equals(obra)) {
                        oldVObraOfFojamedicionListNewFojamedicion.getFojamedicionList().remove(fojamedicionListNewFojamedicion);
                        oldVObraOfFojamedicionListNewFojamedicion = em.merge(oldVObraOfFojamedicionListNewFojamedicion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = obra.getVIdObra();
                if (findObra(id) == null) {
                    throw new NonexistentEntityException("The obra with id " + id + " no longer exists.");
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
            Obra obra;
            try {
                obra = em.getReference(Obra.class, id);
                obra.getVIdObra();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The obra with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Item> itemListOrphanCheck = obra.getItemList();
            for (Item itemListOrphanCheckItem : itemListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Obra (" + obra + ") cannot be destroyed since the Item " + itemListOrphanCheckItem + " in its itemList field has a non-nullable VIdObra field.");
            }
            List<Fojamedicion> fojamedicionListOrphanCheck = obra.getFojamedicionList();
            for (Fojamedicion fojamedicionListOrphanCheckFojamedicion : fojamedicionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Obra (" + obra + ") cannot be destroyed since the Fojamedicion " + fojamedicionListOrphanCheckFojamedicion + " in its fojamedicionList field has a non-nullable VObra field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Financiacion VFinanciacion = obra.getVFinanciacion();
            if (VFinanciacion != null) {
                VFinanciacion.getObraList().remove(obra);
                VFinanciacion = em.merge(VFinanciacion);
            }
            Empresa VEmpresa = obra.getVEmpresa();
            if (VEmpresa != null) {
                VEmpresa.getObraList().remove(obra);
                VEmpresa = em.merge(VEmpresa);
            }
            em.remove(obra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Obra> findObraEntities() {
        return findObraEntities(true, -1, -1);
    }

    public List<Obra> findObraEntities(int maxResults, int firstResult) {
        return findObraEntities(false, maxResults, firstResult);
    }

    private List<Obra> findObraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Obra.class));
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

    public Obra findObra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Obra.class, id);
        } finally {
            em.close();
        }
    }

    public Obra findObraByName(String nombre) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Obra> query = em.createQuery("SELECT f FROM Obra f WHERE f.vDenominacion = :nombre", Obra.class);
            query.setParameter("nombre", nombre);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    
    public int getObraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Obra> rt = cq.from(Obra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
