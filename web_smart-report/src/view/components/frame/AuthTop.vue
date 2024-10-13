<template>
    <div class="top">
        <div class="modal fade" tabindex="-1" role="dialog" id="modal-cookies" data-backdrop="false"
            aria-labelledby="modal-cookies" aria-hidden="true">
            <div class="modal-dialog modal-dialog-aside left-4 right-4 bottom-4">
                <div class="modal-content bg-dark-dark">
                    <div class="modal-body">
                        <!-- Text -->
                        <p class="text-sm text-white mb-3">
                            We use cookies so that our themes work for you. By using our website, you agree to our use of
                            cookies.
                        </p>
                        <!-- Buttons -->
                        <a class="btn btn-sm btn-white" target="_blank">Learn more</a>
                        <button type="button" class="btn btn-sm btn-primary mr-2" data-dismiss="modal">OK</button>
                    </div>
                </div>
            </div>
        </div>
        <a class="btn btn-block btn-dark text-truncate rounded-0 py-2 d-none d-lg-block"
            style="z-index: 1000;color:aliceblue" target="_blank">
            哈尔滨广厦学院 <strong>查重系统 </strong> 测试版本 v0.1
        </a>

        <div>
            <nav class="navbar navbar-expand-lg navbar-light bg-white">
                <div class="container">
                    <!-- Brand -->
                    <a class="navbar-brand" href="/h">
                        <img alt="Image placeholder" style="height:3.4rem" src="#" id="navbar-logo">
                    </a>
                    <!-- Toggler -->
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                        aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <!-- Collapse -->
                    <div class="collapse navbar-collapse" id="navbarCollapse">
                        <ul class="navbar-nav mt-4 mt-lg-0 ml-auto">
                            <li class="nav-item ">
                                <a class="nav-link" href="#">Help </a>
                            </li>
                            <li class="nav-item " @click="taskList()">
                                <a class="nav-link" href="#">我的任务 </a>
                            </li>
                            <li class="nav-item " v-if="false" @click="goMyTask()">
                                <a class="nav-link" href="#">已开始的任务 </a>
                            </li>
                            <li class="nav-item " @click="exit">  
                                <a class="nav-link" href="#">退出 </a>
                            </li>
                        </ul>

                    </div>
                </div>
            </nav>


        </div>
    </div>
</template>
  
<script>

import {synRequestPost,synRequestGet} from "../../../../static/request"

export default {
    
    name: 'Foot',
    data() {
        return {
           
            
        }
    },
    mounted() {
        this.auth();
    },
    methods: {
        //去tasklist
        taskList(){
            this.$router.push("/TaskList");
        },

        goMyTask(){
            this.$router.push("/user/MyTask");
        },

        //前往去登入
        async auth() {
            let obj = await synRequestPost("/user/auth?token="+getCookie("token"));
            console.log(obj);
            console.log(obj == null);
            if(obj.code != "0x200"){
                this.$router.push("/user/login");
            }
        },

        exit(){
            this.$router.push("/user/login");
        }
    }
}
</script>
  
  
  