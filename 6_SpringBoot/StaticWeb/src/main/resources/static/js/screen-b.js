// テキストボックスに入力された値を出力する処理を定義
const clickedTextButton = document.getElementById('text-button');
	
clickedTextButton.addEventListener('click', () => {
	const textOutput = document.getElementById('text-output');
	const textInputValue = document.getElementById('text-input').value;
	if (textInputValue) {
		textOutput.textContent = textInputValue;		
	}
});

// テキストエリアに入力された値を出力する処理を定義
const clickedTextareaButton = document.getElementById('textarea-button');

clickedTextareaButton.addEventListener('click', () => {
	const textareaOutput = document.getElementById('textarea-output');
	const textAreaValue = document.getElementById('textarea-input').value;
	if (textAreaValue) {
		textareaOutput.textContent = textAreaValue;	
	}
});

// ラジオボタンで選択された箇所を出力する処理を定義
const clickedRadioInputButton = document.getElementById('radio-button');

clickedRadioInputButton.addEventListener('click', () => {
	const radioOutput = document.getElementById('radio-output');
	radioOutput.textContent = document.querySelector('input[name="radio-input"]:checked').value;
});

// チェックボックスで選択された箇所を出力する処理を定義
const clickedCheckboxButton = document.getElementById('checkbox-button');

clickedCheckboxButton.addEventListener('click', () => {
	const checkboxOutput = document.getElementById('checkbox-output');
	const el = document.querySelectorAll('input[name="fruits"]:checked');

	if(el.length == 0) {
		checkboxOutput.textContent = '未選択';
	} else if (el.length == 1) {
	    checkboxOutput.textContent = el[0].value;
	} else {
	    checkboxOutput.textContent = el[0].value + el[1].value;
	}
})

// セレクトボックスで選択された箇所を出力する処理を定義
const clickedSelectboxButton = document.getElementById('selectbox-button');

clickedSelectboxButton.addEventListener('click', () => {
	const selectboxInput = document.getElementById('selectbox-input');
	const selectedboxOutput = document.getElementById('selectbox-output');
	selectedboxOutput.textContent = selectboxInput.value;
	
})