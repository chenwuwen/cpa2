/**
 * Created by KANYUN on 2017/4/25.
 */
/*
 * angular directive ng-icheck
 *
 * @description icheck is a plugin of jquery for beautifying checkbox &amp; radio, now I complied it with angular directive
 * @require jquery, icheck
 * @example &lt;input type="radio" ng-model="paomian" value="kangshifu" ng-icheck&gt;
 *          &lt;input type="checkbox" class="icheckbox" name="mantou" ng-model="mantou" ng-icheck checked&gt;
 */
var iCheckDirectives = angular.module('iCheckDirectives', []);
iCheckDirectives.directive('ngIcheck', function($compile) {
    return {
        restrict : 'AE',
        require : '?ngModel',
        link : function($scope, $element, $attrs, $ngModel) {
            if (!$ngModel) {
                return;
            }
            //using iCheck
            $($element).iCheck({
                labelHover : false,
                cursor : true,
                checkboxClass : 'icheckbox_square-blue',
                radioClass : 'iradio_square-blue',
                increaseArea : '20%'
            }).on('ifClicked', function(event) {
                if ($attrs.type == "checkbox") {
                    //checkbox, $ViewValue = true/false/undefined
                    $scope.$apply(function() {
                        $ngModel.$setViewValue(!($ngModel.$modelValue == undefined ? false : $ngModel.$modelValue));
                    });
                } else {
                    // radio, $ViewValue = $attrs.value
                    $scope.$apply(function() {
                        $ngModel.$setViewValue($attrs.value);
                    });
                }
            });
        },
    };
});

