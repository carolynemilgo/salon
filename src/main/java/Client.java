import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;


public class Client{
  private String client_name;
  private int client_number;
  private int id;
  private int stylistId;


public Client(String client_name, int client_number, int stylistId){
  this.client_name=client_name;
  this.client_number=client_number;
  this.stylistId=stylistId;

}
@Override
public boolean equals(Object otherClient){
  if(!(otherClient instanceof Client)){
    return false;
  }else{
    Client newClient=(Client) otherClient;
    return this.getClientName().equals(newClient.getClientName()) &&
           this.getId() == newClient.getId() &&
           this.getStylistId() == newClient.getStylistId() &&
           this.getClientNumber() == newClient.getClientNumber();
  }
}
public String getClientName(){
  return client_name;
}
public int getClientNumber(){
  return client_number;
  }
public int getId(){
  return id;
}
public int getStylistId(){
  return stylistId;
}
public static List<Client> all(){
  String sql="SELECT * FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
}
}
public void save(){
  try(Connection con=DB.sql2o.open()){
    String sql="INSERT INTO clients(client_name, client_number,stylistId) VALUES (:client_name, :client_number, :stylistId)";
    this.id= (int) con.createQuery(sql, true)
      .addParameter("client_name", this.client_name)
      .addParameter("client_number",this.client_number)
      .addParameter("stylistId", this.stylistId)
      .executeUpdate()
      .getKey();
  }
}
public static Client find(int id){
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM clients where id=:id";
    Client client=con.createQuery(sql)
    .addParameter("id",id)
    .executeAndFetchFirst(Client.class);
    return client;
}
}

public void update(String client_name, int client_number){
  try(Connection con=DB.sql2o.open()){
    String sql="UPDATE clients SET client_name= :client_name,client_number=:client_number WHERE id=:id";
    con.createQuery(sql)
    .addParameter("client_name", client_name)
    .addParameter("client_number", client_number)
    .addParameter("id", id)
    .executeUpdate();
  }
}

public void delete(){
  try(Connection con= DB.sql2o.open()){
    String sql="DELETE FROM clients WHERE id=:id";
    con.createQuery(sql)
    .addParameter("id",id)
    .executeUpdate();
  }
}

}
