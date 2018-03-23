<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ include file="base.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>菜鸟科技</title>
    <style type="text/css">
        body {
            position: relative;
            background: #EFF2F4;
            font: normal 13px "宋体" !important;
        }

        body, div, ul, li, p, a, img {
            padding: 0;
            margin: 0;
        }

        /*右侧悬浮菜单*/
        .slide {
            width: 88px;
            height: 420px;
            position: fixed;
            top: 30%;
            margin-top: -126px;
            background: gainsboro;/*#018D75*/
            right: 0;
            border-radius: 5px 0 0 5px;
            z-index: 999;
        }

        .slide ul {
            list-style: none;
        }

        .slide .icon li {
            width: 49px;
            height: 50px;
            background: url(/staticfile/image/index/icon.png) no-repeat;
        }

        .slide .icon .up {
            background-position: -330px -120px;
        }

        .slide .icon li.down {
            background-position: -330px -160px;
        }



        /*控制菜单的按钮*/
        .index_cy {
            width: 30px;
            height: 30px;
            background: url(/staticfile/image/index/index_cy.png);
            position: fixed;
            right: 0;
            top: 50%;
            margin-top: 140px;
            background-position: 62px 0;
            cursor: pointer;
        }

        .index_cy2 {
            width: 30px;
            height: 30px;
            background: url(/staticfile/image/index/index_cy.png);
            position: fixed;
            right: 0;
            top: 50%;
            margin-top: 140px;
            background-position: 30px 0;
            cursor: pointer;
        }

        /*自适应 当屏小于1050时隐藏*/
        @media screen and (max-width: 400px) {
            .slide {
                display: none;
            }
            #btn {
                display: none;
            }
        }
        .popover {
            max-width: 2000px;
        }

    </style>



