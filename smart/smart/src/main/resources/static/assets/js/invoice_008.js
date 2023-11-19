// Create our number formatter.
const formatter = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
    // These options are needed to round to whole numbers if that's what you want.
    //minimumFractionDigits: 0, // (this suffices for whole numbers, but will print 2500.10 as $2,500.1)
    //maximumFractionDigits: 0, // (causes 2500.99 to be printed as $2,501)
});

window.onload = function () {
    $('.modal').hide()
    editData();
}

function editData() {
    let e = {};
    $(".table-edits tr").editable({
        dropdowns: {product: [{"value": "1234", "text": "234"}, "Female"]},
        edit: function (t) {
            $(".edit i", this).removeClass("fa-pencil-alt").addClass("fa-save btnProductSave").attr("title", "Save")
        },
        save: function (t) {
            $(".edit i", this).removeClass("fa-save btnProductSave").addClass("fa-pencil-alt").attr("title", "Edit"), this in e && (e[this].destroy(), delete e[this])
        },
        cancel: function (t) {
            $(".edit i", this).removeClass("fa-save btnProductSave").addClass("fa-pencil-alt").attr("title", "Edit"), this in e && (e[this].destroy(), delete e[this])
        }
    })
    setTimeout(function () {
        editData();
    }, 10);
}

function onChangeInvoiceOrder(e) {
    let value = e.value + '';
    let id, supplierId;
    if (value !== null && value !== undefined && value !== '') {
        id = value.substring(0, value.indexOf('@'));
        supplierId = value.substring(value.includes('@') + 1)
    } else {
        return;
    }
    $('#ip-add-supplier option[value=' + supplierId + ']').attr('selected', true).change();
    $.ajax({
        url: '/api/invoice?orderId=' + id,
        method: 'GET',
        success: function (data) {
            $("#ip-add-product option:selected").attr('selected', false).change();
            $(data).each((index, obj) => {
                $('#ip-add-product option[value=' + obj.productId + ']').attr('selected', true)
                $('#ip-add-product').change();
                $('#ip-add-product-quantity-' + obj.productId).val(obj.quantity)
            })
        },
        error: function (error) {

        }
    })
}

function onChangeAddProduct(e) {
    let arrSelect = getSelectValues(e);
    $(arrSelect).each((index, data) => {
        let invoiceDetail = $('#invoice-detail');
        if (invoiceDetail.children('#' + data.id).length === 0) {
            invoiceDetail.append('<div class="row" id="' + data.id + '">\n' +
                '    <div class="col-lg-4 mb-3">\n' +
                '        <label for="ip-add-product-quantity-' + data.id + '">\n' +
                data.name +
                '        </label>\n' +
                '    </div>\n' +
                '    <div class="col-lg-4 mb-3">\n' +
                '        <input type="number" min="1" onchange="onChangeTotal()" value="1" id="ip-add-product-quantity-' + data.id + '" data-id="' + data.id + '"\n' +
                '               placeholder="Số lượng...."\n' +
                '               class="form-control add-product-quantity-invoice"/>\n' +
                '    </div>\n' +
                '    <div class="col-lg-4 mb-3">\n' +
                '        <input type="number" min="0" onchange="onChangeTotal()" value="0" id="ip-add-product-price-' + data.id + '" data-id="' + data.id + '"\n' +
                '               placeholder="Giá nhập...."\n' +
                '               class="form-control add-product-price-invoice"/>\n' +
                '    </div>\n' +
                '</div>'
            )
        }
    })
    $('#invoice-detail .row').each((index, data) => {
        let flag = false;
        for (let i = 0; i < arrSelect.length; i++) {
            if (arrSelect[i].id === data.id) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            $(data).prop('outerHTML', '')
        }
    })
    onChangeTotal();
}



