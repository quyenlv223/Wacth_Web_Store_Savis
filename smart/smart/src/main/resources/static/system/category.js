function onAdd(elm){
    let level = elm.getAttribute('level');
    let parentId = elm.getAttribute('parentId');
    $('#error')[0].style.display = 'none'
    $('#categoryName')[0].value= ''
    $('#addCategory')[0].style.display = 'unset';
    $('#levelCategory')[0].value = level;
    $('#categoryId')[0].value = null;
    let atbRequied = $('#atbRequied')[0];
    let containerAtb = $('#containerAtb')[0];
    containerAtb.innerHTML = '';
    containerAtb.appendChild(atbRequied);
}

// function onModalEditCategory(categoryId, level, parentId) {
//     $('#editCategory')[0].style.display = 'unset';
//     $('#parentIdEdit')[0].value = parentId;
//     $('#levelCategoryEdit')[0].value = level;
//     $('#categoryIdEdit')[0].value = categoryId;
//
//
// }

function onEdit(elm){
    let level = elm.getAttribute('level');
    let categoryId = elm.getAttribute("categoryId")
    let parentId = elm.getAttribute('parentId');

    $('#editCategory')[0].style.display = 'unset';
    $('#parentIdEdit')[0].value = parentId;
    $('#levelCategoryEdit')[0].value = level;
    $('#categoryIdEdit')[0].value = categoryId;

    $.ajax({
        url: '/category/' +categoryId,
        method: 'GET',
        success: function (datas) {
            console.log(datas)
            let containerAtbEdit = $('#containerAtbEdit')[0]
            containerAtbEdit.innerHTML = '';
            let btnDelete = '<button class="btn btn-danger" onclick="removeRowEditAtb(this)">-</button>'
            $('#categoryEditName')[0].value = datas.categoryName
            for(let i=0;i<datas.attributes.length;i++){
                console.log('oke')

                console.log(containerAtbEdit)
                containerAtbEdit.innerHTML += '<div class="row mb-3">\n' +
                    '                            <div class="col-lg-5">\n' +
                    '<input class="form-control atbEditId" type="hidden" value="'+datas.attributes[i].id+'">\n' +
                    '                                <input class="form-control atbEditName" value="'+datas.attributes[i].name+'" placeholder="Tên thuộc tính">\n' +
                    '                            </div>\n' +
                    '                            <div class="col-lg-6">\n' +
                    '                                <input class="form-control atbEditValue" value="'+datas.attributes[i].value+'" placeholder="Giá trị mặc định 1; giá trị 2; giá trị 3;...">\n' +
                    '                            </div>\n' +
                    '                            <div class="col-lg-1 btnRemoveEditAtb" style="text-align: center">\n' +
                    btnDelete +
                    '                            </div>\n' +
                    '                        </div>'
            }
        },
        error: function (error) {
            console.log(error)
        }
    })
}

function closeModalCategory(){
    $('#addCategory')[0].style.display = 'none';
}

function saveCategory(){

    let categoryName = $('#categoryName')[0].val
    if(categoryName.length === 0){
        toastDanger("Lỗi", "Vui lòng nhập tên hãng");
        return;
    }

    $.ajax({
        url: '/category',
        method: 'POST',
        data: JSON.stringify({
            categoryName: categoryName
        }),
        contentType: 'application/json',
        success: function (datas) {
            console.log(datas)
            $('#error')[0].style.display = 'none'
            window.location.href = '/cate';
        },
        error: function (error) {
            $('#error')[0].style.display = 'unset'
            $('#error')[0].innerHTML = error.responseJSON.vn
        }
    })
}

function saveEditCategory(){
    let atbEditIdInputs = $('.atbEditId');
    let atbEditNameInputs = $('.atbEditName');
    let atbEditValueInputs = $('.atbEditValue');

    let atbArr = [];
    let level = $('#levelCategoryEdit')[0].value;
    let categoryId = $('#categoryIdEdit')[0].value;
    let categoryName = $('#categoryEditName')[0].value
    let parentId = $('#parentIdEdit')[0].value

    for(let i=0;i<atbEditIdInputs.length;i++){
        let obj = {
            id: atbEditIdInputs[i].value,
            name: atbEditNameInputs[i].value,
            value:atbEditValueInputs[i].value
        }
        if(obj.name.trim().length > 0){
            atbArr.push(obj)
        }
    }

    let objUpdate = {
        categoryId: categoryId,
        categoryName: categoryName,
        attributes: atbArr,
        parentId: parentId
    }

    $.ajax({
        url: '/category',
        method: 'PUT',
        data: JSON.stringify(objUpdate),
        contentType: 'application/json',
        success: function (datas) {
            console.log(datas)
            window.location.href = '/category?message=success&level=' + level;
        },
        error: function (error) {
            console.log(error)
        }
    })
}

