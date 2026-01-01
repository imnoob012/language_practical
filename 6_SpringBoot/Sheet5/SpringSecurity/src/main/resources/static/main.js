// Usersテーブルのデータ全件取得処理
document.getElementById('get-button').addEventListener('click', () => {
	fetch('http://localhost:8080/api/users')
		.then(async (response) => {
			if (!response.ok) {
				const errorData = await response.json();
				throw new Error(errorData.message);	
			}
			return response.json();
			
		}).then((userResponse) => {
			const userListBody = document.getElementById('user-list').querySelector('tbody');
			// 画面上の初期化
			userListBody.innerHTML = '';
			
			// userNames配列とuserIds配列を受け取る
			const userNames = userResponse.userNames;
			const userIds = userResponse.userIds;
			const roles = userResponse.roles;
			
			const loginUserName = document.getElementById('login-user-name').value;
			
			// userNames配列の長さを基準にループし、名前とIDを同時に処理する
			userNames.forEach((userName, index) => {
				const newRow = document.createElement('tr');
				if (isAdmin) {
					newRow.innerHTML = `
						<td>${userIds[index]}</td>
			            <td><input type="text" value="${userName}"></td>
			            <td>
			                <select>
			                    <option value="ROLE_USER" ${roles[index] === 'ROLE_USER' ? 'selected' : ''}>一般</option>
			                    <option value="ROLE_ADMIN" ${roles[index] === 'ROLE_ADMIN' ? 'selected' : ''}>管理者</option>
			                </select>
			            </td>
			            <td>
			                <button class="update-user-btn"
									data-id="${userIds[index]}"
									${ userName == loginUserName ? 'disabled' : ''}>更新</button>
			                <button class="delete-user-btn" 
									data-id="${userIds[index]}"
									${ userName == loginUserName ? 'disabled' : ''}>削除</button>
			            </td>
					`;
				} else {
					newRow.innerHTML = `
						<td>${userIds[index]}</td>
			            <td>${userName}</td>
			            <td>${roles[index] == 'ROLE_ADMIN' ? '管理者' : '一般'}</td>
			            <td>
			                <button class="update-user-btn" data-id="${userIds[index]}" disabled>更新</button>
			                <button class="delete-user-btn" data-id="${userIds[index]}" disabled>削除</button>
			            </td>
					`;
				}
				userListBody.appendChild(newRow);
			});
		}).catch(error => alert(error.message))
});

// Usersテーブルの部分一致検索処理
document.getElementById('get-filter-button').addEventListener('click', () => {
	const keyword = document.getElementById('filter-username').value;
	
	fetch(`http://localhost:8080/api/users?input=${encodeURIComponent(keyword)}`, {
		method: 'GET',
	})
	.then(async (response) => {
		if (!response.ok) {
			const errorData = await response.json();
			throw new Error(errorData.message);
		}
		return response.json(); 
	
	}).then((userResponse) => {
		const userListBody = document.getElementById('user-list').querySelector('tbody');
		// 画面上の初期化
		userListBody.innerHTML = '';
		
		// userNames配列とuserIds配列を受け取る
		const userNames = userResponse.userNames;
		const userIds = userResponse.userIds;
		const roles = userResponse.roles;
		
		const loginUserName = document.getElementById('login-user-name').value;
		
		// userNames配列の長さを基準にループし、名前とIDを同時に処理する
		userNames.forEach((userName, index) => {
			const newRow = document.createElement('tr');
			if (isAdmin) {
				newRow.innerHTML = `
					<td>${userIds[index]}</td>
		            <td><input type="text" value="${userName}"></td>
		            <td>
		                <select>
		                    <option value="ROLE_USER" ${roles[index] === 'ROLE_USER' ? 'selected' : ''}>一般</option>
		                    <option value="ROLE_ADMIN" ${roles[index] === 'ROLE_ADMIN' ? 'selected' : ''}>管理者</option>
		                </select>
		            </td>
		            <td>
		                <button class="update-user-btn" 
								data-id="${userIds[index]}"
								${ userName == loginUserName ? 'disabled' : ''}>更新</button>
		                <button class="delete-user-btn"
								data-id="${userIds[index]}"
								${ userName == loginUserName ? 'disabled' : ''}>削除</button>
		            </td>
				`;
			} else {
				newRow.innerHTML = `
					<td>${userIds[index]}</td>
		            <td>${userName}</td>
		            <td>${roles[index] == 'ROLE_ADMIN' ? '管理者' : '一般'}</td>
		            <td>
		                <button class="update-user-btn" data-id="${userIds[index]}" disabled>更新</button>
		                <button class="delete-user-btn" data-id="${userIds[index]}" disabled>削除</button>
		            </td>
				`;
			}
			
			userListBody.appendChild(newRow);
		});
	}).catch(error => alert(error.message));
});

