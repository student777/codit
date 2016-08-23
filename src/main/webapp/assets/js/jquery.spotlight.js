(function ($) {
    $.fn.spotLightOff = function () {
        $("#spotLightCustom").remove();
    }
    $.fn.spotLightOn = function (options) {
        // This is the easiest way to have default options.
        var settings = $.extend({
            // These are the defaults.
            color: "#ffffff",
            backgroundColor: "#000000",
            opacity: "0.4"
        }, options);
        var zIndex = 99999;
        var position = this.get(0).getBoundingClientRect();
        spotLight = $("#spotLightCustom");
        if (spotLight.length == 0) {
            spotLight = $("<div id='spotLightCustom' style='top:0;left:0;border: solid black;display:none;background-color:"
                + settings.color + ";border-color:" + settings.backgroundColor
                + "; position:absolute;border-right-width: 10000px;border-bottom-width: 10000px;' ></div>");
            spotLight.appendTo(document.body);
        }
        spotLight.css("z-index", zIndex);
        spotLight.width(1);
        spotLight.height(1);
        spotLight.css('opacity', settings.opacity);
        // 시점 초기화 안시켜줌
        // spotLight.css("border-top-width", 0);
        // spotLight.css("border-left-width", 0);
        spotLight.show();
        spotLight.animate({
            height: this.height(),
            width: this.width(),
            "border-left-width": position.x ? position.x : position.left,
            "border-top-width": position.y ? position.y : position.top
        }, 1000, function () {
            //리스펙안대혁
            setTimeout( function() {
                alert(options.msg);
                help();
            }, 1);
        });
        return this;
    }
})(jQuery);