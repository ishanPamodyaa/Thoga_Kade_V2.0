package Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private String code;
    private  String description;
    private  Double unitPrice;
    private Integer qtyOnHand;

}
