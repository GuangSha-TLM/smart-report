<template>
  <div>

    <!-- 操作栏 -->
    <el-row type="flex" justify="end" style="margin-bottom: 20px;">
      <el-col :span="3">
        <el-button type="primary" @click="sendIsSure = true">发送</el-button>
      </el-col>
    </el-row>

    <!-- 表格展示数据 -->
    <el-table :data="tableData" style="width: 100%" border>
      <el-table-column label="ID">
            <template slot-scope="scope">{{ scope.$index + 1 }}</template>
      </el-table-column>
      <el-table-column label="名称" prop="username"></el-table-column>

    </el-table>

    
    <!-- 分页组件 -->
     <el-pagination
    :current-page="currentPage"
    :page-size="pageSize"
    :total="totalItems"
    @current-change="handlePageChange"
    layout="total, prev, pager, next, jumper"
    />
  </div>
</template>

<script>
import { formByPageLike,formDelete,formUpdate,formQuery,sendFormUser } from "@/api/form";
import { getUserDatas,pageUserDatas } from "@/api/user";

export default {
  data() {
    return {
      currentPage: 1,  // 当前页数
      pageSize: 10,    // 每页显示多少条数据
      totalItems: 0,   // 数据总数
      tableData: [],   // 表格数据
      nameIsSure: false, // 修改按钮
      sendIsSure: false, // 发送按钮
      viewIsSure: false, // 预览按钮
      formId: '', // 表单id
       selectedUsers: [], //复选框 - 》 勾选中的用户id
      formNames:{},
      // 预览时表单数据
      formData: {},
      userDatas: [], //用户数据
      // 表单配置项
      rule: [],  // 表单规则配置
      option: {},  // 表单其他选项配置
      FormNewPageBo : {
        page: 1,
        pageSize: 10
        },
        FormNewPageBoNew: {
        page: 1,
        limit: 999999999,
        name: ''
      },
      FormUpdateBo:{
        name: '',
        id : ''
      },      
      SendFormBo:{
        formId: '',
        userId: []
      }
    };
  },
  created() {
    this.fetchData(); // 组件创建时加载数据
    this.formNamesData(); // 组件创建时加载数据
    this.userData(); // 组件创建时加载数据
  },
    methods: {
        async formNamesData() {
            this.formNames = await formByPageLike(this.FormNewPageBoNew)
                .then(obj => obj.data); // 获取数据
            console.log('formNames', this.formNames);
        },
        async submitSend() {
            try {
                const obj = await pageUserDatas(this.SendFormBo); // 获取数据
                if (obj && obj.code === "0x200") {
                    console.log("成功", obj.data);
                    this.tableData = obj.data; // 设置表格数据
                    this.totalItems = obj.count; // 设置数据总数 (假设后端返回了 total 字段)
                    this.switchbutton = true; // 根据需要更新按钮状态
                    this.sendIsSure = false;
                    alert(obj.message);
                    this.fetchData();
                } else {
                    alert(obj.message);
                    this.switchbutton = false;
                }
            } catch (error) {
                console.error("请求失败", error);
                this.switchbutton = false;
            }
        },
        async userData() {
            this.userDatas = await getUserDatas()
                .then(obj => obj.data); // 获取数据
            console.log('userDatas', this.userDatas);
         },
    // 获取表单数据和配置
    async fetchFormData() {
      try {
        const response = await formQuery(this.formId);
console.log('config', response);

if (response && response.code === "0x200") {
  try {
    // 假设返回的数据包含表单规则和表单值
    //   this.rule = response.data.map(str => {
    //         console.log('str', str);
    //     return JSON.parse(str);
      //     });  // 表单规则
    this.rule = JSON.parse(response.data);
        //   this.formData = response.data || [];  // 表单回显数据
        //   this.option = response.data || {};  // 表单其他配置（例如布局等）
    } catch (error) {
        console.error("解析数据失败:", error);
    }
    }
    } catch (error) {
    console.error("请求失败", error);
    }
    },
        async fetchData() {
        try {
            const obj = await pageUserDatas(this.FormNewPageBo); // 获取数据
            if (obj && obj.code === "0x200") {
                console.log("成功", obj.data);
                this.tableData = obj.data; // 设置表格数据
                this.totalItems = obj.count; // 设置数据总数 (假设后端返回了 total 字段)
                this.switchbutton = true; // 根据需要更新按钮状态
            } else {
                alert(obj.message);
                this.switchbutton = false;
            }
        } catch (error) {
            console.error("请求失败", error);
            this.switchbutton = false;
        }
    },
    // 页码改变时触发的事件
    handlePageChange(page) {
        this.FormNewPageBo.page = page; // 更新当前页码
        this.fetchData(); // 重新获取数据
      },
      async handleEdit(row) { 
        this.FormUpdateBo.id = row.id;
         this.nameIsSure = true;
        },
        async handleDetail(row) {
            // 路由跳转，假设 `row.id` 是你想传递的参数
            this.$router.push({ name: 'formDetail', params: { id: row.id } });
      },
    async handleDelete(row){

        // 确认删除操作
        this.$confirm('你确定要删除这条数据吗?', '删除确认', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(async () => {
            // 在这里执行删除操作，例如向后端发送请求
            console.log("删除操作", row);
            try {
                const obj = await formDelete(row.id); // 获取数据
                if (obj && obj.code === "0x200") {
                    alert(obj.message);
                    this.switchbutton = true; // 根据需要更新按钮状态
                    this.fetchData();
                } else {
                    alert(obj.message);
                }
            } catch (error) {
                console.error("请求失败", error);
                this.switchbutton = false;
            }
        }).catch(() => {
            // 如果取消删除，什么也不做
            console.log("取消删除");
        });

      },
      async submitUpdate() { 
        this.nameIsSure = false;
        try {
            const obj = await formUpdate(this.FormUpdateBo); // 获取数据
            if (obj && obj.code === "0x200") {
                console.log("成功", obj.data);
                this.switchbutton = true; // 根据需要更新按钮状态
                this.fetchData();
            } else {
                alert(obj.message);
                this.switchbutton = false;
            }
        } catch (error) {
            console.error("请求失败", error);
            this.switchbutton = false;
        }
      },
        // 添加 resetFormName 方法
        resetFormName() {
        // 重置表单相关数据，如果需要可以重置 FormUpdateBo 或其他表单状态
        this.FormUpdateBo.name = '';
        this.FormUpdateBo.id = '';
        console.log("表单已重置");
        },
      handleView(row) {
          this.formId = row.id;
          this.viewIsSure = true;
          this.fetchFormData();
      }
  },
};
</script>

<style scoped>
/* 这里可以添加你的样式 */
</style>
