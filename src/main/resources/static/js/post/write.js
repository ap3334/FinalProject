const Editor = toastui.Editor;

const editor = new Editor({
    el: document.querySelector('#editor'),
    height: '600px',
    initialEditType: 'markdown',
    previewStyle: 'vertical'
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
                this.write();
            }
        });
    },

    write: function () {

        var data = {
            subject: $("#subject").val(),
            content: editor.getMarkdown(),
            contentHtml: editor.getHTML(),
            keywords: $("#keywords").val()
        };

        console.log(data);


        $.ajax({
            type: "POST",
            url: "/post/write",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            success: function (result) {

                alert("글쓰기가 완료되었습니다.");

                location.href = "/";

            },
            error: function (error) {
                console.log(JSON.stringify(error));
            }
        });
    },

};

index.init();

