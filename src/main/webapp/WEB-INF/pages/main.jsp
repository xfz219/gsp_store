<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ include file="taglibs.jsp" %>
<jsp:include page="./editPwd.jsp"></jsp:include>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>gsp</title>
</head>
<body class="easyui-layout">

<div region="north" border="true" style="height: 55px;">
    <%@ include file="header.jsp" %>
</div>
<div data-options="region:'west',split:true,title:'功能导航'" style="height: 200px; padding: 1px; width: 200px;">
    <div style="margin: 10px 0;">
        <ul class="easyui-tree tree">
            <li>
                <div class="tree-node" node-id="938" style="cursor: pointer;"><span class="tree-title">基础信息</span>
                </div>
                <ul style="display: none;">
                    <li>
                        <div class="tree-node" node-id="940" style="cursor: pointer;">
                            <a class="tree-title" href="user" >供应商信息</a></div>
                    </li>
                    <li>
                        <div class="tree-node" node-id="940" style="cursor: pointer;">
                            <a class="tree-title" href="user" >品种信息</a></div>
                    </li>
                </ul>
            </li>
        </ul>

        <ul class="easyui-tree tree">
            <li>
                <div class="tree-node" node-id="938" style="cursor: pointer;"><span class="tree-title">采购管理</span>
                </div>
                <ul style="display: none;">
                    <li>
                        <div class="tree-node" node-id="940" style="cursor: pointer;">
                            <a class="tree-title" href="user" >采购入库单</a></div>
                    </li>
                    <li>
                        <div class="tree-node" node-id="940" style="cursor: pointer;">
                            <a class="tree-title" href="user" >采购退货单</a></div>
                    </li>
                </ul>
            </li>
        </ul>

        <ul class="easyui-tree tree">
            <li>
                <div class="tree-node" node-id="938" style="cursor: pointer;"><span class="tree-title">销售管理</span>
                </div>
                <ul style="display: none;">
                    <li>
                        <div class="tree-node" node-id="940" style="cursor: pointer;">
                            <a class="tree-title" href="user" >价格查询</a></div>
                    </li>
                    <li>
                        <div class="tree-node" node-id="940" style="cursor: pointer;">
                            <a class="tree-title" href="user" >销售出库单</a></div>
                    </li>
                    <li>
                        <div class="tree-node" node-id="940" style="cursor: pointer;">
                            <a class="tree-title" href="user" >销售退货单</a></div>
                    </li>
                </ul>
            </li>
        </ul>

        <ul class="easyui-tree tree">
            <li>
                <div class="tree-node" node-id="938" style="cursor: pointer;"><span class="tree-title">库存管理</span>
                </div>
                <ul style="display: none;">
                    <li>
                        <div class="tree-node" node-id="940" style="cursor: pointer;">
                            <a class="tree-title" href="user" >库存汇总</a></div>
                    </li>
                    <li>
                        <div class="tree-node" node-id="940" style="cursor: pointer;">
                            <a class="tree-title" href="user" >库存明细</a></div>
                    </li>
                    <li>
                        <div class="tree-node" node-id="940" style="cursor: pointer;">
                            <a class="tree-title" href="user" >库存盘点</a></div>
                    </li>
                </ul>
            </li>
        </ul>

        <ul class="easyui-tree tree">
            <li>
                <div class="tree-node" node-id="938" style="cursor: pointer;"><span class="tree-title">基础信息</span>
                </div>
                <ul style="display: none;">
                    <li>
                        <div class="tree-node" node-id="940" style="cursor: pointer;">
                            <a class="tree-title" href="user" >用户管理</a></div>
                    </li>
                    <li>
                        <div class="tree-node" node-id="940" style="cursor: pointer;">
                            <a class="tree-title" href="user" >用户管理</a></div>
                    </li>
                </ul>
            </li>
        </ul>
        <ul class="easyui-tree tree">
            <li>
                <div class="tree-node" node-id="938" style="cursor: pointer;"><span class="tree-title">角色管理</span>
                </div>
                <ul style="display: none;">
                    <li>
                        <div class="tree-node" node-id="940" style="cursor: pointer;">
                            <a class="tree-title" href="user" >用户管理</a></div>
                    </li>
                    <li>
                        <div class="tree-node" node-id="940" style="cursor: pointer;">
                            <a class="tree-title" href="user" >角色管理</a></div>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<div id="mainPanle" region="center" border="true" border="false">
    <div id="tabs" class="easyui-tabs" fit="true" border="false">
        <div title="主页">
            <div class="cs-home-remark">
                <h1>这里是GSP系统</h1> <br>
            </div>
        </div>
    </div>
