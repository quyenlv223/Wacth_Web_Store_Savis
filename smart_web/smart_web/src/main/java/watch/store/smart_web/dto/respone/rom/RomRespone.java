package watch.store.smart_web.dto.respone.rom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import watch.store.smart_web.dto.respone.product.ProductPropertyRespone;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RomRespone {
    private String id;
    private String name;
    private List<ProductPropertyRespone> productPropertyRespones;
}