// usersテーブルへデータ登録処理
document.getElementById('user-add-button').addEventListener('click', () => {
	if (isAdmin != true) {
		alert('権限がありません。管理者のみ実行可能です。');
		return;
	}
	const token = document.querySelector('meta[name="_csrf"]').content;
	const header = document.querySelector('meta[name="_csrf_header"]').content;
	const roleElement = document.querySelector('input[name="role"]:checked');
	const userName = document.getElementById('username').value;
	const password = document.getElementById('password').value;
	
	const userData = {
		name: userName,
		password: password,
		role: roleElement.value
	};
	
	fetch('http://localhost:8080/api/users/add', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json; charset=UTF-8',
			[header]: token
		},
		body: JSON.stringify(userData)
	})
		.then(async response => {
			if (!response.ok) {
				const errorData = await response.json();
				throw new Error(errorData.message);
			} else {
				alert('データの登録に成功しました');
				location.reload();
			}
		}).catch(error => {
			alert(error.message);
		});
})
// Skillsテーブルの全件取得処理
document.getElementById('get-skills-button').addEventListener('click', () => {
	fetch('http://localhost:8080/api/skills')
		.then(async (response) => {
			if (!response.ok) {
				const errorData = await response.json();
				throw new Error(errorData.message);
			} 
			return response.json();
			
		 }).then((skillListDto) => {
			const skillListBody = document.getElementById('skill-list').querySelector('tbody');
			// 画面上の初期化
			skillListBody.innerHTML = '';
			
			// userNames配列とuserSkills配列とskillIds配列を受け取る
			const userNames = skillListDto.userNames;
			const userSkills = skillListDto.userSkills;
			const skillIds = skillListDto.skillIds;
			
			
			// userNames配列の長さを基準にループし、名前とスキルを同時に処理する
			userNames.forEach((userName, index) => {
				const newRow = document.createElement('tr');
				if (isAdmin) {
					newRow.innerHTML = `
						<td>${userName}</td>
						<td><input type="text" value="${userSkills[index]}"></td>
						<td>
							<button class="update-skill-btn" data-skill-id="${skillIds[index]}">更新</button>
							<button class="delete-skill-btn" data-skill-id="${skillIds[index]}">削除</button>
						</td>
					`;
				} else {
					newRow.innerHTML = `
						<td>${userName}</td>
						<td>${userSkills[index]}</td>
						<td>
							<button class="update-skill-btn" data-skill-id="${skillIds[index]}" ${isAdmin ? '': 'disabled'}>更新</button>
							<button class="delete-skill-btn" data-skill-id="${skillIds[index]}" ${isAdmin ? '': 'disabled'}>削除</button>
						</td>
					`;
					
				}
				
				skillListBody.appendChild(newRow);
			})	
		}).catch(error => alert(error.message));
});

