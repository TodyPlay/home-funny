<template>
    <el-menu :router="true" :default-active="$route.path">
        <el-menu-item v-for="route of routes.filter(v=>v.inMenu !== false)" :index="route.path">
            <el-icon>
                <component :is="route.icon"/>
            </el-icon>
            <span>{{ route.name }}</span>
        </el-menu-item>
    </el-menu>
</template>

<script>
import {routes} from "@/router";

export default {
    components: {}
    ,
    computed: {
        routes() {
            return routes;
        }
    }
    ,
    methods: {},

}
</script>

<style scoped>

</style>