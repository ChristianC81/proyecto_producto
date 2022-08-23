/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_producto;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author chris
 */
public class ManagerFactory {
    private EntityManagerFactory emf = null;
    public EntityManagerFactory getEntityManagerFactory(){
       
        return emf=Persistence.createEntityManagerFactory("proyecto_productoPU");
    }
}
