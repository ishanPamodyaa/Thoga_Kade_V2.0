package Service;

import Service.custom.impl.CustomerBoImpl;
import Service.custom.impl.ItemBoImpl;
import Service.custom.impl.OrderBoImpl;
import Utill.BoType;

public class BoFactory {

    private  static  BoFactory instance;

    private BoFactory () {

    }

    public static BoFactory getInstance(){

        return instance==null? instance = new BoFactory(): instance;
    }

    public <T extends SuperService> T getBoType(BoType type){

        switch (type){
            case CUSTOMER: return (T)new CustomerBoImpl();
            case ITEM: return  (T)new ItemBoImpl();
            case  ORDER: return  (T)new OrderBoImpl();
        }

        return null;
    }
}


