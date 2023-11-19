window.onload = function () {
    $('.modal').hide();
    let hiddenInfoVoucher = $('.hidden-info-voucher');
    if (hiddenInfoVoucher !== null) {
        for (let i = 0; i < hiddenInfoVoucher.length; i++) {
            hiddenInfoVoucher[i].hidden = 'true'
        }
    }
    let getSelectPersonApply = document.getElementsByClassName('ip-voucher-person-apply')
    if (getSelectPersonApply !== null && getSelectPersonApply !== undefined) {
        for (let index = 0; index < getSelectPersonApply.length; index++) {
            onChangePersonApply(getSelectPersonApply[index])
        }
    }
    loadUsedByVoucherId();
}

function loadUsedByVoucherId() {
    $('.voucher-used').each(function () {
        $.ajax({
            url: '/api/voucher/' + $(this).attr('data-id') + '/used',
            success: function (data) {
                let html = '';
                for (let i = 0; i < data.length; i++) {
                    html += '<tr>\n' +
                        '    <td class="text-center">' + (i + 1) + '</td>\n' +
                        '    <td>' + data[i].fullName + '</td>\n' +
                        '    <td class="text-end">' + data[i].totalMoney + '</td>\n' +
                        '    <td>' + data[i].dateUsed + '</td>\n' +
                        '</tr>'
                }
                $('.voucher-used-content').html(html);
            }
        })
        // console.log($(this).children('.voucher-used-content'))
    });
}

function onStatusChange(element) {
    const value = $(element).val();
    const tagAll = $('#datatable tbody tr');
    const tag = $('tbody tr td span').filter(function () {
        return $(this).text().includes(value);
    });
    if (value === '') {
        tagAll.show();
    } else {
        tagAll.hide();
        tag.parent().parent().show();
    }
}

function onClickSaveVoucher() {
    // get element mã giảm giá
    let getElementVoucherCode = document.getElementById('ip-voucher-code');
    // get element tên chương trình
    let getElementVoucherName = document.getElementById('ip-voucher-name');
    // get element số lần sử dụng
    let getElementVoucherQuantity = document.getElementById('ip-voucher-quantity');
    // get element mệnh giá
    let getElementVoucherDiscount = document.getElementById('ip-voucher-discount');
    // get element loại mệnh giá
    let getElementVoucherTypeDiscount = document.getElementById('ip-voucher-type-discount');
    // get element số tiền hàng tối thiểu
    let getElementVoucherMoneyMin = document.getElementById('ip-voucher-money-min');
    // get element ngày bắt đầu
    let getElementVoucherStartDate = document.getElementById('ip-voucher-start-date');
    // get element ngày kết thúc
    let getElementVoucherEndDate = document.getElementById('ip-voucher-end-date');
    // get element mặt hàng áp dụng
    let getElementVoucherCategory = document.getElementById('ip-voucher-category');
    // get element đối tượng áp dụng
    let getElementVoucherPersonApply = document.getElementById('ip-voucher-person-apply');
    // get element số tiền giảm đã mua tối thiểu
    let getElementTypeDiscountMoneyMin = document.getElementById('ip-type-discount-money-min');
    // get element khách hàng nhận mã giảm giá
    let getElementTypeDiscountPerson = document.getElementById('ip-type-discount-person');
    // get element mô tả chi tiết mã giảm giá
    let getElementVoucherDescription = document.getElementById('ip-voucher-description');
    let getElementVoucherPromo = document.getElementById('ip-voucher-promo');
    let objVoucher = validateObjectVoucher(
        null,
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
        , getElementVoucherDescription
        , getElementVoucherPromo
    );
    console.log(objVoucher);
    if (objVoucher !== null && objVoucher !== undefined) {
        $.ajax({
            url: '/api/voucher',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(objVoucher),
            success: function (data) {
                toastSuccess('Thành công', 'Đã tạo mã giảm giá')
                setTimeout(function () {
                    location.reload()
                }, 2000)
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR);
                toastDanger('Lỗi', jqXHR.responseJSON.vn);
            }
        })
    }
}

