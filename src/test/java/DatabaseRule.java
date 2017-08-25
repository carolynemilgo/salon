import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "carol", "12345");
  }

  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
    //  String deleteTasksQuery = "DELETE FROM tasks *;";
      String deleteStylistsQuery = "DELETE FROM stylists *;";
    //  con.createQuery(deleteTasksQuery).executeUpdate();
      con.createQuery(deleteStylistsQuery).executeUpdate();
    }
  }

}
