$(document).ready(function () {
    $("#cards-box").html("");
    showArticles();
});

function openClose() {
    // id 값 post-box의 display 값이 block 이면(= 눈에 보이면)
    if ($("#post-box").css("display") == "block") {
        // post-box를 가리고
        $("#post-box").hide();
        // 다시 버튼을 클릭하면, 박스 열기를 할 수 있게 텍스트 바꿔두기
        $("#btn-post-box").text("포스팅 박스 열기");
    } else {
        // 아니면(눈에 보이지 않으면) post-box를 펴라
        $("#post-box").show();
        // 다시 버튼을 클릭하면, 박스 닫기를 할 수 있게 텍스트 바꿔두기
        $("#btn-post-box").text("포스팅 박스 닫기");
    }
}

function postingArticle(){
    let title = $("#title").val();
    let content = $("#content").val();
    let data = {'content': content,'title':title};

    $.ajax({
        type: "POST",
        url: "/articles",
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data),
        success: function (response) {
            alert("저장되었습니다!")
            window.location.reload();

        }
    })
}

function showArticles() {

    $.ajax({
        type: "GET",
        url: "/articles",
        success: function (response) {

            for (let i = 0; i < response.length; i++) {
                let article = response[i];
                makeListPost(article);
            }
        }
    })

}

function makeListPost(article) {
    let tempHtml = ` <tr>
                      <th scope="row">${article['id']}</th>
                      <td><a href="/articles/read?id=${article['id']}">${article['title']}</a></td>
                      <td>0</td>
                      <td>${article['createdAt']}</button></td>
                      </tr>
                    `;
    $("#list-post").append(tempHtml);

}