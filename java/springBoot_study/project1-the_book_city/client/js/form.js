//1.声明变量
var emailObj;
var usernameObj;
var passwordObj;
var confirmObj;
var emailMsg;
var usernameMsg;
var passwordMsg;
var confirmMsg;
//页面加载之后，获取页面中的对象
window.onload = function() {
    emailObj = document.getElementById("email");
    usernameObj = document.getElementById("username");
    passwordObj = document.getElementById("password");
    confirmObj = document.getElementById("confirm");
    emailMsg = document.getElementById("emaileMsg");
    usernameMsg = document.getElementById("usernameMsg");
    confirmMsg = document.getElementById("confirmMsg");
};
//3.检验整个表单
function checkForm() {
    var bEmail = checkEmail();
    var bUsername = checkUsername();
    var bPassword = checkPassword();
    var bConfirm = checkCondfirm();
    return bUsername && bPassword && bConfirm && bEmail;
}
//4.验证邮箱
function checkEmail() {
    var regex =  /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
    var value = emailObj.value;
    var msg = "";
    if(!value) {
        msg = "邮箱必须填写";
    } else if (!regex.test(value)) {
        msg = "邮箱格式不合法";
    }
    emailMsg.innerHTML = msg;
    emailObj.parentNode.parentNode.style.color = msg == ""?"black":"red";
    return msg == "";
}
//5.验证用户名‘
function checkUsername() {
    var regex = /^[a-zA-Z0-9_-]\w{0,9}$/;   //字母数字下划线1-10位，不能是数字开头
    var value = usernameObj.value;          //获取usernameObj中的文本
    var msg = "";                           //最后的提示小事，默认为空
    //如果用户名是null或"",!value的值为false，如果不为空 !value值为true
    if(!value) {
        msg = "用户名必须填写";
    } else if (regex.test(value)) {
        msg = "用户名不合法";
    }
    usernameMsg.innerHTML = msg;
    usernameObj.parentNode.parentNode.style.color = msg == ""?"black":"red";
    return msg == "";                       //如果提示消息为空则代表没出错，返回true
}
//6.验证密码
function checkPassword() {
    var regex = /^.{6,16}$/;            //任意字符，6-16位
    var value = passwordObj.value;
    var msg = "";
    if(!value) {
        msg = "密码必须填写";
    } else if (!regex.test(value)) {
        msg = "密码不合法";
    }
    passwordMsg.parentNode.parentNode.style.color = msg == ""?"black":"red";
    return msg == "";
}
//7.验证确认密码
function checkCondfirm() {
    var passwordValue = passwordObj.value;
    var confirmValue = confirmObj.value;
    var msg = "";
    if(!confirmValue) {
        msg = "确认密码填写";
    } else if (passwordValue != confirmValue) {
        msg = "密码必须保持一致";
    confirmMsg.innerHTML = msg;
    confirmObj.parentNode.parentNode.style.color = msg == ""?"black":"red";
    return msg == "";
    }
}
