(function ($j) {
    $j.fn.windstagball = function (options) {
        var flag = 0;
        if (flag == 0) {
            if ($j(this).find('a').length > 0) {
                flag = 1;
            }
            var defaults = {
                    radius: 120,
                    size: 300,
                    speed: 10,
                    depth: 300,
                    howElliptical: 1,
                    fontsize: 12
                },
                param = $j.extend({}, defaults, options || {}),
                selector = $j(this).selector,
                warp = $j(this),
                items = $j(selector + ' a'),
                dtr = Math.PI / 180,
                itemPosList = [],
                active = true,
                lasta = 1,
                lastb = 1,
                distr = true,
                mouseX = 0,
                mouseY = 0,
                sb, sa, sc, ca, cb, cc, oItem, oEvent, si;
            items.each(function () {
                oItem = {};
                oItem.width = $j(this).width();
                oItem.height = $j(this).height();
                itemPosList.push(oItem);
            });
            init();
            warp.find('a').mouseover(function () {
                active = false;
            });

            warp.mouseout(function () {
                active = true;
            });

            warp.mousemove(function (ev) {
                oEvent = window.event || ev;
                mouseX = oEvent.clientX - (warp.offset().left + warp.width() / 2);
                mouseY = oEvent.clientY - (warp.offset().top + warp.height() / 2);
                mouseX /= 5;
                mouseY /= 5;
            });

            var interval = setInterval(setPosition, 100);

            //初始化位置
            function init() {
                var phi = 0,
                    theta = 0,
                    max = itemPosList.length;
                sineCosine(0, 0, 0);
                //初始随机排序
                items.sort(function () {
                    return Math.random() < 0.5 ? 1 : -1;
                });

                items.each(function (i) {
                    if (distr) {
                        phi = Math.acos(-1 + (2 * i) / max);
                        theta = Math.sqrt(max * Math.PI) * phi;
                    }
                    else {
                        phi = Math.random() * (Math.PI);
                        theta = Math.random() * (2 * Math.PI);
                    }
                    itemPosList[i].cx = param.radius * Math.cos(theta) * Math.sin(phi);
                    itemPosList[i].cy = param.radius * Math.sin(theta) * Math.sin(phi);
                    itemPosList[i].cz = param.radius * Math.cos(phi);
                    $j(this).css('left', itemPosList[i].cx + warp.width() / 2 - itemPosList[i].width / 2 + 'px');
                    $j(this).css('top', itemPosList[i].cy + warp.height() / 2 - itemPosList[i].height / 2 + 'px');
                });
            };

            function sineCosine(a, b, c) {
                sa = Math.sin(a * dtr);
                ca = Math.cos(a * dtr);
                sb = Math.sin(b * dtr);
                cb = Math.cos(b * dtr);
                sc = Math.sin(c * dtr);
                cc = Math.cos(c * dtr);
            };

            function setPosition() {
                var a, b, c = 0, j, rx1, ry1, rz1, rx2, ry2, rz2, rx3, ry3, rz3, l = warp.width() / 2,
                    t = warp.height() / 2;

                if (active) {
                    a = (-Math.min(Math.max(-mouseY, -param.size), param.size) / param.radius) * param.speed;
                    b = (Math.min(Math.max(-mouseX, -param.size), param.size) / param.radius) * param.speed;
                }
                else {
                    /*a = lasta * 0.98;
                     b = lastb * 0.98;*/
                    a = 0.01;
                    b = 0.01;
                }
                lasta = a;
                lastb = b;

                if (Math.abs(a) <= 0.01 && Math.abs(b) <= 0.01) {
                    return interval;
                }
                sineCosine(a, b, c);
                for (j = 0; j < itemPosList.length; j++) {
                    rx1 = itemPosList[j].cx;
                    ry1 = itemPosList[j].cy * ca + itemPosList[j].cz * (-sa);
                    rz1 = itemPosList[j].cy * sa + itemPosList[j].cz * ca;

                    rx2 = rx1 * cb + rz1 * sb;
                    ry2 = ry1;
                    rz2 = rx1 * (-sb) + rz1 * cb;

                    rx3 = rx2 * cc + ry2 * (-sc);
                    ry3 = rx2 * sc + ry2 * cc;
                    rz3 = rz2;

                    itemPosList[j].cx = rx3;
                    itemPosList[j].cy = ry3;
                    itemPosList[j].cz = rz3;

                    per = param.depth / (param.depth + rz3);

                    itemPosList[j].x = (param.howElliptical * rx3 * per) - (param.howElliptical * 2);
                    itemPosList[j].y = ry3 * per;
                    itemPosList[j].scale = per;
                    itemPosList[j].alpha = per;

                    itemPosList[j].alpha = (itemPosList[j].alpha - 0.6) * (10 / 6);
                }

                items.each(function (i) {
                    $j(this).css('left', itemPosList[i].cx + l - itemPosList[i].width / 2 + 'px');
                    $j(this).css('top', itemPosList[i].cy + t - itemPosList[i].height / 2 + 'px');

                    /* $j(this).css('font-size', Math.ceil(param.fontsize * itemPosList[i].scale / 2) + 8 + 'px');

                     $j(this).css('filter', "alpha(opacity=" + 100 * itemPosList[i].alpha + ")");
                     $j(this).css('opacity', itemPosList[i].alpha);
                     $j(this).css("z-index", i);*/
                });
            }

            return interval;
        }
    };
})(jQuery);

