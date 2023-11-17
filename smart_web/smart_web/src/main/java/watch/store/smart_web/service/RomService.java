package watch.store.smart_web.service;


import watch.store.smart_web.dto.respone.rom.RomRespone;

import java.util.List;

public interface RomService {
    List<RomRespone> findByProductId(Long id);
}