function onClickUpdateVoucher(e) {
    let id = e.dataset.id;
    console.log(id)
    // get element mã giảm giá
    let getElementVoucherCode = document.querySelector('.ip-voucher-code' + id);
    // get element tên chương trình
    let getElementVoucherName = document.querySelector('.ip-voucher-name' + id);
    // get element số lần sử dụng
    let getElementVoucherQuantity = document.querySelector('.ip-voucher-quantity' + id);
    // get element mệnh giá
    let getElementVoucherDiscount = document.querySelector('.ip-voucher-discount' + id);
    // get element loại mệnh giá
    let getElementVoucherTypeDiscount = document.querySelector('.ip-voucher-type-discount' + id);
    // get element số tiền hàng tối thiểu
    let getElementVoucherMoneyMin = document.querySelector('.ip-voucher-money-min' + id);
    // get element ngày bắt đầu
    let getElementVoucherStartDate = document.querySelector('.ip-voucher-start-date' + id);
    // get element ngày kết thúc
    let getElementVoucherEndDate = document.querySelector('.ip-voucher-end-date' + id);
    // get element mặt hàng áp dụng
    let getElementVoucherCategory = document.querySelector('.ip-voucher-category' + id);
    // get element đối tượng áp dụng
    let getElementVoucherPersonApply = document.querySelector('.ip-voucher-person-apply' + id);
    // get element số tiền giảm đã mua tối thiểu
    let getElementTypeDiscountMoneyMin = document.querySelector('.ip-type-discount-money-min' + id);
    // get element khách hàng nhận mã giảm giá
    let getElementTypeDiscountPerson = document.querySelector('.ip-type-discount-person' + id);
    // get element mô tả chi tiết mã giảm giá
    let getElementVoucherDescription = document.querySelector('.ip-voucher-description' + id);
    let getElementVoucherPromo = document.querySelector('.ip-voucher-promo' + id);
    // console.log(getElementVoucherTypeDiscount.innerHTML)
    let objVoucher = validateObjectVoucher(
        id,
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
        , getElementVoucherDescription
        , getElementVoucherPromo
    );
    console.log(objVoucher);
    if (objVoucher !== null && objVoucher !== undefined) {
        $.ajax({
            url: '/api/voucher',
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(objVoucher),
            success: function (data) {
                toastSuccess('Thành công', 'Đã cập nhật mã giảm giá')
                setTimeout(function () {
                    location.reload()
                }, 2000)
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR);

                toastDanger('Lỗi', jqXHR.responseJSON.vn);
            }
        })
    }
}

function onClickActiveVoucher(e) {
    let id = e.dataset.id
    if (id !== null && id !== undefined && id !== '') {
        $.ajax({
            url: '/api/voucher/active/' + id,
            method: 'PUT',
            contentType: 'application/json',
            success: function (data) {
                toastSuccess('Thành công', 'Đã kích hoạt mã giảm giá')
                setTimeout(function () {
                    location.reload()
                }, 2000)
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(jqXHR);
                toastDanger('Lỗi', jqXHR.responseJSON.vn);
            }
        })
    }
}

function onClickDeleteVoucher(e) {
    let id = e.dataset.id
    if (id !== null && id !== undefined && id !== '') {
        // sweetConfirm('Xác nhận', 'Xoá mã giảm giá?', 'Xoá', function () {
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success mx-2',
                cancelButton: 'btn btn-danger mx-2'
            },
            buttonsStyling: false
        })

        swalWithBootstrapButtons.fire({
            title: 'Cảnh báo',
            text: `Bạn có chắc muốn xoá mã giảm giá ${e.dataset.code}?`,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Xác nhận',
            cancelButtonText: 'Huỷ',
            reverseButtons: true
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    url: '/api/voucher/' + id,
                    method: 'DELETE',
                    contentType: 'application/json',
                    success: function (data) {
                        toastSuccess('Thành công', 'Đã xoá mã giảm giá')
                        setTimeout(function () {
                            location.reload()
                        }, 2000)
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log(jqXHR);
                        toastDanger('Lỗi', jqXHR.responseJSON.vn);
                    }
                })
            } else if (
                result.dismiss === Swal.DismissReason.cancel
            ) {
                swalWithBootstrapButtons.fire(
                    'Đã huỷ tác vụ',
                    'Dữ liệu được bảo toàn',
                    'error'
                )
            }
        })

        // })
    }
}

