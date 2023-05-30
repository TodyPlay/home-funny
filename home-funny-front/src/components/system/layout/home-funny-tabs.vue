<template>
    <el-tabs v-model="path" type="card" @tab-remove="onTableRemove" @tab-change="onTableChange">
        <el-tab-pane v-for="(route) in routes" :key="route.path" :label="route.params?.tagName ?? route.meta?.title"
                     :name="route.path"
                     :closable="route.path !== '/index'"
        >
        </el-tab-pane>
    </el-tabs>
</template>

<script>
import {routes} from "@/router";

export default {
    data() {
        return {
            path: '/index',
            routes: {
                '/index': routes[0],
            },
        };
    },
    computed: {
        length() {
            return Object.keys(this.routes).length;
        }
    },
    methods: {
        onTableRemove(path) {
            delete this.routes[path];

            if (path === this.path) {
                this.$router.go(-1);
            }

            this.$bus.emit("tab-close", this.routes[path]);
        },
        onTableChange(path) {
            this.$router.push(this.routes[path]);
        },
    },
    watch: {
        '$route'(route) {
            route.path = decodeURIComponent(route.path);
            this.routes[route.path] = route;
            this.path = route.path;
        },
        'length'() {
            this.$emit('update:i_keepalive', Object.values(this.routes).map(v => v.name));
        }
    }
}
</script>

<style scoped>

</style>