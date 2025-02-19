package Model.TM;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartTM {

    private String itemCode;
    private String discription;

    private Integer qtyOnHand;
    private  Double unitPrice;
    private  Double total;
}
