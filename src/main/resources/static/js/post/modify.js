const Editor = toastui.Editor;

const editor = new Editor({
    el: document.querySelector('#editor'),
    height: '600px',
    initialEditType: 'markdown',
    previewStyle: 'vertical',
    initialValue: $('#originalContent').val()
});

function WriteForm__submit(form) {
    form.subject.value = form.subject.value.trim();

    if (form.subject.value.length == 0) {
        alert("제목을 입력해주세요.");
        form.subject.focus();
        return false;
    }

    var content = editor.getMarkdown();

    if (content < 1000) {
        alert("글은 1000자 이상이어야 발행이 가능합니다.");
        return false;
    }

    return true;
};


let index = {

    init: function () {
        $("#writeBtn").on("click", () => {
            if(WriteForm__submit(document.querySelector('#writeForm'))){
                this.modify();
            }
        });
    },

    modify: function () {

        let postId = $("#postId").val();

        var data = {
            subject: $("#subject").val(),
            content: editor.getMarkdown(),
            contentHtml: editor.getHTML(),
            keywords: $("#keywords").val()
        };

        console.log(data.keywords);

        $.ajax({
            type: "POST",
            url: "/post/" + postId + "/modify",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            success: function (result) {

                alert("글 수정이 완료되었습니다.");

                location.href = "/post/" + postId ;

            },
            error: function (error) {
                console.log(JSON.stringify(error));
            }
        });
    },

};

index.init();

