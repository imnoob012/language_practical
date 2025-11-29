// Usersテーブルのデータ全件取得処理
document.getElementById('get-button').addEventListener('click', () => {
	fetch('http://localhost:8080/api/users')
		.then((response) => { return response.json() })
		.then((userResponse) => {
			const userListBody = document.getElementById('user-list').querySelector('tbody');
			// 画面上の初期化
			userListBody.innerHTML = '';
			
			// userNames配列とuserIds配列を受け取る
			const userNames = userResponse.userNames;
			const userIds = userResponse.userIds;
			
			
			// userNames配列の長さを基準にループし、名前とIDを同時に処理する
			userNames.forEach((userName, index) => {
				const newRow = document.createElement('tr');
				
				// IDセルを作成
				const idCell = document.createElement('td');
				idCell.textContent = userIds[index];
				
				// ユーザ名セルを作成
				const nameCell = document.createElement('td');
				nameCell.textContent = userName;
				
				// 削除セルを作成
				const deleteCell = document.createElement('td');
				const button = document.createElement('button');
				button.textContent = '削除';
				// どの削除ボタンかを特定するために定義
				button.setAttribute('data-id', userIds[index]);
				button.classList.add('delete-user-btn');
				deleteCell.appendChild(button);
				
				newRow.appendChild(idCell);
				newRow.appendChild(nameCell);
				newRow.appendChild(deleteCell);
				
				userListBody.appendChild(newRow);
			});
		});
});

// Usersテーブルの部分一致検索処理
document.getElementById('get-filter-button').addEventListener('click', () => {
	fetch('http://localhost:8080/api/users/filter', {
		method: 'POST',
		headers: {
			'Content-Type': 'text/plain; charset=UTF-8' 
		},
		body: document.getElementById('filter-username').value
	})
	.then((response) => { return response.json() })
	.then((userResponse) => {
		const userListBody = document.getElementById('user-list').querySelector('tbody');
		// 画面上の初期化
		userListBody.innerHTML = '';
		
		// userNames配列とuserIds配列を受け取る
		const userNames = userResponse.userNames;
		const userIds = userResponse.userIds;
		
		// userNames配列の長さを基準にループし、名前とIDを同時に処理する
		userNames.forEach((userName, index) => {
			const newRow = document.createElement('tr');
			
			// IDセルを作成
			const idCell = document.createElement('td');
			idCell.textContent = userIds[index];
			
			// ユーザ名セルを作成
			const nameCell = document.createElement('td');
			nameCell.textContent = userName;
			
			// 削除セルを作成
			const deleteCell = document.createElement('td');
			const button = document.createElement('button');
			button.textContent = '削除';
			// どの削除ボタンかを特定するために定義
			button.setAttribute('data-id', userIds[index]);
			button.classList.add('delete-user-btn');
			deleteCell.appendChild(button);
			
			newRow.appendChild(idCell);
			newRow.appendChild(nameCell);
			newRow.appendChild(deleteCell);
			
			userListBody.appendChild(newRow);
		});
	});
});

// usersテーブルへデータ登録処理
document.getElementById('user-add-button').addEventListener('click', () => {
	fetch('http://localhost:8080/api/users/add', {
		method: 'POST',
		headers: {
			'Content-Type': 'text/plain; charset=UTF-8'
		},
		body: document.getElementById('username').value
	})
		.then(data => {
			alert('usersテーブルへデータ登録に成功しました。');
			console.log('usersテーブルへデータ登録に成功しました。', data);
		})
})

// Skillsテーブルの全件取得処理
document.getElementById('get-skills-button').addEventListener('click', () => {
	fetch('http://localhost:8080/api/skills')
		.then((response) => { return response.json() })
		.then((skillListDto) => {
			const skillListBody = document.getElementById('skill-list').querySelector('tbody');
			// 画面上の初期化
			skillListBody.innerHTML = '';
			
			// userNames配列とuserSkills配列を受け取る
			const userNames = skillListDto.userNames;
			const userSkills = skillListDto.userSkills;
			
			
			// userNames配列の長さを基準にループし、名前とスキルを同時に処理する
			userNames.forEach((userName, index) => {
				const newRow = document.createElement('tr');
				
				
				// ユーザ名セルを作成
				const nameCell = document.createElement('td');
				nameCell.textContent = userName;
	
				// スキルセルを作成
				const skillCell = document.createElement('td');
				skillCell.textContent = userSkills[index];
				
				// 削除セルを作成
				const deleteCell = document.createElement('td');
				const button = document.createElement('button');
				button.textContent = '削除';
				// どの削除ボタンかを特定するために定義
				button.classList.add('delete-skill-btn');
				deleteCell.appendChild(button);
				
				newRow.appendChild(nameCell);
				newRow.appendChild(skillCell);
				newRow.appendChild(deleteCell);
				
				skillListBody.appendChild(newRow);
			});
		});
});