</head>
<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <div class="col-md-3 left_col">
            <div class="left_col scroll-view">
                <div class="navbar nav_title" style="border: 0;">
                    <a href="/test" class="site_title"><i class="fa fa-paw"></i><span>菜鸟科技</span></a>
                </div>
                <div class="clearfix"></div>
                <div class="profile clearfix">
                    <div class="profile_pic">
                        <img src="${ctx}\getHeadImg?url=${session_user.headUrl}" class="img-circle profile_img" alt="头像">
                    </div>
                    <div class="profile_info">
                        <span>欢迎您,</span>
                        <h2>${session_user.username}</h2>
                    </div>
                </div>
                <br />

                <!-- 左侧菜单-->
                <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                    <div class="menu_section">
                        <h3>功能导航</h3>
                        <ul class="nav side-menu">
                            <li><a><i class="fa fa-home"></i>个人事务<span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <li><a href="${ctx}/toCal" target="maincontext">日程管理</a></li>
                                    <li><a href="/workflow/listTask.action?user_name=${session_user.username}" target="maincontext">个人任务</a></li>
                                    <li><a href="javascript:void(0)0" target="maincontext">通讯录</a></li>
                                </ul>
                            </li>
                            <li><a><i class="fa fa-clone"></i>导航信息<span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <li><a href="/tonotice" target="maincontext">公司公告</a></li>
                                    <li><a href="/toonduty" target="maincontext">值班记录</a></li>
                                </ul>
                            </li>
                            <shiro:hasPermission name="人事管理">
                            <li>
                                <a><i class="fa fa-edit"></i>人事管理<span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <shiro:hasPermission name="部门管理">
                                    <li><a href="/todeptlist" target="maincontext">部门管理</a></li>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="员工管理">
                                    <li><a href="/toUserlist" target="maincontext">员工管理</a></li>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="角色管理">
                                    <li><a href="/torolelist" target="maincontext">角色管理</a></li>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="模块管理">
                                    <li><a href="/tomodulelist" target="maincontext">模块管理</a></li>
                                    </shiro:hasPermission>
                                </ul>
                            </li>
                            </shiro:hasPermission>
                            <li>
                                <a><i class="fa fa-desktop"></i>流程管理 <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <li><a href="${ctx}/workflow/toselectflow.action" target="maincontext">发起流程</a></li>
                                    <li><a href="${ctx}/workflow/flowList.action" target="maincontext">流程列表</a></li>
                                    <%--<li><a href="liucheng.html" target="maincontext">办结流程</a></li>--%>
                                </ul>
                            </li>
                            <shiro:hasPermission name="工作日记">
                            <li>
                                <a><i class="fa fa-bar-chart-o"></i> 工作日记 <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <shiro:hasPermission name="日记录入">
                                    <li><a href="/diary/entry?userId=${session_user.userId}"  target="maincontext">日记录入</a></li>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="日记查询">
                                    <li><a href="/diary/show?userId=${session_user.userId}"  target="maincontext">日记查询</a></li>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="日记管理">
                                    <li><a href="/diary/manage?deptName=${session_user.dept.deptName}" target="maincontext">日记管理</a></li>
                                    </shiro:hasPermission>
                                </ul>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="日志管理">
                            <li>
                                <a><i class="fa fa-bar-chart-o"></i> 日志管理 <span class="fa fa-chevron-down"></span></a>
                                <ul class="nav child_menu">
                                    <shiro:hasPermission name="登录日志管理">
                                    <li><a href="/logInfo/list" target="maincontext">登录日志管理</a></li>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="操作日志管理">
                                    <li><a href="/commitLog/list" target="maincontext">操作日志管理</a></li>
                                    </shiro:hasPermission>
                                </ul>
                            </li>
                            </shiro:hasPermission>
                        </ul>
                        <div style="margin-top:100px">
                            <iframe frameborder='no' scrolling='no'  src='toMessageNew' name='iframe_c' width='100%' height='400px'></iframe>
                        </div>
                    </div>
                </div><!--左侧菜单结束-->

                <!-- /menu footer buttons -->
                <div class="sidebar-footer hidden-small">
                    <a data-toggle="tooltip" data-placement="top" title="Settings">
                        <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                    </a>
                    <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                        <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
                    </a>
                    <a data-toggle="tooltip" data-placement="top" title="Lock">
                        <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
                    </a>
                    <a data-toggle="tooltip" data-placement="top" title="Logout" href="/logout">
                        <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
                    </a>
                </div>
                <!-- /menu footer buttons -->

            </div>
        </div>

        <!--顶部-->
        <div class="top_nav">
            <div class="nav_menu">
                <nav>
                    <div class="nav toggle">
                        <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                    </div>
                        <a href="/toMessageBoard" target="maincontext" style="color: #d43f3a;font-size: 28px;"> 私信空间 </a>
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                <img src="${ctx}\getHeadImg?url=${session_user.headUrl}" alt="">${session_user.username}
                                <span class=" fa fa-angle-down"></span>
                            </a>
                            <ul class="dropdown-menu dropdown-usermenu pull-right">
                                <li><a href="javascript:;">帮助</a></li>
                                <li><a href="/logout"><i class="fa fa-sign-out pull-right"></i> 登出</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>

        <div class="right_col" role="main" style="padding: 0">
            <div class="right_col embed-responsive embed-responsive-16by9" style="margin-left: 0;">
                <iframe frameborder="no"  src="${ctx}/test" name="maincontext">
                </iframe>
            </div>
        </div>
    </div>
</div>

<!--右侧悬浮菜单-->
<div class="slide">
    <ul class="icon" >
        <li class="up" title="上一页"></li>

        <c:forEach items="${users}" var="u">
            <p class="popover-options" align="center">
                <a href="#" type="button" class="btn btn-warning" title="<h6>聊天窗口</h6>"
                   data-container="body" data-toggle="popover" data-placement="left" style="width: 66px"
                   data-content="
            <iframe frameborder='no' scrolling='no'  src='toTalk?toId=${u.key}' name='iframe_a' width='350' height='500'></iframe>
           "> ${u.value.name}</a>
            </p>
        </c:forEach>
    <li class="down" title="下一页"></li>
    </ul>

</div>
<div id="btn" class="index_cy"></div>


<script src="${ctx}/staticfile/js/bootstrap.js"></script>
<script src="${ctx}/staticfile/js/custom.min.js"></script>
<script src="${ctx}/staticfile/js/messageindex.js"></script>
</body>
</html>
