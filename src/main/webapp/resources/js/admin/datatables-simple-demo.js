window.addEventListener('DOMContentLoaded', event => {
    // Simple-DataTables
    // https://github.com/fiduswriter/Simple-DataTables/wiki

    var datatablesSimple = document.getElementById('datatablesSimple');
    if (datatablesSimple) {
        new simpleDatatables.DataTable(datatablesSimple);
    }
    
    //커스터마이징
    var dataTable = new simpleDatatables.DataTable(datatablesSimple,
    {
	    sortable : false,
	    searching : true,
	    paging : true,
	    perPage : 10,
	    labels: {
		    placeholder: "검색키워드 입력",
		    perPage: "",
		    noRows: "조회결과가 없습니다.",
		    info: "",
		}
	});
});
