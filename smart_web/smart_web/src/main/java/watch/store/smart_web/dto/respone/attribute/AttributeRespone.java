package watch.store.smart_web.dto.respone.attribute;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttributeRespone {
    private String operatingSystem; // he dieu hanh

    private String screen; // man hinh

    private String chip;

    private String camTruoc;
    private String camSau;

    private String pin;

    private String ram;
}
