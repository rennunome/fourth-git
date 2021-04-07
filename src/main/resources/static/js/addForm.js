/**
 * フォームの追加ボタン
 */
var i = 1 ;
function addForm() {
  var input_data = document.createElement('input');
  input_data.type = 'text';
  input_data.id = 'answer_' + i;
  //input_data.placeholder = 'フォーム-' + i;
  var parent = document.getElementById('form_area');
  parent.appendChild(input_data);
  i++ ;
document.qaForm.submit();
}