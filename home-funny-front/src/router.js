import {createRouter, createWebHashHistory} from "vue-router";
import {Grid, List} from "@element-plus/icons-vue";

const routes = [
    {
        path: "/index",
        component: () => import("@/components/index.vue"),
        name: 'index',
        meta: {title: "首页", icon: Grid}
    },
    {
        path: '/media-index',
        component: () => import("@/components/media/list/media-index.vue"),
        name: "media-index",
        meta: {title: "多媒体列表", icon: List}
    },
    {
        path: "/media-detail-list/:id/:tagName",
        component: () => import("@/components/media/list/media-detail-list.vue"),
        name: "media-detail-list",
        meta: {title: "多媒体详情", icon: Grid, inMenu: false}
    },
    {
        path: "/media-management",
        component: () => import("@/components/media/management/media-management-index.vue"),
        name: "/media-management",
        meta: {title: "多媒体管理", icon: Grid}
    },
    {
        path: "/media-management-edite",
        component: () => import("@/components/media/management/media-management-edit.vue"),
        name: "/media-management-edite",
        meta: {title: "多媒体编辑页", icon: Grid, inMenu: false}
    },
    {
        path: "/storage-list",
        component: () => import("@/components/storage/storage-index.vue"),
        name: "/storage-list",
        meta: {title: "文件管理", icon: List}
    },
    {
        path: "/tags-index",
        component: () => import("@/components/tags/tags-index.vue"),
        name: "/tags-index",
        meta: {title: "标签管理", icon: List}
    }
]


let router = createRouter({
    history: createWebHashHistory(), routes: routes
});

router.beforeEach((to, from, next) => {
    next();
})

export {router, routes};