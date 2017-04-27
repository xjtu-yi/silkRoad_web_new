$(document).ready(function () {
	var user_id =102;
    $.ajax({
        url: "recommend?type=1&user_id="+user_id,
        async: false,
        success: function (jsonData) {
        	
            for (var i = 0; i < 5; i++) {
                var t = jsonData[i];
     
	                var name = t.name;
	                var location = t.location;
	                var country = t.country;
	                var doc_url = t.url;
	                var rankings = t.rankings;
	                var summary = t.summary; // not use
	                var u1 = '<div style="broder-style:solid; broder-color:#000;width: 100%;height: 70px;margin-top: 20px">' +
                    '<div style="position: relative;height: 70px;float: left;margin-left: 10px;word-break: break-word">' +
                    '<p><a href="' + doc_url + '"><strong style="font-size: 14px; color: #00adef">' + name + '</strong></a></p>' +
                    '<p style="font-size: 12px; color: #999;"><b>Country</b>&nbsp;' + country + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Location&nbsp;</b>' + location + '</p>' +
                    '<p style="font-size: 12px; color: #999;"> <b>Rankings</b> </p>' +
                    '<p style="width: 90%; height: 13px; overflow: hidden; font-size: 12px; color: #999;">' + rankings + '</p>' +
                    '</div>' +
                    '</div>' +

                    '<hr style="width: 400px;margin-bottom: 10px; height:1px;border:none;border-top:1px dashed #CCCCCC;">';
	                $(".companylist").append(u1);            
	                            
            }
        }
    });
    
    
    $.ajax({
        url: "recommend?type=2&user_id="+user_id,
    	// url: "http://123.206.87.123:8080/silkRoad2/news_info",
        async: false,
        success: function (jsonData) {
        	
            for (var i = 0; i < 5; i++) {
                var t = jsonData[i];
     
                var title = t.title;
                var abstract = t.abstract; // not use
                var publish_place = t.publish_place;
                var pub_date = t.pub_date;
                var doc_url = t.doc_url;
                var subjects = t.palce;

                var u1 = '<div style="broder-style:solid; broder-color:#000;width: 100%;height: 70px;margin-top: 20px">' +
                '<div style="position: relative;height: 70px;float: left;margin-left: 10px;word-break: break-word">' +
                '<p><a href="' + doc_url + '"><strong style="font-size: 14px; color: #00adef">' + title + '</strong></a></p>' +
                '<p style="font-size: 12px; color: #999;"><b>Publish Place</b>&nbsp;' + publish_place + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Publish Date&nbsp;</b>' + pub_date + '</p>' +
                '<p style="font-size: 12px; color: #999;"> <b>Subjects</b> </p>' +
                '<p style="font-size: 12px; color: #999;">' + subjects + '</p>' +
                '</div>' +
                '</div>' +

                '<hr style="width: 400px;margin-bottom: 10px; height:1px;border:none;border-top:1px dashed #CCCCCC;">';
            $(".countrylist").append(u1);
	                            
            }
        }
    });
    
    $.ajax({
        url: "recommend?type=3&user_id="+user_id,
    	// url: "http://123.206.87.123:8080/silkRoad2/news_info",
        async: false,
        success: function (jsonData) {
        	
            for (var i = 0; i < 5; i++) {
                var t = jsonData[i];
     
                var title = t.title;
                var doi = t.doi;
                var date_publication = t.date_publication;
                var summary = t.summary;
                var text_url = t.text_url;

                var u1 = '<div style="broder-style:solid; broder-color:#000;width: 100%;height: 105px;margin-top: 20px">' +
                '<div style="position: relative;height: 70px;float: left;margin-left: 10px;word-break: break-word">' +
                '<p><a href="' + text_url + '"><strong style="font-size: 14px; color: #00adef">' + title + '</strong></a></p>' +
                '<p style="font-size: 12px; color: #999;"><b>Doi</b>&nbsp;' + doi + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Publish Date&nbsp;</b>' + date_publication + '</p>' +
                '<p style="font-size: 12px; color: #999;"> <b>Summary</b> </p>' +
                '<p style="font-size: 12px; color: #999; height: 35px; width: 90%; overflow: hidden;">' + summary + '</p>' +
                '</div>' +
                '</div>' +

                '<hr style="width: 400px;margin-bottom: 10px; height:1px;border:none;border-top:1px dashed #CCCCCC;">';
            $(".pebooklist").append(u1);
	                            
            }
        }
    });
    $.ajax({
        url: "recommend?type=4&user_id="+user_id,
    	// url: "http://123.206.87.123:8080/silkRoad2/news_info",
        async: false,
        success: function (jsonData) {
        	
            for (var i = 0; i < 5; i++) {
                var t = jsonData[i];
     
                var title = t.title;
                var doi = t.doi;
                var date_publication = t.date_publication;
                var abstract = t.abstract; // not use
                var url = t.url;
                var full_text = t.full_text_url;

                var u1 = '<div style="broder-style:solid; broder-color:#000;width: 100%;height: 105px;margin-top: 20px">' +
                '<div style="position: relative;height: 70px;float: left;margin-left: 10px;word-break: break-word">' +
                '<p style="height: 40px; width: 90%; overflow: hidden;"><a href="' + url + '"><strong style="font-size: 14px; color: #00adef; width:80%;">' + title + '</strong></a></p>' +
                '<p style="font-size: 12px; color: #999;"><b>Doi</b>&nbsp;' + doi + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Publish Date&nbsp;</b>' + date_publication + '</p>' +
                '<p style="font-size: 12px; color: #999;"> <b>Full text URL</b> </p>' +
                '<a style="font-size: 12px; color: #999;" href='+full_text +'>' + full_text + '</a>' +
                '</div>' +
                '</div>' +

                '<hr style="width: 400px;margin-bottom: 10px; height:1px;border:none;border-top:1px dashed #CCCCCC;">';
            $(".regulationlist").append(u1);
	                            
            }
        }
    });
    $.ajax({
        url: "recommend?type=5&user_id="+user_id,
    	// url: "http://123.206.87.123:8080/silkRoad2/news_info",
        async: false,
        success: function (jsonData) {
        	
            for (var i = 0; i < 5; i++) {
                var t = jsonData[i];
     
                var title = t.title;
                var doi = t.doi;
                var author = t.author;
                var summary = t.summary; // not use
                var year = t.year;
                var url = t.url;

                var u1 = '<div style="broder-style:solid; broder-color:#000;width: 100%;height: 105px;margin-top: 20px">' +
                '<div style="position: relative;height: 70px;float: left;margin-left: 10px;word-break: break-word">' +
                '<p style="height: 56px; width: 90%; overflow: hidden;"><a href="' + url + '"><strong style="font-size: 14px; color: #00adef">' + title + '</strong></a></p>' +
                '<p style="font-size: 12px; color: #999;"><b>Doi</b>&nbsp;' + doi + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Year&nbsp;</b>'+ year + '</p>' +
                '<p style="font-size: 12px; color: #999;"> <b>Author</b> </p>' +
                '<p style="height: 13px; width: 90%; overflow: hidden;font-size: 12px; color: #999;">' + author + '</p>' +
                '</div>' +
                '</div>' +

                '<hr style="width: 400px;margin-bottom: 10px; height:1px;border:none;border-top:1px dashed #CCCCCC;">';
            $(".uansrlist").append(u1);
	                            
            }
        }
    });
    $.ajax({
        url: "recommend?type=6&user_id="+user_id,
        async: false,
        success: function (jsonData) {
        	
            for (var i = 0; i < 5; i++) {
                var t = jsonData[i];
     
                var title = t.title; // 需要修改！
                var doi = t.doi;
                var author = t.author;
                var summary = t.summary; // not use
                var year = t.year;
                var url = t.url;
                var img_url = t.img_url;
                var nation = t.nation;

                var u1 = '<div style="broder-style:solid; broder-color:#000;width: 100%;height: 105px;margin-top: 20px">' +
                '<div style="float: left;">' +
                '<img style="width: 70px;height: 90px;" src="' + img_url + '">' +
                '</div>' +
                '<div style="position: relative; width: 350px; height: 70px;float: left;margin-left: 10px;word-break: break-word">' +
                '<p><a href="' + url + '"><strong style="font-size: 14px; color: #00adef">' + title + '</strong></a></p>' +
                '<p style="font-size: 12px; color: #999;"> <b>Author</b> </p>' +
                '<p style="font-size: 12px; color: #999;">' + author +  '</p>' +
                '<p style="font-size: 12px; color: #999;"><b>Nation</b>&nbsp;' + nation + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Year&nbsp;</b>'+ year + '</p>' +
                '</div>' +
                '</div>' +

                '<hr style="width: 400px;margin-bottom: 10px; height:1px;border:none;border-top:1px dashed #CCCCCC;">';
            $(".uebooklist").append(u1);
	                            
            }
        }
    });
    
    
});