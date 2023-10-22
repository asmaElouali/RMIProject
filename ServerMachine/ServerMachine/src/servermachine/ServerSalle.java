/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servermachine;

import dao.IDao;
import entities.Salle;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Hibernate;
import service.SalleService;
import util.HibernateUtil;

/**
 *
 * @author acer
 */
public class ServerSalle{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  throws RemoteException  {
        // TODO code application logic here
        try {
            
           // ServerMachine.main(new String[]{});
            IDao<Salle> dao1 = new SalleService();
            LocateRegistry.createRegistry(1099);
            dao1.create(new Salle("aze12"));
            Naming.bind("rmi://localhost:1099/dao1", dao1);

        } catch (AlreadyBoundException ex) {
            Logger.getLogger(ServerSalle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ServerSalle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
