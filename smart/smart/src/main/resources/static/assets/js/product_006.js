// let dataImageUploadEdit = [];
// let dataImageUploadAdd = [];
// let sizeImage = 0;
// var showtext = [[${listRom}]];
// console.log(showtext)
// window.onload = function () {
//     $('.modal').hide();
//     CKEDITOR.replace('productAddDescription');
//     CKEDITOR.replace('productEditDescription');
//     $('#ip-edit-product-image').change(function () {
//         for (var i = 0, f; (f = this.files[i]); i++) {
//             if (!f.type.match("image.*")) {
//                 continue;
//             }
//             var reader = new FileReader();
//             reader.onload = (function (theFile) {
//                 return function (e) {
//                     dataImageUploadEdit.push(e.target.result)
//                     addImagePreview(e.target.result)
//                 };
//             })(f);
//             // Read in the image file as a data URL.
//             reader.readAsDataURL(f);
//         }
//     })
//     $('#ip-add-product-image').change(function () {
//         for (var i = 0, f; (f = this.files[i]); i++) {
//             if (!f.type.match("image.*")) {
//                 continue;
//             }
//             var reader = new FileReader();
//             reader.onload = (function (theFile) {
//                 return function (e) {
//                     dataImageUploadAdd.push(e.target.result)
//                     addImagePreviewAdd(e.target.result)
//                 };
//             })(f);
//             // Read in the image file as a data URL.
//             reader.readAsDataURL(f);
//         }
//     })
//     showAttributeCategory();
// }
//
// function showAttributeCategory() {
//     let category = document.getElementById('ip-add-product-category');
//     if (category !== null && category !== undefined) onChangeCategory(category);
// }
//
// function zoomImageProduct(e) {
//     let srcImg = e.src
//     console.log(srcImg)
//     $('#modal-zoom-image').modal('show');
//     let imgZoomImage = document.getElementById('img-zoom-image')
//     if (imgZoomImage !== null && imgZoomImage !== undefined) {
//         imgZoomImage.src = srcImg
//     }
// }
//
// function onClickChooseImage() {
//     $('#ip-edit-product-image').click()
// }
//
// function onClickChooseImageAdd() {
//     $('#ip-add-product-image').click()
// }
//
// function addImagePreviewAdd(image) {
//     let carouselIndicators = document.getElementById('carousel-indicators-add')
//     if (sizeImage === 0) {
//         carouselIndicators.innerHTML +=
//             '<button type="button" data-bs-target="#carouselExampleCaptionsAdd" data-bs-slide-to="' + sizeImage + '"\n' +
//             'aria-label="Slide ' + sizeImage + '" class="active" aria-current="true"></button>'
//     } else {
//         carouselIndicators.innerHTML +=
//             '<button type="button" data-bs-target="#carouselExampleCaptionsAdd" data-bs-slide-to="' + sizeImage + '"\n' +
//             'aria-label="Slide ' + sizeImage + '"></button>'
//     }
//     let divImage = document.createElement('div')
//     divImage.className = sizeImage == 0 ? 'carousel-item active' : 'carousel-item'
//     divImage.innerHTML = [
//         '<img style="height: 600px;" src="',
//         image,
//         '" title="',
//         '"/>'
//     ].join("");
//     document.getElementById("previewUploadAdd").insertBefore(divImage, null);
//     sizeImage += 1;
// }
//
// function addImagePreview(image) {
//     let carouselIndicators = document.getElementById('carousel-indicators')
//     if (sizeImage === 0) {
//         carouselIndicators.innerHTML +=
//             '<button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="' + sizeImage + '"\n' +
//             'aria-label="Slide ' + sizeImage + '" class="active" aria-current="true"></button>'
//     } else {
//         carouselIndicators.innerHTML +=
//             '<button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="' + sizeImage + '"\n' +
//             'aria-label="Slide ' + sizeImage + '"></button>'
//     }
//     let divImage = document.createElement('div')
//     divImage.className = sizeImage == 0 ? 'carousel-item active' : 'carousel-item'
//     divImage.innerHTML = [
//         '<img style="height: 600px;" src="',
//         image,
//         '" title="',
//         '"/>'
//     ].join("");
//     document.getElementById("previewUpload").insertBefore(divImage, null);
//     sizeImage += 1;
// }
//
// function onClickUpdateProduct(e) {
//     // get element tên sản phẩm
//     let getElementProductName = document.getElementById('ip-edit-product-name');
//
//     // get element he dieu hanh
//     let getElementProductOs = document.getElementById('ip-edit-product-os').value;
//
//     // get element man hinh
//     let getElementProductScreen = document.getElementById('ip-edit-product-screen').value;
//
//     // get element chip
//     let getElementProductChip = document.getElementById('ip-edit-product-chip').value;
//
//     //get element cam sau
//     let getElementProductCamSau = document.getElementById('ip-edit-product-cam_sau').value;
//
//
//     //get element pin sac
//     let getElementProductPin = document.getElementById('ip-edit-product-pin').value;
//
//     // get mô tả chi tiết sản phẩm
//     let productDescription = CKEDITOR.instances['productEditDescription'].getData();
//
//
//     // get element danh muc
//     let productCategory = document.getElementById('ip-edit-product-category').value;
//
//     // get element ram
//     let productRam = document.getElementById('ip-edit-product-ram').value;
//
//     // get element rom
//     let formRom8Gb = {};
//     let formRom32Gb = {};
//     let formRom64Gb = {};
//     let formRom16Gb = {};
//     let formRom128Gb = {};
//     let formRom256Gb = {};
//     let formRom512Gb = {};
//     let romRequestAdds = [];
//     let check = 0
//     for (let i = 0; i <= 6; i++) {
//         if(document.getElementById('rom' + i).checked && i === 0){
//             formRom8Gb = {
//                 "nameRom" : document.getElementById('edit-rom' + i).value
//             }
//             romRequestAdds.push(formRom8Gb)
//             check++;
//         }
//         if(document.getElementById('edit-rom' + i).checked && i === 1){
//             formRom16Gb = {
//                 "nameRom" : document.getElementById('edit-rom' + i).value
//             }
//             romRequestAdds.push(formRom16Gb)
//
//             check++;
//         }
//         if(document.getElementById('edit-rom' + i).checked && i === 2){
//             formRom32Gb = {
//                 "nameRom" : document.getElementById('edit-rom' + i).value
//
//             }
//             romRequestAdds.push(formRom32Gb)
//
//             check++;
//         }
//         if(document.getElementById('edit-rom' + i).checked && i === 3){
//             formRom64Gb = {
//                 "nameRom" : document.getElementById('edit-rom' + i).value
//
//             }
//             romRequestAdds.push(formRom64Gb)
//
//             check++;
//         }
//         if(document.getElementById('edit-rom' + i).checked && i === 4){
//             formRom128Gb = {
//                 "nameRom" : document.getElementById('edit-rom' + i).value
//
//             }
//             check++;
//             romRequestAdds.push(formRom128Gb)
//
//         }
//         if(document.getElementById('edit-rom' + i).checked && i === 5){
//             formRom256Gb = {
//                 "nameRom" : document.getElementById('edit-rom' + i).value
//
//             }
//             romRequestAdds.push(formRom256Gb)
//
//             check++;
//         }
//         if(document.getElementById('edit-rom' + i).checked && i === 6){
//             formRom512Gb = {
//                 "nameRom" : document.getElementById('edit-rom' + i).value
//
//             }
//             romRequestAdds.push(formRom512Gb)
//
//             check++;
//         }
//     }
//     let objectProduct = validateObjectProductEdit(
//         getElementProductName,
//         getElementProductOs,
//         getElementProductScreen,
//         getElementProductCamSau,
//         getElementProductPin,
//         getElementProductChip,
//         productCategory,
//         productRam,
//         productDescription,
//         dataImageUploadEdit,
//         check,
//         romRequestAdds
//     );
//     console.log(objectProduct);
//
//
//     if (objectProduct !== null && objectProduct !== undefined) {
//         objectProduct["idProduct"] = e.dataset.id
//         $.ajax({
//             url: '/api/product',
//             method: 'PUT',
//             contentType: 'application/json',
//             data: JSON.stringify(objectProduct),
//             success: function (data) {
//                 console.log(data);
//                 toastSuccess('Thành công', 'Đã cập nhật sản phẩm')
//                 setTimeout(function () {
//                     location.reload()
//                 }, 2000)
//             },
//             error: function (jqXHR, textStatus, errorThrown) {
//                 console.log(jqXHR);
//                 toastDanger('Lỗi', jqXHR.responseJSON.vn);
//             }
//         })
//     }
// }
//
// function onClickSaveProduct() {
//     // get element tên sản phẩm
//     let getElementProductName = document.getElementById('ip-add-product-name');
//
//     // get element he dieu hanh
//     let getElementProductOs = document.getElementById('ip-add-product-os').value;
//
//     // get element man hinh
//     let getElementProductScreen = document.getElementById('ip-add-product-screen').value;
//
//     // get element chip
//     let getElementProductChip = document.getElementById('ip-add-product-chip').value;
//
//     //get element cam sau
//     let getElementProductCamSau = document.getElementById('ip-add-product-cam').value;
//
//
//     //get element pin sac
//     let getElementProductPin = document.getElementById('ip-add-product-pin').value;
//
//     // get mô tả chi tiết sản phẩm
//     let productDescription = CKEDITOR.instances['productAddDescription'].getData();
//
//     // get element danh muc
//     let productCategory = document.getElementById('ip-add-product-category').value;
//
//     // get element ram
//     let productRam = document.getElementById('ip-add-product-ram').value;
//
//     // get element rom
//     let formRom8Gb = {};
//     let formRom32Gb = {};
//     let formRom64Gb = {};
//     let formRom16Gb = {};
//     let formRom128Gb = {};
//     let formRom256Gb = {};
//     let formRom512Gb = {};
//     let romRequestAdds = [];
//     let check = 0
//     for (let i = 0; i <= 6; i++) {
//         if(document.getElementById('rom' + i).checked && i === 0){
//             formRom8Gb = {
//                 "nameRom" : document.getElementById('rom' + i).value
//             }
//             romRequestAdds.push(formRom8Gb)
//             check++;
//         }
//         if(document.getElementById('rom' + i).checked && i === 1){
//             formRom16Gb = {
//                 "nameRom" : document.getElementById('rom' + i).value
//             }
//             romRequestAdds.push(formRom16Gb)
//
//             check++;
//         }
//         if(document.getElementById('rom' + i).checked && i === 2){
//             formRom32Gb = {
//                 "nameRom" : document.getElementById('rom' + i).value
//
//             }
//             romRequestAdds.push(formRom32Gb)
//
//             check++;
//         }
//         if(document.getElementById('rom' + i).checked && i === 3){
//             formRom64Gb = {
//                 "nameRom" : document.getElementById('rom' + i).value
//
//             }
//             romRequestAdds.push(formRom64Gb)
//
//             check++;
//         }
//         if(document.getElementById('rom' + i).checked && i === 4){
//             formRom128Gb = {
//                 "nameRom" : document.getElementById('rom' + i).value
//
//             }
//             check++;
//             romRequestAdds.push(formRom128Gb)
//
//         }
//         if(document.getElementById('rom' + i).checked && i === 5){
//             formRom256Gb = {
//                 "nameRom" : document.getElementById('rom' + i).value
//
//             }
//             romRequestAdds.push(formRom256Gb)
//
//             check++;
//         }
//         if(document.getElementById('rom' + i).checked && i === 6){
//             formRom512Gb = {
//                 "nameRom" : document.getElementById('rom' + i).value
//
//             }
//             romRequestAdds.push(formRom512Gb)
//
//             check++;
//         }
//     }
//     if(check > 0){
//         console.log(romRequestAdds)
//     }
//
//     let objectProduct = validateObjectProduct(
//         getElementProductName,
//         getElementProductOs,
//         getElementProductScreen,
//         getElementProductCamSau,
//         getElementProductPin,
//         getElementProductChip,
//         productCategory,
//         productRam,
//         productDescription,
//         dataImageUploadAdd,
//         check,
//         romRequestAdds
//     );
//     console.log(objectProduct);
//     if (objectProduct !== null && objectProduct !== undefined) {
//         $.ajax({
//             url: '/api/product',
//             method: 'POST',
//             contentType: 'application/json',
//             data: JSON.stringify(objectProduct),
//             success: function (data) {
//                 toastSuccess('Thành công', 'Đã lưu mới sản phẩm')
//                 console.log(data);
//                 setTimeout(function () {
//                     location.reload()
//                 }, 2000)
//             },
//             error: function (jqXHR, textStatus, errorThrown) {
//                 console.log(jqXHR);
//                 toastDanger('Lỗi', jqXHR.responseJSON.vn);
//             }
//         })
//     }
// }
// function onRoleChange() {
//     const role = $('select.roleFilter').val();
//     const rows = $('table#datatable tr');
//
//     if (role.length === 0) {
//         rows.show();
//     } else {
//         rows.hide();
//         role.forEach(e => {
//             rows.find(`.role:contains(${e})`).parent().parent().show();
//         })
//     }
//     resizeTable();
// }
// function validateObjectProduct(
//     getElementProductName,
//     getElementProductOs,
//     getElementProductScreen,
//     getElementProductCamSau,
//     getElementProductPin,
//     getElementProductChip,
//     productCategory,
//     productRam,
//     productDescription,
//     productImages,
//     check,
//     romRequestAdds
// ) {
//     let productOs, productName, productScreen, productCamSau,
//         productChip, productPin, productCamTruoc, productSim;
//
//     // get tên sản phẩm
//     if (getElementProductName !== null && getElementProductName !== undefined) {
//         productName = getElementProductName.value;
//     }
//
//     // get mô tả chi tiết sản phẩm
//     if (productDescription !== null && productDescription !== undefined) {
//         productDescription = (productDescription + '').substring(3);
//         productDescription = (productDescription + '').substring(0, productDescription.length - 5);
//     }
//
//
//
//     // validate tên sản phẩm
//     if (productName === null || productName === undefined || productName === '') {
//         console.log('Vui lòng nhập tên sản phẩm');
//         toastDanger('Lỗi', 'Vui lòng nhập tên sản phẩm');
//         return;
//     }
//     if (productName.length < 5 || productName.length > 100) {
//         console.log('Tên sản phẩm chỉ từ 10 đến 100 ký tự');
//         toastDanger('Lỗi', 'Tên sản phẩm chỉ từ 10 đến 100 ký tự');
//         return;
//     }
//
//
//
//     // validate loại sản phẩm & thuộc tính sản phẩm
//     if (productCategory === null || productCategory === undefined || productCategory === '') {
//         console.log('Vui lòng chọn danh mục sản phẩm');
//         toastDanger('Lỗi', 'Vui lòng chọn danh mục sản phẩm');
//         return;
//     }
//
//     // validate mô tả chi tiết sản phẩm
//     if (productDescription === null || productDescription === undefined || productDescription === '') {
//         console.log('Vui lòng nhập mô tả chi tiết sản phẩm');
//         toastDanger('Lỗi', 'Vui lòng nhập mô tả chi tiết sản phẩm');
//         return;
//     }
//
//     // validate hình ảnh sản phẩm
//     if (productImages === null || productImages === undefined || productImages.length === 0) {
//         toastDanger('Lỗi', 'Vui lòng chọn hình ảnh sản phẩm');
//         return;
//     }
//
//     if(check === 0){
//         console.log('Vui lòng chọn dung lượng');
//         toastDanger('Lỗi', 'Vui lòng chọn dung lượng');
//         return;
//     }
//
//
//
//
//     return {
//         "nameProduct": productName,
//         "categoryId" : productCategory,
//         "note": productDescription,
//         "image": productImages,
//         "attributeRequestAdd": {
//             "operatingSystem" : getElementProductOs,
//             "screen" : getElementProductScreen,
//             "chip" : getElementProductChip,
//             "cam" : getElementProductCamSau,
//             "pin" : getElementProductPin,
//             "ram" : productRam
//         },
//         romRequestAdds
//     };
// }
//
// function validateObjectProductEdit(
//     getElementProductName,
//     getElementProductOs,
//     getElementProductScreen,
//     getElementProductCamSau,
//     getElementProductPin,
//     getElementProductChip,
//     productCategory,
//     productRam,
//     productDescription,
//     productImages,
//     check,
//     romRequestAdds
// ) {
//     let productOs, productName, productScreen, productCamSau,
//         productChip, productPin, productCamTruoc, productSim;
//
//     // get tên sản phẩm
//     if (getElementProductName !== null && getElementProductName !== undefined) {
//         productName = getElementProductName.value;
//     }
//
//
//     // get mô tả chi tiết sản phẩm
//     if (productDescription !== null && productDescription !== undefined) {
//         productDescription = (productDescription + '').substring(3);
//         productDescription = (productDescription + '').substring(0, productDescription.length - 5);
//
//     }
//
//
//
//
//     // validate tên sản phẩm
//     if (productName === null || productName === undefined || productName === '') {
//         console.log('Vui lòng nhập tên sản phẩm');
//         toastDanger('Lỗi', 'Vui lòng nhập tên sản phẩm');
//         return;
//     }
//     if (productName.length < 5 || productName.length > 100) {
//         console.log('Tên sản phẩm chỉ từ 10 đến 100 ký tự');
//         toastDanger('Lỗi', 'Tên sản phẩm chỉ từ 10 đến 100 ký tự');
//         return;
//     }
//
//
//     // validate loại sản phẩm & thuộc tính sản phẩm
//     if (productCategory === null || productCategory === undefined || productCategory === '') {
//         console.log('Vui lòng chọn danh mục sản phẩm');
//         toastDanger('Lỗi', 'Vui lòng chọn danh mục sản phẩm');
//         return;
//     }
//
//     // validate mô tả chi tiết sản phẩm
//     if (productDescription === null || productDescription === undefined || productDescription === '') {
//         console.log('Vui lòng nhập mô tả chi tiết sản phẩm');
//         toastDanger('Lỗi', 'Vui lòng nhập mô tả chi tiết sản phẩm');
//         return;
//     }
//
//
//
//     if(check === 0){
//         console.log('Vui lòng chọn dung lượng');
//         toastDanger('Lỗi', 'Vui lòng chọn dung lượng');
//         return;
//     }
//
//
//
//
//     return {
//         "nameProduct": productName,
//         "categoryId" : productCategory,
//         "note": productDescription,
//         "image": productImages,
//         "attributeRequestedit": {
//             "operatingSystem" : getElementProductOs,
//             "screen" : getElementProductScreen,
//             "chip" : getElementProductChip,
//             "cam" : getElementProductCamSau,
//             "pin" : getElementProductPin,
//             "ram" : productRam,
//             "id" : $('#ip-edit-product-attributeId').val()
//         },
//         romRequestAdds
//     };
// }
//
//
//
// function onClickAddProduct() {
//     dataImageUploadEdit = [];
// }
//
// function onClickEditProduct(e) {
//     dataImageUploadEdit = [];
//     let id = e.dataset.id;
//     // get element mã sản phẩm
//     let getElementButtonEditProduct = document.getElementById('btn-submit-edit-product');
//     if (getElementButtonEditProduct !== null) {
//         getElementButtonEditProduct.dataset.id = id
//     }
//     let getElementButtonEditProduct1 = document.getElementById('btn-submit-edit-product-status');
//     if (getElementButtonEditProduct1 !== null) {
//         getElementButtonEditProduct1.dataset.id = id
//     }
//
//     // get element tên sản phẩm
//     let getElementProductName = document.getElementById('ip-edit-product-name');
//
//     //get element id attribute
//     let getElementAttributeID = document.getElementById('ip-edit-product-attributeId');
//
//     // get element he dieu hanh
//     let getElementProductOs = document.getElementById('ip-edit-product-os');
//
//     // get element man hinh
//     let getElementProductScreen = document.getElementById('ip-edit-product-screen');
//
//     // get element chip
//     let getElementProductChip = document.getElementById('ip-edit-product-chip');
//
//     //get element cam truoc
//     let getElementProductCamTruoc = document.getElementById('ip-edit-product-cam_sau')
//
//
//     //get element sim
//     let getElementProductSim = document.getElementById('ip-edit-product-sim')
//
//     //get element pin sac
//     let getElementProductPin = document.getElementById('ip-edit-product-pin')
//
//     // get mô tả chi tiết sản phẩm
//     let productDescription = CKEDITOR.instances['productEditDescription'].getData();
//
//     // get element danh muc
//     let productCategory = document.getElementById('ip-edit-product-category');
//
//     // get element ram
//     let productRam = document.getElementById('ip-edit-product-ram');
//
//     let romDetail = document.getElementById("rom-product-detail");
//     while (romDetail.lastElementChild) {
//         romDetail.removeChild(romDetail.lastElementChild);
//     }
//
//     let colorRom = document.getElementById("color-rom");
//     while (colorRom.lastElementChild) {
//         colorRom.removeChild(colorRom.lastElementChild);
//     }
//
//     $("#product-detai-qty").val("");
//     $("#product-detai-priceString").val("");
//     $("#product-detai-price").val("");
//     $.ajax({
//         url: '/api/product/' + id,
//         method: 'GET',
//         success: function (data) {
//             console.log(data)
//             // set rom cho check box
//             for (let i = 0; i < 7; i++) {
//                 $("#edit-rom" + i).prop('checked', false);
//                 for (let j = 0; j < data.romRespones.length; j++) {
//                     if($("#edit-rom" + i).val() === data.romRespones[j].name){
//                         $("#edit-rom" + i).prop('checked', true);
//                         romDetail.innerHTML+='<option value="'+data.romRespones[j].id+'" >'+data.romRespones[j].name+' GB</option>'
//                         break;
//                     }
//                 }
//             }
//             if(data.status === 'ON'){
//                 document.getElementById("btn-submit-edit-product-status").innerHTML = "Ngừng kinh doanh"
//             }else {
//                 document.getElementById("btn-submit-edit-product-status").innerHTML = "Kinh doanh lại"
//             }
//             if(data.romRespones[0].productPropertyResponeList.length > 0){
//                 for (let i = 0; i < data.romRespones[0].productPropertyResponeList.length; i++) {
//                     colorRom.innerHTML+='<option value="'+data.romRespones[0].productPropertyResponeList[i].colorId+'" >'+data.romRespones[0].productPropertyResponeList[i].colorName+'</option>'
//                 }
//                 $("#product-detai-qty").val(data.romRespones[0].productPropertyResponeList[0].quantity);
//                 $("#product-detai-priceString").val(data.romRespones[0].productPropertyResponeList[0].priceString);
//                 $("#product-detai-price").val(data.romRespones[0].productPropertyResponeList[0].price);
//                 $("#name-product-detail").val(data.nameProduct);
//                 console.log(data.romRespones[0].productPropertyResponeList[0].status);
//                 $("#btn-submit-edit-product-detail-status").val("");
//                 if(data.romRespones[0].productPropertyResponeList[0].status === 'ON'){
//                     document.getElementById("btn-submit-edit-product-detail-status").innerHTML = "Ngừng kinh doanh"
//                 }else {
//                     document.getElementById("btn-submit-edit-product-detail-status").innerHTML = "Kinh doanh lại"
//                 }
//                 document.getElementById("updateDetail").style.display = "block";
//             }else {
//                 document.getElementById("updateDetail").style.display = "none";
//             }
//
//
//
//             if (getElementProductName !== null) {
//                 getElementProductName.value = data.nameProduct;
//             }
//             if(getElementProductOs !== null){
//                 $('#ip-edit-product-os').val(data.attributeRespone.operatingSystem).change();
//             }
//
//             if (getElementProductScreen !== null) {
//                 $('#ip-edit-product-screen').val(data.attributeRespone.screen).change();
//             }
//
//             if (getElementProductChip !== null) {
//                 $('#ip-edit-product-chip').val(data.attributeRespone.chip).change();            }
//
//             if (getElementProductCamTruoc !== null) {
//                 $('#ip-edit-product-cam_sau').val(data.attributeRespone.cam).change();
//             }
//
//             if (getElementProductPin !== null) {
//                 $('#ip-edit-product-pin').val(data.attributeRespone.pin).change();
//             }
//
//             if (productCategory !== null) {
//                 $('#ip-edit-product-category').val(data.categoryResponeDto.categoryId).change();
//             }
//             if(productRam !== null){
//                 $('#ip-edit-product-ram').val(data.attributeRespone.ram).change();
//             }
//             if(getElementAttributeID != null){
//                 getElementAttributeID.value = data.attributeRespone.id;
//             }
//
//
//
//             if(data.imageProduct !== null && data.imageProduct !== undefined){
//                 //dataImageUploadEdit.push(data.imageProduct);
//                 sizeImage = 0;
//                 let previewUpload = document.getElementById('previewUpload')
//                 let carouselIndicators = document.getElementById('carousel-indicators')
//                 if (previewUpload !== null && previewUpload !== undefined) {
//                     previewUpload.innerHTML = ''
//                     carouselIndicators.innerHTML = ''
//                 }
//                 addImagePreview(data.imageProduct)
//                 if (data.image.length > 0){
//                     for (let i = 0; i < data.image.length; i++) {
//                         addImagePreview(data.image[i].img_link);
//                         //dataImageUploadEdit.push(data.image[i].img_link);
//                     }
//                 }
//                 console.log(dataImageUploadEdit.length)
//             }
//
//             CKEDITOR.instances['productEditDescription'].setData(data.note);
//         },
//         error: function (jqXHR, textStatus, errorThrown) {
//             console.log(jqXHR)
//         }
//     })
// }
//
// function onClickDeleteProduct(e) {
//     let id = e.dataset.id;
//     const swalWithBootstrapButtons = Swal.mixin({
//         customClass: {
//             confirmButton: 'btn btn-success mx-2',
//             cancelButton: 'btn btn-danger mx-2'
//         },
//         buttonsStyling: false
//     })
//     swalWithBootstrapButtons.fire({
//         title: `Bạn chắc chắn muốn xoá <br> sản phẩm này chứ?`,
//         text: "Chú ý không thể hoàn tác sau khi thực hiện tác vụ này!",
//         icon: 'warning',
//         showCancelButton: true,
//         confirmButtonText: 'Vâng, tôi chắc chắn!',
//         cancelButtonText: 'Không, huỷ bỏ tác vụ!',
//         reverseButtons: true
//     }).then((result) => {
//         if (result.isConfirmed) {
//             swalWithBootstrapButtons.fire(
//                 'Đã xoá thành công!',
//                 'Dữ liệu đã bị xoá.',
//                 'success'
//             ).then(
//                 $.ajax({
//                     url: `/api/product/` + id,
//                     type: 'DELETE',
//                     contentType: 'application/json',
//                     success: function (result) {
//                         window.location.href = "/product";
//                     },
//                     error: function (error) {
//                         console.log(error);
//                     }
//                 })
//             );
//         } else if (
//             result.dismiss === Swal.DismissReason.cancel
//         ) {
//             swalWithBootstrapButtons.fire(
//                 'Đã huỷ tác vụ',
//                 'Dữ liệu được bảo toàn :)',
//                 'error'
//             )
//         }
//     })
// }
//
// function onClickUpdateProductStatus(e) {
//     let id = e.dataset.id;
//     var check = document.getElementById("btn-submit-edit-product-status").innerText;
//     console.log(check);
//     if(check === "Ngừng kinh doanh"){
//         $.ajax({
//             url: `/api/product/status/` + id + `/OFF`,
//             type: 'PUT',
//             contentType: 'application/json',
//             success: function (result) {
//                 Swal.fire({
//                     position: 'center',
//                     icon: 'success',
//                     title: 'Cập nhật thành công',
//                     showConfirmButton: true,
//                     timerProgressBar: true,
//                     timer: 3000
//                 }).then(()=> window.location.reload());
//             },
//             error: function (error) {
//                 toastDanger('Thất bại', 'Lỗi hệ thống')
//             }
//         })
//     }else {
//         $.ajax({
//             url: `/api/product/status/` + id + `/ON`,
//             type: 'PUT',
//             contentType: 'application/json',
//             success: function (result) {
//                 Swal.fire({
//                     position: 'center',
//                     icon: 'success',
//                     title: 'Cập nhật thành công',
//                     showConfirmButton: true,
//                     timerProgressBar: true,
//                     timer: 3000
//                 }).then(()=> window.location.reload());
//             },
//             error: function (error) {
//                 toastDanger('Thất bại', 'Lỗi hệ thống')
//             }
//         })
//     }
//
//
// }
//
// function onStatusChange(element) {
//     const value = $(element).val();
//     const tagAll = $('#datatable tbody tr');
//     const tag = $('tbody tr td span').filter(function () {
//         return $(this).text().includes(value);
//     });
//     if (value === '') {
//         tagAll.show();
//     } else {
//         tagAll.hide();
//         tag.parent().parent().show();
//     }
// }
//
//
//
// function onChangeQuantity(e) {
//     let value = e.value;
//     if (value === null || value === undefined || value === '' || !Number(value) || Number(value) < 0) {
//         e.value = 0;
//     } else if (Number(value) < Number(e.dataset.min)) {
//         e.value = Number(e.dataset.min);
//     } else {
//         e.value = Number(value);
//     }
// }
//
// function onClickSubmitAddOrderInvoice(e) {
//     let getOrderCode = document.getElementById('ip-order-invoice-code');
//     let orderCode;
//     if (getOrderCode !== null && getOrderCode !== undefined) {
//         orderCode = getOrderCode.value;
//         if (orderCode === null || orderCode === undefined || orderCode === '') {
//             toastDanger('Lỗi', 'Vui lòng nhập mã đặt hàng');
//             return;
//         }
//         if (orderCode.length < 5 || orderCode.length > 50) {
//             toastDanger('Lỗi', 'Mã đặt hàng từ 5 đến 50 ký tự');
//             return;
//         }
//         let check  = false;
//         $.ajax({
//             url: '/api/order-invoice/code/' + orderCode,
//             method: 'GET',
//             success: function (data) {
//                 if(data === "ok"){
//                     toastDanger('Lỗi', 'Trùng mã đơn hàng');
//                 }
//             },
//             error: function (error) {
//                 toastDanger('Lỗi', error);
//
//             }
//         })
//
//         if(check){
//             return;
//         }
//     }
//     let getDate = document.getElementById('ip-order-invoice-date');
//     let orderDate;
//     if (getDate !== null && getDate !== undefined) {
//         orderDate = getDate.value;
//         if (orderDate === null || orderDate === undefined || orderDate === '') {
//             toastDanger('Lỗi', 'Vui lòng chọn ngày hẹn nhận');
//             return;
//         }
//     }
//     let getSupplier = document.getElementById('ip-order-invoice-supplier');
//     let idSupplier;
//     if (getSupplier !== null && getSupplier !== undefined) {
//         idSupplier = getSupplier.value;
//         if (idSupplier === null || idSupplier === undefined) {
//             toastDanger('Lỗi', 'Vui long chọn nhà cung cấp');
//             return;
//         }
//     }
//     let getNote = document.getElementById('ip-order-invoice-note');
//     let note;
//     if (getNote !== null && getNote !== undefined) {
//         note = getNote.value;
//     }
//      let lstDetails = [];
//     let getTrProduct = document.getElementsByClassName('tr-order-add-product');
//     if(e === 0){
//         if (getTrProduct !== null && getTrProduct !== undefined) {
//             for (const $tr of getTrProduct) {
//                 if($tr.childNodes[9].childNodes[0].value <= 0){
//                     toastDanger("Lỗi", "Vui lòng nhập số lượng lớn hơn 0");
//                     return;
//                 }
//                 lstDetails.push({
//                     "romId" : $tr.childNodes[7].childNodes[0].value,
//                     "quantityInvoice" : $tr.childNodes[9].childNodes[0].value,
//                     "colorId" : $tr.childNodes[5].childNodes[1].value
//                 });
//             }
//         }
//     }else {
//         if (getTrProduct !== null && getTrProduct !== undefined) {
//             for (const $tr of getTrProduct) {
//                 if($tr.childNodes[9].childNodes[1].value <= 0){
//                     continue;
//                 }
//                 lstDetails.push({
//                     "romId" : $tr.childNodes[7].childNodes[1].value,
//                     "quantityInvoice" : $tr.childNodes[9].childNodes[1].value,
//                     "colorId" : $tr.childNodes[5].childNodes[1].value
//                 });
//             }
//         }
//     }
//
//
//     console.log(lstDetails);
//     if (lstDetails.length === 0) {
//         toastDanger('Lỗi', 'Vui lòng nhập số lượng ít nhất 1 sản phẩm');
//         return;
//     }
//     for (let i = 0; i < lstDetails.length; i++) {
//         for (let j = 0; j < lstDetails.length; j++) {
//             if(lstDetails[i].romId === lstDetails[j].romId && i !== j && lstDetails[i].colorId === lstDetails[j].colorId){
//                 toastDanger('Lỗi', 'Đã bị trùng sản phẩm xin vui lòng nhập lại');
//                 return;
//             }
//         }
//     }
//
//
//     let orderInvoice = {
//         "note" : note,
//         "receiveDate" : orderDate,
//         "suppliderId" : idSupplier,
//         "orderCode" : orderCode,
//         "detailRequest" : lstDetails
//     };
//
//     $.ajax({
//         url: '/api/order-invoice',
//         method: 'POST',
//         contentType: 'application/json;charset=UTF-8',
//         data: JSON.stringify(orderInvoice),
//         success: function (data) {
//             toastSuccess('Thành công', 'Đã tạo mới hoá đơn đặt hàng NCC');
//             $('.btn-close').click();
//             setTimeout(function () {
//                 location.reload();
//             }, 2000)
//         },
//         error: function (error) {
//             toastDanger('Lỗi', error);
//         }
//     })
//
// }
//
//
// function addOrderProduct(id){
//         $.ajax({
//             url: '/api/product/' + id,
//             method: 'GET',
//             success: function (data) {
//                 toastSuccess("Thành công","Thêm sản phẩm để đặt thành công");
//                 console.log(data);
//                 const selectTemp = document.getElementById("romProduct");
//                 const selectColor = document.getElementById("color");
//                 for (let i = 0; i < data.romRespones.length; i++) {
//                         selectTemp.innerHTML+='<option value="'+data.romRespones[i].id+'" >'+data.romRespones[i].name+'</option>'
//                 }
//                 document.getElementById("sptemp").setAttribute("hidden", true);
//                 selectColor.removeAttribute("id");
//                 selectColor.removeAttribute("hidden");
//                 selectTemp.removeAttribute("id");
//                 selectTemp.removeAttribute("hidden");
//                 document.getElementById("bodyAddProductOrderInvoice").innerHTML+='<tr class="tr-order-add-product">\n' +
//                     '                                        <td>'+ data.nameProduct+'</td>\n' +
//                     '                                        <td>\n' +
//                     '                                            <a onclick="copy_row(this)" type="button" \n' +
//                     '                                               class="btn btn-primary btn-sm"\n' +
//                     '                                               >\n' +
//                     '                                                +1\n' +
//                     '                                            </a>\n' +
//                     '                                            <a onclick="delete_row(this)" href="#"\n' +
//                     '                                               class="btn btn-danger btn-sm waves-effect waves-light"\n' +
//                     '                                              >\n' +
//                     '                                                Xóa\n' +
//                     '                                            </a>\n' +
//                     '                                        </td>\n' +
//                     '                                        <td> \n' +
//                                                                 selectColor.outerHTML +
//                     '                                        </td>\n' +
//                     '                                        <td>' + selectTemp.outerHTML +'</td>\n' +
//                     '                                        <td colspan="1" class="ip-edit-quantity text-end border-1"><input class="form-control-sm quantity-product" type="number" data-min="1"\n' +
//                     '                                                   value="0" onchange="onChangeQuantity(this)"/></td>\n' +
//                     '                                    </tr>';
//
//                 selectTemp.setAttribute("id", "romProduct");
//                 selectTemp.setAttribute("hidden", true);
//                 selectColor.setAttribute("hidden", true);
//                 selectColor.setAttribute("id", "color");
//                 while (selectTemp.firstChild) {
//                     selectTemp.firstChild.remove();
//                 }
//             },
//             error: function (jqXHR, textStatus, errorThrown) {
//                 console.log(jqXHR)
//             }
//         })
//
// }
//
// function changeColor(){
//     document.getElementById("btn-submit-edit-product-detail-status").innerHTML = "";
//     var romId = $("#rom-product-detail").find(":selected").val();
//     console.log(romId);
//     var colorId = $("#color-rom").find(":selected").val();
//     var status = document.getElementById("btn-submit-edit-product-detail-status").innerText;
//     console.log(colorId);
//     var request = {
//         "romId" : romId,
//         "colorId" : colorId
//     }
//     $.ajax({
//         url : "api/product-property",
//         method : "POST",
//         contentType: 'application/json',
//         data: JSON.stringify(request),
//         success : function (data) {
//             console.log(data)
//             if(data.status === 'ON'){
//                 document.getElementById("btn-submit-edit-product-detail-status").innerHTML = "Ngừng kinh doanh"
//             }else {
//                 document.getElementById("btn-submit-edit-product-detail-status").innerHTML = "Kinh doanh lại"
//             }
//             $("#product-detai-qty").val(data.quantity);
//             $("#product-detai-price").val(data.price);
//             $("#product-detai-priceString").val(data.priceString);
//         },
//         error : function (error) {
//             console.log(error)
//         }
//     })
//
// }
//
// function changeRom(){
//     document.getElementById("btn-submit-edit-product-detail-status").innerHTML = "";
//     var romId = $("#rom-product-detail").find(":selected").val();
//     console.log(romId);
//
//     var request = {
//         "romId" : romId
//     }
//     let colorRom = document.getElementById("color-rom");
//     while (colorRom.lastElementChild) {
//         colorRom.removeChild(colorRom.lastElementChild);
//     }
//     $.ajax({
//         url : "api/product-property",
//         method : "POST",
//         contentType: 'application/json',
//         data: JSON.stringify(request),
//         success : function (data) {
//             console.log(data)
//             if(data === 'false'){
//                 toastDanger("Lỗi", "Sản phẩm này chưa có màu, số lượng với giá, vui lòng chọn loại khác");
//                 $("#product-detai-qty").val(0);
//                 $("#product-detai-price").val(0);
//                 $("#product-detai-priceString").val('0 đ');
//                 return;
//             }
//             if(data[0].status === 'ON'){
//                 document.getElementById("btn-submit-edit-product-detail-status").innerHTML = "Ngừng kinh doanh"
//             }else {
//                 document.getElementById("btn-submit-edit-product-detail-status").innerHTML = "Kinh doanh lại"
//             }
//             $("#product-detai-qty").val(data[0].quantity);
//             $("#product-detai-price").val(data[0].price);
//             $("#product-detai-priceString").val(data[0].priceString);
//             for (var  i = 0; i < data.length; i++){
//                 colorRom.innerHTML+='<option value="'+data[i].colorId+'" >'+data[i].colorName+'</option>'
//             }
//
//         },
//         error : function (error) {
//             console.log(error)
//         }
//     })
//
// }
//
// function onClickUpdateProductStatusDetail() {
//     var romId = $("#rom-product-detail").find(":selected").val();
//     var colorId = $("#color-rom").find(":selected").val();
//     console.log(document.getElementById("btn-submit-edit-product-detail-status").innerText)
//     console.log(romId);
//     console.log(colorId);
//
//     var request = {
//         "romId" : romId,
//         "colorId" : colorId,
//         "status" : document.getElementById("btn-submit-edit-product-detail-status").innerText
//     }
//
//     $.ajax({
//         url : "api/product-property/status",
//         method : "PUT",
//         contentType: 'application/json',
//         data: JSON.stringify(request),
//         success : function (data) {
//             toastSuccess("Thành công", "Cập nhập thành công");
//             if(document.getElementById("btn-submit-edit-product-detail-status").innerText === 'Kinh doanh lại'){
//                 document.getElementById("btn-submit-edit-product-detail-status").innerText = 'Ngừng kinh doanh'
//             }else {
//                 document.getElementById("btn-submit-edit-product-detail-status").innerText = 'Kinh doanh lại'
//             }
//         },
//         error : function (error) {
//             console.log(error)
//             toastDanger("Thất bại", "Lỗi hệ thống");
//         }
//     })
//
// }
//
// function updateDetail(){
//     if($("#product-detai-price").val().length === 0){
//         toastDanger("Lỗi", "Không được để trống giá")
//         return;
//     }
//     if($("#product-detai-qty").val().length === 0){
//         toastDanger("Lỗi", "Không được để trống số lượng")
//         return;
//     }
//     var romId = $("#rom-product-detail").find(":selected").val();
//     console.log(romId);
//     var colorId = $("#color-rom").find(":selected").val();
//     console.log(colorId);
//
//     var request = {
//         "romId" : romId,
//         "colorId" : colorId,
//         "quantity" : $("#product-detai-qty").val(),
//         "price" : $("#product-detai-price").val()
//     }
//     $.ajax({
//         url : "api/product-property",
//         method : "PUT",
//         contentType: 'application/json',
//         data: JSON.stringify(request),
//         success : function (data) {
//             console.log(data)
//             $("#product-detai-qty").val(data.quantity);
//             $("#product-detai-price").val(data.price);
//             $("#product-detai-priceString").val(data.priceString)
//             $("#product-detai-priceString").val(data.priceString);
//             toastSuccess("Thành công", "Cập nhập thành công");
//         },
//         error : function (error) {
//             console.log(error)
//             toastDanger("Thất bại", "Lỗi hệ thống");
//         }
//     })
// }
//
//
// function delete_row(e) {
//     e.parentNode.parentNode.parentNode.removeChild(e.parentNode.parentNode);
// }
//
// function copy_row(e){
//     var tr = e.parentNode.parentNode.cloneNode(true);
//     document.getElementById("bodyAddProductOrderInvoice").appendChild(tr)
// }
//
// function onclickDetail(){
//     $('#modal-edit-product').modal('hide');
// }
//
