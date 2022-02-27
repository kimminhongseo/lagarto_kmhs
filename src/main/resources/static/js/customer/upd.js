const fileList = []/*[[ ${fileList} ]]*/; /*[- 업로드 파일 리스트 -]*/
let fileIdx = isEmpty(fileList) ? 0 : fileList.length; /*[- 파일 인덱스 처리용 전역 변수 -]*/

function addFile() {

    const fileDivs = $('div[data-name="fileDiv"]');
    if (fileDivs.length > 2) {
        alert('파일은 최대 세 개까지 업로드 할 수 있습니다.');
        return false;
    }

    fileIdx++;

    const fileHtml = `
					<div data-name="fileDiv" class="form-group filebox bs3-primary">
						<label for="file_${fileIdx}" class="col-sm-2 control-label"></label>
						<div class="col-sm-10">
							<input type="text" class="upload-name" value="파일 찾기" readonly />
							<label for="file_${fileIdx}" class="control-label">찾아보기</label>
							<input type="file" name="files" id="file_${fileIdx}" class="upload-hidden" onchange="changeFilename(this)" />

							<button type="button" onclick="removeFile(this)" class="btn btn-bordered btn-xs visible-xs-inline visible-sm-inline visible-md-inline visible-lg-inline">
								<i class="fa fa-minus" aria-hidden="true"></i>
							</button>
						</div>
					</div>
				`;

    $('#btnDiv').before(fileHtml);
}

function removeFile(elem) {

    document.getElementById('changeYn').value = 'Y';

    const prevTag = $(elem).prev().prop('tagName');
    if (prevTag === 'BUTTON') {
        const file = $(elem).prevAll('input[type="file"]');
        const filename = $(elem).prevAll('input[type="text"]');
        file.val('');
        filename.val('파일 찾기');

        $(elem).prevAll('input[name="fileIdxs"]').remove();
        return false;
    }

    const target = $(elem).parents('div[data-name="fileDiv"]');
    target.remove();
}

function changeFilename(file) {

    document.getElementById('changeYn').value = 'Y';

    file = $(file);
    const filename = file[0].files[0].name;
    const target = file.prevAll('input.upload-name');
    target.val(filename);

    file.prevAll('input[name="fileIdxs"]').remove();
}

/**
 * 자료형에 상관없이 값이 비어있는지 확인
 *
 * @param value - 타겟 밸류
 * @returns true or false
 */
function isEmpty(value) {
    if (value == null || value == "" || value == undefined || (value != null && typeof value == "object" && !Object.keys(value).length)) {
        return true;
    }

    return false;
}

// function registerBoard(form) {
//
//     form.noticeYn.value = form.noticeYn.checked == false ? 'N' : 'Y';
//     form.secretYn.value = form.secretYn.checked == false ? 'N' : 'Y';
//
//     const result = (
//         isValid(form.title, "제목", null, null)
//         && isValid(form.content, "내용", null, null)
//     );
//
//     if ( result == false ) {
//         return false;
//     }

    // var idx = [[ ${board.idx} ]];
    // if (isEmpty(idx) == false) {
    //     // var queryString = /*[[ ${params.makeQueryString(params.currentPageNo)} ]]*/;
    //
    //     /*[- 쿼리 스트링을 오브젝트 형태로 변환 -]*/
    //     queryString = new URLSearchParams(queryString);
    //     queryString.forEach(function(value, key) {
    //         if (isEmpty(value) == false) {
    //             $(form).append('<input type="hidden" name="' + key + '" value="' + value + '" />');
    //         }
    //     });
    // }
// }