
// document.onload =  function () {

// }
function getNotification(){
    $.ajax({
        url: '/api/notification',
        method: 'GET',
        success: function (datas) {
            console.log(datas)
            let rows = new Array();
            let notReads = new Array();
            for(data of datas){
                if(!data.read){
                    notReads.push(data.id)
                }
                let button = ''
                if(data.type === "ORDER"){
                    button = '<a href="#" onclick="viewOrderNoti(\''+data.id+'\')" class="btn btn-primary" id="'+data.id+'">Chi tiết</a>'
                } else {
                    button = '<button onclick="viewDetail(\''+data.productId+'\',\''+data.id+'\')" class="btn btn-primary" id="'+data.id+'">Chi tiết</button>'
                }
                let row = {
                    btn: button,
                    content: data.content,
                    time: data.time
                }
                rows.push(row)
            }
            t1.clear().draw();
            t1.rows.add(rows).draw();

            console.log(notReads)
            for(let notRead of notReads){
                document.getElementById(notRead).parentNode.parentNode.style.fontWeight = '900';
            }

        },
        error: function (error) {
            console.log(error)
        }
    })
}

function viewDetail(productId, id){
    $.ajax({
        url: '/api/notification/read?id=' + id,
        method: 'GET',
        success: function (datas) {

        },
        error: function (error) {
            console.log(error)
        }
    })
    $.ajax({
        url: '/view-comment?productId=' + productId,
        method: 'GET',
        success: function (datas) {
            window.location.href = datas
        },
        error: function (error) {
            console.log(error)
        }
    })
}
function viewOrderNoti(id){
    $.ajax({
        url: '/api/notification/read?id=' + id,
        method: 'GET',
        success: function (datas) {
            window.location.href = '/orders'
        },
        error: function (error) {
            console.log(error)
        }
    })
}