function validateObjectVoucher(
    id,
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
    , getElementVoucherDescription
    , getElementVoucherPromo
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
        console.log(getElementVoucherTypeDiscount.innerHTML)
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
        toastDanger('Lỗi', 'Vui lòng nhập mã giảm giá')
        return null;
    }
    if (!/^[a-zA-Z0-9\-]+$/.test(voucherCode)) {
        console.log('Mã giảm giá không được chứa các ký tự đặc biệt.')
        toastDanger('Lỗi', 'Mã giảm giá không được chứa các ký tự đặc biệt')
        return null;
    }
    if (voucherCode.length > 50) {
        console.log('Mã giảm giá không được quá 50 ký tự.')
        toastDanger('Lỗi', 'Mã giảm giá không được quá 50 ký tự')
        return null;
    }
    // validate tên chương trình
    if (voucherName === null || voucherName === undefined || voucherName === '') {
        console.log('Tên chương trình không được bỏ trống')
        toastDanger('Lỗi', 'Vui lòng nhập tên chương trình')
        return null;
    }
    if (voucherName.length > 255) {
        console.log('Tên chương trình quá dài')
        toastDanger('Lỗi', 'Tên chương trình quá dài')
        return null;
    }
    // validate số lần sử dụng
    if (voucherQuantity === null || voucherQuantity === undefined || voucherQuantity === '') {
        console.log('Số lần sử dụng không được bỏ trống')
        toastDanger('Lỗi', 'Vui lòng nhập số lần sử dụng')
        return null;
    }
    // validate mệnh giá
    if (voucherDiscount === null || voucherDiscount === undefined || voucherDiscount === '') {
        console.log('Mệnh giá không được bỏ trống')
        toastDanger('Lỗi', 'Vui lòng nhập mệt giá')
        return null;
    }
    if (!/^[0-9]+$/.test(voucherDiscount)) {
        console.log('Mệnh giá không đúng')
        toastDanger('Lỗi', 'Mệnh giá không đúng')
        return null;
    }
    if (voucherTypeDiscount === null || voucherTypeDiscount === undefined) {
        voucherTypeDiscount = '%';
    }
    voucherTypeDiscount = voucherTypeDiscount.trim();
    console.log(voucherTypeDiscount)
    if (voucherTypeDiscount === '%') {
        if (Number(voucherDiscount) < 0 || Number(voucherDiscount) > 100) {
            console.log('Tỉ lệ giảm không đúng (từ 0% - 100%)');
            toastDanger('Lỗi', 'Tỉ lệ không đúng (từ 0% - 100%)')
            return null;
        }
        if (Number(voucherDiscount) === 0) {
            console.log('Tỉ lệ giảm phải từ 0%');
            toastDanger('Lỗi', 'Tỉ lệ giảm phải từ 0%')
            return null;
        }
    } else if (voucherTypeDiscount === 'đ') {
        if (Number(voucherDiscount) < 1) {
            console.log('Số tiền giảm không đúng (từ 0đ)');
            toastDanger('Lỗi', 'Số tiền giảm không đúng (từ 0đ)')
            return null;
        }
    } else {
        console.log('Có lỗi khi nhập mệnh giá giảm');
        toastDanger('Lỗi', 'Có lỗi khi nhập mệnh giá giảm')
        return null;
    }
    // validate số tiền hàng tối thiểu
    if (voucherMoneyMin === null || voucherMoneyMin === undefined || voucherMoneyMin === '') {
        console.log('Vui lòng nhập số tiền hàng mua tối thiểu')
        toastDanger('Lỗi', 'Vui lòng nhập số tiền hàng mua tối thiểu')
        return null;
    }
    if (!/^[0-9]+$/.test(voucherMoneyMin)) {
        console.log('Số tiền hàng mua tối thiểu không đúng')
        toastDanger('Lỗi', 'Số tiền hàng mua tối thiểu không đúng')
        return null;
    }
    if (Number(voucherMoneyMin) < 0) {
        console.log('Số tiền hàng mua tối thiểu phải từ 0đ')
        toastDanger('Lỗi', 'Số tiền hàng mua tối thiểu phải từ 0đ')
        return null;
    }
    // validate ngày bắt đầu
    if (voucherStartDate === null || voucherStartDate === undefined || voucherStartDate === '') {
        console.log('Ngày bắt đầu không được bỏ trống')
        toastDanger('Lỗi', 'Vui lòng nhập ngày bắt đầu')
        return null;
    }
    voucherStartDate = voucherStartDate.substring(0, voucherStartDate.indexOf(' '));
    if (!/^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/.test(voucherStartDate)) {
        console.log('Ngày bắt đầu không đúng (dd-MM-yyyy)')
        toastDanger('Lỗi', 'Ngày bắt đầu không đúng (dd-MM-yyyy)')
        return null;
    }
    // validate ngày kêt thúc
    if (voucherEndDate === null || voucherEndDate === undefined || voucherEndDate === '') {
        console.log('Ngày kết thúc không được bỏ trống')
        toastDanger('Lỗi', 'Vui lòng nhập ngày kết thúc')
        return null;
    }
    voucherEndDate = voucherEndDate.substring(0, voucherEndDate.indexOf(' '));
    if (!/^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/.test(voucherEndDate)) {
        console.log('Ngày kết thúc không đúng (dd-MM-yyyy)')
        toastDanger('Lỗi', 'Ngày kết thúc không đúng (dd-MM-yyyy)')
        return null;
    }
    // validate ngày bát đầu và ngày kết thúc trùng nhau
    if (voucherStartDate === voucherEndDate) {
        console.log('Ngày bắt đầu và ngày kết thúc không thể trúng nhau')
        toastDanger('Lỗi', 'Ngày bắt đầu và ngày kết thúc không thể trùng nhau')
        return null;
    }
    // validate loại sản phẩm áp dụng
    if (voucherCategory === null || voucherCategory === undefined) {
        console.log('Vui lòng chọn loại sản phẩm áp dụng')
        toastDanger('Lỗi', 'Vui lòng chọn mặt hàng áp dụng')
        return null;
    }
    // validate đối tượng áp dụng
    if (voucherPersonApply === null || voucherPersonApply === undefined) {
        console.log('Vui lòng chọn đối tượng áp dụng')
        toastDanger('Lỗi', 'Vui lòng chọn đối tượng áp dụng')
        return null;
    }
    if (voucherPersonApply === 'PERSON-BUY-MONEY') {
        if (typeDiscountMoneyMin === null || typeDiscountMoneyMin === undefined || typeDiscountMoneyMin === '') {
            console.log('Vui lòng nhập số tiền đã mua tối thiểu của khác hàng áp dụng');
            toastDanger('Lỗi', 'Vui lòng nhập số tiền đã mua tối thiểu của khách hàng áp dụng')
            return null;
        }
        if (!/^[0-9]+$/.test(typeDiscountMoneyMin)) {
            console.log('Số tiền đã mua tối thiểu của khách không đúng')
            toastDanger('Lỗi', 'Số tiền đã mua tối thiểu của khách không đúng')
            return null;
        }
        if (Number(typeDiscountMoneyMin) < 0) {
            console.log('Số tiền đã mua tối thiểu của khách phải từ 0đ')
            toastDanger('Lỗi', 'Số tiền đã mua tối thiểu của khách phải từ 0đ')
            return null;
        }
        typeDiscountPerson = null;
    } else if (voucherPersonApply === 'CHOOSE-PERSON') {
        if (typeDiscountPerson === null || typeDiscountPerson === undefined || typeDiscountPerson.length === 0) {
            console.log('Vui lòng chọn khách hàng nhận mã giảm giá');
            toastDanger('Lỗi', 'Vui lòng chọn khách hàng nhận mã giảm giá')
            return null;
        }
        typeDiscountMoneyMin = null;
    } else {
        typeDiscountMoneyMin = null;
        typeDiscountPerson = null;
    }
    return {
        "voucherId": id,
        "voucherCode": voucherCode,
        "voucherName": voucherName,
        "voucherQuantity": voucherQuantity,
        "voucherDiscount": voucherDiscount,
        "voucherTypeDiscount": voucherTypeDiscount,
        "voucherMoneyMin": voucherMoneyMin,
        "voucherStartDate": voucherStartDate,
        "voucherEndDate": voucherEndDate,
        "voucherCategory": voucherCategory,
        "voucherPersonApply": voucherPersonApply,
        "typeDiscountMoneyMin": typeDiscountMoneyMin,
        "typeDiscountPerson": typeDiscountPerson,
        "voucherAccompanyPromo": getElementVoucherPromo.checked ? 'ON' : 'OFF',
        "voucherDescription": getElementVoucherDescription === null || getElementVoucherDescription === undefined ? "" : getElementVoucherDescription.value
    };
}

