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
                     stringLength: {
                         min: 3,
                         max: 12,
                         message: '用户名长度必须在3到12位之间'
                     },
                     regexp: {
                         regexp: /^[a-zA-Z0-9_]+$/,
                         message: '用户名只能包含大写、小写、数字和下划线'
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
             identifyingCode: {
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
         },
             submitHandler: function(validator, form, submitButton) {
                 // a)
                 // Use Ajax to submit form data
                 //$.post(form.attr('action'), form.serialize(), function(result) {
                 // ... process the result ...
                 //}, 'json');

                 //b)
                 // Do your task
                 // ...
                 // Submit the form
                 validator.defaultSubmit();
             }
         }
     );

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
                        min: 3,
                        max: 12,
                        message: '用户名长度必须在3到12位之间'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: '用户名只能包含大写、小写、数字和下划线'
                    },
                    callback:{
                        message:"用户名已被占用",
                    callback:function(value,validator){
                        if (value.match(/^[a-zA-Z0-9_]+$/)) {
                            $.ajax({
                                url: 'user/checkname.do',
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
        identifyingCode: {
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
    });



})