window.onload = function () {
    let href = window.location.href;
    if(href.indexOf('message=success') > -1){
        toastSuccess("Thành công", "Cập nhật thành công.")
    }

    let level = href.substring(href.length-1, href.length);
    let tab = $('.tab_lv' + level)
    if(tab != null){
        tab.click()
    }
}

function onRemove(elm){
    let categoryId = elm.getAttribute('categoryId');
    let comfirm = confirm("Bạn có chắc muốn xóa danh mục này?")
    console.log(comfirm)
    console.log(categoryId)
    if(comfirm){
        $.ajax({
            url: '/category/' +categoryId,
            method: 'DELETE',
            success: function (datas) {
                window.location.href = '/category?message=success';
            },
            error: function (error) {
                console.log(error)
            }
        })
    }
}

function closeModalEditCategory() {
    $('#editCategory')[0].style.display = 'none';
}

// QuotationDetail(
//     commodity=Commodity(
//         commodityType=CommodityType(
//             commodities=null,
//             id=1,
//             name=?????????????,
//             clinicId=1,
//             isEnabled=true,
//             createdAt=Fri Sep 27 19:39:46 ICT 2019,
//             updatedAt=Fri Sep 27 19:39:46 ICT 2019,
//             exclusive=0, shortName=P,
//             contentType=0
//         ),
//         contractType=null,
//         operationType=null,
//         referralReservations=[],
//         referralDiscountSettings=[],
//         discountSets=[],
//         commodityBodyRegionTypes=[
//             CommodityBodyRegionType(
//                 bodyRegionType=null,
//                 id=50573,
//                 commodityId=50068,
//                 bodyRegionTypeId=4,
//                 clinicId=1,
//                 isEnabled=true,
//                 createdAt=Tue Mar 17 11:24:15 ICT 2020,
//                 updatedAt=Tue Mar 17 11:24:15 ICT 2020,
//                 exclusive=0
//             ),
//             CommodityBodyRegionType(
//                 bodyRegionType=null,
//                 id=50574,
//                 commodityId=50068,
//                 bodyRegionTypeId=5,
//                 clinicId=1,
//                 isEnabled=true,
//                 createdAt=Tue Mar 17 11:24:15 ICT 2020,
//                 updatedAt=Tue Mar 17 11:24:15 ICT 2020,
//                 exclusive=0
//             ),
//             CommodityBodyRegionType(
//                 bodyRegionType=null,
//                 id=50575,
//                 commodityId=50068,
//                 bodyRegionTypeId=2,
//                 clinicId=1,
//                 isEnabled=true,
//                 createdAt=Tue Mar 17 11:24:15 ICT 2020,
//                 updatedAt=Tue Mar 17 11:24:15 ICT 2020,
//                 exclusive=0
//             ),
//             CommodityBodyRegionType(
//                 bodyRegionType=null,
//                 id=50576,
//                 commodityId=50068,
//                 bodyRegionTypeId=3,
//                 clinicId=1,
//                 isEnabled=true,
//                 createdAt=Tue Mar 17 11:24:15 ICT 2020,
//                 updatedAt=Tue Mar 17 11:24:15 ICT 2020, exclusive=0
//             )
//         ],
//         id=50068,
//         commodityTypeId=1,
//         contractTypeId=1,
//         operationTypeId=1,
//         name=?2????????VIO????or???,
//         unitPrice=35000.0,
//         maxTimes=5,
//         requiredOperationMinutes=60,
//         intervalWeeks=8,
//         contractEffectiveMonths=30,
//         isCounseling=false,
//         clinicId=1,
//         isEnabled=true,
//         createdAt=Tue Mar 17 11:24:15 ICT 2020,
//         updatedAt=Tue Mar 17 11:24:15 ICT 2020,
//         exclusive=0, minTimes=5,
//         timesIncrementUnit=0,
//         expirationExtensionUnit=6,
//         isProcedure=false,
//         isExamination=false,
//         shortName=P,
//         finalPurchasedServiceAdjustment=0),
//         contractEffectiveDaysExtension=null,
//         purchasedServices=[
//             PurchasedService(
//                 operationReservation=
//                     OperationReservation(
//                         operationHistory=null,
//                         visitClinicHistory=null,
//                         operationRoom=
//                             OperationRoom(
//                                 operationType=null,
//                                 room=
//                                     Room(
//                                         counselingRooms=null,
//                                         operationRoom=null,
//                                         roomOpenDayOfWeekSettings=[],
//                                         store=
//                                             Store(
//                                                 clinic=null,
//                                                 id=1, name=???,
//                                                 clinicId=1,
//                                                 isEnabled=true,
//                                                 createdAt=Thu Sep 12 19:38:32 ICT 2019,
//                                                 updatedAt=Thu Sep 12 19:38:32 ICT 2019,
//                                                 exclusive=19,
//                                                 address=????????4-2-2?1????4?,
//                                                 telNo=03-3538-3309,
//                                                 bankItakushaCode=2001180001,
//                                                 bankItakushaName=?)??????,
//                                                 bankCode=0038,
//                                                 bankName=??SBI?????,
//                                                 bankBranchCode=106,
//                                                 bankBranchName=??????,
//                                                 bankYokinShumoku=1,
//                                                 bankAccount=1244600,
//                                                 googleMapUrl=https://goo.gl/maps/NxMsfNegSTL9FADLA,
//                                                 storeAreaId=1,
//                                                 mensAvailable=ONLY_COOL_SCALPTING
//                                             ),
//                                         id=17014,
//                                         storeId=1,
//                                         name=???,
//                                         clinicId=1,
//                                         isEnabled=true,
//                                         createdAt=Fri Oct 18 17:00:06 ICT 2019,
//                                         updatedAt=Fri Oct 18 17:00:06 ICT 2019,
//                                         exclusive=0,
//                                         reservable=true,
//                                         counseling=false,
//                                         operation=true,
//                                         procedure=false,
//                                         examination=false
//                                     ),
//                                     id=17014,
//                                     roomId=17014,
//                                     clinicId=1,
//                                     isEnabled=true,
//                                     createdAt=Fri Oct 18 17:00:06 ICT 2019,
//                                     updatedAt=Fri Oct 18 17:00:06 ICT 2019,
//                                     exclusive=0, operationTypeId=1
//                             ),
//                             purchasedService=null,
//                             getOperationReservationList=null,
//                             beforeOperationInquiryAnswers=null,
//                             id=471783,
//                             purchasedServiceId=694284,
//                             operationRoomId=17014,
//                             reservationDate=2022-03-24,
//                             beginTime=17:00,
//                             createdAt=Thu Mar 24 10:46:12 ICT 2022,
//                             status=FINISHED,
//                             operationReservationHistoryId=683293,
//                             updatedAt=Thu Mar 24 11:01:50 ICT 2022,
//                             exclusive=3, nurseId=null,
//                             remarks=,
//                             operationReservationGroupId=287621
//                     ),
//                     quotationDetail=null,
//                     contractEffectiveDaysExtension=null,
//                     id=694284, patientId=129180,
//                     quotationDetailId=601481,
//                     isCanceled=false,
//                     isTroubled=false,
//                     forceConsumeWorkerId=null,
//                     clinicId=1, isEnabled=true,
//                     createdAt=Thu Mar 24 10:44:37 ICT 2022,
//                     updatedAt=Thu Mar 24 10:44:37 ICT 2022,
//                     exclusive=0,
//                     forceConsumedDatetime=null,
//                     isRefunded=false,
//                     troubledPurchasedServiceId=null,
//                     troubledReason=null,
//                     isForceDigestioned=false,
//                     isCancelDigestioned=false,
//                     forceConsumedStoreId=null,
//                     isSumMoney=false
//             ),
//             PurchasedService(
//                 operationReservation=
//                     OperationReservation(
//                         operationHistory=null,
//                         visitClinicHistory=null,
//                         operationRoom=
//                             OperationRoom(
//                                 operationType=null,
//                                 room=
//                                     Room(
//                                         counselingRooms=null,
//                                         operationRoom=null,
//                                         roomOpenDayOfWeekSettings=[],
//                                         store=Store(
//                                             clinic=null, id=1, name=???,
//                                             clinicId=1, isEnabled=true,
//                                             createdAt=Thu Sep 12 19:38:32 ICT 2019,
//                                             updatedAt=Thu Sep 12 19:38:32 ICT 2019,
//                                             exclusive=19, address=????????4-2-2?1????4?,
//                                             telNo=03-3538-3309, bankItakushaCode=2001180001,
//                                             bankItakushaName=?
//                                         )??????,
//                                         bankCode=0038,
//                                         bankName=??SBI?????, bankBranchCode=106,
//                                         bankBranchName=??????, bankYokinShumoku=1,
//                                         bankAccount=1244600,
//                                         googleMapUrl=https://goo.gl/maps/NxMsfNegSTL9FADLA,
//                                         storeAreaId=1, mensAvailable=ONLY_COOL_SCALPTING
//                                         ),
//                                         id=17014, storeId=1, name=???, clinicId=1,
//                                         isEnabled=true, createdAt=Fri Oct 18 17:00:06 ICT 2019,
//                                         updatedAt=Fri Oct 18 17:00:06 ICT 2019, exclusive=0,
//                                         reservable=true, counseling=false, operation=true,
//                                         procedure=false, examination=false
//                                     ),
//                                 id=17014,
//                                 roomId=17014,
//                                 clinicId=1,
//                                 isEnabled=true,
//                                 createdAt=Fri Oct 18 17:00:06 ICT 2019,
//                                 updatedAt=Fri Oct 18 17:00:06 ICT 2019, exclusive=0,
//                                 operationTypeId=1
//                             ),
//                         purchasedService=null,
//                         getOperationReservationList=null,
//                         beforeOperationInquiryAnswers=null,
//                         id=471784, purchasedServiceId=694285,
//                         operationRoomId=17014,
//                         reservationDate=2022-03-24,
//                         beginTime=18:00, endTime=19:00,
//                         clinicId=1, createdAt=Thu Mar 24 11:05:33 ICT 2022,
//                         status=INVALID, operationReservationHistoryId=683294,
//                         updatedAt=Thu Mar 24 11:07:05 ICT 2022,
//                         exclusive=2, nurseId=null, remarks=,
//                         operationReservationGroupId=287622
//                     ),
//                 quotationDetail=null,
//                 contractEffectiveDaysExtension=null,
//                 id=694285, patientId=129180,
//                 quotationDetailId=601481,
//                 isCanceled=false, isTroubled=false,
//                 forceConsumeWorkerId=null, clinicId=1,
//                 isEnabled=true, createdAt=Thu Mar 24 10:44:37 ICT 2022,
//                 updatedAt=Thu Mar 24 10:44:37 ICT 2022, exclusive=0,
//                 forceConsumedDatetime=null, isRefunded=false,
//                 troubledPurchasedServiceId=null, troubledReason=null,
//                 isForceDigestioned=false, isCancelDigestioned=false,
//                 forceConsumedStoreId=null, isSumMoney=false
//             ),
//             PurchasedService(
//                 operationReservation=
//                     OperationReservation(
//                         operationHistory=null,
//                         visitClinicHistory=null,
//                         operationRoom=
//                             OperationRoom(
//                                 operationType=null,
//                                 room=
//                                     Room(
//                                         counselingRooms=null,
//                                         operationRoom=null,
//                                         roomOpenDayOfWeekSettings=[],
//                                         store=
//                                             Store(
//                                                 clinic=null, id=1, name=???, clinicId=1,
//                                                 isEnabled=true, createdAt=Thu Sep 12 19:38:32 ICT 2019,
//                                                 updatedAt=Thu Sep 12 19:38:32 ICT 2019, exclusive=19,
//                                                 address=????????4-2-2?1????4?, telNo=03-3538-3309,
//                                                 bankItakushaCode=2001180001, bankItakushaName=?
//                                             )??????,
//                                         bankCode=0038, bankName=??SBI?????,
//                                         bankBranchCode=106, bankBranchName=??????,
//                                         bankYokinShumoku=1, bankAccount=1244600,
//                                         googleMapUrl=https://goo.gl/maps/NxMsfNegSTL9FADLA,
//                                         storeAreaId=1, mensAvailable=ONLY_COOL_SCALPTING
//                                     ),
//                                     id=17014, storeId=1, name=???, clinicId=1,
//                                     isEnabled=true, createdAt=Fri Oct 18 17:00:06 ICT 2019,
//                                     updatedAt=Fri Oct 18 17:00:06 ICT 2019,
//                                     exclusive=0, reservable=true, counseling=false,
//                                     operation=true, procedure=false, examination=false
//                                 ),
//                             id=17014,
//                             roomId=17014,
//                             clinicId=1,
//                             isEnabled=true, createdAt=Fri Oct 18 17:00:06 ICT 2019,
//                             updatedAt=Fri Oct 18 17:00:06 ICT 2019,
//                             exclusive=0, operationTypeId=1
//                             ),
//                         purchasedService=null,
//                         getOperationReservationList=null,
//                         beforeOperationInquiryAnswers=null,
//                         id=471785, 
//                         purchasedServiceId=694286,
//                         operationRoomId=17014, 
//                         reservationDate=2022-03-24,
//                         beginTime=19:00, 
//                         endTime=20:00, 
//                         clinicId=1,
//                         createdAt=Thu Mar 24 11:09:47 ICT 2022, 
//                         status=FINISHED,
//                         operationReservationHistoryId=683295, 
//                         updatedAt=Thu Mar 24 11:20:05 ICT 2022, 
//                         exclusive=6, 
//                         nurseId=null, 
//                         remarks=,
//                         operationReservationGroupId=287623
//                     ),
//                 quotationDetail=null,
//                 contractEffectiveDaysExtension=null,
//                 id=694286, 
//                 patientId=129180, 
//                 quotationDetailId=601481,
//                 isCanceled=false, 
//                 isTroubled=false,
//                 forceConsumeWorkerId=null, 
//                 clinicId=1, 
//                 isEnabled=true,
//                 createdAt=Thu Mar 24 10:44:37 ICT 2022, 
//                 updatedAt=Thu Mar 24 10:44:37 ICT 2022, 
//                 exclusive=0,
//                 forceConsumedDatetime=null, 
//                 isRefunded=false, 
//                 troubledPurchasedServiceId=null, 
//                 troubledReason=null,
//                 isForceDigestioned=false, 
//                 isCancelDigestioned=false, 
//                 forceConsumedStoreId=null, 
//                 isSumMoney=false