</div>
<div data-options="region:'south',border:'none'" style="height:20px;">
    <%@ include file="footer.jsp" %>
</div>
<!-- 菜单树、选项卡 -->
<script type="text/javascript" charset="UTF-8">
    var _treeView = $('#treeView');//菜单栏
    var _centerTabs = $('#tabs');//选项卡栏
    $(function () {
        //加载左侧菜单树
        <%--_treeView.tree({--%>
            <%--checkbox: false,--%>
            <%--url: "<%=request.getContextPath()%>/treeView?id=",--%>
            <%--onBeforeExpand: function (node) {--%>
                <%--_treeView.tree("options").url = "<%=request.getContextPath()%>/treeView?id=" + node.id;--%>
            <%--},--%>
            <%--onClick: function (node) {--%>
                <%--var click_obj = $('#treeView').tree('getSelected');--%>
                <%--if (click_obj.state == 'open' && click_obj.attributes == null) {--%>
                    <%--_treeView.tree('collapse', node.target);--%>
                <%--} else if (click_obj.state == 'closed' && click_obj.attributes == null) {--%>
                    <%--_treeView.tree('expand', node.target);--%>
                <%--} else {--%>
                    <%--var _tit = node.text;--%>
                    <%--var _contant = node.attributes["url"];--%>
                    <%--var _tabCls = node.iconCls;--%>
                    <%--addNewTab(_tit, _contant, _tabCls, node.id);--%>
                <%--}--%>
            <%--}--%>
        <%--});--%>
        <%--id--%>

        //给菜单添加点击事件
        $('.easyui-tree a').click(function () {
            var _tit = $(this).html(),
                _contant = $(this).attr("href"),
                _tabCls = $(this).attr("tabCls");
            addNewTab(_tit, _contant, _tabCls, $(this).attr("id"));
            return false;
        });

        //加载选项卡
        _centerTabs.tabs({
            fit: true,
            border: false,
            onContextMenu: function (e, title) {
                e.preventDefault();
                tabsMenu.menu('show', {
                    left: e.pageX,
                    top: e.pageY
                }).data('tabTitle', title);
            }
        });

        //添加选项卡
        function addNewTab(title, href, tabCls, nodeId) {
            //选项卡索引
            var index;

            //选择存在的选项卡
            $(_centerTabs.tabs('tabs')).each(function (i) {
                if (this[0].id == 'tree' + nodeId) {
                    index = _centerTabs.tabs('getTabIndex', this);
                    _centerTabs.tabs('select', index);
                    return false;
                }
            });

            //不存在，则创建选项卡
            if (!_centerTabs.tabs('exists', index)) {
                var content = '木有可以加载的页面';
                if (Boolean(href)) {
                    content = '<iframe scrolling="no" frameborder="0"  src="' + href + '" style="width:100%;height:99%;"></iframe>';
                }

                _centerTabs.tabs({
                    scrollIncrement: 100
                }).tabs('add', {
                    id: 'tree' + nodeId,
                    title: title,
                    content: content,
                    closable: true,
                    iconCls: tabCls
                });
            }
        }
    });
</script>
</body>
</html>