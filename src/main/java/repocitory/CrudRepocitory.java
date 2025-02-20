package repocitory;

import java.util.List;

public interface CrudRepocitory <T,ID> extends SuperDao{

    boolean save (T  entity);
    boolean update (ID id, T entity);

    T search(ID id );

    boolean delete (ID id);

    List <T> getAll();
}
