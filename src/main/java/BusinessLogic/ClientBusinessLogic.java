

package BusinessLogic;

import Model.Client;

import DataAccess.ClientDataAccess;

import java.util.NoSuchElementException;

public class ClientBusinessLogic {

    /**
     * This class it's used for clients operations between the database.
     */

    public ClientBusinessLogic(){}

    public Client findClientById(int id)
    {
        Client client = ClientDataAccess.findById(id);
        if(client == null)
        {
            throw new NoSuchElementException("The client with ID " + id + " does not exist in the data base!");
        }
        return client;
    }

    public void insertClient(Client client)
    {
        ClientDataAccess.insert(client);
    }

    public void deleteClient(int clientId)
    {
        ClientDataAccess.delete(clientId);
    }

}
