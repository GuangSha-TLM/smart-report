<template>
    <div id="app">
        <div class="content">
            <div class="Left_content">
                <el-row class="tac">
                    <el-col :span="23">
                        <h5>智能报表导航</h5>
                        <el-menu router="true" default-active="1" class="el-menu-vertical-demo">
                            <el-submenu v-if="role == 2" index="1">
                                <template slot="title">
                                    <i class="el-icon-location"></i>
                                    <span>表单板块</span>
                                </template>
                                <el-menu-item index="/form">创建表单</el-menu-item>
                                <el-menu-item index="/myForm">我的表单</el-menu-item>
                            </el-submenu>                            
                            <el-submenu index="2">
                                <template slot="title">
                                    <i class="el-icon-location"></i>
                                    <span>用户板块</span>
                                </template>
                                <el-menu-item index="/userinfo">用户信息</el-menu-item>
                                <el-menu-item index="/myReserve">消息</el-menu-item>
                            </el-submenu>
                            <!-- <el-menu-item index="/userinfo">
                                <i class="el-icon-menu"></i>
                                <span slot="title">用户板块</span>
                            </el-menu-item> -->
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
  import { getCookie } from "../../../static/ZuiBlog/ZuiBlog";

export default {
    name: 'App',
    data() {
        return {
            role: '',
        }
    },
    created() {
        this.role = getCookie("role");
    },
}
</script>
<style>
.content {
    display: flex;
    justify-content: center;
    width: 100%;
    height: 850px;
    /* background-color: aqua; */

    .Left_content {
        width: 14%;
        /* background-color: pink; */
    }

    .Rright_content {
        width: 80%;
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
