"use strict";
angular.module("baiduWeatherWidgetApp", []).config(["$sceDelegateProvider", function (e) {
    e.resourceUrlWhitelist(["self", "http://api.map.baidu.com/**"])
}]);