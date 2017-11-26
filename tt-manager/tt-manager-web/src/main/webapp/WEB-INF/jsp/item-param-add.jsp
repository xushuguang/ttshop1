<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="easyui-panel" title="商品规格参数模板详情" data-options="fit:true"  style="width:100%;max-width:600px;padding:10px 20px;">
    <form class="form" id="itemParamAddForm" name="itemParamAddForm" method="post">
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" id="cid" name="cid" style="width:60%" data-options="label:'商品类目:',required:true">
        </div>
        <div style="margin-bottom:20px">
            规格参数:
        </div>
        <div style="margin-left:80px ;margin-top:-40px;">
            <button class="easyui-linkbutton" onclick="addGroup()" type="button" data-options="iconCls:'icon-add'">添加分组</button>
            <ul id="item-param-group">

            </ul>
            <ul id="item-param-group-template" style="display:none;">
                <li>
                    <input name="group">
                    <button title="添加参数" class="easyui-linkbutton" onclick="addParam(this)" type="button" data-options="iconCls:'icon-add'"></button>
                    <button title="删除分组" class="easyui-linkbutton" onclick="delGroup(this)" type="button" data-options="iconCls:'icon-cancel'"></button>
                    <ul class="item-param">
                        <li>
                            <input name="param">
                            <button title="删除参数" class="easyui-linkbutton" onclick="delParam(this)" type="button" data-options="iconCls:'icon-cancel'"></button>
                        </li>
                    </ul>
                </li>
            </ul>
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
   //添加分组
    function addGroup(){
        var $templateLi = $('#item-param-group-template li').eq(0).clone();
        $('#item-param-group').append($templateLi);
    }
    //添加参数
    function addParam(ele){
        var  $paramLi = $('#item-param-group-template .item-param li').eq(0).clone();
        $(ele).parent().find('.item-param').append($paramLi);
    }
    function delParam(ele){
        $(ele).parent().remove();
    }
    function delGroup(ele){
        $(ele).parent().remove();
    }
    //表单提交动作
    function submitForm(){
        var groupValues = [];
        //遍历分组
        var $groups = $('#item-param-group [name=group]' );
        $groups.each(function (index,ele) {
            //遍历分组项
            var  paramValues = [];
            var $params = $(ele).parent().find('.item-param [name=param]');
            $params.each(function (_index,_ele) {
                var _val = $(ele).val();
                if ($.trim(_val).length>0){
                    paramValues.push(_val);
                }
            });
            var val = $(ele).val();
            var o  = {};
            o.group = val;
            o.params = paramValues;
            if ($.trim(val).length>0 && paramValues.length>0){
                groupValues.push(o);
            }
        });
        //得到规格参数模板的json串
        var cid = $('#cid').combotree('getValue');
        var url = 'itemParam/save/'+cid;
        var  jsonStr = JSON.stringify(groupValues);
        $.post(url,{paramData:jsonStr},function (data) {
            if (data>0){
                ttshop.closeTab('新增规格参数');
                ttshop.closeTab('查询规格参数');
                ttshop.addTab('查询规格参数','item-param-list');
                $.messager.alert('保存成功','保存商品模板成功');
            }
        });
    }
    //表单重置动作
    function clearForm() {
       $('#itemParamAddForm').form('clear');
    }
</script>