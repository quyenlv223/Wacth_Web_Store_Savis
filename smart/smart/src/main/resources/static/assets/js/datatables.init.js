$(document).ready(function() {
    let href = window.location.href
    if(href.indexOf('/orders') < 0 && href.indexOf('/notification') < 0) {
        var table = $("#datatable").DataTable({
            paging: false,
            // pageLength: 10,
            // lengthMenu: [[5, 10, 20, -1], [5, 10, 20, 'Todos']],
            lengthChange: !1,
            buttons: [{
                extend: 'copy',
                text: '<i class="fas fa-copy"></i>',
                titleAttr: 'Sao chép'
            },
                {
                    extend: 'excel',
                    text: '<i class="fas fa-file-excel"></i>',
                    titleAttr: 'Excel'
                },
                {
                    extend: 'pdfHtml5',
                    orientation: 'landscape',
                    pageSize: 'LEGAL',
                    text: '<i class="fas fa-file-pdf"></i>',
                    titleAttr: 'PDF'
                },
                {
                    extend: 'colvis',
                    text: '<i class="fas fa-filter"></i>',
                    titleAttr: 'Hiển thị tuỳ chỉnh'
                }],

            scrollY: "58vh",
            scrollX: true,
            scrollCollapse: true,

            "columnDefs": [{
                "searchable": false,
                "orderable": false,
                "targets": [0, 1],
            }],

            "order": [[5, 'asc']],

            info: false,
        });
        table.buttons().container().appendTo("#datatable_wrapper .col-md-6:eq(0)"),
            $(".dataTables_length select").addClass("form-select form-select-sm");
        $("body").find(".dataTables_scrollBody").addClass("scrollbar");

        table.on('order.dt search.dt', function () {
            table.column(0, {search: 'applied', order: 'applied'}).nodes().each(function (cell, i) {
                cell.innerHTML = i + 1;
            });
        }).draw();
    }
});