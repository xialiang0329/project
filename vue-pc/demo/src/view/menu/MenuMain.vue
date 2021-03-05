<template>
    <div style="height: 100%;">
      <el-row :gutter="20">
        <el-col :span="5">
          <div style="background-color: #FFF;padding: 2px;">
            <el-input
              placeholder="输入关键字进行过滤"
              v-model="filterText">
            </el-input>
          </div>
          <div class="outer-container " style="background-color: #FFF;height: 95%">
            <div class="inner-container" >
              <el-tree class="filter-tree"
                       :data="menuList"
                       :props="defaultProps"
                       :filter-node-method="filterNode"
                       ref="tree"
                       @node-click="handleNodeClick">
                <span class="custom-tree-node" slot-scope="{ node, data }">
                  <span :class="checkedMenu.pkid == data.pkid ? 'node-color-blue' : 'node-color-none'">{{ node.label }}</span>
                </span>
               </el-tree>
            </div>
          </div>
        </el-col>
        <el-col :span="19">
          <div style="background-color: #FFF;height: 100%">
              <el-form  :rules="rules" ref="ruleForm" :model="form" class="demo-ruleForm"  label-width="80px" style="padding: 10%">
                <el-form-item label="上级菜单" >
                  <el-input v-model="checkedMenu.name" disabled></el-input>
                </el-form-item>
                <el-form-item label="菜单名称" prop="name">
                  <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="菜单图标">
                  <el-input v-model="form.icon"></el-input>
                </el-form-item>
                <el-form-item label="菜单路径">
                  <el-input v-model="form.url"></el-input>
                </el-form-item>
                <el-form-item label="菜单类型">
                  <el-select v-model="form.type" style="width: 100%">
                    <el-option  label="pc" value="pc"></el-option>
                    <el-option  label="mobile" value="mobile"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="onSubmit">提交</el-button>
                </el-form-item>
              </el-form>
          </div>
        </el-col>
      </el-row>
    </div>
</template>

<script>
  import {addMenu, queryMenuList} from "../../api/menu";
    import dateFormat from "../../common/util";

    export default {
      name: "MenuMain",
      watch: {
        filterText(val) {
          this.$refs.tree.filter(val);
        }
      },
      data() {
        return {
          filterText: '',
          menuList: [],
          checkedMenu: {},
          defaultProps: {
            children: 'children',
            label: 'name'
          },
          form: {
            pkid:'',
            type:'pc',
            name: '',
            icon: '',
            parentId:'',
            sort:'',
            url: '',
            createTime:'',
            children:[]
          },
          rules: {
            name: [
              {required: true, message: '请输入菜单名称', trigger: 'blur'},
              {max: 50, message: '长度在 1 到 50 个字符', trigger: 'blur'}
            ]
          }
        }
      },
      created() {
          this.initMain();
      },
      methods: {
        initMain(){
          let $this = this;
          $this.getMenuList();
        },
        getMenuList(){
          let $this = this;
          //查询菜单集合
          queryMenuList().then(res =>{
            $this.menuList = res.data;
          }).catch(error =>{
            $this.$message({
              message: '菜单查询出错!',
              type: 'error'
            });
          })
        },
        filterNode(value, data) {
          if (!value) return true;
          return data.name.indexOf(value) !== -1;
        },
        handleNodeClick(data) {
          let $this = this;
          if (!$.isEmptyObject($this.checkedMenu) && data.pkid == $this.checkedMenu.pkid) {
            $this.checkedMenu = {};
            return;
          }
          $this.checkedMenu = data;
          $this.form = JSON.parse(JSON.stringify(data));
        },

        onSubmit() {
          let $this = this;
          $this.form.createTime = dateFormat('YYYY-mm-dd HH:MM:SS',new Date());
          if ($.isEmptyObject($this.checkedMenu)) {
            //顶级
            $this.form.parentId = '-1';
            $this.form.sort = $this.menuList.length;
          } else {
            $this.form.parentId = $this.checkedMenu.pkid;
            $this.form.sort = $this.checkedMenu.children.length;
          }
          addMenu($this.form).then(res =>{
            $this.$message({message: "新增成功!", type: 'success'});
            $this.form.pkid = res.data;
            $this.form.children = [];
            if (!$.isEmptyObject($this.checkedMenu)) {
              $this.checkedMenu.children.push($this.form);
            } else {
              $this.menuList.push($this.form);
            }
            $this.resetForm("ruleForm");
            $this.$store.commit('setMenuList',$this.menuList);
          }).catch(error =>{
            $this.$message({
              message: error.response.data.message,
              type: 'error'
            });
          })
        },
        resetForm(formName) {
          this.$refs[formName].resetFields();
        }
      }
    }
</script>

<style scoped>
  .el-row {
    height: 100%;
    margin-bottom: 20px;
  }
  .el-col {
    height: 100%;
    border-radius: 4px;
  }

  .node-color-blue{
    font-size: 14px;
    color: #409EFF;
  }
  .node-color-none{
    font-size: 14px;
  }

</style>
