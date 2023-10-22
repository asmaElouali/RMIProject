/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientmachine;

import dao.IDao;
import entities.Machine;
import entities.Salle;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Lachgar
 */
public class ClientMachine {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws NamingException {
        try {

            final Hashtable<Object, Object> jndiProperties = new Hashtable<Object, Object>();
            jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
            jndiProperties.put(Context.PROVIDER_URL, "rmi://localhost:1099");
            InitialContext ctx = new InitialContext(jndiProperties);
            IDao<Machine> dao = (IDao<Machine>) Naming.lookup("rmi://localhost:1099/dao");
            IDao<Salle> dao1 = (IDao<Salle>) Naming.lookup("rmi://localhost:1099/dao1");

            //dao.create(new Machine("RT55", "TOSHIBA", 7000));
            //dao1.create(new Salle("TOSHIBA"));
        } catch (NotBoundException ex) {
            Logger.getLogger(ClientMachine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClientMachine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientMachine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
