"use strict";
app.factory("weatherService", ["$http", function (t) {
    return {
        getWeather: function (e) {
            return t({
                method: "JSONP",
                url: "http://api.map.baidu.com/telematics/v3/weather?location=" + e + "&output=json&ak=hBDoMmfaQvkxwifiKdsQij6s"
            }).then(function (t) {
                return t.data
            }, function (t) {
                return t.data
            })
        }
    }
}]);