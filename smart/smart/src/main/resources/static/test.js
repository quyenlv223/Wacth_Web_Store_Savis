function validateObjectVoucher(
    getElementVoucherCode
    , getElementVoucherName
    , getElementVoucherQuantity
    , getElementVoucherDiscount
    , getElementVoucherTypeDiscount
    , getElementVoucherMoneyMin
    , getElementVoucherStartDate
    , getElementVoucherEndDate
    , getElementVoucherCategory
    , getElementVoucherPersonApply
    , getElementTypeDiscountMoneyMin
    , getElementTypeDiscountPerson
) {
    let voucherCode, voucherName, voucherQuantity, voucherDiscount, voucherTypeDiscount, voucherMoneyMin,
        voucherStartDate, voucherEndDate, voucherCategory,
        voucherPersonApply, typeDiscountMoneyMin, typeDiscountPerson;
    // get mã giảm giá
    if (getElementVoucherCode !== null && getElementVoucherCode !== undefined) {
        voucherCode = getElementVoucherCode.value;
    }
    // get tên chương trình
    if (getElementVoucherName !== null && getElementVoucherName !== undefined) {
        voucherName = getElementVoucherName.value;
    }
    // get số lần sử dụng
    if (getElementVoucherQuantity !== null && getElementVoucherQuantity !== undefined) {
        voucherQuantity = getElementVoucherQuantity.value;
    }
    // get mệnh giá
    if (getElementVoucherDiscount !== null && getElementVoucherDiscount !== undefined) {
        voucherDiscount = getElementVoucherDiscount.value;
    }
    // get loại mệnh giá
    if (getElementVoucherTypeDiscount !== null && getElementVoucherTypeDiscount !== undefined) {
        voucherTypeDiscount = getElementVoucherTypeDiscount.innerHTML;
    }
    // get tiền hàng tối thiểu
    if (getElementVoucherMoneyMin !== null && getElementVoucherMoneyMin !== undefined) {
        voucherMoneyMin = getElementVoucherMoneyMin.value;
    }
    // get thời gian bắt đầu
    if (getElementVoucherStartDate !== null && getElementVoucherStartDate !== undefined) {
        voucherStartDate = getElementVoucherStartDate.value;
    }
    // get thời gian kết thúc
    if (getElementVoucherEndDate !== null && getElementVoucherEndDate !== undefined) {
        voucherEndDate = getElementVoucherEndDate.value;
    }
    // get mặt hàng áp dụng
    if (getElementVoucherCategory !== null && getElementVoucherCategory !== undefined) {
        voucherCategory = getElementVoucherCategory.value;
    }
    // get đối tượng áp dụng
    if (getElementVoucherPersonApply !== null && getElementVoucherPersonApply !== undefined) {
        voucherPersonApply = getElementVoucherPersonApply.value;
    }
    // get tiền đã mua tối thiểu của khách nhận mã giảm giá
    if (getElementTypeDiscountMoneyMin !== null && getElementTypeDiscountMoneyMin !== undefined) {
        typeDiscountMoneyMin = getElementTypeDiscountMoneyMin.value;
    }
    // get danh sách khách hàng nhận mã giảm giá
    if (getElementTypeDiscountPerson !== null && getElementTypeDiscountPerson !== undefined) {
        typeDiscountPerson = getSelectValues(getElementTypeDiscountPerson);
    }
    // validate mã giảm giá
    if (voucherCode === null || voucherCode === undefined || voucherCode === '') {
        console.log('Mã giảm giá không được bỏ trống')
        return null;
    }
    if (!/^[a-zA-Z0-9\-]+$/.test(voucherCode)) {
        console.log('Mã giảm giá không được chứa các ký tự đặc biệt.')
        return null;
    }
    if (voucherCode.length > 50) {
        console.log('Mã giảm giá không được quá 50 ký tự.')
        return null;
    }
    // validate tên chương trình
    if (voucherName === null || voucherName === undefined || voucherName === '') {
        console.log('Tên chương trình không được bỏ trống')
        return null;
    }
    if (voucherName.length > 255) {
        console.log('Tên chương trình quá dài')
        return null;
    }
    // validate số lần sử dụng
    if (voucherQuantity === null || voucherQuantity === undefined || voucherQuantity === '') {
        console.log('Số lần sử dụng không được bỏ trống')
        return null;
    }
    // validate mệnh giá
    if (voucherDiscount === null || voucherDiscount === undefined || voucherDiscount === '') {
        console.log('Mệnh giá không được bỏ trống')
        return null;
    }
    if (!/^[0-9]+$/.test(voucherDiscount)) {
        console.log('Mệnh giá không đúng')
        return null;
    }
    if (voucherTypeDiscount === null || voucherTypeDiscount === undefined) {
        voucherTypeDiscount = '%';
    }
    voucherTypeDiscount = voucherTypeDiscount.trim();
    if (voucherTypeDiscount === '%') {
        if (Number(voucherDiscount) < 0 || Number(voucherDiscount) > 100) {
            console.log('Tỉ lệ giảm không đúng (từ 0% - 100%)');
            return null;
        }
        if (Number(voucherDiscount) === 0) {
            console.log('Tỉ lệ giảm phải từ 0%');
            return null;
        }
    } else if (voucherTypeDiscount === 'đ') {
        if (Number(voucherDiscount) < 1) {
            console.log('Số tiền giảm không đúng (từ 0đ)');
            return null;
        }
    } else {
        console.log('Có lỗi khi nhập mệnh giá giảm');
        return null;
    }
    // validate số tiền hàng tối thiểu
    if (voucherMoneyMin === null || voucherMoneyMin === undefined || voucherMoneyMin === '') {
        console.log('Vui lòng nhập số tiền hàng mua tối thiểu')
        return null;
    }
    if (!/^[0-9]+$/.test(voucherMoneyMin)) {
        console.log('Số tiền hàng mua tối thiểu không đúng')
        return null;
    }
    if (Number(voucherMoneyMin) < 0) {
        console.log('Số tiền hàng mua tối thiểu phải từ 0đ')
        return null;
    }
    // validate ngày bắt đầu
    if (voucherStartDate === null || voucherStartDate === undefined || voucherStartDate === '') {
        console.log('Ngày bắt đầu không được bỏ trống')
        return null;
    }
    voucherStartDate = voucherStartDate.substring(0, voucherStartDate.indexOf(' '));
    if (!/^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/.test(voucherStartDate)) {
        console.log('Ngày bắt đầu không đúng (dd-MM-yyyy)')
        return null;
    }
    // validate ngày kêt thúc
    if (voucherEndDate === null || voucherEndDate === undefined || voucherEndDate === '') {
        console.log('Ngày kết thúc không được bỏ trống')
        return null;
    }
    voucherEndDate = voucherEndDate.substring(0, voucherEndDate.indexOf(' '));
    if (!/^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/.test(voucherEndDate)) {
        console.log('Ngày kết thúc không đúng (dd-MM-yyyy)')
        return null;
    }
    // validate ngày bát đầu và ngày kết thúc trùng nhau
    if (voucherStartDate === voucherEndDate) {
        console.log('Ngày bắt đầu và ngày kết thúc không thể trúng nhau')
        return null;
    }
    // validate loại sản phẩm áp dụng
    if (voucherCategory === null || voucherCategory === undefined) {
        console.log('Vui lòng chọn loại sản phẩm áp dụng')
        return null;
    }
    // validate đối tượng áp dụng
    if (voucherPersonApply === null || voucherPersonApply === undefined) {
        console.log('Vui lòng chọn đối tượng áp dụng')
        return null;
    }
    if (voucherPersonApply === 'PERSON-BUY-MONEY') {
        if (typeDiscountMoneyMin === null || typeDiscountMoneyMin === undefined || typeDiscountMoneyMin === '') {
            console.log('Vui lòng nhập số tiền đã mua tối thiểu của khác hàng áp dụng');
            return null;
        }
        if (!/^[0-9]+$/.test(typeDiscountMoneyMin)) {
            console.log('Số tiền đã mua tối thiểu của khách không đúng')
            return null;
        }
        if (Number(typeDiscountMoneyMin) < 0) {
            console.log('Số tiền đã mua tối thiểu của khách phải từ 0đ')
            return null;
        }
    } else if (voucherPersonApply === 'CHOOSE-PERSON') {
        if (typeDiscountPerson === null || typeDiscountPerson === undefined || typeDiscountPerson.length === 0) {
            console.log('Vui lòng chọn khách hàng nhận mã giảm giá');
            return null;
        }
        typeDiscountMoneyMin = null;
    } else {
        typeDiscountMoneyMin = null;
        typeDiscountPerson = null;
    }
    return {
        "voucherCode": voucherCode,
        "voucherName": voucherName,
        "quantity": voucherQuantity,
        "discount": voucherDiscount,
        "typeDiscount": voucherTypeDiscount,
        "moneyMin": voucherMoneyMin,
        "startDate": voucherStartDate,
        "endDate": voucherEndDate,
        "category": voucherCategory,
        "personApply": voucherPersonApply,
        "discountMoneyMin": typeDiscountMoneyMin,
        "discountPerson": typeDiscountPerson,
    };
}

function getSelectValues(select) {
    let result = [];
    let options = select && select.options;
    let opt;

    for (let i = 0, iLen = options.length; i < iLen; i++) {
        opt = options[i];
        if (opt.selected) {
            result.push(opt.value || opt.text);
        }
    }
    return result;
}