function getSelectValues(select) {
    var result = [];
    var options = select && select.options;
    var opt;

    for (let i = 0, iLen = options.length; i < iLen; i++) {
        opt = options[i];
        if (opt.selected) {
            result.push(opt.value || opt.text);
        }
    }
    return result;
}

function onClickChangeTypeDiscount(e) {
    e.innerHTML === '%' ? e.innerHTML = 'đ' : e.innerHTML = '%'
}

function onChangePersonApply(e) {
    let nextElement = e.parentElement.parentElement.nextElementSibling
    if (nextElement !== null && nextElement !== undefined) {
        let getChildrenElement = nextElement.childNodes;
        if (getChildrenElement !== null && getChildrenElement !== undefined) {
            for (let index = 0; index < getChildrenElement.length; index++) {
                getChildrenElement[index].hidden = !(getChildrenElement[index].className + '').includes(e.value);
            }
        }
    }
}

function onClickInfoVoucher(e) {
    let getTrNextElement = e.parentElement.parentElement.nextElementSibling
    if (getTrNextElement !== null && getTrNextElement !== undefined) {
        if (getTrNextElement.hidden) {
            e.innerHTML = 'Đóng'
            e.className = 'btn btn-secondary btn-sm'
        } else {
            e.innerHTML = 'Chi tiết'
            e.className = 'btn btn-info btn-sm'
        }
        getTrNextElement.hidden = !getTrNextElement.hidden
    }
}