$(function () {
    //登陆
    $('#loginform').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                message: '用户名验证失败',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },

                }
            },
            password: {
                message: '密码验证失败',
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: '密码只能包含大写、小写、数字和下划线'
                    }
                }
            },
            validateCode: {
                validators: {
                    notEmpty: {
                        message: '验证码不能为空'
                    },
                    stringLength: {
                        min: 4,
                        max: 6,
                        message: '验证码有误'
                    },
                }
            }
        }
    })
        .on('success.form.bv', function (e) { //表单验证通过;表单元素最好放在 <div class="form-group">下
            // $('#success_message').slideDown({opacity: "show"}, "slow") // 验证通过,隐藏成功标志
            // $('#loginform').data('bootstrapValidator').resetForm();
            e.preventDefault();  // 阻止默认事件提交
            // 获得表单实例
            var $form = $(e.target);
            // 得到BootstrapValidator实例
            var bv = $form.data('bootstrapValidator');
            // 使用Ajax提交表单数据
            $.post($form.attr('action'), $form.serialize(), function (result) {
                if (result.status==1) {
                    var userId = result.userId;//将数据中用户信息的ID赋值给变量
                    sessionStorage.userId = userId; //将变量存储到本地sessionStorage中，并且value为userId
                    window.location.href = 'page/main.html';//正确登录后页面跳转至
                } else {
                    swal({
                        title: result.msg,
                        text: '2秒后将自动关闭窗口',
                        timer: 2000,
                        type:'error'
                    });
                }}, 'json')

        });

    //注册
    $('#registerform').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                message: '用户名验证失败',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 5,
                        max: 10,
                        message: '用户名长度必须在5到10位之间'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: '用户名只能包含大写、小写、数字和下划线'
                    },
                    callback: {
                        message: "用户名已被占用",
                        callback: function (value, validator) {
                            if (value.match(/^[a-zA-Z0-9_]+$/)) {
                                $.ajax({
                                    url: 'user/checkname',
                                    type: 'post',
                                    dataType: 'json',
                                    async: false,
                                    data: {username: value},
                                    success: function (data) {
                                        if (data.status != 'success') {
                                            res = false;
                                        }
                                    }
                                });
                            }
                            return res;
                        }
                    }
                }
            },
            password: {
                message: '密码验证失败',
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 18,
                        message: '密码长度必须在6到18位之间'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: '密码只能包含大写、小写、数字和下划线'
                    }
                }
            },
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    identical: {
                        field: 'password',
                        message: '两次密码输入不一致'
                    },
                    different: {
                        field: 'username',
                        message: '密码不能与用户名一致'
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
                    emailAddress: {
                        message: '邮箱地址格式有误'
                    }
                }
            }
        },
        validateCode: {
            validators: {
                notEmpty: {
                    message: '验证码不能为空'
                },
                stringLength: {
                    min: 4,
                    max: 6,
                    message: '验证码有误'
                },
            }
        }
    })
        .on('success.form.bv', function (e) { //表单验证通过;表单元素最好放在 <div class="form-group">下
            $('#success_message').slideDown({opacity: "show"}, "slow") // Do something ...
            // $('#contact_form').data('bootstrapValidator').resetForm();

            // 阻止表单提交
            e.preventDefault();

            // 获得表单实例
            var $form = $(e.target);

            // 得到BootstrapValidator实例
            var bv = $form.data('bootstrapValidator');
            // 使用Ajax提交表单数据
            $.post($form.attr('action'), $form.serialize(), function (result) {
                if (result.status==1) {
                    var userId = result.userId;//将数据中用户信息的ID赋值给变量
                    sessionStorage.userId = userId; //将变量存储到本地sessionStorage中，并且value为userId
                    swal({
                        title:'注册成功!',
                        text:'2秒后自动跳转到登陆页面!',
                        type:'success',
                        timer: 2000,
                        showConfirmButton:false
                    });
                    setTimeout(function () {
                        window.location.href = 'index.html#tologin';//正确登录后页面跳转至
                    },2000)
                } else {
                    swal({
                        title: result.msg,
                        text: '2秒后将自动关闭窗口',
                        type:'error',
                        timer: 2000,
                        type:'warning'
                    });
                }
            }, 'json')

        });


})
