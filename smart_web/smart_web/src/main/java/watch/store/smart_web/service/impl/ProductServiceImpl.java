package watch.store.smart_web.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import watch.store.smart_web.dto.respone.product.NewProductRespone;
import watch.store.smart_web.dto.respone.product.ProductRespone;
import watch.store.smart_web.entity.CategoryEntity;
import watch.store.smart_web.entity.ProductEntity;
import watch.store.smart_web.entity.ProductDetailStatusEntity;
import watch.store.smart_web.repo.CategoryRepo;
import watch.store.smart_web.repo.ProductDetailStatusRepo;
import watch.store.smart_web.repo.ProductRepo;
import watch.store.smart_web.service.ProductService;
import watch.store.smart_web.util.ConvertUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ConvertUtil convertUtil;
    private final ProductRepo productRepo;

    private final CategoryRepo categoryRepo;

    private final ImageServiceImpl imageService;

    private final RomServiceImpl romService;

    private final AttributeServiceImpl attributeService;

    private final ProductDetailStatusRepo productDetailStatusRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<NewProductRespone> findAll(Pageable pageable) {
        List<NewProductRespone> listPro = new ArrayList<>();
        List<ProductEntity> list = productRepo.findByRandomTop20(pageable);
        int i = 0;
        for (ProductEntity product : list) {
            //boolean check = true;
            if(i < 10){
                NewProductRespone productRespone = new NewProductRespone();
                productRespone.setId(String.valueOf(product.getId()));
                productRespone.setName(product.getName());
                productRespone.setNote(product.getNote());
                productRespone.setImageKey(product.getImage_key());
                productRespone.setSrcImage(imageService.getAllImageByProduct(String.valueOf(productRespone.getId())));
                productRespone.setRomRespones(romService.findByProductId(Long.valueOf(productRespone.getId())));
                productRespone.setAttributeRespone(attributeService.findByProduct(Long.valueOf(productRespone.getId())));
                if(productRespone.getRomRespones() != null && productRespone.getRomRespones().size() > 0){
                    if(productRespone.getRomRespones().get(0).getProductPropertyRespones() != null && productRespone.getRomRespones().get(0).getProductPropertyRespones().get(0).getPriceString() != null){
                        listPro.add(productRespone);

                    }

                }
                i++;
            }
        }
        return listPro;
    }

    @Override
    public ProductRespone findById(Long id) {
        Optional<ProductEntity> productEntity = productRepo.findByIdProduct(id);
        if (productEntity.isPresent()) {
            ProductRespone respone = mapToEntity(productEntity.get());
            respone.setSrcImage(imageService.getAllImageByProduct(respone.getId())); // set list image cho product
            respone.setRomRespones(romService.findByProductId(Long.valueOf(respone.getId()))); // set list Rom cho product
            respone.setAttributeRespone(attributeService.findByProduct(productEntity.get().getId()));

            for (int i = 0; i < respone.getRomRespones().size(); i++) {
                if(respone.getRomRespones().get(i).getProductPropertyRespones().size() == 0){
                    respone.getRomRespones().remove(i);
                }
            }
            return respone;
        }
        log.error("Không tìm thấy product");
        return null;
    }

    @Override
    public List<NewProductRespone> findByCateId(Long id) {
        List<NewProductRespone> listPro = new ArrayList<>();
        Optional<CategoryEntity> categoryEntity = categoryRepo.findById(id);
        List<ProductEntity> list = productRepo.findByCategoryId(categoryEntity.get().getId());
        for (ProductEntity product : list) {
            NewProductRespone productRespone = new NewProductRespone();
            productRespone.setId(String.valueOf(product.getId()));
            productRespone.setName(product.getName());
            productRespone.setNote(product.getNote());
            productRespone.setImageKey(product.getImage_key());
            productRespone.setSrcImage(imageService.getAllImageByProduct(String.valueOf(productRespone.getId())));
            productRespone.setRomRespones(romService.findByProductId(Long.valueOf(productRespone.getId())));
            productRespone.setAttributeRespone(attributeService.findByProduct(Long.valueOf(productRespone.getId())));
            if(productRespone.getRomRespones() != null && productRespone.getRomRespones().size() > 0){
                if(productRespone.getRomRespones().get(0).getProductPropertyRespones() != null && productRespone.getRomRespones().get(0).getProductPropertyRespones().get(0).getPriceString() != null){
                    listPro.add(productRespone);
                }
            }
        }
        return listPro;
    }

    @Override
    public List<NewProductRespone> findByAppleWatch() {
        List<NewProductRespone> listPro = new ArrayList<>();
        List<ProductEntity> list = productRepo.findByCategoryId(1L);
        for (ProductEntity product : list) {
            //boolean check = true;
            NewProductRespone productRespone = new NewProductRespone();
            productRespone.setId(String.valueOf(product.getId()));
            productRespone.setName(product.getName());
            productRespone.setNote(product.getNote());
            productRespone.setImageKey(product.getImage_key());
            productRespone.setSrcImage(imageService.getAllImageByProduct(String.valueOf(productRespone.getId())));
            productRespone.setRomRespones(romService.findByProductId(Long.valueOf(productRespone.getId())));
            productRespone.setAttributeRespone(attributeService.findByProduct(Long.valueOf(productRespone.getId())));
            if(productRespone.getRomRespones() != null && productRespone.getRomRespones().size() > 0){
                if(productRespone.getRomRespones().get(0).getProductPropertyRespones() != null && productRespone.getRomRespones().get(0).getProductPropertyRespones().get(0).getPriceString() != null){
                    listPro.add(productRespone);

                }

            }
        }
        return listPro;
    }

    @Override
    public List<NewProductRespone> findBySamSung() {
        List<NewProductRespone> listPro = new ArrayList<>();
        List<ProductEntity> list = productRepo.findByCategoryId(2L);
        for (ProductEntity product : list) {
            //boolean check = true;
            NewProductRespone productRespone = new NewProductRespone();
            productRespone.setId(String.valueOf(product.getId()));
            productRespone.setName(product.getName());
            productRespone.setNote(product.getNote());
            productRespone.setImageKey(product.getImage_key());
            productRespone.setSrcImage(imageService.getAllImageByProduct(String.valueOf(productRespone.getId())));
            productRespone.setRomRespones(romService.findByProductId(Long.valueOf(productRespone.getId())));
            productRespone.setAttributeRespone(attributeService.findByProduct(Long.valueOf(productRespone.getId())));
            if(productRespone.getRomRespones() != null && productRespone.getRomRespones().size() > 0){
                if(productRespone.getRomRespones().get(0).getProductPropertyRespones() != null && productRespone.getRomRespones().get(0).getProductPropertyRespones().get(0).getPriceString() != null){
                    listPro.add(productRespone);

                }

            }
        }
        return listPro;
    }

    @Override
    public List<NewProductRespone> findByXiaomi() {
        List<NewProductRespone> listPro = new ArrayList<>();
        List<ProductEntity> list = productRepo.findByCategoryId(4L);
        for (ProductEntity product : list) {
            //boolean check = true;
            NewProductRespone productRespone = new NewProductRespone();
            productRespone.setId(String.valueOf(product.getId()));
            productRespone.setName(product.getName());
            productRespone.setNote(product.getNote());
            productRespone.setImageKey(product.getImage_key());
            productRespone.setSrcImage(imageService.getAllImageByProduct(String.valueOf(productRespone.getId())));
            productRespone.setRomRespones(romService.findByProductId(Long.valueOf(productRespone.getId())));
            productRespone.setAttributeRespone(attributeService.findByProduct(Long.valueOf(productRespone.getId())));
            if(productRespone.getRomRespones() != null && productRespone.getRomRespones().size() > 0){
                if(productRespone.getRomRespones().get(0).getProductPropertyRespones() != null && productRespone.getRomRespones().get(0).getProductPropertyRespones().get(0).getPriceString() != null){
                    listPro.add(productRespone);

                }

            }
        }
        return listPro;
    }

    @Override
    public List<NewProductRespone> findByHuawei() {
        List<NewProductRespone> listPro = new ArrayList<>();
        List<ProductEntity> list = productRepo.findByCategoryId(3L);
        for (ProductEntity product : list) {
            //boolean check = true;
            NewProductRespone productRespone = new NewProductRespone();
            productRespone.setId(String.valueOf(product.getId()));
            productRespone.setName(product.getName());
            productRespone.setNote(product.getNote());
            productRespone.setImageKey(product.getImage_key());
            productRespone.setSrcImage(imageService.getAllImageByProduct(String.valueOf(productRespone.getId())));
            productRespone.setRomRespones(romService.findByProductId(Long.valueOf(productRespone.getId())));
            productRespone.setAttributeRespone(attributeService.findByProduct(Long.valueOf(productRespone.getId())));
            if(productRespone.getRomRespones() != null && productRespone.getRomRespones().size() > 0){
                if(productRespone.getRomRespones().get(0).getProductPropertyRespones() != null && productRespone.getRomRespones().get(0).getProductPropertyRespones().get(0).getPriceString() != null){
                    listPro.add(productRespone);

                }

            }
        }
        return listPro;
    }

    @Override
    public List<NewProductRespone> findByGarmin() {
        List<NewProductRespone> listPro = new ArrayList<>();
        List<ProductEntity> list = productRepo.findByCategoryId(5L);
        for (ProductEntity product : list) {
            //boolean check = true;
            NewProductRespone productRespone = new NewProductRespone();
            productRespone.setId(String.valueOf(product.getId()));
            productRespone.setName(product.getName());
            productRespone.setNote(product.getNote());
            productRespone.setImageKey(product.getImage_key());
            productRespone.setSrcImage(imageService.getAllImageByProduct(String.valueOf(productRespone.getId())));
            productRespone.setRomRespones(romService.findByProductId(Long.valueOf(productRespone.getId())));
            productRespone.setAttributeRespone(attributeService.findByProduct(Long.valueOf(productRespone.getId())));
            if(productRespone.getRomRespones() != null && productRespone.getRomRespones().size() > 0){
                if(productRespone.getRomRespones().get(0).getProductPropertyRespones() != null && productRespone.getRomRespones().get(0).getProductPropertyRespones().get(0).getPriceString() != null){
                    listPro.add(productRespone);

                }

            }
        }
        return listPro;
    }

    @Override
    public List<NewProductRespone> findbyTop10() {
        List<NewProductRespone> listPro = new ArrayList<>();
        List<ProductEntity> list = productRepo.findByDeleteFlagIsFalseAndStatus();
        int i = 0;
        for (ProductEntity product : list) {
            //boolean check = true;
            if(i < 10){
                NewProductRespone productRespone = new NewProductRespone();
                productRespone.setId(String.valueOf(product.getId()));
                productRespone.setName(product.getName());
                productRespone.setNote(product.getNote());
                productRespone.setImageKey(product.getImage_key());
                productRespone.setSrcImage(imageService.getAllImageByProduct(String.valueOf(productRespone.getId())));
                productRespone.setRomRespones(romService.findByProductId(Long.valueOf(productRespone.getId())));
                productRespone.setAttributeRespone(attributeService.findByProduct(Long.valueOf(productRespone.getId())));
                if(productRespone.getRomRespones() != null && productRespone.getRomRespones().size() > 0){
                    if(productRespone.getRomRespones().get(0).getProductPropertyRespones() != null && productRespone.getRomRespones().get(0).getProductPropertyRespones().get(0).getPriceString() != null){
                        listPro.add(productRespone);

                    }

                }
                i++;
            }
        }
        return listPro;
    }

    @Override
    public List<NewProductRespone> findByName(String name) {
        List<NewProductRespone> listPro = new ArrayList<>();
        List<ProductEntity> list = productRepo.findByName("%" + name + "%");
        for (ProductEntity product : list) {
            boolean check = true;
            NewProductRespone productRespone = new NewProductRespone();
            productRespone.setId(String.valueOf(product.getId()));
            productRespone.setName(product.getName());
            productRespone.setNote(product.getNote());
            productRespone.setImageKey(product.getImage_key());
            productRespone.setSrcImage(imageService.getAllImageByProduct(String.valueOf(productRespone.getId())));
            productRespone.setRomRespones(romService.findByProductId(Long.valueOf(productRespone.getId())));
            productRespone.setAttributeRespone(attributeService.findByProduct(Long.valueOf(productRespone.getId())));
            int size = productRespone.getRomRespones().size();
            for (int i = 0; i < size; i++) {
                List<ProductDetailStatusEntity> productPropertyEntities = productDetailStatusRepo.findByRomId(Long.parseLong(productRespone.getRomRespones().get(i).getId()));
                if(productPropertyEntities == null || productPropertyEntities.size() == 0){
                    check = false;
                    productRespone.getRomRespones().remove(i);
                    size--;
                    continue;
                }else {
                    for (ProductDetailStatusEntity listProductProperty : productPropertyEntities) {
                        productRespone.setPrice(convertUtil.moneyToStringFormat(listProductProperty.getPrice()));
                        productRespone.setPricePromotion(listProductProperty.getPricePromotion());
                    }
                }
            }
            if(check){
                listPro.add(productRespone);
            }
        }
        return listPro;
    }

    @Override
    public List<NewProductRespone> findbyRandom() {
        List<NewProductRespone> listPro = new ArrayList<>();
        List<ProductEntity> list = productRepo.findByRandom();
        int i = 0;
        for (ProductEntity product : list) {
            //boolean check = true;
            if(i < 10){
                NewProductRespone productRespone = new NewProductRespone();
                productRespone.setId(String.valueOf(product.getId()));
                productRespone.setName(product.getName());
                productRespone.setNote(product.getNote());
                productRespone.setImageKey(product.getImage_key());
                productRespone.setSrcImage(imageService.getAllImageByProduct(String.valueOf(productRespone.getId())));
                productRespone.setRomRespones(romService.findByProductId(Long.valueOf(productRespone.getId())));
                productRespone.setAttributeRespone(attributeService.findByProduct(Long.valueOf(productRespone.getId())));
                if(productRespone.getRomRespones() != null && productRespone.getRomRespones().size() > 0){
                    if(productRespone.getRomRespones().get(0).getProductPropertyRespones() != null && productRespone.getRomRespones().get(0).getProductPropertyRespones().get(0).getPriceString() != null){
                        listPro.add(productRespone);

                    }

                }
                i++;
            }
        }
        return listPro;
    }

    @Override
    public List<NewProductRespone> findbyTop3() {
        List<NewProductRespone> listPro = new ArrayList<>();
        List<ProductEntity> list = productRepo.findByDeleteFlagIsFalseAndStatus();
        int i = 0;
        for (ProductEntity product : list) {
            //boolean check = true;
            if(i < 3){
                NewProductRespone productRespone = new NewProductRespone();
                productRespone.setId(String.valueOf(product.getId()));
                productRespone.setName(product.getName());
                productRespone.setNote(product.getNote());
                productRespone.setImageKey(product.getImage_key());
                productRespone.setSrcImage(imageService.getAllImageByProduct(String.valueOf(productRespone.getId())));
                productRespone.setRomRespones(romService.findByProductId(Long.valueOf(productRespone.getId())));
                productRespone.setAttributeRespone(attributeService.findByProduct(Long.valueOf(productRespone.getId())));
                if(productRespone.getRomRespones() != null && productRespone.getRomRespones().size() > 0){
                    if(productRespone.getRomRespones().get(0).getProductPropertyRespones() != null && productRespone.getRomRespones().get(0).getProductPropertyRespones().get(0).getPriceString() != null){
                        listPro.add(productRespone);

                    }

                }
                i++;
            }
        }
        return listPro;
    }

    @Override
    public List<NewProductRespone> findbycolor(Long id, Long idcolor, Long gia1 , Long gia2) {
        List<NewProductRespone> list = new ArrayList<>();
        try {

            List<ProductEntity> list2 = new ArrayList<>();
            String  sql = " select  p.ID, p.NAME, p.NOTE, p.STATUS, p.IMAGE_KEY, p.DELETE_FLAG , c.ID, r.ID , pd.ID , pd.PRICE, pd.COLOR_ID\n" +
                    "from  product p \n" +
                    "join  category c on  p.CATEGORY_ID =  c.ID\n" +
                    "join  rom r  on r.PRODUCT_ID =  p.ID\n" +
                    "join product_detail_status  pd on pd.ROM_ID =  r.ID\n" +
                    "where c.ID = " +id ;
            sql =  idcolor != null  ? sql +"  and pd.COLOR_ID=" +idcolor  : sql + "";
             String sql2 = gia1 != null && gia2 !=null ? sql + "pd.PRICE between pd.PRICE=" + gia1 +"pd.PRICE=" + gia2 :sql +"";
            System.out.println(sql);
            jdbcTemplate.query(sql2, rs -> {
                NewProductRespone productRespone = new NewProductRespone();
                productRespone.setId(String.valueOf(rs.getLong("ID")));
                productRespone.setName(rs.getString("NAME"));
                productRespone.setNote(rs.getString("NOTE"));
                productRespone.setImageKey(rs.getString("IMAGE_KEY"));
                productRespone.setSrcImage(imageService.getAllImageByProduct(String.valueOf(productRespone.getId())));
                productRespone.setRomRespones(romService.findByProductId(Long.valueOf(productRespone.getId())));
                productRespone.setAttributeRespone(attributeService.findByProduct(Long.valueOf(productRespone.getId())));
                if(productRespone.getRomRespones() != null && productRespone.getRomRespones().size() > 0){
                    if(productRespone.getRomRespones().get(0).getProductPropertyRespones() != null && productRespone.getRomRespones().get(0).getProductPropertyRespones().get(0).getPriceString() != null){
                        list.add(productRespone);

                    }

                }
            });
//            for (ProductEntity product : list2) {
                //boolean check = true;

//            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list ;
    }

    private ProductRespone mapToEntity(ProductEntity entity) {
        ProductRespone respone = new ProductRespone();
        respone.setId(String.valueOf(entity.getId()));
        respone.setName(entity.getName());
        respone.setNote(entity.getNote());
        respone.setImageKey(entity.getImage_key());
        return respone;
    }
}
