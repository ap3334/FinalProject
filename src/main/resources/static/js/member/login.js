function LoginForm__submit(form) {
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

    form.submit();
};

