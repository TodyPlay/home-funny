<template>
    <el-container>
        <el-header>
            <el-link type="primary" href="/">
                <h1>Home Funny</h1>
            </el-link>
        </el-header>
        <el-container style="height: 830px">
            <el-aside>
                <home-funny-menu style="height: 100%"/>
            </el-aside>
            <el-main>
                <home-funny-tabs @update:i_keepalive="val => i_keepalive = val"/>
                <router-view v-slot="{ Component }">
                    <keep-alive :include="i_keepalive">
                        <component :is="Component"/>
                    </keep-alive>
                </router-view>
            </el-main>
        </el-container>
    </el-container>
</template>


<script>

import HomeFunnyMenu from "@/HomeFunnyMenu.vue";
import HomeFunnyTabs from "@/HomeFunnyTabs.vue";

export default {
    components: {HomeFunnyTabs, HomeFunnyMenu},
    data() {
        return {
            i_keepalive: []
        }
    },
}


</script>

<style>
</style>