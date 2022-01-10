package Controllers;

public class CartConroller {

public CartConroller(java.sql.Connection con){


}
    public String updatePrice(double price,String total){
    double totalPrice = Double.parseDouble(total)+price;
        return String.valueOf(totalPrice);
    }
}
