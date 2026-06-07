import BusinessLogic.OrdersManagement;
import BusinessLogic.ProductBusinessLogic;
import GUI.MainFrame;
import Model.*;
import DataAccess.*;

public class Main
{
    public static void main(String argc[])
    {
        try
        {
            com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme.setup();
        }
        catch( Exception ex )
        {
            System.err.println( "Failed to initialize the theme" );
        }
        new MainFrame();


    }
}
