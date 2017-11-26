<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="easyui-panel" title="商品详情" data-options="fit:true"  style="width:100%;max-width:600px;padding:10px 20px;">
    <form class="itemForm" id="itemAddForm" name="itemAddForm" method="post">
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" id="cid" name="cid" style="width:60%" data-options="label:'商品类目:',required:true">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" id="title" name="title" style="width:90%" data-options="label:'商品标题:',required:true">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" id="sellPoint" name="sellPoint" style="width:90%;height:60px" data-options="label:'商品卖点:',validType:'length[0,150]',multiline:true">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-numberbox" type="text" id="priceView" name="priceView" style="width:40%" data-options="label:'商品价格:', required:true,min:0,precision:2">
            <input type="hidden" id="price" name="price">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-numberbox" type="text" id="num" name="num" style="width:40%" data-options="label:'商品库存:', required:true,min:0,precision:0">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" type="text" id="barcode" name="barcode" style="width:90%" data-options="label:'条形码:', validType:'length[0,30]'">
        </div>
        <div style="margin-bottom:20px">
            <!-- 加载编辑器的容器 -->
            <script id="container" name="content" type="text/plain">商品描述</script>
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" type="text" id="#" name="#" style="width:60%" data-options="label:'商品规格:'">
        </div>
    </form>
    <div style="text-align:center;padding:5px 0">
        <button onclick="submitForm()" class="easyui-linkbutton" type="button" style="width:40%"
                data-options="iconCls:'icon-ok'">保存
        </button>
        <button onclick="clearForm()" class="easyui-linkbutton" type="button" style="width:40%"
                data-options="iconCls:'icon-undo'">重置
        </button>
    </div>
</div>
<script>
    //初始化类别选择树
    $('#cid').combotree({
        url: 'itemCats/0',
        required: true,
        //在节点展开之前触发，返回false可以取消展开操作。
        onBeforeExpand: function (node) {
            //获取当前被点击的tree
            var $currentTree = $('#cid').combotree('tree');
            //调用easyui tree 组件的options方法
            var option = $currentTree.tree('options');
            //修改option的url属性
            option.url = 'itemCats/'+node.id;
        },
        //在用户选择一个节点之前触发，返回false可以取消选择动作。
        onBeforeSelect: function (node) {
            var isLeaf = $('#cid').tree('isLeaf',node.target);
            if (!isLeaf){
                $.messager.alert('警告','请选择最终类目','warning');
                return false;
            }
        }
    });
    //初始化富文本编辑器
    var  ue = UE.getEditor('container');
    //表单提交动作
    function submitForm(){
        $('#itemAddForm').form('submit',{
            //表单提交之后交给谁处理
            url: 'item',
            //表单提交之前被触发，如果返回false终止提交
            onSubmit: function () {
                //给隐藏域设置ID属性，并且设值
                $('#price').val($('#priceView').val()*100);
                return $(this).form('validate');
            },
            //表单提交成功后触发，而非item处理成功后触发
            success: function (data) {
                if (data>0){
                    $.messager.alert('消息','保存成功','info');
                    ttshop.addTab('查询商品','item-list');
                }
            }
        });
    }
    function clearForm() {
        $('#itemAddForm').form('clear');
    }
</script>