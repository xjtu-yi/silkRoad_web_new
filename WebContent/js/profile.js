/**
 * http://202.117.15.155:8080/silkRoad/profiles.html
 */

$(document).ready(function () {
    var time = [];
    var times = [];
    var total = 0;
    
    $.ajax({
        url:"userInfo?user_id=802",
        async:false,
        success:function (jsonData) {
            var tem = jsonData;
            
            time = [tem.company, tem.country, tem.pebook, tem.regulation, tem.uansr, tem.uebook];
            // alert(time);
            
            times=[{name:"Company",value:tem.company}, {name:"Country",value:tem.country},
                {name:"Pebook",value:tem.pebook}, {name:"History and Culture",value:tem.uebook},
                {name:"Education of Science",value:tem.uansr}, {name:"IEEE",value:tem.regulation}];
        }
    });
    
    // alert(percents);
    document.getElementById('company').innerHTML = time[0];
    document.getElementById('country').innerHTML = time[1];
    document.getElementById('pebook').innerHTML = time[2];
    document.getElementById('regulation').innerHTML = time[3];
    document.getElementById('uansr').innerHTML = time[4];
    document.getElementById('uebook').innerHTML = time[5];

    // ECharts
    var myChart = echarts.init(document.getElementById('bar')); // 基于准备好的dom，初始化echarts实例
    
    var option = { // 指定图表的配置项和数据
	    title : {
	        text: 'Visiting times',
	        // subtext: '纯属虚构',
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        orient : 'vertical',
	        x : 'left',
	        data:['Company','Country','Pebook','History and Culture','Education of Science', 'IEEE']
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {
	                show: true, 
	                type: ['pie', 'funnel'],
	                option: {
	                    funnel: {
	                        x: '25%',
	                        width: '50%',
	                        funnelAlign: 'left',
	                        max: 1548
	                    }
	                }
	            },
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    series : [
	        {
	            name:'Visiting times',
	            type:'pie',
	            radius : '40%',
	            center: ['50%', '60%'],
	            data:times
	        }
	    ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    
});
