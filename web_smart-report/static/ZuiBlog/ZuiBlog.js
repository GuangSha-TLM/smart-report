//è®¾ç½®cookie
export function setCookie(name, value) {
  if (value) {
    var Days = 365;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
    document.cookie =
      name +
      "=" +
      encodeURIComponent(value) +
      ";expires=" +
      exp.toGMTString() +
      "; path=/";
  }
}
//èŽ·å–cookie
export function getCookie(NameOfCookie) {
  if (document.cookie.length > 0) {
    // 查找指定的 Cookie 名称加上等号
    let begin = document.cookie.indexOf(NameOfCookie);
    if (begin !== -1) {
      begin += NameOfCookie.length + 1;
      let end = document.cookie.indexOf(";", begin);
      if (end === -1) end = document.cookie.length;
      return unescape(document.cookie.substring(begin, end));
    }
  }
  return null;
}

//获取 URL 中查询字符串部分（以 ? 开头的部分）
function getQueryVariable(variable) {
  var query = window.location.search.substring(1);
  var vars = query.split("&");
  for (var i = 0; i < vars.length; i++) {
    var pair = vars[i].split("=");
    if (pair[0] == variable) {
      return pair[1];
    }
  }
  return false;
}

/**
 * 错误代码检测
 * @param {*} obj
 */
function check(obj) {
  if (obj == "") {
    return false;
  } else if (obj.code == "0x444") {
    alert(obj.message);
    //跳转登入页面
    window.location.href = "/user/login";
    return false;
  } else if (obj.code == "0x445") {
    alert(obj.message);
    //跳转首页
    window.location.href = "/h";
    return false;
  } else if (obj.code == "0x500") {
    alert("操作失败");
    return false;
  } else if (obj.code == "0x501") {
    alert("服务器接受数据异常");
    return false;
  } else if (obj.code == "0x502") {
    alert(obj.message);
    return false;
  } else if (obj.code == "0x501") {
    alert(obj.message);
    return false;
  } else if (obj.code == "0x504") {
    alert(obj.message);
    window.location.href = "/h";
    return false;
  } else if (obj.code == "0x505") {
    alert(obj.message);
    return false;
  } else if (obj.code == "0x506") {
    alert(obj.message);
    return false;
  } else if (obj.code == "0x507") {
    alert(obj.message);
    return false;
  } else if (obj.code == "0x508") {
    alert(obj.message);
    //跳转首页
    window.location.href = "/h";
    return false;
  }

  return true;
}
