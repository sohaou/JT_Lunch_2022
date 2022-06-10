/**
 * 공지사항 모달 부가설정
 */
 // 만료 시간과 함께 데이터를 저장
function setItemWithExpireTime(keyName, keyValue, tts){
	// localStorage에 저장할 객체
	const obj = {
		value : keyValue,
		expire : Date.now() + tts
	}
	
	// 객체를 JSON 문자열로 변환
	const objString = JSON.stringify(obj);
	
	// setIten
	window.localStorage.setItem(keyName, objString);
}


 function notice(val){
	// localStorage 데이터 추가
	localStorage.setItem("popUpSet",val);
}