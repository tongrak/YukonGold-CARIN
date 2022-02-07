package yukongold.carin.helloH2;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SimpleObjRepository extends CrudRepository<SimpleObj, Long> {
    List<SimpleObj> findByName(String Name);
    SimpleObj findById(long id);
}
