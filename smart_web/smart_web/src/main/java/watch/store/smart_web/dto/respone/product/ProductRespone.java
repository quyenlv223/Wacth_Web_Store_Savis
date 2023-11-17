package watch.store.smart_web.dto.respone.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import watch.store.smart_web.dto.respone.attribute.AttributeRespone;
import watch.store.smart_web.dto.respone.rom.RomRespone;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRespone {
    private String id;
    private String name;
    private String imageKey;
    private String status;
    private String note;
    private List<String> srcImage;
    private List<RomRespone> romRespones;
    private AttributeRespone attributeRespone;
}
