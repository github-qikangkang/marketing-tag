
$(function () {
    echarts_4();

    echarts_35();
    echarts_36();
    echarts_5();





    function echarts_5() {
        // 周下单量
        var myChart = echarts.init(document.getElementById('echart5'));

        option = {
            grid: {
                left: '0%',
                top:'10px',
                right: '0%',
                bottom: '4%',
                containLabel: true
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: { type: 'shadow'}
            },
            dataset: {
                source: [
                    ['时间', '当期', '上期', ],
                    ['近一天', 43.3, 85.8],
                    ['近二天', 83.1, 73.4],
                    ['近三天', 86.4, 65.2],
                    ['近四天', 86.4, 65.2],
                    ['近五天', 86.4, 65.2],
                    ['近六天', 72.4, 53.9],
                    ['近七天', 72.4, 53.9],
                ]
            },
            xAxis: {
                type: 'category',
                axisLine: {
                    show: true,
                    lineStyle: {
                        color: "rgba(255,255,255,.1)",
                        width: 1,
                        type: "solid"
                    },
                },

                axisTick: {
                    show: false,
                },
                axisLabel:  {
                    interval: 0,
                    // rotate:50,
                    show: true,
                    splitNumber: 15,
                    textStyle: {
                        color: "rgba(255,255,255,.6)",
                        fontSize: '12'
                    }
                },
            },
            yAxis: {
                axisLabel: {
                    //formatter: '{value} %'
                    show:true,
                    textStyle: {
                        color: "rgba(255,255,255,.6)",
                        fontSize: '12',
                    },
                },
                axisTick: {
                    show: false,
                },
                axisLine: {
                    show: true,
                    lineStyle: {
                        color: "rgba(255,255,255,.1	)",
                        width: 1,
                        type: "solid"
                    },
                },
                splitLine: {
                    lineStyle: {
                        color: "rgba(255,255,255,.1)",
                    }
                }
            },
            // Declare several bar series, each will be mapped
            // to a column of dataset.source by default.
            series: [
                {type: 'bar'},
                {type: 'bar'}
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        window.addEventListener("resize",function(){
            myChart.resize();
        });
    }

    function echarts_4() {
        // 漏斗图
        var myChart = echarts.init(document.getElementById('echart4'));

        option = {
            tooltip: {
                trigger: 'item',
                formatter: "{b} : {c}%",
                axisPointer: {
                    lineStyle: {
                        color: '#dddc6b'
                    }
                }
            },
            toolbox: {
                top:150,
                feature: {
                    dataView: {readOnly: false},
                    restore: {},
                    saveAsImage: {}
                }
            },
            legend: {
                top:'0%',
                data: ['展现', '点击', '加购', '下单', '复购', "储值"],
                textStyle: {
                    color: 'rgba(255,255,255,.5)',
                    fontSize:'12',
                }
            },
            grid: {
                left: '10',
                top: '30',
                right: '10',
                bottom: '10',
                containLabel: true
            },


            calculable: true,
            series: [
                {
                    name: '漏斗图',
                    type: 'funnel',
                    left: '15',
                    top: 40,
                    bottom: 0,
                    width: '90%',
                    min: 0,
                    max: 100,
                    minSize: '0%',
                    maxSize: '100%',
                    sort: 'descending',
                    gap: 2,
                    label: {
                        show: true,
                        position: 'inside'
                    },
                    labelLine: {
                        length: 3,
                        lineStyle: {
                            width: 1,
                            type: 'solid'
                        }
                    },
                    itemStyle: {
                        borderColor: '#fff',
                        borderWidth: 0.2
                    },
                    emphasis: {
                        label: {
                            fontSize: 8
                        }
                    },
                    data: [
                        {value: 20, name: '储值'},
                        {value: 40, name: '复购'},
                        {value: 55, name: '下单'},
                        {value: 70, name: '加购'},
                        {value: 85, name: '点击'},
                        {value: 100, name: '展现'}
                    ],
                    toolbox: {
                        feature: {
                            dataView: {readOnly: false},
                            restore: {},
                            saveAsImage: {}
                        }
                    },
                }
            ]

        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        window.addEventListener("resize",function(){
            myChart.resize();
        });
    }





    function echarts_35() {
        // 首单免费提醒
        var myChart = echarts.init(document.getElementById('new-user-coupon'));
        option = {

            title: [{
                text: '首单免费提醒',
                left: 'center',
                textStyle: {
                    color: '#fff',
                    fontSize:'16'
                }

            }],
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)",
                position:function(p){   //其中p为当前鼠标的位置
                    return [p[0] + 10, p[1] - 10];
                }
            },
            legend: {

                top:'70%',
                itemWidth: 10,
                itemHeight: 10,
                data:['前一天','前二天','前三天','前四天','前五天','前六天','前七天'],
                textStyle: {
                    color: 'rgba(255,255,255,.5)',
                    fontSize:'12',
                }
            },
            series: [
                {
                    name:'消费属性',
                    type:'pie',
                    center: ['50%', '42%'],
                    radius: ['40%', '60%'],
                    color: ['#065aab', '#066eab', '#0682ab', '#0696ab', '#06a0ab','#06b4ab','#06c8ab','#06dcab','#06f0ab'],
                    label: {show:false},
                    labelLine: {show:false},
                    data:[
                        {value:335, name:'前一天'},
                        {value:310, name:'前两天'},
                        {value:234, name:'前三天'},
                        {value:234, name:'前四天'},
                        {value:234, name:'前五天'},
                        {value:234, name:'前六天'},
                        {value:135, name:'前七天'}
                    ]
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        window.addEventListener("resize",function(){
            myChart.resize();
        });
    }
    function echarts_36() {
        // 优惠券失效提醒
        var myChart = echarts.init(document.getElementById('user-coupon-invalid'));
        option = {
            title: [{
                text: '优惠券失效提醒',
                left: 'center',
                textStyle: {
                    color: '#fff',
                    fontSize:'16'
                }

            }],
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)",
                position:function(p){   //其中p为当前鼠标的位置
                    return [p[0] + 10, p[1] - 10];
                }
            },
            legend: {
                top:'70%',
                itemWidth: 10,
                itemHeight: 10,
                data:['三天后','五天后','七天后','一天后'],
                textStyle: {
                    color: 'rgba(255,255,255,.5)',
                    fontSize:'12',
                }
            },
            series: [
                {
                    name:'消费属性',
                    type:'pie',
                    center: ['50%', '42%'],
                    radius: ['40%', '60%'],
                    color: ['#065aab', '#066eab', '#0682ab', '#0696ab', '#06a0ab','#06b4ab','#06c8ab','#06dcab','#06f0ab'],
                    label: {show:false},
                    labelLine: {show:false},
                    data:[
                        {value:335, name:'三天后'},
                        {value:310, name:'五天后'},
                        {value:234, name:'七天后'},
                        {value:234, name:'一天后'}
                    ]
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        window.addEventListener("resize",function(){
            myChart.resize();
        });
    }


})


















