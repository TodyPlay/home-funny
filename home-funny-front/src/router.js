import {createRouter, createWebHashHistory} from "vue-router";
import {ChatLineSquare, Grid, List} from "@element-plus/icons-vue";
import Cookies from "js-cookie";
import {ElMessage} from "element-plus";

const routes = [
    {
        path: "/index",
        component: () => import("@/components/views/index.vue"),
        name: 'Index',
        meta: {title: "首页", icon: Grid}
    },
    {
        path: '/media-index',
        component: () => import("@/components/views/media/list/media-index.vue"),
        name: "MediaIndex",
        meta: {title: "多媒体列表", icon: List}
    },
    {
        path: "/media-detail-list/:id/:tagName",
        component: () => import("@/components/views/media/list/media-detail-list.vue"),
        name: "MediaDetailList",
        meta: {title: "多媒体详情", icon: Grid, inMenu: false}
    },
    {
        path: "/media-management",
        component: () => import("@/components/views/media/management/media-management-index.vue"),
        name: "MediaManagement",
        meta: {title: "多媒体管理", icon: Grid}
    },
    {
        path: "/media-management-edite",
        component: () => import("@/components/views/media/management/media-management-edit.vue"),
        name: "MediaManagementEdite",
        meta: {title: "多媒体编辑页", icon: Grid, inMenu: false}
    },
    {
        path: "/storage-list",
        component: () => import("@/components/views/storage/storage-index.vue"),
        name: "StorageList",
        meta: {title: "文件管理", icon: List}
    },
    {
        path: "/tags-index",
        component: () => import("@/components/views/tags/tags-index.vue"),
        name: "TagsIndex",
        meta: {title: "标签管理", icon: List}
    },
    {
        path: "/test-page",
        component: () => import("@/components/views/test/test-index.vue"),
        name: "TestPage",
        meta: {title: "测试页面", icon: ChatLineSquare}
    },
]

let routes_parent = [
    {
        path: "/",
        redirect: '/index',
        component: () => import("@/components/system/layout/layout-index.vue"),
    },
    {
        path: "",
        redirect: "/index",
        component: () => import("@/components/system/layout/layout-index.vue"),
        children: routes,
    },
    {
        path: "/login",
        component: () => import("@/components/system/login/login-index.vue"),
        name: "Login"
    }
]


let router = createRouter({
    history: createWebHashHistory(), routes: routes_parent
});
router.beforeEach((to, from, next) => {
    if (Cookies.get('Token') || to.path === '/login') {
        next();
    } else {
        ElMessage.error("请先登陆");
        next({
            path: "login",
            query: {
                redirect: to.fullPath
            }
        })
    }
})

export {router, routes};