package oose.dea.services.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;


@Path("/items")
@Singleton
public class ItemResource {

    private List<Item> items = new ArrayList<Item>();

    public ItemResource()
    {
        items.add(new Item("Bread", "Grains", "Grain product"));
        items.add(new Item("Butter", "Dairy", "Dairy product"));
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTextItems(){
        return "bread, butter";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Item> getJsonItems()  {
        //return "["bread", "butter"]";
        return items;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{sku}")
    public Item getJsonItem(@PathParam("sku") final String sku) {
        return items.stream().filter((item) -> item.getSku().equals(sku)).findAny().get();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addItem(Item item){
        items.add(item);
        System.out.println(item.getSku() + " has been added");
    }

    @DELETE
    @Path("/{sku}")
    public void deleteItem(@PathParam("sku") final String sku){
        items.removeIf(item -> item.getSku().equals(sku));
        System.out.println("Deletion has occurred");
    }
}
