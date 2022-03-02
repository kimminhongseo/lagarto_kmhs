function movePage(uri, queryString) {
    location.href = uri + queryString;
}

function searchBoard(form) {
    /*[- 드롭다운이 아닌 메인 검색 키워드로 검색했을 때 -]*/
    if (isEmpty(form) === true) {
        var searchKeyword = document.getElementById("mainSearchKeyword");
        if (isEmpty(searchKeyword.value) === true) {
            alert("키워드를 입력해 주세요.");
            searchKeyword.focus();
            return false;
        }

        form = document.getElementById("searchForm");
        form.searchKeyword.value = searchKeyword.value;
        form.submit();
    }

    if (isEmpty(form.searchKeyword.value) === true) {
        alert("키워드를 입력해 주세요.");
        form.searchKeyword.focus();
        return false;
    }
}