// skillsテーブルへの部分一致検索
document.getElementById('get-skill-filter-button').addEventListener('click', () => {
	const keyword = document.getElementById('filter-skill').value;
	fetch(`http://localhost:8080/api/skills?input=${encodeURIComponent(keyword)}`, {
		method: 'GET'
	})
		.then(async (response) => {
			if (!response.ok) {
				const errorData = await response.json();
				throw new Error(errorData.message);
			} 
			return response.json();
			
		}).then((skillListDto) => {
			const skillListBody = document.getElementById('skill-list').querySelector('tbody');
			// 画面上の初期化
			skillListBody.innerHTML = '';
			
			// userNames配列とuserSkills配列とskillIds配列を受け取る
			const userNames = skillListDto.userNames;
			const userSkills = skillListDto.userSkills;
			const skillIds = skillListDto.skillIds;
			
			// userNames配列の長さを基準にループし、名前とスキルを同時に処理する
			userNames.forEach((userName, index) => {
				const newRow = document.createElement('tr');
				if (isAdmin) {
					newRow.innerHTML = `
						<td>${userName}</td>
						<td><input type="text" value="${userSkills[index]}"></td>
						<td>
							<button class="update-skill-btn" data-skill-id="${skillIds[index]}">更新</button>
							<button class="delete-skill-btn" data-skill-id="${skillIds[index]}">削除</button>
						</td>
					`;
				} else {
					newRow.innerHTML = `
						<td>${userName}</td>
						<td>${userSkills[index]}</td>
						<td>
							<button class="update-skill-btn" data-skill-id="${skillIds[index]}" ${isAdmin ? '': 'disabled'}>更新</button>
							<button class="delete-skill-btn" data-skill-id="${skillIds[index]}" ${isAdmin ? '': 'disabled'}>削除</button>
						</td>
					`;
					
				}
				skillListBody.appendChild(newRow);
			});
		}).catch(error => alert(error.message));
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
			const cell = row.querySelectorAll('td')[columnIndex]; 
			return {
				element: row, // ソート完了後にHTML上のテーブルを並び替える為に定義
				value: cell.querySelector('input') ? cell.querySelector('input').value.trim() : cell.textContent.trim()
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
	const token = document.querySelector('meta[name="_csrf"]').content;
	const header = document.querySelector('meta[name="_csrf_header"]').content;
	if (isAdmin != true) {
		alert('権限がありません。管理者のみ実行可能です。');
		return;
	}
	const skillData = {
		//JSONではスネークケースが多用される
		user_id: parseInt(document.getElementById('user-id').value),
		skill: document.getElementById('skill').value
	};
	
	fetch('http://localhost:8080/api/skills/add', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
			[header]: token
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
			location.reload();			
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
		const token = document.querySelector('meta[name="_csrf"]').content;
		const header = document.querySelector('meta[name="_csrf_header"]').content;
		
		//Usersテーブルの単一レコード更新処理
		if (e.target.classList.contains('update-user-btn')) {
			const updatedData = {
				id: e.target.getAttribute('data-id'),
				name: e.target.closest('tr').querySelector('input[type="text"]').value.trim(),
				role: e.target.closest('tr').querySelector('select').value
			};
			if (!updatedData.name) {
		        alert("ユーザー名は入力してください");
		        e.target.closest('tr').querySelector('input[type="text"]').focus();
		        return;
		    }
			try {	
				const response = await fetch(`http://localhost:8080/api/users/update`, {
					method: 'POST',
		            headers: {
		                'Content-Type': 'application/json',
		                [header]: token
		            },
		            body: JSON.stringify(updatedData)
				});
				if (response.ok) {
					alert('データの更新に成功しました');
					location.reload();
				} else {
					const errorData = await response.json();
					throw new Error(errorData.message);
				}
			} catch (error) {
				alert(error.message);
			}
	    }
		
		//Skillテーブルの単一レコード更新処理
		if (e.target.classList.contains('update-skill-btn')) {
			const updatedData = {
				skillId: e.target.getAttribute('data-skill-id'),
				skill: e.target.closest('tr').querySelector('input[type="text"]').value.trim()
			};
			if (!updatedData.skill) {
		        alert("スキル名は入力してください");
		        e.target.closest('tr').querySelector('input[type="text"]').focus();
		        return;
		    }
			try {
				const response = await fetch(`http://localhost:8080/api/skills/update`, {
					method: 'POST',
		            headers: {
		                'Content-Type': 'application/json',
		                [header]: token
		            },
		            body: JSON.stringify(updatedData)
				});
				if (response.ok) {
					alert('データの更新に成功しました');
					location.reload();
				} else {
					const errorData = await response.json();
					throw new Error(errorData.message);
				}
			} catch (error) {
				alert(error.message);
			}
	    }
		
		// ユーザーテーブルの削除ボタン押下時処理
		if (e.target.classList.contains('delete-user-btn')) {
			if (isAdmin != true) {
				alert('権限がありません。管理者のみ実行可能です。');
				return;
			}
			const userId = e.target.getAttribute('data-id');
			try {
				const response = await fetch(`/api/users/delete/${userId}`, {
					method: 'POST',
					headers: {
						'Content-type': 'application/json',
						[header]: token
					}
				})
				if (response.ok) {
					// 画面上のレコードを削除
					const recordToDelete = e.target.closest('tr');
					recordToDelete.remove();
					alert('削除に成功しました');
				} else {
					const errorData = await response.json();
					throw new Error(errorData.message);
				}
			} catch (error) {
				alert(error.message);
			}
		}
		// スキルテーブルの削除ボタン押下時処理
		if (e.target.classList.contains('delete-skill-btn')) {
			if (isAdmin != true) {
				alert('権限がありません。管理者のみ実行可能です。');
				return;
			}
			const skillId = e.target.getAttribute('data-skill-id');
			try {
				const response = await fetch(`/api/skills/delete/${skillId}`, {
					method: 'POST',
					headers: {
						'Content-type': 'application/json',
						[header]: token
					}
				});
				if (response.ok) {
					const recordToDelete = e.target.closest('tr');
					recordToDelete.remove();
					alert('削除に成功しました');
				} else {
					const errorData = await response.json();
					throw new Error(errorData.message);
				}
			} catch (error) {
				alert(error.message);
			}
		}
	});
})