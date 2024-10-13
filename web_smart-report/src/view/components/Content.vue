<template>
    <div id="app">
        <div class="content">
            <div class="Left_content">
                <el-row class="tac">
                    <el-col :span="23">
                        <h5>智能报表导航</h5>
                        <el-menu router="true" default-active="1" class="el-menu-vertical-demo">
                            <el-submenu index="1">
                                <template slot="title">
                                    <i class="el-icon-location"></i>
                                    <span>表单板块</span>
                                </template>
                                <el-menu-item index="1-1">选项1</el-menu-item>
                                <el-menu-item index="1-2">选项2</el-menu-item>
                            </el-submenu>
                            <el-menu-item index="/userinfo">
                                <i class="el-icon-menu"></i>
                                <span slot="title">用户板块</span>
                            </el-menu-item>
                        </el-menu>
                    </el-col>
                </el-row>
            </div>
            <div class="Rright_content">
                <router-view />
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'App'
}
</script>
<style>
.content {
    display: flex;
    justify-content: center;
    width: 100%;
    height: 500px;
    /* background-color: aqua; */

    .Left_content {
        width: 14%;
        /* background-color: pink; */
    }

    .Rright_content {
        width: 50%;
        /* background-color: yellow; */
    }

    /* el-card的样式 */
    .text {
        font-size: 14px;
    }

    .item {
        padding: 18px 0;
    }

    .box-card {
        width: 100%;
    }
}
</style>
