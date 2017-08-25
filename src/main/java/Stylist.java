import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import org.sql2o.*;



public class Stylist{
  private String stylistName;
  private int stylistNumber;
  private int id;


public Stylist(String stylistName, int stylistNumber){
  this.stylistName=stylistName;
  this.stylistNumber=stylistNumber;
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
  return stylistName;
}

public int getNumber(){
  return stylistNumber;
}

public int getId(){
  return id;
}

public void save(){
  try(Connection con=DB.sql2o.open()){
    String sql="INSERT INTO stylists (stylistName,stylistNumber) VALUES (:stylistName,:stylistNumber)";
    //con.createQuery(sql)
    this.id=(int) con.createQuery(sql, true)
    .addParameter("stylistName", this.stylistName)
    .addParameter("stylistNumber", this.stylistNumber)
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

public List<Stylist> getStylists(){
//return mTasks;
try(Connection con=DB.sql2o.open()){
  String sql="SELECT * FROM stylists WHERE stylistId=:id";
  return con.createQuery(sql)
  .addParameter("id", this.id)
  .executeAndFetch(Stylist.class);
}
}


}
