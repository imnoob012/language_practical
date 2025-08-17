// alert("test");

let outputElement;

window.onload = function() {
  const radioInputContainer = document.querySelector('.radio_input_container')
  // タグの属性の値を配列として定義
  const radioButtonData = [
    {id: 'radio_multiplied_by', value: 'multiplied_by', text: '*'},
    {id: 'radio_divided_by', value: 'divided_by', text: '/'}
  ]
  // 繰り返し処理で*,/の選択肢生成
  radioButtonData.forEach(function(data) {
    const inputElement = document.createElement('input')
    inputElement.setAttribute('type', 'radio')
    inputElement.setAttribute('id', data.id)
    inputElement.setAttribute('value', data.value)
    inputElement.setAttribute('name', 'radio_calc_type')

    const labelElement = document.createElement('label')
    labelElement.setAttribute('for', data.id)
    labelElement.textContent = data.text

    radioInputContainer.appendChild(inputElement)
    radioInputContainer.appendChild(labelElement)

  })

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