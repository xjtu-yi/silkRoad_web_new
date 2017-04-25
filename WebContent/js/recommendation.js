/**
 * 20161127 recommendation.js
 */
$(document).ready(function () {
    var data = [];
    $.ajax({
        url: "http://123.206.87.123:8080/silkRoad2/rec_info",
        //url: "http://202.117.15.155:8080/silkRoad2/rec_info",
        async: false,
        success: function (jsonData) {
            for (var i = 0; i < jsonData.length; i++) {
                var res = jsonData[i];
                var res_type = res.res_type;

                switch (res_type) {
                    case "news":
                        {
                            var title = res.title;
                            var content = res.content.substring(0,120) + ' ...';
                            var img_url = res.img_url;
                            var date = res.date.substring(0,10);
                            var url = res.url;
                            //alert(url);
                            var webContent = 
                                '<div style="margin:5px;padding:5px;height:70px;">' +
                                    '<div style="float:left;">' +
                                        '<img style="width:90px;height:60px" src="' + img_url + '">' +
                                    '</div>' +
                                    '<div style="float:left;margin-left:15px;word-break: break-word">' +
                                        '<p>' + 
                                            '<a href="' + url + '">' + 
                                                '<strong style="font-size: 16px; color: #00adef">' + title + '</strong>' + 
                                            '</a>' + 
                                        '</p>' +
                                        '<p style="margin-top:5px;width:800px;height:20px;overflow:hidden;">' + 
                                            content + 
                                        '</p>' +
                                        '<p style="margin-top:5px;font-size: 12px; color: #999;">' + 
                                            date + 
                                        '</p>' +
                                    '</div>' +
                                '</div>' +
                                '<hr style="width: 800px;margin-bottom: 10px; height:1px;border:none;border-top:1px dashed #CCCCCC;">';
                            $("#recNews").append(webContent);
                        }
                        break;
                    case "ebook":
                        {
                            var isbn = res.isbn;
                            var eisbn = res.eisbn;
                            var author = res.author;
                            var title = res.title;
                            var pages = res.pages;
                            var publisher = res.publisher;
                            var year = res.year;
                            var img_url = res.img_url;
                            var language = res.language;
                            var url = res.url;
                            var webContent = 
                                '<div style="margin:5px;padding:5px;height:110px;">' +
                                    '<div style="float:left;">' +
                                        '<img style="width:75px;height:110px" src="' + img_url + '">' +
                                    '</div>' +
                                    '<div style="float:left;margin-left:30px;word-break: break-word">' +
                                        '<p>' + 
                                            '<a href="' + url + '">' + 
                                                '<strong style="font-size: 16px; color: #00adef">' + title + '</strong>' + 
                                            '</a>' + 
                                        '</p>' +
                                        '<p style="width:800px;height:20px;margin-top:5px;font-size: 14px;color:#53cffe"> Author: ' + 
                                            author + 
                                        '</p>' +
                                        '<p style="margin-top:5px;font-size: 12px; color: #7c7c7c;">' + 
                                            '<span style="margin-right:40px;">ISBN: ' + isbn + '</span>' + '<span>EISBN: ' + eisbn + '</span>' +
                                        '</p>' +
                                        '<p style="margin-top:5px;font-size: 12px; color: #7c7c7c;">' + 
                                            '<span style="margin-right:40px;">Language: ' + language + '</span>' + 
                                            '<span style="margin-right:40px;">Publisher: ' + publisher + '</span>' +
                                            '<span style="margin-right:40px;">Year: ' + year + '</span>' +
                                            '<span>Pages: ' + pages + '</span>' +
                                        '</p>' +
                                    '</div>' +
                                '</div>' +
                                '<hr style="width: 800px;margin-bottom: 10px; height:1px;border:none;border-top:1px dashed #CCCCCC;">';
                            $("#recEbook").append(webContent);
                        }
                        break;
                    case "uansr":
                        {
                            var title = res.title;
                            var year = res.year;
                            var volume = res.volume;
                            var doi = res.doi;
                            var summary = res.summary.substring(0,600) + ' ...';
                            var url = res.url;
                            var webContent = 
                                '<div style="margin:5px;padding:5px;height:120px;">' +
                                    '<div style="">' +
                                        '<p>' + 
                                            '<a href="' + url + '">' + 
                                                '<strong style="font-size: 16px; color: #00adef">' + title + '</strong>' + 
                                            '</a>' + 
                                        '</p>' +
                                        '<p style="width:97%;height:20px;margin-top:5px;font-size: 14px;color:#53cffe">' + 
                                            '<span style="margin-right:40px;"> ' + doi + '</span>' + 
                                            '<span style="margin-right:40px;"> Year: ' + year + '</span>' + 
                                            '<span style="margin-right:40px;"> Volum: ' + volume + '</span>' + 
                                        '</p>' +
                                        '<p style="width:97%;height:80px;margin-top:5px;overflow:hidden;font-size: 12px;">' + 
                                            summary +
                                        '</p>' +
                                    '</div>' +
                                '</div>' +
                                '<hr style="width: 800px;margin-bottom: 10px; height:1px;border:none;border-top:1px dashed #CCCCCC;">';
                            $("#recPaper").append(webContent);
                        }
                        break;
                    case "conf":
                        {
                            var conference_name = res.conference_name;
                            var organizer = res.organizer;
                            var start_date = res.start_date;
                            var broad_theme = res.broad_theme;                
                            var url = res.url;
                            var webContent = 
                                '<div style="margin:5px;padding:5px;height:100px;">' +
                                    '<div style="">' +
                                        '<p>' + 
                                            '<a href="' + url + '">' + 
                                                '<strong style="font-size: 16px; color: #00adef">' + conference_name + '</strong>' + 
                                            '</a>' + 
                                        '</p>' +
                                        '<p style="width:97%;height:20px;margin-top:5px;font-size: 14px;color:#53cffe">' + 
                                            broad_theme +
                                        '</p>' +
                                        '<p style="width:97%;height:20px;margin-top:5px;overflow:hidden;font-size: 12px;">' + 
                                            'Organizer: ' + organizer +
                                        '</p>' +
                                        '<p style="width:97%;height:20px;margin-top:5px;overflow:hidden;font-size: 12px;">' + 
                                            'Start_time: ' + start_date +
                                        '</p>' +
                                    '</div>' +
                                '</div>' +
                                '<hr style="width: 800px;margin-bottom: 10px; height:1px;border:none;border-top:1px dashed #CCCCCC;">';
                            $("#recConf").append(webContent);
                        }
                        break;
                    case "patent":
                        {
                            var patent_number = res.patent_number;
                            var title = res.title;
                            var inventor = res.inventor;
                            var assignee_name = res.assignee_name_or_code;                
                            var url = res.url;
                            var webContent = 
                                '<div style="margin:5px;padding:5px;height:100px;">' +
                                    '<div style="">' +
                                        '<p>' + 
                                            '<a href="' + url + '">' + 
                                                '<strong style="font-size: 16px; color: #00adef">' + patent_number + '</strong>' + 
                                            '</a>' + 
                                        '</p>' +
                                        '<p style="width:97%;height:20px;margin-top:5px;font-size: 14px;color:#53cffe">' + 
                                            title +
                                        '</p>' +
                                        '<p style="width:97%;height:20px;margin-top:5px;overflow:hidden;font-size: 12px;">' + 
                                            'Assignee_name: ' + assignee_name +
                                        '</p>' +
                                        '<p style="width:97%;height:20px;margin-top:5px;overflow:hidden;font-size: 12px;">' + 
                                            'Inventor: ' + inventor +
                                        '</p>' +
                                    '</div>' +
                                '</div>' +
                                '<hr style="width: 800px;margin-bottom: 10px; height:1px;border:none;border-top:1px dashed #CCCCCC;">';
                            $("#recPatent").append(webContent);
                        }
                        break;
                }
            }
        }
    });
});