function onChangeAddProductEdit(e) {
    let arrSelect = getSelectValues(e);
    $(arrSelect).each((index, data) => {
        let invoiceDetail = $('#invoice-edit-detail');
        if (invoiceDetail.children('#edit' + data.id).length === 0) {
            invoiceDetail.append('<div class="row" id="edit' + data.id + '">\n' +
                '    <div class="col-lg-4 mb-3">\n' +
                '        <label for="ip-edit-product-quantity-' + data.id + '">\n' +
                data.name +
                '        </label>\n' +
                '    </div>\n' +
                '    <div class="col-lg-4 mb-3">\n' +
                '        <input type="number" min="1" onchange="onChangeTotalEdit()" value="1" id="ip-edit-product-quantity-' + data.id + '" data-id="' + data.id + '"\n' +
                '               placeholder="Số lượng...."\n' +
                '               class="form-control edit-product-quantity-invoice"/>\n' +
                '    </div>\n' +
                '    <div class="col-lg-4 mb-3">\n' +
                '        <input type="number" min="0" onchange="onChangeTotalEdit()" value="0" id="ip-edit-product-price-' + data.id + '" data-id="' + data.id + '"\n' +
                '               placeholder="Giá nhập...."\n' +
                '               class="form-control edit-product-price-invoice"/>\n' +
                '    </div>\n' +
                '</div>'
            )
        }
    })
    $('#invoice-edit-detail .row').each((index, data) => {
        let flag = false;
        for (let i = 0; i < arrSelect.length; i++) {
            if (data.id.includes(arrSelect[i].id)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            $(data).prop('outerHTML', '')
        }
    })

}

function getSelectValues(select) {
    let result = [];
    let options = select && select.options;
    let opt;
    for (let i = 0, iLen = options.length; i < iLen; i++) {
        opt = options[i];
        if (opt.selected) {
            result.push({"id": opt.value, "name": opt.text});
        }
    }
    return result;
}

function onClickAddInvoice() {
    let getInvoiceOrderCode = document.getElementById('ip-add-order-code');
    let getSupplier = document.getElementById('ip-add-supplier');
    let getInvoiceDiscount = document.getElementById('ip-add-invoice-discount');
    let getInvoicePaid = document.getElementById('ip-add-invoice-paid');
    let getInvoiceNote = document.getElementById('ip-add-invoice-note');
    let getAllProductId = document.getElementsByClassName('add-product1');
    let tienThua = document.getElementById("ip-add-thua");
    let obj = valueDateInvoice(

        getInvoiceOrderCode,
        getSupplier,
        getInvoiceDiscount,
        getInvoicePaid,
        getInvoiceNote,
        getAllProductId,
        tienThua
    )
    console.log(obj)
    if (obj !== null && obj !== undefined ) {
        $.ajax({
            url: '/api/invoice',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(obj),
            success: function (data) {
                toastSuccess('Thành công', 'Nhập hàng thành công')
                setTimeout(function () {
                    location.reload()
                }, 2000)
            },
            error: function (error) {
                toastDanger('Lỗi', 'Hệ thống lỗi');
            }
        })
    }
}
function valueDateInvoice(

    getInvoiceOrderCode,
    getSupplier,
    getInvoiceDiscount,
    getInvoicePaid,
    getInvoiceNote,
    getAllProductId,
    tienThua
) {
    let id = 0;
    if(getInvoiceOrderCode === null || getInvoiceOrderCode === undefined || getInvoiceOrderCode.value === ''){
        id = 0;
    }else {
        id = getInvoiceOrderCode.value;
    }

    if (getSupplier === null || getSupplier === undefined || getSupplier.value === '') {
        toastDanger('Lỗi', 'Vui lòng chọn nhà cung cấp');
        return;
    }
    if (getInvoiceDiscount.value === null || getInvoiceDiscount.value === undefined) {
        getInvoiceDiscount.value = 0
    }
    if (Number(getInvoiceDiscount.value) || getInvoiceDiscount.value === '0') {
        if (Number(getInvoiceDiscount.value) < 0) {
            toastDanger('Lỗi', 'Số tiền giảm giá phải từ 0đ')
            return;
        }
    } else {
        toastDanger('Lỗi', 'Số tiền giảm giá phải là một số nguyên')
        return;
    }

    if (Number(tienThua.value) || tienThua.value === '0') {
        if (Number(tienThua.value) < 0) {
            toastDanger('Lỗi', 'Số tiền thừa phải từ 0đ')
            return;
        }
    } else {
        toastDanger('Lỗi', 'Số tiền thừa phải là một số nguyên')
        return;
    }

    if (getInvoicePaid.value === null || getInvoicePaid.value === undefined) {
        getInvoicePaid.value = 0
    }
    if (Number(getInvoicePaid.value) || getInvoicePaid.value === '0') {
        if (Number(getInvoicePaid.value) < 0) {
            toastDanger('Lỗi', 'Số tiền trả NCC phải từ 0đ')
            return;
        }
    } else {
        toastDanger('Lỗi', 'Số tiền trả NCC phải là một số nguyên')
        return;
    }
    let lstDetails = [];
    let tong = 0;
    if (getAllProductId !== null && getAllProductId !== undefined) {
        let count = 0;

        for (const $tr of getAllProductId) {
            if(count === 0){
                count ++;
                continue;
            }
            if(count > 0){
                if($tr.childNodes[9].childNodes[1].value <= 0){
                    toastDanger("Lỗi", "Vui lòng nhập số lượng lớn hơn 0");
                    return;
                }

                var e = $tr.childNodes[15].childNodes[1];
                if($tr.childNodes[11].childNodes[1].value > 0 && Number(e.options[e.selectedIndex].value) == 3){
                    toastDanger("Lỗi", "Bạn phải huỷ sản phẩm do NCC hết hàng hoặc để giá tiền bằng 0");
                    return ;
                }

                if($tr.childNodes[11].childNodes[1].value > 0 && Number(e.options[e.selectedIndex].value) == 1){
                    toastDanger("Lỗi", "Sản phẩm đang nhập thì giá tiền bằng 0");
                    return ;
                }
                if($tr.childNodes[11].childNodes[1].value <= 0 && Number(e.options[e.selectedIndex].value) == 2){
                    toastDanger("Lỗi", "Vui lòng nhập giá tiền lớn hơn 0");
                    return;
                }
                lstDetails.push({
                    "romId" : $tr.childNodes[7].childNodes[3].innerText,
                    "colorId" : $tr.childNodes[5].childNodes[3].innerText,
                    "quantityInvoice" : $tr.childNodes[9].childNodes[1].value,
                    "moneyInvoice" : $tr.childNodes[11].childNodes[1].value,
                    "note" : $tr.childNodes[13].childNodes[1].value,
                    "status" : e.options[e.selectedIndex].value
                });
                tong+=Number($tr.childNodes[11].childNodes[1].value);
            }
        }
        if(lstDetails.length === 0){
            toastDanger("Lỗi", "Bạn chưa chọn sản phẩm nào");
            return;
        }
        for (let i = 0; i < lstDetails.length; i++) {
            for (let j = 0; j < lstDetails.length; j++) {
                if(lstDetails[i].romId === lstDetails[j].romId && i !== j && lstDetails[i].colorId === lstDetails[j].colorId){
                    toastDanger('Lỗi', 'Đã bị trùng sản phẩm xin vui lòng nhập lại');
                    return;
                }
            }
        }
        if(Number(getInvoicePaid.value) -  Number(tong) + Number(getInvoiceDiscount.value)   < 0 ){
            toastDanger("Lỗi", "Tổng tiền trả NCC ít hơn tổng tiền sản phẩm");
            return;
        }

        if(Number(tienThua.value) > (Number(getInvoicePaid.value) - (Number(tong) - Number(getInvoiceDiscount.value)))){
            toastDanger("Lỗi", "Tổng tiền thừa không đúng");
            return;
        }
        if(Number(tienThua.value) < (Number(getInvoicePaid.value) - (Number(tong) - Number(getInvoiceDiscount.value)))){
            toastDanger("Lỗi", "Tổng tiền thừa không đúng");
            return;
        }

    }

    return {
        "id" : id,
        "suppliderId": getSupplier.value,
        "discount": getInvoiceDiscount.value,
        "paid": getInvoicePaid.value,
        "note": getInvoiceNote.value,
        "totalMoney" : tong,
        "tienThua" : tienThua.value,
        "detailRequest" : lstDetails
    }
}

function onClickOpenModalAdd() {
    $('#btnSubmitAddInvoice').show();
    $('#btnSubmitEditInvoice').hide();
    $('#titleModal').html('Thêm hoá đơn nhập hàng')
    $.ajax({
        url: '/invoice/component/edit-invoice?id=',
        success: function (html) {
            $('#editInvoice').html(html)
            $.ajax({
                url: '/invoice/component/add-product?number=1',
                success: function (tr) {
                    $('#bodyAddProduct').append(tr);
                }
            })
        },
        error: function (e) {
            $('#modal-edit-invoice').modal('hide');
        }
    })
}

function onChangeInvoiceOrderEdit(e) {
    const rowMain = document.getElementById("rowMain");
    const rowClone = rowMain.cloneNode(true);
    const  table = document.getElementById("bodyAddProduct");
    while (table.lastElementChild) {
        table.removeChild(table.lastElementChild);
    }
    table.appendChild(rowClone);

    let value = e.value + '';
    let id, supplierId;
    if (value !== null && value !== undefined && value !== '') {
        id = value;
    } else {
        return;
    }

    $.ajax({
        url: '/api/order-invoice/' + value,
        method: 'GET',
        success: function (data) {
            console.log(data);
            for (let i = 0; i < data.orderDetail.length; i++){
                const rowChinh = rowClone.cloneNode(true);
                rowChinh.childNodes[1].textContent = data.orderDetail[i].productName;
                rowChinh.childNodes[5].childNodes[1].textContent =  data.orderDetail[i].colorName;
                rowChinh.childNodes[5].childNodes[3].textContent = data.orderDetail[i].colorId;
                rowChinh.childNodes[7].childNodes[1].textContent =  data.orderDetail[i].productRomName;
                rowChinh.childNodes[7].childNodes[3].textContent = data.orderDetail[i].productRomID;
                rowChinh.childNodes[9].childNodes[1].value = data.orderDetail[i].quantityProduct;
                rowChinh.childNodes[13].childNodes[1].value = data.orderDetail[i].note;
                rowChinh.childNodes[15].childNodes[1].value = data.orderDetail[i].status;
                rowChinh.removeAttribute("id");
                rowChinh.removeAttribute("style");
                table.appendChild(rowChinh);
            }

        },
        error: function (error) {
            toastDanger('Lỗi', 'Hệ thống lỗi');
        }
    })
}


function onClickEditInvoice(e) {
    $('#btnSubmitEditInvoice').show();
    $('#btnSubmitEditInvoice').attr('data-id', e.dataset.id)
    $('#titleModal').html('Cập nhật hoá đơn nhập hàng')
    const rowMain = document.getElementById("rowMain1");
    const rowClone = rowMain.cloneNode(true);
    const  table = document.getElementById("bodyEditProduct");
    while (table.lastElementChild) {
        table.removeChild(table.lastElementChild);
    }
    table.appendChild(rowClone);



    $.ajax({
        url: '/api/order-invoice/' + e.dataset.id,
        method : 'GET',
        success: function (data) {
            console.log(data)
            for (let i = 0; i < data.orderDetail.length; i++){
                const rowChinh = rowClone.cloneNode(true);
                if(Number(data.orderDetail[i].status) === 2){
                    rowChinh.childNodes[3].childNodes[1].setAttribute("hidden", true);
                }
                rowChinh.childNodes[1].textContent = data.orderDetail[i].productName;
                rowChinh.childNodes[5].childNodes[1].textContent =  data.orderDetail[i].colorName;
                rowChinh.childNodes[5].childNodes[3].textContent = data.orderDetail[i].colorId;
                rowChinh.childNodes[7].childNodes[1].textContent =  data.orderDetail[i].productRomName;
                rowChinh.childNodes[7].childNodes[3].textContent = data.orderDetail[i].productRomID;
                rowChinh.childNodes[9].childNodes[1].value = data.orderDetail[i].quantityProduct;
                rowChinh.childNodes[11].childNodes[1].value = data.orderDetail[i].priceProduce;
                rowChinh.childNodes[13].childNodes[1].value = data.orderDetail[i].note;
                rowChinh.childNodes[15].childNodes[1].value = data.orderDetail[i].status;
                rowChinh.removeAttribute("id");
                rowChinh.removeAttribute("style");
                table.appendChild(rowChinh);
                document.getElementById("ip-edit-thua").value = data.tienThua
                document.getElementById("ip-edit-invoice-paid").value = data.phaiTraNCC
                document.getElementById("ip-edit-invoice-discount").value = data.giamGia
                document.getElementById("ip-edit-order-code").innerText = data.codeOrder
            }
        },
        error: function (e) {
            $('#modal-edit-invoice').modal('hide');
        }
    })
}

function onClickSubmitEditInvoice(e) {
    let getInvoiceOrderCode = document.getElementById('ip-edit-order-code');
    let getSupplier = document.getElementById('ip-edit-supplier');
    let getInvoiceDiscount = document.getElementById('ip-edit-invoice-discount');
    let getInvoicePaid = document.getElementById('ip-edit-invoice-paid');
    let getInvoiceNote = document.getElementById('ip-edit-invoice-note');
    let getAllProductQuantity = document.getElementsByClassName('ip-edit-quantity');
    let getAllProductPrice = document.getElementsByClassName('ip-edit-price');
    let getAllProductId = document.getElementsByClassName('edit-product1');
    let tienThua = document.getElementById('ip-edit-thua');
    let obj = valueDateInvoiceEdit(
        e.dataset.id,
        getInvoiceOrderCode,
        getSupplier,
        getInvoiceDiscount,
        getInvoicePaid,
        getInvoiceNote,
        getAllProductQuantity,
        getAllProductPrice,
        getAllProductId,
        tienThua
    )
    console.log(obj)
    if (obj !== null && obj !== undefined) {
        $.ajax({
            url: '/api/invoice',
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(obj),
            success: function (data) {
                toastSuccess('Thành công', 'Đã cập nhật thông tin nhập hàng')
                setTimeout(function () {
                    location.reload()
                }, 2000)
            },
            error: function (error) {
                console.log(error)
                toastDanger('Lỗi', error.responseJSON.vn);
            }
        })
    }
}

function valueDateInvoiceEdit(
    id,
    getInvoiceOrderCode,
    getSupplier,
    getInvoiceDiscount,
    getInvoicePaid,
    getInvoiceNote,
    getAllProductQuantity,
    getAllProductPrice,
    getAllProductId,
    tienThua
) {

    if (getSupplier === null || getSupplier === undefined || getSupplier.value === '') {
        toastDanger('Lỗi', 'Vui lòng chọn nhà cung cấp');
        return;
    }
    if (getInvoiceDiscount.value === null || getInvoiceDiscount.value === undefined) {
        getInvoiceDiscount.value = 0
    }
    if (Number(getInvoiceDiscount.value) || getInvoiceDiscount.value === '0') {
        if (Number(getInvoiceDiscount.value) < 0) {
            toastDanger('Lỗi', 'Số tiền giảm giá phải từ 0đ')
            return;
        }
    } else {
        toastDanger('Lỗi', 'Số tiền giảm giá phải là một số nguyên')
        return;
    }

    if (Number(tienThua.value) || tienThua.value === '0') {
        if (Number(tienThua.value) < 0) {
            toastDanger('Lỗi', 'Số tiền thừa phải từ 0đ')
            return;
        }
    } else {
        toastDanger('Lỗi', 'Số tiền thừa phải là một số nguyên')
        return;
    }

    if (getInvoicePaid.value === null || getInvoicePaid.value === undefined) {
        getInvoicePaid.value = 0
    }
    if (Number(getInvoicePaid.value) || getInvoicePaid.value === '0') {
        if (Number(getInvoicePaid.value) < 0) {
            toastDanger('Lỗi', 'Số tiền trả NCC phải từ 0đ')
            return;
        }
    } else {
        toastDanger('Lỗi', 'Số tiền trả NCC phải là một số nguyên')
        return;
    }
    let lstDetails = [];
    let tong = 0;
    if (getAllProductId !== null && getAllProductId !== undefined) {
        let count = 0;

        for (const $tr of getAllProductId) {
            if(count === 0){
                count ++;
                continue;
            }
            if(count > 0){
                if($tr.childNodes[9].childNodes[1].value <= 0){
                    toastDanger("Lỗi", "Vui lòng nhập số lượng lớn hơn 0");
                    return;
                }
                var e = $tr.childNodes[15].childNodes[1];


                if($tr.childNodes[11].childNodes[1].value > 0 && Number(e.options[e.selectedIndex].value) == 3){
                    toastDanger("Lỗi", "Bạn phải huỷ sản phẩm do NCC hết hàng hoặc để giá tiền bằng 0");
                    return ;
                }

                if($tr.childNodes[11].childNodes[1].value > 0 && Number(e.options[e.selectedIndex].value) == 1){
                    toastDanger("Lỗi", "Sản phẩm đang nhập thì giá tiền bằng 0");
                    return ;
                }

                if($tr.childNodes[11].childNodes[1].value <= 0 && Number(e.options[e.selectedIndex].value) == 2){
                    toastDanger("Lỗi", "Vui lòng nhập giá tiền lớn hơn 0");
                    return;
                }


                lstDetails.push({
                    "romId" : $tr.childNodes[7].childNodes[3].innerText,
                    "colorId" : $tr.childNodes[5].childNodes[3].innerText,
                    "quantityInvoice" : $tr.childNodes[9].childNodes[1].value,
                    "moneyInvoice" : $tr.childNodes[11].childNodes[1].value,
                    "note" : $tr.childNodes[13].childNodes[1].value,
                    "status" : e.options[e.selectedIndex].value
                });
                tong+=Number($tr.childNodes[11].childNodes[1].value);
            }
        }
        if(lstDetails.length === 0){
            toastDanger("Lỗi", "Bạn chưa chọn sản phẩm nào");
            return;
        }
        for (let i = 0; i < lstDetails.length; i++) {
            for (let j = 0; j < lstDetails.length; j++) {
                if(lstDetails[i].romId === lstDetails[j].romId && i !== j && lstDetails[i].colorId === lstDetails[j].colorId){
                    toastDanger('Lỗi', 'Đã bị trùng sản phẩm xin vui lòng nhập lại');
                    return;
                }
            }
        }
        if(Number(getInvoicePaid.value) -  Number(tong) + Number(getInvoiceDiscount.value)   < 0 ){
            toastDanger("Lỗi", "Tổng tiền trả NCC ít hơn tổng tiền sản phẩm");
            return;
        }

         if(Number(tienThua.value) > (Number(getInvoicePaid.value) - (Number(tong) - Number(getInvoiceDiscount.value)))){
             toastDanger("Lỗi", "Tổng tiền thừa không đúng");
             return;
         }
        if(Number(tienThua.value) < (Number(getInvoicePaid.value) - (Number(tong) - Number(getInvoiceDiscount.value)))){
            toastDanger("Lỗi", "Tổng tiền thừa không đúng");
            return;
        }

    }
    return {
        "id": id,
        "suppliderId": getSupplier.value,
        "discount": getInvoiceDiscount.value,
        "paid": getInvoicePaid.value,
        "note": getInvoiceNote.value,
        "totalMoney" : tong,
        "tienThua" : tienThua.value,
        "detailRequest" : lstDetails
    }
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

function onChangeQuantityOrPrice() {
    let quantityActual = document.getElementsByClassName('ip-edit-quantity-actual');
    let price = document.getElementsByClassName('ip-edit-price');
    let totalAmount = 0;
    if (quantityActual !== null && price !== null) {
        for (let i = 0; i < quantityActual.length; i++) {
            let quantityImport = Number(quantityActual[i].innerText)
            let priceImport = Number(price[i].innerText)
            totalAmount += (quantityImport * priceImport);
        }
    }
    let getDiscount = $('#ip-edit-invoice-discount').val();
    let getPaid = $('#ip-edit-invoice-paid').val();
    if (totalAmount < Number(getDiscount)) {
        toastDanger('Lỗi', `Tổng tiền nhập hàng không thể nhỏ hơn số tiền NCC giảm. Tổng tiền ${totalAmount}đ`)
        return false;
    }
    if (totalAmount < Number(getPaid)) {
        toastDanger('Lỗi', `Tổng tiền nhập hàng không thể nhỏ hơn số tiền trả NCC. Tổng tiền ${totalAmount}đ`)
        return false;
    }
    if (totalAmount < Number(getDiscount) + Number(getPaid)) {
        toastDanger('Lỗi', `Tổng tiền nhập hàng không thể nhỏ hơn tổng tiền trả NCC và tiền NCC giảm. Tổng tiền ${totalAmount}đ`)
        return false;
    }
    return true;
}