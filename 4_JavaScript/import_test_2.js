// alert("test");

let outputElement;

window.onload = function() {
  // ① *と/の選択肢を追加
  const inputElement_1 = document.createElement("input")
  inputElement_1.setAttribute('type', 'radio')
  inputElement_1.setAttribute('name', 'radio_calc_type')
  inputElement_1.setAttribute('id', 'radio_multiplied_by')
  inputElement_1.setAttribute('value', 'multiplied_by')
  const labelElement_1 =document.createElement("label")
  labelElement_1.setAttribute('for', 'radio_multiplied_by')
  labelElement_1.textContent = '*'

  const inputElement_2 = document.createElement("input")
  inputElement_2.setAttribute('type', 'radio')
  inputElement_2.setAttribute('name', 'radio_calc_type')
  inputElement_2.setAttribute('id', 'radio_divided_by')
  inputElement_2.setAttribute('value', 'divided_by')
  const labelElement_2 =document.createElement("label")
  labelElement_2.setAttribute('for', 'radio_divided_by')
  labelElement_2.textContent = '/'

  const radioInputContainer = document.querySelector('.radio_input_container')
  radioInputContainer.appendChild(inputElement_1)
  radioInputContainer.appendChild(labelElement_1)
  radioInputContainer.appendChild(inputElement_2)
  radioInputContainer.appendChild(labelElement_2)

  // 出力欄の要素作成
  outputElement = document.createElement('p')
  document.body.appendChild(outputElement)

  document.getElementById('submit').addEventListener('click', function () {
    submitButton()
  });
}


function submitButton() {
  let textForm1 = Number(document.getElementById('text_form_1').value)
  let textForm2 = Number(document.getElementById('text_form_2').value)
  let calcType = getCalcType()
  let calcResult = 0
  outputElement.textContent = ''

  switch (calcType) {
    case 'plus':
      calcResult = textForm1 + textForm2
      break;
    case 'minus':
      calcResult = textForm2 - textForm1
      break;
    case 'multiplied_by':
      calcResult = textForm1 * textForm2
      break;
    case 'divided_by':
      calcResult = textForm1 / textForm2
      break;
    default:
      outputElement.textContent = 'calc type was not selected'
      return false;
  }
  outputElement.textContent = '結果は' + calcResult + 'です。'
}

function getCalcType() {
  let calcType = null
  document.getElementsByName('radio_calc_type').forEach(function(e) {
    if(e.checked) {
      calcType = e.value
      // ラジオボタンにチェックが入っていたら、true
    }
  })
  return calcType
}