// skillsテーブルへの部分一致検索
document.getElementById('get-skill-filter-button').addEventListener('click', () => {
	fetch('http://localhost:8080/api/skills/filter', {
		method: 'POST',
		headers: {
			'Content-Type': 'text/plain; charset=UTF-8' 
		},
		body: document.getElementById('filter-skill').value
	})
		.then((response) => { return response.json() })
		.then((skillListDto) => {
			const skillListBody = document.getElementById('skill-list').querySelector('tbody');
			// 画面上の初期化
			skillListBody.innerHTML = '';
			
			// userNames配列とuserSkills配列を受け取る
			const userNames = skillListDto.userNames;
			const userSkills = skillListDto.userSkills;
			
			// userNames配列の長さを基準にループし、名前とスキルを同時に処理する
			userNames.forEach((userName, index) => {
				const newRow = document.createElement('tr');
				
				
				// ユーザ名セルを作成
				const nameCell = document.createElement('td');
				nameCell.textContent = userName;
				
				// スキルセルを作成
				const skillCell = document.createElement('td');
				skillCell.textContent = userSkills[index];
				
				// 削除セルを作成
				const deleteCell = document.createElement('td');
				const button = document.createElement('button');
				button.textContent = '削除';
				// どの削除ボタンかを特定するために定義
				button.classList.add('delete-skill-btn');
				deleteCell.appendChild(button);
				
				newRow.appendChild(nameCell);
				newRow.appendChild(skillCell);
				newRow.appendChild(deleteCell);
				
				skillListBody.appendChild(newRow);
			});
		});
});
// skillsテーブルのユーザーカラムとスキルカラムのソート処理を定義
let sortDirection = 1; // 昇順が1降順が-1とする
const sortList = document.querySelectorAll('.sort-row');

sortList.forEach(header => {
	header.addEventListener('click', (e) => {
		const table = document.getElementById('skill-list');
		const tbody = table.querySelector('tbody');
		const rows = Array.from(tbody.querySelectorAll('tr'));
		const columnIndexString = e.currentTarget.getAttribute('data-index');
		const columnIndex = parseInt(columnIndexString, 10);
		
		// ソートアイコンを変える処理
		if (sortDirection === 1) {
			e.currentTarget.querySelector('i').outerHTML = '<i class="fa-solid fa-sort-up"></i>';
		} else if (sortDirection === -1) {
			e.currentTarget.querySelector('i').outerHTML = '<i class="fa-solid fa-sort-down"></i>';
		}
		
		// ソートするカラムの抽出
		const data = rows.map(row => {
			return {
				element: row, // ソート完了後にHTML上のテーブルを並び替える為に定義
				value: row.querySelectorAll('td')[columnIndex].textContent.trim()
			};
		});
		// 配列のソート処理
		data.sort((a, b) => {
			if (a.value < b.value) return -1 * sortDirection;
			if (a.value > b.value) return 1 * sortDirection;
			return 0;
		});
		
		tbody.append(...data.map(item => item.element));
		sortDirection *= -1;
		
	});
});


// skillsテーブルへデータ登録処理
document.getElementById('skill-add-button').addEventListener('click', () => {
	const skillData = {
		//JSONではスネークケースが多用される
		user_id: parseInt(document.getElementById('user-id').value),
		skill: document.getElementById('skill').value
	};
	
	fetch('http://localhost:8080/api/skills/add', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(skillData)
	})
	//HTTPレスポンスが返ってきた場合
	.then(async response => {
		if (!response.ok) {
			const errorData = await response.json();
			throw new Error(errorData.message);		
		} else {
			alert('登録に成功しました。');				
		}
	})
	.catch(error => {
		alert(error.message);
	});
});

document.addEventListener('DOMContentLoaded', () => {
	// 処理の対象となる要素の親要素を取得
	const table = document.getElementById('table-content');
	
	table.addEventListener('click', async (e) => {
		// クリックされた要素が目的の削除ボタンか確認
		const deleteButton = e.target;
		// ユーザーテーブルの削除ボタン押下時処理
		if (deleteButton.classList.contains('delete-user-btn')) {
			const userId = deleteButton.getAttribute('data-id');
			const response = await fetch(`/api/users/delete/${userId}`, {
				method: 'POST',
				headers: {
					'Content-type': 'application/json'
				}
			});
			if (response.ok) {
				alert(`ユーザーID:${userId}のレコードを削除しました。`);	
				
				// 画面上のレコードを削除
				const recordToDelete = deleteButton.closest('tr');
				recordToDelete.remove();
			}
		}
		// スキルテーブルの削除ボタン押下時処理
		if (deleteButton.classList.contains('delete-skill-btn')) {
			const userName = deleteButton.closest('tr').cells[0].textContent.trim();
			const userSkill = deleteButton.closest('tr').cells[1].textContent.trim();
			const response = await fetch(`/api/skills/delete/${userName}/${userSkill}`, {
				method: 'POST',
				headers: {
					'Content-type': 'application/json'
				}
			});
			if (response.ok) {
				alert(`ユーザー（${userName}）のスキル（${userSkill}）を削除しました。`);
				const recordToDelete = deleteButton.closest('tr');
				recordToDelete.remove();
			}
		}
	})
})