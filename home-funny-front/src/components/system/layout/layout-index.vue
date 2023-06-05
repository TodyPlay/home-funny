<template>
    <el-container>
        <el-header>
            <el-link type="primary" href="/">
                <h1>Home Funny</h1>
            </el-link>
            <el-button @click="handleLogout" type="danger" size="small"
                       style="position: absolute;right: 10px; top: 10px">注销
            </el-button>
        </el-header>
        <el-container style="height: 830px">
            <el-aside>
                <home-funny-menu style="height: 100%"/>
            </el-aside>
            <el-main>
                <home-funny-tabs @update:i_keepalive="val => i_keepalive = val"/>
                <router-view v-slot="{ Component, route}">
                    <transition name="fade" mode="out-in">
                        <keep-alive :include="i_keepalive">
                            <component :is="Component" :key="route.path"/>
                        </keep-alive>
                    </transition>
                </router-view>
            </el-main>
        </el-container>
    </el-container>
</template>


<script>

import HomeFunnyMenu from "@/components/system/layout/home-funny-menu.vue";
import HomeFunnyTabs from "@/components/system/layout/home-funny-tabs.vue";
import {restApi} from "@/api/restApi";

export default {
    components: {HomeFunnyTabs, HomeFunnyMenu},
    data() {
        return {
            i_keepalive: []
        }
    }, methods: {
        async handleLogout() {
            try {
                await restApi.do_login_out();
                this.$cookies.remove("Token");
                this.$router.push("/login");
                this.$message.success("登出成功")
            } catch (e) {
                console.log(e);
                this.$message.error("登出失败")
            }
        }
    },
}


</script>

<style>

/* 路由切换动画 */
/* fade-transform */
.fade-leave-active,
.fade-enter-active {
    transition: all 0.1s;
}

/* 可能为enter失效，拆分为 enter-from和enter-to */
.fade-enter-from {
    opacity: 0;
    transform: translateY(30px);
}

.fade-enter-to {
    opacity: 1;
    transform: translateY(0px);
}

.fade-leave-to {
    opacity: 0;
    transform: translateY(0px);
}
</style>