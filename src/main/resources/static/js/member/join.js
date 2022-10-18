function JoinForm__submit(form) {
    form.username.value = form.username.value.trim();

    if (form.username.value.length == 0) {
        alert("아이디를 입력해주세요.");
        form.username.focus();
        return;
    }

    form.password.value = form.password.value.trim();

    if (form.password.value.length == 0) {
        alert("비밀번호를 입력해주세요.");
        form.password.focus();
        return;
    }

    form.passwordConfirm.value = form.passwordConfirm.value.trim();

    if (form.passwordConfirm.value.length == 0) {
        alert("비밀번호확인을 입력해주세요.");
        form.passwordConfirm.focus();
        return;
    }

    form.email.value = form.email.value.trim();

    if (form.email.value.length == 0) {
        alert("이메일을 입력해주세요.");
        form.email.focus();
        return;
    }

    if (form.isOverlap.value == "true") {
        alert("아이디 중복 확인이 필요합니다.");
        form.username.focus();
        return;
    }

    if (form.password.value != form.passwordConfirm.value) {
        alert("비밀번호가 일치하지 않습니다.");
        form.passwordConfirm.focus();
        return;
    }

    form.submit();
};

let index = {
    init: function () {
        $("#idCheckBtn").on("click", () => {

            var data = {
                username: $("#username").val()
            };

            $.ajax({
                url: "/member/checkId",
                type: "POST",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                success: function (result) {

                    alert("해당 아이디 사용이 가능합니다.");

                    $("input[name=isOverlap]").attr("value", "false");
                },
                error : function (error) {

                    if(error.status == 409) {
                        alert("동일한 아이디가 존재합니다.");
                    } else {
                        alert("아이디 중복 확인이 정상적으로 이루어지지 않았습니다. 다시 시도해주세요.");
                    }

                    $("input[name=isOverlap]").attr("value", "true");

                }
            })

        })},

};

index.init();