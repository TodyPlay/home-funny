<template>
    <el-tabs v-model="path" type="card" @tab-remove="onTableRemove" @tab-change="onTableChange">
        <el-tab-pane v-for="(route) in routes" :key="route.path" :label="route.params?.tagName ?? route.name"
                     :name="route.path"
                     :closable="route.path !== '/'"
        >
        </el-tab-pane>
    </el-tabs>
</template>

<script>
export default {
    data() {
        return {
            path: '/',
            routes: {
                '/': {
                    path: '/',
                    name: '首页',
                }
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