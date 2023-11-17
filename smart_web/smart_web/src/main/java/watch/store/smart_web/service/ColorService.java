package watch.store.smart_web.service;

import watch.store.smart_web.dto.respone.color.ColorRespone;
import watch.store.smart_web.entity.ColorEntity;

import java.util.List;

public interface ColorService {

    String findById(Long id);

    List<ColorRespone> findAll();}
