package watch.store.smart_web.repo;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import watch.store.smart_web.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Long> {
    @Query("select o from ProductEntity o where o.deleteFlag = false  and o.status = 'ON' and o.id = ?1")
    Optional<ProductEntity> findByIdProduct(Long id);

    @Query("select o from ProductEntity o where o.deleteFlag = false  and o.status = 'ON' and o.categoryId = ?1")
    List<ProductEntity> findByCategoryId(Long id);

    @Query("select o from ProductEntity o where o.deleteFlag = false and o.status = 'ON' order by o.createDate desc ")
    List<ProductEntity> findByDeleteFlagIsFalseAndStatus();

    @Query("select o from ProductEntity o where o.deleteFlag = false  and o.status = 'ON' and o.categoryId = ?1")
    List<ProductEntity> findProductTop();

    @Query("select o from ProductEntity o where o.deleteFlag = false  and o.status = 'ON'")
    List<ProductEntity> findAll();

    @Query("select o from ProductEntity o where o.deleteFlag = false  and o.status = 'ON' and o.name like %?1%")
    List<ProductEntity> findByName(String name);

    @Query("select o from ProductEntity o where o.deleteFlag = false and o.status = 'ON' order by o.name desc")
    List<ProductEntity> findByRandom();


    @Query("select o from ProductEntity o where o.deleteFlag = false and o.status = 'ON' order by o.name desc")
    List<ProductEntity> findByRandomTop20(Pageable pageable);


}