//             ),
//             // số 5
//             PurchasedService(
//                 operationReservation=null,
//                 quotationDetail=null,
//                 contractEffectiveDaysExtension=null,
//                 id=694288,
//                 patientId=129180,
//                 quotationDetailId=601481,
//                 isCanceled=false,
//                 isTroubled=false,
//                 forceConsumeWorkerId=null,
//                 clinicId=1,
//                 isEnabled=true,
//                 createdAt=Thu Mar 24 10:44:37 ICT 2022,
//                 updatedAt=Thu Mar 24 10:44:37 ICT 2022,
//                 exclusive=0,
//                 forceConsumedDatetime=null,
//                 isRefunded=false,
//                 troubledPurchasedServiceId=null,
//                 troubledReason=null,
//                 isForceDigestioned=false,
//                 isCancelDigestioned=false,
//                 forceConsumedStoreId=null,
//                 isSumMoney=false
//             ),
//             // số 1
//             PurchasedService(
//                 operationReservation=null,
//                  quotationDetail=null,
//                  contractEffectiveDaysExtension=null,
//                  id=694287,
//                  patientId=129180,
//                  quotationDetailId=601481,
//                  isCanceled=false,
//                  isTroubled=false,
//                  forceConsumeWorkerId=1,
//                  clinicId=1,
//                  isEnabled=true,
//                  createdAt=Thu Mar 24 10:44:37 ICT 2022,
//                  updatedAt=Thu Mar 24 11:21:36 ICT 2022,
//                  exclusive=1,
//                  forceConsumedDatetime=Thu Mar 24 11:21:36 ICT 2022,
//                  isRefunded=false,
//                  troubledPurchasedServiceId=null,
//                  troubledReason=null,
//                  isForceDigestioned=false,
//                  isCancelDigestioned=true,
//                  forceConsumedStoreId=null,
//                  isSumMoney=false
//             ),

//         ],
//         quotation=null,
//         cancellationDetail=null,
//         id=601481, quotationId=371871,
//         commodityId=50068, purchasedServiceTime=5,
//         isCanceled=false, clinicId=1, isEnabled=true,
//         createdAt=Thu Mar 24 10:45:04 ICT 2022,
//         updatedAt=Thu Mar 24 10:45:04 ICT 2022,
//         exclusive=0,
//         contractEffectiveDays=915
//     )