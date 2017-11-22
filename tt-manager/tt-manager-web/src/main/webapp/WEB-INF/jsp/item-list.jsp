<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--容器放好--%>
<table id="dgItems"></table>
<%--通过js代码来渲染容器--%>
<script>
    //自定义工具栏
    var itemListToolbar = [{
        iconCls: 'icon-add',
        text: '新增',
        handler: function () {
            console.log('add');
        }
    }, {
        iconCls: 'icon-remove',
        text: '删除',
        handler: function () {
            //debugger; //尤其可以使用这种嵌套的页面
            //取到客户选中的记录集合
            var rows = $('#dgItems').datagrid('getSelections');
//            console.log(rows);
            if (rows.length == 0) {
                $.messager.alert('警告', '请选中至少一条记录！', 'warning');
                return;
            }
            $.messager.confirm('确认', '您确定要删除记录吗？', function (r) {
                if (r) {
                    //客户已经点击“确定”按钮
                    //定义一个空的数组，用来存放ID的集合
                    var ids = [];
                    //遍历的是客户选中的记录集合
                    for (var i = 0; i < rows.length; i++) {
                        ids.push(rows[i].id);
                    }
                    //发出ajax请求
                    //$.ajax() $.post() $.get()
                    $.post(
                        //url，提交给后台谁去处理
                        'items/batch/3',
                        //data，提交什么到后台，ids
                        {'ids[]':ids},
                        //callback,相当于$.ajax中success
                        function (data) {
//                            //string转为object
//                            var obj = JSON.parse(data);
//                            //object转换string
//                            var objString = JSON.stringify(obj);
//                            console.log(typeof objString);
//                            alert(obj.id);
//                            debugger;
                            if(data > 0){
                                $('#dgItems').datagrid('reload');
                            }
                        }
                    );
                }

            });
        }
    }, {
        iconCls: 'icon-edit',
        text: '编辑',
        handler: function () {
            console.log('edit');
        }
    }, {
        iconCls: 'icon-up',
        text: '上架',
        handler: function () {
//            debugger;
           //取到客户选中的记录集合
            var rows = $('#dgItems').datagrid('getSelections');
            if(rows.length == 0){
                $.messager.alert('警告','请选中至少一条记录!','warning');
                return;
            }
            $.messager.confirm('确认','您确定要上架么？',function (r) {
                if (r){
                    //定义一个空的数组用来存放id的集合
                    var ids = [];
                    //遍历的是客户选中的记录集合
                    for (var i = 0; i<rows.length; i++){
                        ids.push(rows[i].id);
                    }
                    //发出ajax请求
                    $.post(
                        //url,提交给后台谁去处理
                        'items/batch/1',
                        //data,提交什么到后台，ids
                        {'ids[]':ids},
                        //callback,相当于$.ajax中的success
                        function (data) {
                            if (data > 0){
                                $('#dgItems').datagrid('reload');
                            }
                        }
                    );
                }
            });
        }
    }, {
        iconCls: 'icon-down',
        text: '下架',
        handler: function () {
            //取到客户选中的记录集合
            var rows = $('#dgItems').datagrid('getSelections');
            if(rows.length == 0){
                $.messager.alert('警告','请选中至少一条记录!','warning');
                return;
            }
            $.messager.confirm('确认','您确定要下架么？',function (r) {
                if (r){
                    //定义一个空的数组用来存放id的集合
                    var ids = [];
                    //遍历的是客户选中的记录集合
                    for (var i = 0; i<rows.length; i++){
                        ids.push(rows[i].id);
                    }
                    //发出ajax请求
                    $.post(
                        //url,提交给后台谁去处理
                        'items/batch/2',
                        //data,提交什么到后台，ids
                        {'ids[]':ids},
                        //callback,相当于$.ajax中的success
                        function (data) {
                            if (data > 0){
                                $('#dgItems').datagrid('reload');
                            }
                        }
                    );
                }
            });
        }
    }];
    //初始化数据表格代码
    $('#dgItems').datagrid({
        //是否容许多列排序，默认是false
        multiSort:true,
        //添加工具栏
        toolbar: itemListToolbar,
        //初始化页面数据条数
        pageSize: 20,
        //在设置分页属性的时候 初始化页面大小选择列表
        pageList: [20, 50, 100],
        //请求服务器端数据
        url: 'items',
        //请求方式，默认是POST
        method: 'get',
        //是否显示分页工具栏
        pagination: true,
        //自适应父容器
        fit: true,
        //列属性
        columns: [[
            {field: 'ck', checkbox: true},
            {field: 'id', title: '编号', width: 100 , sortable:true},
            {field: 'title', title: '标题', width: 200,sortable:true},
            {field: 'sellPoint', title: '卖点', width: 200, align: 'right'},
            {field: 'catName', title: '商品类别', width: 100},
            {
                field: 'status', title: '商品状态', width: 100, formatter: function (value, row, index) {
//                console.group("商品状态");
//                console.log(value);
//                console.log(row);
//                console.log(index);
//                console.groupEnd();
                switch (value) {
                    case 1:
                        return '正常';
                        break;
                    case 2:
                        return '下架';
                        break;
                    case 3:
                        return '删除';
                        break;
                    default:
                        return '未知';
                        break;
                }
            }
            },
            {field: 'created', title: '创建时间',width:100, formatter:function (value,rows,index) {
                return moment(value).format('L');
            }},
            {field: 'updated', title: '更新时间',width:100, formatter:function (value,rows,index) {
                return moment(value).format('L');
            }},
            {field: 'price', title: '商品价格', width:100, formatter:function (value) {
                var priceView = value/100;
                return accounting.formatMoney(priceView,"￥");
            }}
        ]]
    });
</script>
