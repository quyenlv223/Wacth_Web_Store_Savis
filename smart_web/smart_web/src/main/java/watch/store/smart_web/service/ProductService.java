package watch.store.smart_web.service;

import org.springframework.data.domain.Pageable;
import watch.store.smart_web.dto.respone.product.NewProductRespone;
import watch.store.smart_web.dto.respone.product.ProductRespone;

import java.util.List;

public interface ProductService {

    List<NewProductRespone> findAll(Pageable pageable);

    ProductRespone findById(Long id);

    List<NewProductRespone> findByCateId(Long id);

    List<NewProductRespone> findByAppleWatch();

    List<NewProductRespone> findBySamSung();

    List<NewProductRespone> findByXiaomi();

    List<NewProductRespone> findByHuawei();

    List<NewProductRespone> findByGarmin();

    List<NewProductRespone> findbyTop10();

    List<NewProductRespone> findByName(String name);

    List<NewProductRespone> findbyRandom();
    List<NewProductRespone> findbyTop3();


    List<NewProductRespone> findbycolor(Long  id , Long idcolor, Long gia1, Long gia2);

}
