<template>
    <div>
        <el-tabs v-model="path" type="card" @tab-remove="onTableRemove" @tab-change="onTableChange"
                 @contextmenu.native.prevent="contextMenuShow($event)"
        >
            <el-tab-pane v-for="(route) in routes" :key="route.path" :label="route.params?.tagName ?? route.meta?.title"
                         :name="route.path"
                         :closable="route.path !== '/index'"
            >
            </el-tab-pane>
        </el-tabs>
        <div v-if="contextMenu.show" :style="contextMenu.style">
            <el-button type="danger" @click="closeAllTAB">关闭所有TAB</el-button>
        </div>
    </div>

</template>

<script>
import {routes} from "@/router";

export default {
    emits: ['update:i_keepalive'],
    data() {
        return {
            path: '/index',
            routes: {
                '/index': routes[0],
            },
            contextMenu: {
                show: false,
                position: {x: 0, y: 0},
                style: ""
            }
        };
    },
    computed: {
        length() {
            return Object.keys(this.routes).length;
        }
    },
    methods: {
        onTableRemove(path) {
            let route = this.routes[path];

            delete this.routes[path];

            if (path === this.path) {
                let last = Object.keys(this.routes).slice(-1)[0];
                this.$router.push(this.routes[last]);
            }
            this.$bus.emit("tab-close", route);
        },
        onTableChange(path) {
            this.$router.push(this.routes[path]);
        },
        contextMenuShow(event) {
            this.contextMenu.show = true;
            this.contextMenu.position = {x: event.x, y: event.y};
            this.contextMenu.style = `position:absolute; z-index: 10; left: ${this.contextMenu.position.x}px; top:${this.contextMenu.position.y}px`
        },
        closeAllTAB() {
            Object.values(this.routes).filter(v => v.path !== '/index').forEach(route => {
                this.$bus.emit("tab-close", route);
            })

            this.path = "/index";
            this.routes = {
                "/index": routes[0]
            }
        },
        closeContextMenu() {
            this.contextMenu.show = false;
        }

    },
    watch: {
        '$route'(route) {
            route.path = decodeURIComponent(route.path);
            this.routes[route.path] = route;
            this.path = route.path;
        },
        'length'() {
            localStorage.setItem("tabs", JSON.stringify(this.routes));
            this.$emit('update:i_keepalive', Object.values(this.routes).map(v => v.name));

        },
        "contextMenu.show"(show) {
            if (show) {
                window.addEventListener("click", this.closeContextMenu);
            } else {
                window.removeEventListener("click", this.closeContextMenu);
            }
        },
    },
    mounted() {
        let tabs = localStorage.getItem("tabs");
        if (tabs) {
            this.routes = JSON.parse(tabs);
        }
        let route = this.$route;
        route.path = decodeURIComponent(route.path);
        this.routes[route.path] = route;
        this.path = route.path;
    },
}
</script>

<style scoped>

</style>