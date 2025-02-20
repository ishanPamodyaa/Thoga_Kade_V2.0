package Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDetail {
    private String orderId;
    private String itemCode;
    private  Integer qty;
    private Double unitPrice;

//    public OrderDetail(String orderId, String itemCode, String discription, Integer qtyOnHand, Double unitPrice) {
//    }
}
