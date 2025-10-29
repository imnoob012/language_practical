	
	document.addEventListener('DOMContentLoaded', function() {
		// レコードを追加する処理を定義
		function addRow() {
			let table = document.getElementById('tableData').querySelector('tbody');
			let rowCount = document.getElementById('tableData').querySelector('tbody').getElementsByTagName('tr').length + 1;
			let newRow = table.insertRow();
	
			let cell1 = newRow.insertCell();
			cell1.textContent = rowCount;
			
			let cell2 = newRow.insertCell();
			cell2.innerHTML ='<input type="text">';
			
			let cell3 = newRow.insertCell();
			cell3.innerHTML = '<input type="number">';
			
			let cell4 = newRow.insertCell();
			cell4.innerHTML = '<button class="remove-row-btn">削除</button>';
		}
		
		const addButton = document.getElementById('addButton');
		
		if (addButton) {
			addButton.addEventListener('click', addRow);
		}
		
		// レコードを削除する処理を定義
		const dataTableBody = document.getElementById('tableData').querySelector('tbody');
		
		if (dataTableBody) {
			dataTableBody.addEventListener('click', (e) => {
				// 各レコードに対するクリックは、削除ボタンと入力欄の2つの内、入力を除くような変数を定義
				const clickedButton = e.target.closest('.remove-row-btn');
				
				if (clickedButton) {
					clickedButton.closest('tr').remove();
				}
			});
		}
		
		addRow();
	});


	