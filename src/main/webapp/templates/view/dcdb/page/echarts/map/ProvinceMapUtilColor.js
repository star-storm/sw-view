define(['jquery', 'echarts'], function($, echarts) {
    var province = {
        /** 
           idName   div id名
            data    数据 
            clickCallBack  点击事件
            MapparamDefaultTrue  手动设置那个面块 选中
            sixareaContentsFlag  城六区 面板文本是否显示，保留城区名字 true时生效
            sixareaJSONFlag  城六区小区JSON true时生效
            activeName 高亮name
         */
        showProvince: function(obj) {
            //idName, data, clickCallBack, MapparamDefaultTrue, sixareaContentsFlag, sixareaJSONFlag, activeName, IdNames, optionData
            var idName = obj.idName || '',
                data = obj.data || [],
                clickCallBack = obj.clickCallBack || function() {},
                MapparamDefaultTrue = obj.MapparamDefaultTrue || false,
                sixareaContentsFlag = obj.sixareaContentsFlag || false,
                sixareaJSONFlag = obj.sixareaJSONFlag || false,
                IdNames = obj.IdNames || '',
                activeName = obj.activeName || false,
                isShowBorder = obj.activeName || false,
                optionData = obj.optionData || null,
                Company = obj.Company || null;
            var arr = [];
            // var colorBorder = isShowBorder ? ['rgba(124,243,250,1)'] : ['rgba(124,243,250,0)'];
            var colorBorder = isShowBorder ? ['rgba(155, 155, 152,1)'] : ['rgba(124,243,250,0)'];
            var colorTop = isShowBorder ? ['rgba(124,243,250,1)'] : ['rgba(124,243,250,0)'];
            var colorActive = ['rgba(124,243,250,0.5)'];
            var colorBottom = ['rgba(124,243,250,0)'];
            var getSixBorderWidth = function getSixBorderWidth(item) {
                var str = ''
                if (item.name == '东城区' || item.name == '西城区' || item.name == '丰台区' || item.name == '石景山区' || item.name == '海淀区' || item.name == '朝阳区') {
                    return 4
                } else {
                    return 1
                }
            }
            var getSixBorderBorder = function getSixBorderBorder(item) {
                var str = ''
                if (item.name == '东城区' || item.name == '西城区' || item.name == '丰台区' || item.name == '石景山区' || item.name == '海淀区' || item.name == '朝阳区') {
                    // if (item.iyem == '5' || item.iyem == '4') {
                    //     return 'rgba(0,0,0,1)'
                    //         // return '#82ff4e' //    82ff4e
                    // } else if (item.iyem == '1' || item.iyem == '2') {
                    //     return 'rgba(0,0,0,1)' //   82ff4e
                    // } else {
                    //     return 'rgba(0,0,0,1)'
                    // }
                    return 'rgba(98,69,172,1)'//六区边框颜色
                } else {
                    return 'rgba(140,140,140,1)'//其他区边框颜色
                }
            }
            var symbol = [];
            var convertData = function(data) {
                var res = [];
                $.ajax({
                    url: 'json/BJMapGeoJson.json',
                    type: "get",
                    success: function(BJMapGeoJson) {
                        for (var i = 0; i < data.length; i++) {
                            var geoCoord = BJMapGeoJson[data[i].name];
                            if (geoCoord) {
                                res.push({
                                    name: data[i].name,
                                    value: geoCoord.concat(data[i].value),
                                    symbol: 'image://data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABYAAAATCAIAAAAbG2rfAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyBpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYwIDYxLjEzNDc3NywgMjAxMC8wMi8xMi0xNzozMjowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNSBXaW5kb3dzIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOkQwRTQzMDc2Njc1MTExRTlCMUFFRjIzQTMwNDZGODNGIiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOkQwRTQzMDc3Njc1MTExRTlCMUFFRjIzQTMwNDZGODNGIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6Q0RBOTE4MzM2NzUxMTFFOUIxQUVGMjNBMzA0NkY4M0YiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6Q0RBOTE4MzQ2NzUxMTFFOUIxQUVGMjNBMzA0NkY4M0YiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz4l95tdAAADVUlEQVR42nRUTUwTQRTe2V3cUiyF5X/5FaHVggUxoiEFq0IBqYgXTQw3T548YPRioomJR2+oJ4LxxkE5VCWUHyEYbGP4kb8SKr+RAra7xdD0J3bH2Z22rCbMbnZm37z3vTdvvveAyXodEgAQaECCkGdpERuxFZBWIPaHdUUIRUIyBGTMilBagfh7KAMJY0LES0DKjiEk4SF83CuQ0dHmP8hQ2oWJddwBIGgAlM5jKlqNRld2sqSwMC87R5uqUR1jkDQUCXt5/qfH41pZca/+EEUUiQQKTFarIgVAX5xjqjNxXLHL7V7f3Nre2RYEPhz5Ew3zalVybn55AcdVV1YmqxjboH3e5RIjAoaQAsrNzrrZ2khR9Oj4yMLSPJGUFj8jjIaFxIEohkWS0uKSWzfap2acn+yfqCJdOdqoMRpvd3SMjtn7be9393YgShKtRvLjanVJUXG6NjWK4giHkISkVejL7/unZ2etzZZAIEAj/zXGM1cbGl6/6f3l9UE5iZSKtba0Wq406st12Lkoil+dXwaGRhzfnDjjgWCw/6PdYq6nM1i2ranpVW+vl/ehDSo5I1WjefLocaXBoLwQkiTrLtaj9+nzZ5NOh5x6uLqxll9whzTX1Y1PTnp9PnxliDIP73f9Z68cF87XYmahr1qdEgyGSINePz03h8LHNEhRq8/XnCOOHouLCyLmCAFRBtEFk8mMKhAIEjDGiVAo5PV5j7IfGh0eGLYTcsIy0zPNpvrPExNUc5tV8As+QcAciwa98wuz1dW1Go1GaezZ2e5++eJtXx9mQGYGe7ezc3hsbGnRCe51PWi6bO7u6UF3Fg0JOBiaTqo6a+Ly8th0lhd8K66ZpeXFWF6Z9BpjVXtzy4ehQcfXYQnPdK2t6fIlY0XFO9sH9/LUIYVULIzXBaYWRZGGU5XmBgsqsPc227bHE40IMQg0ndbrrJbmg4PA9znn5tYafwBDiEgQMgyTptVyOTknivL0J3W7Xv6Lw7GwvJyowahEcBlC4jIAZaUlBr2O47gslmWSjiF5OBL27//27O2tb26s/HD7/fvxAgUYBQVJy5FCXPru1VVUgnLRAXQKKIoJZQAU7QOARB+SEic1j3i/kLDwJD/IDEJlE1D2Dqwsqf8VYABEkYvkUl+iHgAAAABJRU5ErkJggg==',
                                    symbolSize: [22, 19],
                                    symbolRotate: 8,
                                    label: { show: false }
                                });
                            }
                        }
                        return res;
                    }
                })
            };
            var arr = [];
            // var colorBorder = [
            //     'rgba(255,77,67,1)', 'rgba(255,128,4,1)',
            //     'rgba(255,255,103,1)', 'rgba(1,176,31,1)', 'rgba(255,255,255,1)'
            // ];

            var colorBorder = [
                'rgba(155,155,152,1)', 'rgba(155,155,152,1)',
                'rgba(155,155,152,1)', 'rgba(155,155,152,1)', 'rgba(155,155,152,1)'
            ];

//            var colorActive = [
//                'red', 'red',
//                'red', 'red', 'red'
//            ];

            var colorTop = [
                'rgba(255,77,67,1)', 'rgba(255,128,4,1)',
                'rgba(255,255,103,1)', 'rgba(1,176,31,1)', 'rgba(255,255,255,1)', 'rgba(251,231,139,1)'
            ];
            var colorActive = [
                'rgba(255,77,67,1)', 'rgba(255,128,4,1)',
                'rgba(255,255,103,1)', 'rgba(1,176,31,1)', 'rgba(255,255,255,1)'
            ];
            var colorBottom = [
                'rgba(255,77,67,1)', 'rgba(255,128,4,1)',
                'rgba(255,255,103,1)', 'rgba(1,176,31,1)', 'rgba(255,255,255,1)', 'rgba(251,231,139,1)'
            ];

            data.map(function(item, index) {
                if (IdNames == 'overView1_meddle_cont_maps_bjMap') {
                    var colorTemp = [];//区域颜色（非高亮）
                    if (item.value == 0) {
                        colorTemp.push('rgba(255,123,37)', 'rgba(255,123,37)', 'rgba(255,123,37)', 'rgba(255,123,37)')
                    } else if (item.value >= 1 && item.value <= 20) {
                        colorTemp.push('rgba(251, 230, 139, 1)', 'rgba(251, 230, 139, 1)', 'rgba(251, 230, 139, 1)', 'rgba(251, 230, 139, 1)')
                    } else if (item.value > 20 && item.value <= 40) {
                    	colorTemp.push('rgba(251, 230, 139, 1)', 'rgba(251, 230, 139, 1)', 'rgba(251, 230, 139, 1)', 'rgba(251, 230, 139, 1)')
                    } else if (item.value > 40) {
                    	colorTemp.push('rgba(251, 230, 139, 1)', 'rgba(251, 230, 139, 1)', 'rgba(251, 230, 139, 1)', 'rgba(251, 230, 139, 1)')
                        }
                }
                arr.push(
                    Object.assign(item, {
                        label: {
                            show: true,
                            formatter: function(d) {

                                if (sixareaContentsFlag) {
                                    if (d.name == '东城区' || d.name == '西城区' || d.name == '丰台区' || d.name == '石景山区' || d.name == '海淀区' || d.name == '朝阳区') {
                                        if (d.name == '东城区' || d.name == '西城区') {
                                            var not = d.name.split("").join("\n")
                                            return `{ee|${not}}`
                                        } else {
                                            return `${d.name}`
                                        }
                                    } else {
                                        if (Company == null) {
                                            return `${d.name}`
                                        } else if (Company == '无') {
                                            return `${d.name}\n{bb|${d.data.colors}`
                                        } else {
                                            return `${d.name}\n${Company}`
                                        }

                                    }
                                } else {
                                    if (d.name == '东城区' || d.name == '西城区' || d.name == '丰台区' || d.name == '石景山区' || d.name == '海淀区' || d.name == '朝阳区') {
                                        if (d.name == '东城区' || d.name == '西城区') {
                                            var not = d.name.split("").join("\n")
//                                            return `${not}`
                                            if (d.name == '东城区')
                                            	return '{gg|'+`${not}`.replace("区","")+'}';
                                            else
                                            	return '{ff|'+`${not}`.replace("区","")+'}';
                                        } else {
                                            return `${d.name}`
                                        }
                                    } else {
                                        if (Company == null) {
                                            return `${d.name}}`
                                        } else if (Company == '无') {
                                            return `${d.name}\n（${d.value}）`
                                        } else {
                                            return `${d.name}`
                                        }
                                    }


                                }

                            },
                            rich: {
                                cc: {
                                    fontSize: 12,
                                    height: 45,
                                    lineHeight: 50,
                                    width: 80,
                                    align: 'center',
                                },
                                aa: {
                                    fontSize: 12,
                                    width: 80,
                                    align: 'center',
                                },
                                bb: {
                                    fontSize: 12,
                                    height: 40,
                                    lineHeight: 40,
                                    width: 80,
                                    align: 'center',
                                },
                                ee: {
                                    fontSize: 12,
                                    width: 80,
                                    align: 'center',
                                },
                                ff:{
                                	fontSize: 10,
                                    fontFamily: '微软雅黑',
                                    color: 'red'
                                },
                                gg:{
                                	padding: [-26, 0, 0, 0],
                                    lineHeight: 12,
                                	fontSize: 12,
                                    color: 'red'
                                }
                            },
                            //  position: ['0%', '0%'],
                            // distance: 0,
                            rotate: 0,
                            offset: [0, -10],
                            color: '#000',
                            fontSize: 14,
                            fontFamily: '微软雅黑',
//                            fontWeight: 'BOLD',
                            // textBorderColor: '#173383',
                            // textBorderWidth: 2,
                        },
                        emphasis: {
                            itemStyle: {
                                areaColor: 'rgba(0,141,245,1)'//'rgba(95, 209, 226,1)'  
                            },
                            label: {
                                color: '#fff',
                                fontSize: 14,
                                fontFamily: '微软雅黑',
                                fontWeight: '100'
                                // textBorderColor: '#173383',
                                // textBorderWidth: 2,
                            }
                        },
                        selected: (activeName == item.name) ? true : false,
                        itemStyle: {
                            // areaColor: 'rgba(45,255,255,0.05)',
                            areaColor: {
                                type: 'radial',
                                x: 0.5,
                                y: 0.5,
                                r: 1,
                                colorStops: [{
                                        offset: 0,
                                        color: colorTemp[3] // 0% 处的颜色
                                    },

                                    {
                                        offset: 1,
                                        color: colorTemp[1] // 100% 处的颜色
                                    }
                                ],
                                globalCoord: false // 缺省为 false
                            },
                            // borderColor: colorTemp[0],
                            borderColor: getSixBorderBorder(item),
                            // borderColor: "rgba(126,3,38,1)",
                            borderWidth: getSixBorderWidth(item),
                            borderType: 'solid',
                            // shadowBlur: 10,
                            // shadowColor: colorTemp[0],
                        },

                    })
                )

            });


            var myecharts = echarts.init(document.getElementById(idName));
            var options = {
                /*     tooltip: {
                         trigger: 'item'
                     },*/
//                 legend: {
//                         orient: 'vertical',
//                         left: 'left',
//                         data: ['shuliang']
//                     },
                // geo: {
                //     show: false,
                //     map: '110100.json',
                //     zoom: 1.2,
                //     roam: false,
                // },
                title: {
                	text: '单位：件',
                	x:'right',
                	padding: [6,12,0,0],
                    textStyle: {
                        color: 'white',
                        fontFamliy: 'Microsoft YaHei',
                        fontSize: 18,
                        fontWeight: 'normal'
                    },
                },
                series: [{
                        name: '数量',
                        type: 'map',
                        mapType: sixareaJSONFlag ? 'six.json' : '110100.json',
                        roam: false,
                        zoom: 1.2,
                        symbol: 'circle',
                        selectedMode: 'single', 
                        itemStyle: {
                            areaColor: {
                                type: 'radial',
                                x: 0.5,
                                y: 0.5,
                                r: 1,
                                colorStops: [{
                                        offset: 0,
                                        color: colorBottom[4] // 0% 处的颜色
                                    },

                                    {
                                        offset: 1,
                                        color: colorTop[4] // 100% 处的颜色
                                    }
                                ],
                                globalCoord: false // 缺省为 false
                            },
                            // borderColor: colorBorder[4],
                            borderColor: 'rgba(155, 155, 152,1)',
                            borderWidth: 1,
                        },

                        emphasis: {
                            itemStyle: {
                                areaColor: colorActive[4],
                            },
                            label: {
                                color: 'transparent',
                            }
                        },
                        data: arr

                    },
                    {
                        name: '点',
                        type: 'scatter',
                        coordinateSystem: 'geo',
                        symbol: 'pin', //气泡
                        symbolSize: 80,
                        label: {
                            normal: {
                                show: false,
                            }
                        },
                        zlevel: 6,
                        data: convertData(data),
                    },
                ]
            };
            if (optionData != null) {
                option = $.extend(true, options, optionData);
            }
            $.get(sixareaJSONFlag ? 'json/six.json' : 'json/110100.json', function(geoJson) {
                echarts.registerMap(sixareaJSONFlag ? 'six.json' : '110100.json', geoJson);
                myecharts.setOption(options);
            });
            myecharts.off('click');  
            myecharts.on('click', function(params) {
                //  点击事件  
                if (typeof clickCallBack === "function") {
                    clickCallBack(params, myecharts, options);
                }
            });
            if (MapparamDefaultTrue == true) {
                // var params = window.MapparamDefault;
                // myecharts.dispatchAction({
                //     type: 'downplay',
                //     seriesIndex: 0
                // });
                // myecharts.dispatchAction({
                //     type: 'highlight',
                //     seriesIndex: 0,
                //     dataIndex: params.dataIndex
                // });
                // myecharts.dispatchAction({
                //     type: 'showTip',
                //     seriesIndex: 0,
                //     dataIndex: params.dataIndex,
                // });
            }
        }

    };
    return province;
})