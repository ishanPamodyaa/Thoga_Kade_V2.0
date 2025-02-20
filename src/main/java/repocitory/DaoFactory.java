package repocitory;

import Model.Customer;
import Service.SuperService;
import Utill.DaoType;
import repocitory.custom.CustomerDao;
import repocitory.custom.impl.CustomerDaoImpl;
import repocitory.custom.impl.ItemDaoImpl;

import java.util.ArrayList;

public class DaoFactory {
    private  static  DaoFactory instance;
   private DaoFactory (){

   }

   public static DaoFactory getInstance(){

       return instance==null ? instance = new DaoFactory() : instance;
   }

   public <T extends SuperDao> T getDaoType(DaoType type){

       switch (type){
           case CUSTOMER:return  (T)new CustomerDaoImpl();
           case ITEM:return  (T)new ItemDaoImpl();
//           case ORDER:return (T)new

       }
       return null;
   }


}
