/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servermachine;

import dao.IDao;
import entities.Machine;
import entities.Salle;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.hibernate.Hibernate;
import service.MachineService;
import service.SalleService;
import util.HibernateUtil;

/**
 *
 * @author Lachgar
 */
public class ServerMachine {

    /**
     * @param args the command line arguments
     */  
    private static InitialContext ctx;
    public static void initJNDI(){
       try{
        LocateRegistry.createRegistry(1099);
        final Hashtable<Object, Object> jndiProperties = new Hashtable<Object, Object>();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.rmi.registry.RegistryContextFactory");
        jndiProperties.put(Context.PROVIDER_URL,"rmi://localhost:1099");
        ctx = new InitialContext(jndiProperties);
       }catch(RemoteException e){
           e.printStackTrace();
       } catch(NamingException e){
           e.printStackTrace();}
    }
    
    
    public static void main(String[] args) throws RemoteException {
        try {
            initJNDI();
            IDao<Machine> dao = new  MachineService();
            IDao<Salle> dao1 = new SalleService();
            
            
           // LocateRegistry.createRegistry(1099);
          //  dao.create(new Machine());
            //dao1.create(new Salle());
           
            
            Naming.bind("rmi://localhost:1099/dao", dao);
            Naming.bind("rmi://localhost:1099/dao1", dao1);
           
            
            System.out.println("En attente d'un client ");
            
        } catch (AlreadyBoundException ex) {
            Logger.getLogger(ServerMachine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServerMachine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
