

package Model;


import DataAccess.ClientDataAccess;

public class Client {
    /**
     * Model class of a client. We are storing the ID, email and name
     */
    private int id;
    private String email;
    private String name;

    public Client()
    {}

    public Client(String email, String name)
    {
        this.email = email;
        this.name = name;
    }

    public int getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
        ClientDataAccess.updateEmail(this.getId(),email);
    }

    public void setName(String name)
    {
        this.name = name;
        ClientDataAccess.updateName(this.getId(),name);
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "Client id: " + this.getId() + " ,name: " + this.getName() + " ,and email: " + this.getEmail();
    }
}
