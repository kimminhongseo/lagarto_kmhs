let fileIdx = 0; /*[- 파일 인덱스 처리용 전역 변수 -]*/
const writeFrmElem = document.querySelector('#writeFrm');

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

// function removeFile(elem) {
//
//     const prevTag = $(elem).prev().prop('tagName');
//     if (prevTag === 'BUTTON') {
//         const file = $(elem).prevAll('input[type="file"]');
//         const filename = $(elem).prevAll('input[type="text"]');
//         file.val('');
//         filename.val('파일 찾기');
//         return false;
//     }
//
//     const target = $(elem).parents('div[data-name="fileDiv"]');
//     target.remove();
// }

writeFrmElem.addEventListener('submit', (e) => {
    const titleVal = writeFrmElem.title.value;
    const ctntVal = writeFrmElem.ctnt.value;
    if(titleVal.length < 1 || titleVal === '') {
        alert('제목을 입력해 주세요.');
        e.preventDefault();
    }
    if(ctntVal.length < 1 || ctntVal === '') {
        alert('내용을 입력해 주세요.');
        e.preventDefault();
    }
});

    function changeFilename(file) {

    file = $(file);
    const filename = file[0].files[0].name;
    const target = file.prevAll('input');
    target.val(filename);
}

function registerBoard(form) {

    form.noticeYn.value = form.noticeYn.checked === false ? 'N' : 'Y';
    form.secretYn.value = form.secretYn.checked === false ? 'N' : 'Y';


    var result = (
        isValid(form.title, "제목", null, null)
        && isValid(form.content, "내용", null, null)
    );

    if (result === false) {
        return false;
    }
}

/**
 * field의 값이 올바른 형식인지 체크 (정규표현식 사용)
 *
 * @param field - 타겟 필드
 * @param fieldName - 필드 이름 (null 허용)
 * @param focusField - 포커스할 필드 (null 허용)
 * @returns 메시지
 */
function isValid(field, fieldName, focusField) {

    if (isEmpty(field.value) === true) {
        /* 종성으로 조사(을 또는 를) 구분 */
        const message = (charToUnicode(fieldName) > 0) ? fieldName + "을 확인해 주세요." : fieldName + "를 확인해 주세요.";
        field.focus();
        alert(message);
        return false;
    }

    return true;
}

/**
 * 문자열의 마지막 문자의 종성을 반환
 *
 * @param str - 타겟 문자열
 * @returns 문자열의 마지막 문자의 종성
 */
function charToUnicode(str) {
    return (str.charCodeAt(str.length - 1) - 0xAC00) % 28;
}

