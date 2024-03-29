package com.project;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/*

Help: https://www.baeldung.com/hibernate-one-to-many

*/

public class Main {

   public static void main(String[] args) {
      
      String basePath = System.getProperty("user.dir") + "/data/";

      // Crear la carpeta 'data' si no existeix
      File dir = new File(basePath);
      if (!dir.exists()){
         if(!dir.mkdirs()) {
               System.out.println("Error en la creació de la carpeta 'data'");
         }
      }

      Manager.createSessionFactory();

      Cart refCart1 = Manager.addCart("Cart 1");
      Cart refCart2 = Manager.addCart("Cart 2");
      Cart refCart3 = Manager.addCart("Cart 3");

      Item refItem1 = Manager.addItem("Item 1");
      Item refItem2 = Manager.addItem("Item 2");
      Item refItem3 = Manager.addItem("Item 3");
      Item refItem4 = Manager.addItem("Item 4");
      Item refItem5 = Manager.addItem("Item 5");
      Item refItem6 = Manager.addItem("Item 6");

      Set<Item> itemsCard1 = new HashSet<Item>();
      itemsCard1.add(refItem1);
      itemsCard1.add(refItem2);
      itemsCard1.add(refItem3);

      Manager.updateCart(refCart1.getCartId(), refCart1.getType(), itemsCard1);

      Set<Item> itemsCard2 = new HashSet<Item>();
      itemsCard2.add(refItem4);
      itemsCard2.add(refItem5);

      Manager.updateCart(refCart2.getCartId(), refCart2.getType(), itemsCard2);

      Manager.delete(Cart.class, refCart3.getCartId());
      Manager.delete(Item.class, refItem6.getItemId());

      System.out.println(Manager.collectionToString(Cart.class, Manager.listCollection(Cart.class, "")));
      System.out.println(Manager.collectionToString(Item.class, Manager.listCollection(Item.class, "")));

      Manager.close();
   }
}