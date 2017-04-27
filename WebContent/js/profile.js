/**
 * http://202.117.15.155:8080/silkRoad/profiles.html
 */

function toPercent(point){
    var str=Number(point*100).toFixed(2);
    str+="%";
    return str;
}

$(document).ready(function () {
    var percents = [];
    var times = [];
    var total = 0;
    
    $.ajax({
        url:"http://202.117.15.155:8080/silkRoad/userInfo?user_id=103",
        // url:"http://123.206.87.123:8080/silkRoad2/user_info",
        async:false,
        success:function (jsonData) {
            for (var i = 0; i < jsonData.length; i++) {
                var tem = jsonData[i];
                
                total = tem.company + tem.country + tem.pebook + tem.regulation + tem.uansr + tem.uebook;
                
                percents = [tem.company/total, tem.country/total, tem.pebook/total, tem.regulation/total, 
                    tem.uansr/total, tem.uebook/total];
                
                times=[{name:"company",value:tem.company}, {name:"country",value:tem.country},
                    {name:"pebook",value:tem.pebook}, {name:"regulation",value:tem.regulation},
                    {name:"uansr",value:tem.uansr}, {name:"uebook",value:tem.uebook}];
            }
        }
    });
    
    alert(percents[0]);
    document.getElementById('company').innerHTML = toPercent(percents[0]);
    document.getElementById('country').innerHTML = toPercent(percents[1]);
    document.getElementById('pebook').innerHTML = toPercent(percents[2]);
    document.getElementById('regulation').innerHTML = toPercent(percents[3]);
    document.getElementById('uansr').innerHTML = toPercent(percents[4]);
    document.getElementById('uebook').innerHTML = toPercent(percents[5]);

    /*require.config({
        paths: {
            echarts: './build/dist/'
        }
    });
    require(
        [
            'echarts',
            'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载
            'echarts/chart/pie'
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('bar'));
            option = {
                title: {
                    text: 'Number of visiting the type of sources',
                    // subtext: '纯属虚构',
                    x: 'right'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data: ['News', 'Paper', 'Ebook', 'Patent', 'Conference']
                },
                calculable: true,
                series: [
                    {
                        name: 'type of source',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', "60%"],
                        data:data
                    }
                ]
            };

            var dataAxis = ['Company', 'Country', 'Pebook', 'IEEE', 'History and Culture'];
            var data4 = [];
            for (var i = 0;i<data.length;i++){
                data4[i]=data[i].value;
            }
            var yMax = 500;
            var dataShadow = [];

            for (var i = 0; i < data4.length; i++) {
                dataShadow.push(yMax);
            }

            option2 = {
                title : {
                    text: 'Number of visiting the type of sources',
                    x:'center'
                },
                tooltip : {
                    trigger: 'item'
                },

                calculable : true,
                xAxis : [
                    {   name : 'category',
                        type : 'category',
                        data:dataAxis
                    }
                ],
                yAxis : [
                    {   name:'number',
                        type : 'value'
                    }
                ],
                series : [

                    {
                        name:"number",
                        type:"bar",
                        itemStyle: {
                            normal: {
                                label : {
                                    show: true, position: 'top'
                                },
                                color: '#6495ED'
                            }
                        },
                        data:data4
                    }

                ]
            };

            myChart2.setOption(option2);

            setTimeout(function () {
                window.onresize = function () {
                    myChart2.resize();
                }
            }, 200)

        }
    );*/
});
