<%@ page language="java" import="org.silkroad.history.GetUserHistory, java.util.List, org.bson.Document" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>国际工程科技知识中心</title>

    <link rel="stylesheet" type="text/css" href="css/page.css">
    <link rel="stylesheet" type="text/css" href="resource/material/style.css">
    <link rel="stylesheet" type="text/css" href="css/history.css">
    
    <script type="text/javascript" src="resource/material/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="resource/material/ddsmoothmenu.js"></script>
    <script type="text/javascript" src="resource/material/koala.min.1.5.js"></script>
    
    <script type="text/javascript">
        // 收缩展开效果
        $(document).ready(function () {
            $("div.inszs_d").show(); // 默认隐藏div，或者在样式表中添加.text{display:none}，推荐使用后者
            $(".zs_bos .zhank").click(function () {
                $(this).next(".inszs_d").slideToggle("slow");
            })
        });
    </script>

    <style>
        .course_list .course_title a {
            color: #262626;
            font-size: 22px;
        }

        .dis_con {
            border: 0;
            padding-bottom: 15px;
            margin-top: 0;
        }

        .dis_con ul li {
            border-bottom: 1px solid #ccc;
            padding: 15px 0;
            line-height: 18px;
            background: none;
        }

        .ebook_cont_right {
            width: 900px;
        }

        .disCon_title {
            font-size: 16px;
        }

        .dis_con ul li .disCon_title a {
            color: #262626;
            font-size: 16px;
        }

        .disCon_right {
            width: 900px;
        }

        .ins_list ul li {
            background: none;
            width: 1095px;
        }

        .ins_list ul li .ins_title a {
            color: #262626;
            font-size: 20px;
        }

        .patent_title03 ul li {
            float: none;
            background: none;
            padding-left: 0;
        }
    </style>
    
    <link rel="stylesheet" type="text/css" href="resource/material/_sitegray_d.css">
    <script language="javascript" src="resource/material/_sitegray.js"></script>
    <link rel="stylesheet" type="text/css" href="resource/material/index.vsb.css">
    <meta name="keywords" content="国际工程科技知识中心">
    <script type="text/javascript" src="resource/material/vsbscreen.min.js" id="_vsbscreen" devices="pc|pad"></script>
    <script type="text/javascript" src="resource/material/counter.js"></script>
    <script type="text/javascript">_jsq_(1001,'/index.jsp',-1,1258519432)</script> 
</head>

