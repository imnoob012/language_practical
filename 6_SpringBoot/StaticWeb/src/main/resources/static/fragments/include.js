
function includeHTML(url, targetSelector) {
	//挿入先の要素を取得
	const targetElement = document.getElementById(targetSelector);
	
	if (!targetElement) {
		console.error('挿入先要素 ${targetSelector} が見つかりません。');
	}
	
	//fetchで挿入するHTMLファイルを読み込む
	fetch(url)
		.then(response => {
			if(!response.ok) {
				throw new Error(`http error status: ${response.status}`);
			}
			return response.text();
		})
		.then(htmlText => {
			targetElement.innerHTML = htmlText;
		})
		.catch(error => {
			console.error('HTMLの読み込みに失敗しました：', error);
			targetElement.innerHTML = '<strong>コンテンツの読み込みが失敗しました</strong>';
		});
}


//domcontentloadedイベント発火時に、includeHTML関数を実行
document.addEventListener('DOMContentLoaded', function() {
	includeHTML('fragments/header.html', 'page-header');
	includeHTML('fragments/footer.html', 'page-footer');
})












