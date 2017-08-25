import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

  // get("/", (request, response) -> {
  //     Map<String, Object> model = new HashMap<String, Object>();
  //   //  model.put("stylists", Stylist.all());
  //     model.put("template", "templates/index.vtl");
  //     return new ModelAndView(model, layout);
  //   }, new VelocityTemplateEngine());

    post("/stylists", (request, response) ->{
     Map<String, Object> model = new HashMap<String, Object>();
     String stylist_name=request.queryParams("stylistName");
     int stylist_number=Integer.parseInt(request.queryParams("stylistNumber"));
     Stylist newStylist= new Stylist(stylist_name, stylist_number);
     newStylist.save();
     model.put("template", "templates/success.vtl");
     return new ModelAndView(model, layout);
   }, new VelocityTemplateEngine());

   get("/", (request, response) -> {
       Map<String, Object> model = new HashMap<String, Object>();
       model.put("stylists", Stylist.all());
       model.put("template", "templates/index.vtl");
       return new ModelAndView(model, layout);
     }, new VelocityTemplateEngine());

    get("/clients", (request, response) ->{
      Map<String, Object> model= new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("clients", Client.all());
      model.put("template", "templates/add-client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/clients", (request,response) ->{
      Map<String, Object> model= new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
      String client_name=request.queryParams("clientName");
      int client_number=Integer.parseInt(request.queryParams("clientNumber"));
      Client newClient= new Client(client_name, client_number, stylist.getId());
      newClient.save();
      model.put("stylist", stylist);
      model.put("template", "templates/client-success.vtl");
      return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

    //   get("/stylists", (request,response) ->{
    //   Map<String, Object> model= new HashMap<String, Object>();
    //   //model.put("clients", Client.all());
    // //  model.put("stylists", Stylist.all());
    //   model.put("templates", "templates/stylists.vtl");
    //   return new ModelAndView(model, layout);
    //   }, new VelocityTemplateEngine());



      get("/stylists/:id", (request,response) ->{
        Map<String, Object> model = new HashMap<String, Object>();
        Stylist stylist=Stylist.find(Integer.parseInt(request.params(":id")));
        model.put("stylist", stylist);
        model.put("template", "templates/stylists.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      // get("/stylists/:stylistId/clients/:id", (request, response) ->{
      //   Map<String, Object> model= new HashMap<String, Object>();
      //   Stylist stylist=Stylist.find(Integer.parseInt(request.params(":stylistId")));
      //   Client client=Client.find(Integer.parseInt(request.params(":id")));
      //   model.put("stylist",stylist);
      //   model.put("client", client);
      //   model.put("template", "templates/clients.vtl");
      //   return new ModelAndView(model,layout);
      // },
      // new VelocityTemplateEngine());

      post("/stylists/:id/delete", (request,response) ->{
        Map<String, Object> model = new HashMap<String, Object>();
        //Task task = Task.find(Integer.parseInt(request.params("id")));
        Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
        stylist.delete();
        model.put("stylist", stylist);
        model.put("template", "templates/index.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      post("/stylists/:id/update", (request, response) ->{
        Map<String, Object> model=new HashMap<String, Object>();
        //Task task=Task.find(Integer.parseInt(request.params("id")));
        Stylist stylist=Stylist.find(Integer.parseInt(request.params(":id")));
        String stylist_name=request.queryParams("stylistName");
        int stylist_number=Integer.parseInt(request.queryParams("stylistNumber"));
        //Category category=Category.find(task.getCategoryId());
        stylist.update(stylist_name, stylist_number);
        //String url = String.format("/stylists/%d/", stylist.getId());
        response.redirect("/");
        return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());


        get("/client", (request, response) ->{
          Map<String, Object> model= new HashMap<String, Object>();
          model.put("clients", Client.all());
          model.put("template", "templates/client.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/clients/:id/delete", (request,response) ->{
          Map<String, Object> model= new HashMap<String, Object>();
           Client client = Client.find(Integer.parseInt(request.params("id")));
          client.delete();
          model.put("client", client);
          model.put("template", "templates/success.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

  }


}