<body style="zoom: 1">

    <div id="wrapper">

        <!-- head  start -->
        <div class="head">
            <div class="topbar">
                <div class="topbar_con">
                    <div class="topbar_left fl">
                        <script language="javascript" src="resource/material/mp4video.js"></script>
                        <table width="100%">
                            <tbody>
                            <tr>
                                <td>&nbsp;Welcome to the IKCEST</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="head_m">
                <div class="head_t">
                    <div class="logo">
                        <table border="0" cellspacing="0" cellpadding="0">
                            <tbody>
                                <tr>
                                    <td>
                                        <img src="resource/material/logo2.jpg" border="0" alt="国际工程科技知识中心" title="国际工程科技知识中心">
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div id="lib_Tab1" class="head_r">
                        <div class="head_r_link fl">
                        </div>
                        <div id="con_one_1">
                            <div class="search">
                                <script type="text/javascript">
                                    function search_check() {
                                        var keyword = document.getElementById('showkeycode49439').value;
                                        if (window.toFF == 1) {
                                            document.getElementById("searchkeyword").value = Simplized(keyword);
                                        } else {
                                            document.getElementById("searchkeyword").value = keyword;
                                        }
                                        document.getElementById("search_actiontype").value = "search";

                                        var base64 = new Base64();
                                        document.getElementById("searchkeyword").value = base64.encode(document.getElementById("showkeycode49439").value);
                                        new VsbFormFunc().disableAutoEnable(document.getElementById("showkeycode49439"));

                                        return true;
                                    }
                                    function changepage_search(viewid, searchValue, page) {
                                        document.getElementById("a49439a").action = "new_search_zhss.jsp?urltype=tree.TreeTempUrl&wbtreeid=1293&currentPage" + viewid + "=" + page + "&hoverviewid=" + viewid;
                                        document.getElementById("searchkeyword").value = searchValue;
                                        document.getElementById("a49439a").submit();
                                    }
                                </script>
                                <form action="http://ikcest.xjtu.edu.cn/new_search_zhss.jsp?urltype=tree.TreeTempUrl&amp;wbtreeid=1293"
                                      method="post" id="a49439a" name="a49439a" onsubmit="return search_check()"
                                      style="display: inline">
                                    <input type="hidden" id="search_actiontype" name="search_actiontype" value="">
                                    <input type="hidden" id="searchkeyword" name="searchkeyword" value="">
                                    <input name="showkeycode" id="showkeycode49439" value=""
                                           style="width:400px;padding-left:10px;border:0;margin-left:2px;">
                                    <input type="image" src="resource/material/ss.gif" align="absmiddle"
                                           style="cursor: hand">
                                </form>
                                <script language="javascript" src="resource/material/base64.js"></script>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="head_d">
                <div id="smoothmenu1" class="ddsmoothmenu">                    
    			<ul>
    			  <li style="height:35px;width:228px;"><a href="http://ikcest.xjtu.edu.cn/index.htm">Home</a></li>
    			  <li style="height:35px;width:228px;"><a href="http://ikcest.xjtu.edu.cn/News.htm">News</a></li>
    			  <li style="height:35px;width:228px;"><a href="http://ikcest.xjtu.edu.cn/Symposium.jsp?urltype=tree.TreeTempUrl&amp;wbtreeid=1294">Symposium</a></li>
    			  <li style="height:35px;width:228px;z-index: 20;"><a href="#" class="">Characteristic Database</a>
    			    <ul class="ul_4" style="top: 35px; visibility: visible; margin-left:550px;; width: 1140px; display: none;">
    			      <li><a href="http://ikcest.xjtu.edu.cn/culture-list.jsp?urltype=tree.TreeTempUrl&amp;wbtreeid=1472">History And Culture </a></li>
    			       <li style="width:10px;color:#00adef;">|</li>
    			      <li><a href="http://ikcest.xjtu.edu.cn/education.jsp?urltype=tree.TreeTempUrl&amp;wbtreeid=1473">Education Of Science</a></li>
    			       <li style="width:10px;color:#00adef;">|</li>
    			      <li><a href="http://ikcest.xjtu.edu.cn/state.jsp?urltype=tree.TreeTempUrl&amp;wbtreeid=1474">Nation Conditions</a></li>
    			       <li style="width:10px;color:#00adef;">|</li>
    			      <li><a href="http://ikcest.xjtu.edu.cn/gy_list.jsp?urltype=tree.TreeTempUrl&amp;wbtreeid=1475">Fortune Global 500</a></li>
    			       <li style="width:10px;color:#00adef;">|</li>
    			      <li><a href="http://ikcest.xjtu.edu.cn/ieee.jsp?urltype=tree.TreeTempUrl&amp;wbtreeid=1476">IEEE</a></li>
    			    </ul>
    			  </li>
    			  <li style="height:35px;width:228px;"><a href="http://ikcest.xjtu.edu.cn/About_US2.htm">About US</a></li>
    			</ul>                
                </div>
                <script type="text/javascript">
                    ddsmoothmenu.init({
                        mainmenuid: "smoothmenu1", //menu DIV id
                        orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
                        classname: 'ddsmoothmenu', //class added to menu's outer DIV
                        //customtheme: ["#1c5a80", "#18374a"],
                        contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
                    })
                </script>
                <div class="sub_nav"></div>
            </div>
        </div>
        <!-- head  end -->

        <!-- 小标题 start -->
        <div class="main">
    		<div class="list">
    			<div class="list_right">
    				<div class="list_r_t">
    					<span class="location">
    						<table class="winstyle49271" cellspacing="0" cellpadding="0">
    							<tbody>
    								<tr>
    									<td nowrap="">
    										<a href="http://ikcest.xjtu.edu.cn/index.htm">
    											 <span class="fontstyle49271">Home</span>
    										</a>
    										<span class="splitflagstyle49271">&gt;&gt;</span>
    										<a href="#">
    											 <span class="fontstyle49271">Personalized Service</span>
    										</a>
    									</td>
    								</tr>
    							</tbody>
    						</table>
    					</span>
                        <span class="name_list dqlm">
    					    <span class="windowstyle49270"> Personalized Service</span>
    					</span>
    				</div>
    			</div>
    		</div>
    	</div>
        <!-- 小标题 end -->

    	<!-- main body start -->
        <div class="jd_main" style="min-height: 1100px">
            <div class="jd_main_sidebar">
                <a href="profiles.html" class="cur">Profiles</a>
                <a href="hotResources.html">Hot Resources</a>
                <a href="recommendation.html">Recommendation</a>
            </div>
            <div class="jd_main_content" style="position: relative">
                <span id="historyBand">History of Company</span>
                
                <!-- User history start -->
                <%
                    String res_type = request.getAttribute("res_type").toString();
                    String user_id = request.getAttribute("user_id").toString();
                    
                    //System.out.println("companyHistory " + user_id);
                    
                    List<Document> docsArr = GetUserHistory.getHistory(user_id, res_type);
                    
                    int n = docsArr.size();
                    
                    Integer times = 0;
                    String name = null;
                    String summary = null;
                    String location = null;
                    String country = null;
                    String doc_url = null;
                    String rankings = null;
                    for(Document doc : docsArr) {
                    	times = doc.getInteger("times");
                    	name = doc.getString("name");
                    	summary = doc.getString("summary");
                    	location = doc.getString("location");
                    	country = doc.getString("country");
                    	doc_url = doc.getString("doc_url");
                    	rankings = doc.getString("rankings");
                %>
                <div class="company">
                    <a href="<%out.println(doc_url);%>">
                        <strong class="companyName"><%out.println(name);%></strong>
                    </a>
                    <p class="company-tag">Abstract</p>
                    <p style="height:67px; overflow:hidden;"><%out.println(summary);%></p>
                    <p>
                        <storng class="company-tag">Country</storng>&nbsp;<%out.println(country);%>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <storng class="company-tag">Location</storng>&nbsp;<%out.println(location);%>
                    </p>
                    <p class="company-tag">Rankings</p>
                    <p><%out.println(rankings);%></p>
                    <hr class="split">
                </div>
                <div class="times">
                    <% out.println(times); %>
                </div>
                <%
                    }
                %>
                <!-- User history stop -->
                
            </div>
        </div>
        <!-- main body end -->

        <!-- foot start -->
        <div class="foot">
            <div class="foot_link">
                <ul>
                    <script language="javascript" src="resource/material/openlink.js"></script>
                    <li class="foot_last"></li>
                    <li class="foot_last">
                        <a href="http://ikcest.xjtu.edu.cn/index.htm#" target="_blank" title="" onclick="">站长统计</a>
                    </li>
                </ul>
            </div>
            <div class="foot_left fl">
                <div class="foot_pic">
                    <table border="0" cellpadding="0" cellspacing="0">
                        <tbody>
                            <tr>
                                <td>
                                    <img src="resource/material/f_11.jpg" width="452" height="78" hspace="0" vspace="0" border="0">
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="foot_copy">
                    <p></p>
                    <p>
                        ICP备案号：京ICP14021735号-1&nbsp;&nbsp;©2008-2015 IKCEST All rights reserved
                    </p>
                    <p></p>
                </div>
            </div>
        </div>
        <!--foot end-->

    </div>

</body>
</html>