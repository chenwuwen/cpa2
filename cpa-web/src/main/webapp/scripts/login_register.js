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
                    localStorage.setItem("userName",result.data.userName); //将变量存储到本地sessionStorage中，并且value为userName
                    localStorage.setItem("userId",result.data.id); //将变量存储到本地sessionStorage中，并且value为userId
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
                    threshold :  5 , //有6字符以上才发送ajax请求，（input中输入一个字符，插件会向服务器发送一次，设置限制，6字符以上才开始）
                    remote: { //ajax验证,向服务发送当前input name值，获得一个json数据。这里需要说明的是bootstrap的remote验证器需要的返回结果一定是json格式的数据 :{"valid":false} //表示不合法，验证不通过,{"valid":true} //表示合法，验证通过
                        message: "用户名已被占用",
                                    url: 'user/checkname',
                                    type: 'post',
                                    delay :  2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                                    dataType: 'json',
                        /**自定义提交数据，默认值提交当前input value
                         *  data: function(validator) {
                               return {
                                   password: $('[name="passwordNameAttributeInYourForm"]').val(),
                                   whatever: $('[name="whateverNameAttributeInYourForm"]').val()
                               };
                            }
                         */



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
