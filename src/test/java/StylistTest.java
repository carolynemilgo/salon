
import org.junit.*;
import org.sql2o.*;
import static org.junit.Assert.*;


public class StylistTest{

//   @Before
//   public void setUp(){
//     DB.sql2o=new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "carol", "12345");
//   }
//
//
//   @After
// public void tearDown(){
//   try(Connection con=DB.sql2o.open()){
//     //String deleteTasksQuery="DELETE FROM tasks *;";
//     String deleteStylistsQuery="DELETE FROM stylists *;";
//   //  con.createQuery(deleteTasksQuery).executeUpdate();
//     con.createQuery(deleteStylistsQuery).executeUpdate();
//   }
// }
@Rule
 public DatabaseRule database = new DatabaseRule();

  @Test
  public void classInstantiatesCorrectly_true(){
    Stylist myStylist=new Stylist("Jane", 456);
    assertEquals(true, myStylist instanceof Stylist);
  }
  @Test
  public void Jane(){
    Stylist myStylist=new Stylist("Jane", 556);
    assertEquals("Jane", myStylist.getName());
  }
  @Test
  public void stylistInstantiatesWithPhoneNumber_0723456(){
    Stylist myStylist=new Stylist("Jane", 456);
    assertEquals(456, myStylist.getNumber());
  }
  @Test
  public void find_returnsNullWhenNoTaskIsFound(){
    assertTrue(Stylist.find(999) == null);
  }
@Test
  public void save_savesIntoDatabase_true(){
    Stylist myStylist=new Stylist("Joan", 769);
    myStylist.save();
    assertTrue(Stylist.all().get(0).equals(myStylist));
  }

@Test
    public void returnsAllInstancesOfStylist_true(){
    Stylist firstStylist=new Stylist("Jose", 766);
    firstStylist.save();
    Stylist secondStylist=new Stylist("Dee", 56745);
    secondStylist.save();
    assertEquals(true, Stylist.all().get(0).equals(firstStylist));
    assertEquals(true, Stylist.all().get(1).equals(secondStylist));
  }
@Test
    public void save_assignsCorrectIdToObject(){
    Stylist myStylist= new Stylist("Jenny", 987);
    myStylist.save();
    Stylist savedStylist= Stylist.all().get(0);
    assertEquals(myStylist.getId(), savedStylist.getId());
  }

}
