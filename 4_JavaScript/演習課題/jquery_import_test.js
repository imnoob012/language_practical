$(function() {
  // ①* /の選択肢を追加
  $('.radio_input_container').append(
    '<input type="radio" name="radio_calc_type" id="radio_multiplied_by" value="multiplied_by">',
    '<label for="radio_multiplied_by">*</label>',
    '<input type="radio" name="radio_calc_type" id="radio_divided_by" value="divided_by">',
    '<label for="radio_divided_by">/</label>'
    )
  // 出力欄の要素を作成
  $('body').append('<p class="answer"></p>')

  $('#submit').on('click', function() {
    submitButton()
  })
});

function submitButton() {
  let textForm1 = Number($('#text_form_1').val())
  let textForm2 = Number($('#text_form_2').val())
  let calcType = $('input[name="radio_calc_type"]:checked').val()
  let calcResult = 0
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
      $('.answer').text('calc type was not selected')
      return false;
  }
  $('.answer').text('結果は' + calcResult + 'です。');
}