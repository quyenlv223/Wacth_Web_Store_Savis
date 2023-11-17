package watch.store.smart_web.service;


import watch.store.smart_web.dto.respone.voucher.VoucherRespone;

public interface VoucherService {
    VoucherRespone findByCode(String code);
}
