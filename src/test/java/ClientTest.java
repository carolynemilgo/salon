import org.junit.*;
import org.sql2o.*;
import static org.junit.Assert.*;


public class ClientTest{
  @Rule
   public DatabaseRule database = new DatabaseRule();

   @Test
   public void Client_instantiatesCorrectly_true(){
     Client myClient= new Client("Jess", 789, 1);
     assertEquals(true, myClient instanceof Client);
   }
   @Test
   public void getClientId_clientsInstantiateWithAnID_1() {
     //Task.clear();  // Remember, the test will fail without this line! We need to empty leftover Tasks from previous tests!
       Client myClient= new Client("Caro", 8787, 1);
       myClient.save();
       assertTrue(myClient.getId() > 0);
   }
   @Test
   public void client_InstantiatesWithPhoneNumber_0723456(){
     Client myClient=new Client("Jane", 456, 1);
     assertEquals(456, myClient.getClientNumber());
   }
   @Test
   public void all_returnsAllInstancesOfClient_true(){
     Client firstClient= new Client("Jill", 9878, 1);
     firstClient.save();
     Client secondClient= new Client("Too", 9888, 1);
     secondClient.save();
     assertEquals(true, Client.all().contains(firstClient));
     assertEquals(true, Client.all().contains(secondClient));
   }

@Test
public void update_updatesClientDetails_true(){
  Client myClient=new Client("Eli", 765, 1);
  myClient.save();
  myClient.update("Kim", 765);
  assertEquals("Kim", Client.find(myClient.getId()).getClientName());
}

@Test
public void save_clientSavedToTheDB_true(){
  Client myClient=new Client("Joan", 769, 1);
  myClient.save();
  assertTrue(Client.all().get(0).equals(myClient));
}

@Test
public void delete_deletesClient_true(){
  Client myClient=new Client("Cierra", 7778, 1);
  myClient.save();
  int myClientId=myClient.getId();
  myClient.delete();
  assertEquals(null, Client.find(myClientId));
}
}
