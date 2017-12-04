<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div id="paramListToolbar">
    <div>
        <button type="button" onclick="paramAdd()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</button>
        <button type="button" onclick="paramEdit()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</button>
        <button type="button" onclick="paramDel()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true">删除</button>
    </div>
</div>
<table id="dgParamList"></table>
<script>
    $(function () {
        //列表
        $('#dgParamList').datagrid({
            //数据表格的标题
            title: '商品规格模板列表',
            //显示行号
            rownumbers: true,
            //是否容许多列排序，默认是false
            multiSort:true,
            //添加工具栏
            toolbar: '#paramListToolbar',
            //初始化页面数据条数
            pageSize: 10,
            //在设置分页属性的时候 初始化页面大小选择列表
            pageList: [10, 20, 50],
            //请求服务器端数据
            url: 'itemParam/findByPage',
            //请求方式，默认是POST
            method: 'get',
            //是否显示分页工具栏
            pagination: true,
            //自适应父容器
            fit: true,
            columns:[[
                {field:'ck',checkbox: true},
                {field:'id',title:'ID', sortable: true},
                {field:'itemCatName',title:'商品类目'},
                {field:'paramData', title:'规格(只显示分组名称)',formatter:function (value,row,index) {
                    //js中把字符串转换为对象（反序列化）
                    //js中把对象转换字符串（序列化）
                    var json = JSON.parse(value);
                    var array = [];
                    $.each(json,function(i,e){
                        array.push(e.group);
                    });
                    return array.join(",");
                }},
                {field:'created',title:'创建日期',formatter: function(value,row,index) {
                    return moment(value).format('YYYY年MM月DD日,hh:mm:ss');
                }},
                {field:'updated',title:'更新日期', formatter:function(value,row,index){
                    return moment(value).format('YYYY年MM月DD日,hh:mm:ss');
                }}
            ]]
        });
    });
    function paramAdd(){
        ttshop.addTab('新增规格参数','item-param-add');
    }
    function paramEdit(){

    }
    function paramDel(){
        //取到客户选中的记录集合
        var rows = $('#dgParamList').datagrid('getSelections');
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
                $.post(
                    //url，提交给后台谁去处理
                    'itemParam/delete',
                    //data，提交什么到后台，ids
                    {'ids[]':ids},
                    //callback,相当于$.ajax中success
                    function (data){
                        console.log(data);
                        if(data > 0){
                            $('#dgParamList').datagrid('reload');
                        }
                    }
                );
            }

        });
    }
</script>