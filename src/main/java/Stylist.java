import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import org.sql2o.*;



public class Stylist{
  private String stylist_name;
  private int stylist_number;
  private int id;


public Stylist(String stylist_name, int stylist_number){
  this.stylist_name=stylist_name;
  this.stylist_number=stylist_number;
}

@Override
public boolean equals(Object otherStylist){
  if(!(otherStylist instanceof Stylist)){
    return false;
  }else{
    Stylist myStylist=(Stylist) otherStylist;
    return this.getName().equals(myStylist.getName())&&
          this.getNumber() == myStylist.getNumber() &&
          this.getId() ==myStylist.getId();
  }
}

public static List<Stylist> all(){
  String sql="SELECT * FROM stylists";
  try(Connection con=DB.sql2o.open()){
    return con.createQuery(sql).executeAndFetch(Stylist.class);
  }
}

public String getName(){
  return stylist_name;
}

public int getNumber(){
  return stylist_number;
}

public int getId(){
  return id;
}

public void save(){
  try(Connection con=DB.sql2o.open()){
    String sql="INSERT INTO stylists (stylist_name,stylist_number) VALUES (:stylist_name,:stylist_number)";
    //con.createQuery(sql)
    this.id=(int) con.createQuery(sql, true)
    .addParameter("stylist_name", this.stylist_name)
    .addParameter("stylist_number", this.stylist_number)
    .executeUpdate()
    .getKey();
  }
}

public static Stylist find(int id) {
try(Connection con=DB.sql2o.open()){
  String sql="SELECT * FROM stylists where id=:id";
  Stylist stylist=con.createQuery(sql)
  .addParameter("id",id)
  .executeAndFetchFirst(Stylist.class);
  return stylist;
}
}

public List<Client> getClients(){
try(Connection con=DB.sql2o.open()){
  String sql="SELECT * FROM clients WHERE stylistid=:id";
  return con.createQuery(sql)
  .addParameter("id", this.id)
  .executeAndFetch(Client.class);
}
}

public void update(String stylist_name, int stylist_number){
  try(Connection con=DB.sql2o.open()){
    String sql="UPDATE stylists SET stylist_name= :stylist_name,stylist_number=:stylist_number WHERE id=:id";
    con.createQuery(sql)
    .addParameter("stylist_name", stylist_name)
    .addParameter("stylist_number", stylist_number)
    .addParameter("id", id)
    .executeUpdate();
  }
}
public void delete(){
  try(Connection con= DB.sql2o.open()){
    String sql="DELETE FROM stylists WHERE id=:id";
    con.createQuery(sql)
    .addParameter("id",id)
    .executeUpdate();
  }
}

}
